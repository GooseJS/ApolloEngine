package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.backend.lwjgl.opengl.VAOUtils;
import com.goosejs.apollo.util.ApolloBufferUtils;

public class TexturedPrimitive2D
{

    private static int vaoID = -1;

    private Texture texture;

    public TexturedPrimitive2D(Texture texture)
    {
        this.texture = texture;

        if (vaoID == -1)
            generatePrimitive();
    }

    private float[] vertices = new float[]
            {
                    -0.5f, 0.5f,
                    -0.5f, -0.5f,
                    0.5f, -0.5f,

                    0.5f, -0.5f,
                    0.5f, 0.5f,
                    -0.5f, 0.5f,
            };

    private void generatePrimitive()
    {
        vaoID = VAO.createVAO();
        VAOUtils.storeDataInAttributeList(0, 2, 0, ApolloBufferUtils.createFloatBuffer(vertices));
    }

    public int getVAOID()
    {
        return vaoID;
    }

}
