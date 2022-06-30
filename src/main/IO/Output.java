package main.IO;

import main.game.Adventurer;
import main.game.GameMap;
import main.game.Mountain;
import main.game.Plain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Output {
    public static void saveEndOfGame(final GameMap gameMapState,final List<Adventurer> adventurerList) throws IOException {
        StringBuilder sb = new StringBuilder();
        int width = gameMapState.getWidth();
        int height = gameMapState.getHeight();

        String carteInformation = "C - "+ width +" - "+height;
        sb.append(carteInformation);
        sb.append("\n");

        List<String> mountainList = new ArrayList<>();
        List<String> treasureList = new ArrayList<>();
        final Plain[][] map = gameMapState.getMapState();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Plain plain = map[y][x];
                if(plain instanceof Mountain){
                    mountainList.add("M - "+x+" - "+y);
                }
                else{
                    int numberOfTreasure = plain.getNumberOfTreasures();
                    if(numberOfTreasure > 0)
                        treasureList.add("T - "+x+" - "+y+" - "+numberOfTreasure);
                }
            }
        }
        for(String s : mountainList){
            sb.append(s);
            sb.append("\n");
        }
        for(String s : treasureList){
            sb.append(s);
            sb.append("\n");
        }
        for(Adventurer adventurer : adventurerList){
            String adventurerInformation = "A - "+ adventurer.getName()+" - "+adventurer.getX()+" - "+ adventurer.getY()+" - "+ adventurer.getOrientation()+ " - "+ adventurer.getNumberOfTreasures();
            sb.append(adventurerInformation);
            sb.append("\n");
        }
        System.out.println(sb);

        BufferedWriter writer = new BufferedWriter(new FileWriter("results\\Output.txt"));
        writer.write(sb.toString());
        writer.close();
    }
}
