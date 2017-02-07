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
        GuiButton mainMenuButton = new GuiButton("Start Game", () -> Logger.info("FAGGOT"), 10, 10, 500, 0);
        //mainMenuButton.setFontScale(40f);
        mainMenuButton.setHeight(Math.round(GuiElement.getFontRenderer().getFontHeight() + 10));
        guiManager.addGuiElement(mainMenuButton);
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
