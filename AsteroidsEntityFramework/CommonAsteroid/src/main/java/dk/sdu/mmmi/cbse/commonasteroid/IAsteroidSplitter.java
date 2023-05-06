package dk.sdu.mmmi.cbse.commonasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public interface IAsteroidSplitter {

    /**
     * this method is used to create a split asteroid
     * pre-condition: the entity and world must be initialized
     * post-condition: a split asteroid will be created
     * @param e the entity to be split
     * @param w the world containing the entities
     */
    void createSplitAsteroid(Entity e, World w);
}
