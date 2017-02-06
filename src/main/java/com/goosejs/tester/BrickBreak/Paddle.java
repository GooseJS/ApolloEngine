package com.goosejs.tester.BrickBreak;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
public class Paddle
{

    private static final float height = 25;
    private static final float width = 100;

    private static final TexturedPrimitive2D primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), width, height);

    private float x;
    private float y;

    public Paddle() {
    }

    public Paddle(float x)
    {
        this.x = 550;
        this.y = 100;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y);
    }

    public void moveRight()
    {
        if(x <= 1195)
            x += 5;

    }

    public void moveLeft()
    {
        if(x >= 5)
            x -= 5;

    }

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
