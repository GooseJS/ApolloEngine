package com.goosejs.apollo.client.renderer.texturedRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteBatch
{

    private Map<Integer, List<Renderable>> renders;

    private TexturedRenderer renderer;

    public SpriteBatch()
    {
        renders = new HashMap<>();
        renderer = new TexturedRenderer();
    }

    public void draw(TexturedPrimitive2D primitive, float x, float y)
    {
        List<Renderable> renderables = renders.get(primitive.getTexture().getTextureID());
        if (renderables == null)
            renderables = new ArrayList<>();

        renderables.add(new Renderable(primitive, x, y));
        renders.put(primitive.getTexture().getTextureID(), renderables);
    }

    public void flushQueue()
    {
        for (Integer integer : renders.keySet())
        {
            List<Renderable> renderables = renders.get(integer);

            for (Renderable renderable : renderables)
            {
                renderer.drawTexture(renderable.primitive, renderable.x, renderable.y);
            }
        }

        renders.clear();
    }

    private class Renderable
    {
        final TexturedPrimitive2D primitive;
        final float x;
        final float y;

        public Renderable(TexturedPrimitive2D primitive, float x, float y)
        {
            this.primitive = primitive;
            this.x = x;
            this.y = y;
        }
    }

}