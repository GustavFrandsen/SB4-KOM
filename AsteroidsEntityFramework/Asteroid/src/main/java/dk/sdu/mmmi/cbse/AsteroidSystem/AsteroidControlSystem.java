package dk.sdu.mmmi.cbse.AsteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {
    private static final AsteroidSplittet asteroidSplittet = new AsteroidSplittet();
    @Override
    public void process(GameData gameData, World world) {
    for (Entity asteroid : world.getEntities(Asteroid.class)){
        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        LifePart lifePart = asteroid.getPart(LifePart.class);
        int numPoints = 12;
        if (lifePart.getLife() == 1) {
            numPoints = 6;
        }
        Random random = new Random();
        movingPart.setUp(true);
        movingPart.setLeft(random.nextBoolean());
        movingPart.setRight(random.nextBoolean());

        movingPart.process(gameData, asteroid);
        positionPart.process(gameData, asteroid);
        lifePart.process(gameData,asteroid);

        if(lifePart.isIsHit()){
          asteroidSplittet.createSplitAsteroid(asteroid,world);
        }
        updateShape(asteroid,numPoints);

    }
    }

    private void updateShape (Entity entity, int numpoints){
        float[] shapex = new float[numpoints];
        float[] shapey = new float[numpoints];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float radius = entity.getRadius();

        float angle = 0;

        for (int i = 0; i < numpoints; i++) {
            shapex[i] = x + (float) Math.cos(angle + radians) * radius;
            shapey[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numpoints;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
