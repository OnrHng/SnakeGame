package games.onr;

import javax.swing.*;

public class Main {

    public Main() {

        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKE");

        frame.pack(); //ask it
        frame.setLocationRelativeTo(null); // to appear the frame in the middle
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
