package com.goosejs.tester.BrickBreak;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class BrickBreak extends LoopingApplicationBase
{
    private Window window;
    private Paddle Paddle;
    private Ball ball;
    private SpriteBatch batch;

    private boolean lost = false;

    private TrueTypeFontRenderer fontRenderer;

    public static void main(String[] args)
    {
        BrickBreak brickBreak = new BrickBreak();
        brickBreak.setApplicationLoop(new DefaultApplicationLoop());
        brickBreak.setupApplicationLoop(brickBreak);
    }

    @Override
    public boolean init()
    {
        window = new Window(1200,700,"brick break",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        Paddle = new Paddle(20);
        ball = new Ball(600,350,0,-5);

        batch  = new SpriteBatch();
        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        if (!lost)
            playGame();

        batch.flushQueue();


        if (!window.update())
            getApplicationLoop().stopLoop();
    }

    private void playGame()
    {
        Paddle.draw(batch);
        ball.draw(batch);
        ball.update();
        ball.checkcollision(Paddle);

        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_RIGHT))
            Paddle.moveRight();
        else if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_LEFT))
            Paddle.moveLeft();
    }


}
