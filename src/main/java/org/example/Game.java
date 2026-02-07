package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class Game {
    Random random = new Random();
    private SpecialFrame frame;
    private CardHolder ch = new CardHolder();
    private HashMap<String, Player> players = new HashMap<>();
    private Player currentPlayer;
    private CardHolder playersCards = new CardHolder();
    private CardHolder dealer = new CardHolder();
    private int playerValue = 0;
    private int dealerValue = 0;
    private int playerAceCount = 0;
    private int dealerAceCount = 0;
    private int betAmount = 10;

    public Game(SpecialFrame sajatFrame) {
        frame = sajatFrame;
    }
    public void setup() {
        players = Player.deSerializePlayers();
        currentPlayer = players.get("Ja");
        ch.fillCards();
        ch.shuffleCards(random);

        CardLabel card1 = new CardLabel(ch.getCards().getFirst());
        dealer.addCard(ch.getCards().getFirst());
        ch.removeCard(0);
        CardLabel uresKaryta = new CardLabel();
        CardLabel card2 = new CardLabel(ch.getCards().getFirst());
        playersCards.addCard(ch.getCards().getFirst());
        ch.removeCard(0);

        frame.addLabelIntoNumberedPanel("top1",card1);
        frame.addLabelIntoNumberedPanel("top1",uresKaryta);

        frame.addLabelIntoNumberedPanel("bottom",card2);
        updateAll();
    }

    public void reset () {
        playersCards = new CardHolder();
        dealer = new CardHolder();
        ch = new CardHolder();
        frame.reset();

        setup();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void updatePlayerValue() {
        playerAceCount = 0;
        playerValue = 0;
        for (Card card : playersCards.getCards()) {
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

    public int finalValue(int value, int aceCount) {
        if (aceCount > 21) {
            return value;
        }

        //Calculates the value with aces upto the amount of aces in hand quits at the first one that is a bust.
        int fValue = value;
        for (int i = 1; i <= aceCount; i++) {
            if (value + i*10 <= 21) {
                fValue = value + i*10;
            }
        }
        return fValue;
    }

    public String displayValue(int value, int aceCount) {
        if (aceCount > 0) {
            return value + "/" + calculateSoftValue(value, aceCount);
        }
        return String.valueOf(value);
    }

    public void playerHit() {
        CardLabel card = new CardLabel(ch.getCards().getFirst());
        playersCards.addCard(ch.getCards().getFirst());
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
        updateDealerValue();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void dealerAi() {
        frame.removeHiddenCard();
        while (calculateSoftValue(dealerValue, dealerAceCount) < 17) {
            dealerHit();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep error");
            }
        }
        resultLogic();
    }

    public void resultLogic() {
        if (finalValue(playerValue, playerAceCount) > 21) {
            System.out.println("bust");
            lose();
        } else if (finalValue(dealerValue, dealerAceCount) > 21) {
            System.out.println("dealer bust");
            win();
        } else if (finalValue(dealerValue, dealerAceCount) < finalValue(playerValue, playerAceCount)) {
            System.out.println("regular win");
            win();
        } else if (finalValue(dealerValue, dealerAceCount) > finalValue(playerValue, playerAceCount)) {
            System.out.println("regular lose");
            lose();
        } else if (finalValue(dealerValue, dealerAceCount) == finalValue(playerValue, playerAceCount)) {
            System.out.println("draw");
            draw();
        }
    }

    public void win() {
        frame.showCard("winCard");
        currentPlayer.setWins(currentPlayer.getWins() + 1);
        currentPlayer.setChips((toIntExact(round(currentPlayer.getChips() + betAmount * 1.5))));
    }

    public void draw() {
        frame.showCard("drawCard");
        currentPlayer.setDraws(currentPlayer.getDraws() + 1);
    }

    public void lose() {
        frame.showCard("loseCard");
        currentPlayer.setLosses(currentPlayer.getLosses() + 1);
        currentPlayer.setChips(currentPlayer.getChips() - betAmount);
    }

    public void savePlayerData() {
        Player.serializePlayers(players);
    }
}
