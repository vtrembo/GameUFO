package Model;

import constants.Constants;
import image.Image;
import image.ImageCreator;
import javax.swing.*;

public class Laser extends Sprite {
    public Laser () {

    }
    public Laser(int x, int y){
        this.x = x;
        this.y = y;
        init();
    }
    private void init() {
        ImageIcon imageIcon = ImageCreator.createImage(Image.Laser);
        setImage(imageIcon.getImage());

        setX(x+ Constants.SpaceShipWidth/2);
        setY(y);
    }
    @Override
    public void move() {
        this.y -= Constants.laserSpeed;

        if(this.y < 0 ) {
            this.die();
        }
    }
}
