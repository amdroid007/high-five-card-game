package com.iliaskomp.highfivecardgame.models;

public class Card {
    public enum SUIT {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    public enum RANK {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    private final SUIT mSuit;
    private final RANK mRank;
    private Rule mRule;
    private String drawableString;

    public Card(SUIT suit, RANK rank) {
        this.mSuit = suit;
        this.mRank = rank;
        this.drawableString = null;
    }

    public SUIT getSuit() {
        return mSuit;
    }

    public RANK getRank() {
        return mRank;
    }

    public Rule getRule() {
        return mRule;
    }

    public void setRule(Rule rule) {
        this.mRule = rule;
    }

    public String getDrawableString() {
        return drawableString;
    }

    public void setDrawableString(String drawableString) {
        this.drawableString = drawableString;
    }

    @Override
    public String toString() {
        return this.getRank() + " of " + this.getSuit() + " - Rule: " + mRule.getDescription()
                + " - Drawable: " + this.getDrawableString();
    }
}
