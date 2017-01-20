package com.goosejs.tester;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.ApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableCursorPosCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.entity.subsystems.EntitySubSystemPosition;
import com.goosejs.apollo.util.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class EntityTester extends LoopingApplicationBase
{

    private Window window;
    private TrueTypeFontRenderer fontRenderer;

    public static void main(String args[])
    {
        new EntityTester().startApp();
    }

    public void startApp()
    {
        setApplicationLoop(new ApplicationLoop());
        setupApplicationLoop(this);
    }

    private TestEntity testEntity;
    private TestEntity testEntity2;

    private int delayTimer = 100;

    @Override
    public void runApplicationLoop()
    {
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        fontRenderer.drawString(10, 10, String.format("Entity Position - X: %.3f, Y: %.3f, Z: %.3f", testEntity.getX(), testEntity.getY(), testEntity.getZ()));
        if (testEntity2 != null)
        {
            fontRenderer.drawString(10, 30, String.format("Entity 2 Position - X: %.3f, Y: %.3f, Z: %.3f", testEntity2.getX(), testEntity2.getY(), testEntity2.getZ()));
            if (delayTimer < 0)
            {
                //testEntity2.setX((float) window.getCursorPosCallback().getMouseY());
                //testEntity2.setY((float) window.getCursorPosCallback().getMouseX());
            }
            else
                delayTimer--;
        }

        testEntity.setX((float)window.getCursorPosCallback().getMouseX());
        testEntity.setY((float)window.getCursorPosCallback().getMouseY());

        if (window.getKeyboardCallback().isKeyJustDown(GLFW.GLFW_KEY_SPACE))
            testEntity2 = (TestEntity) testEntity.instantiate();

        window.swapBuffers();
        window.pollEvents();

        if (window.shouldClose())
            getApplicationLoop().stopLoop();
    }

    @Override
    public boolean preInit()
    {
        Logger.info("Pre-Init");

        window = new Window(800, 600, "gEngine", false, false);
        window.createWindow();
        window.setCursorPosCallback(new ExtendableCursorPosCallback());
        window.setKeyboardCallback(new ExtendableKeyboardCallback());

        return true;
    }

    @Override
    public boolean init()
    {
        Logger.info("Init");

        testEntity = new TestEntity();

        return true;
    }

    @Override
    public boolean postInit()
    {
        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 25f, 0f, 0, 0, window.getWidth(), window.getHeight());
        fontRenderer.resetTranslation();

        return true;
    }
}