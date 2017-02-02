package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.util.Logger;
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

    private TrueTypeFontRenderer fontRenderer;

    private SpriteBatch batch;

    private boolean lost;

    private int score;

    @Override
    public boolean init()
    {
        window = new Window(800, 600, "FlappyBird", false, false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        bird = new Bird();
        pipe = new Pipe();
        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 50f);

        batch = new SpriteBatch();

        bird.init();
        pipe.init();

        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0f, 0f, 0f, 1.0f);

        if (!lost)
            playGame();
        else
            lostGame();

        if (!window.update())
            getApplicationLoop().stopLoop();
    }

    public void lostGame()
    {
        String formatedString = String.format("Final Score: %d", score);
        fontRenderer.drawString((800f / 2f) - (fontRenderer.getStringWidth("You Lost!") / 2f), 200, "You Lost!");
        fontRenderer.drawString((800f / 2f) - (fontRenderer.getStringWidth(formatedString) / 2f), 240, formatedString);
        fontRenderer.drawString((800f / 2f) - (fontRenderer.getStringWidth("Press Space to Restart.") / 2f), 280, "Press Space to Restart.");

        if (window.getKeyboardCallback().isKeyJustDown(GLFW.GLFW_KEY_SPACE))
        {
            score = 0;
            bird.reset();
            pipe.reset(800);
            lost = false;
        }
    }

    public void playGame()
    {
        bird.update();
        bird.draw(batch);
        if (window.getKeyboardCallback().isKeyJustDown(GLFW.GLFW_KEY_SPACE))
            bird.flap();

        pipe.update();
        pipe.draw(batch);
        if (pipe.outsideOfScreen())
            pipe.reset(800);

        if (bird.checkCollision(pipe))
            lost = true;

        if (pipe.hasPassedX(100))
            score++;

        batch.flushQueue();

        fontRenderer.drawString(5, 5, String.format("Score: %d", score));
    }
}
