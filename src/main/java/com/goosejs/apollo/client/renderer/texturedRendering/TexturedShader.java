package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.shader.ShaderProgram;
import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

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
    Vector3f position = new Vector3f();
    public void loadTranslation(float x, float y, float z, float rotX, float rotY, float rotZ)
    {
        MatrixUtils.createTransformationMatrix(position.set(x, y, z), rotX, rotY, rotZ, 1, matrix);
        super.loadMatrix4f(super.getUniformLocation("translationMatrix"), matrix);
    }
}
