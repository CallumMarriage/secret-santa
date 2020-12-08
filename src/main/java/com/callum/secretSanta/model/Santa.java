package com.callum.secretSanta.model;

public class Santa {
    private String name;

    private String email;

    public Santa(String email, String name ){
        this.name = name;
        this.email = email;
    }

    public Santa(){

    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
}
