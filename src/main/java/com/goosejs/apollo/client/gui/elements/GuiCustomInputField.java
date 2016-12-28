//package com.goosejs.apollo.client.gui.elements;
//
//public class GuiCustomInputField extends GuiInputField
//{
//    private String customText;
//    private OnPressEnter onPressEnter;
//
//    public GuiCustomInputField(String customText, OnPressEnter onPressEnter, int x, int y, int width, int height)
//    {
//        super(x, y, width, height);
//
//        this.customText = customText;
//        this.onPressEnter = onPressEnter;
//        clearText();
//    }
//
//    public void clearText()
//    {
//        editor.getLines().set(0, customText);
//        editor.setCursorXToEndOfLine();
//    }
//
//    @Override
//    public void newLine()
//    {
//        if (onPressEnter.onAction(getUserText())) clearText();
//    }
//
//    @Override
//    public void backspace()
//    {
//        if (editor.getCursorX() > customText.length())
//            super.backspace();
//    }
//
//    @Override
//    public void cursorUp()
//    {
//        editor.setCursorX(customText.length());
//    }
//
//    @Override
//    public void cursorDown()
//    {
//        editor.setCursorXToEndOfLine();
//    }
//
//    @Override
//    public void cursorBack()
//    {
//        if (editor.getCursorX() > customText.length() + 1)
//            super.cursorBack();
//    }
//
//    public String getCustomText()
//    {
//        return this.customText;
//    }
//
//    public String getUserText()
//    {
//        return editor.getCurrentLine().replaceAll(customText, "");
//    }
//
//    public void setCustomText(String customText)
//    {
//        this.customText = customText;
//        clearText();
//    }
//
//    public void setOnPressEnter(OnPressEnter onPressEnter)
//    {
//        this.onPressEnter = onPressEnter;
//    }
//
//    public interface OnPressEnter
//    {
//        /**
//         * Called when enter is pushed on the GuiCustomInputField
//         * @param text the text that the user has com.goosejs.apollo.input
//         * @return true to clear text, false otherwise
//         */
//        boolean onAction(String text);
//    }
//}
