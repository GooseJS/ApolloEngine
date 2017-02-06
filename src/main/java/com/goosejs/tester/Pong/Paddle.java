package com.goosejs.tester.Pong;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;

public class Paddle
{

    private static final float width = 25;
    private static final float height = 100;

    private static final TexturedPrimitive2D primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), width, height);

    private float x;
    private float y;

    public Paddle() {
    }

    public Paddle(float x)
    {
        this.x = x;
        this.y = 300;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y);
    }

    public void moveUp()
    {
        if(y <= 580)
            y += 20;

    }

    public void moveDown()
    {
        if(y >= 20)
            y -= 20;

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
