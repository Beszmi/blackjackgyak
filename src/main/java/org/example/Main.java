package org.example;

public class Main {
    static void main() {
        DisplayFrame displayFrame = new DisplayFrame();
        Game game = new Game(displayFrame);

        displayFrame.setupGameConnection(game);
        displayFrame.setVisible(true);
    }
}
