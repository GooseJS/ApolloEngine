package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;


public class Character
{
    private TexturedPrimitive2D primitive;

    private float width = 90;
    private float height = 90;

    private AABB2D aabb;

    private Vector2f position;
    private Vector2f velocity;

    private float gravity = 1;
    private float t_velocity = 300;


    public Character(float x, float y,float xvel, float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel,yvel);
        primitive = new TexturedPrimitive2D(new Texture("flappy/bird.png"), width, height);
        this.aabb = new AABB2D(x, y, width, height);
    }
    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public void update()
    {
        if(Game.window.getKeyboardCallback().isKeyDown(32))
        {
            if (position.y <= 0)
            {
                velocity.y = 100;
                position.y += velocity.y;
            }

        }
        else if (position.y > 0) fall();
    }

    public void fall ()
    {
        velocity.y = 10;
        //velocity.y = velocity.y + gravity;
        //position.y -= velocity.y;
        //if (velocity.y == t_velocity) {velocity.y = t_velocity;}
        position.y = position.y - velocity.y;

    }

    public void jump(SpriteBatch batch) {}

    public void moveRight()
    {
        if(position.x < (1200 - width))
            position.x += velocity.x;
        else
            position.x = (1200 - width);

        aabb.setX(position.x);
    }

    public void moveLeft()
    {
        if(position.x > 0)
            position.x -= velocity.x;
        else
            position.x = 0;

        aabb.setX(position.x);
    }
}
