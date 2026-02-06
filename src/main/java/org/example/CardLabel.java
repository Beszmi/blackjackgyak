package org.example;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel {

    public CardLabel() {
        ImageIcon emptyKep = new ImageIcon("resources/kartyaEmpty.png");
        this.setIcon(emptyKep);
    }

    public CardLabel(Card card) {
        ImageIcon kartyaKep = new ImageIcon("resources/kartya.png");
        this.setIcon(kartyaKep);
        this.setText(card.toString());
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setHorizontalTextPosition(JLabel.CENTER);
    }
}
