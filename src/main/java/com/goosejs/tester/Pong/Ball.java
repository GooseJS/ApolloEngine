package com.goosejs.tester.Pong;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

public class Ball
{
    private final float maxYVelocity = 5;

    private TexturedPrimitive2D primitive;

    private Vector2f position;
    private Vector2f velocity;

    private float diameter = 15;

    public Ball(float x,float y,float xvel,float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel, yvel);

        primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), diameter, diameter);
    }

    public void update()
    {
        position.add(velocity);

        if (position.y < 0) velocity.y = -velocity.y;
        if ((position.y + diameter) > 700) velocity.y = -velocity.y;
    }

    public void checkcollision(Paddle leftPaddle, Paddle rightPaddle)
    {
        if ((position.x < leftPaddle.getX() + leftPaddle.getWidth()))
        {
            if ((position.y > leftPaddle.getY()) && (position.y < (leftPaddle.getY() + leftPaddle.getHeight())))
            {
                velocity.x = -velocity.x;
                velocity.y = getYVelocity(leftPaddle);
            }
        }

        if ((position.x > (rightPaddle.getX() - diameter)))
        {
            if ((position.y > rightPaddle.getY()) && (position.y < (rightPaddle.getY() + rightPaddle.getHeight())))
            {
                velocity.x = -velocity.x;
                velocity.y = getYVelocity(rightPaddle);
            }
        }
    }

    private float getYVelocity(Paddle paddle)
    {
        float ballCenter = position.y - paddle.getY() + (diameter / 2f);
        float percentage = ballCenter / paddle.getHeight();

        if (percentage > 0.5f)
            return maxYVelocity * ((percentage - 0.5f) * 2f);
        else
            return -(maxYVelocity * (percentage * 2f));
    }

    public float getX()
    {
        return position.x;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

}
