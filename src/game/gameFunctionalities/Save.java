package game.gameFunctionalities;

import city.cs.engine.DynamicBody;
import game.characters.*;
import game.otherObjects.Rock;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes into a file the game status and info.
 * e.g. player health, position, score, monsters' positions.
 */
public class Save {

    private String fileName;
    private Game game;

    public Save(String fileName,Game game) {
        this.fileName = fileName;
        this.game=game;
    }

    public void writeSave() throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(game.getLevel() + "," + game.getPlayer().getPosition().x + "," + game.getPlayer().getPosition().y +  "," + game.getPlayer().getHealth() + "," + game.getPlayer().getKills());
            ArrayList<DynamicBody> bodies = new ArrayList<>(game.getWorld().getDynamicBodies());
            //look at all dynamic bodies in the world
            //if any of these bodies are enemies then save their name and position
            for (DynamicBody body:bodies){
                if(body instanceof Monster){
                    writer.write("," + "monster" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof MonsterTier2){
                    writer.write("," + "monsterTier2" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof Rock){
                    writer.write("," + "rock" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else  if(body instanceof RedMonster){
                    writer.write("," + "redMonster" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof GiantMonster){
                    writer.write("," + "giantMonster" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof Turret){
                    writer.write("," + "turret" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof MiniTurret){
                    writer.write("," + "miniTurret" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
                else if(body instanceof Spawner){
                    writer.write("," + "spawner" + "," + body.getPosition().x + "," + body.getPosition().y + "," + ((Spawner) body).isAtBoss());
                }
                else if(body instanceof Boss){
                    writer.write("," + "boss" + "," + body.getPosition().x + "," + body.getPosition().y + "," + ((Boss) body).getHealth());
                } else if(body instanceof Alien){
                    writer.write("," + "alien" + "," + body.getPosition().x + "," + body.getPosition().y);
                }
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}