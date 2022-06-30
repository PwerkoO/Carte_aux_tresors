package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

class InputTest {

    @Test
    void readMap() throws IOException, NullPointerException {
        File folder = new File("map");
        for(File file : Objects.requireNonNull(folder.listFiles())){
            List<String> mapInformation = Files.readAllLines(file.toPath());
            final String[] s = mapInformation.get(0).replaceAll(" ","").split("-");
            Assertions.assertTrue(s[0].length() == 1 && s[0].equals("C"), "In file "+file.getName()+", "+s[0]+" is not a valid input (line "+(1)+")");
            Assertions.assertTrue(isInteger(s[1]));
            Assertions.assertTrue(isInteger(s[2]));
            int width = Integer.parseInt(s[1]);
            int height = Integer.parseInt(s[2]);
            int x,y;
            for(int i = 1; i < mapInformation.size(); i++){
                final String[] s2 = mapInformation.get(i).replaceAll(" ","").split("-");
                switch(s2[0]){
                    case "M":
                        Assertions.assertTrue(isInteger(s2[1]),"In file "+file.getName()+", conversion to int failed '"+ s2[1]+"' can not be converted to int (line "+(i+1)+")");
                        Assertions.assertTrue(isInteger(s2[2]),"In file "+file.getName()+", conversion to int failed '"+ s2[2]+"' can not be converted to int (line "+(i+1)+")");

                        x = Integer.parseInt(s2[1]);
                        y = Integer.parseInt(s2[2]);
                        Assertions.assertTrue(x >= 0 && x < width && y >= 0 && y < height, s2[0] + "is out of bound (line "+(i+1)+")");
                        break;
                    case "T":
                        Assertions.assertTrue(isInteger(s2[1]),"In file "+file.getName()+", conversion to int failed '"+ s2[1]+"' can not be converted to int (line "+(i+1)+")");
                        Assertions.assertTrue(isInteger(s2[2]),"In file "+file.getName()+", conversion to int failed '"+ s2[2]+"' can not be converted to int (line "+(i+1)+")");
                        Assertions.assertTrue(isInteger(s2[3]),"In file "+file.getName()+", conversion to int failed '"+ s2[3]+"' can not be converted to int (line "+(i+1)+")");

                        x = Integer.parseInt(s2[1]);
                        y = Integer.parseInt(s2[2]);
                        Assertions.assertTrue(x >= 0 && x < width && y >= 0 && y < height, s2[0] + "is out of bound (line "+(i+1)+")");
                        break;
                    case "A":
                        String name = s2[1];
                        Assertions.assertTrue(isInteger(s2[2]),"In file "+file.getName()+", conversion to int failed '"+ s2[2]+"' can not be converted to int (line "+(i+1)+")");
                        Assertions.assertTrue(isInteger(s2[3]),"In file "+file.getName()+", conversion to int failed '"+ s2[3]+"' can not be converted to int (line "+(i+1)+")");
                        Assertions.assertTrue(s2[4].length() == 1 && "NEWS".contains(s2[4]), "In file "+file.getName()+", "+s2[4]+" is not a valid orientation (line "+(i+1)+")");
                        Assertions.assertEquals(0, s2[5].replaceAll("[AGD]", "").length(), "In file " + file.getName() + ", " + s2[5] + " is not a valid path (line " + (i + 1) + ")");

                        x = Integer.parseInt(s2[2]);
                        y = Integer.parseInt(s2[3]);
                        Assertions.assertTrue(x >= 0 && x < width && y >= 0 && y < height, s2[0] + "is out of bound (line "+(i+1)+")");
                        break;
                    default:
                        Assertions.fail("In file "+file.getName()+", "+ s2[0]+ " is not a valid input (line"+(i+1)+")");
                }
            }
        }
    }

    public boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }
}