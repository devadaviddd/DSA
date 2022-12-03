import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Maze {
    int rowsMap;
    int colsMap;
    String[] map;

    char[][] mapChar2D;
    int steps = 0;

    int robotRow;

    int robotCol;

    int finishRow;

    int finishCol;

    boolean[][] visited;

    StringBuilder path = new StringBuilder();

    public Maze(int robotRow, int robotCol) {
/*        String[] map = new String[7];
        map[0] = "................";
        map[1] = ".              .";
        map[2] = ".....          .";
        map[3] = ".   .          .";
        map[4] = ".    .         .";
        map[5] = ".  .          X.";
        map[6] = "................";*/

        String[] map = new String[7];
        map[0] = "........";
        map[1] = ".      .";
        map[2] = ".....  .";
        map[3] = ".  X.  .";
        map[4] = ".    . .";
        map[5] = ".  .   .";
        map[6] = "........";
        this.map = map;

        this.rowsMap = map.length;
        this.colsMap = map[0].length();
        this.robotRow = robotRow;
        this.robotCol = robotCol;
        this.visited = new boolean[rowsMap][colsMap];


    }

    public void printMaze() {
        for(int i = 0; i < rowsMap; i++) {
            for(int j = 0; j < map[i].length(); j++) {
                System.out.print(map[i].charAt(j));
            }
            System.out.println();
        }
    }


    String randomDirection() {
        String direction = "";
        double rnd = Math.random();
        if (rnd <= 0.25) {
            direction = "UP";
        } else if (rnd <= 0.50) {
            direction = "DOWN";
        } else if (rnd <= 0.75 ) {
            direction = "LEFT";
        } else {
            direction = "RIGHT";
        }
        return direction;
    }

    public void passDataToMaze(int currentRow, int currentCol, int finishRow, int finishCol, boolean[][] visited) {
        this.robotRow = currentRow;
        this.robotCol = currentCol;
        this.finishRow = finishRow;
        this.finishCol = finishCol;
        this.visited = visited;
        /*System.out.println(Arrays.deepToString(this.visited));*/


    }

    public String go(String direction) {
        if(direction.equals("UP")) {
            if(robotRow - 1 >= 0 && (mapChar2D[robotRow - 1][robotCol] == ' ' || mapChar2D[robotRow - 1][robotCol] == 'X') && !visited[robotRow - 1][robotCol]) {
                return "true";
            } else {
                return "false";
            }
        } else if(direction.equals("LEFT")) {
            if (robotCol - 1 >= 0 && (mapChar2D[robotRow][robotCol - 1] == ' ' ||  mapChar2D[robotRow ][robotCol - 1] == 'X')  &&
                    !visited[robotRow][robotCol - 1]) {
                return "true";
            } else {
                return "false";
            }
        }  else if(direction.equals("DOWN")) {
            if (robotRow + 1 < rowsMap && (mapChar2D[robotRow + 1][robotCol] == ' ' ||  mapChar2D[robotRow + 1][robotCol] == 'X') &&
                    !visited[robotRow + 1][robotCol]) {
                return "true";
            } else {
                return "false";
            }
        } else { // direction equals "RIGHT"
            if (robotCol + 1 < colsMap && (mapChar2D[robotRow][robotCol + 1] == ' ' ||  mapChar2D[robotRow][robotCol + 1] == 'X') &&
                    !visited[robotRow][robotCol + 1]) {
                return "true";
            } else {
                return "false";
            }
        }
    }


}