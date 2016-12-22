package client.gui.elements.subelements;

public class GuiString
{
    protected String text;

    protected boolean bold; // TODO: Implement Bold
    protected boolean italic; // TODO: Implement Italic
    protected boolean magic; // TODO: Implement Magic

    protected float r;
    protected float g;
    protected float b;

    public GuiString(String text)
    {
        this(text, 1.0f, 1.0f, 1.0f);
    }

    public GuiString(String text, int color)
    {
        this(text, (color >> 24 & 255), (color >> 16 & 255), (color >> 8 & 255));
    }

    public GuiString(String text, int r, int g, int b)
    {
        this(text, (float)r / 255.0f, (float)g / 255.0f, (float)b / 255.0f);
    }

    public GuiString(String text, float r, float g, float b)
    {
        this.text = text;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public float getR()
    {
        return this.r;
    }

    public void setR(float r)
    {
        this.r = r;
    }

    public float getG()
    {
        return this.g;
    }

    public void setG(float g)
    {
        this.g = g;
    }

    public float getB()
    {
        return this.b;
    }

    public void setB(float b)
    {
        this.b = b;
    }

    @Override
    public String toString()
    {
        return this.text;
    }

}