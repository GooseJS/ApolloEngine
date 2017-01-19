package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexArray;

public class TexturedPrimitive
{

    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    private int vaoID;

    public TexturedPrimitive(Texture texture, float x, float y, float width, float height)
    {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        generatePrimitive();
    }

    private void generatePrimitive()
    {
        vaoID = VertexArray.createVAO();

    }

}
