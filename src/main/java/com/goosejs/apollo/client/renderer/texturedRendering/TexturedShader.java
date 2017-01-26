package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.shader.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;

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

    public void loadPerspectiveMatrix(Matrix4f matrix)
    {
        super.loadMatrix4f(super.getUniformLocation("perspectiveMatrix"), matrix);
    }

    Matrix4f matrix = new Matrix4f();
    public void loadTranslation(float x, float y, float z, float rotX, float rotY, float rotZ)
    {
        matrix.translation(x, y, z).rotateX((float)Math.toRadians(rotX)).rotateY((float)Math.toRadians(rotY)).rotateZ((float)Math.toRadians(rotZ));
        super.loadMatrix4f(super.getUniformLocation("translationMatrix"), matrix);
    }
}
