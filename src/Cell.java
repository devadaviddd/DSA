public class Cell {
    String direction;
    int pattern = 0;
    //pattern denote:
    //0 -> UP
    //1 -> RIGHT
    //2 -> DOWN
    //3 -> LEFT
    //use to initialize the spawning cell
    public Cell(String direction, int i) {
        this.direction = direction;
        this.pattern = i;
    }
    //initialize the cell with default direction UP
    public Cell(String direction) {
        this.direction = direction;
        this.pattern = 0;
    }
    public String getDirection() {
        return direction;
    }
    public int getPattern() {
        return pattern;
    }
    public void setPattern(int pattern) {
        this.pattern = pattern;
    }
}
