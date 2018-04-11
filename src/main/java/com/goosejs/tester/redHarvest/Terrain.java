package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;

/**
 * Created by andrewrusso on 2/7/17.
 */
public class Terrain
{
    private TexturedPrimitive2D primitive;

    private AABB2D aabb;

    private Vector2f position;
    private Vector2f velocity;
    public Terrain(float x, float y, float length, float width)
    {
        this.position = new Vector2f(x, y);
        primitive = new TexturedPrimitive2D(new Texture("flappy/pipe.png"), length, width);

    }

}
