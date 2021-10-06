package UI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEventListener extends KeyAdapter {

    private GamePanel keyboard;
    public GameEventListener (GamePanel keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyboard.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyboard.keyPressed(e);
    }
}
