package com.goosejs.tester.Pong;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

public class Ball
{
    private final float maxXVelocity = 5;

    private TexturedPrimitive2D primitive;

    private Vector2f position;
    private Vector2f velocity;
    private boolean gameOver;

    private float diameter = 15;

    public Ball(float x,float y,float xvel,float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel, yvel);

        primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), diameter, diameter);
    }

    public void respawn(float x,float y,float xvel,float yvel)
    {
        position.x = x;
        position.y = y;
        velocity.x = xvel;
        velocity.y = yvel;
    }

    public void update()
    {
        position.add(velocity);

        //check if hit wall

        if (position.y < 0)
        {
            velocity.y = -velocity.y;
        }
        if ((position.y + diameter) > 700)
        {

            velocity.y = -velocity.y;
        }
    }

    public void checkcollision(Paddle paddle)
    {
        if ((position.x < paddle.getX() + paddle.getWidth()))
        {
            if ((position.x > paddle.getX()) && (position.y < (paddle.getY() + paddle.getHeight())))
            {
                velocity.x = -velocity.x;
                velocity.y = getXVelocity(paddle);
                respawn(600,350,-10,-10);
            }
        }

    }

    private float getXVelocity(Paddle paddle)
    {
        float ballCenter = position.x - paddle.getX() + (diameter / 2f);
        float percentage = ballCenter / paddle.getHeight();

        if (percentage > 0.5f)
            return maxXVelocity * ((percentage - 0.5f) * 2f);
        else
            return -(maxXVelocity * (percentage * 2f));
    }

    public float getX()
    {
        return position.x;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public boolean getGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean bool)
    {
        gameOver = bool;
    }
}
