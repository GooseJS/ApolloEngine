package com.goosejs.apollo.application;

import com.goosejs.apollo.application.applicationLoop.IApplicationLoop;
import com.goosejs.apollo.state.StateManager;

/**
 * An {@link ApplicationBase} that handles the game loop for you so you don't have to.
 * This will also simplify
 */
public abstract class LoopingApplicationBase extends ApplicationBase
{
    /** The application loop that this application will use */
    private IApplicationLoop applicationLoop;

    /** If a state manager is used, this will be it */
    private StateManager stateManager;

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
        if (stateManager != null) getApplicationLoop().useStateManager(stateManager);
        getApplicationLoop().startLoop();
    }

    /**
     * Will make the LoopingApplicationBase use a state manager
     * @param stateManager the state manager to use
     */
    protected final void useStateManager(StateManager stateManager)
    {
        this.stateManager = stateManager;
    }

    /**
     * @see #stateManager
     */
    protected final StateManager getStateManager()
    {
        return this.stateManager;
    }



}
