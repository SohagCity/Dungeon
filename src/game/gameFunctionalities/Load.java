package game.gameFunctionalities;

import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Reads a file and loads its information in tha game.
 */
public class Load {

    private String fileName;
    private Game game;
    private GameLevel world;
    /**
     * Initialise a new Load
     *
     * @param fileName the name of the save file
     */
    public Load(String fileName, Game game) {
        this.fileName = fileName;
        this.game = game;
    }

    /**
     * Reads the level from the file and loads it in the game.
     * Reads the position of the player, its heath and score and applies the changes.
     * Removes all enemies.
     * Reads the type of enemy and its position and then adds it to the world.
     */
    public void readSave() throws GameLoadException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            //reads the file
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            if(line!=null) {
                //divides the line into tokens
                String[] tokens = line.split(",");
                int level = 1;
                float posX = 0;
                float posY = 0;
                int health = 400;
                int kills = 0;
                try {
                    level = Integer.parseInt(tokens[0]);
                    posX = Float.parseFloat(tokens[1]);
                    posY = Float.parseFloat(tokens[2]);
                    health = Integer.parseInt(tokens[3]);
                    kills = Integer.parseInt(tokens[4]);
                } catch (NumberFormatException e){
                    game.goNextLevel(1);
                    throw new GameLoadException("error loading player info");
                }
                game.goNextLevel(level);
                world = game.getWorld();
                game.destroy();
                game.getPlayer().setPosition(new Vec2(posX, posY));
                game.getPlayer().setKills(kills);
                game.getPlayer().setHealth(health);

                //reads the contents of the rest of file
                for (int i = 5; i < tokens.length; i+=3) {
                    float x=0;
                    float y=0;
                    try{
                        if((i+1)<tokens.length) {
                            x = Float.parseFloat(tokens[i + 1]);
                            y = Float.parseFloat(tokens[i + 2]);
                        }
                    } catch (NumberFormatException e){
                        game.goNextLevel(1);
                        throw new GameLoadException("Enemy's coordinates must be number");
                    }
                    //checks what type of monster was saved and adds it to the gama at the saved position
                    if (tokens[i].equals("monster")) {
                        world.addMonster(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("monsterTier2")) {
                        world.addMonsterTier2(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("giantMonster")) {
                        world.addGiantMonster(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("redMonster")) {
                        world.addRedMonster(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("rock")) {
                        world.addRock(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("turret")) {
                        world.addTurret(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("miniTurret")) {
                        world.addMiniTurret(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("alien")) {
                        world.addAlien(new Vec2(x, y));
                    }
                    else if (tokens[i].equals("boss")) {
                        int heath=800;
                        try{
                            health=Integer.parseInt(tokens[i+3]);
                        }catch (NumberFormatException e){
                            throw new GameLoadException("boss health must be a number");
                        }
                        i++;
                        world.addBoss(new Vec2(x, y));
                        world.getBoss().setHealth(health);
                        game.updateBoss();
                    }
                    else if (tokens[i].equals("spawner")) {
                        boolean atBoss=false;
                        atBoss=Boolean.parseBoolean(tokens[i + 3]);
                        world.addSpawner(new Vec2(x, y), Boolean.parseBoolean(tokens[i + 3]));
                        i++;
                    }
                }
            }
            System.out.println("...done.");
        } catch(FileNotFoundException e) {
            throw new GameLoadException("file does not exist");
        } catch (IOException e){
            throw new GameLoadException("problem reading file");
        }
    }
}