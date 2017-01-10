package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.shader.ShaderProgram;

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
}
