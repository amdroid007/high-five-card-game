package com.iliaskomp.highfivecardgame.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final String TAG = "Deck";

    private List<Card> cards = new ArrayList<>();
    private Card currentCard = null;
    private List<Card.RANK> randomCards = null;
    private List<Card.RANK> defaultCards = null;

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        Log.d(TAG, "Creating deck..");

        addCards();
        shuffle();
        addImagesToCards();

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

    public void addDefaultRules() {
        Log.d(TAG, "Adding default rules to cards...");

        Rule rule1 = new Rule("Default. High five the person before you.");
        Rule rule2 = new Rule("High five the person after you.");
        Rule rule3 = new Rule("High five yourself.");
        Rule rule4 = new Rule("High five both players around you.");
        Rule rule5 = new Rule("Don't do anything. Direction changes.");

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
        defaultCards = new ArrayList<>();
        defaultCards.add(Card.RANK.FIVE);
        defaultCards.add(Card.RANK.TEN);
        defaultCards.add(Card.RANK.QUEEN);
        defaultCards.add(Card.RANK.ACE);


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

    private void burnDrawnCard() {
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

    public void addRandomRules() {
        Log.d(TAG, "Adding random rules to cards...");

        Rule rule0 = new Rule("Default. High five the person before you.");
        Rule rule1 = new Rule("High five the person after you.");
        Rule rule2 = new Rule("High five yourself.");
        Rule rule3 = new Rule("High five both players around you.");
        Rule rule4 = new Rule("Don't do anything. Direction changes.");

        List<Integer> random = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            random.add(i);
        }
        Collections.shuffle(random);

        final Card.RANK[] values = Card.RANK.values();

        for (Card card : cards) {
            Card.RANK rank = card.getRank();

            if (rank == values[random.get(0)]) {
                card.setRule(rule1);
            } else if (rank == values[random.get(1)]) {
                card.setRule(rule2);
            } else if (rank == values[random.get(2)]) {
                card.setRule(rule3);
            } else if (rank == values[random.get(3)]) {
                card.setRule(rule4);
            } else {
                card.setRule(rule0);
            }
        }

        randomCards = new ArrayList<>();
        randomCards.add(values[random.get(0)]);
        randomCards.add(values[random.get(1)]);
        randomCards.add(values[random.get(2)]);
        randomCards.add(values[random.get(3)]);

    }

    public List<Card.RANK> getRanksWithRandomRules() {
        return randomCards;
    }

    public List<Card.RANK> getRanksWithDefaultRules() {
        return defaultCards;
    }

    public String getRuleDescriptionFromRank(Card.RANK rank) {
        for (Card card : getCards()) {
            if (card.getRank() == rank) {
                return card.getRule().getDescription();
            }
        }
        return null;
    }

    public String getDrawableStringFromRank(Card.RANK rank) {
        for (Card card : getCards()) {
            if (card.getRank() == rank && card.getSuit() ==Card.SUIT.HEARTS) {
                return card.getDrawableString();
            }
        }
        return null;
    }


}
