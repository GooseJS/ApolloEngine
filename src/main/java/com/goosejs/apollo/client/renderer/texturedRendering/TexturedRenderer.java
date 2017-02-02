package com.goosejs.apollo.client.renderer.texturedRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.GlobalPerspectiveMatrices;
import com.goosejs.apollo.backend.lwjgl.opengl.Texture;
import com.goosejs.apollo.backend.lwjgl.opengl.VAO;
import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class TexturedRenderer
{

    private TexturedShader texturedShader;

    private boolean batchRendering = false;
    private int boundVAO = 0;

    public TexturedRenderer()
    {
        this.texturedShader = new TexturedShader();
        texturedShader.useProgram();
        if (GlobalPerspectiveMatrices.has2DBeenInit())
            texturedShader.loadPerspectiveMatrix(GlobalPerspectiveMatrices.getGlobal2DPerspectiveMatrix());
        else
            throw new RuntimeException("GlobalPerspectiveMatrices.update2DPerspectiveMatrix() must be called before creating a TexturedRenderer!");
        texturedShader.stopUsingProgram();
    }

    public void loadPerspectiveMatrix(Matrix4f matrix)
    {
        texturedShader.useProgram();
        texturedShader.loadPerspectiveMatrix(matrix);
        texturedShader.stopUsingProgram();
    }

    public void drawTexture(TexturedPrimitive2D texturedPrimitive, float x, float y, float z, float rotationX, float rotationY, float rotationZ)
    {
        if (!batchRendering)
        {
            texturedShader.useProgram();
            texturedPrimitive.getTexture().bindTexture();
            VAO.bindVAO(texturedPrimitive.getVAOID());
            VAO.enableAttribArray(0, 1);
            texturedShader.loadTranslation(x, y, z, rotationX, rotationY, rotationZ);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, texturedPrimitive.getVertexCount());
            VAO.disableAttribArray(0, 1);
            texturedShader.stopUsingProgram();
        } // TODO: Else
    }

    public void batchRenderStart(int texture)
    {
        batchRendering = true;
        texturedShader.useProgram();
        Texture.bindTexture(texture);
    }

    public void batchRenderDraw(TexturedPrimitive2D primitive, float x, float y, float z, float rotationX, float rotationY, float rotationZ)
    {
        if (primitive.getVAOID() != boundVAO)
        {
            VAO.bindVAO(primitive.getVAOID());
            boundVAO = primitive.getVAOID();
        }
        VAO.enableAttribArray(0, 1);
        texturedShader.loadTranslation(x, y, z, rotationX, rotationY, rotationZ);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, primitive.getVertexCount());
        VAO.disableAttribArray(0, 1);
    }

    public void batchRenderEnd()
    {
        batchRendering = false;
        texturedShader.stopUsingProgram();
    }

}