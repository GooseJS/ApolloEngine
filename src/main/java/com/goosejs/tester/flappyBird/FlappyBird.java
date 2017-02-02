package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class FlappyBird extends LoopingApplicationBase
{
    public static void main(String args[])
    {
        FlappyBird flappy = new FlappyBird();
        flappy.setApplicationLoop(new DefaultApplicationLoop());
        flappy.setupApplicationLoop(flappy);
    }

    private Window window;
    private Bird bird;
    private Pipe pipe;

    private SpriteBatch batch;

    @Override
    public boolean init()
    {
        window = new Window(800, 600, "FlappyBird", false, false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        bird = new Bird();
        pipe = new Pipe();

        batch = new SpriteBatch();

        bird.init();
        //pipe.init();

        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        bird.draw(batch);
        bird.update();
        if (window.getKeyboardCallback().isKeyJustDown(GLFW.GLFW_KEY_SPACE))
            bird.flap();

        batch.flushQueue();

        if (!window.update())
            getApplicationLoop().stopLoop();
    }
}
