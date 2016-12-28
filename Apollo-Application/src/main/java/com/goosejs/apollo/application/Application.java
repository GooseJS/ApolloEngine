package com.goosejs.apollo.application;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;

public class Application
{

    private Window window;

    public void openWindow()
    {
        window = new Window(800, 600, "Loo", false);
        window.createWindow();
    }

    public boolean shouldCloseWindow()
    {
        return window.shouldClose();
    }

    public void doShit()
    {
        window.pollEvents();
        window.swapBuffers();
    }

    public void shutdown()
    {
        window.destroyWindow();
    }


}