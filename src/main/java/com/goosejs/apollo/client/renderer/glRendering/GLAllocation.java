package com.goosejs.apollo.client.renderer.glRendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLAllocation
{

    public static int generateDisplayLists(int range)
    {
        int result = GL11.glGenLists(range);

        if (result == 0)
        {
            int error = GL11.glGetError();
            String errorString = "No error code reported";
            
            throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + range + ", GL error (" + error + "): " + errorString);
        }
        else
        {
            return result;
        }
    }

    public static void deleteDisplayLists(int list, int range)
    {
        GL11.glDeleteLists(list, range);
    }

    public static void deleteDisplayList(int list)
    {
        deleteDisplayLists(list, 1);
    }

    public static ByteBuffer createDirectByteBuffer(int capacity)
    {
        return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
    }

    public static IntBuffer createDirectIntBuffer(int capacity)
    {
        return createDirectByteBuffer(capacity << 2).asIntBuffer();
    }

    public static FloatBuffer createDirectFloatBuffer(int capacity)
    {
        return createDirectByteBuffer(capacity << 2).asFloatBuffer();
    }

}