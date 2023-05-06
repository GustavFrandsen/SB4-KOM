package dk.sdu.mmmi.cbse.commonbullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author corfixen
 */
public interface IBulletSPI {
    /**
     * this method is used to create a bullet
     * pre-condition: the player and gameData must be initialized
     * post-condition: a bullet will be created
     * @param player the player entity
     * @param gameData the current game state
     * @return the bullet entity
     */
    Entity createBullet(Entity player, GameData gameData);
}
