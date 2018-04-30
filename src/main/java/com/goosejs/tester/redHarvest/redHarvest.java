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
public class redHarvest extends LoopingApplicationBase
{
        private Character character;
        public static Window window;
        private SpriteBatch batch;
        private Terrain terrain;
        private Enemy enemy;
        private Projectile projectile;
        private static boolean isGameOver;
        private static boolean isWin;
        private static ImageDisplay image;
        private static ImageDisplay2 image2;


        private TrueTypeFontRenderer fontRenderer;

    public static void main(String[] args)
    {
        redHarvest redHarvest = new redHarvest();
        redHarvest.setApplicationLoop(new DefaultApplicationLoop());
        redHarvest.setupApplicationLoop(redHarvest);
    }

    @Override
    public boolean init()
    {
        window = new Window(1200,700,"Red Harvest",false,false);
        window.createWindow();
        window.setKeyboardCallback(new ExtendableKeyboardCallback());
        enemy = new Enemy(1000,0,2,0);
        character = new Character(0,0,10,5);
        terrain = new Terrain(0,0,700,10);
        projectile = new Projectile(enemy.getPosition().x, enemy.getWidth() / 2,5,0);
        enemy.makeTarget();
        image = new ImageDisplay(870,630,200,35,"redHarvest/gameover.png");
        image2 = new ImageDisplay2(870,630,200,35,"redHarvest/win.png");
        isGameOver = true;
        isWin = true;


        GlobalPerspectiveMatrices.update2DPerspectiveMatrix(window);


        batch = new SpriteBatch();

        fontRenderer = new TrueTypeFontRenderer("Roboto-Regular.ttf", 50f, window);
        return true;
    }

    @Override
    public void runApplicationLoop()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        playGame();
        if(isGameOver && isWin)
        {
            character.draw(batch);
            character.update(enemy);
            enemy.draw(batch);
            enemy.update(character);
            projectile.draw(batch);
            projectile.update(character, enemy);
        }
        else if(!isGameOver)
        {
            image.draw(batch);
        }
        else if(!isWin)
        {
            image2.draw(batch);
        }


        batch.flushQueue();

        if (!window.update())
            getApplicationLoop().stopLoop();
        System.out.println("game over:");
        System.out.println( isGameOver);
        System.out.println("image2:");
        System.out.println(isWin);
    }

    private void playGame()
    {
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_SPACE))
        {
            if (character.getPosition().y == 0 || character.getPosition().y == 90)
            {
                character.setisJumping(true);
            }
        }
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_D)) character.moveRight();
        if(window.getKeyboardCallback().isKeyDown(GLFW.GLFW_KEY_A)) character.moveLeft();
    }
    public static void setisGameOver(boolean bool)
    {
        isGameOver = bool;
    }
    public static void setIsWin(boolean bool)
    {
        isWin = bool;
    }

    public Window getWindow()
    {
        return(window);
    }
    public Character getCharacter() {return (character);}
}

