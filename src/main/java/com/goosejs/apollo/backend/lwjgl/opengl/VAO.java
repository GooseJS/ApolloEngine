package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * A Utility class to wrap VertexArray functionality (creation, binding,
 * etc.) all into one class.
 */
public class VAO
{

    /** All the created VAO's so they can be deleted on Application quit */
    private static final List<Integer> vaos = new ArrayList<>();

    /** A private constructor to make sure the class is not instantiated */
    private VAO() {}

    /**
     * @see GL30#glGenVertexArrays()
     */
    public static int createVAO()
    {
        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        return vaoID;
    }

    /**
     * @see GL30#glBindVertexArray(int)
     */
    public static void bindVAO(int vaoID)
    {
        glBindVertexArray(vaoID);
    }

    /**
     * @see GL30#glBindVertexArray(int)
     * Called with VertexArray 0
     */
    public static void unbindVAO()
    {
        glBindVertexArray(0);
    }

    /**
     * see GL20#glVertexAttribPointer(int, int, int, boolean, int, long);
     */
    public static void vertexAttribPointer(int index, int size, int type)
    {
        vertexAttribPointer(index, size, type, false, 0, 0);
    }

    /**
     * see GL20#glVertexAttribPointer(int, int, int, boolean, int, long);
     */
    public static void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointerOffset)
    {
        glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
    }

    /**
     * see GL20#glEnableVertexAttribArray(int)
     */
    public static void enableAttribArray(int ... attribArray)
    {
        for (int value : attribArray) glEnableVertexAttribArray(value);
    }

    /**
     * see GL20#glDisableVertexAttribArray(int)
     */
    public static void disableAttribArray(int ... attribArray)
    {
        for (int value : attribArray) glDisableVertexAttribArray(value);
    }

    /**
     * Deletes all created VAO's
     * Should be called on program shutdown
     */
    public static void cleanup()
    {
        vaos.forEach(GL30::glDeleteVertexArrays);
    }

}