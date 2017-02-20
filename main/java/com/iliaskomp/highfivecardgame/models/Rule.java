package com.iliaskomp.highfivecardgame.models;

/**
 * Created by IliasKomp on 19/02/17.
 */

public class Rule {
    private int id;
    private String description;

    public Rule(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
