//package com.goosejs.apollo.client.gui.elements;
//
//import com.goosejs.apollo.client.gui.GuiElement;
//import com.goosejs.apollo.client.gui.elements.subelements.GuiString;
//
//public class GuiLabel extends GuiElement
//{
//    private GuiString text;
//
//    public GuiLabel(String string, int x, int y)
//    {
//        this(new GuiString(string), x, y);
//    }
//
//    public GuiLabel(GuiString guiString, int x, int y)
//    {
//        super(x, y, 0, 0);
//        this.text = guiString;
//        setWidth((int)Math.ceil(getFontRenderer().getStringWidth(text.toString())));
//        setHeight((int)Math.ceil(getFontRenderer().getFontHeight()));
//    }
//
//    public void setText(String text)
//    {
//        this.text.setText(text);
//        this.setWidth((int)Math.ceil(getFontRenderer().getStringWidth(text)));
//    }
//
//    public String getText()
//    {
//        return this.text.toString();
//    }
//
//    public void setGuiString(GuiString text)
//    {
//        this.text = text;
//    }
//
//    public GuiString getGuiString()
//    {
//        return this.text;
//    }
//
//    @Override
//    public void onDraw()
//    {
//        getFontRenderer().drawString(0, 0, text.toString(), text.getR(), text.getG(), text.getB());
//    }
//}
