package image;

import constants.Constants;

import javax.swing.*;

public class ImageCreator {
    public static ImageIcon createImage (Image image) {
        ImageIcon imageIcon = null;

        switch (image) {
            case SpaceShip:
                imageIcon = new ImageIcon(Constants.SpaceShipUrl);
                break;
            case Laser:
                imageIcon = new ImageIcon(Constants.LaserUrl);
                break;
            case Background:
                imageIcon = new ImageIcon(Constants.BackgroundUrl);
                break;
            case EnemyShip:
                imageIcon = new ImageIcon(Constants.EnemyShipUrl);
                break;
            case Bomb:
                imageIcon = new ImageIcon(Constants.BombUrl);
                break;
            default:
                return null;
        }
        return imageIcon;
    }
}
