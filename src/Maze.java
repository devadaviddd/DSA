import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Maze {
    private int rowsMap;
    private int colsMap;
    private String[] map;
    private char[][] mapChar2D;

    private int steps = 0;

    private int robotRow;

    private int robotCol;

    private int finishRow;

    private int finishCol;

    private boolean[][] visited;

    private StringBuilder path = new StringBuilder();


    public Maze() {
        String[] map = new String[6];
     /*   map[0] = "................";
        map[1] = ".              .";
        map[2] = ".....          .";
        map[3] = ".   .          .";
        map[4] = ".    .         .";
        map[5] = ".  .          X.";
        map[6] = "................";*/

        map[0] = "..........";
        map[1] = ".     .X .";
        map[2] = "... . .. .";
        map[3] = ". ... .  .";
        map[4] = ".       ..";
        map[5] = "..........";

/*
        map[0] = ".....";
        map[1] = ".   X";
        map[2] = ".   .";
        map[3] = ".....";*/
/*

        map[0] = "........";
        map[1] = ".      .";
        map[2] = ".....  .";
        map[3] = ".  X.  .";
        map[4] = ".    . .";
        map[5] = ".  .   .";
        map[6] = "........";*/
        this.map = map;

        this.rowsMap = map.length;
        this.colsMap = map[0].length();
        this.robotCol = 3;
        this.robotRow = 2;
        this.visited = new boolean[rowsMap][colsMap];

        this.mapChar2D = convertTo2Darr(this.map);

        // Coordinate of X point
        this.finishRow = coordinateOfFinishPoint(this.mapChar2D)[0];
        this.finishCol = coordinateOfFinishPoint(this.mapChar2D)[1];

    }

    public void printMaze() {
        for(int i = 0; i < rowsMap; i++) {
            for(int j = 0; j < map[i].length(); j++) {
                System.out.print(map[i].charAt(j));
            }
            System.out.println();
        }
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

    public void passDataToMaze(int currentRow, int currentCol, boolean[][] visited) {
        this.robotRow = currentRow;
        this.robotCol = currentCol;
        this.visited = visited;
    }

    public String go(String direction) {
        steps++;
        if(robotRow == finishRow && robotCol == finishCol) {
            System.out.println("EXIT");
            return "win";
        }
        switch (direction) {
            case "UP":
                if (robotRow - 1 >= 0 && (mapChar2D[robotRow - 1][robotCol] == ' ' || mapChar2D[robotRow - 1][robotCol] == 'X') && !visited[robotRow - 1][robotCol]) {
                    System.out.println("ROBOT MOVE UP TO THE NEW COORDINATE " + "[" + (robotRow - 1) + ", " + (robotCol) + "]" + Main.ANSI_GREEN+ " [UP]" + Main.ANSI_RESET);
                    return "true";
                } else {
                    System.out.println("ROBOT MOVE UP TO THE NEW COORDINATE " + "[" + (robotRow - 1) + ", " + (robotCol) + "]" + Main.ANSI_BLUE + " [UP]" + Main.ANSI_RESET);
                    if(!visited[robotRow - 1][robotCol]) {
                        System.out.println("!!!ROBOT COLLIDE Wall OF THE MAZE".toUpperCase() + Main.ANSI_RED + "[COLLIDE]" + Main.ANSI_RESET);
                    } else
                        System.out.println("!!!Path Already Visited In Memory Cache Of The ROBOT".toUpperCase()  + Main.ANSI_YELLOW+ " [AWARENESS]" + Main.ANSI_RESET);
                    return "false";
                }
            case "LEFT":
                if (robotCol - 1 >= 0 && (mapChar2D[robotRow][robotCol - 1] == ' ' || mapChar2D[robotRow][robotCol - 1] == 'X') &&
                        !visited[robotRow][robotCol - 1]) {
                    System.out.println("ROBOT MOVE LEFT TO THE NEW COORDINATE " + "[" + (robotRow) + ", " + (robotCol - 1) + "]" + Main.ANSI_GREEN+ " [LEFT]" + Main.ANSI_RESET);
                    return "true";
                } else {
                    System.out.println("ROBOT MOVE UP TO THE NEW COORDINATE " + "[" + (robotRow) + ", " + (robotCol - 1) + "]" + Main.ANSI_BLUE + " [UP]" + Main.ANSI_RESET);
                    if(!visited[robotRow - 1][robotCol]) {
                        System.out.println("!!!Collide Wall Of The Maze".toUpperCase() + Main.ANSI_RED + " [COLLIDE]" + Main.ANSI_RESET);
                    } else
                        System.out.println("!!!Path Already Visited In Memory Cache".toUpperCase() +  Main.ANSI_YELLOW+ " [AWARENESS]" + Main.ANSI_RESET);
                    return "false";
                }
            case "DOWN":
                if (robotRow + 1 < rowsMap && (mapChar2D[robotRow + 1][robotCol] == ' ' || mapChar2D[robotRow + 1][robotCol] == 'X') &&
                        !visited[robotRow + 1][robotCol]) {
                    System.out.println("ROBOT MOVE DOWN TO THE NEW COORDINATE " + "[" + (robotRow + 1) + ", " + (robotCol) + "]" + Main.ANSI_GREEN+ " [DOWN]" + Main.ANSI_RESET);
                    return "true";
                } else {
                    System.out.println("ROBOT MOVE UP TO THE NEW COORDINATE " + "[" + (robotRow + 1) + ", " + (robotCol) + "]" + Main.ANSI_BLUE + " [UP]" + Main.ANSI_RESET);
                    if(!visited[robotRow - 1][robotCol]) {
                        System.out.println("!!!Collide Wall Of The Maze".toUpperCase() + Main.ANSI_RED + " [COLLIDE]" + Main.ANSI_RESET);
                    } else
                        System.out.println("!!!Path Already Visited In Memory Cache".toUpperCase() + Main.ANSI_YELLOW+ " [AWARENESS]" + Main.ANSI_RESET);
                    return "false";
                }
            default:  // direction equals "RIGHT"
                if (robotCol + 1 < colsMap && (mapChar2D[robotRow][robotCol + 1] == ' ' || mapChar2D[robotRow][robotCol + 1] == 'X') &&
                        !visited[robotRow][robotCol + 1]) {
                    System.out.println("ROBOT MOVE RIGHT TO THE NEW COORDINATE " + "[" + (robotRow) + ", " + (robotCol + 1) + "]" + Main.ANSI_GREEN+ " [RIGHT]" + Main.ANSI_RESET);
                    return "true";
                } else {
                    System.out.println("ROBOT MOVE UP TO THE NEW COORDINATE " + "[" + (robotRow) + ", " + (robotCol + 1) + "]" + Main.ANSI_BLUE + " [UP]" + Main.ANSI_RESET);
                    if(!visited[robotRow - 1][robotCol]) {
                        System.out.println("!!!Collide Wall Of The Maze".toUpperCase() + Main.ANSI_RED + " [COLLIDE]" + Main.ANSI_RESET);
                    } else
                        System.out.println("!!!Path Already Visited In Memory Cache".toUpperCase() + Main.ANSI_YELLOW+ " [AWARENESS]" + Main.ANSI_RESET);
                    return "false";
                }
        }

    }

    /* GETTER & SETTER */

    public int getRowsMap() {
        return rowsMap;
    }

    public void setRowsMap(int rowsMap) {
        this.rowsMap = rowsMap;
    }

    public int getColsMap() {
        return colsMap;
    }

    public void setColsMap(int colsMap) {
        this.colsMap = colsMap;
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }

    public char[][] getMapChar2D() {
        return mapChar2D;
    }

    public void setMapChar2D(char[][] mapChar2D) {
        this.mapChar2D = mapChar2D;
    }


    public int getRobotRow() {
        return robotRow;
    }

    public void setRobotRow(int robotRow) {
        this.robotRow = robotRow;
    }

    public int getRobotCol() {
        return robotCol;
    }

    public void setRobotCol(int robotCol) {
        this.robotCol = robotCol;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public void setFinishRow(int finishRow) {
        this.finishRow = finishRow;
    }

    public int getFinishCol() {
        return finishCol;
    }

    public void setFinishCol(int finishCol) {
        this.finishCol = finishCol;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public void setVisited(boolean[][] visited) {
        this.visited = visited;
    }

    public StringBuilder getPath() {
        return path;
    }

    public void setPath(StringBuilder path) {
        this.path = path;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}