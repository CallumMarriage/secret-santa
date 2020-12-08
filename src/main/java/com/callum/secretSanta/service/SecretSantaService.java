package com.callum.secretSanta.service;

import com.callum.secretSanta.model.Matched;

import java.util.*;

public class SecretSantaService {

    public SecretSantaService(){

    }

    public void pickSecretSanta(HashMap santas) {
        System.out.println("Shuffling Secret Santas");

        List<String> sendGiftNames = new ArrayList<>(santas.keySet());
        List<Matched> recieveGiftNames = new ArrayList<>(santas.values());
        Collections.shuffle(recieveGiftNames, new Random(recieveGiftNames.size() - 1));


        List<Integer> preMatched = new ArrayList<>();

        Map<String, Matched> matchedMap = new HashMap<>();

        for (String sender : sendGiftNames) {
            for (int i = 0; i < recieveGiftNames.size(); i++) {
                if (!preMatched.contains(i) && !sender.equals(recieveGiftNames.get(i).getEmail())) {
                    matchedMap.put(sender, recieveGiftNames.get(i));
                    preMatched.add(i);
                    break;
                }
            }

        }
        System.out.println("Finished shuffling secret santas!");
        System.out.println("Sending emails to secret santas");
        EmailService emailService = new EmailService();

        for (String email : matchedMap.keySet()) {
            emailService.sendEmail(email, matchedMap.get(email).getEmail(), matchedMap.get(email).getName());
        }
        System.out.println("Finished sending emails");
    }
}
