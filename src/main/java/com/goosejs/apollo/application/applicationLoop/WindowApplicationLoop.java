package com.goosejs.apollo.application.applicationLoop;

import com.goosejs.apollo.application.ApplicationInitializer;
import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.backend.lwjgl.glfw.*;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.state.StateManager;
import com.goosejs.apollo.util.Logger;

public class WindowApplicationLoop implements IApplicationLoop
{
    private LoopingApplicationBase applicationBase;

    private StateManager stateManager;

    private boolean running;
    private boolean usingStateManager;

    private Window window;

    public WindowApplicationLoop(int windowWidth, int windowHeight, String windowTitle , boolean fullscreen, boolean resizable)
    {
        window = new Window(windowWidth, windowHeight, windowTitle, fullscreen, resizable);
    }

    @Override
    public void setApplicationBase(LoopingApplicationBase applicationBase)
    {
        this.applicationBase = applicationBase;
    }

    @Override
    public void startLoop()
    {
        if (applicationBase == null)
        {
            Logger.fatal("Cannot start application loop on nothing.");
            Logger.printStackTrace();
            return;
        }

        running = true;

        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());
        window.setCharacterCallback(new ExtendableCharacterCallback());
        window.setMouseButtonCallback(new ExtendableMouseButtonCallback());
        window.setCursorPosCallback(new ExtendableCursorPosCallback());

        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);

        ApplicationInitializer.initApplicationAndPlugins(applicationBase);

        Logger.info("Starting application loop.");
        while (running)
        {
            applicationBase.runApplicationLoop();
            if (usingStateManager)
            {
                stateManager.update(); // TODO: Look into splitting these up / also doing something with the ApplicationBase
                stateManager.draw();
            }

            if (window.shouldClose())
                stopLoop();

            window.pollEvents();
            window.swapBuffers();
        }
        if (usingStateManager)
            stateManager.shutdown();
        Logger.info("Stopping application loop.");
    }

    @Override
    public void stopLoop()
    {
        running = false;
    }

    @Override
    public double getDeltaTime()
    {
        return 0;
    }

    @Override
    public void useStateManager(StateManager stateManager)
    {
        usingStateManager = true;
        this.stateManager = stateManager;
    }

    public Window getWindow()
    {
        return this.window;
    }

}