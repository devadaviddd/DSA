public class Robot {
    private int row = 0;
    private int col = 0;
    private Linkedlist<Coordinate> visitedList;

    private  ArrayStack<String> stackDirection;


    // A very simple implementation
    // where the robot just go randomly
    public void navigate() {
        Maze maze = new Maze();
        String result = "";
        stackDirection = new ArrayStack<>();
        int pair = 0;
        visitedList = new Linkedlist<>();
        visitedList.appendNode(new Coordinate(row, col));


        // go return false | win | true
        while (!result.equals("win")) {
            if(((result = maze.go("UP")).equals("true") || result.equals("win")) && !checkVisited(row - 1, col, "DOWN", maze)){
                stackDirection.push("UP");
                System.out.println("UP");
                visitedList.appendNode(new Coordinate(row - 1, col));
                row--;
                System.out.println(maze.getRobotRow()+ " " +  maze.getRobotCol());
            } else if(((result = maze.go("DOWN")).equals("true") || result.equals("win")) && !checkVisited(row + 1, col, "UP", maze)) {
                stackDirection.push("DOWN");
                System.out.println("DOWN");
                System.out.println(maze.getRobotRow()+ " " +  maze.getRobotCol());
                visitedList.appendNode(new Coordinate(row + 1, col));
                row++;

            } else if(((result = maze.go("LEFT")).equals("true") || result.equals("win")) && !checkVisited(row, col - 1, "RIGHT", maze)) {
                stackDirection.push("LEFT");
                System.out.println("LEFT");
                System.out.println(maze.getRobotRow()+ " " +  maze.getRobotCol());
                visitedList.appendNode(new Coordinate(row, col - 1));
                col--;
            } else if(((result = maze.go("RIGHT")).equals("true") || result.equals("win")) && !checkVisited(row, col + 1, "LEFT", maze)) {
                stackDirection.push("RIGHT");
                System.out.println("RIGHT");
                visitedList.appendNode(new Coordinate(row, col + 1));
                col++;
                System.out.println(maze.getRobotRow()+ " " +  maze.getRobotCol());
            } else if(result.equals("false")) {
                // backtracking
                String backTrackStep = backTracking(stackDirection.peek(), maze);
                unstatedVisited(backTrackStep);
                stackDirection.pop();


            }
        }

    }

    private void unstatedVisited(String backTrackStep) {
        if(backTrackStep.equals("UP")) {
            visitedList.deleteNode(new Coordinate(row - 1, col));
        } else if(backTrackStep.equals("DOWN")) {
            visitedList.deleteNode(new Coordinate(row + 1, col));
        } else if(backTrackStep.equals("LEFT")) {
            visitedList.deleteNode(new Coordinate(row, col - 1));
        } else {
            visitedList.deleteNode(new Coordinate(row, col + 1));
        }
    }

    private boolean checkVisited(int row, int col, String direction, Maze maze) {
        Coordinate coordinate = new Coordinate(row, col);
        if(visitedList.isContainNode(coordinate)) {
            maze.go(direction);
            return true;
        } else {
            return false;
        }
    }

    private String backTracking(String peek, Maze maze) {
        if(peek.equals("UP")) {
            maze.go("DOWN");
            System.out.println("DOWN");

            return "DOWN";
        } else if(peek.equals("DOWN")) {
            maze.go("UP");
            System.out.println("UP");;
            return "UP";
        } else if(peek.equals("LEFT")) {
            maze.go("RIGHT");
            System.out.println("RIGHT");
            return "RIGHT";
        } else {
            maze.go("LEFT");
            System.out.println("LEFT");
            return "LEFT";
        }

    }
}
