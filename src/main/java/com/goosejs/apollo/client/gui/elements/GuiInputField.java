package com.goosejs.apollo.client.gui.elements;

import com.goosejs.apollo.client.gui.GuiElement;
import com.goosejs.apollo.client.renderer.glRendering.Tessellation;
import com.goosejs.apollo.input.TypingInput;
import com.goosejs.apollo.util.Logger;
import com.goosejs.apollo.util.Timer;

import java.util.ArrayList;

public class GuiInputField extends GuiElement
{

    protected TypingInput editor;

    private boolean fieldActive;

    private Timer cursorTimer;
    private boolean cursorBlink;

    private boolean wrapped = false;
    private float wrappedX = 0;
    private float wrappedY = 0;

    // TODO: Link all these fields to a ConfigFile
    private final int xOffset = 6;
    private final int textWrappingPadding = 10;

    private boolean shouldAutoWrap = false;

    public GuiInputField(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        cursorTimer = new Timer(() -> cursorBlink = !cursorBlink, 0.5f);
        cursorTimer.start();
        editor = new TypingInput();
    }

    public void onMouseClick()
    {
        // TODO: Check if mouse clicked on this field, if so, activate it (set field active to true)
    }

    @Override
    public void onDraw()
    {
        renderOutline();
        if (cursorBlink)
            renderCursor();

        float y = 6;

        for (String line : editor.getLines())
        {
            if (getFontRenderer().getStringWidth(line) + xOffset > getWidth() - textWrappingPadding)
            {
                ArrayList<String> displayStrings = new ArrayList<>();
                displayStrings.add(line);
                boolean calculatingString = true;
                int i = 0;
                while (calculatingString)
                {
                    boolean attemptedSpace = false;
                    while (getFontRenderer().getStringWidth(displayStrings.get(i)) + xOffset > getWidth() -
                            textWrappingPadding)
                    {
                        if (!line.contains(" ") || attemptedSpace)
                            displayStrings.set(i, displayStrings.get(i).substring(0, displayStrings.get(i).length() - 1));
                        else
                        {
                            if (displayStrings.get(i).endsWith(" "))
                            {
                                String tempLine = displayStrings.get(i).substring(0, displayStrings.get(i).length() - 1);
                                if (displayStrings.get(i).lastIndexOf(" ") != -1) displayStrings.set(i, displayStrings.get(i).substring(0, tempLine.lastIndexOf(" ") + 1));
                            }
                            else
                                if (displayStrings.get(i).lastIndexOf(" ") != -1) displayStrings.set(i, displayStrings.get(i).substring(0, displayStrings.get(i).lastIndexOf(" ") + 1));

                            if (displayStrings.get(i).lastIndexOf(" ") == -1) attemptedSpace = true;
                        }
                    }
                    int currentLength = 0;
                    for (String value : displayStrings)
                        currentLength += value.length();
                    displayStrings.add(line.substring(currentLength));
                    i++;
                    if (!(getFontRenderer().getStringWidth(displayStrings.get(i)) + xOffset > getWidth() -
                            textWrappingPadding))
                    {
                        calculatingString = false;
                    }
                }
                int currentFullCursorX = 0;
                boolean wrappedSet = false;
                for (int i1 = 0; i1 < displayStrings.size(); i1++)
                {
                    getFontRenderer().drawString(xOffset, y, displayStrings.get(i1));
                    if (!wrappedSet && (displayStrings.get(i1).length()) >= (editor.getCursorX() - currentFullCursorX))
                    {
                        wrappedY = y;
                        wrappedX = getFontRenderer().getStringWidth(displayStrings.get(i1).substring(0, editor.getCursorX() - currentFullCursorX));
                        wrappedSet = true;
                    }
                    y += getFontRenderer().getFontHeight();
                    currentFullCursorX += displayStrings.get(i1).length();
                }

                wrapped = true;
            }
            else
            {
                getFontRenderer().drawString(xOffset, y, line);
                y += getFontRenderer().getFontHeight();

                wrapped = false;
            }

        }

        cursorTimer.update();
    }

