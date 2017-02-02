package com.goosejs.tester.flappyBird;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import org.joml.Vector2f;

public class Pipe
{

    private Texture texture;
    private Vector2f dimension;
    private Vector2f postion;

    public void init()
    {
        texture = new Texture("pipe.png");
        dimension = new Vector2f();
    }

}