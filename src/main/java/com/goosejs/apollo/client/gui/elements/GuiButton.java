//package com.goosejs.apollo.client.gui.elements;
//
//import com.goosejs.apollo.client.gui.GuiElement;
//import com.goosejs.apollo.client.gui.elements.subelements.GuiString;
//import com.goosejs.apollo.renderer.glRendering.Tessellation;
//import com.goosejs.apollo.util.interfaces.IInvokable;
//
//public class GuiButton extends GuiElement
//{
//    private GuiString text;
//
//    private float backgroundR;
//    private float backgroundG;
//    private float backgroundB;
//
//    private float hoverR;
//    private float hoverG;
//    private float hoverB;
//
//    private boolean outline;
//
//    private boolean hovering;
//
//    private IInvokable onClick;
//
//    public GuiButton(IInvokable onClick, int x, int y, int width, int height)
//    {
//        this("", onClick, x, y, width, height);
//    }
//
//    public GuiButton(String text, IInvokable onClick, int x, int y, int width, int height)
//    {
//        this(new GuiString(text), onClick, x, y, width, height);
//    }
//
//    public GuiButton(String text, int x, int y, int width, int height)
//    {
//        this(new GuiString(text), null, x, y, width, height);
//    }
//
//    public GuiButton(GuiString text, IInvokable onClick, int x, int y, int width, int height)
//    {
//        super(x, y, width, height);
//        this.text = text;
//        this.onClick = onClick;
//    }
//
//    public void setOnClick(IInvokable onClick)
//    {
//        this.onClick = onClick;
//    }
//
//    public void setBackgroundColor(float r, float g, float b)
//    {
//        backgroundR = r;
//        backgroundG = g;
//        backgroundB = b;
//    }
//
//    public void setHoverColor(float r, float g, float b)
//    {
//        hoverR = r;
//        hoverG = g;
//        hoverB = b;
//    }
//
//    public void drawOutline(boolean outline)
//    {
//        this.outline = outline;
//    }
//
//    public GuiString getText()
//    {
//        return this.text;
//    }
//
//    @Override
//    public void onDraw()
//    {
//        if (hovering)
//            Tessellation.drawRect(1, 1, getWidth(), getHeight() - 1, hoverR, hoverG, hoverB, 1.0f);
//        else
//            Tessellation.drawRect(1, 1, getWidth(), getHeight() - 1, backgroundR, backgroundG, backgroundB, 1.0f);
//        if (outline) Tessellation.drawLineLoop(1, 1, getWidth(), getHeight() - 1, 1.0f, 1.0f, 1.0f, 1.0f);
//        getFontRenderer().drawString(((getWidth() / 2) - (getFontRenderer().getStringWidth(text.toString()) / 2)), ((getHeight() / 2) - (getFontRenderer().getFontHeight() / 2)), text.toString());
//    }
//
//    @Override
//    public void onMouseMove(double mouseX, double mouseY)
//    {
//        hovering = isHovering(mouseX, mouseY);
//    }
//
//    @Override
//    public void onMouseButtonPressWhileOver(int button, double mouseX, double mouseY)
//    {
//        onClick.invoke();
//    }
//}
