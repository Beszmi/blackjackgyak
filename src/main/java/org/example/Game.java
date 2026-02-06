package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game {
    Random random = new Random();
    private SpecialFrame frame;
    private CardHolder ch = new CardHolder();
    private CardHolder player = new CardHolder();
    private CardHolder dealer = new CardHolder();
    private int playerValue = 0;
    private int dealerValue = 0;
    private int playerAceCount = 0;
    private int dealerAceCount = 0;

    public Game(SpecialFrame sajatFrame) {
        frame = sajatFrame;
    }
    public void setup() {
        ch.fillCards();
        ch.shuffleCards(random);

        CardLabel card1 = new CardLabel(ch.getCards().getFirst());
        dealer.addCard(ch.getCards().getFirst());
        ch.removeCard(0);
        CardLabel uresKaryta = new CardLabel();
        CardLabel card2 = new CardLabel(ch.getCards().getFirst());
        player.addCard(ch.getCards().getFirst());
        ch.removeCard(0);

        frame.addLabelIntoNumberedPanel("top1",card1);
        frame.addLabelIntoNumberedPanel("top1",uresKaryta);

        frame.addLabelIntoNumberedPanel("bottom",card2);
        updateAll();
    }

    public void reset () {
        player = new CardHolder();
        dealer = new CardHolder();
        ch = new CardHolder();
        frame.reset();

        setup();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void updatePlayerValue() {
        playerAceCount = 0;
        playerValue = 0;
        for (Card card : player.getCards()) {
            if (card.getValue() == 1) {
                playerAceCount++;
            }
            playerValue += Math.min(card.getValue(), 10);
        }
        System.out.println(displayValue(playerValue, playerAceCount));
    }

    public void updateDealerValue() {
        dealerAceCount = 0;
        dealerValue = 0;
        for (Card card : dealer.getCards()) {
            if (card.getValue() == 1) {
                dealerAceCount++;
            }
            dealerValue += Math.min(card.getValue(), 10);
        }
        System.out.println(displayValue(dealerValue, dealerAceCount));
    }

    public void updateAll() {
        updatePlayerValue();
        updateDealerValue();
    }

    public int calculateSoftValue(int value, int aceCount) {
        if (aceCount == 0) {
            return value;
        } else  {
            return value + aceCount*10;
        }
    }

    public String displayValue(int value, int aceCount) {
        if (aceCount > 0) {
            return value + "/" + calculateSoftValue(value, aceCount);
        }
        return String.valueOf(value);
    }

    public void playerHit() {
        CardLabel card = new CardLabel(ch.getCards().getFirst());
        player.addCard(ch.getCards().getFirst());
        ch.removeCard(0);
        frame.addLabelIntoNumberedPanel("bottom",card);
        updatePlayerValue();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void dealerHit() {
        CardLabel card = new CardLabel(ch.getCards().getFirst());
        dealer.addCard(ch.getCards().getFirst());
        ch.removeCard(0);
        frame.addLabelIntoNumberedPanel("top1",card);
        updatePlayerValue();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void dealerAi() {
        while (calculateSoftValue(dealerValue, dealerAceCount) < 17) {
            dealerHit();
        }
    }
}
