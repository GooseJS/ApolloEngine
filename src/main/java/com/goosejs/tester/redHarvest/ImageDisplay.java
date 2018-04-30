package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

/**
 * Created by andrewrusso on 4/29/18.
 */
public class ImageDisplay
{
    private static TexturedPrimitive2D primitive;
    private static Vector2f position;
    public ImageDisplay(int width, int height, int x, int y, String fileName)
    {
        primitive = new TexturedPrimitive2D(new Texture(fileName), width, height);
        position = new Vector2f(x, y);
    }
    public static void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }
}
