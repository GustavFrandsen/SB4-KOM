import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.mmmi.cbse.AsteroidSystem.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.AsteroidSystem.AsteroidControlSystem;

}