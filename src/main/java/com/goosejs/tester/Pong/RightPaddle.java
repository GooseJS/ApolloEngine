package com.goosejs.tester.Pong;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.client.renderer.texturedRendering.SpriteBatch;
import com.goosejs.apollo.client.renderer.texturedRendering.TexturedPrimitive2D;
import org.joml.Vector2f;

public class RightPaddle
{
        private TexturedPrimitive2D primitive;
        private Vector2f position;
        private float x;
        private float y;
        public void init()
        {
            primitive = new TexturedPrimitive2D(new Texture("pong/paddle.png"), 25, 100);
            position = new Vector2f(10, 10);
        }

        public void draw(SpriteBatch batch)
        {
            batch.draw(primitive, x, y, 0, 0);
        }

        public void move(int i)
        {
            if (i ==1)
                y += 5;
            else if(i == -1)
                y -= 5;

        }
    }
