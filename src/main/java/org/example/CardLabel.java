package org.example;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel {
    private static final ImageIcon cardImg = new ImageIcon("resources/card.png");
    private static final ImageIcon emptyImg = new ImageIcon("resources/cardEmpty.png");
    public CardLabel() {
        this.setIcon(emptyImg);
    }

    public CardLabel(Card card) {
        this.setIcon(cardImg);
        this.setText(card.toString());
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
    }
}
