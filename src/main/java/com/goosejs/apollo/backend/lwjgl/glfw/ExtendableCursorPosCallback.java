package com.goosejs.apollo.backend.lwjgl.glfw;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExtendableCursorPosCallback extends GLFWCursorPosCallback
{

    private double mouseX;
    private double mouseY;

    private double lastMouseX;
    private double lastMouseY;

    private List<CursorPosCallback> cursorPosCallbacks = new CopyOnWriteArrayList<>();

    @Override
    public void invoke(long window, double xpos, double ypos)
    {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        mouseX = xpos;
        mouseY = ypos;

        cursorPosCallbacks.forEach(cursorPosCallback -> cursorPosCallback.invoke(window, xpos, ypos));
    }

    public void addCursorPosCallback(CursorPosCallback callback)
    {
        cursorPosCallbacks.add(callback);
    }

    public boolean removeCursorPosCallbacK(CursorPosCallback callback)
    {
        return cursorPosCallbacks.remove(callback);
    }

    public double getMouseX()
    {
        return mouseX;
    }

    public double getMouseY()
    {
        return mouseY;
    }

    public double getDeltaX()
    {
        double oldLast = lastMouseX;
        lastMouseX = mouseX;
        return mouseX - oldLast;
    }

    public double getDeltaY()
    {
        double oldLast = lastMouseY;
        lastMouseY = mouseY;
        return mouseY - oldLast;
    }

}