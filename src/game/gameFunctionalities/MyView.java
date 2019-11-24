package game.gameFunctionalities;

import java.awt.*;
import javax.swing.ImageIcon;
import city.cs.engine.*;
import game.characters.Boss;
import game.characters.Player;

/**
 * extended view
 */
public class MyView extends UserView {
    private Player player;

    private int x=45;
    private int y=75;
    private int width=411;
    private int height=350;

    private static final Image healthContainerBoss= new ImageIcon("resources/healthContainerBoss.png").getImage();
    private Image background = new ImageIcon("resources/ground.png").getImage();
    private static final Image healthContainer = new ImageIcon("resources/healthContainer.png").getImage();
    private Image health= new ImageIcon("resources/health.png").getImage();
    private Image healthBoss= new ImageIcon("resources/healthBoss.png").getImage();
    private static Image key= new ImageIcon("resources/key.png").getImage();
    private static Image gameOver= new ImageIcon("resources/gameOver.png").getImage();
    private static Image winImage= new ImageIcon("resources/win.jpg").getImage();

    private Boss boss;

    private boolean win=false;

    /**
     * Initialise a new View.
     * @param world The World.
     * @param player The player.
     * @param width Width of the image.
     * @param height Height of the image.
     */
    public MyView(World world, Player player, int width, int height) {
        super(world, width, height);
        this.player = player;
    }

    /**
     *Draws the Background of the level.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, x, y, width,height,this);
    }

    /**
     *Draws GUI (player's health, score and key count).
     * Also boss' health bar when there is a boss.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        //uncomment this for real health
//        g.drawImage(health, 10, 10,  player.getHealth()/2,20,this);

        //this health is to make the game easier
        g.drawImage(health, 10, 10,  player.getHealth()/4,20,this);
        g.drawImage(healthContainer, 10, 10,  200,20,this);
        for (int i=0; i< player.getKeys(); i++) {
            g.drawImage(key, 400 + i * 30, 470, 25, 20, this);
        }
        g.setFont(new Font("TimesRoman",Font.BOLD,15));
        g.setColor(Color.WHITE);
        g.drawString("KILLS:  " + player.getKills() ,300,20);
        if (boss!=null){
            g.drawImage(healthContainerBoss, 0, 380,  460,200,this);
            g.drawImage(healthBoss, 30, 470,  boss.getHealth()/2,25,this);
        }
        if(player.getHealth()<=0){
            g.drawImage(gameOver, 0, 0,  500,500,this);
        }
        if(win){
            g.drawImage(winImage, 0, 0,  500,500,this);
        }
    }

    /**
     * Sets the background for the level.
     * @param image Image of the background.
     * @param x Position x.
     * @param y Position y.
     * @param width Width of the image.
     * @param height Height of the image.
     */
    public void setBackground(Image image,int x,int y,int width,int height){
        background=image;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    /**
     * return if the player reached the end of the game.
     * @return Player finished the game?
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Sets if the player finished the game
     * @param win Player won.
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    public void setBody(Player player) {
        this.player = player;
    }

    public void addBoss(Boss boss){
        this.boss=boss;
    }
    public void removeBoss(){
        boss=null;
    }
}
