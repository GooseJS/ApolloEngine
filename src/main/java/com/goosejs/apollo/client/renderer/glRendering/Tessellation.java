package com.goosejs.apollo.client.renderer.glRendering;

import org.lwjgl.opengl.GL11;

public class Tessellation
{

    public static void drawLineLoop(float x0, float y0, float x1, float y1)
    {
        drawRectOutline(x0, y0, x1, y1);
    }

    public static void drawRectOutline(float x0, float y0, float x1, float y1)
    {
        drawRectOutline(x0, y0, x1, y1, 255, 255, 255, 255);
    }

    public static void drawLineLoop(float x0, float y0, float x1, float y1, float r, float g, float b, float a)
    {
        drawRectOutline(x0, y0, x1, y1, r, g, b, a);
    }

    public static void drawRectOutline(float x0, float y0, float x1, float y1, float r, float g, float b, float a)
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(x0, y0).color(r, g, b, a).endVertex();
        buffer.pos(x1, y0).color(r, g, b, a).endVertex();
        buffer.pos(x1, y1).color(r, g, b, a).endVertex();
        buffer.pos(x0, y1).color(r, g, b, a).endVertex();
        tessellator.draw();
    }

    public static void drawRect(float x0, float y0, float x1, float y1, float r, float g, float b, float a)
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(x0, y0).color(r, g, b, a).endVertex();
        buffer.pos(x0, y1).color(r, g, b, a).endVertex();
        buffer.pos(x1, y1).color(r, g, b, a).endVertex();
        buffer.pos(x1, y1).color(r, g, b, a).endVertex();
        buffer.pos(x0, y0).color(r, g, b, a).endVertex();
        buffer.pos(x1, y0).color(r, g, b, a).endVertex();
        tessellator.draw();
    }

    public static void drawRect(float x0, float y0, float x1, float y1)
    {
        drawRect(x0, y0, x1, y1, 255, 255, 255, 255);
    }

    public static void drawLine(float x0, float y0, float x1, float y1)
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_LINE, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(x0, y0).color(255, 255, 255, 255).endVertex();
        buffer.pos(x1, y1).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
    }
}