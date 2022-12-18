public class Maze {
    private final int rows;
    private final int cols;
    private final String[] map;
    private int robotRow;
    private int robotCol;
    private int steps;

    public Maze() {
        rows = 30;
        cols = 100;
        map = new String[rows];


        map[0]  = "....................................................................................................";
        map[1]  = ".                                              ..                                                  .";
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
        map[23] = ".      .            .           .            .                             ..          X           .";
        map[24] = ".      .     .      .           .            .                             ..                      .";
        map[25] = ".      .     .      .                        .        .                    ..                      .";
        map[26] = ".      .     .      .                        .        .                    ..                      .";
        map[27] = ".      .     .      .           .            .        .                    ..                      .";
        map[28] = ".            .                  .                     .                    ..                      .";
        map[29] = "....................................................................................................";

        robotRow = 1;
        robotCol = 1;
    }

    public int getRows() {
        return rows;
    }


    public int getCols() {
        return cols;
    }


    public String[] getMap() {
        return map;
    }


    public int getRobotRow() {
        return robotRow;
    }


    public int getRobotCol() {
        return robotCol;
    }

    public int getSteps() {
        return steps;
    }

    public String go(String direction) {
        if (!direction.equals("UP") &&
                !direction.equals("DOWN") &&
                !direction.equals("LEFT") &&
                !direction.equals("RIGHT")) {
            // invalid direction
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

