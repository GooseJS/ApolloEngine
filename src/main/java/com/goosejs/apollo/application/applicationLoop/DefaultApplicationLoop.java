package com.goosejs.apollo.application.applicationLoop;

import com.goosejs.apollo.application.ApplicationInitializer;
import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.state.StateManager;
import com.goosejs.apollo.util.Logger;

public class DefaultApplicationLoop implements IApplicationLoop
{
    private LoopingApplicationBase applicationBase;

    private StateManager stateManager;

    private boolean running;
    private boolean usingStateManager;

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
}