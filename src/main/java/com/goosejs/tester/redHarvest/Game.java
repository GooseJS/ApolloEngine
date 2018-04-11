package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.application.LoopingApplicationBase;
import com.goosejs.apollo.application.applicationLoop.DefaultApplicationLoop;
import com.goosejs.apollo.backend.lwjgl.glfw.ExtendableKeyboardCallback;
import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.client.renderer.font.TrueTypeFontRenderer;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 * Created by andrewrusso on 2/7/17.
 */
public class Game extends LoopingApplicationBase
{
        private Character character;
        public static Window window;
        private SpriteBatch batch;
        private Terrain terrain;


        private TrueTypeFontRenderer fontRenderer;

    public static void main(String[] args)
    {
        Game Game = new Game();
        Game.setApplicationLoop(new DefaultApplicationLoop());
        Game.setupApplicationLoop(Game);
    }

    @Override
    public boolean init()
    {
        window = new Window(1200,700,"Red Harvest",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());
        character = new Character(0,0,10,0);
        terrain = new Terrain(0,0,700,10);


        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);


        batch  = new SpriteBatch();

        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 50f, window);
        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        playGame();
        character.draw(batch);
        character.update();


        batch.flushQueue();

        if (!window.update())
            getApplicationLoop().stopLoop();
    }

    private void playGame()
    {
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_SPACE)) character.setisJumping();
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_D)) character.moveRight();
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_A)) character.moveLeft();
    }

    public Window getWindow()
    {
        return(window);
    }
}

