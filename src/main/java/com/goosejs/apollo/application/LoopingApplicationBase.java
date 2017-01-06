package com.goosejs.apollo.application;

import com.goosejs.apollo.application.applicationLoop.IApplicationLoop;

/**
 * An {@link ApplicationBase} that handles the game loop for you so you don't have to.
 * This will also simplify
 */
public abstract class LoopingApplicationBase extends ApplicationBase
{
    /** The application loop that this application will use */
    private IApplicationLoop applicationLoop;

    /** Used to start the application loop */
    public abstract void runApplicationLoop();

    /** This will set the application loop to be used */
    protected final void setApplicationLoop(IApplicationLoop applicationLoop)
    {
        this.applicationLoop = applicationLoop;
    }

    /** Will return the application loop that is currently being used for this application */
    protected final IApplicationLoop getApplicationLoop()
    {
        return this.applicationLoop;
    }

    /** Will setup the application loop to be used. Can be implemented manually */
    protected final void setupApplicationLoop(LoopingApplicationBase application)
    {
        getApplicationLoop().setApplicationBase(application);
        getApplicationLoop().startLoop();
    }

}
