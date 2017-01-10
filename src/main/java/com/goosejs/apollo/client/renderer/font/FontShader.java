package com.goosejs.apollo.client.renderer.font;

import com.goosejs.apollo.shader.ShaderProgram;
import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class FontShader extends ShaderProgram
{

	private static final String VERTEX_FILE = "shaders/fontShaders/shader.vert";
	private static final String FRAGMENT_FILE = "shaders/fontShaders/shader.frag";
	
	public FontShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
        super.bindAttribute(0, "positions");
        super.bindAttribute(1, "textureCoordinates");
	}

    public void loadOrthoMatrix(int left, int right, int top, int bottom, int near, int far)
    {
        super.loadMatrix4f(super.getUniformLocation("orthoMatrix"), MatrixUtils.createOrthoMatrix(left, right, top, bottom, near, far));
    }

    public void loadOrthoMatrix(Matrix4f matrix)
    {
        super.loadMatrix4f(super.getUniformLocation("orthoMatrix"), matrix);
    }

    public void loadColor(float r, float g, float b)
    {
        loadColor(new Vector3f(r, g, b));
    }

    public void loadColor(Vector3f color)
    {
        super.loadVector3f(super.getUniformLocation("color"), color);
    }

//    public void loadAlpha(float a) // TODO: See if I can get this working
//    {
//        super.loadFloat(super.getUniformLocation("alpha"), a);
//    }

}
