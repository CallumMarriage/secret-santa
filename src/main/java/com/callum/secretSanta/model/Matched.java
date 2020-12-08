package com.callum.secretSanta.model;

public class Matched {

    private String name;

    private String email;

    public Matched(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
}
