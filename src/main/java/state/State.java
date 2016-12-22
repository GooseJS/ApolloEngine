package state;

import com.goosejs.gchat.client.GChat;

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
        return GChat.getInstance().getClient().getStateManager().setState(state);
    }

    public void pushState(State state)
    {
        GChat.getInstance().getClient().getStateManager().pushState(state);
    }

    public State popState()
    {
        return GChat.getInstance().getClient().getStateManager().popState();
    }
}
