package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonbullet.IBulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {
    private long lastShotTime;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            LifePart lifePart = player.getPart(LifePart.class);


            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));

            if(gameData.getKeys().isDown(GameKeys.SPACE)){
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShotTime > 200) {
                    for (IBulletSPI bulletSPI : getIBulletSPIs()) {
                        world.addEntity(bulletSPI.createBullet(player, gameData));
                        lastShotTime = currentTime;
                    }
                }
            }
            
            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            lifePart.process(gameData,player);

            updateShape(player);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float size = 10;

        shapex[0] = (float) (x + Math.cos(radians) * 20);
        shapey[0] = (float) (y + Math.sin(radians) * 20);

        shapex[1] = (float) (x + Math.cos(radians + Math.PI/3) * size);
        shapey[1] = (float) (y + Math.sin(radians + Math.PI/3) * size);

        shapex[2] = (float) (x + Math.cos(radians + 2*Math.PI/3) * size);
        shapey[2] = (float) (y + Math.sin(radians + 2*Math.PI/3) * size);

        shapex[3] = (float) (x + Math.cos(radians + Math.PI) * size);
        shapey[3] = (float) (y + Math.sin(radians + Math.PI) * size);

        shapex[4] = (float) (x + Math.cos(radians + 4*Math.PI/3) * size);
        shapey[4] = (float) (y + Math.sin(radians + 4*Math.PI/3) * size);

        shapex[5] = (float) (x + Math.cos(radians + 5*Math.PI/3) * size);
        shapey[5] = (float) (y + Math.sin(radians + 5*Math.PI/3) * size);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    private Collection<? extends IBulletSPI> getIBulletSPIs() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
