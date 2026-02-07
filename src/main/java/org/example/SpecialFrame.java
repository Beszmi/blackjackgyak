package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class SpecialFrame extends JFrame{
    //Used to avoid replicated code used only to make replicas of buttons
    protected class ResetButton extends JButton{
        public ResetButton(){
            super("Reset");
            setFocusable(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            addActionListener(e -> game.reset());
        }
    }
    private CardLayout cl = new CardLayout();
    private HashMap<String, JPanel> panels = new HashMap<>();
    private HashMap<String, JButton> buttons = new HashMap<>();
    private HashMap<String, JLabel> labels = new HashMap<>();
    public Game game;

    public SpecialFrame() {
        panels.put("mainPanel", new JPanel(cl));
        this.setSize(1280, 720);
        this.setTitle("BlackJack");

        ImageIcon icon = new ImageIcon("src/main/resources/fetchimage.png");
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.savePlayerData();
                System.exit(0);
            }
        });

        //Buttons
        buttons.put("hitButton", new JButton("Hit"));
        buttons.get("hitButton").setFocusable(false);
        buttons.get("hitButton").addActionListener(e -> game.playerHit());

        buttons.put("standButton", new JButton("Stand"));
        buttons.get("standButton").setFocusable(false);
        buttons.get("standButton").addActionListener(e -> game.dealerAi());

        buttons.put("reset1Button", new ResetButton());
        buttons.put("reset2Button", new ResetButton());
        buttons.put("reset3Button", new ResetButton());
        buttons.put("reset4Button", new ResetButton());

        //Labels
        labels.put("winLabel", new JLabel("VICTORY!"));
        labels.get("winLabel").setFocusable(false);
        labels.get("winLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("winLabel").setFont(new Font("Arial", Font.BOLD, 72));

        labels.put("drawLabel", new JLabel("DRAW!"));
        labels.get("drawLabel").setFocusable(false);
        labels.get("drawLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("drawLabel").setFont(new Font("Arial", Font.BOLD, 72));

        labels.put("loseLabel", new JLabel("LOSE!"));
        labels.get("loseLabel").setFocusable(false);
        labels.get("loseLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("loseLabel").setFont(new Font("Arial", Font.BOLD, 72));

        //Panels
        JPanel top1 = new JPanel();
        top1.setBackground(Color.red);
        top1.setPreferredSize(new Dimension(1000, 360));
        panels.put("top1", top1);

        JPanel top2 = new JPanel();
        top2.setBackground(Color.blue);
        top2.setPreferredSize(new Dimension(200, 300));
        top2.add(buttons.get("hitButton"));
        top2.add(buttons.get("standButton"));
        top2.add(buttons.get("reset1Button"));
        panels.put("top2", top2);

        panels.put("bottom", new JPanel());
        panels.get("bottom").setBackground(Color.green);
        panels.get("bottom").setPreferredSize(new Dimension(1280, 360));

        panels.put("winPanel", new JPanel());
        panels.get("winPanel").setLayout(new BoxLayout(panels.get("winPanel"), BoxLayout.Y_AXIS));
        panels.get("winPanel").setBackground(Color.green);
        panels.get("winPanel").setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panels.get("winPanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("winPanel").add(labels.get("winLabel"));
        panels.get("winPanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("winPanel").add(buttons.get("reset2Button"));

        panels.put("drawPanel", new JPanel());
        panels.get("drawPanel").setLayout(new BoxLayout(panels.get("drawPanel"), BoxLayout.Y_AXIS));
        panels.get("drawPanel").setBackground(Color.blue);
        panels.get("drawPanel").setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panels.get("drawPanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("drawPanel").add(labels.get("drawLabel"));
        panels.get("drawPanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("drawPanel").add(buttons.get("reset3Button"));

        panels.put("losePanel", new JPanel());
        panels.get("losePanel").setLayout(new BoxLayout(panels.get("losePanel"), BoxLayout.Y_AXIS));
        panels.get("losePanel").setBackground(Color.red);
        panels.get("losePanel").setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panels.get("losePanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("losePanel").add(labels.get("loseLabel"));
        panels.get("losePanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("losePanel").add(buttons.get("reset4Button"));

        //Cards
        panels.put("mainPanel", new JPanel(cl));

        panels.put("gameCard", new JPanel());
        panels.get("gameCard").setBackground(Color.black);
        panels.get("gameCard").add(panels.get("top1"));
        panels.get("gameCard").add(panels.get("top2"));
        panels.get("gameCard").add(panels.get("bottom"));
        panels.get("gameCard").setBounds(0,0,1280,720);

        panels.put("winCard", new JPanel(new GridBagLayout()));
        panels.get("winCard").setBackground(Color.LIGHT_GRAY);
        panels.get("winCard").add(panels.get("winPanel"));
        panels.get("winCard").setBounds(0,0,1280,720);

        panels.put("drawCard", new JPanel(new GridBagLayout()));
        panels.get("drawCard").setBackground(Color.LIGHT_GRAY);
        panels.get("drawCard").add(panels.get("drawPanel"));
        panels.get("drawCard").setBounds(0,0,1280,720);

        panels.put("loseCard", new JPanel(new GridBagLayout()));
        panels.get("loseCard").setBackground(Color.LIGHT_GRAY);
        panels.get("loseCard").add(panels.get("losePanel"));
        panels.get("loseCard").setBounds(0,0,1280,720);

        panels.get("mainPanel").add("gameCard", panels.get("gameCard"));
        panels.get("mainPanel").add("winCard", panels.get("winCard"));
        panels.get("mainPanel").add("drawCard", panels.get("drawCard"));
        panels.get("mainPanel").add("loseCard", panels.get("loseCard"));

        cl.show(panels.get("mainPanel"), "gameCard");

        add(panels.get("mainPanel"));
    }

    public void setupGameConnection(Game game) {
        this.game = game;
    }

    public void addLabelIntoNumberedPanel(String panelName, JLabel label){
        panels.get(panelName).add(label);
    }

    //Used to reset the screen after a game is restarted/finished
    public void reset() {
        panels.get("top1").removeAll();
        panels.get("bottom").removeAll();
        cl.show(panels.get("mainPanel"), "gameCard");
    }

    //Abstracts the call to show cards in the layout
    public void showCard(String name) {
        cl.show(panels.get("mainPanel"), name);
    }

    //Removes the dealers unrevealed cards visual representation
    public void removeHiddenCard() {
        panels.get("top1").remove(1);
    }

}
