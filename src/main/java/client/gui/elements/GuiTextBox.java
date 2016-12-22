package client.gui.elements;

import com.goosejs.gchat.client.gui.GuiElement;
import com.goosejs.gchat.client.gui.elements.subelements.GuiString;
import com.goosejs.gchat.renderer.glRendering.Tessellation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GuiTextBox extends GuiElement
{

    private List<GuiString> lines;
    private int maxSavedLines = 50;

    private boolean topNewest = false;
    private boolean locatedAtBottom = false;

    private int wrappingIndent = 50;

    // TODO: Link all these fields to a ConfigFile
    private final int xOffset = 6;
    private final int cursorXOffset = 2;
    private final int textWrappingPadding = 10;

    public GuiTextBox(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        this.lines = new CopyOnWriteArrayList<>();
    }

    @Override
    public void onDraw()
    {
        renderOutline();

        float y;

        if (locatedAtBottom)
            y = getHeight() - getFontRenderer().getFontHeight();
        else
            y = 6;

        for (GuiString string : lines)
        {
            String line = string.toString();

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

                            if (displayStrings.get(i).lastIndexOf(" ") != -1) displayStrings.set(i, displayStrings.get(i).substring(0, displayStrings.get(i).lastIndexOf(" ") + 1));
                            else attemptedSpace = true;
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
                for (int i1 = 0; i1 < displayStrings.size(); i1++)
                {
                    if (locatedAtBottom)
                    {
                        if (i1 == 0)
                        {
                            y -= (displayStrings.size() - 1) * getFontRenderer().getFontHeight();

                            getFontRenderer().drawString(xOffset, y, displayStrings.get(i1), string.getR(), string.getG(), string.getB());
                            y += getFontRenderer().getFontHeight();
                        }
                        else
                        {
                            getFontRenderer().drawString(wrappingIndent, y, displayStrings.get(i1), string.getR(), string.getG(), string.getB());
                            if (i1 != displayStrings.size() - 1) y += getFontRenderer().getFontHeight();
                            else
                                y -= displayStrings.size() * getFontRenderer().getFontHeight();
                        }
                    }
                    else
                    {
                        getFontRenderer().drawString(i1 == 0 ? xOffset : wrappingIndent, y, displayStrings.get(i1), string.getR(), string.getG(), string.getB());
                        if (locatedAtBottom)
                            y -= getFontRenderer().getFontHeight();
                        else
                            y += getFontRenderer().getFontHeight();
                    }
                }
            }
            else
            {
                getFontRenderer().drawString(xOffset, y, line, string.getR(), string.getG(), string.getB());
                if (locatedAtBottom)
                    y -= getFontRenderer().getFontHeight();
                else
                    y += getFontRenderer().getFontHeight();
            }

        }
    }

    public void addLine(String line)
    {
        addLine(new GuiString(line));
    }

    public void addLine(GuiString line)
    {
        if (topNewest)
        {
            this.lines.add(line);
            if (lines.size() > maxSavedLines)
                removeLine(0);
        }
        else
        {
            this.lines.add(0, line);
            if (lines.size() > maxSavedLines)
                removeLine(maxSavedLines);
        }
    }

    public boolean removeLine(GuiString line)
    {
        return this.lines.remove(line);
    }

    public GuiString removeLine(int elementID)
    {
        return this.lines.remove(elementID);
    }

    public List<GuiString> getLines()
    {
        return this.lines;
    }

    public void setLines(List<GuiString> lines)
    {
        this.lines = lines;
    }

    public void renderOutline()
    {
        Tessellation.drawRectOutline(1, 1, getWidth(), getHeight() - 1, 1, 1, 1, 1);
    }

    public boolean isTopNewest()
    {
        return topNewest;
    }

    public boolean isLocatedAtBottom()
    {
        return locatedAtBottom;
    }

    public void setTopNewest(boolean topNewest)
    {
        this.topNewest = topNewest;
    }

    public void setLocatedAtBottom(boolean locatedAtBottom)
    {
        this.locatedAtBottom = locatedAtBottom;
    }
}