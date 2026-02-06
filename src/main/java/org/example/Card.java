package org.example;

public class Card {
    private final int value;
    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS
    }
    private final Suit suit;

    Card(int value, int suitIn) {
        this.value = value;
        switch(suitIn) {
            case 0: this.suit = Suit.HEARTS;
            break;
            case 1: this.suit = Suit.DIAMONDS;
                break;
            case 2: this.suit = Suit.SPADES;
                break;
            case 3: this.suit = Suit.CLUBS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + suitIn);
        }
    }
    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value + " " + suit;
    }
}
