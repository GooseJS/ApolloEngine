package com.goosejs.tester.mmoTest;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.application.applicationLoop.WindowApplicationLoop;
import com.goosejs.apollo.state.StateManager;
import com.goosejs.tester.mmoTest.states.MMOStateMainMenu;

public class MMO extends LoopingApplicationBase
{
    public static void main(String args[])
    {
        new MMO().startGame();
    }

    public void startGame()
    {
        setApplicationLoop(new WindowApplicationLoop(800, 600, "MMO", false, false));
        useStateManager(new StateManager());
        setupApplicationLoop(this);
    }

    @Override
    public boolean postInit()
    {
        getStateManager().pushState(new MMOStateMainMenu(this));
        return true;
    }

    @Override
    public void runApplicationLoop()
    {

    }
}
