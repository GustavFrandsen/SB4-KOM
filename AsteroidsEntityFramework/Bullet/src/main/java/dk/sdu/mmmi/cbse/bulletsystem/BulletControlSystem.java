package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.IBulletSPI;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;


import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletControlSystem implements IEntityProcessingService, IBulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)){
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            if(timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            movingPart.setUp(true);

            movingPart.process(gameData,bullet);
            positionPart.process(gameData,bullet);
            timerPart.process(gameData,bullet);

            setShape(bullet);


        }
    }



    private void setShape(Entity entity){
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float radius = 3;

        for (int i = 0; i < 6; i++) {
            shapex[i] = (float) (x + radius * Math.cos(radians + i * Math.PI / 3));
            shapey[i] = (float) (y + radius * Math.sin(radians + i * Math.PI / 3));
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }



    @Override
    public Entity createBullet(Entity player, GameData gameData) {
        PositionPart playerPos = player.getPart(PositionPart.class);
        float posX = playerPos.getX();
        float posY = playerPos.getY();
        float radians = playerPos.getRadians();
        float maxSpeed = 450;

        Entity bullet = new Bullet();
        bullet.setRadius(3);

        float bx = (float) cos(radians) * player.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * player.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx+posX,by+posY,radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0,60000,maxSpeed,5));
        bullet.add(new TimerPart(2));

        return bullet;
    }
}
