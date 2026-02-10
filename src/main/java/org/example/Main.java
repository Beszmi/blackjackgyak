package org.example;

public class Main {
    static void main() {
        SpecialFrame specialFrame = new SpecialFrame();
        Game game = new Game(specialFrame);

        specialFrame.setupGameConnection(game);
        specialFrame.setVisible(true);
    }
}
