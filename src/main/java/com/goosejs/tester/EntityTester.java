package com.goosejs.tester;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.ApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableCursorPosCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.entity.subsystems.EntitySubSystemPosition;
import com.goosejs.apollo.util.Logger;
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
    private EntitySubSystemPosition testEntityPosition;

    @Override
    public void runApplicationLoop()
    {
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        fontRenderer.drawString(10, 10, String.format("Entity Position - X: %.3f, Y: %.3f, Z: %.3f", testEntity.getX(), testEntity.getY(), testEntity.getZ()));
        fontRenderer.drawString(10, 30, String.format("Live Subsystem Position - X: %.3f, Y: %.3f, Z: %.3f", ((EntitySubSystemPosition) testEntity.getSubsystem("Position")).getX(), ((EntitySubSystemPosition) testEntity.getSubsystem("Position")).getY(), ((EntitySubSystemPosition) testEntity.getSubsystem("Position")).getZ()));
        fontRenderer.drawString(10, 50, String.format("Cached Subsystem Position - X: %.3f, Y: %.3f, Z: %.3f", testEntityPosition.getX(), testEntityPosition.getY(), testEntityPosition.getZ()));

        testEntity.setX((float)window.getCursorPosCallback().getMouseX());
        testEntity.setY((float)window.getCursorPosCallback().getMouseY());

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

        return true;
    }

    @Override
    public boolean init()
    {
        Logger.info("Init");

        testEntity = new TestEntity();
        testEntityPosition = (EntitySubSystemPosition) testEntity.getSubsystem("Position");
        if (testEntityPosition == null) throw new RuntimeException("Fuck");

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