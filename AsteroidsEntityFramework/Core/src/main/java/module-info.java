module Core {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    requires com.badlogic.gdx;
    requires Enemy;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}