package main.system;

import main.game.Game;
import main.IO.Input;
import main.IO.Output;

import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        List<String> mapInformation = Input.readMap();

        Game game = new Game(mapInformation);
        game.Run();

        Output.saveEndOfGame(game.getMapState(),game.getAdventurerList());

    }
}
