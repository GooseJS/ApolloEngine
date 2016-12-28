package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray
{

    private static final List<Integer> vaos = new ArrayList<>();

    private VertexArray() {}

    public static int createVAO()
    {
        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        return vaoID;
    }

    public static void bindVAO(int vaoID)
    {
        glBindVertexArray(vaoID);
    }

    public static void unbindVAO()
    {
        glBindVertexArray(0);
    }

    public static void vertexAttribPointer(int index, int size, int type)
    {
        vertexAttribPointer(index, size, type, false, 0, 0);
    }

    public static void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointerOffset)
    {
        glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
    }

    public static void enableAttribArray(int ... attribArray)
    {
        for (int value : attribArray) glEnableVertexAttribArray(value);
    }

    public static void disableAttribArray(int ... attribArray)
    {
        for (int value : attribArray) glDisableVertexAttribArray(value);
    }

    public static void cleanup()
    {
        vaos.forEach(GL30::glDeleteVertexArrays);
    }

}