    public void renderOutline()
    {
        if (fieldActive)
            Tessellation.drawRectOutline(1, 1, getWidth(), getHeight() - 1, 1, 1, 1, 1);
        else
            Tessellation.drawRectOutline(1, 1, getWidth(), getHeight() - 1, 0.75f, 0.75f, 0.75f, 1.0f);
    }

    private int oldX;
    private int oldY;

    private void renderCursor()
    {
        if (editor.getCursorX() != oldX || editor.getCursorY() != oldY)
        {
            cursorTimer.start();
            cursorBlink = true;
            oldX = editor.getCursorX();
            oldY = editor.getCursorY();
        }

        if (!wrapped)
            getFontRenderer().drawString(cursorPosToStringLength(editor.getCursorX(), editor.getCurrentLine()) + xOffset, (editor.getCursorY() * getFontRenderer().getFontHeight()) + 6, "|");
        else
            getFontRenderer().drawString(wrappedX + xOffset, wrappedY, "|");
    }

    private float cursorPosToStringLength(int cursorX, String string)
    {
        if (cursorX > string.length())
            cursorX = string.length();

        String line = string.substring(0, cursorX);

        float stringWidth = getFontRenderer().getStringWidth(line);

        // TODO: Figure out why spaces are fucking retarded

        return stringWidth;
    }

    public void typeCharacter(String character)
    {
        typeCharacter(character.charAt(0));
    }

    public void typeCharacter(char character)
    {
        if (editor.getCursorX() > editor.getCurrentLine().length())
            editor.setCursorXToEndOfLine();

        if (editor.getCursorX() != editor.getCurrentLine().length())
        {
            String linePartOne = editor.getCurrentLine().substring(0, editor.getCursorX());
            String linePartTwo = editor.getCurrentLine().substring(editor.getCursorX());
            editor.setCurrentLine(linePartOne + character + linePartTwo);
        }
        else
        {
            editor.setCurrentLine(editor.getCurrentLine().concat(String.valueOf(character)));
        }

        int cachedCursorX = editor.getCursorX();
        int cachedCursorY = editor.getCursorY();

        for (int i = 0;  i < editor.getLines().size(); i++)
        {
            if (shouldAutoWrap && cursorPosToStringLength(editor.getCurrentLine().length() + 1, editor.getCurrentLine() + "-") > getWidth())
            {
                editor.setCursorXToEndOfLine();
                editor.setCursorX(editor.getCursorX() - 1);
                while (editor.getCurrentLine().charAt(editor.getCursorX()) != ' ')
                {
                    if (editor.getCursorX() > 0)
                    {
                        editor.setCursorX(editor.getCursorX() - 1);
                        if (editor.getCurrentLine().charAt(editor.getCursorX()) == ' ')
                        {
                            editor.setCursorX(editor.getCursorX() + 1);
                            break;
                        }
                    }
                    else
                    {
                        editor.setCursorXToEndOfLine();
                        break;
                    }
                }

                newLine();
                editor.setCursorXToEndOfLine();
            }
        }

        if (cachedCursorY > editor.getLines().size()) cachedCursorY = editor.getLines().size();
        if (cachedCursorX > editor.getLines().get(cachedCursorY).length()) cachedCursorX = editor.getCurrentLine().length();
        editor.setCursorY(cachedCursorY);
        editor.setCursorX(cachedCursorX);

        cursorForward();
    }

    public void newLine()
    {
        if (editor.getCursorX() == editor.getCurrentLine().length() || editor.getCurrentLine().isEmpty() || editor.getCurrentLine().equals(""))
        {
            editor.setCursorY(editor.getCursorY() + 1);
            editor.addLineAtCursor();
            editor.setCursorX(0);
        }
        else
        {
            String halfLine = editor.getCurrentLine().substring(editor.getCursorX());
            editor.setCurrentLine(editor.getCurrentLine().substring(0, editor.getCursorX()));
            editor.setCursorY(editor.getCursorY() + 1);
            editor.addLineAtCursor(halfLine);
            editor.setCursorX(0);
        }
    }

    public void tab()
    {
        // TODO: Do something with this
    }

