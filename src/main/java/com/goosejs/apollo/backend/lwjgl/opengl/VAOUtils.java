package com.goosejs.apollo.backend.lwjgl.opengl;

import com.goosejs.apollo.util.GChatBufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Various utils for use with VAO's
 */
public class VAOUtils
{

    /**
     * The constructor is private because no new instances
     * of this class should ever be created
     */
    private VAOUtils() {}

    /**
     * Stores the specified data in the specified index in the currently bound vertex array
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param data the data itself
     */
    public static int storeDataInAttributeList(int index, int coordinateSize, float[] data)
    {
        return storeDataInAttributeList(-1, index, coordinateSize, 0, data);
    }

    /**
     * Stores the specified data in the specified index in the specified VAO
     * @param vaoID the VAO to store the data in
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param data the data itself
     * @return the VBO that the data was stored in
     */
    public static int storeDataInAttributeList(int vaoID, int index, int coordinateSize, float[] data)
    {
        return storeDataInAttributeList(vaoID, index, coordinateSize, 0, data);
    }

    /**
     *
     * Stores the specified data in the specified index in the specified VAO
     * @param vaoID the VAO to store the data in
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param stride The stride of the data
     * @param data the data itself
     * @return the VBO that the data was stored in
     */
    public static int storeDataInAttributeList(int vaoID, int index, int coordinateSize, int stride, float[] data)
    {
        return storeDataInAttributeList(vaoID, false, -1, true, index, coordinateSize, stride, data);
    }

    /**
     * Stores the specified data in the specified index in the specified VAO
     * @param vaoID the VAO to store the data in
     * @param vaoBinding true if you would like to bind the index, false if you don't
     * @param vboID the VBO ID to store the data in
     * @param vboBinding true if you want to create a VBO, false is you would like to provide your own
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param stride The stride of the data
     * @param data the data itself
     * @return the VBO that the data was stored in
     */
    public static int storeDataInAttributeList(int vaoID, boolean vaoBinding, int vboID, boolean vboBinding, int index, int coordinateSize, int stride, float[] data)
    {
        ArrayList<Float> realData = new ArrayList<>();
        for (float dataPoint : data) realData.add(dataPoint);
        return storeDataInAttributeList(vaoID, vaoBinding, vboID, vboBinding, index, coordinateSize, stride, realData);
    }

    /**
     * Stores the specified data in the specified index in the specified VAO
     * @param vaoID the VAO to store the data in
     * @param vaoBinding true if you would like to bind the index, false if you don't
     * @param vboID the VBO ID to store the data in
     * @param vboBinding true if you want to create a VBO, false is you would like to provide your own
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param stride The stride of the data
     * @param data the data itself
     * @return the VBO that the data was stored in
     */
    public static int storeDataInAttributeList(int vaoID, boolean vaoBinding, int vboID, boolean vboBinding, int index, int coordinateSize, int stride, List<Float> data)
    {
        if (vaoBinding) VertexArray.bindVAO(vaoID);
        if (vboBinding) vboID = VertexBufferObject.createVBO();
        VertexBufferObject.bindVBO(GL15.GL_ARRAY_BUFFER, vboID);
        VertexBufferObject.uploadData(GL15.GL_ARRAY_BUFFER, GChatBufferUtils.createFloatBuffer(data), GL15.GL_DYNAMIC_DRAW); // TODO: Dynamic is temp
        VertexArray.vertexAttribPointer(index, coordinateSize, GL11.GL_FLOAT, false, stride, 0);
        VertexBufferObject.unbindVBO(GL15.GL_ARRAY_BUFFER);
        if (vaoBinding) VertexArray.unbindVAO();
        return vboID;
    }

    /**
     * Stores the specified data in the specified index in the specified VAO
     * @param vaoID the VAO to store the data in
     * @param vaoBinding true if you would like to bind the index, false if you don't
     * @param vboID the VBO ID to store the data in
     * @param createVBO true if you want to create a VBO, false is you would like to provide your own
     * @param index the index to store the data
     * @param coordinateSize the size of the data (ex. Vec3 would be 3)
     * @param stride The stride of the data
     * @param data the data itself
     * @return the VBO that the data was stored in
     */
    public static int storeDataInAttributeList(int vaoID, boolean vaoBinding, int vboID, boolean createVBO, int index, int coordinateSize, int stride, FloatBuffer data)
    {
        if (vaoBinding) VertexArray.bindVAO(vaoID);
        if (createVBO) vboID = VertexBufferObject.createVBO();
        VertexBufferObject.bindVBO(GL15.GL_ARRAY_BUFFER, vboID);
        VertexBufferObject.uploadData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_DYNAMIC_DRAW); // TODO: Dynamic is temp
        VertexArray.vertexAttribPointer(index, coordinateSize, GL11.GL_FLOAT, false, stride, 0);
        VertexBufferObject.unbindVBO(GL15.GL_ARRAY_BUFFER);
        if (vaoBinding) VertexArray.unbindVAO();
        return vboID;
    }

}
