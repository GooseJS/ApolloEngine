package com.goosejs.apollo.backend.lwjgl.opengl;

import com.goosejs.apollo.util.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.stb.STBImage.*;

// TODO: Document this class
public class Texture
{

    private static final List<Integer> textures = new ArrayList<>();

    private int textureID;

    public Texture(int textureID)
    {
        this.textureID = textureID;
    }

    public Texture(File textureFile)
    {
        this(textureFile.getAbsolutePath());
    }

    public Texture(String textureFile)
    {
        this(textureFile, GL13.GL_TEXTURE0);
    }

    public Texture(File textureFile, int textureUnit)
    {
        this(textureFile.getAbsolutePath(), textureUnit);
    }

    public Texture(String textureFile, int textureUnit)
    {
        textureID = loadTexture(textureFile, textureUnit);
    }

    public int loadTexture(String image, int textureUnit)
    {
        ByteBuffer fileBuffer;

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer actualImage;

        try
        {
            fileBuffer = IOUtils.fileToByteBufferE(image, 8 * 1024);
            stbi_set_flip_vertically_on_load(true);
            stbi_info_from_memory(fileBuffer, width, height, comp);

            actualImage = stbi_load_from_memory(fileBuffer, width, height, comp, 0);
        }
        catch (IOException e)
        {
            return -1; // TODO: Log this as an error
        }

        if (actualImage != null)
        {

            int texID = GL11.glGenTextures();

            GL13.glActiveTexture(textureUnit);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);

            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width.get(0), height.get(0), 0, GL11.GL_RGBA, GL11
                    .GL_UNSIGNED_BYTE, actualImage);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

            textures.add(texID);

            unbindTexture();

            stopUsingTexture();

            return texID;
        }

        return -1; // TODO: Log this as an error
    }

    public void useTexture()
    {
        GL13.glActiveTexture(textureID);
    }

    public void bindTexture()
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    public void unbindTexture()
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static void unbindTextureStatic()
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void stopUsingTexture()
    {
        stopUsingTextureStatic();
    }

    public static void stopUsingTextureStatic()
    {
        GL13.glActiveTexture(0);
    }

    public int getTextureID()
    {
        return textureID;
    }

    public static void cleanup()
    {
        textures.forEach(GL11::glDeleteTextures);
    }

    public static int createTexture()
    {
        int result = GL11.glGenTextures();
        textures.add(result);
        return result;
    }

    public static void bindTexture(int textureID)
    {
        bindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    public static void bindTexture(int target, int textureID)
    {
        GL11.glBindTexture(target, textureID);
    }

    public static void useTexture(int textureID)
    {
        GL13.glActiveTexture(textureID);
    }

    public static void setTextureData(int width, int height, ByteBuffer data)
    {
        setTextureData(GL11.GL_ALPHA, width, height, data);
    }

    public static void setTextureData(int format, int width, int height, ByteBuffer data)
    {
        setTextureData(GL11.GL_TEXTURE_2D, 0, format, width, height, 0, GL11.GL_UNSIGNED_BYTE, data);
    }

    public static void setTextureData(int target, int level, int format, int width, int height, int border, int type, ByteBuffer data)
    {
        GL11.glTexImage2D(target, level, format, width, height, border, format, type, data);
    }

    public static void setTextureParameter(int parameter, int value)
    {
        setTextureParameter(GL11.GL_TEXTURE_2D, parameter, value);
    }

    public static void setTextureParameter(int target, int parameter, int value)
    {
        GL11.glTexParameteri(target, parameter, value);
    }

    public static void generateMipMap()
    {
        generateMipMap(GL11.GL_TEXTURE_2D);
    }

    public static void generateMipMap(int type)
    {
        GL30.glGenerateMipmap(type);
    }

}