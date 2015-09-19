package factory;

import control.GameKeyListener;
import model.*;


public class ServerModeFactory extends AbstractFactory {
    @Override
    public Tile[][] createWorldTiles() {
        return new Tile[0][];
    }

    @Override
    public Player createPlayerActor(GameKeyListener keyListener) {
        return null;
    }

    @Override
    public Coin createCoin() {
        return null;
    }

    @Override
    public Inventory createInventory() {
        return null;
    }

    @Override
    public Collectable createCollectable() {
        return null;
    }


}