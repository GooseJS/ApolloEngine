package com.goosejs.tester;

import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedRenderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TexturedRendererTester
{

    private Window window;

    public static void main(String args[])
    {
        new TexturedRendererTester().start();
    }

    public void start()
    {
        window = new Window(800, 600, "TexturedRendererTest", false, false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        Texture texture = new Texture("texture.png");

        TexturedRenderer texturedRenderer = new TexturedRenderer();
        TexturedPrimitive2D texturedPrimitive = new TexturedPrimitive2D(texture, 100, 100);

        float renderX = 0;
        float renderY = 0;

        while (!window.shouldClose())
        {
            GL11.glClearColor(1, 0, 0, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            texturedRenderer.drawTexture(texturedPrimitive, renderX, renderY);

            if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_W))
                renderY--;
            if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_S))
                renderY++;
            if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_A))
                renderX--;
            if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_D))
                renderX++;

            window.pollEvents();
            window.swapBuffers();
        }

        window.destroyWindow();
    }

}