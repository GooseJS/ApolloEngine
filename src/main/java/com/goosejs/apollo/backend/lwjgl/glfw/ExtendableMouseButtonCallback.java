package com.goosejs.apollo.backend.lwjgl.glfw;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

public class ExtendableMouseButtonCallback extends GLFWMouseButtonCallback
{

    /**
     * TO-DO List:
     * TODO: Add window checking support
     * TODO: Add modifier support
     * TODO: Make the callback modular (allow support for extensions)
     */

    private List<MouseButtonCallback> mouseButtonCallbacks = new CopyOnWriteArrayList<>();

    private boolean[] mouseButtons = new boolean[16];
    private boolean[] repeatButtons = new boolean[16];

    @Override
    public void invoke(long window, int button, int action, int mods)
    {
        mouseButtons[button] = action != GLFW_RELEASE;
        repeatButtons[button] = action != GLFW_RELEASE && action != GLFW_REPEAT;

        mouseButtonCallbacks.forEach(mouseButtonCallback -> mouseButtonCallback.invoke(window, button, action, mods));
    }

    public void addMouseButtonCallback(MouseButtonCallback callback)
    {
        mouseButtonCallbacks.add(callback);
    }

    public boolean removeMouseButtonCallback(MouseButtonCallback callback)
    {
        return mouseButtonCallbacks.remove(callback);
    }

    public boolean isButtonDown(int button)
    {
        return button < mouseButtons.length && mouseButtons[button];
    }

    public boolean isButtonJustDown(int button)
    {
        if (button < repeatButtons.length)
            return repeatButtons[button];
        return false;
    }

}