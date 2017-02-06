package com.goosejs.tester.brickBreak;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;

public class brickBreakPaddle
{

    private static final float width = 200;
    private static final float height = 25;
    private static final float padding = 20;

    private static final TexturedPrimitive2D primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), width, height);

    private float x;
    private float y;

    private float movementSpeed = 25;

    public brickBreakPaddle(float y, TrueTypeFontRenderer fontRenderer)
    {
        this.x = 200;
        this.y = y;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y);
    }

    public void moveRight()
    {
        if(x < (1200 - getWidth() - padding))
            x += movementSpeed;
        else
            x = (1200 - getWidth() - padding);

    }

    public void moveLeft()
    {
        if(x > padding)
            x -= movementSpeed;
        else
            x = padding;

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
