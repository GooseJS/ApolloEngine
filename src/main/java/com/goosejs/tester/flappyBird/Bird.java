package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

public class Bird
{

    private TexturedPrimitive2D primitive;
    private Vector2f position;

    public void init()
    {
        primitive = new TexturedPrimitive2D(new Texture("flappy/bird.png"), 100, 100);
        position = new Vector2f(0, 0);
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y, 0, 0); // TODO: Make bird rotate as he falls / flaps
    }
}
