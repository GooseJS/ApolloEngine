package com.goosejs.apollo.client.renderer.batchRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;

import java.util.ArrayList;
import java.util.HashMap;

public class SpriteBatch
{

    private HashMap<Integer, ArrayList<Renderable>> batches;

    public SpriteBatch()
    {
        batches = new HashMap<>();
    }

    public void drawSprite(Texture texture, int x, int y)
    {
        ArrayList<Renderable> spriteBatch = batches.get(texture.getTextureID());

        if (spriteBatch == null) spriteBatch = new ArrayList<>();

        spriteBatch.add(new Renderable(x, y, 0));

        batches.put(texture.getTextureID(), spriteBatch);
    }

    public class Renderable
    {
        public final float x;
        public final float y;
        public final float z;

        public Renderable(float x, float y, float z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}