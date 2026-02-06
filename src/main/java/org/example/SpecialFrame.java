package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpecialFrame extends JFrame{
    private CardLayout cl = new CardLayout();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private JPanel mainpanel = new JPanel(cl);
    private JPanel card1 = new JPanel();
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");

    public Game game;
    public boolean gameConnected = false;

    public SpecialFrame() {
        this.setSize(1280, 720);
        this.setTitle("BlackJack");

        ImageIcon icon = new ImageIcon("src/main/resources/fetchimage.png");
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //this.setResizable(false);

        //Buttons
        JButton hitButton = new JButton("Hit");
        hitButton.setHorizontalAlignment(SwingConstants.RIGHT);
        hitButton.setFocusable(false);
        hitButton.addActionListener(e -> game.playerHit());

        JButton standButton = new JButton("Stand");
        hitButton.setFocusable(false);
        standButton.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.setHorizontalAlignment(SwingConstants.RIGHT);
        resetButton.addActionListener(e -> game.reset());

        //Panels
        JPanel top1 = new JPanel();
        top1.setBackground(Color.red);
        top1.setPreferredSize(new Dimension(1000, 360));
        panels.add(top1);

        JPanel top2 = new JPanel();
        top2.setBackground(Color.blue);
        top2.setPreferredSize(new Dimension(200, 300));
        top2.add(hitButton);
        top2.add(standButton);
        top2.add(resetButton);
        panels.add(top2);

        JPanel bottom= new JPanel();
        bottom.setBackground(Color.green);
        bottom.setPreferredSize(new Dimension(1280, 360));
        panels.add(bottom);

        //Cards
        card1.setBackground(Color.black);
        card1.add(panels.get(0));
        card1.add(panels.get(1));
        card1.add(panels.get(2));
        card1.setBounds(0,0,1280,720);

        mainpanel.add("card1", card1);

        cl.show(mainpanel, "card1");

        add(mainpanel);
    }

    public void setupGameConnection(Game game) {
        this.game = game;
        gameConnected = true;
    }

    public void addLabelIntoNumberedPanel(int panelId, JLabel label){
        panels.get(panelId).add(label);
    }

    public void reset() {
        panels.get(0).removeAll();
        panels.get(2).removeAll();
    }

}
