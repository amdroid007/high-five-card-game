package com.iliaskomp.highfivecardgame.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IliasKomp on 19/02/17.
 */

public class Deck {
    //private final static int NUMBER_OF_CARDS = 52;
    private List<Card> cards = new ArrayList<>();
    private Card currentCard = null;

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        addCards();
        shuffle();
        addImagesToCards();
        addDefaultRules();
    }

    private void addCards() {
        Card card;
        for (Card.SUIT suit : Card.SUIT.values()) {
            for (Card.RANK rank : Card.RANK.values()) {
                card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    private void addImagesToCards() {
        for (Card card : cards) {
            card.setDrawableString(card.getRank().toString().toLowerCase() + "_of_"
                    + card.getSuit().toString().toLowerCase());
        }
    }

    private void addDefaultRules() {
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
        currentCard = cards.get(0);
        burnDrawnCard();
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
}
