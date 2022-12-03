public class Cell {
    private int x;
    private int y;
    private int direction; // 0 --> Up || 1 --> Left || 2 --> Down || 3 --> Right

    public String path;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        direction = 0;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
