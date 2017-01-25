package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.util.MatrixUtils;
import org.lwjgl.opengl.GL11;

public class TexturedRenderer
{

    private TexturedShader texturedShader;

    private boolean batchRendering = false;

    public TexturedRenderer()
    {
        this.texturedShader = new TexturedShader();
        texturedShader.useProgram();
        texturedShader.loadOrthoMatrix(MatrixUtils.createOrthoMatrix(0, 800, 0, 600, -1, 1)); // TODO: Global ortho matrix
        texturedShader.stopUsingProgram();
    }

    public void drawTexture(TexturedPrimitive2D texturedPrimitive, float x, float y)
    {
        if (!batchRendering)
        {
            texturedShader.useProgram();
            texturedPrimitive.getTexture().bindTexture();
            VAO.bindVAO(texturedPrimitive.getVAOID());
            VAO.enableAttribArray(0, 1);
            texturedShader.loadTranslation(x, y);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, texturedPrimitive.getVertexCount());
            VAO.disableAttribArray(0, 1);
            texturedShader.stopUsingProgram();
        } // TODO: Else
    }

    public void batchRenderStart(Texture texture)
    {
        batchRendering = true;
        texturedShader.useProgram();
        texture.bindTexture();
    }

    public void batchRenderDraw(TexturedPrimitive2D primitive, float x, float y)
    {
        VAO.bindVAO(primitive.getVAOID());
        VAO.enableAttribArray(0, 1);
        texturedShader.loadTranslation(x, y);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, primitive.getVertexCount());
        VAO.disableAttribArray(0, 1);
    }

    public void batchRenderEnd()
    {
        batchRendering = false;
        texturedShader.stopUsingProgram();
    }

}