package UI;

import constants.Constants;
import image.Image;
import image.ImageCreator;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() { initWindow(); }

    private void initWindow() {
        add(new GamePanel());
        setTitle(Constants.title);
        setIconImage(ImageCreator.createImage(Image.SpaceShip).getImage());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) { new GameFrame(); }
}
