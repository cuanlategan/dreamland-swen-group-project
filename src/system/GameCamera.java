package system;

import control.GameController;
import control.Main;
import model.Actor;
import model.Position; //is compiler getting Position from Actor? or is it because of getters and setters????
import model.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuan on 9/19/15.
 */
public class GameCamera implements GameSystem{
    private GameController controller;
    private Tile[][] worldTiles;
    private List<Tile> viewTiles;

    public GameCamera(GameController controller){
        this.controller = controller;
        worldTiles = controller.getWorld();
        viewTiles = new ArrayList<>(/*(Main.C_HEIGHT/Main.TILE_SIZE)+Main.C_WIDTH/Main.TILE_SIZE*/);
    }

    @Override
    public void performSystem() {
        //get player location
        viewTiles.clear();
        final Actor playerActor = controller.getPlayerActor();
        int colMin;
        int colMax;
        int rowMin;
        int rowMax;

        //check edge case where player is less than half a screen away from edge of map
        //if so floor or ceiling the row/yPos or colum/xPos value
        if(playerActor.getPosition().getxPos() <= Main.C_WIDTH/2){
            colMin = 0;
            colMax = (Main.C_WIDTH/Main.TILE_SIZE/2); //is plus 1 needed?
        }else if (playerActor.getPosition().getxPos() >= (worldTiles[0].length*Main.TILE_SIZE)-Main.C_WIDTH/2){
            colMax = worldTiles[0].length;
            colMin = colMax - (Main.C_WIDTH/Main.TILE_SIZE/2); //is plus or minus 1 needed?)
        }else{
            colMin = playerActor.getPosition().getxPos()/Main.TILE_SIZE - (Main.C_WIDTH/Main.TILE_SIZE/2);
            colMax = playerActor.getPosition().getxPos()/Main.TILE_SIZE + (Main.C_WIDTH/Main.TILE_SIZE/2);
        }

        if(playerActor.getPosition().getyPos() <= Main.C_HEIGHT/2){
            rowMin = 0;
            rowMax = (Main.C_HEIGHT/Main.TILE_SIZE/2); //is plus 1 needed?
        }else if (playerActor.getPosition().getyPos() >= (worldTiles.length*Main.TILE_SIZE)-Main.C_HEIGHT/2){
            rowMax = worldTiles.length;
            rowMin = rowMax - (Main.C_HEIGHT/Main.TILE_SIZE/2); //is plus or minus 1 needed?)
        }else{
            rowMin = playerActor.getPosition().getyPos()/Main.TILE_SIZE - (Main.C_HEIGHT/Main.TILE_SIZE/2);
            rowMax = playerActor.getPosition().getyPos()/Main.TILE_SIZE + (Main.C_HEIGHT/Main.TILE_SIZE/2);
        }

        for(int i=colMin; i<colMax; i++){
            for(int j=rowMin; j<rowMax; j++){
                viewTiles.add(worldTiles[j][i]);
            }
        }

    }

    public List<Actor> getActorList() {
        final Actor playerActor = controller.getPlayerActor();
        final int HALF_C_WIDTH = Main.C_WIDTH/2;
        final int HALF_C_HEIGHT = Main.C_HEIGHT/2;
        List<Actor> worldActors = controller.getActors();
        List<Actor> viewActors = new ArrayList<>();
        for(Actor actor: worldActors){
            if(actor.getPosition().getxPos() < (playerActor.getPosition().getxPos() - HALF_C_WIDTH)){continue;}
            else if(actor.getPosition().getxPos() > (playerActor.getPosition().getxPos() + HALF_C_WIDTH)){continue;}
            else if(actor.getPosition().getyPos() < (playerActor.getPosition().getyPos() - HALF_C_HEIGHT)){continue;}
            else if(actor.getPosition().getyPos() > (playerActor.getPosition().getyPos() + HALF_C_WIDTH)){continue;}
            viewActors.add(actor);
        }
        return viewActors;
    }

    public List<Tile> getTileList() {
        return viewTiles;
    }

    public Actor getPlayerActor(){
        return controller.getPlayerActor();
    }
}