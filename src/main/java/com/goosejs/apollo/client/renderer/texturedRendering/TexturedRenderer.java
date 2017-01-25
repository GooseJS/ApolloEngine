package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.util.MatrixUtils;
import org.lwjgl.opengl.GL11;

public class TexturedRenderer
{

    private TexturedShader texturedShader;

    public TexturedRenderer()
    {
        this.texturedShader = new TexturedShader();
        texturedShader.useProgram();
        texturedShader.loadOrthoMatrix(MatrixUtils.createOrthoMatrix(0, 800, 0, 600, -1, 1));
        texturedShader.stopUsingProgram();
    }

    public void drawTexture(TexturedPrimitive2D texturedPrimitive, float x, float y)
    {
        texturedShader.useProgram();
        texturedPrimitive.getTexture().bindTexture();
        VAO.bindVAO(texturedPrimitive.getVAOID());
        VAO.enableAttribArray(0, 1);
        texturedShader.loadTranslation(x, y);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, texturedPrimitive.getVertexCount());
        VAO.disableAttribArray(0, 1);
        texturedShader.stopUsingProgram();
    }

}