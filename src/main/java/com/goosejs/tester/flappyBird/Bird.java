package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import com.goosejs.apollo.physics.CollisionUtils;

public class Bird
{

    private TexturedPrimitive2D primitive;
    private float x;
    private float y;

    private float width = 50;
    private float height = 50;

    private float jumpHeight = 6;
    private float gravity = 0.4f;
    private float yFall = 0;

    private AABB2D aabb;

    public void init()
    {
        primitive = new TexturedPrimitive2D(new Texture("flappy/bird.png"), 50, 50);
        x = 100;
        y = 400;
        aabb = new AABB2D(x, y, width, height);
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, x, y, 0, -(yFall * 5));
    }

    public void update()
    {
        if (y > -10) yFall += gravity; else yFall = 0;
        y -= yFall;
        aabb.setY(y);
    }

    public void flap()
    {
        yFall = -jumpHeight;
    }

    public boolean checkCollision(Pipe pipe)
    {
        return CollisionUtils.testAABBAABB(pipe.getAABBTop(), aabb) || CollisionUtils.testAABBAABB(pipe.getAABBBottom(), aabb) || y < -10;
    }

    public float getY()
    {
        return this.y;
    }

    public void reset()
    {
        yFall = 0;
        y = 400;
    }
}
