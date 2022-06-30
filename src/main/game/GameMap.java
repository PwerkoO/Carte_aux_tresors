package main.game;

public class GameMap {
    private final Plain[][] map;
    private final int width;
    private final int height;

    /**
     * Initialize the game's map with empty plains
     * @param width Width of the map
     * @param height Height of the map
     */
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Plain[height][width];

        for(int i = 0; i < height ; i++)
            for(int j = 0; j < width; j++)
                map[i][j] = new Plain();
    }

    /**
     * Adding mountain to the map
     * @param x Horizontal position of the mountain
     * @param y Vertical position of the mountain
     */
    public void putMountain(int x, int y){
        map[y][x] = new Mountain();
    }

    /**
     * Adding treasure to the map
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     * @param numberOfTreasures number of treasures to add to the plain
     */
    public void addTreasure(int x, int y, int numberOfTreasures){
        map[y][x].setNumberOfTreasures(numberOfTreasures);
    }

    /**
     * Give information about existence of treasure in a plain.
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     * @return Return true if the plain got any treasures in it
     */
    public boolean gotTreasuresOnThisPlain(int x, int y){
        return map[y][x].getNumberOfTreasures() > 0;
    }

    /**
     * Remove one treasure from the plain
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     */
    public void pickUpTreasure(int x, int y){
        map[y][x].setNumberOfTreasures(map[y][x].getNumberOfTreasures()-1);
    }

    /**
     * Setting the plain accessible
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     */
    public void enableAccessToPlain(int x, int y){
        map[y][x].setAccessible(true);
    }

    /**
     * Setting the plain inaccessible
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     */
    public void disableAccessToPlain(int x, int y){
        map[y][x].setAccessible(false);
    }

    /**
     * Return true if the plain is accessible for an adventurer
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     */
    public boolean isAccessible(int x, int y){
        return map[y][x].isAccessible();
    }

    /**
     * Give information about number of treasure in a plain
     * @param x Horizontal position of the plain
     * @param y Vertical position of the plain
     * @return the number of treasure in a plain
     */
    public int getNumberOfTreasureOfPlain(int x, int y){
        return map[y][x].getNumberOfTreasures();
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Plain[][] getMapState() {
        return map;
    }
}
