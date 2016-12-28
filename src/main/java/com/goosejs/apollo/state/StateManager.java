package com.goosejs.apollo.state;

import java.util.Stack;

public class StateManager
{
    private Stack<State> states;

    public StateManager()
    {
        this.states = new Stack<>();
    }

    public void update()
    {
        if (!states.isEmpty()) states.peek().update();
    }

    public void draw()
    {
        if (!states.isEmpty()) states.peek().draw();
    }

    public void shutdown()
    {
        if (!states.isEmpty()) states.peek().shutdown();
    }

    public void pushState(State state)
    {
        if (!states.isEmpty())
            states.peek().deactivate();
        states.push(state);
        states.peek().initialize();
        states.peek().activate();
    }

    public State popState()
    {
        if (!states.isEmpty())
        {
            states.peek().deactivate();
            states.peek().destroy();
            State returnState = states.pop();
            if (!states.isEmpty()) states.peek().activate();
            return returnState;
        }
        return null;
    }

    public State setState(State state)
    {
        State currentState = popState();
        pushState(state);
        return currentState;
    }

    public State getActiveState()
    {
        return states.peek();
    }
}