package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardHolder {
    private ArrayList<Card> cards = new ArrayList<>();
    CardHolder() {}

    public void fillCards() {
        for (int i = 1; i <= 14; i++) {
            for (int j = 0; j <= 3; j++) {
                cards.add(new Card(i, j));
            }
        }
    }

    public void shuffleCards(Random generator) {
        ArrayList<Card> newArray = new ArrayList<>();
        for (int i = 0; i < 56; i++) {
            int randomNum = generator.nextInt(cards.size());
            newArray.add(cards.get(randomNum));
            cards.remove(randomNum);
        }
        cards = newArray;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(int index) {
        cards.remove(index);
    }

    @Override
    public String toString() {
        String str = "";
        int counter = 0;
        for (Card card : cards) {
            str = counter++ + " " + card.toString() + "\n";
        }
        return str;
    }
}
