package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import com.sun.xml.internal.bind.v2.TODO;
import org.joml.Vector2f;

public class Character
{
    private TexturedPrimitive2D primitive;

    private float width = 118;
    private float height = 150;

    private AABB2D aabb;

    private Vector2f position;
    private Vector2f velocity;

    private float gravity = 1;
    private float t_velocity = 300;

    boolean isJumping = false;
    boolean onTop = false;

    public Character(float x, float y,float xvel, float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel,yvel);
        primitive = new TexturedPrimitive2D(new Texture("redHarvest/detective.png"), width, height);
        this.aabb = new AABB2D(x, y, width, height);
    }
    public Character()
    {
        super();
        this.position = new Vector2f(1,1);
        this.velocity = new Vector2f(0,0);
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public void update(Enemy enemy)
    {
        if (isJumping)
        {
            jump();
        } else if (position.y > 0) fall(enemy);
    }


    public void fall (Enemy enemy)
    {
        if((position.x > enemy.getPosition().x - enemy.getWidth() && position.x < enemy.getPosition().x + enemy.getWidth()) && position.y == enemy.getPosition().y + enemy.getHeight())
        {
            position.y = position.y;
            onTop = true;
            redHarvest.setIsWin(false);
        }
        else
        {
            velocity.y = 10;
            position.y = position.y - velocity.y;
            onTop = false;
        }

    } // TODO: Implement collision detection

    private void jump()
    {
        if (onTop)
        {
            velocity.y = 20;
            if( position.y >= 290)
            {
                isJumping = false;
            }
            else if(position.y < + 290)
            {
                position.y = position.y + velocity.y;
            }
        }

        else if(!onTop)
        {
            velocity.y = 20;
            if( position.y >= 200)
            {
                isJumping = false;
            }
            else if(position.y < + 200)
            {
                position.y = position.y + velocity.y;
            }
        }

    }


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

    public void setisJumping (boolean bool) {isJumping = bool;}

    public Vector2f getPosition() {return position;}
    public Vector2f getVelocity() {return velocity;}
    public void setPosition (Vector2f position) {position = position;}
    public float getWidth() {return width;}
    public float getHeight() {return height;}
}
