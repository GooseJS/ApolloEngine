package com.goosejs.tester.brickBreak;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;

public class Brick
{
    private static final AABB2D NULL_AABB = new AABB2D(0, 0, 0, 0);

    private TexturedPrimitive2D primitive;
    private float width;
    private float height;

    private Vector2f position;

    private AABB2D aabb;

    private boolean active;

    public Brick(float x, float y, float width, float height)
    {
        this.position = new Vector2f(x, y);
        this.width = width;
        this.height = height;

        active = true;

        primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), width, height);

        aabb = new AABB2D(x, y, width, height);
    }

    public void draw(SpriteBatch batch)
    {
        if (active) batch.draw(primitive, position.x, position.y);
    }

    public float getX()
    {
        return position.x;
    }
    public float getY()
    {
        return position.y;
    }
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public void delete()
    {
        active = false;
    }

    public AABB2D getAABB()
    {
        if (active) return aabb;
        else return NULL_AABB;
    }
}
