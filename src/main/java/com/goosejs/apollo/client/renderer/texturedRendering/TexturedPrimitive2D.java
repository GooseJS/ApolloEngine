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

//    public static final int
//            GL_CURRENT_BIT         = 0x1,
//            GL_POINT_BIT           = 0x2,
//            GL_LINE_BIT            = 0x4,
//            GL_POLYGON_BIT         = 0x8,
//            GL_POLYGON_STIPPLE_BIT = 0x10,
//            GL_PIXEL_MODE_BIT      = 0x20,
//            GL_LIGHTING_BIT        = 0x40,
//            GL_FOG_BIT             = 0x80,
//            GL_DEPTH_BUFFER_BIT    = 0x100,
//            GL_ACCUM_BUFFER_BIT    = 0x200,
//            GL_STENCIL_BUFFER_BIT  = 0x400,
//            GL_VIEWPORT_BIT        = 0x800,
//            GL_TRANSFORM_BIT       = 0x1000,
//            GL_ENABLE_BIT          = 0x2000,
//            GL_COLOR_BUFFER_BIT    = 0x4000,
//            GL_HINT_BIT            = 0x8000,
//            GL_EVAL_BIT            = 0x10000,
//            GL_LIST_BIT            = 0x20000,
//            GL_TEXTURE_BIT         = 0x40000,
//            GL_SCISSOR_BIT         = 0x80000,
//            GL_ALL_ATTRIB_BITS     = 0xFFFFF;

    public static final int
            UPSIDE_DOWN_TEXTURE = 0x1;

    private Texture texture;
    private int vaoID;
    private int flags;

    private int vertexCount; // TODO: This is most likely always going to be the same, can probably be removed

    public TexturedPrimitive2D(Texture texture, float width, float height)
    {
        this(texture, width, height, 0);
    }

    public TexturedPrimitive2D(Texture texture, float width, float height, int flags)
    {
        this.texture = texture;
        this.flags = flags;

        generatePrimitive(width, height);
    }

    private void generatePrimitive(float width, float height)
    {
        vaoID = VAO.createVAO();
        VAO.bindVAO(vaoID);
        ArrayList<Float> vertices = new ArrayList<>();
        ArrayList<Float> texCoord = new ArrayList<>();

        if ((flags & UPSIDE_DOWN_TEXTURE) == UPSIDE_DOWN_TEXTURE)
            GLUtils.addDataToArrayList(vertices, texCoord, 0, 0, width, height, 1, 1, 0, 0);
        else
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
