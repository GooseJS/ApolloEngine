package com.goosejs.apollo.shader;

import com.goosejs.apollo.util.IOUtils;
import com.goosejs.apollo.util.Logger;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.HashMap;

public abstract class ShaderProgram
{

    private int id; // TODO: Lock com.goosejs.apollo.shader? Throw exception? Handle this if it is equal to -1
    private HashMap<String, Integer> uniformLocations;

    private FloatBuffer matrixBuffer;

    public ShaderProgram(File vertexShaderFile, File fragmentShaderFile)
    {
        createProgram(IOUtils.getStringFromFileWithoutComments(vertexShaderFile), IOUtils.getStringFromFileWithoutComments(fragmentShaderFile));
        finishInitialization();
    }

    public ShaderProgram(String vertexShaderFile, String fragmentShaderFile)
    {
        createProgram(IOUtils.getStringFromFileWithoutComments(vertexShaderFile), IOUtils.getStringFromFileWithoutComments(fragmentShaderFile));
        finishInitialization();
    }

    private void finishInitialization()
    {
        uniformLocations = new HashMap<>();
        matrixBuffer = BufferUtils.createFloatBuffer(16);
    }

    private void createProgram(String vertexShaderSource, String fragmentShaderSource)
    {
        int vertexShaderID = loadShader(vertexShaderSource, GL20.GL_VERTEX_SHADER);
        int fragmentShaderID = loadShader(fragmentShaderSource, GL20.GL_FRAGMENT_SHADER);
        id = GL20.glCreateProgram();
        bindAttributes();
        GL20.glAttachShader(id, vertexShaderID);
        GL20.glAttachShader(id, fragmentShaderID);
        GL20.glLinkProgram(id);
        GL20.glValidateProgram(id);
        if (GL20.glGetShaderi(id, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            Logger.error(GL20.glGetProgrami(id, GL20.GL_INFO_LOG_LENGTH));
            Logger.error(GL20.glGetProgramInfoLog(id));
            throw new RuntimeException("Could not link program!"); // TODO: Handle this better
        }

        GL20.glDetachShader(id, vertexShaderID);
        GL20.glDetachShader(id, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
    }

    private int loadShader(String shaderSource, int type)
    {
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE)
            throw new RuntimeException("Could not compile shader!\nSource: " + shaderSource +"\nReason: " + GL20.glGetShaderInfoLog(shaderID, 500)); // TODO: Handle this better
        return shaderID;
    }

    protected int getUniformLocation(String uniformName)
    {
        if (uniformLocations.containsKey(uniformName))
            return uniformLocations.get(uniformName);
        uniformLocations.put(uniformName, GL20.glGetUniformLocation(id, uniformName));
        return getUniformLocation(uniformName);
    }

    protected void loadFloat(int location, float value)
    {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector2f(int location, Vector2f value)
    {
        GL20.glUniform2f(location, value.x, value.y);
    }

    protected void loadVector3f(int location, Vector3f value)
    {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    protected void loadVector4f(int location, Vector4f value)
    {
        GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
    }

    protected void loadBoolean(int location, boolean value)
    {
        GL20.glUniform1i(location, value ? 1 : 0);
    }

    protected void loadMatrix4f(int location, Matrix4f value)
    {
        matrixBuffer.clear();
        value.get(matrixBuffer);
        GL20.glUniformMatrix4fv(location, false, matrixBuffer);
    }

    public void useProgram()
    {
        GL20.glUseProgram(id);
    }

    public void stopUsingProgram()
    {
        GL20.glUseProgram(0);
    }

    public void cleanup()
    {
        stopUsingProgram();
        GL20.glDeleteProgram(id);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int atttribute, String variableName)
    {
        GL20.glBindAttribLocation(id, atttribute, variableName);
    }
}