package Model;

import constants.Constants;
import image.Image;
import image.ImageCreator;

import javax.swing.*;


public class Bomb extends Sprite {
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
        init();
    }
    private void init () {
         ImageIcon imageIcon = ImageCreator.createImage(Image.Bomb);
         setImage(imageIcon.getImage());
    }
    @Override
    public void move() {
        this.y++;
        if (y >= Constants.boarderHeight - Constants.bombHeight) {
            die();
        }
    }
}
