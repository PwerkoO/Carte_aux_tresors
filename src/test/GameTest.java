package test;

import main.game.Adventurer;
import main.game.Game;
import main.game.GameMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    @BeforeEach
    void init(){
        List<String> mapInformation = new ArrayList<>();
        mapInformation.add("C - 3 - 4");
        mapInformation.add("M - 1 - 0");
        mapInformation.add("M - 2 - 1");
        mapInformation.add("T - 0 - 3 - 2");
        mapInformation.add("T - 1 - 3 - 3");
        mapInformation.add("A - Lara - 1 - 1 - S - AADADAGGA");
        game = new Game(mapInformation);
    }
    @Test
    void testRotation() {
        Adventurer adventurer = game.getAdventurerList().get(0);

        game.Rotate(adventurer, 'D');
        Assertions.assertEquals("W", adventurer.getOrientation());

        game.Rotate(adventurer, 'D');
        Assertions.assertEquals("N", adventurer.getOrientation());

        game.Rotate(adventurer, 'D');
        Assertions.assertEquals("E", adventurer.getOrientation());

        game.Rotate(adventurer, 'D');
        Assertions.assertEquals("S", adventurer.getOrientation());

        game.Rotate(adventurer, 'G');
        Assertions.assertEquals("E", adventurer.getOrientation());

        game.Rotate(adventurer, 'G');
        Assertions.assertEquals("N", adventurer.getOrientation());

        game.Rotate(adventurer, 'G');
        Assertions.assertEquals("W", adventurer.getOrientation());

        game.Rotate(adventurer, 'G');
        Assertions.assertEquals("S", adventurer.getOrientation());

    }

    @Test
    void testMovement() {
        Adventurer adventurer = game.getAdventurerList().get(0);
        int x = adventurer.getX();
        int y = adventurer.getY();

        GameMap map = game.getMapState();

        Assertions.assertFalse(map.isAccessible(x,y));

        //Moving the adventurer to a plain with no treasure.
        game.Move(adventurer);

        //The plain where the adventurer is located must be inaccessible, while that of his old position must no longer be.
        Assertions.assertTrue(map.isAccessible(x,y));
        Assertions.assertEquals(1, adventurer.getX());
        Assertions.assertEquals(2, adventurer.getY());
        Assertions.assertEquals("S", adventurer.getOrientation());

        //Moving adventurer to a plain with a treasure.
        game.Move(adventurer);

        //The adventurer should have taken a treasure from a plain.
        Assertions.assertEquals(1, adventurer.getNumberOfTreasures());
        Assertions.assertEquals(2, map.getNumberOfTreasureOfPlain(adventurer.getX(), adventurer.getY()));

        //Moving the adventurer out of bounds.
        game.MoveForward(adventurer);

        //The adventurer should not move.
        Assertions.assertEquals(1, adventurer.getX());
        Assertions.assertEquals(3, adventurer.getY());

        //The adventurer should not take any treasures.
        Assertions.assertEquals(1, adventurer.getNumberOfTreasures());
        Assertions.assertEquals(2, map.getNumberOfTreasureOfPlain(adventurer.getX(), adventurer.getY()));

        //End the game
        game.Run();

        /*
            At the end of the run the adventurer should be on this state :
            A - Lara - 0 - 3 - S - 3
        */
        Assertions.assertEquals(0, adventurer.getX());
        Assertions.assertEquals(3, adventurer.getY());
        Assertions.assertEquals("S", adventurer.getOrientation());
        Assertions.assertEquals(3, adventurer.getNumberOfTreasures());
    }


}