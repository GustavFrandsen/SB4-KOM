package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetector : world.getEntities()) {
                LifePart entityLife = entity.getPart(LifePart.class);

                if (entity.getID().equals(collisionDetector.getID())) {
                    continue;
                }

                if (this.collides(entity,collisionDetector)){
                    if(entityLife.getLife() > 0){
                        entityLife.setLife(entityLife.getLife()-1);
                        entityLife.setIsHit(true);

                    }
                    if(entityLife.getLife() <= 0){
                        world.removeEntity(entity);
                    }

                }
            }
        }

    }

    protected boolean collides(Entity entity1, Entity entity2) {
        PositionPart entity1Pos = entity1.getPart(PositionPart.class);
        PositionPart entity2Pos = entity2.getPart(PositionPart.class);
        float dx = entity1Pos.getX() - entity2Pos.getX();
        float dy = entity1Pos.getY() - entity2Pos.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());

    }
}
