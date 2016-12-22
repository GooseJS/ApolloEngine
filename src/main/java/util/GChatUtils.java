package util;

import com.goosejs.gchat.backend.lwjgl.opengl.Texture;
import com.goosejs.gchat.backend.lwjgl.opengl.VertexArray;
import com.goosejs.gchat.backend.lwjgl.opengl.VertexBufferObject;

public class GChatUtils
{

    private GChatUtils() {}

    public static void cleanupEverything()
    {
        VertexArray.cleanup();
        VertexBufferObject.cleanup();
        Texture.cleanup();
    }

}