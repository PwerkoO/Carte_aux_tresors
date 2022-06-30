package main.game;

public class Plain {

    protected boolean accessible;
    private int numberOfTreasures;

    /**
     * Initialize a new plain with accessibility and no treasure
     */
    public Plain() {
        accessible = true;
        numberOfTreasures = 0;
    }
    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }
    public void setNumberOfTreasures(int numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }
}
