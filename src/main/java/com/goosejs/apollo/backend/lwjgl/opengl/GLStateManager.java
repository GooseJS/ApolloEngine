package com.goosejs.apollo.backend.lwjgl.opengl;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.Stack;

public class GLStateManager
{

    private static Stack<GLState> stateStack;

    static
    {
        stateStack = new Stack<>();
        stateStack.push(new GLState());
    }

    public static void initializeOpenGL()
    {
        GL.createCapabilities();
    }

    public static void pushState()
    {
        stateStack.push(new GLState(stateStack.peek()));
    }

    public static void popState()
    {
        stateStack.pop();
        stateStack.peek().resetSubStates();
    }

    //region Culling

    public static void enableCulling()
    {
        stateStack.peek().enableCulling();
    }

    public static void setCullType(int cullType)
    {
        stateStack.peek().setCullType(cullType);
    }

    public static void disableCulling()
    {
        stateStack.peek().disableCulling();
    }

    public static boolean isCullingEnabled()
    {
        return stateStack.peek().isCullingEnabled();
    }

    public static int getCullType()
    {
        return stateStack.peek().getCullType();
    }

    //endregion

    //region Depth

    public static void enableDepth()
    {
        stateStack.peek().enableDepth();
    }

    public static void disableDepth()
    {
        stateStack.peek().disableDepth();
    }

    public static boolean isDepthEnabled()
    {
        return stateStack.peek().isDepthEnabled();
    }

    //endregion

    //region Blending

    public static void enableBlending()
    {
        stateStack.peek().enableBlending();
    }

    public static void setBlendFunc(int sFactor, int dFactor)
    {
        stateStack.peek().setBlendFunc(sFactor, dFactor);
    }

    public static void disableBlending()
    {
        stateStack.peek().disableBlending();
    }

    public static boolean isBlendingEnabled()
    {
        return stateStack.peek().isBlendingEnabled();
    }

    public static int getBlendingSFactor()
    {
        return stateStack.peek().getBlendingSFactor();
    }

    public static int getBlendingDFactor()
    {
        return stateStack.peek().getBlendingDFactor();
    }

    //endregion

    //region Lighting

    public static void enableLighting()
    {
        stateStack.peek().enableLighting();
    }

    public static void disableLighting()
    {
        stateStack.peek().disableLighting();
    }

    public static boolean isLightingEnabled()
    {
        return stateStack.peek().isLightingEnabled();
    }

    //endregion

    //region Color

    public static void glClearColor(float r, float g, float b, float a)
    {
        stateStack.peek().setClearColor(r, g, b, a);
    }

    // TODO: Create color getters

    //endregion

    //region Utility

    public static void glClear(int bit)
    {
        GL11.glClear(bit);
    }

    //endregion

}