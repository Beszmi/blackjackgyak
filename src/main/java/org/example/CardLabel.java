package org.example;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel {
    private static final ImageIcon kartyaKep = new ImageIcon("resources/kartya.png");
    private static final ImageIcon emptyKep = new ImageIcon("resources/kartyaEmpty.png");
    public CardLabel() {
        ImageIcon emptyKep = new ImageIcon("resources/kartyaEmpty.png");
        this.setIcon(emptyKep);
    }

    public CardLabel(Card card) {
        this.setIcon(kartyaKep);
        this.setText(card.toString());
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setHorizontalTextPosition(JLabel.CENTER);
    }
}
