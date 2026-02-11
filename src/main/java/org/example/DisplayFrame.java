package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.example.config.*;

public class DisplayFrame extends JFrame{
    //Used to avoid replicated code used only to make replicas of buttons
    protected static class ResetButton extends JButton{
        public ResetButton(){
            super("Reset");
            setFocusable(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            addActionListener(e -> game.reset());
        }
    }

    protected static class statLabel extends JLabel{
        public statLabel(){
            super("Value: no data");
            setFocusable(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setFont(new Font("Arial", Font.BOLD, 20));
            setForeground(Color.white);
        }
    }

    private final CardLayout cl = new CardLayout();
    private final HashMap<String, JPanel> panels = new HashMap<>();
    private final HashMap<String, JButton> buttons = new HashMap<>();
    private final HashMap<String, JLabel> labels = new HashMap<>();
    public static Game game;

    public DisplayFrame() {
        Image bgImage = null;
        try {
            bgImage = ImageIO.read(new File("resources/bg.jpg"));
        } catch (IOException e) {e.printStackTrace();}
        final Image finalImg = bgImage;

        //This forces the background image to load underneath the cards
        JPanel mainPanel = new JPanel(cl) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalImg != null) {
                    g.drawImage(finalImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panels.put("mainPanel", mainPanel);

        this.setSize(screenWidth, screenHeight);
        this.setTitle("BlackJack");

        ImageIcon icon = new ImageIcon("resources/icon.png");
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                switch (JOptionPane.showConfirmDialog(DisplayFrame.this, "Do you want to save your stats?", "Exiting", JOptionPane.YES_NO_CANCEL_OPTION)) {
                    case JOptionPane.YES_OPTION:
                        game.savePlayerData();
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        });

        //Labels
        labels.put("titleLabel", new JLabel("JAVA SWING BLACKJACK"));
        labels.get("titleLabel").setFocusable(false);
        labels.get("titleLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("titleLabel").setFont(new Font("Arial", Font.BOLD, 72));
        labels.get("titleLabel").setForeground(Color.red);

        labels.put("selectLabel", new JLabel("Select your user: "));
        labels.get("selectLabel").setFocusable(false);
        labels.get("selectLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("selectLabel").setFont(new Font("Arial", Font.BOLD, 56));
        labels.get("selectLabel").setForeground(Color.black);

        labels.put("winLabel", new JLabel("VICTORY!"));
        labels.get("winLabel").setFocusable(false);
        labels.get("winLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("winLabel").setFont(new Font("Arial", Font.BOLD, 72));
        labels.get("winLabel").setForeground(Color.green);

        labels.put("drawLabel", new JLabel("DRAW!"));
        labels.get("drawLabel").setFocusable(false);
        labels.get("drawLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("drawLabel").setFont(new Font("Arial", Font.BOLD, 72));
        labels.get("drawLabel").setForeground(Color.blue);

        labels.put("loseLabel", new JLabel("LOSE!"));
        labels.get("loseLabel").setFocusable(false);
        labels.get("loseLabel").setAlignmentX(Component.CENTER_ALIGNMENT);
        labels.get("loseLabel").setFont(new Font("Arial", Font.BOLD, 72));
        labels.get("loseLabel").setForeground(Color.red);

        //Background image's label
        labels.put("backgroundLabel", new JLabel());
        labels.get("backgroundLabel").setIcon(new ImageIcon("resources/bg.jpg"));

        // Stat Labels
        labels.put("userNameLabel", new statLabel());
        labels.put("chipsLabel", new statLabel());
        labels.put("winStatLabel", new statLabel());
        labels.put("drawStatLabel", new statLabel());
        labels.put("loseStatLabel", new statLabel());

        //Buttons
        buttons.put("playButton", new JButton("PLAY"));
        buttons.get("playButton").setFocusable(false);
        buttons.get("playButton").addActionListener(e -> cl.show(panels.get("mainPanel"), "gameCard"));
        buttons.get("playButton").setFont(new Font("Arial", Font.BOLD, 72));

        buttons.put("registerButton", new JButton("REGISTER NEW USER"));
        buttons.get("registerButton").setFocusable(false);
        buttons.get("registerButton").addActionListener(e -> cl.show(panels.get("mainPanel"), "registerCard"));
        buttons.get("registerButton").setFont(new Font("Arial", Font.BOLD, 48));

        buttons.put("hitButton", new JButton("Hit"));
        buttons.get("hitButton").setFocusable(false);
        buttons.get("hitButton").addActionListener(e -> game.playerHit());

        buttons.put("standButton", new JButton("Stand"));
        buttons.get("standButton").setFocusable(false);
        buttons.get("standButton").addActionListener(e -> game.dealerAi());

        buttons.put("userSelectButton", new JButton("Player selection"));
        buttons.get("userSelectButton").setFocusable(false);
        buttons.get("userSelectButton").addActionListener(e -> cl.show(panels.get("mainPanel"), "playerCard"));

        buttons.put("reset1Button", new ResetButton());
        buttons.put("reset2Button", new ResetButton());
        buttons.put("reset3Button", new ResetButton());
        buttons.put("reset4Button", new ResetButton());

        //Panels
        panels.put("titlePanel", new JPanel());
        panels.get("titlePanel").add(labels.get("titleLabel"));
        panels.get("titlePanel").setOpaque(false);

        panels.put("selectionPanel", new JPanel());
        panels.get("selectionPanel").add(labels.get("selectLabel"));
        panels.get("selectionPanel").setOpaque(false);

        panels.put("playPanel", new JPanel());
        panels.get("playPanel").add(buttons.get("playButton"));
        panels.get("playPanel").add(buttons.get("registerButton"));
        panels.get("playPanel").setOpaque(false);

        panels.put("top1", new JPanel());
        panels.get("top1").setBackground(Color.red);
        panels.get("top1").setPreferredSize(new Dimension(1000, 360));

        panels.put("top2", new JPanel());
        panels.get("top2").setBackground(Color.blue);
        panels.get("top2").setPreferredSize(new Dimension(200, 300));
        panels.get("top2").add(buttons.get("hitButton"));
        panels.get("top2").add(buttons.get("standButton"));
        panels.get("top2").add(buttons.get("reset1Button"));
        panels.get("top2").add(labels.get("userNameLabel"));
        panels.get("top2").add(labels.get("chipsLabel"));
        panels.get("top2").add(labels.get("winStatLabel"));
        panels.get("top2").add(labels.get("drawStatLabel"));
        panels.get("top2").add(labels.get("loseStatLabel"));
        panels.get("top2").add(buttons.get("userSelectButton"));

        panels.put("bottom", new JPanel());
        panels.get("bottom").setBackground(Color.green);
        panels.get("bottom").setPreferredSize(new Dimension(1280, 360));

        panels.put("winPanel", new JPanel());
        panels.get("winPanel").setLayout(new BoxLayout(panels.get("winPanel"), BoxLayout.Y_AXIS));
        panels.get("winPanel").setOpaque(false);
        panels.get("winPanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("winPanel").add(labels.get("winLabel"));
        panels.get("winPanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("winPanel").add(buttons.get("reset2Button"));

        panels.put("drawPanel", new JPanel());
        panels.get("drawPanel").setLayout(new BoxLayout(panels.get("drawPanel"), BoxLayout.Y_AXIS));
        panels.get("drawPanel").setOpaque(false);
        panels.get("drawPanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("drawPanel").add(labels.get("drawLabel"));
        panels.get("drawPanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("drawPanel").add(buttons.get("reset3Button"));

        panels.put("losePanel", new JPanel());
        panels.get("losePanel").setLayout(new BoxLayout(panels.get("losePanel"), BoxLayout.Y_AXIS));
        panels.get("losePanel").setOpaque(false);
        panels.get("losePanel").setPreferredSize(new Dimension(1280, 720));
        panels.get("losePanel").add(labels.get("loseLabel"));
        panels.get("losePanel").add(Box.createRigidArea(new Dimension(0, 10)));
        panels.get("losePanel").add(buttons.get("reset4Button"));

        //Cards
        panels.put("playerCard", new JPanel());
        panels.get("playerCard").setOpaque(false);
        panels.get("playerCard").setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panels.get("playerCard").add(panels.get("titlePanel"), c);
        c.gridy = 1;
        panels.get("playerCard").add(panels.get("selectionPanel"), c);
        c.gridy = 2;
        panels.get("playerCard").add(panels.get("playPanel"), c);
        panels.get("playerCard").setBounds(0,0,1280,720);

        panels.put("registerCard", new JPanel());
        panels.get("registerCard").setOpaque(false);
        panels.get("registerCard").setBounds(0,0,1280,720);

        panels.put("gameCard", new JPanel());
        panels.get("gameCard").setOpaque(false);
        panels.get("gameCard").add(panels.get("top1"));
        panels.get("gameCard").add(panels.get("top2"));
        panels.get("gameCard").add(panels.get("bottom"));
        panels.get("gameCard").setBounds(0,0,1280,720);

        panels.put("winCard", new JPanel(new GridBagLayout()));
        panels.get("winCard").setOpaque(false);
        panels.get("winCard").add(panels.get("winPanel"));
        panels.get("winCard").setBounds(0,0,1280,720);

        panels.put("drawCard", new JPanel(new GridBagLayout()));
        panels.get("drawCard").setOpaque(false);
        panels.get("drawCard").add(panels.get("drawPanel"));
        panels.get("drawCard").setBounds(0,0,1280,720);

        panels.put("loseCard", new JPanel(new GridBagLayout()));
        panels.get("loseCard").setOpaque(false);
        panels.get("loseCard").add(panels.get("losePanel"));
        panels.get("loseCard").setBounds(0,0,1280,720);

        panels.get("mainPanel").add("playerCard", panels.get("playerCard"));
        panels.get("mainPanel").add("registerCard", panels.get("registerCard"));
        panels.get("mainPanel").add("gameCard", panels.get("gameCard"));
        panels.get("mainPanel").add("winCard", panels.get("winCard"));
        panels.get("mainPanel").add("drawCard", panels.get("drawCard"));
        panels.get("mainPanel").add("loseCard", panels.get("loseCard"));

        cl.show(panels.get("mainPanel"), "playerCard");

        add(panels.get("mainPanel"));
    }

    public void setupGameConnection(Game game) {
        DisplayFrame.game = game;

        //game connection needed for players selection
        JComboBox combo = new JComboBox(game.getPlayerNames());
        combo.setFocusable(false);
        combo.setFont(new Font("Arial", Font.PLAIN, 28));
        combo.addActionListener(e -> {
            game.setCurrentPlayer(combo.getSelectedItem().toString());
        });
        panels.get("selectionPanel").add(combo);

        JTextField field = new JTextField();
        field.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        field.setPreferredSize(new Dimension(250, 30));
        panels.get("registerCard").add(field);

        JButton registerConfirmButton = new JButton("register");
        registerConfirmButton.addActionListener(e -> {
            game.addPlayer(field.getText());
            combo.addItem(field.getText());
            SwingUtilities.updateComponentTreeUI(this);
            cl.show(panels.get("mainPanel"), "playerCard");
        });
        registerConfirmButton.setFont(new Font("Arial", Font.BOLD, 36));
        panels.get("registerCard").add(registerConfirmButton);
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

    public void updateNameLabel(String name) {
        String newString = "Username: " + name;
        labels.get("userNameLabel").setText(newString);
    }

    public void updateChips(int chips) {
        String newString = "Chips: " + chips;
        labels.get("chipsLabel").setText(newString);
    }

    public void updateStats(int wins, int draws, int loses) {
        String newString = "Wins: " + wins + "\n";
        labels.get("winStatLabel").setText(newString);
        newString = "Draws: " + draws + "\n";
        labels.get("drawStatLabel").setText(newString);
        newString = "Loses: " + loses + "\n";
        labels.get("loseStatLabel").setText(newString);
    }

}
