import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Maze {
    int rowsMap;
    int colsMap;
    String[] map;
    int steps = 0;

    int robotRow;

    int robotCol;

    StringBuilder path = new StringBuilder();

    public Maze(String[] map, int robotRow, int robotCol) {
        this.map = map;
        this.rowsMap = map.length;
        this.colsMap = map[0].length();
        this.robotRow = robotRow;
        this.robotCol = robotCol;

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

    public String go(String direction) {
        char moveToUp = 0, moveToRight = 0, moveToLeft = 0, moveToDown = 0;
        String reached = "false";

        moveToUp = this.map[robotRow - 1].charAt(robotCol);
        moveToDown = this.map[robotRow + 1].charAt(robotCol);
        moveToLeft = this.map[robotRow].charAt(robotCol - 1);
        moveToRight = this.map[robotRow].charAt(robotCol + 1);

        if (moveToUp == 'X' || moveToDown == 'X' || moveToLeft == 'X' || moveToRight == 'X') {
            System.out.println(direction);
            StringBuilder stringBuilder = new StringBuilder(this.map[robotRow]);
            stringBuilder.setCharAt(robotCol, '+');
            this.map[robotRow] = String.valueOf(stringBuilder);
            return "True";
        }

        if(this.map[robotRow].charAt(robotCol) == ' ') {
            StringBuilder stringBuilder = new StringBuilder(this.map[robotRow]);
            stringBuilder.setCharAt(robotCol, '+');
            this.map[robotRow] = String.valueOf(stringBuilder);
        }

        if (moveToUp == ' ' && reached.equals("false")) {
            System.out.println(direction);
            robotRow = robotRow - 1;
            reached = go("UP");
        }
        if (moveToDown == ' ' && reached.equals("false")) {
            System.out.println(direction);
            robotRow = robotRow + 1;
            reached = go("DOWN");
        }
        if (moveToLeft == ' ' && reached.equals("false")) {
            System.out.println(direction);
            robotCol = robotCol - 1;
            reached = go("LEFT");
        }
        if (moveToRight == ' ' && reached.equals("false")) {
            System.out.println(direction);
            robotCol = robotCol + 1;
            reached = go("RIGHT");
        }
        return reached;
    }

}