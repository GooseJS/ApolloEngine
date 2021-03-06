package com.goosejs.tester;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.*;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedShader;
import com.goosejs.apollo.util.ApolloBufferUtils;
import com.goosejs.apollo.util.Logger;
import org.lwjgl.opengl.GL11;

public class ApolloTester extends LoopingApplicationBase
{

    private Window window;

    public static void main(String args[])
    {
        new ApolloTester().startApp();
    }

    public void startApp()
    {
        setApplicationLoop(new DefaultApplicationLoop());
        setupApplicationLoop(this);
    }

    private float r;
    private float g;
    private float b;

    private boolean rIncrease = true;
    private boolean gIncrease = true;
    private boolean bIncrease = true;

    private float rIncreaseNum = 0.05f;
    private float rDecreaseNum = 0.05f;

    private float gIncreaseNum = 0.04f;
    private float gDecreaseNum = 0.03f;

    private float bIncreaseNum = 0.03f;
    private float bDecreaseNum = 0.02f;

    @Override
    public void runApplicationLoop()
    {
        GL11.glClearColor(r, g, b, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        if (rIncrease) r += rIncreaseNum; else r -= rDecreaseNum;
        if (gIncrease) g += gIncreaseNum; else g -= gDecreaseNum;
        if (bIncrease) b += bIncreaseNum; else b -= bDecreaseNum;

        if (r > 1) rIncrease = false;
        if (r < 0) rIncrease = true;

        if (g > 1) gIncrease = false;
        if (g < 0) gIncrease = true;

        if (b > 1) bIncrease = false;
        if (b < 0) bIncrease = true;

        VAO.bindVAO(vaoID);
        texturedShader.useProgram();
        texture.bindTexture();
        VAO.enableAttribArray(0, 1);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
        VAO.disableAttribArray(0, 1);
        texture.unbindTexture();
        texturedShader.stopUsingProgram();
        VAO.unbindVAO();

        fontRenderer.drawString(1, 1, "Looooo testing");

        // TODO: Eventually this stuff should be handled by the DefaultApplicationLoop
        window.swapBuffers();
        window.pollEvents();

        if (window.shouldClose())
            getApplicationLoop().stopLoop();
    }

    @Override
    public boolean preInit()
    {
        Logger.info("Pre-Init");

        window = new Window(800, 600, "gEngine", false, false);
        window.createWindow();

        return true;
    }

    @Override
    public boolean init()
    {
        Logger.info("Init");
        return true;
    }

    Texture texture;
    int vboID;
    int texCoordID;
    int vaoID;
    TexturedShader texturedShader;
    TrueTypeFontRenderer fontRenderer;

    @Override
    public boolean postInit()
    {
        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 25f, 0f, 0, 0, window.getWidth(), window.getHeight());
        fontRenderer.resetTranslation();
        vaoID = VAO.createVAO();
        VAO.bindVAO(vaoID);
        texture = new Texture("texture.png");
        texturedShader = new TexturedShader();
        vboID = VBO.createVBO();
        texCoordID = VBO.createVBO();
        vboID = VAOUtils.storeDataInAttributeList(0, 2, 0, ApolloBufferUtils.createFloatBuffer(new float[] {
                -0.5f, 0.5f,
                0.5f, 0.5f,
                -0.5f, -0.5f,

                -0.5f, -0.5f,
                0.5f, 0.5f,
                0.5f, -0.5f
        }));
        VAOUtils.storeDataInAttributeList(1, 2, 0, ApolloBufferUtils.createFloatBuffer(new float [] {
                1, 0,
                0, 0,
                1, 1,
                1, 1,
                0, 0,
                0, 1
        }));
        VAO.unbindVAO();

        Logger.info("Post-Init");
        //AudioMaster.init();
        return true;
    }
}