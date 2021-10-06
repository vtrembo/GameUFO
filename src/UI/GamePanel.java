package UI;

import Model.Bomb;
import Model.EnemyShip;
import Model.Laser;
import Model.SpaceShip;
import constants.Constants;
import image.Image;
import image.ImageCreator;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GamePanel extends JPanel {
    private ImageIcon backgroundImage;
    private SpaceShip spaceShip;
    private Laser laser;
    private boolean inGame = true;
    private int direction = -1;
    private List<EnemyShip> enemyShips;
    private List<Bomb> bombs;
    private Random generator;
    private String message;
    private int deaths;
    private int bestScoreSecInt;
    private int score;
    private int healthPoints = 3;
    private long startTime;

    public GamePanel() {
        initVariables();
        initWindow();
        initGame();
    }
    private void initVariables() {
        this.generator = new Random();
        this.bombs = new ArrayList<>();
        this.enemyShips = new ArrayList<>();
        this.backgroundImage = ImageCreator.createImage(Image.Background);
        this.laser = new Laser();
        this.spaceShip = new SpaceShip();
        startTime = System.currentTimeMillis();
    }
    private void initWindow() {
        addKeyListener(new GameEventListener(this));
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.boarderWidth, Constants.boarderHeight));
    }
    private void initGame() {
        loadScoreFromFile();
        for (int i = 0; i < Constants.enemyShipRow; i++) {
            for (int j = 0; j < Constants.enemyShipColumn; j++) {
                EnemyShip enemyShip = new EnemyShip(Constants.enemyShipInitX + 70 * j, Constants.enemyShipInitY + 70 * i);
                this.enemyShips.add(enemyShip);
            }
        }
        new Thread(() -> {
            while (true) {
                if (!inGame) break;
                update();
                repaint();
                try {
                    Thread.sleep(Constants.gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void drawPlayer(Graphics g) {
        g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
    }

    private void drawLaser(Graphics g) {
        if (!laser.isDead()) {
            g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
        }
    }

    private void drawBombs(Graphics g) {
        for (Bomb bomb : this.bombs)
            if (!bomb.isDead())
                g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
    }

    private void drawAliens(Graphics g) {
        for (EnemyShip enemyShip : this.enemyShips)
            if (enemyShip.isVisible())
                g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
    }

    private void drawGameOver(Graphics g) {
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
        Font font = new Font("Arial", Font.BOLD, 50);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message, (Constants.boarderWidth / 2) - 150, Constants.boarderHeight / 2 - 100);
    }

    private void drawScore(Graphics g) {
        if (!inGame) return;
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.GRAY);
        g.drawString("Score: " + score, 20, 30);
        if(bestScoreSecInt == 0) {
            g.drawString("Best score: " + score, 20, 60);
        } else {
            g.drawString("Best score: " + bestScoreSecInt, 20, 60);
        }
        g.drawString("HP: " + healthPoints, Constants.boarderWidth - 100, 30);
    }

    private void loadScoreFromFile() {
        int scoreInt, bestScoreInt;
        try {
            Scanner scanner = new Scanner(new FileInputStream("score.txt"));
            if (!scanner.hasNextInt()) return;
            bestScoreInt = scanner.nextInt();
            while (scanner.hasNextInt()) {
                scoreInt = scanner.nextInt();
                if (bestScoreInt > scoreInt) bestScoreInt = scoreInt;
            }
            bestScoreSecInt = bestScoreInt;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("score.txt", true);
            PrintWriter pw = new PrintWriter((fos));
            pw.println(score);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
        if (inGame) {
            drawPlayer(g);
            drawLaser(g);
            drawAliens(g);
            drawBombs(g);
            drawScore(g);
        } else {
            drawGameOver(g);
        }
    }

    public void update() {
        if (inGame) score = (int)(System.currentTimeMillis() - startTime) / 1000;
        if (spaceShip.isDead()) {
            inGame = false;
            message = Constants.game_over;
        }
        if (deaths == Constants.enemyShipRow * Constants.enemyShipColumn) {
            inGame = false;
            message = Constants.win;
            saveToFile();
        }
        this.spaceShip.move();

        if (!laser.isDead()) {
            int shotX = laser.getX();
            int shotY = laser.getY();

            for (EnemyShip alien : this.enemyShips) {
                int alienX = alien.getX();
                int alienY = alien.getY();
                if (!alien.isVisible()) continue;
                if (shotX >= (alienX) && shotX <= (alienX + Constants.SpaceShipWidth) && shotY >= alienY && shotY <= (alienY + Constants.SpaceShipHeight)) {
                    alien.setVisible(false);
                    laser.die();
                    deaths++;
                }
            }
            this.laser.move();
        }
        for (EnemyShip enemyShip : this.enemyShips) {
            if (enemyShip.getX() >= Constants.boarderWidth - 2 * Constants.enemyShipPadding && direction != -1 || enemyShip.getX() <= Constants.enemyShipPadding && direction != 1) {
                direction *= -1;
                Iterator<EnemyShip> ufoIterator = enemyShips.iterator();
                while (ufoIterator.hasNext()) {
                    EnemyShip ufo = ufoIterator.next();
                    ufo.setY(ufo.getY() + Constants.goDown);
                }
            }

            if (enemyShip.isVisible()) {

                if (enemyShip.getY() > Constants.boarderHeight - 100 - Constants.SpaceShipHeight) {
                    spaceShip.die();
                }
                enemyShip.move(direction);
            }
        }
        for (EnemyShip enemyShip : this.enemyShips) {
            if (enemyShip.isVisible() && generator.nextDouble() < Constants.bombDropChance) {
                Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
                this.bombs.add(bomb);
            }
        }
        for (Bomb bomb : this.bombs) {

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int spaceShipX = spaceShip.getX();
            int spaceShipY = spaceShip.getY();

            if (!bomb.isDead() && !spaceShip.isDead()) {
                if (bombX >= spaceShipX && bombX <= (spaceShipX + Constants.SpaceShipWidth) && bombY >= spaceShipY && bombY <= (spaceShipY + Constants.SpaceShipHeight)) {
                    bomb.die();
                    healthPoints--;
                    if (healthPoints == 0) spaceShip.die();

                }
            }
            if (!bomb.isDead()) {
                bomb.move();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        this.spaceShip.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        this.spaceShip.keyPressed(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            int laserX = this.spaceShip.getX();
            int laserY = this.spaceShip.getY();

            if (inGame && laser.isDead()) {
                laser = new Laser(laserX, laserY);
            }
        }
    }
}
