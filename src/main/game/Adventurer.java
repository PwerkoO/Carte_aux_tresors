package main.game;

public class Adventurer {
    private String name;
    private int x;
    private int y;
    private String orientation;
    private String path;
    private int numberOfTreasures;
    private int movements;

    /**
     * Initialize Adventurer with given information
     * @param name Adventurer's name
     * @param x Adventurer's horizontal position
     * @param y Adventurer's vertical position
     * @param orientation Adventurer's orientation
     * @param path Adventurer's path
     */
    public Adventurer(String name, int x, int y, String orientation, String path) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.path = path;
        this.numberOfTreasures = 0;
        this.movements = 0;
    }

    /**
     * Check if the adventurer has done every movement from his path
     * @return true if the adventurer is done moving
     */
    public boolean isDoneMoving(){
        return movements == path.length();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setNumberOfTreasures(int numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int movements) {
        this.movements = movements;
    }
}
