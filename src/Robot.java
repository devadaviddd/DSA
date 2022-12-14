public class Robot {
    private int visitedRow = 1000; // origin (1000, 1000)
    private int visitedCol = 1000;


    private boolean[][] visitedGraph = new boolean[2000][2000];

    /*private Linkedlist<Coordinate> visitedList;*/
    static  LinkedListStack<Cell> stackDirection;


    private char[][] drawMap;
    // A very simple implementation
    // where the robot just go randomly
    public void navigate() {
        Maze maze = new Maze();
        String result = "";
        stackDirection = new LinkedListStack<>();

        drawMap = new char[maze.getRows()][maze.getCols()]; //this is used for illustration only
        convertTo2Darr(maze.getMap());

        /*visitedList = new Linkedlist<>();*/
        /*visitedList.appendNode(new Coordinate(row, col));*/
        /*visitedGraph[row][col] = true;*/

        // clockwise rotation: UP -> RIGHT -> DOWN -> LEFT
        // 0 --> 1 --> 2 --> 3

        // go return false | win | true
        visitedGraph[visitedRow][visitedCol] = true;
        stackDirection.push(new Cell("SPAWNING", 0));

        while (!result.equals("win")) {
            Cell currentCell = stackDirection.peek(); // 0 iteration 2 --> 1
            int pattern = currentCell.getPattern();

            // advance to follow the direction of anti clock
            currentCell.setPattern(currentCell.getPattern() + 1);
            stackDirection.pop();
            stackDirection.push(currentCell);

            // UP
            if(pattern == 0) {
                if(!visitedGraph[visitedRow - 1][visitedCol]) {
                    result = maze.go("UP");
                    if(result.equals("true")) {
                        visitedGraph[visitedRow - 1][visitedCol] = true;
                        visitedRow--;
                        System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("UP"));

                    } else if(result.equals("win")) {
                        visitedRow--;
                        System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        break;
                    }
                }
            } else if(pattern == 1) { // RIGHT
                if(!visitedGraph[visitedRow][visitedCol + 1]) {
                    result = maze.go("RIGHT");
                    if (result.equals("true")) {
                        visitedGraph[visitedRow][visitedCol + 1] = true;
                        visitedCol++;
                        System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';                        stackDirection.push(new Cell("RIGHT"));

                    } else if(result.equals("win")) {
                        visitedCol++;
                        System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        break;
                    }
                }
            } else if(pattern == 2) { // DOWN
                if(!visitedGraph[visitedRow + 1][visitedCol]) {
                    result = maze.go("DOWN");
                    if (result.equals("true")) {
                        visitedGraph[visitedRow + 1][visitedCol] = true;
                        visitedRow++;
                        System.out.println("DOWN: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';                        stackDirection.push(new Cell("DOWN"));
                    } else if(result.equals("win")) {
                        visitedRow++;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        break;
                    }
                }
            } else if(pattern == 3) { // LEFT
                if(!visitedGraph[visitedRow][visitedCol - 1]) {
                    result = maze.go("LEFT");
                    if(result.equals("true")) {
                        visitedGraph[visitedRow][visitedCol - 1] = true;
                        visitedCol--;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("LEFT"));
                    } else if(result.equals("win")) {
                        visitedCol--;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
                        break;
                    }
                }
            } else {
/*
                visitedGraph[row][col] = false; // cell dang dung robot --> unvisited
*/
                Cell backtrackCell = stackDirection.peek(); // cordinate cell dang dung
                stackDirection.pop();

                // reset the pattern for the next cell

                advanceRobotCoordinate(backtrackCell.getDirection(), stackDirection.peek(), maze);


            }
        }
        printPath();

    }

    private void advanceRobotCoordinate(String direction, Cell peek, Maze maze) {
        // reverse coordinate

        if(direction.equals("UP")) { // move back DOWN
            maze.go("DOWN");
            visitedRow++;
            System.out.println("DOWN: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");

            /*peek.setPattern(2);*/
        } else if(direction.equals("RIGHT")) { // move back LEFT
            maze.go("LEFT");
            visitedCol--;
            System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");


            /*peek.setPattern(1);*/
        } else if(direction.equals("DOWN")) { // move back UP
            maze.go("UP");
            visitedRow--;
            System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");


            /*peek.setPattern(0);*/
        } else if(direction.equals("LEFT")) { // move back RIGHT
            maze.go("RIGHT");
            visitedCol++;
            System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + visitedRow + "," + visitedCol + ")");
        }
    }

/*    private Cell initialDirection(Maze maze) {
        if(maze.go("UP").equals("true")) {
            row--;
            System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
            drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';

            return new Cell("UP", 0);
        } else if(maze.go("LEFT").equals("true")) {
            System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
            drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
            col--;

            return new Cell("LEFT", 3);
        } else if(maze.go("DOWN").equals("true")) {
            row++;
            System.out.println("DOWN: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
            drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';


            return new Cell("DOWN", 2);
        } else if(maze.go("RIGHT").equals("true")) {
            col++;
            System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
            drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';

            return new Cell("RIGHT", 1);
        } else {
            return new Cell("constraint cover", 5);
        }
    }*/

    public void convertTo2Darr(String[] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length(); j++) {
                drawMap[i][j] = map[i].charAt(j);
            }
        }

    }

    public void printPath() {
        for(int i = 0; i < drawMap.length; i++) {
            for(int j = 0; j < drawMap[i].length; j++) {
                System.out.print(drawMap[i][j]);
            }
            System.out.println();
        }
    }



}
