package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


import java.util.Random;


public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    public EnemyPlugin (){

    }
    @Override
    public void start(GameData gameData, World world) {
    enemy= createEnemyShip(gameData);
    world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
    world.removeEntity(enemy);
    }
    private Entity createEnemyShip(GameData gameData){
        Random random = new Random();
        float deacceleration = 10;
        float acceleration = 50;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() - random.nextFloat();
        float y = gameData.getDisplayHeight() - random.nextFloat();
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.setRadius(10);
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(3));
         return enemyShip;
    }
}
