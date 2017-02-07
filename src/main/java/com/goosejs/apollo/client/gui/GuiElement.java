package com.goosejs.apollo.client.gui;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.glRendering.Tessellator;
import org.lwjgl.opengl.GL11;

public abstract class GuiElement
{

    private static final float DEFAULT_FONT_SCALE = 17.5f;
    private static final TrueTypeFontRenderer FONT_RENDERER;
    private static final String GUI_FONT_FILE = "Roboto-Regular.ttf"; // TODO: Package a font w/ Apollo

    public static final TrueTypeFontRenderer getFontRenderer()
    {
        return FONT_RENDERER;
    }

    static
    {
        FONT_RENDERER = new TrueTypeFontRenderer(GUI_FONT_FILE, DEFAULT_FONT_SCALE, 1f, 0, 0, 400, 600);
        FONT_RENDERER.loadOrthoMatrix(0, 0, 0, 0);
    }

    private int x;
    private int y;
    private int width;
    private int height;

    private String identifier;

    private float fontScale = DEFAULT_FONT_SCALE;

    public GuiElement(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public final void drawStart()
    {
        FONT_RENDERER.loadOrthoMatrix(0, 0, width, height);
        FONT_RENDERER.setFontScale(fontScale);
        Tessellator.getInstance().loadOrthoMatrix(0, 0, width, height);
        GL11.glViewport(x, (Window.getWindow().getHeight() - height - y), width, height); // TODO: Add support for retina (THIS SHOULD BE TIMES 2 FOR RETINA
        onDrawStart();
    }

    public final void draw()
    {
        drawStart();
        onDraw();
        drawEnd();
    }

    public final void drawEnd()
    {
        onDrawEnd();
        resetFontScale();
    }

    public final String getIdentifier()
    {
        return this.identifier;
    }

    public final void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public final void resetFontScale()
    {
        getFontRenderer().setFontScale(DEFAULT_FONT_SCALE);
    }

    public final void setFontScale(float fontScale)
    {
        this.fontScale = fontScale;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void onCreation() {}

    public void onDestruction() {}

    public void onDrawStart() {}

    public abstract void onDraw();

    public void onDrawEnd() {}

    public void onKeyPress(String s, int key, int scancode, boolean repeat) {}

    public void onMouseButtonPress(int button, double mouseX, double mouseY) {}

    public void onMouseButtonPressWhileOver(int button, double mouseX, double mouseY) {}

    public void onMouseHover(double mouseX, double mouseY) {}

    public void onMouseMove(double mouseX, double mouseY) {}

    public void update() {}

    public boolean isHovering(double mouseX, double mouseY)
    {
        return ((getX() < mouseX && (getX() + getWidth()) > mouseX) && (getY() < mouseY && (getY() + getHeight()) > mouseY));
    }
}