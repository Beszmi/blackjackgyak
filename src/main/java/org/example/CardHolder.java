package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHolder {
    private ArrayList<Card> cards = new ArrayList<>();
    CardHolder() {}

    public void fillCards() {
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j <= 3; j++) {
                cards.add(new Card(i, j));
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
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
