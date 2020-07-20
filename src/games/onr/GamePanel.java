package games.onr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private static final int WIDTH = 500, HEIGTH = 500;
    private Thread thread; // have to learn

    private boolean isRunning;
    private boolean right = true, left = false, up = false, down = false;

    private SnakeBody bodyPart;
    private ArrayList<SnakeBody> snake;

    private Point point;
    private ArrayList<Point> points;
    private Random random;

    private int xCoor = 10 , yCoor = 10, size = 15;
    private int ticks = 0;

    public GamePanel() {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        addKeyListener(this);

        snake = new ArrayList<>();
        points = new ArrayList<>();
        random = new Random();

        start();
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this); // buradaki this unuttugum
        thread.start();
    }

    public void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (snake.size() == 0) {
            bodyPart = new SnakeBody(xCoor, yCoor, 10);
            snake.add(bodyPart);
        }
        ticks++;

        if (ticks > 550000) {
            if (right) xCoor++;
            if (left) xCoor--;
            if (up) yCoor--;
            if (down) yCoor++;

            ticks = 0;

            bodyPart = new SnakeBody(xCoor, yCoor, 10);
            snake.add(bodyPart);

            if (snake.size() > size) {
                snake.remove(0);
            }
        }

        if (points.size() == 0) {
            int xCoor = random.nextInt(49);
            int yCoor = random.nextInt(49);

            point = new Point(xCoor, yCoor, 10);
            points.add(point);
        }

        for (int i = 0; i < points.size() ; i++) {
            if (xCoor == points.get(i).getxCoor() && yCoor == points.get(i).getyCoor()){
                size++;
                points.remove(i);
            }
        }

        // game over on the snake body
        for (int i = 0; i < snake.size(); i++) {
            if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
                if (i != snake.size() - 1) {
                    System.out.println("GAME OVER");
                    stop();
                    i++;
                }
            }
        }

        // game over on the screen border
        if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49){
            System.out.println("game over");
            stop();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, WIDTH , HEIGTH); // for testen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 , WIDTH, HEIGTH);
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGTH); // have to learn
        }
        for (int i = 0; i < HEIGTH / 10; i++) {
            g.drawLine(0, i * 10, HEIGTH, i * 10);
        }

        for (int i = 0; i < snake.size() ; i++) {
            snake.get(i).draw(g);
        }

        for (int i = 0; i < points.size(); i++) {
            points.get(i).draw(g);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            tick();
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left){
            right = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_LEFT && !right){
            left = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_UP && !down){
            up = true;
            left = false;
            right = false;
        }
        if (key == KeyEvent.VK_DOWN && !up){
            down = true;
            right = false;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
