package dk.sdu.mmmi.cbse.commonbullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author corfixen
 */
public interface IBulletSPI {
    Entity createBullet(Entity player, GameData gameData);
}
