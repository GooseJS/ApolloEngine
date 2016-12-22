package backend.lwjgl.opengl;

import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class VertexBufferObject
{

    private static final List<Integer> vbos = new ArrayList<>();

    private VertexBufferObject() {}

    public static int createVBO()
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        return vboID;
    }

    public static void bindVBO(VertexBufferType type, int vboID)
    {
        bindVBO(type.getIntValue(), vboID);
    }

    public static void bindVBO(int type, int vboID)
    {
        glBindBuffer(type, vboID);
    }

    public static void unbindVBO(VertexBufferType type)
    {
        unbindVBO(type.getIntValue());
    }

    public static void unbindVBO(int type)
    {
        glBindBuffer(type, 0);
    }

    public static void uploadData(VertexBufferType type, FloatBuffer data)
    {
        uploadData(type, data, GL15.GL_STATIC_DRAW);
    }

    public static void uploadData(int type, FloatBuffer data)
    {
        uploadData(type, data, GL15.GL_STATIC_DRAW);
    }

    public static void uploadData(VertexBufferType type, FloatBuffer data, int usage)
    {
        uploadData(type.getIntValue(), data, usage);
    }

    public static void uploadData(int type, FloatBuffer data, int usage)
    {
        glBufferData(type, data, usage);
    }

    public static void uploadData(VertexBufferType type, IntBuffer data)
    {
        uploadData(type, data, GL15.GL_STATIC_DRAW);
    }

    public static void uploadData(int type, IntBuffer data)
    {
        uploadData(type, data, GL15.GL_STATIC_DRAW);
    }

    public static void uploadData(VertexBufferType type, IntBuffer data, int usage)
    {
        uploadData(type.getIntValue(), data, usage);
    }

    public static void uploadData(int type, IntBuffer data, int usage)
    {
        glBufferData(type, data, usage);
    }

    public static void cleanup()
    {
        vbos.forEach(GL15::glDeleteBuffers);
    }

}