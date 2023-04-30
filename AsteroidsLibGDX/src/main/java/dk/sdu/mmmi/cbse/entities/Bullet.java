package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

    private float lifetime;
    private float lifetimer;

    private boolean remove;

    public Bullet(float x, float y, float radians){
            this.x = x;
            this.y = y;
            this.radians=radians;

        float speed = 350;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        width = height =2;
        lifetime = 3;
        lifetimer = 0;

    }

    public boolean shouldRemove(){return remove;}

    public void update(float dt){
        x += dx * dt;
        y += dy *dt;
        wrap();
        lifetimer += dt;
        if(lifetimer>lifetime){
            remove = true;
        }

    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,0,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(x-width/2f,y-height/2f,width/2f);
        sr.end();
    }

}
