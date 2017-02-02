package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;

public class Bird
{

    private TexturedPrimitive2D primitive;
    private float x;
    private float y;

    private float gravity = 0.2f;
    private float yFall = 0;

    public void init()
    {
        primitive = new TexturedPrimitive2D(new Texture("flappy/bird.png"), 50, 50);
        x = 100;
        y = 700;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y, 0, -(yFall * 5));
    }

    public void update()
    {
        if (y > -10) yFall += gravity; else yFall = 0;
        y -= yFall;
    }

    public void flap()
    {
        yFall = -4;
    }
}
