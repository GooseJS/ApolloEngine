package com.goosejs.apollo.util;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.backend.lwjgl.opengl.VBO;

public class ApolloUtils
{

    private ApolloUtils() {}

    public static void cleanupEverything()
    {
        VAO.cleanup();
        VBO.cleanup();
        Texture.cleanup();
    }

}