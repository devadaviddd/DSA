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
    int resetCounter;

    public Maze() {
        // Note: in my real test, I will create much larger
        // and more complicated map
//        rows = 5;
//        cols = 5;
//        map = new String[rows];
//        map[0] = ".....";
//        map[1] = ". .X.";
//        map[2] = ".   .";
//        map[3] = ". . .";
//        map[4] = ".....";

//        rows = 6;
//        cols = 6;
//        map = new String[rows];
//        map[0] = ".......";
//        map[1] = ".     .";
//        map[2] = "... . .";
//        map[3] = ".X... .";
//        map[4] = ".     .";
//        map[5] = ".......";
//        robotRow = 2;
//        robotCol = 3;

        rows = 6;
        cols = 10;
        map = new String[rows];
        map[0] = "..........";
        map[1] = ".     .X .";
        map[2] = "... . .. .";
        map[3] = ". ... .  .";
        map[4] = ".       ..";
        map[5] = "..........";
        robotRow = 2;
        robotCol = 3;
        steps = 0;
        directionOrder = new LinkedListStack<>();
        visitedMap = new boolean[rows][cols];
        setVisited(true);
        initialPoint = new Node(robotRow, robotCol);
        directionOrder.push(initialPoint);
        mazeInt = mazeToIntArr(map);
        resetCounter = 0;

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

    public String go(int a) {
        if (!directionOrder.isEmpty()) {

            // Pop the top node and move to the
            // left, right, top, down or retract
            // back according the value of node's
            // dir variable.
            initialPoint = directionOrder.peek(); //temp = initialPoint
            int d = initialPoint.getDir();

            robotRow = initialPoint.getX();
            robotCol = initialPoint.getY();

            // Increment the direction and
            // push the node in the stack again.
            if(d == a){
                initialPoint.setDir(initialPoint.getDir() + 1);
            }else {
                initialPoint.setDir(initialPoint.getDir());
            }

            directionOrder.pop();
            directionOrder.push(initialPoint);

            // If we reach the Food coordinates
            // return true
            if (mazeInt[robotRow][robotCol] == 88) {
                return "win";
            }

            if (d == 0 && d == a ) {
                // Checking the Up direction.
                if (robotRow - 1 >= 1 && (mazeInt[robotRow - 1][robotCol] == 32 || mazeInt[robotRow - 1][robotCol] == 88) &&
                        visitedMap[robotRow - 1][robotCol]) {
                    System.out.println("UP");
                    if (mazeInt[robotRow-1][robotCol] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow - 1, robotCol,0);
                    visitedMap[robotRow - 1][robotCol] = false;
                    directionOrder.push(temp1);
                    resetCounter=0;
                    return "true";
                }
            } else if (d == 1 && d == a) {
                // Checking the left direction
                if (robotCol - 1 >= 1 && (mazeInt[robotRow][robotCol - 1] == 32 || mazeInt[robotRow][robotCol - 1] == 88) &&
                        visitedMap[robotRow][robotCol - 1]) {
                    System.out.println("LEFT");
                    if (mazeInt[robotRow][robotCol - 1] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow, robotCol - 1,1);
                    visitedMap[robotRow][robotCol - 1] = false;
                    directionOrder.push(temp1);
                    resetCounter=0;
                    return "true";
                }
            } else if (d == 2 && d == a) {
                // Checking the down direction
                if (robotRow + 1 < rows && (mazeInt[robotRow + 1][robotCol] == 32 || mazeInt[robotRow + 1][robotCol] == 88) &&
                        visitedMap[robotRow + 1][robotCol]) {
                    System.out.println("DOWN");
                    if (mazeInt[robotRow+1][robotCol] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow + 1, robotCol,2);
                    visitedMap[robotRow + 1][robotCol] = false;
                    directionOrder.push(temp1);
                    resetCounter=0;
                    return "true";
                }
            } else if (d == 3 && d == a) {
                // Checking the right direction
                if (robotCol + 1 < cols && (mazeInt[robotRow][robotCol + 1] == 32 || mazeInt[robotRow][robotCol + 1] == 88) &&
                        visitedMap[robotRow][robotCol + 1]) {
                    System.out.println("Right");
                    if (mazeInt[robotRow][robotCol + 1] == 88) {
                        return "win";
                    }
                    Node temp1 = new Node(robotRow, robotCol + 1,3);
                    visitedMap[robotRow][robotCol + 1] = false;
                    directionOrder.push(temp1);
                    resetCounter=0;
                    return "true";
                }
            }
            // If the stack is empty and
            // no path is found return false.
            resetCounter++;
            // If none of the direction can take
            // the rat to the Food, retract back
            // to the path where the rat came from.
            if(resetCounter > 4) {
                visitedMap[initialPoint.getX()][initialPoint.getY()] = true;
                initialPoint = directionOrder.peek();
                if(initialPoint.getPrint()==0){
                    System.out.println("Down Back");
                } else if (initialPoint.getPrint()==1) {
                    System.out.println("Right Back");
                } else if (initialPoint.getPrint()==2) {
                    System.out.println("Up Back");
                }else if (initialPoint.getPrint()==3) {
                    System.out.println("Left Back");
                }
                directionOrder.pop();

                resetCounter=0;
                return "false";
            }
        }

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
    int print;

    public Node(int i, int j)
    {
        this.x = i;
        this.y = j;

        // default value for direction set to 0 (Up)
        dir = 0;
    }
    public Node(int i, int j, int print)
    {
        this.x = i;
        this.y = j;

        // default value for direction set to 0 (Up)
        this.print = print;
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

    public int getPrint() {
        return print;
    }

    public void setPrint(int print) {
        this.print = print;
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
            boolean reset = true;

            if(reset){
                result = maze.go(0); //UP
                if(result.equals("win")){
                    break;
                }
            }
            if(result.equals("true")){
                reset = false;
            }


            if(reset){
                result = maze.go(1); //LEFT
                if(result.equals("win")){
                    break;
                }
            }
            if(result.equals("true")){
                reset = false;
            }

            if(reset){
                result = maze.go(2); //DOWN
                if(result.equals("win")){
                    break;
                }
            }
            if(result.equals("true")){
                reset = false;
            }

            if(reset){
                result = maze.go(3); //RIGHT
                if(result.equals("win")){
                    break;
                }
            }
            if(result.equals("true")){
                reset = false;
            }
        }
        System.out.println("Exit Reached");


    }

}