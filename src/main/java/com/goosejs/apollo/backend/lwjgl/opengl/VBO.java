package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

/**
 * A simple wrapper class in order to make VertexBufferObject functions simpler
 */
public class VBO
{

    /** A list of all created VBO's */
    private static final List<Integer> vbos = new ArrayList<>();

    /** A private constructor to prevent this class from being instantiated */
    private VBO() {}

    /**
     * @see GL15#glGenBuffers()
     */
    public static int createVBO()
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        return vboID;
    }

    /**
     * @see GL15#glBindBuffer(int, int)
     */
    public static void bindVBO(VertexBufferType type, int vboID)
    {
        bindVBO(type.getIntValue(), vboID);
    }

    /**
     * @see GL15#glBindBuffer(int, int)
     */
    public static void bindVBO(int type, int vboID)
    {
        glBindBuffer(type, vboID);
    }

    /**
     * @see GL15#glBindBuffer(int, int)
     * Called with buffer 0
     */
    public static void unbindVBO(VertexBufferType type)
    {
        unbindVBO(type.getIntValue());
    }

    /**
     * @see GL15#glBindBuffer(int, int)
     * Called with buffer 0
     */
    public static void unbindVBO(int type)
    {
        glBindBuffer(type, 0);
    }

    /**
     * @see GL15#glBufferData(int, ByteBuffer, int)
     */
    public static void uploadData(VertexBufferType type, FloatBuffer data, int usage)
    {
        uploadData(type.getIntValue(), data, usage);
    }

    /**
     * @see GL15#glBufferData(int, ByteBuffer, int)
     */
    public static void uploadData(int type, FloatBuffer data, int usage)
    {
        glBufferData(type, data, usage);
    }

    /**
     * @see GL15#glBufferData(int, ByteBuffer, int)
     */
    public static void uploadData(VertexBufferType type, IntBuffer data, int usage)
    {
        uploadData(type.getIntValue(), data, usage);
    }

    /**
     * @see GL15#glBufferData(int, ByteBuffer, int)
     */
    public static void uploadData(int type, IntBuffer data, int usage)
    {
        glBufferData(type, data, usage);
    }

    /**
     * Deletes all created VAO's
     * Should be called on program shutdown
     */
    public static void cleanup()
    {
        vbos.forEach(GL15::glDeleteBuffers);
    }

}