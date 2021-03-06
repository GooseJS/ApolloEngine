package com.goosejs.tester.Pong;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Pong extends LoopingApplicationBase
{
    private Window window;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;
    private SpriteBatch batch;

    private boolean lost = false;

    private TrueTypeFontRenderer fontRenderer;

    public static void main(String[] args)
    {
        Pong pong = new Pong();
        pong.setApplicationLoop(new DefaultApplicationLoop());
        pong.setupApplicationLoop(pong);
    }

    @Override
    public boolean init()
    {
        window = new Window(1200,700,"pong",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        leftPaddle = new Paddle(20);
        rightPaddle = new Paddle(1155);
        ball = new Ball(600,350,-10,0);


        batch  = new SpriteBatch();
        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        if (!ball.getGameOver())
            playGame();


        batch.flushQueue();

        if (!window.update())
            getApplicationLoop().stopLoop();
    }

    private void playGame()
    {
        leftPaddle.draw(batch);
        rightPaddle.draw(batch);
        ball.draw(batch);
        ball.update();

        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_W))
            leftPaddle.moveUp();
        else if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_S))
            leftPaddle.moveDown();

        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_UP))
            rightPaddle.moveUp();
        else if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_DOWN))
            rightPaddle.moveDown();
    }


}
