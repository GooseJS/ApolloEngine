package com.goosejs.tester.mmoTest.states;

import com.goosejs.apollo.state.State;
import com.goosejs.apollo.util.Logger;
import com.goosejs.tester.mmoTest.MMO;
import org.lwjgl.opengl.GL11;

public class MMOStateMainMenu extends State
{

    private MMO instance;

    public MMOStateMainMenu(MMO mmo)
    {
        this.instance = mmo;
    }

    @Override
    public void update()
    {
        Logger.info("It works!");
    }

    @Override
    public void draw()
    {
        GL11.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
}
