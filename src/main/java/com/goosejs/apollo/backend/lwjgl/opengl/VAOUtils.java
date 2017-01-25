package com.goosejs.apollo.backend.lwjgl.opengl;

import com.goosejs.apollo.util.ApolloBufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Various utils for use with VAO's
 */
public class VAOUtils
{

    /**
     * A private constructor to prevent instantiation
     */
    private VAOUtils() {}

    public static int storeDataInAttributeList(int index, int coordinateSize, int stride, FloatBuffer data)
    {
        int vboID = VBO.createVBO();
        storeDataInAttributeList(vboID, index, coordinateSize, stride, data);
        return vboID;
    }

    public static void storeDataInAttributeList(int vboID, int index, int coordinateSize, int stride, FloatBuffer data)
    {
        VBO.bindVBO(GL15.GL_ARRAY_BUFFER, vboID);
        VBO.uploadData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW); // TODO: Figure out how to handle static / dynamic
        VAO.vertexAttribPointer(index, coordinateSize, GL11.GL_FLOAT, false, stride, 0);
    }

}
