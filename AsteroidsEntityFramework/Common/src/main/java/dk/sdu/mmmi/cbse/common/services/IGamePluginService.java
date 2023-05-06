package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * this method is used to start the game
     * pre-condition: the gameData and world must be initialized
     * post-condition: the game will be started
     * @param gameData the current game state
     * @param world the world containing the entities
     */
    void start(GameData gameData, World world);
/**
     * this method is used to stop the game
     * pre-condition: the gameData and world must be initialized
     * post-condition: the game will be stopped
     * @param gameData the current game state
     * @param world the world containing the entities
     */
    void stop(GameData gameData, World world);
}
