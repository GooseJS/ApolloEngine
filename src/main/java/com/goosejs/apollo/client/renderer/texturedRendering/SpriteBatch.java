package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;

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
        //renderer.loadPerspectiveMatrix(MatrixUtils.createPerspectiveMatrix(180f, 800f / 600f, -1, 1000));
        renderer.loadPerspectiveMatrix(createProjectionMatrix());
    }

    private Matrix4f createProjectionMatrix()
    {
        float farPlane = 1000f;
        float nearPlane = 0.1f;

        float aspectRatio = 800f / 600f; // TODO: This should not be hardcoded!
        float yScale = (float)((1f / Math.tan(Math.toRadians(90f / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustrumLength = farPlane - nearPlane;

        Matrix4f projectionMatrix = new Matrix4f().identity();
        projectionMatrix.m00(xScale);
        projectionMatrix.m11(yScale);
        projectionMatrix.m22(-((farPlane + nearPlane) / frustrumLength));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * nearPlane * farPlane) / frustrumLength));
        projectionMatrix.m33(0);

        return projectionMatrix;
    }

    public void draw(TexturedPrimitive2D primitive2D, float x, float y)
    {
        draw(primitive2D, x, y, 1);
    }

    public void draw(TexturedPrimitive2D primitive, float x, float y, float z)
    {
        List<Renderable> renderables = renders.get(primitive.getTexture().getTextureID());
        if (renderables == null)
            renderables = new ArrayList<>();

        renderables.add(new Renderable(primitive, x, y, z));
        renders.put(primitive.getTexture().getTextureID(), renderables);
    }

    float renderRot = 0;

    public void flushQueue()
    {
        renderRot += 0.5f;
        for (Integer integer : renders.keySet())
        {
            List<Renderable> renderables = renders.get(integer);

            for (Renderable renderable : renderables)
            {
                renderer.drawTexture(renderable.primitive, renderable.x, renderable.y, renderable.z, renderRot);
            }
        }

        renders.clear();
    }

    private class Renderable
    {
        final TexturedPrimitive2D primitive;
        final float x;
        final float y;
        final float z;

        public Renderable(TexturedPrimitive2D primitive, float x, float y, float z)
        {
            this.primitive = primitive;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}