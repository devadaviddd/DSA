public class Maze {
    private int rows;
    private int cols;
    private String[] map;
    private int robotRow;
    private int robotCol;
    private int steps;

    public Maze() {
        // Note: in my real test, I will create much larger
        // and more complicated map
        rows = 30;
        cols = 100;
        map = new String[rows];

        /* .....
           .   X
           .S  .
           .....
        * */
       /* map[0] = ".....";
        map[1] = ". ...";
        map[2] = ".  X.";
        map[3] = ".....";*/

/*        map[0] = "..........";
        map[1] = ".     .X .";
        map[2] = "... . .. .";
        map[3] = ". ... .  .";
        map[4] = ".       ..";
        map[5] = "..........";*/

/*        map[0] = "........";
        map[1] = ".      .";
        map[2] = ".....  .";
        map[3] = ".  X.  .";
        map[4] = ".    . .";
        map[5] = ".  .   .";
        map[6] = "........";*/
/*        map[0] = "..........";
        map[1] = ".        .";
        map[2] = ". ........";
        map[3] = ".  .  .  .";
        map[4] = ".      X..";
        map[5] = "..........";*/

        map[0]  = "....................................................................................................";
        map[1]  = ".X                                              ..                                                 .";
        map[2]  = ".                                              ..                          ..                      .";
        map[3]  = ".                                              ..                          ..                      .";
        map[4]  = ".      ..............                          ..                  ...     ..                      .";
        map[5]  = ".            .............                     ..                  ...     ..   ....................";
        map[6]  = ".                                              ..                  ...     ..   ....................";
        map[7]  = ".       ....  .   .  .  .....                                                   ...     ..         .";
        map[8]  = ".       .  .  .. ..  .    .            ........                     .........   ...     ..         .";
        map[9]  = ".       ....  . . .  .    .            ........                    .........    ...     ..         .";
        map[10] = ".       ..    .   .  .    .            ........                                 ...     ..         .";
        map[11] = ".       . .   .   .  .    .        .   ........                        ..                       ....";
        map[12] = ".       .  .  .   .  .    .        .   ........                        ..                       ....";
        map[13] = ".                                  .                                   ..  ..           .......    .";
        map[14] = ".    ..       ..    ..       ..    .        ..      ..    ...          ..  ..           .......    .";
        map[15] = ".    ....     ..    ....     ..    .        ..      ..    .....        ..  ..                      .";
        map[16] = ".    .. ..    ..    .. ..    ..        .    ..      ..    ..  ...  ..      ..                      .";
        map[17] = ".    ..  ..   ..    ..  ..   ..   .......   ..........    ..   ..  ..      ..                      .";
        map[18] = ".    ..   ..  ..    ..   ..  ..   .......   ..........    ..   ..  ..      ..                      .";
        map[19] = ".    ..    .. ..    ..    .. ..        .    ..      ..    ..  ...          ..                      .";
        map[20] = ".    ..     ....    ..     ....             ..      ..    .....            ..    ..........        .";
        map[21] = ".    ..       ..    ..       ..             ..      ..    ...              ..    ..........        .";
        map[22] = ".                                                                          ..                      .";
        map[23] = ".      .            .           .            .                             ..                      .";
        map[24] = ".      .     .      .           .            .                             ..                      .";
        map[25] = ".      .     .      .                        .        .                    ..                      .";
        map[26] = ".      .     .      .                        .        .                    ..                      .";
        map[27] = ".      .     .      .           .            .        .                    ..                      .";
        map[28] = ".            .                  .                     .                    ..                      .";
        map[29] = "....................................................................................................";
        robotRow = 28;
        robotCol = 98;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
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

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String go(String direction) {
        if (!direction.equals("UP") &&
                !direction.equals("DOWN") &&
                !direction.equals("LEFT") &&
                !direction.equals("RIGHT")) {
            // invalid direction
            steps++;
            return "false";
        }
        int currentRow = robotRow;
        int currentCol = robotCol;
        if (direction.equals("UP")) {
            currentRow--;
        } else if (direction.equals("DOWN")) {
            currentRow++;
        } else if (direction.equals("LEFT")) {
            currentCol--;
        } else {
            currentCol++;
        }

        // check the next position
        if (map[currentRow].charAt(currentCol) == 'X') {
            // Exit gate
            steps++;
            robotRow = currentRow;
            robotCol = currentCol;
            System.out.println("Steps to reach the Exit gate " + steps);
            return "win";
        } else if (map[currentRow].charAt(currentCol) == '.') {
            // Wall
            steps++;
            return "false";
        } else {
            // Space => update robot location
            steps++;
            robotRow = currentRow;
            robotCol = currentCol;
            return "true";
        }

    }
}

