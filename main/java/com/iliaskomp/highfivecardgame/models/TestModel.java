package com.iliaskomp.highfivecardgame.models;

import java.util.List;

/**
 * Created by IliasKomp on 19/02/17.
 */

public class TestModel {

    public static void main(String[] args){
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();

        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println("--------------------------");
        deck.drawCard();
        System.out.println("get drawable string: " + deck.getCurrentCard().getDrawableString());

//        System.out.println(deck.getCurrentCard().getRank());
//        System.out.println(deck.getCurrentCard().getRank().toString());
//        System.out.println(deck.getCurrentCard().getRank().toString().toLowerCase());

//        System.out.println("");
//        System.out.println("SIZE OF DECK: " + cards.size());
//        deck.drawCard();
//        System.out.println("Card drawn: " + deck.getCurrentCard());
//        deck.drawCard();
//        System.out.println("Card drawn: " + deck.getCurrentCard());
//        deck.drawCard();
//        System.out.println("Card drawn: " + deck.getCurrentCard());
//        System.out.println("SIZE OF DECK: " + cards.size());

    }
}
