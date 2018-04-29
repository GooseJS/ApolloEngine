package com.goosejs.tester.redHarvest;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import com.goosejs.apollo.physics.AABB2D;
import org.joml.Vector2f;

/**
 * Created by andrewrusso on 4/11/18.
 */
public class Projectile
{
    private TexturedPrimitive2D primitive;

    private float width = 10;
    private float height = 10;

    private Vector2f position;
    private Vector2f velocity;

    public Projectile(float x, float y, float xvel, float yvel)
    {
        this.position = new Vector2f(x, y);
        this.velocity = new Vector2f(xvel, yvel);
        primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), height, width);
    }
    public void draw(SpriteBatch batch)
    {
        batch.draw(primitive, position.x, position.y);
    }

    public void update(Character character, Enemy enemy)
    {

        if (((character.getPosition().x + character.getWidth() == position.x || character.getPosition().x == position.x || (character.getPosition().x + character.getWidth() == position.x - velocity.x || character.getPosition().x + character.getWidth() == position.x + velocity.x) &&  character.getPosition().y + character.getHeight() / 2 == position.y) || (position.x == 0)))
        {
            redHarvest.setisGameOver(false);
            //position.x = enemy.getPosition().x;
        }
        else   if (position.x > character.getPosition().x + character.getWidth()) position.x -= velocity.x;
        else { position.x = position.x - velocity.x; }
    }
}
