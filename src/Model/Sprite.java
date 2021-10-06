package Model;

import java.awt.*;

public abstract class Sprite {
    private Image image;
    private boolean dead;

    protected int x;
    protected int y;
    protected int dx;

    public abstract void move();

    public Sprite() {
        this.dead = false;
    }

    public void die() {
        this.dead = true;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public boolean isDead() {
        return this.dead;
    }
}
