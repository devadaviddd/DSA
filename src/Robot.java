import com.sun.security.jgss.GSSUtil;

public class Robot {
    private int visitedRow = 1000; // origin (1000, 1000)
    private int visitedCol = 1000;
    private final boolean[][] visitedGraph = new boolean[2000][2000];
    static  LinkedListStack<Cell> stackDirection;
    private char[][] drawMap;

    public void navigate() {
        Maze maze = new Maze();
        String result = "";
        stackDirection = new LinkedListStack<>();
        long time1  = System.currentTimeMillis();

        drawMap = new char[maze.getRows()][maze.getCols()]; //this is used for illustration only
        convertTo2Darr(maze.getMap());

        // clockwise rotation: UP -> RIGHT -> DOWN -> LEFT
        // 0 --> 1 --> 2 --> 3

        // go return false | win | true
        visitedGraph[visitedRow][visitedCol] = true;
        stackDirection.push(new Cell("SPAWNING", 0));

        while (!result.equals("win")) {
            Cell currentCell = stackDirection.peek(); // 0 iteration 2 --> 1
            int pattern = currentCell.getPattern();
            // advance to follow the direction of clockwise direction
            currentCell.setPattern(currentCell.getPattern() + 1);
            // UP
            if(pattern == 0) {
                if(!visitedGraph[visitedRow - 1][visitedCol]) {
                    result = maze.go("UP");
                    if(result.equals("true")) {
                        visitedGraph[visitedRow - 1][visitedCol] = true;
                        visitedRow--;
                        System.out.println("UP:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("UP"));

                    } else if(result.equals("win")) {
                        visitedRow--;
                        System.out.println("UP:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        stackDirection.push(new Cell("UP"));
                        System.out.println("Total steps to reach the Exit the maze is: " + maze.getSteps());
                        break;
                    }
                }
            } else if(pattern == 1) { // RIGHT
                if(!visitedGraph[visitedRow][visitedCol + 1]) {
                    result = maze.go("RIGHT");
                    if (result.equals("true")) {
                        visitedGraph[visitedRow][visitedCol + 1] = true;
                        visitedCol++;
                        System.out.println("RIGHT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("RIGHT"));

                    } else if(result.equals("win")) {
                        visitedCol++;
                        System.out.println("RIGHT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        stackDirection.push(new Cell("RIGHT"));
                        System.out.println("Total steps to reach the Exit the maze is: " + maze.getSteps());
                        break;
                    }
                }
            } else if(pattern == 2) { // DOWN
                if(!visitedGraph[visitedRow + 1][visitedCol]) {
                    result = maze.go("DOWN");
                    if (result.equals("true")) {
                        visitedGraph[visitedRow + 1][visitedCol] = true;
                        visitedRow++;
                        System.out.println("DOWN:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("DOWN"));
                    } else if(result.equals("win")) {
                        visitedRow++;
                        System.out.println("DOWN:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        stackDirection.push(new Cell("DOWN"));
                        System.out.println("Total steps to reach the Exit the maze is: " + maze.getSteps());
                        break;
                    }
                }
            } else if(pattern == 3) { // LEFT
                if(!visitedGraph[visitedRow][visitedCol - 1]) {
                    result = maze.go("LEFT");
                    if(result.equals("true")) {
                        visitedGraph[visitedRow][visitedCol - 1] = true;
                        visitedCol--;
                        System.out.println("LEFT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        drawMap[maze.getRobotRow()][maze.getRobotCol()] = '+';
                        stackDirection.push(new Cell("LEFT"));
                    } else if(result.equals("win")) {
                        visitedCol--;
                        System.out.println("LEFT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                                + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
                        stackDirection.push(new Cell("LEFT"));
                        System.out.println("Total steps to reach the Exit the maze is: " + maze.getSteps());
                        break;
                    }
                }
            } else {
                Cell backtrackCell = stackDirection.peek();
                stackDirection.pop();
                advanceRobotCoordinate(backtrackCell.getDirection(), maze);
            }
        }
        System.out.println("Time to exit the maze (ms): " + (System.currentTimeMillis() - time1));
        printPath();
    }

    private void advanceRobotCoordinate(String direction, Maze maze) {
        // reverse coordinate
        if(direction.equals("UP")) { // move back DOWN
            maze.go("DOWN");
            visitedRow++;
            System.out.println("DOWN:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                    + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
        } else if(direction.equals("RIGHT")) { // move back LEFT
            maze.go("LEFT");
            visitedCol--;
            System.out.println("LEFT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                    + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
        } else if(direction.equals("DOWN")) { // move back UP
            maze.go("UP");
            visitedRow--;
            System.out.println("UP:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                    + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
        } else if(direction.equals("LEFT")) { // move back RIGHT
            maze.go("RIGHT");
            visitedCol++;
            System.out.println("RIGHT:" + " Coordinate of the robot in class Maze:"  + " (" +  maze.getRobotRow() + "," + maze.getRobotCol()  + ")"
                    + " Coordinate of the robot in class Robot:" + " (" + visitedRow + "," + visitedCol + ")");
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
