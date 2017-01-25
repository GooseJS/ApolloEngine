package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.shader.ShaderProgram;
import org.joml.Matrix4f;

import java.io.File;

public class TexturedShader extends ShaderProgram
{

    public TexturedShader()
    {
        super("shaders/textureShaders/shader.vert", "shaders/textureShaders/shader.frag");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "positions");
        super.bindAttribute(1, "textureCoordinates");
    }

    public void loadOrthoMatrix(Matrix4f matrix)
    {
        super.loadMatrix4f(super.getUniformLocation("orthoMatrix"), matrix);
    }

    Matrix4f matrix = new Matrix4f();
    public void loadTranslation(float x, float y)
    {
        matrix.zero();
        matrix.translation(x, y, 0);
        super.loadMatrix4f(super.getUniformLocation("translationMatrix"), matrix);
    }
}
