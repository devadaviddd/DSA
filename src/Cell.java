public class Cell {
    String direction;
    int pattern = 0;

    public Cell(String direction, int i) {
        this.direction = direction;
        this.pattern = i;
    }

    public Cell(String direction) {
        this.direction = direction;
        this.pattern = 0;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPattern() {
        return pattern;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }
}
