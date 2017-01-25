package com.goosejs.tester.stupidEasy;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.tester.stupidEasy.renderEngine.Loader;
import com.goosejs.tester.stupidEasy.renderEngine.RawModel;
import com.goosejs.tester.stupidEasy.renderEngine.Renderer;
import org.lwjgl.opengl.GL11;

public class Tester
{

    private Window window;

    public static void main(String args[])
    {
        new Tester().run();
    }

    Loader loader = new Loader();
    Renderer renderer = new Renderer();

    public void run()
    {
        window = new Window(800, 640, "gEngine", false, false);
        window.createWindow();

        GL11.glViewport(0, 0, window.getWidth(), window.getHeight());

        float[] vertices = new float[] {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };

        int[] indices = new int[] {
                0, 1, 3,
                3, 1, 2
        };

        RawModel model = loader.loadToVAO(vertices, indices);

        while (!window.shouldClose())
        {
            renderer.prepare();

            renderer.render(model);

            window.pollEvents();
            window.swapBuffers();
        }

        window.destroyWindow();
        loader.cleanup();
    }

}