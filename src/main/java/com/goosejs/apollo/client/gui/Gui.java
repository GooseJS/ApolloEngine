package com.goosejs.apollo.client.gui;

import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.glRendering.DefaultVertexFormats;
import com.goosejs.apollo.client.renderer.glRendering.Tessellator;
import com.goosejs.apollo.client.renderer.glRendering.VertexBuffer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class Gui
{
    protected float zLevel; // TODO: Decide if this will be used or not
    private ArrayList<IDrawable> drawQueue;

    protected void drawHorizontalLine(float x, float y, float width, int color)
    {
        if (width < x)
        {
            float i = x;
            x = width;
            width = i;
        }

        queueRectDraw(x, y, width + 1, y + 1, color);
    }

    protected void drawVerticalLine(float x, float y, float height, int color)
    {
        if (height < y)
        {
            float i = y;
            y = height;
            height = i;
        }

        queueRectDraw(x, y + 1, x + 1, height, color);
    }

    protected void drawRect(float x, float y, float width, float height, int color)
    {
        queueRectDraw(x, y, width, height, color);
    }

    protected void queueRectDraw(float x, float y, float width, float height, int color)
    {
        if (drawQueue == null)
            drawQueue = new ArrayList<>();

        drawQueue.add(vertexBuffer -> drawRect(x, y, width, height, color, vertexBuffer));
    }

    // TODO: Add support for drawing textured rectangles

    public void processDrawQueue()
    {
        if (drawQueue == null)
            drawQueue = new ArrayList<>();
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexBuffer = tessellator.getBuffer();

        drawQueue.forEach(iDrawable -> iDrawable.draw(vertexBuffer));

        tessellator.draw();
    }

    private void drawRect(float x, float y, float width, float height, int color, VertexBuffer vertexBuffer)
    {
        if (x < width)
        {
            float i = x;
            x = width;
            width = i;
        }

        if (y < height)
        {
            float i = y;
            y = height;
            height = i;
        }

        float r = (float)(color >> 24 & 255) / 255.0f;
        float g = (float)(color >> 16 & 255) / 255.0f;
        float b = (float)(color >> 8 & 255) / 255.0f;
        float a = (float)(color & 255) / 255.0f;
        vertexBuffer.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);
        vertexBuffer.pos(x, y).color(r, g, b, a).endVertex();
        vertexBuffer.pos(x, height).color(r, g, b, a).endVertex();
        vertexBuffer.pos(width, height).color(r, g, b, a).endVertex();
        vertexBuffer.pos(width, height).color(r, g, b, a).endVertex();
        vertexBuffer.pos(x, y).color(r, g, b, a).endVertex();
        vertexBuffer.pos(width, y).color(r, g, b, a).endVertex();
    }

    public void drawCenteredString(TrueTypeFontRenderer fontRenderer, String text, float x, float y, int color)
    {
        float r = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float b = (float)(color >> 8 & 255) / 255.0F;
        fontRenderer.drawString((x - fontRenderer.getStringWidth(text) / 2), y, text, r, g, b);
    }

    public void drawString(TrueTypeFontRenderer fontRenderer, String text, int x, int y, int color)
    {
        float r = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float b = (float)(color >> 8 & 255) / 255.0F;
        fontRenderer.drawString(x, y, text, r, g, b);
    }

    public interface IDrawable
    {
        void draw(VertexBuffer vertexBuffer);
    }
}