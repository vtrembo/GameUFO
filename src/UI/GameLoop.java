package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {
    private GamePanel gamePanel;
    public GameLoop (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.gamePanel.update();
        this.gamePanel.repaint();
    }
}
