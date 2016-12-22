package client.renderer.glRendering;

import com.goosejs.gchat.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class VertexFormat
{
    private final List<VertexFormatElement> elements;
    private final List<Integer> offsets;

    private int nextOffset;
    private int colorElementOffset;

    public VertexFormat(VertexFormat vertexFormat)
    {
        this();

        for (int i = 0; i < vertexFormat.getElementCount(); ++i)
        {
            this.addElement(vertexFormat.getElement(i));
        }

        this.nextOffset = vertexFormat.getNextOffset();
    }

    public VertexFormat()
    {
        this.elements = new ArrayList<>();
        this.offsets = new ArrayList<>();
        this.colorElementOffset = -1;
    }

    public void clear()
    {
        this.elements.clear();
        this.offsets.clear();
        this.colorElementOffset = -1;
        this.nextOffset = 0;
    }

    public VertexFormat addElement(VertexFormatElement element)
    {
        if (element.isPositionElement() && this.hasPosition())
        {
            Logger.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
            return this;
        }
        else
        {
            this.elements.add(element);
            this.offsets.add(this.nextOffset);

            if(element.getUsage() == VertexFormatElement.EnumUsage.COLOR)
                this.colorElementOffset = nextOffset;

            this.nextOffset += element.getSize();
            return this;
        }
    }

    public boolean hasColor()
    {
        return this.colorElementOffset >= 0;
    }

    public int getColorOffset()
    {
        return this.colorElementOffset;
    }

    public String toString()
    {
        String s = "format: " + this.elements.size() + " elements: ";

        for (int i = 0; i < this.elements.size(); ++i)
        {
            s = s + this.elements.get(i).toString();

            if (i != this.elements.size() - 1)
            {
                s = s + " ";
            }
        }

        return s;
    }

    private boolean hasPosition()
    {
        int i = 0;

        for (int j = this.elements.size(); i < j; ++i)
        {
            if (this.elements.get(i).isPositionElement())
                return true;
        }

        return false;
    }

    public int getIntegerSize()
    {
        return this.getNextOffset() / 4;
    }

    public int getNextOffset()
    {
        return this.nextOffset;
    }

    public List<VertexFormatElement> getElements()
    {
        return this.elements;
    }

    public int getElementCount()
    {
        return this.elements.size();
    }

    public VertexFormatElement getElement(int i)
    {
        return this.elements.get(i);
    }

    public int getOffset(int i)
    {
        return this.offsets.get(i);
    }

    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
        {
            return true;
        }
        else if (otherObject != null && this.getClass() == otherObject.getClass())
        {
            VertexFormat vertexFormat = (VertexFormat)otherObject;
            return (this.nextOffset == vertexFormat.nextOffset) && this.elements.equals(vertexFormat.elements) && this.offsets.equals(vertexFormat.offsets);
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int result = this.elements.hashCode();
        result = 31 * result + this.offsets.hashCode();
        result = 31 * result + this.nextOffset;
        return result;
    }

}