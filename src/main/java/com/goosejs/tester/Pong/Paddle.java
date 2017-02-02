package com.goosejs.tester.Pong;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;

public class Paddle
{

    private static final TexturedPrimitive2D primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), 25, 100);

    private float x;
    private float y;

    public Paddle(float x)
    {
        this.x = x;
        this.y = 0;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y);
    }

    public void moveUp()
    {
        if(y<=600)
        {
            y += 5;
        }
    }

    public void moveDown()
    {
        if(y>=0)
        {
            y -= 5;
        }
    }
}
