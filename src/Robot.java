import java.util.LinkedList;

class Robot {

    public Robot() {

    }

    /* navigate ( while algorithm --> breakdown maze.go() */
    public void navigate() {

        Maze maze = new Maze();

        maze.printMaze();

        // convert String[] to char[][]
        char[][] map = maze.getMapChar2D();

        boolean[][] visited = maze.getVisited();

        // Initially starting point of the robot
        int currentRow = maze.getRobotRow();
        int currentCol = maze.getRobotCol();

        visited[currentRow][currentCol] = true;

        // Create a Stack ADT
        ArrayStack<Cell> stack = new ArrayStack<>();

        Cell currentCell = new Cell(currentRow, currentCol);

        stack.push(currentCell);


        LinkedList<Cell> listCellPass = new LinkedList<>();
        listCellPass.add(currentCell);

        String result = "";
        while (!result.equals("win")) {
            // Peek the Top Cell
            currentCell = stack.peek();
            int direction = currentCell.getDirection(); // 0 --> Up || 1 --> Left || 2 --> Down || 3 --> Right
            currentRow = currentCell.getX();
            currentCol = currentCell.getY();

            // Pass coordinate of current robot && finish point && visited Map into Maze class
            maze.passDataToMaze(currentRow, currentCol, visited);

            //  move to clockwise according to the direction state of the cell by increasing the direction variable
            currentCell.setDirection(currentCell.getDirection() + 1);


            // Pop the Top Cell and Push again to update the new direction state for that current Cell
            stack.pop();
            stack.push(currentCell);


            // If direction is Up
            if(direction == 0) {
                result = maze.go("UP");
                if(result.equals("true")) {

                    Cell nextCell = new Cell(currentRow - 1, currentCol);
                    visited[currentRow - 1][currentCol] = true;

                    /* direction reverse */
                    currentCell.reversePath = "DOWN";
                    listCellPass.add(currentCell);

                    stack.push(nextCell);
                }
            } else if(direction == 1) { // if direction is LEFT
                result = maze.go("LEFT");

                if(result.equals("true")) {
                    Cell nextCell = new Cell(currentRow, currentCol - 1);
                    visited[currentRow][currentCol - 1] = true;

                    /* direction reverse */
                    currentCell.reversePath = "RIGHT";
                    listCellPass.add(currentCell);

                    stack.push(nextCell);
                }
            } else if(direction == 2) { // if direction is DOWN
                result = maze.go("DOWN");
                if(result.equals("true")) {
                    Cell nextCell = new Cell(currentRow + 1, currentCol);
                    visited[currentRow + 1][currentCol] = true;

                    /* direction reverse */
                    currentCell.reversePath = "UP";
                    listCellPass.add(currentCell);

                    stack.push(nextCell);
                }
            } else if(direction == 3) { // if direction is RIGHT
                result = maze.go("RIGHT");
                if(result.equals("true")) {
                    Cell nextCell = new Cell(currentRow , currentCol + 1);

                    visited[currentRow][currentCol + 1] = true;

                    /* direction reverse */
                    currentCell.reversePath = "LEFT";
                    listCellPass.add(currentCell);

                    stack.push(nextCell);
                }

            } else {
                /* if both direction return false then retract to the cell where the robot located*/
                visited[currentCell.getX()][currentCell.getY()] = false;
                /*System.out.println("Back " +  currentCell.path);*/
                System.out.print("ROBOT BACKTRACKING " + listCellPass.getLast().reversePath + " TO THE NEW COORDINATE ");
                switch (listCellPass.getLast().reversePath) {
                    case "UP" -> System.out.print("[ " + (currentRow - 1) + ", " + (currentCol) + " ]" + Main.ANSI_CYAN+ " [RETRACT]" + Main.ANSI_RESET + " " + Main.ANSI_GREEN+ "[UP]" + Main.ANSI_RESET);
                    case "DOWN" -> System.out.print("[" + (currentRow + 1) + ", " + (currentCol) + "]" + Main.ANSI_CYAN+ " [RETRACT]" + Main.ANSI_RESET + " " + Main.ANSI_GREEN+ "[DOWN]" + Main.ANSI_RESET);
                    case "LEFT" -> System.out.print("[" + (currentRow) + ", " + (currentCol - 1) + "]" + Main.ANSI_CYAN+ " [RETRACT]" + Main.ANSI_RESET + " " + Main.ANSI_GREEN+ "[LEFT]" + Main.ANSI_RESET);
                    case "RIGHT" -> System.out.print("[" + (currentRow) + ", " + (currentCol + 1) + "]" + Main.ANSI_CYAN+ " [RETRACT]" + Main.ANSI_RESET + " " + Main.ANSI_GREEN+ "[RIGHT]" + Main.ANSI_RESET);
                }
                System.out.println();
                listCellPass.removeLast();
                stack.pop();
            }
        }
        System.out.println("The step for the robot to exit the maze is: " + maze.getSteps());

    }

}

