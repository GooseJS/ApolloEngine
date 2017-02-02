package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;

public class Pipe
{

    private static final Texture texture = new Texture("flappy/pipe.png");

    private final float gapWidth = 150;
    private final float primitiveHeight = 400;

    private TexturedPrimitive2D bottomPrimitive;
    private TexturedPrimitive2D topPrimitive;
    private float x;
    private float y;

    public void init()
    {
        bottomPrimitive = new TexturedPrimitive2D(texture, 100, primitiveHeight);
        topPrimitive = new TexturedPrimitive2D(texture, 100, primitiveHeight, TexturedPrimitive2D.UPSIDE_DOWN_TEXTURE);
        x = 500;
        y = 200;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(bottomPrimitive, x, y - primitiveHeight);
        batch.draw(topPrimitive, x, y + gapWidth);
    }

    public void update()
    {
        x -= 2;
    }
}