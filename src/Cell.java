public class Cell {
    private int x;
    private int y;
    private String direction = "";

    private int numOfDirectionChecked;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = "UP"; //         this.direction = "UP";
        this.numOfDirectionChecked = 0; // 0 -> UP || 1 --> DOWN || 2 --> LEFT || 3 --> RIGHT
    }

    public Cell() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getNumOfDirectionChecked() {
        return numOfDirectionChecked;
    }

    public void setNumOfDirectionChecked(int numOfDirectionChecked) {
        this.numOfDirectionChecked = numOfDirectionChecked;
    }
}
