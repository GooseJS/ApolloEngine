package com.goosejs.tester.brickBreak;

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
    private BrickBreakPaddle brickBreakPaddle;
    private BrickBreakBall brickBreakBall;
    private SpriteBatch batch;
    Brick[] bricks;

    private TrueTypeFontRenderer fontRenderer;

    public static void main(String[] args)
    {
        BrickBreak BrickBreak = new BrickBreak();
        BrickBreak.setApplicationLoop(new DefaultApplicationLoop());
        BrickBreak.setupApplicationLoop(BrickBreak);
    }

    @Override
    public boolean init()
    {
        window = new Window(1200,700,"Brick break",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        brickBreakPaddle = new BrickBreakPaddle(20);
        brickBreakBall = new BrickBreakBall(600,150,0,-5);

        bricks = new Brick[30];
        for(int i = 0; i < bricks.length; i++)
        {
            bricks[i] = new Brick((20+(i*120)),400f, 100f, 20f);
            bricks[i] = new Brick((20+(i*120)),450f, 100f, 20f);
            bricks[i] = new Brick((20+(i*120)),500f, 100f, 20f);
        }

        batch  = new SpriteBatch();

        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 50f, window);
        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        playGame();

        batch.flushQueue();

        if (!window.update())
            getApplicationLoop().stopLoop();
    }

    private void playGame()
    {

        brickBreakPaddle.draw(batch);
        brickBreakBall.draw(batch);

        for(int i = 0; i < bricks.length; i++)
        {
            bricks[i].draw(batch);
        }
        brickBreakBall.update();
        brickBreakBall.checkCollisionBrick(bricks);
        brickBreakBall.checkCollisionPaddle(brickBreakPaddle);


        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_RIGHT))
            brickBreakPaddle.moveRight();
        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_LEFT))
            brickBreakPaddle.moveLeft();
    }


}
