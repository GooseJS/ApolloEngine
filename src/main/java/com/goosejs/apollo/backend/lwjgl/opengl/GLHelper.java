package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL11;

/**
 * A simple helper class so you don't have to remember the version
 * number of certain OpenGL features
 */
public class GLHelper
{

    /** Private constructor to ensure this class isn't instantiated */
    private GLHelper() {}

    /**
     * @see GL11#glDrawArrays(int, int, int)
     */
    public static void drawArrays(int mode, int first, int count)
    {
        GL11.glDrawArrays(mode, first, count);
    }

    /**
     * @see GL11#glDrawArrays(int, int, int)
     */
    public static void drawElements(int mode, int count, int type, int indicesOffset)
    {
        GL11.glDrawElements(mode, count, type, indicesOffset);
    }

}