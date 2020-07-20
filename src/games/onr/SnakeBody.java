package games.onr;

import java.awt.*;

public class SnakeBody {

    private int xCoor, yCoor, width, height;

    public SnakeBody(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.width = tileSize;
        this.height = tileSize;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    public void tick() {

    }
}
