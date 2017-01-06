package com.goosejs.apollo.application.applicationLoop;

import com.goosejs.apollo.application.ApplicationBase;
import com.goosejs.apollo.application.ApplicationInitializer;
import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.util.Logger;

public class ApplicationLoop implements IApplicationLoop
{
    private LoopingApplicationBase applicationBase;

    private boolean running;

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
        }
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
}
