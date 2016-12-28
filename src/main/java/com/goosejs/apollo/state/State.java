package com.goosejs.apollo.state;

public abstract class State
{
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

    public State setState(State state)
    {
        // TODO: Implement this
        return null;
    }

    public void pushState(State state)
    {
        // TODO: Implement this
    }

    public State popState()
    {
        //TODO: Implement this
        return null;
    }
}
