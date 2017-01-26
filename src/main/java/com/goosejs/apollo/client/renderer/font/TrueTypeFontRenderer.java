package com.goosejs.apollo.client.renderer.font;

import com.goosejs.apollo.backend.lwjgl.opengl.*;
import com.goosejs.apollo.client.gui.elements.subelements.GuiString;
import com.goosejs.apollo.util.ApolloBufferUtils;
import com.goosejs.apollo.util.GLUtils;
import com.goosejs.apollo.util.IOUtils;
import com.goosejs.apollo.util.Logger;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * TODO: Documentation
 */
public class TrueTypeFontRenderer
{
    private FontShader fontShader;

    private final float scale;

    private final STBTTAlignedQuad quad = STBTTAlignedQuad.malloc();
    private final FloatBuffer xb = MemoryUtil.memAllocFloat(1);
    private final FloatBuffer yb = MemoryUtil.memAllocFloat(1);

    private STBTTPackedchar.Buffer chardata;

    private int fontTextureID;
    private int vaoID;
    private int texCoordID;
    private int vertexID;
    private float additionFontSpacing;

    private int transX1;
    private int transX2;
    private int transY1;
    private int transY2;

    private int originalX;
    private int originalWidth;
    private int originalY;
    private int originalHeight;

    private boolean drawing;
    private float efficientDrawR;
    private float efficientDrawG;
    private float efficientDrawB;
    private ArrayList<Float> efficientDrawVertices;
    private ArrayList<Float> efficientDrawTexCoords;

    private boolean vaoBinding = true;

    public TrueTypeFontRenderer(String fontFile)
    {
        this(fontFile, 20f);
    }

    public TrueTypeFontRenderer(String fontFile, float scale)
    {
        this(fontFile, scale, 0);
    }

    public TrueTypeFontRenderer(String fontFile, float scale, float additionalFontSpacing)
    {
        this(fontFile, scale, additionalFontSpacing, 0, 0, 800, 600);
    }

    public TrueTypeFontRenderer(String fontFile, float scale, float additionalFontSpacing, int x0, int y0, int x1, int y1)
    {
        this.scale = scale;
        this.additionFontSpacing = additionalFontSpacing;
        fontShader = new FontShader();
        loadFonts(fontFile);
        GlobalPerspectiveMatrices.add2DPerspectiveOnChange((x02, y02, x12, y12) -> windowResized(x12, y12));
        this.originalX = x0;
        this.originalWidth = x1;
        this.originalY = y0;
        this.originalHeight = y1;
        this.transX1 = originalX;
        this.transX2 = originalWidth;
        this.transY1 = originalY;
        this.transY2 = originalHeight;

        resetTranslation();
    }

    public void windowResized(int x1, int y1)
    {
        fontShader.useProgram();
        fontShader.loadOrthoMatrix(0, x1, 0, y1, -1, 1);
        fontShader.stopUsingProgram();
        this.originalWidth = x1;
        this.originalHeight = y1;
        this.transX2 = x1;
        this.transY2 = y1;
    }

    public void translate(float x, float y)
    {
        transX1 += x;
        transX2 += x;
        transY1 += y;
        transY2 += y;
        loadOrthoMatrix(transX1, transY1, transX2, transY2);
    }

    public void translateTo(int x, int y)
    {
        transX1 = x;
        transX2 = x + originalWidth;
        transY1 = y;
        transY2 = y + originalHeight;
        loadOrthoMatrix(transX1, transY1, transX2, transY2);
    }

    public void resetTranslation()
    {
        loadOrthoMatrix(originalX, originalY, originalWidth, originalHeight);
    }

    public void setDimensions(int x, int y, int width, int height)
    {
        loadOrthoMatrix(x, y, x + width, y + height);
    }

    public void loadOrthoMatrix(int x, int y, int width, int height)
    {
        fontShader.useProgram();
        fontShader.loadOrthoMatrix(x, width, y, height, -1, 1);
        fontShader.stopUsingProgram();
        this.originalWidth = width;
        this.originalHeight = height;
        this.transX1 = x;
        this.transY1 = y;
        this.transX2 = width;
        this.transY2 = height;
    }

    public void loadOrthoMatrix(Matrix4f matrix)
    {
        fontShader.useProgram();
        fontShader.loadOrthoMatrix(matrix);
        fontShader.stopUsingProgram();
    }

