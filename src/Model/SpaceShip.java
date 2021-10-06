package Model;

import constants.Constants;
import image.Image;
import image.ImageCreator;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class SpaceShip extends Sprite {


   public SpaceShip () {
       init();
   }
   private void init () {
       ImageIcon imageIcon = ImageCreator.createImage(Image.SpaceShip);
       setImage(imageIcon.getImage());

       int start_x = Constants.boarderWidth/2-Constants.SpaceShipWidth/2;
       int start_y = Constants.boarderHeight-150;

       setX(start_x);
       setY(start_y);
   }
    @Override
    public void move () {
        x += dx;

        if (x < Constants.SpaceShipWidth/8) {
            x = Constants.SpaceShipWidth/8;
        }
        if (x >= Constants.boarderWidth - Constants.SpaceShipWidth - Constants.SpaceShipWidth/8) {
            x = Constants.boarderWidth - Constants.SpaceShipWidth - Constants.SpaceShipWidth/8;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 2;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
