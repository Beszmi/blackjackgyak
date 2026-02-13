package org.example;

public class Main {
    static void main() {
        DisplayFrame displayFrame = new DisplayFrame();
        new Game(displayFrame);

        displayFrame.setVisible(true);
    }
}
