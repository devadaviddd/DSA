package QuangRat_In_Maze;


public class Maze {
    private int rows;
    private int cols;
    private String[] map;
    private int robotRow;
    private int robotCol;
    private int steps;
    private LinkedListStack<Node> directionOrder;
    private boolean[][] visitedMap;
    private Node initialPoint;
    private int[][] mazeInt;

    public Maze() {
        // Note: in my real test, I will create much larger
        // and more complicated map
        rows = 5;
        cols = 5;
        map = new String[rows];
        map[0] = ".....";
        map[1] = ". . .";
        map[2] = ".   .";
        map[3] = ". .X.";
        map[4] = ".....";
        robotRow = 1;
        robotCol = 1;
        steps = 0;
        directionOrder = new LinkedListStack<>();
        visitedMap = new boolean[rows][cols];
        setVisited(true);
        initialPoint = new Node(robotRow, robotCol);
        directionOrder.push(initialPoint);
        mazeInt = mazeToIntArr(map);

    }

    private void setVisited(boolean b)
    {
        for (int i = 0; i < visitedMap.length; i++)
        {
            for (int j = 0; j < visitedMap[i].length; j++)
            {
                visitedMap[i][j] = b;
            }
        }

    }

    public String go(int d) {
        if (!directionOrder.isEmpty()) {

            // Pop the top node and move to the
            // left, right, top, down or retract
            // back according the value of node's
            // dir variable.
            initialPoint = directionOrder.peek(); //temp = initialPoint
//            int d = initialPoint.getDir();
            robotRow = initialPoint.getX();
            robotCol = initialPoint.getY();

            // Increment the direction and
            // push the node in the stack again.
//            initialPoint.setDir(initialPoint.getDir() + 1);
            directionOrder.pop();
            directionOrder.push(initialPoint);

            // If we reach the Food coordinates
            // return true
            if (mazeInt[robotRow][robotCol] == 88) {
                return "win";
            }

            if (d == 0) {
                // Checking the Up direction.
                if (robotRow - 1 >= 1 && (mazeInt[robotRow - 1][robotCol] == 32 || mazeInt[robotRow - 1][robotCol] == 88) &&
                        visitedMap[robotRow - 1][robotCol]) {
                    if (mazeInt[robotRow-1][robotCol] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow - 1, robotCol);
                    visitedMap[robotRow - 1][robotCol] = false;
                    directionOrder.push(temp1);
                    return "true";
                }
            } else if (d == 1) {
                // Checking the left direction
                if (robotCol - 1 >= 1 && (mazeInt[robotRow][robotCol - 1] == 32 || mazeInt[robotRow][robotCol - 1] == 88) &&
                        visitedMap[robotRow][robotCol - 1]) {
                    if (mazeInt[robotRow][robotCol - 1] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow, robotCol - 1);
                    visitedMap[robotRow][robotCol - 1] = false;
                    directionOrder.push(temp1);
                    return "true";
                }
            } else if (d == 2) {
                // Checking the down direction
                if (robotRow + 1 < rows-1 && (mazeInt[robotRow + 1][robotCol] == 32 || mazeInt[robotRow + 1][robotCol] == 88) &&
                        visitedMap[robotRow + 1][robotCol]) {
                    if (mazeInt[robotRow+1][robotCol] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow + 1, robotCol);
                    visitedMap[robotRow + 1][robotCol] = false;
                    directionOrder.push(temp1);
                    return "true";
                }
            } else if (d == 3) {
                // Checking the right direction
                if (robotCol + 1 < cols-1 && (mazeInt[robotRow][robotCol + 1] == 32 || mazeInt[robotRow][robotCol + 1] == 88) &&
                        visitedMap[robotRow][robotCol + 1]) {
                    if (mazeInt[robotRow][robotCol + 1] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow, robotCol + 1);
                    visitedMap[robotRow][robotCol + 1] = false;
                    directionOrder.push(temp1);
                    return "true";
                }
            }

            // If none of the direction can take
            // the rat to the Food, retract back
            // to the path where the rat came from.
            else {
                visitedMap[initialPoint.getX()][initialPoint.getY()] = true;
                directionOrder.pop();
            }
        }

        // If the stack is empty and
        // no path is found return false.
        return "false";
    }

    public int[][] mazeToIntArr(String[] maze){
        int[][] mazeInt = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                mazeInt[i][j] = map[i].charAt(j);
                System.out.print(mazeInt[i][j] + " ");
            }
            System.out.println();
        }
        return mazeInt;
    }


    public static void main(String[] args) {
        (new Robot()).navigate();
    }
}

class Node
{
    int x, y;
    int dir;

    public Node(int i, int j)
    {
        this.x = i;
        this.y = j;

        // default value for direction set to 0 (Up)
        dir = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir()
    {
        return dir;
    }

    public void setDir(int dir)
    {
        this.dir = dir;
    }
}

class Robot {
    // A very simple implementation
    // where the robot just go randomly
    public void navigate() {
        Maze maze = new Maze();
        String result = "";

        //It is guarantee that there is a possible exit

        while(!result.equals("win")){
//            result = maze.go(0);
//            if(result.equals("win")){
//                break;
//            } else if (result.equals("true")) {
//                System.out.println("UP");
//            }

            do {
                result = maze.go(0); //UP
                if(result.equals("win")){
                    break;
                } else if (result.equals("true")) {
                    System.out.println("UP");
                }
            }while (result.equals("true"));
            if(result.equals("win")){
                System.out.println("UP");
                break;
            }

            do {
                result = maze.go(2); //DOWN
                if(result.equals("win")){
                    break;
                } else if (result.equals("true")) {
                    System.out.println("DOWN");
                }
            }while (result.equals("true"));
            if(result.equals("win")){
                System.out.println("DOWN");
                break;
            }

            do {
                result = maze.go(1); //LEFT
                if(result.equals("win")){
                    break;
                } else if (result.equals("true")) {
                    System.out.println("LEFT");
                }
            }while (result.equals("true"));
            if(result.equals("win")){
                System.out.println("LEFT");
                break;
            }


            do {
                result = maze.go(3); //RIGHT
                if(result.equals("win")){
                    break;
                } else if (result.equals("true")) {
                    System.out.println("RIGHT");
                }
            }while (result.equals("true"));
            if(result.equals("win")){
                System.out.println("RIGHT");
                break;
            }
        }
        System.out.println("Exit Reached");

    }
}