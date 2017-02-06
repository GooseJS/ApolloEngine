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
    private BrickBreakPaddle BrickBreakPaddle;
    private BrickBreakBall BrickBreakBall;
    private SpriteBatch batch;

    private boolean lost = false;

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
        window = new Window(1200,700,"brick break",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        BrickBreakPaddle = new BrickBreakPaddle(20, fontRenderer);
        BrickBreakBall = new BrickBreakBall(600,350,0,-5);

        batch  = new SpriteBatch();

        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 50f, window);
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
        BrickBreakPaddle.draw(batch);
        BrickBreakBall.draw(batch);
        BrickBreakBall.update();
        BrickBreakBall.checkcollision(BrickBreakPaddle);

        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_RIGHT))
            BrickBreakPaddle.moveRight();
        if (window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_LEFT))
            BrickBreakPaddle.moveLeft();
    }


}
