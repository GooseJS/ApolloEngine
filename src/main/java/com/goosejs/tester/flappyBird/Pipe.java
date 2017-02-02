package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;

public class Pipe
{

    private static final Texture texture = new Texture("flappy/pipe.png");

    private final float gapHeight = 150;
    private final float primitiveWidth = 100;
    private final float primitiveHeight = 400;
    private final float speed = 4;

    private TexturedPrimitive2D bottomPrimitive;
    private TexturedPrimitive2D topPrimitive;
    private float x;
    private float y;

    private AABB2D aabbTop;
    private AABB2D aabbBottom;

    boolean hasPassedX;

    public void init()
    {
        bottomPrimitive = new TexturedPrimitive2D(texture, primitiveWidth, primitiveHeight);
        topPrimitive = new TexturedPrimitive2D(texture, primitiveWidth, primitiveHeight, TexturedPrimitive2D.UPSIDE_DOWN_TEXTURE);
        x = 500;
        y = 200;
        aabbTop = new AABB2D(x, y + gapHeight, primitiveWidth, primitiveHeight);
        aabbBottom = new AABB2D(x, y - primitiveHeight, primitiveWidth, primitiveHeight);
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(bottomPrimitive, x, y - primitiveHeight);
        batch.draw(topPrimitive, x, y + gapHeight);
    }

    public void update()
    {
        x -= speed;
        aabbTop.setX(x);
        aabbBottom.setX(x);
    }

    public boolean outsideOfScreen()
    {
        return x < (0 - primitiveWidth);
    }

    public void reset(float x)
    {
        this.x = x;
        this.y = 100 + ((float)Math.random() * 400f);
        aabbTop.setX(x);
        aabbBottom.setX(x);

        aabbTop.setY(y + gapHeight);
        aabbBottom.setY(y - primitiveHeight);

        hasPassedX = false;
    }

    public boolean hasPassedX(float x)
    {
        if (!hasPassedX && this.x < x)
        {
            hasPassedX = true;
            return true;
        }

        return false;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        return primitiveWidth;
    }

    public float getHeight()
    {
        return primitiveHeight;
    }

    public AABB2D getAABBTop()
    {
        return aabbTop;
    }

    public AABB2D getAABBBottom()
    {
        return aabbBottom;
    }
}