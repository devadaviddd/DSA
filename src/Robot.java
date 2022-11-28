class Robot {
    private int row;
    private int col;

    public Robot(int robotRow, int robotColumn) {
        row = robotRow;
        col = robotColumn;
    }

    public void navigate() {
        String[] map = new String[7];
        map[0] = "......................";
        map[1] = ". ....................";
        map[2] = ". ....................";
        map[3] = ".                    .";
        map[4] = ".................... .";
        map[5] = ".X                   .";
        map[6] = "......................";

        Maze maze = new Maze(map, row, col);
        maze.robotCol = this.col;
        maze.robotRow = this.row;
        maze.go("UP");
        maze.printMaze();
    }

}

