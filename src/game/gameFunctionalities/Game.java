package game.gameFunctionalities;

import city.cs.engine.*;
import game.characters.Player;
import game.controls.*;
import game.levels.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controls the game. Which level is next, creates the window, mouse and keyboard controls etc.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel world;
    /** A graphical display of the world (a specialised JPanel). */
    private MyView view;
    private int level;

    private WheelListener wheelListener;
    private Controller controller;
    private MouseHandler mouseHandler;
    private MouseMovement mouseMovement;

    private SoundClip gameMusic;
    private ControlPanel controlPanel;
    /** Initialise a new Game. */
    public Game() {

        // make the world
        level = 1;
        world = new Level1();
        world.populate(this);

        music("resources/Celestial.wav");


        // make a view
        view = new MyView(world,getPlayer(), 500, 500);
        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);

        // display the view in a frame
        final JFrame frame = new JFrame("The Dungeon");


        //background color
        view.setBackground(Color.black);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.SOUTH);

        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);

        // size the game window to fit the world view
        frame.pack();
        // make the window visible.
        frame.setVisible(true);

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);

        //key bindings
        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(frame));


        // add some mouse actions
        mouseHandler=new MouseHandler(view,world);
        view.addMouseListener(mouseHandler);
        mouseMovement=new MouseMovement(world.getPlayer(),view);
        view.addMouseMotionListener(mouseMovement);
        wheelListener=new WheelListener(world.getPlayer());
        view.addMouseWheelListener(wheelListener);

        // start!
        world.start();
    }

    /** The player in the current level. */
    public Player getPlayer() {
        return world.getPlayer();
    }

    public GameLevel getWorld(){return world;}

    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /**
     * Advance to the next level of the game.
     * @param level Which level it will progress to
     */
    public void goNextLevel(int level) {
        world.stop();
        this.level=level;
        Player oldPlayer= world.getPlayer();
        //removes the boss if there is one. (removes its heath bar)
        view.removeBoss();
        if (level==1){
            // get a new world
            world = new Level1();
            view.setBackground( new ImageIcon("resources/ground.png").getImage(),45,75,411,350);
            music("resources/Celestial.wav");
            nextLevelPrep(oldPlayer);
        } else if (level==2){
            // get a new world
            world = new Level2();
            view.setBackground( new ImageIcon("resources/level2.png").getImage(),0,0,500,500);
            music("resources/Red Carpet Wooden Floor.wav");
            nextLevelPrep(oldPlayer);
        } else if (level == 3) {
            // get a new world
            world = new Level3();
            view.setBackground( new ImageIcon("resources/level3.png").getImage(),0,0,500,500);
            music("resources/Windless Slopes.wav");
            nextLevelPrep(oldPlayer);
        } else if (level==4) {
            // get a new world
            world = new Level4();
            view.setBackground( new ImageIcon("resources/level4.png").getImage(),0,0,500,500);
            music("resources/Nocturnal Mysteries.wav");
            nextLevelPrep(oldPlayer);
        } else if (level==5){
            // get a new world
            world = new Level5();
            view.setBackground( new ImageIcon("resources/level5.png").getImage(),0,0,500,500);
            music("resources/Celestial.wav");
            nextLevelPrep(oldPlayer);
        } else if (level==6) {
            // get a new world
            world = new Level6();
            view.setBackground(new ImageIcon("resources/levelBoss.png").getImage(), 0, 0, 500, 500);
            music("resources/Foggy Woods.wav");
            nextLevelPrep(oldPlayer);
        } else if (level==7) {
            world.stop();
            music("resources/win.wav");
            view.setWin(true);
        }
    }

    /**
     * Preparations before going to a new levlel
     * @param oldPlayer Previous player.
     */
    public void nextLevelPrep(Player oldPlayer){
        world.populate(this);
        // switch the keyboard control to the new player
        controller.setPlayer(world.getPlayer());
        mouseHandler.setPlayer(world);
        mouseMovement.setPlayer(world.getPlayer());
        wheelListener.setPlayer(world.getPlayer());
        getPlayer().setSwitchGun(oldPlayer.getSwitchGun()-1);
        getPlayer().switchGuns();
        getPlayer().setHealth(oldPlayer.getHealth());
        getPlayer().setKills(oldPlayer.getKills());
        view.setBody(getPlayer());
        view.addBoss(world.getBoss());

        // show the new world in the view
        view.setWorld(world);
        world.start();
    }

    /** Run */
    public static void main(String[] args) {
                new Game();
    }

    /**
     * Current level.
     * @return Level numeber.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Loads and play background music.
     * @param s path
     */
    public void music(String s){
            try {
                if (gameMusic != null) {
                    gameMusic.stop();
                }
                gameMusic = new SoundClip(s);   // Open an audio input stream
                gameMusic.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                controlPanel.updateError("error loading sound");
            }
    }

    /**
     * Destroys all dynamic bodies in the current level.
     */
    public void destroy(){
        ArrayList<DynamicBody> bodies=new ArrayList<>(world.getDynamicBodies());
        for(int i=0; i<bodies.size();i++){
            if(!(bodies.get(i) instanceof Player))
            bodies.get(i).destroy();
        }
    }

    /**
     * Updates the boss' health bar.
     */
    public void updateBoss(){
        view.addBoss(world.getBoss());
    }

}
