module Core {
    opens dk.sdu.mmmi.cbse.main;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    requires com.badlogic.gdx;
    requires spring.context;
    requires spring.beans;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}