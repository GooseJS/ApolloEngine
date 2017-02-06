package com.goosejs.apollo.state;

public abstract class State
{

    private StateManager stateManager;

    public final void initialize()
    {
        onInitialize();
    }

    public final void activate()
    {
        onActivated();
    }

    public final void deactivate()
    {
        onDeactivated();
    }

    public final void destroy()
    {
        onDestroyed();
    }

    public final void shutdown() { onShutdown(); }

    public void onInitialize() {}
    public void onActivated() {}
    public void onDeactivated() {}
    public void onDestroyed() {}
    public void onShutdown() {}

    public abstract void update();
    public abstract void draw();

    public void usingStateManager(StateManager stateManager)
    {
        this.stateManager = stateManager;
    }

    protected State setState(State state)
    {
        if (checkStateManager())
        {
            State poppedState = popState();
            pushState(state);
            return poppedState;
        }

        return null;
    }

    protected void pushState(State state)
    {
        if (checkStateManager()) stateManager.pushState(state);
    }

    protected State popState()
    {
        if (checkStateManager()) return stateManager.popState();
        return null;
    }

    private boolean checkStateManager()
    {
        if (stateManager != null) return true;
        throw new RuntimeException("Cannot use StateManager utility functions without first calling State.usingStateManager!");
    }
}
