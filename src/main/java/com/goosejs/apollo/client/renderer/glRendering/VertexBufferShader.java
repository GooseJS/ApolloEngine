package com.goosejs.apollo.client.renderer.glRendering;

import com.goosejs.apollo.shader.ShaderProgram;
import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;

public class VertexBufferShader extends ShaderProgram
{
    public VertexBufferShader()
    {
        super("shaders/vertexBufferShaders/com.goosejs.apollo.shader.vert", "shaders/vertexBufferShaders/com.goosejs.apollo.shader.frag");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "positions");
        super.bindAttribute(1, "color");
    }

    public void loadOrthoMatrix(float left, float right, float top, float bottom, float near, float far)
    {
        super.loadMatrix4f(super.getUniformLocation("orthoMatrix"), MatrixUtils.createOrthoMatrix(left, right, top, bottom, near, far));
    }

    public void loadOrthoMatrix(Matrix4f matrix)
    {
        super.loadMatrix4f(super.getUniformLocation("orthoMatrix"), matrix);
    }
}
