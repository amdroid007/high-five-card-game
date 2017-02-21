package com.iliaskomp.highfivecardgame.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IliasKomp on 19/02/17.
 */

public class Deck {
    private static final String TAG = "Deck";

    private List<Card> cards = new ArrayList<>();
    private Card currentCard = null;

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        Log.d(TAG, "Creating deck..");

        addCards();
        shuffle();
        addImagesToCards();
        addDefaultRules();

        Log.d(TAG, "Deck created..");
    }

    private void addCards() {
        Log.d(TAG, "Adding cards...");
        Card card;
        for (Card.SUIT suit : Card.SUIT.values()) {
            for (Card.RANK rank : Card.RANK.values()) {
                card = new Card(suit, rank);
                cards.add(card);
            }
        }

        Log.d(TAG, "Added cards...");

    }

    private void addImagesToCards() {
        Log.d(TAG, "Adding images to cards...");

        for (Card card : cards) {
            card.setDrawableString(card.getRank().toString().toLowerCase() + "_of_"
                    + card.getSuit().toString().toLowerCase());
        }
    }

    private void addDefaultRules() {
        Log.d(TAG, "Adding rules to cards...");

        Rule rule1 = new Rule(0, "Default. High five the person before you.");
        Rule rule2 = new Rule(0, "High five the person after you.");
        Rule rule3 = new Rule(0, "High five yourself.");
        Rule rule4 = new Rule(0, "High five both players around you.");
        Rule rule5 = new Rule(0, "Don't do anything. Direction changes.");

        for (Card card : cards) {
            switch (card.getRank()) {
                case FIVE:
                    card.setRule(rule2);
                    break;
                case TEN:
                    card.setRule(rule4);
                    break;
                case QUEEN:
                    card.setRule(rule5);
                    break;
                case ACE:
                    card.setRule(rule3);
                    break;
                default:
                    card.setRule(rule1);
                    break;
            }
        }

    }

    private void shuffle() {
        Collections.shuffle(cards);
    }


    public List<Card> getCards() {
        return cards;
    }

    public void drawCard() {
        this.burnDrawnCard();
        Log.d(TAG, "Cards size: " + cards.size());
        currentCard = cards.get(0);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void burnDrawnCard() {
        cards.remove(0);
    }

    public int numberOfCardsLeft() {
        return cards.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (Card card : cards) {
            output += card.toString() + "\n";
        }

        return output;
    }
}
