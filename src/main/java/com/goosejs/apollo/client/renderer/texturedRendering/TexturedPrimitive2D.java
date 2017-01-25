package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.backend.lwjgl.opengl.VAOUtils;
import com.goosejs.apollo.util.ApolloBufferUtils;
import com.goosejs.apollo.util.GLUtils;

import java.util.ArrayList;

public class TexturedPrimitive2D
{
    // TODO: Remember to look into rewritting this class and the Texture class to support texture atlases

    private Texture texture;
    private int vaoID;

    private int vertexCount; // TODO: This is most likely always going to be the same, can probably be removed

    public TexturedPrimitive2D(Texture texture, float width, float height)
    {
        this.texture = texture;

        generatePrimitive(width, height);
    }

    private void generatePrimitive(float width, float height)
    {
        vaoID = VAO.createVAO();
        VAO.bindVAO(vaoID);
        ArrayList<Float> vertices = new ArrayList<>();
        ArrayList<Float> texCoord = new ArrayList<>();

        GLUtils.addDataToArrayList(vertices, texCoord, 0, 0, width, height, 0, 0, 1, 1);
        this.vertexCount = vertices.size() / 2;

        VAOUtils.storeDataInAttributeList(0, 2, 0, ApolloBufferUtils.createFloatBuffer(vertices));
        VAOUtils.storeDataInAttributeList(1, 2, 0, ApolloBufferUtils.createFloatBuffer(texCoord));
    }

    public int getVAOID()
    {
        return vaoID;
    }

    public Texture getTexture()
    {
        return this.texture;
    }

    public int getVertexCount()
    {
        return this.vertexCount;
    }
}
