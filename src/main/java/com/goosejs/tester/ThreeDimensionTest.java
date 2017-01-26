package com.goosejs.tester;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;

public class ThreeDimensionTest
{

    public static void main(String args[])
    {
        new ThreeDimensionTest().run();
    }

    public void run()
    {
        Window window = new Window(800, 600, "3D Testing", false, false);
        window.createWindow();

        while (!window.shouldClose())
        {
            window.pollEvents();
            window.swapBuffers();
        }

        window.destroyWindow();
    }

}