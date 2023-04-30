package dk.sdu.mmmi.cbse.AsteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;



import java.util.ArrayList;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private float xAxis;

    private ArrayList<Entity> asteroids;
    private Entity asteroid;

    public AsteroidPlugin(){
    }
    @Override
    public void start(GameData gameData, World world) {
        xAxis = 0;
        asteroids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Entity asteroid = createAsteroid(gameData);
            asteroids.add(asteroid);
            xAxis += 100;
        }
        for (Entity asteroids : asteroids){
            world.addEntity(asteroids);
        }

    }

    private Entity createAsteroid(GameData gameData){
        Random random = new Random();
        float deacceleration = 10;
        float acceleration = 150;
        float maxSpeed = 150;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() - xAxis;
        float y = gameData.getDisplayHeight() - random.nextFloat();
        float radians = 3.1415f / 2;
    Entity asteroid = new Asteroid();
    asteroid.setRadius(20);
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(3));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (int i = 0; i < 10; i++) {
            world.removeEntity(asteroid);
        }
    }
}
