import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;

public class Maze {
    int rowsMap;
    int colsMap;
    char[][] map;
    int steps;
    int cnt = 0;

    int robotRow;

    int robotCol;

    StringBuilder path = new StringBuilder();

    public Maze(char[][] map) {
        this.map = map;
        this.rowsMap = map.length;
        this.colsMap = map[0].length;
        steps = 0;
    }

    public void printMaze() {
        for(int i = 0; i < rowsMap; i++) {
            for(int j = 0; j < colsMap; j++) {
                System.out.print(map[i][j] + " ");
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

        if(direction.equals("UP")) {
            moveToUp = this.map[robotRow - 1][robotCol];
        } else if(direction.equals("DOWN")) {
            moveToDown = this.map[robotRow + 1][robotCol];
        } else if(direction.equals("LEFT")) {
            moveToLeft = this.map[robotRow][robotCol - 1];
        } else {
            moveToRight = this.map[robotRow][robotCol + 1];
        }

        if(moveToUp == 'X' || moveToDown == 'X' || moveToLeft == 'X' || moveToRight == 'X') {
//            this.map[robotRow][robotCol] = '+'; // draw the path as '+'
            System.out.println(direction);
            return "True";
        }

/*
        if(this.map[robotRow][robotCol] == ' ') {
            this.map[robotRow][robotCol] = '+';
        }
*/

        if (moveToUp == ' ' &&  reached.equals("false")) {
            System.out.println("UP");
            robotRow = robotRow - 1;
        }
        if (moveToDown == ' ' && reached.equals("false")) {
            System.out.println("DOWN");
            robotRow = robotRow + 1;
        }
        if (moveToLeft == ' ' &&  reached.equals("false")) {
            System.out.println("LEFT");
            robotCol = robotCol - 1;
        }
        if(moveToRight == ' ' &&  reached.equals("false")) {
            System.out.println("RIGHT");
            robotCol = robotCol + 1;
        }
        reached = go(randomDirection());
        return reached;
    }

}