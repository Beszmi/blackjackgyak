package org.example;

import java.util.ArrayList;
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
        ArrayList<Card> new_array = new ArrayList<Card>();
        for (int i = 0; i < 56; i++) {
            int random_num = generator.nextInt(cards.size());
            new_array.add(cards.get(random_num));
            cards.remove(random_num);
        }
        cards = new_array;
    }

    public ArrayList<Card> getCards() {
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
            str += counter++ + " " + card.toString();
            str += "\n";
        }
        return str;
    }
}
