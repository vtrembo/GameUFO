package constants;

public class Constants {
    private Constants() { }
    public static final String title = "UFO :: GUI";

    public static final int boarderWidth = 1024;
    public static final int boarderHeight = 768;

    public static final int SpaceShipWidth = 100;
    public static final int SpaceShipHeight = 100;

    public static final int laserSpeed = 4;

    public static final int enemyShipInitX = 240;
    public static final int enemyShipInitY = 20;
    public static final int enemyShipRow = 3;
    public static final int enemyShipColumn = 8;
    public static final int enemyShipPadding = 50;
    public static final int goDown = 10;
    public static final int bombHeight = 19;

    public static final double bombDropChance = 0.0005;

    public static final int gameSpeed = 5;

    public static final String win = "      WIN!";
    public static final String game_over = "GAME OVER!";

    public static final String BombUrl = "gameImages/bomb.png";
    public static final String LaserUrl = "gameImages/laser.png";
    public static final String SpaceShipUrl = "gameImages/spaceShip.png";
    public static final String EnemyShipUrl = "gameImages/enemyShip.png";
    public static final String BackgroundUrl = "gameImages/spaceBackground.jpg";
}
