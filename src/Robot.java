import java.util.Arrays;

class Robot {
    private int row;
    private int col;

    public Robot(int robotRow, int robotColumn) {
        row = robotRow;
        col = robotColumn;
    }

    public char[][] convertTo2Darr(String[] map) {
        char[][] maze = new char[map.length][map[0].length()];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length(); j++) {
                maze[i][j] = map[i].charAt(j);
            }
        }
        return maze;
    }

    public int[] coordinateOfFinishPoint(char[][] map) {
        int[] coordinateOfX = new int[2];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'X') {
                    coordinateOfX[0] = i;
                    coordinateOfX[1] = j;
                }

            }
        }
        return coordinateOfX;
    }

    public void navigate() {

        Maze maze = new Maze(row, col);

        maze.printMaze();

        // convert String[] to char[][]

        char[][] map = convertTo2Darr(maze.map);

        // pass the mapChar2D to Maze class
        maze.mapChar2D = map;

        boolean[][] visited = new boolean[map.length][map[0].length];


        // Initially starting point of the robot
        int currentRow = row;
        int currentCol = col;

        // Coordinate of X point
        int finishRow = coordinateOfFinishPoint(map)[0];
        int finishCol = coordinateOfFinishPoint(map)[1];

        // Create a Stack ADT
        ArrayStack<Cell> stack = new ArrayStack<>();

        Cell currentCell = new Cell(currentRow, currentCol);

        stack.push(currentCell);

        while (!stack.isEmpty()) {
            // Peek the Top Cell
            currentCell = stack.peek();
            int direction = currentCell.getDirection(); // 0 --> Up || 1 --> Left || 2 --> Down || 3 --> Right
            currentRow = currentCell.getX();
            currentCol = currentCell.getY();

            // Pass coordinate of current robot && finish point && visited Map into Maze class
            maze.passDataToMaze(currentRow, currentCol, finishRow, finishCol, visited);

            //  move to clockwise according to the direction state of the cell by increasing the direction variable
            currentCell.setDirection(currentCell.getDirection() + 1);

            // Pop the Top Cell and Push again to update the new direction state for that current Cell
            stack.pop();
            stack.push(currentCell);

            // Base case if we reach the Exit Gate
            if(currentRow == finishRow && currentCol == finishCol) {
                System.out.println("Exit");
                break;
            }

            // If direction is Up
            if(direction == 0) {
                if(maze.go("UP").equals("true")) {
                    System.out.println("UP");
                    Cell nextCell = new Cell(currentRow - 1, currentCol);
                    visited[currentRow - 1][currentCol] = true;
                    stack.push(nextCell);
                }
            } else if(direction == 1) { // if direction is LEFT
                if(maze.go("LEFT").equals("true")) {
                    System.out.println("LEFT");
                    Cell nextCell = new Cell(currentRow, currentCol - 1);
                    visited[currentRow][currentCol - 1] = true;
                    stack.push(nextCell);
                }
            } else if(direction == 2) { // if direction is DOWN
                if(maze.go("DOWN").equals("true")) {
                    System.out.println("DOWN");
                    Cell nextCell = new Cell(currentRow + 1, currentCol);
                    visited[currentRow + 1][currentCol] = true;
                    stack.push(nextCell);
                }
            } else if(direction == 3) { // if direction is RIGHT
                if(maze.go("RIGHT").equals("true")) {
                    System.out.println("RIGHT");
                    Cell nextCell = new Cell(currentRow , currentCol + 1);
                    visited[currentRow][currentCol + 1] = true;
                    stack.push(nextCell);
                }
            } else {
                /* if both direction return false then retract to the cell where the robot located*/
                visited[currentCell.getX()][currentCell.getY()] = false;
                stack.pop();
            }
        }

    }

}

