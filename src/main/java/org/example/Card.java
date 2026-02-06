package org.example;

public class Card {
    private final int value;
    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS
    };
    private final Suit suit;

    Card(int value, int suit_in) {
        this.value = value;
        switch(suit_in) {
            case 0: this.suit = Suit.HEARTS;
            break;
            case 1: this.suit = Suit.DIAMONDS;
                break;
            case 2: this.suit = Suit.SPADES;
                break;
            case 3: this.suit = Suit.CLUBS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + suit_in);
        }
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        value = value;
    }
    public Suit getSuit() {
        return suit;
    }
    public void setSuit(int suit) {}
    @Override
    public String toString() {
        String name;
        return value + " " + suit;
    }
    @Override
    public boolean equals(Object o) {
        Card card = (Card)o;
        if (this.value == card.getValue() && this.suit == card.getSuit()) return true;
        return false;
    }
}
