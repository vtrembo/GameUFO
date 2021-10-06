package Model;

import image.Image;
import image.ImageCreator;

import javax.swing.*;

public class EnemyShip extends Sprite {
    private boolean visible = true;
    public EnemyShip (int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }
    private void init(){
        ImageIcon imageIcon = ImageCreator.createImage(Image.EnemyShip);
        setImage(imageIcon.getImage());
    }
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void move(int direction){
        this.x += direction;
    }

    @Override
    public void move() {

    }
}
