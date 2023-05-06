package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * this method is used to process the entities in the world
     * pre-condition: the world and gameData must be initialized
     * post-condition: the entities in the world will be processed
     * @param gameData the current game state
     * @param world the world containing the entities
     *
     */

    void process(GameData gameData, World world);
}
