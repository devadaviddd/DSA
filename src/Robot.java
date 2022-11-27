class Robot {
    Coordinate currentPoint = new Coordinate(0, 0);

    public Robot(int robotRow, int robotColumn) {
        currentPoint.row = robotRow;
        currentPoint.col = robotColumn;
    }

    public void navigate() {
        char[][] map = new char[][]{ //robot at r = 2 and c = 1
                {'.', '.', '.', '.'},
                {'.', ' ', ' ', '.'},
                {'.', ' ', ' ', '.'},
                {'.', ' ', ' ', '.'},
                {'.', '.', 'X', '.'},

        };
        Maze maze = new Maze(map);
        maze.robotCol = this.currentPoint.col;
        maze.robotRow = this.currentPoint.row;
        String direction = "DOWN";
        System.out.println(maze.go(direction));
        maze.printMaze();
        System.out.println(maze.cnt);
    }

}

