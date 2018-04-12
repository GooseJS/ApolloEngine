package com.goosejs.tester.mmoTest.states;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.gui.GuiElement;
import com.goosejs.apollo.client.gui.GuiManager;
import com.goosejs.apollo.client.gui.elements.GuiButton;
import com.goosejs.apollo.state.State;
import com.goosejs.apollo.util.Logger;
import com.goosejs.apollo.util.interfaces.IInvokable;
import com.goosejs.tester.mmoTest.MMO;
import org.lwjgl.opengl.GL11;

public class MMOStateMainMenu extends State
{

    private MMO instance;
    private GuiManager guiManager;

    public MMOStateMainMenu(MMO mmo)
    {
        this.instance = mmo;
    }

    @Override
    public void onInitialize()
    {
        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(Window.getWindow());

        this.guiManager = new GuiManager(Window.getWindow());
        GuiButton mainMenuButton = new GuiButton("Start redHarvest", () -> Logger.info("MainMenu"), (int)(((float)Window.getWindow().getWidth() / 2f) - (500f / 2f)), 200, 500, 60);
        mainMenuButton.setFontScale(30f);
        mainMenuButton.setBackgroundColor(0.4f, 0.4f, 0.4f);
        mainMenuButton.setHoverColor(0.6f, 0.6f, 0.6f);
        GuiButton optionsButton = new GuiButton("Options", () -> Logger.info("Options"), 10, 400, 500, 60);
        optionsButton.setBackgroundColor(0.4f, 0.4f, 0.4f);
        optionsButton.setHoverColor(0.6f, 0.6f, 0.6f);
        guiManager.addGuiElement(mainMenuButton);
        guiManager.addGuiElement(optionsButton);
    }

    @Override
    public void update()
    {
        guiManager.update();
    }

    @Override
    public void draw()
    {
        GL11.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        guiManager.draw();
    }
}
