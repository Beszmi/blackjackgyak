package org.example;

import java.util.Random;

public class Main {
    static void main() {
        /*test_thread test1 = new test_thread();
        test1.start();
        test2_thread test2 = new test2_thread();
        test2.start();*/
        SpecialFrame specialFrame = new SpecialFrame();
        Game game = new Game(specialFrame);
        specialFrame.setupGameConnection(game);

        game.setup();
        specialFrame.setVisible(true);
    }
}
