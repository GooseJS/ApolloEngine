package util;

import com.goosejs.gchat.util.interfaces.IInvokable;

import java.util.ArrayList;

public class Timer
{

    private ArrayList<IInvokable> IInvokables;
    private long lastTime;
    private int timerTime;
    private boolean running = false;

    public Timer(IInvokable IInvokable, float delayInSeconds)
    {
        ArrayList<IInvokable> IInvokables = new ArrayList<>();
        IInvokables.add(IInvokable);
        this.IInvokables = IInvokables;
        this.timerTime = (int)(delayInSeconds * 1000000000);
    }

    public Timer(ArrayList<IInvokable> IInvokables, float delayInSeconds)
    {
        this.IInvokables = IInvokables;
        this.timerTime = (int)(delayInSeconds * 1000000000);
    }

    public void start()
    {
        running = true;
        lastTime = System.nanoTime();
    }

    public void pause()
    {
        running = false;
    }

    public boolean isPaused()
    {
        return !running;
    }

    public void update()
    {
        if (running)
        {
            if ((System.nanoTime() - lastTime) > timerTime)
            {
                IInvokables.forEach(IInvokable::invoke);
                lastTime = System.nanoTime();
            }
        }
    }

}
