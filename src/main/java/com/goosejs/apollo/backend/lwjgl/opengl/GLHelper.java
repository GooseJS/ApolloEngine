package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL11;

public class GLHelper
{

    private GLHelper() {}

    public static void drawArrays(int mode, int first, int count)
    {
        GL11.glDrawArrays(mode, first, count);
    }

    public static void drawElements(int mode, int count, int type, int indicesOffset)
    {
        GL11.glDrawElements(mode, count, type, indicesOffset);
    }

}