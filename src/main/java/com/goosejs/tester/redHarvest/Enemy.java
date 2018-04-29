package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;
import com.goosejs.tester.redHarvest.*;
import java.lang.*;
import java.math.*;
import java.util.Random;

/**
 * Created by andrewrusso on 4/11/18.
 */
public class Enemy
{
    private TexturedPrimitive2D primitive;

    private float width = 130;
    private float height = 150;

    private AABB2D aabb;

    private Vector2f position;
    private Vector2f velocity;
    private Projectile projectile;

    private float gravity = 1;
    private float t_velocity = 300;

    private Random random;

    int blah;
    int targetX;

    public Enemy(float x, float y,float xvel, float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel,yvel);
        primitive = new TexturedPrimitive2D(new Texture("redHarvest/Joker.png"), width, height);
        this.aabb = new AABB2D(x, y, width, height);
        random = new Random();
    }

    public void makeTarget()
    {
        blah = random.nextInt(200) - 100;
        targetX = (int) (position.x + blah);
        System.out.println(targetX);
    }
    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public void update(Character character)
    {
        System.out.println(position.x());
        System.out.println(position.y());
        System.out.println(width);
        System.out.println(height);
        /*if(targetX > position.x)
            moveRight();
        else if(targetX < position.x)
            moveLeft();*/
    }

    public void moveRight()
    {
        if(position.x < (1200 - width))
            position.x += velocity.x;
        else if(position.x == (1200 - width) || position.x > targetX)
        {
            position.x = (1200 - width);
            makeTarget();
        }
    }

    public void moveLeft()
    {
        if(position.x > 0)
            position.x -= velocity.x;
        else if(position.x == 0 || position.x < targetX)
        {
            position.x = (0);
            makeTarget();
        }
    }
    public Vector2f getPosition() {return position;}
    public Vector2f getVelocity() {return velocity;}
    public void setPosition (Vector2f position) { position = position;}
    public float getWidth() {return width;}
    public float getHeight() {return height;}
}
