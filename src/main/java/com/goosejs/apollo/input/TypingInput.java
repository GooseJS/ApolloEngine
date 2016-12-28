//package com.goosejs.apollo.input;
//
//import java.util.ArrayList;
//
//public class TypingInput
//{
//    private ArrayList<String> lines;
//
//    private int cursorX;
//    private int cursorY;
//
//    public TypingInput()
//    {
//        lines = new ArrayList<>();
//        lines.add("");
//        cursorX = 0;
//        cursorY = 0;
//    }
//
//    public int getCursorX()
//    {
//        return cursorX;
//    }
//
//    public int getCursorY()
//    {
//        return cursorY;
//    }
//
//    public void setCursorX(int cursorX)
//    {
//        this.cursorX = cursorX;
//    }
//
//    public void setCursorY(int cursorY)
//    {
//        this.cursorY = cursorY;
//    }
//
//    public void setCursorXToEndOfLine()
//    {
//        if (lines.get(cursorY) != null)
//            cursorX = lines.get(cursorY).length();
//    }
//
//    public ArrayList<String> getLines()
//    {
//        return lines;
//    }
//
//    public void setCurrentLine(String newLine)
//    {
//        setLine(cursorY, newLine);
//    }
//
//    public void setLine(int index, String newLine)
//    {
//        if (index <= lines.size())
//        {
//            getLines().set(index, newLine);
//        }
//        else
//        {
//            // TODO: Exception handling / logging
//        }
//    }
//
//    public String getCurrentLine()
//    {
//        return getLines().get(cursorY);
//    }
//
//    public void addLine()
//    {
//        addLine("");
//    }
//
//    public void addLine(int index)
//    {
//        addLine(index, "");
//    }
//
//    public void addLine(String newLine)
//    {
//        getLines().add(newLine);
//    }
//
//    public void addLine(int index, String newLine)
//    {
//        getLines().add(index, newLine);
//    }
//
//    public void addLineAtCursor()
//    {
//        addLineAtCursor("");
//    }
//
//    public void addLineAtCursor(String newLine)
//    {
//        getLines().add(cursorY, newLine);
//    }
//
//    public interface OnKeyTyped
//    {
//        void invoke(String currentLine, InputFieldContainer editor);
//    }
//
//}