package com.goosejs.apollo.util;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexArray;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexBufferObject;

public class ApolloUtils
{

    private ApolloUtils() {}

    public static void cleanupEverything()
    {
        VertexArray.cleanup();
        VertexBufferObject.cleanup();
        Texture.cleanup();
    }

}