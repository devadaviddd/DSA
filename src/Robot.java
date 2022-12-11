import javax.sql.rowset.RowSetWarning;

public class Robot {
    private int row = 1000;
    private int col = 1000;
    /*private Linkedlist<Coordinate> visitedList;*/

    private boolean[][] visitedGraph = new boolean[2000][2000];

    static  LinkedListStack<Cell> stackDirection;


    private char[][] drawMap;
    // A very simple implementation
    // where the robot just go randomly
    public void navigate() {
        Maze maze = new Maze();
        String result = "";
        stackDirection = new LinkedListStack<>();
        int pair = 0;
        drawMap = new char[maze.getRows()][maze.getCols()];

        convertTo2Darr(maze.getMap());

        /*visitedList = new Linkedlist<>();*/
        /*visitedList.appendNode(new Coordinate(row, col));*/
        /*visitedGraph[row][col] = true;*/

        // clockwise rotation: UP -> RIGHT -> DOWN -> LEFT
        // 0 --> 1 --> 2 --> 3

        // go return false | win | true
        visitedGraph[row][col] = true;
        Cell initialStart = initialDirection(maze);
        stackDirection.push(initialStart);
        visitedGraph[row][col] = true;


        while (!result.equals("win")) {
            Cell currentCell = stackDirection.peek();
            int pattern = currentCell.getPattern();

            // advance to follow the direction of anti clock
            currentCell.setPattern(currentCell.getPattern() + 1);
            stackDirection.pop();
            stackDirection.push(currentCell);


            // UP
            if(pattern == 0) {
                if(!visitedGraph[row - 1][col]) {
                    result = maze.go("UP");
                    if(result.equals("true")) {
                        visitedGraph[row - 1][col] = true;
                        row--;
                        System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("UP"));

                    } else if(result.equals("win")) {
                        row--;
                        System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        break;
                    }
                }
            } else if(pattern == 1) { // LEFT
                if(!visitedGraph[row][col + 1]) {
                    result = maze.go("RIGHT");
                    if (result.equals("true")) {
                        visitedGraph[row][col + 1] = true;
                        col++;
                        System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';                        stackDirection.push(new Cell("RIGHT"));

                    } else if(result.equals("win")) {
                        col++;
                        System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        break;
                    }
                }
            } else if(pattern == 2) { // DOWN
                if(!visitedGraph[row + 1][col]) {
                    result = maze.go("DOWN");
                    if (result.equals("true")) {
                        visitedGraph[row + 1][col] = true;
                        row++;
                        System.out.println("DOWN: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';                        stackDirection.push(new Cell("DOWN"));
                    } else if(result.equals("win")) {
                        row++;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        break;
                    }
                }
            } else if(pattern == 3) { // RIGHT
                if(!visitedGraph[row][col - 1]) {
                    result = maze.go("LEFT");
                    if(result.equals("true")) {
                        visitedGraph[row][col - 1] = true;
                        col--;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("LEFT"));
                    } else if(result.equals("win")) {
                        col--;
                        System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");
                        break;
                    }
                }
            } else {
                visitedGraph[row][col] = false;
                Cell backtrackCell = stackDirection.peek();
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
            row++;
            System.out.println("DOWN: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");


            /*peek.setPattern(2);*/
        } else if(direction.equals("LEFT")) { // move back RIGHT
            maze.go("RIGHT");
            col++;
            System.out.println("RIGHT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");


            /*peek.setPattern(3);*/
        } else if(direction.equals("DOWN")) { // move back UP
            maze.go("UP");
            row--;
            System.out.println("UP: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");


            /*peek.setPattern(0);*/
        } else if(direction.equals("RIGHT")) { // move back LEFT
            maze.go("LEFT");
            col--;
            System.out.println("LEFT: " + maze.getRobotRow() + " " + maze.getRobotCol() + " " + "(" + row + "," + col + ")");


            /*peek.setPattern(1);*/
        }

    }

    private Cell initialDirection(Maze maze) {
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
    }

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
