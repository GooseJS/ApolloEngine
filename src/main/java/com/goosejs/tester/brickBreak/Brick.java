package com.goosejs.tester.brickBreak;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

/**
 * Created by andrewrusso on 2/6/17.
 */
public class Brick
{
    private TexturedPrimitive2D primitive;
    private float width;
    private float length;

    private Vector2f position;
    public Brick(float x, float y, float length1, float width1)
    {
            this.position = new Vector2f(x, y);
            width = width1;
            length = length1;

            primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), length1, width1);

    }
    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public float getX()
    {
        return position.x;
    }
    public float getY()
    {
        return position.y;
    }
    public float getLength()
    {
        return length;
    }
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return position.y();
    }
    public void delete()
    {
        System.out.print("penis");
    }
}
