package com.iliaskomp.highfivecardgame.models;

public class Rule {
    // --Commented out by Inspection (21/02/17 23:30):private final int id;
    private final String description;

    public Rule(String description) {
//        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