    private void loadFonts(String fontFile)
    {
        fontTextureID = Texture.createTexture();
        chardata = STBTTPackedchar.malloc(128);

        STBTTPackContext pc = STBTTPackContext.malloc();

        ByteBuffer ttf = IOUtils.fileToByteBuffer(fontFile, 160 * 1024);
        ByteBuffer bitmap = BufferUtils.createByteBuffer(512 * 512);

        STBTruetype.stbtt_PackBegin(pc, bitmap, 512, 512, 0, 1);

        chardata.limit(127);
        chardata.position(32);
        STBTruetype.stbtt_PackSetOversampling(pc, 1, 1);
        STBTruetype.stbtt_PackFontRange(pc, ttf, 0, scale, 32, chardata);

        chardata.clear();
        STBTruetype.stbtt_PackEnd(pc);

        Texture.useTexture(GL13.GL_TEXTURE0);
        Texture.bindTexture(fontTextureID);
        Texture.setTextureData(GL11.GL_RED, 512, 512, bitmap);
        Texture.setTextureParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        Texture.setTextureParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        Texture.generateMipMap();

        if (vaoBinding)
            vaoID = VAO.createVAO();
        VAO.bindVAO(vaoID);
        vertexID = VBO.createVBO();
        texCoordID = VBO.createVBO();
        if (vaoBinding)
            VAO.unbindVAO();
    }

    public void drawString(float x, float y, String text)
    {
        drawString(x, y, text, 1, 1, 1);
    }

    public void drawString(float x, float y, GuiString text)
    {
        drawString(x, y, text.toString(), text.getR(), text.getG(), text.getB());
    }

    public void drawString(float x, float y, String text, float r, float g, float b)
    {
        if (drawing)
        {
            throw new IllegalAccessError("Cannot draw string while drawing string!");
        }

        startEfficientDraw(r, g, b);

        addTextToEfficientDraw(x, y, text);

        drawEfficientDraw();
    }

    public void startEfficientDraw(float r, float g, float b)
    {
        if (!drawing)
        {
            drawing = true;

            GLStateManager.pushState();
            GLStateManager.disableCulling();
            GLStateManager.disableLighting();
            GLStateManager.disableDepth();
            GLStateManager.enableBlending();
            GLStateManager.setBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            efficientDrawR = r;
            efficientDrawG = g;
            efficientDrawB = b;
            efficientDrawVertices = new ArrayList<>();
            efficientDrawTexCoords = new ArrayList<>();
        }
        else
            Logger.warn("Programmer error! Attempting to start drawing a string while already drawing another one!",
                    new Throwable());
    }

    public void addTextToEfficientDraw(float x, float y, String text)
    {
        if (drawing)
        {
            xb.put(0, x);
            yb.put(0, (float) (y + (scale / 1.5)));
            chardata.position(0);


            for (int i = 0; i < text.length(); i++)
            {
                STBTruetype.stbtt_GetPackedQuad(chardata, 512, 512, text.charAt(i), xb, yb, quad, false);
                GLUtils.addDataToArrayList(efficientDrawVertices, efficientDrawTexCoords,
                        quad.x0(), quad.y0(), quad.x1(), quad.y1(),
                        quad.s0(), quad.t0(), quad.s1(), quad.t1());
                xb.put(0, xb.get(0) + additionFontSpacing);
            }
        }
        else
            Logger.warn("Programmer error! Attempting to draw string before initializing draw!", new Throwable());
    }

    public void drawEfficientDraw()
    {
        if (drawing)
        {
            drawing = false;

            if (vaoBinding)
                VAO.bindVAO(vaoID);
            VAOUtils.storeDataInAttributeList(0, 2, 0, ApolloBufferUtils.createFloatBuffer(efficientDrawVertices));
            VAOUtils.storeDataInAttributeList(1, 2, 0, ApolloBufferUtils.createFloatBuffer(efficientDrawTexCoords));

            fontShader.useProgram();
            fontShader.loadColor(efficientDrawR, efficientDrawG, efficientDrawB);
            //fontShader.loadAlpha(a); TODO: Make this work
            Texture.bindTexture(fontTextureID);
            VAO.enableAttribArray(0, 1);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, efficientDrawVertices.size() / 2);
            VAO.disableAttribArray(0, 1);
            Texture.unbindTextureStatic();
            fontShader.stopUsingProgram();
            if (vaoBinding)
                VAO.unbindVAO();

            GLStateManager.popState();
        }
        else
            Logger.warn("Programmer error! Attempting to draw string before initializing draw!", new Throwable());
    }

    public float getStringWidth(String text)
    {
        /** TODO: Look into the efficiency of this, and possibly make it more efficient if need be */
        float currentWidth = 0f;

        xb.put(0, 0);
        yb.put(0, (float) (0 + (scale / 1.5)));
        chardata.position(0);

        for (int i = 0; i < text.length(); i++)
        {
            STBTruetype.stbtt_GetPackedQuad(chardata, 512, 512, text.charAt(i), xb, yb, quad, false);
            if (i == text.length() - 1)
                currentWidth = quad.x1();
            xb.put(0, xb.get(0) + additionFontSpacing);
        }

        return currentWidth;
    }

    public float getFontHeight()
    {
        return scale;
    }

    public float getTranslatedX()
    {
        return transX1;
    }

    public float getTranslatedWidth()
    {
        return transX2;
    }

    public float getTranslatedY()
    {
        return transY1;
    }

    public float getTranslatedHeight()
    {
        return transY2;
    }
}