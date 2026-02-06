package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SpecialFrame extends JFrame{
    private CardLayout cl = new CardLayout();
    private HashMap<String, JPanel> panels = new HashMap<>();
    private JPanel mainpanel = new JPanel(cl);
    private JPanel card1 = new JPanel();
    private JPanel card2 = new JPanel(new GridBagLayout());
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");

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
        standButton.addActionListener(e -> {
            cl.show(mainpanel, "card2");
            System.out.println("winpanelnek kéne lennie");
        });

        JButton resetButton1 = new JButton("Reset");
        resetButton1.setFocusable(false);
        resetButton1.addActionListener(e -> game.reset());

        JButton resetButton2 = new JButton("Reset");
        resetButton2.setFocusable(false);
        resetButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton2.addActionListener(e -> game.reset());

        JLabel winText = new JLabel("VICTORY!");
        winText.setFocusable(false);
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
        winText.setFont(new Font("Arial", Font.BOLD, 72));

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
        winPanel.setBackground(Color.red);
        winPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        winPanel.setPreferredSize(new Dimension(1280, 720));
        winPanel.add(winText);
        winPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        winPanel.add(resetButton2);
        panels.put("winPanel", winPanel);

        //Cards
        card1.setBackground(Color.black);
        card1.add(panels.get("top1"));
        card1.add(panels.get("top2"));
        card1.add(panels.get("bottom"));
        card1.setBounds(0,0,1280,720);

        card2.setBackground(Color.LIGHT_GRAY);
        card2.add(panels.get("winPanel"));
        card2.setBounds(0,0,1280,720);

        mainpanel.add("card1", card1);
        mainpanel.add("card2", card2);

        cl.show(mainpanel, "card1");

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
        cl.show(mainpanel, "card1");
    }

}
