package util;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class GChatBufferUtils
{

    private GChatBufferUtils() {}

    public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity)
    {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public static FloatBuffer createFloatBuffer(List<Float> data)
    {
        return createFloatBuffer(data, true);
    }

    public static FloatBuffer createFloatBuffer(List<Float> data, boolean flip)
    {
        FloatBuffer resultBuffer = BufferUtils.createFloatBuffer(data.size());
        data.forEach(resultBuffer::put);
        if (flip) resultBuffer.flip();
        return resultBuffer;
    }

    public static FloatBuffer createFloatBuffer(float[] data)
    {
        return createFloatBuffer(data, true);
    }

    public static FloatBuffer createFloatBuffer(float[] data, boolean flip)
    {
        FloatBuffer resultBuffer = BufferUtils.createFloatBuffer(data.length);
        resultBuffer.put(data);
        if (flip) resultBuffer.flip();
        return resultBuffer;
    }

    public static IntBuffer createIntBuffer(ArrayList<Integer> data)
    {
        return createIntBuffer(data, true);
    }

    public static IntBuffer createIntBuffer(ArrayList<Integer> data, boolean flip)
    {
        IntBuffer resultBuffer = BufferUtils.createIntBuffer(data.size());
        data.forEach(resultBuffer::put);
        if (flip) resultBuffer.flip();
        return resultBuffer;
    }

    public static IntBuffer createIntBuffer(int[] data)
    {
        return createIntBuffer(data, true);
    }

    public static IntBuffer createIntBuffer(int[] data, boolean flip)
    {
        IntBuffer resultBuffer = BufferUtils.createIntBuffer(data.length);
        resultBuffer.put(data);
        if (flip) resultBuffer.flip();
        return resultBuffer;
    }

}