    public void backspace()
    {
        if (editor.getCurrentLine().length() > 0)
        {
            if (editor.getCursorX() > 0)
            {
                if (editor.getCursorX() > editor.getCurrentLine().length())
                    editor.setCursorX(editor.getCurrentLine().length());

                String linePartOne = editor.getCurrentLine().substring(0, editor.getCursorX() - 1);
                String linePartTwo = editor.getCurrentLine().substring(editor.getCursorX());
                editor.setCurrentLine(linePartOne + linePartTwo);

                cursorBack();
            }
            else
            {
                if (editor.getCursorY() > 0)
                {
                    int cachedLength = editor.getLines().get(editor.getCursorY() - 1).length();
                    editor.getLines().set(editor.getCursorY() - 1, editor.getLines().get(editor.getCursorY() - 1)
                            .concat(editor.getCurrentLine()));
                    editor.getLines().remove(editor.getCursorY());
                    editor.setCursorY(editor.getCursorY() - 1);
                    editor.setCursorX(cachedLength);
                }
            }
        }
        else
        {
            if (editor.getCursorY() > 0)
            {
                editor.getLines().remove(editor.getCursorY());
                editor.setCursorY(editor.getCursorY() - 1);
                editor.setCursorXToEndOfLine();
            }
        }

        int cachedCursorX = editor.getCursorX();
        int cachedCursorY = editor.getCursorY();

        for (int i = 0;  i < editor.getLines().size(); i++)
        {
            if (shouldAutoWrap && editor.getCursorY() + 1 < editor.getLines().size())
            {
                String[] splitText = editor.getLines().get(editor.getCursorY() + 1).split(" ");
                if (cursorPosToStringLength(editor.getCurrentLine().length() + 1, editor.getCurrentLine() + "-") + cursorPosToStringLength(splitText[0].length(), splitText[0]) < getWidth())
                {
                    editor.setCursorXToEndOfLine();
                    editor.setCursorX(editor.getCursorX() - 1);

                    if ((getFontRenderer().getStringWidth(editor.getCurrentLine()) + getFontRenderer().getStringWidth(splitText[0])) < getWidth())
                    {
                        editor.setLine(editor.getCursorY(), editor.getCurrentLine().concat(splitText[0]));
                        String newString = "";
                        for (int i1 = 1; i1 < splitText.length; i1++)
                        {
                            newString = newString.concat(splitText[i1].concat(" "));
                        }
                        editor.setLine(editor.getCursorY() + 1, newString);
                    }
                }
                if (editor.getCursorY() < editor.getLines().size() - 1)
                    editor.setCursorY(editor.getCursorY() + 1);
            }
        }
        if (cachedCursorY > editor.getLines().size()) cachedCursorY = editor.getLines().size();
        if (cachedCursorX > editor.getLines().get(cachedCursorY).length()) cachedCursorX = editor.getCurrentLine().length();
        editor.setCursorY(cachedCursorY);
        editor.setCursorX(cachedCursorX);
    }

    public void cursorUp()
    {
        if (editor.getCursorY() > 0)
        {
            editor.setCursorY(editor.getCursorY() - 1);
            if (editor.getCurrentLine().length() < editor.getCursorX())
                editor.setCursorXToEndOfLine();
        }
        else
        {
            editor.setCursorX(0);
        }
    }

    public void cursorDown()
    {
        if (editor.getCursorY() < (editor.getLines().size() - 1))
        {
            editor.setCursorY(editor.getCursorY() + 1);
            if (editor.getCurrentLine().length() < editor.getCursorY())
                editor.setCursorXToEndOfLine();
        }
        else
        {
            editor.setCursorXToEndOfLine();
        }
    }

    public void cursorBack()
    {
        if (editor.getCursorX() > 0)
            editor.setCursorX(editor.getCursorX() - 1);
    }

    public void cursorForward()
    {
        if (editor.getCursorX() < editor.getCurrentLine().length())
            editor.setCursorX(editor.getCursorX() + 1);
    }

    public TypingInput getEditor()
    {
        return editor;
    }
}