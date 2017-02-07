package com.goosejs.tester.brickBreak;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

public class BrickBreakBall
{
    private final float maxXVelocity = 5f;

    private TexturedPrimitive2D primitive;

    private Vector2f position;
    private Vector2f velocity;

    private float diameter = 15;

    public BrickBreakBall(float x, float y, float xvel, float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel, yvel);

        primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), diameter, diameter);
    }

    public void update()
    {
        position.add(velocity);

        if (position.y <= 0) respawn();
        if ((position.y + diameter) > 700) velocity.y = -velocity.y;
        if (position.x < 0 || position.x > 1200) velocity.x = -velocity.x;
    }

    public void checkCollisionPaddle(brickBreakPaddle BrickBreakPaddle)
    {
        if ((position.x < BrickBreakPaddle.getX() + BrickBreakPaddle.getWidth()))
        {
            if ((position.x > BrickBreakPaddle.getX()) && (position.y < (BrickBreakPaddle.getY() + BrickBreakPaddle.getHeight())))
            {
                velocity.y = -velocity.y;
                velocity.x += getXVelocityPaddle(BrickBreakPaddle);
            }
        }

    }
    public void checkCollisionBrick(Brick[] bricks)
    {
        for(int i = 0; i < 10; i++)
        {
            if ((position.x < bricks[i].getX() + bricks[i].getWidth()))
            {
                if ((position.x > bricks[i].getX()) && (position.y < (bricks[i].getY() + bricks[i].getHeight())))
                {
                    velocity.y = -velocity.y;
                    velocity.x += getXVelocityBrick(bricks[i]);
                    bricks[i].delete();
                }
            }
        }


    }

    private float getXVelocityBrick(Brick Brick)
    {
        float ballCenter = position.x - Brick.getX() + (diameter / 2f);
        float percentage = ballCenter / Brick.getWidth();

        if (percentage > 0.5f)
            return maxXVelocity * ((percentage - 0.5f) * 2f);
        else
            return -(maxXVelocity * (percentage * 2f));
    }
    private float getXVelocityPaddle(brickBreakPaddle BrickBreakPaddle)
    {
        float ballCenter = position.x - BrickBreakPaddle.getX() + (diameter / 2f);
        float percentage = ballCenter / BrickBreakPaddle.getWidth();

        if (percentage > 0.5f)
            return maxXVelocity * ((percentage - 0.5f) * 2f);
        else
            return -(maxXVelocity * (percentage * 2f));
    }
    public void respawn()
    {
        position.x = 1200/2 + diameter;
        position.y = 700/2 + diameter;
        changeVel(0,-5);
    }
    public void changeVel(float xvel, float yvel){
        velocity.x = xvel;
        velocity.y = yvel;
    }

    public float getX()
    {
        return position.x;
    }

    public float getYVeloicty()
    {
        return velocity.y;
    }
    public float getXVeloicty()
    {
        return velocity.x;
    }
    public void setYVelocity(float yvel)
    {
        velocity.y = yvel;
    }
    public void setXVelocity(float xvel)
    {
        velocity.x = xvel;
    }
    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }


}
