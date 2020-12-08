package com.callum.secretSanta;

import com.callum.secretSanta.model.Matched;
import com.callum.secretSanta.service.SecretSantaService;

import java.util.*;


public class Application {



    public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        HashMap<String, Matched> santas = jsonFileReader.readJsonFileToSantas("santas.json");
        SecretSantaService secretSantaService = new SecretSantaService();
        secretSantaService.pickSecretSanta(santas);
    }
}