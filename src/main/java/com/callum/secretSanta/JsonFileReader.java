package com.callum.secretSanta;

import com.callum.secretSanta.model.Matched;
import com.callum.secretSanta.model.Santa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class JsonFileReader {


    public HashMap<String, Matched> readJsonFileToSantas(String jsonFile){
        try {
            String fileContents = FileUtils.readFileToString(new File("src/main/resources/"+jsonFile));
            Santa[] santas = new ObjectMapper().readValue(fileContents, Santa[].class);
            HashMap<String, Matched> matchedHashMap = new HashMap<>();
            for(int i = 0; i < santas.length; i++){
                Santa santa = santas[i];
                matchedHashMap.put(santa.getEmail(), new Matched(santa.getName(), santa.getEmail()));
            }
            return matchedHashMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
