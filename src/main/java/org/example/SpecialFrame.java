package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SpecialFrame extends JFrame{
    private CardLayout cl = new CardLayout();
    private HashMap<String, JPanel> panels = new HashMap<>();
    private JPanel mainpanel = new JPanel(cl);
    private JPanel gameCard = new JPanel();
    private JPanel winCard = new JPanel(new GridBagLayout());
    private JPanel drawCard = new JPanel(new GridBagLayout());
    private JPanel loseCard = new JPanel(new GridBagLayout());

    public Game game;

    public SpecialFrame() {
        this.setSize(1280, 720);
        this.setTitle("BlackJack");

        ImageIcon icon = new ImageIcon("src/main/resources/fetchimage.png");
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);

        //Buttons
        JButton hitButton = new JButton("Hit");
        hitButton.setHorizontalAlignment(SwingConstants.RIGHT);
        hitButton.setFocusable(false);
        hitButton.addActionListener(e -> game.playerHit());

        JButton standButton = new JButton("Stand");
        hitButton.setFocusable(false);
        standButton.setHorizontalAlignment(SwingConstants.RIGHT);
        standButton.addActionListener(e -> game.dealerAi());

        JButton resetButton1 = new JButton("Reset");
        resetButton1.setFocusable(false);
        resetButton1.addActionListener(e -> game.reset());

        JButton resetButton2 = new JButton("Reset");
        resetButton2.setFocusable(false);
        resetButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton2.addActionListener(e -> game.reset());

        //Labels
        JLabel winText = new JLabel("VICTORY!");
        winText.setFocusable(false);
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
        winText.setFont(new Font("Arial", Font.BOLD, 72));

        JLabel drawText = new JLabel("DRAW!");
        drawText.setFocusable(false);
        drawText.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawText.setFont(new Font("Arial", Font.BOLD, 72));

        JLabel loseText = new JLabel("LOSE!");
        loseText.setFocusable(false);
        loseText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loseText.setFont(new Font("Arial", Font.BOLD, 72));

        //Panels
        JPanel top1 = new JPanel();
        top1.setBackground(Color.red);
        top1.setPreferredSize(new Dimension(1000, 360));
        panels.put("top1", top1);

        JPanel top2 = new JPanel();
        top2.setBackground(Color.blue);
        top2.setPreferredSize(new Dimension(200, 300));
        top2.add(hitButton);
        top2.add(standButton);
        top2.add(resetButton1);
        panels.put("top2", top2);

        JPanel bottom= new JPanel();
        bottom.setBackground(Color.green);
        bottom.setPreferredSize(new Dimension(1280, 360));
        panels.put("bottom", bottom);

        JPanel winPanel = new JPanel();
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        winPanel.setBackground(Color.green);
        winPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        winPanel.setPreferredSize(new Dimension(1280, 720));
        winPanel.add(winText);
        winPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        winPanel.add(resetButton2);
        panels.put("winPanel", winPanel);

        JPanel drawPanel = new JPanel();
        drawPanel.setLayout(new BoxLayout(drawPanel, BoxLayout.Y_AXIS));
        drawPanel.setBackground(Color.blue);
        drawPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        drawPanel.setPreferredSize(new Dimension(1280, 720));
        drawPanel.add(drawText);
        drawPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        drawPanel.add(resetButton2);
        panels.put("drawPanel", drawPanel);

        JPanel losePanel = new JPanel();
        losePanel.setLayout(new BoxLayout(losePanel, BoxLayout.Y_AXIS));
        losePanel.setBackground(Color.red);
        losePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        losePanel.setPreferredSize(new Dimension(1280, 720));
        losePanel.add(loseText);
        losePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        losePanel.add(resetButton2);
        panels.put("losePanel", losePanel);

        //Cards
        gameCard.setBackground(Color.black);
        gameCard.add(panels.get("top1"));
        gameCard.add(panels.get("top2"));
        gameCard.add(panels.get("bottom"));
        gameCard.setBounds(0,0,1280,720);

        winCard.setBackground(Color.LIGHT_GRAY);
        winCard.add(panels.get("winPanel"));
        winCard.setBounds(0,0,1280,720);

        drawCard.setBackground(Color.LIGHT_GRAY);
        drawCard.add(panels.get("drawPanel"));
        drawCard.setBounds(0,0,1280,720);

        loseCard.setBackground(Color.LIGHT_GRAY);
        loseCard.add(panels.get("losePanel"));
        loseCard.setBounds(0,0,1280,720);

        mainpanel.add("gameCard", gameCard);
        mainpanel.add("winCard", winCard);
        mainpanel.add("drawCard", drawCard);
        mainpanel.add("loseCard", loseCard);

        cl.show(mainpanel, "gameCard");

        add(mainpanel);
    }

    public void setupGameConnection(Game game) {
        this.game = game;
    }

    public void addLabelIntoNumberedPanel(String panelName, JLabel label){
        panels.get(panelName).add(label);
    }

    public void reset() {
        panels.get("top1").removeAll();
        panels.get("bottom").removeAll();
        cl.show(mainpanel, "gameCard");
    }

    public void showCard(String name) {
        cl.show(mainpanel, name);
    }

    public void removeHiddenCard() {
        panels.get("top1").remove(1);
    }

}
