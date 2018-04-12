package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;
import com.goosejs.tester.redHarvest.*;
import java.lang.*;

/**
 * Created by andrewrusso on 4/11/18.
 */
public class Enemy
{
    private TexturedPrimitive2D primitive;

    private float width = 90;
    private float height = 90;

    private AABB2D aabb;

    private Vector2f position;
    private Vector2f velocity;
    private Projectile projectile;

    private float gravity = 1;
    private float t_velocity = 300;


    public Enemy(float x, float y,float xvel, float yvel)
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

    public void update(Character character)
    {
        /*
        //if enemy is to the right of character
        if (position.x > character.getPosition().x)
        {
            moveLeft();
            //System.out.println("working left");
            //System.out.println(character.getPosition().x);
        }

        //if enemy is to the left of character
        else if (position.x < character.getPosition().x)
        {
            moveRight();
            //System.out.println("working right");
            //System.out.println(character.getPosition().x);
        }

        else if(position.x == character.getPosition().x)
        {
            position.x = character.getPosition().x;
        }
        */

    }

    /* private void jump()
    {
        velocity.y = 20;
        if( position.y >= 200)
        {
            isJumping = false;
        }
        else if(position.y < 200)
        {
            position.y = position.y + velocity.y;
        }
    } */

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
