package main.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Adventurer> adventurerList;
    private final GameMap gameMap;

    private boolean currentAdventurerMoved;


    /**
     * Game initializer
     * @param mapInformation test
     */
    public Game(List<String> mapInformation) {
        this.adventurerList = new ArrayList<>();

        String[] s = mapInformation.get(0).replaceAll(" ","").split("-");
        int x = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);
        this.gameMap = new GameMap(x,y);

        for(int i = 1; i < mapInformation.size(); i++){
            s = mapInformation.get(i).replaceAll(" ","").split("-");
            switch(s[0]){
                case "M":
                    x = Integer.parseInt(s[1]);
                    y = Integer.parseInt(s[2]);
                    gameMap.putMountain(x,y);
                    break;
                case "T":
                    x = Integer.parseInt(s[1]);
                    y = Integer.parseInt(s[2]);
                    int numberOfTreasures= Integer.parseInt(s[3]);
                    gameMap.addTreasure(x,y,numberOfTreasures);
                    break;
                case "A":
                    String name = s[1];
                    x = Integer.parseInt(s[2]);
                    y = Integer.parseInt(s[3]);
                    String orientation = s[4];
                    String path = s[5];
                    adventurerList.add(new Adventurer(name,x,y,orientation,path));
                    gameMap.disableAccessToPlain(x,y);
                    break;
            }
        }
    }

    /**
     * Performing every adventurers' actions until none can move.
     */
    public void Run() {
        boolean atLeastOneAdventurerIsMoving = true;
        while(atLeastOneAdventurerIsMoving){
            atLeastOneAdventurerIsMoving = false;
            for(Adventurer adventurer : adventurerList){
                if(!adventurer.isDoneMoving()){
                    Move(adventurer);
                    atLeastOneAdventurerIsMoving = true;
                }
            }
        }
    }

    /**
     * Perform the action included in the adventurer's path.
     * @param adventurer The adventurer that will perform a move
     */
    public void Move(Adventurer adventurer){
        this.currentAdventurerMoved = false;

        int movements = adventurer.getMovements();
        String path = adventurer.getPath();
        char currentMovement = path.charAt(movements);

        if(currentMovement == 'A'){
            MoveForward(adventurer);
            PickupTreasure(adventurer);
        }
        else{
            Rotate(adventurer,currentMovement);
        }
        adventurer.setMovements(movements+1);
    }

    /**
     * Moving forward based of the adventurer's direction.
     * @param adventurer The adventurer that will move forward
     */
    public void MoveForward(Adventurer adventurer) {
        String orientation = adventurer.getOrientation();
        int xp = 0;
        int yp = 0;

        if("N".equals(orientation))
            yp=-1;
        else if ("E".equals(orientation))
            xp=1;
        else if("S".equals(orientation))
            yp=1;
        else if("W".equals(orientation))
            xp=-1;

        int x = adventurer.getX();
        int y = adventurer.getY();

        if(IsInBound(x,y,xp,yp) && gameMap.isAccessible(x+xp,y+yp)){
            adventurer.setX(x+xp);
            adventurer.setY(y+yp);
            gameMap.disableAccessToPlain(x+xp,y+yp);
            gameMap.enableAccessToPlain(x,y);

            this.currentAdventurerMoved = true;
        }

    }

    /**
     * Taking the treasure from the plain and give it to the adventurer.
     * @param adventurer The adventurer that will pick a treasure
     */
    public void PickupTreasure(Adventurer adventurer) {
        int x = adventurer.getX();
        int y = adventurer.getY();

        if(this.currentAdventurerMoved && gameMap.gotTreasuresOnThisPlain(x,y)){
            adventurer.setNumberOfTreasures(adventurer.getNumberOfTreasures()+1);
            gameMap.pickUpTreasure(x,y);
        }
    }


    /**
     * Rotating the adventure depending on the given instruction.
     * @param adventurer The adventurer that will rotate
     * @param currentMovement The current action in the adventurer's path that will be performed
     */
    public void Rotate(Adventurer adventurer, char currentMovement) {
        String Direction = "NESW";
        String oldOrientation = adventurer.getOrientation();
        int movement = currentMovement == 'D' ? 1 : -1;

        /*
            Simple formula to assign a new orientation
                            "NESW"
                              ^
                              |
                -1 if (G)  <-   -> +1 if (D)

            using modulo to redirect at the start or the end of the string.
         */
        char newOrientation = Direction.charAt(((Direction.indexOf(oldOrientation)+movement)+Direction.length())%Direction.length());
        adventurer.setOrientation(newOrientation+"");
    }

    /**
     * Return true if the coordinates are not out of bounds.
     * @param x Adventurer's horizontal position
     * @param y Adventurer's vertical position
     * @param xp The adventurer's horizontal movement
     * @param yp The adventurer's vertical movement
     * @return true if the future adventurer's position is in not out of bounds
     */
    private boolean IsInBound(int x, int y, int xp, int yp){
        return (x + xp >= 0) && (x + xp < gameMap.getWidth()) && (y + yp >= 0) && (y + yp < gameMap.getHeight());
    }

    public GameMap getMapState(){
        return gameMap;
    }

    public List<Adventurer> getAdventurerList() {
        return adventurerList;
    }
}
