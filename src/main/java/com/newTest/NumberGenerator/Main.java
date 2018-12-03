package com.newTest.NumberGenerator;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

       List<String> names = new ArrayList<String>();

       names.add("Callum");
       names.add("Tom");
       names.add("Caner");
       names.add("Daniel");
       names.add("Andrew");
       System.out.println(pickName(names, 2));
       List<String> games = shuffleFile("./videoGames.txt");

       if(games != null){
           for(int i =0; i < games.size(); i++){
               if(i % 2 == 0 ){
                   System.out.println("|----------| round 1 |----------|");
               }
               System.out.println(games.get(i));

           }
       } else {
           System.out.println("ERROR! Game file does not exist or has a format problem!");
       }

    }

    private static String pickName(List<String> names, Integer numberOfNames){
        String title = ">> Picking ";
        if(numberOfNames == 1){
            title += " a ";
        }
        title += numberOfNames + " name";
        if(numberOfNames > 1){
            title += "s";
        }
        System.out.println(title + "\n");

        Integer random;
        StringBuilder returnedNames = new StringBuilder();
        for(int i = 1; i <= numberOfNames; i++) {
            random = new SecureRandom().nextInt(names.size() - 1);
            returnedNames.append(names.get(random));
            if(i != numberOfNames){
                returnedNames.append(", ");
            }
            names.remove(names.get(random));
        }
        return returnedNames.toString();
    }

    private static List<String> shuffleFile(String pathToFile){
        System.out.println("\n>> Shuffling Shows \n");
        List<String> unorganisedGames;
        try {
            unorganisedGames = Arrays.asList(FileUtils.readFileToString(new File(pathToFile)).split("\n"));
            Collections.shuffle(unorganisedGames);
            return unorganisedGames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
