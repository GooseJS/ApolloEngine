package com.goosejs.apollo.client.renderer.glRendering;

import com.goosejs.apollo.backend.lwjgl.opengl.GlobalOrthoMatrix;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexArray;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexBufferObject;
import com.goosejs.apollo.backend.lwjgl.opengl.VertexBufferType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.util.List;

public class VertexBufferUploader
{

    private int VAOID = 0;
    private int VBOID = 0;
    private int VBOCOLID = 0;
    private VertexBufferShader shader;

    public void loadOrthoMatrix(float x, float y, float width, float height)
    {
        if (VBOID == 0)
            setup();

        shader.useProgram();
        shader.loadOrthoMatrix(x, width, y, height, -1, 1);
        shader.stopUsingProgram();
    }

    public void resetOrthoMatrix()
    {
        if (VBOID == 0)
            setup();

        shader.useProgram();
        shader.loadOrthoMatrix(GlobalOrthoMatrix.getGlobalOrthoMatrix());
        shader.stopUsingProgram();
    }

    public void draw(VertexBuffer vertexBuffer)
    {
        if (VBOID == 0)
            setup();

        if (vertexBuffer.getVertexCount() > 0)
        {
            VertexFormat vertexformat = vertexBuffer.getVertexFormat();
            int nextOffset = vertexformat.getNextOffset();
            ByteBuffer bytebuffer = vertexBuffer.getByteBuffer();
            List<VertexFormatElement> list = vertexformat.getElements();

            VertexArray.bindVAO(VAOID);

            for (int i = 0; i < list.size(); ++i)
            {
                VertexFormatElement vertexFormatElement = list.get(i);
                VertexFormatElement.EnumUsage usage = vertexFormatElement.getUsage();
                int glConstant = vertexFormatElement.getType().getGlConstant();
                bytebuffer.position(vertexformat.getOffset(i));

                switch (usage)
                {
                    case POSITION:
                        VertexBufferObject.bindVBO(VertexBufferType.ARRAY_BUFFER, VBOID);
                        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, bytebuffer, GL15.GL_DYNAMIC_DRAW);
                        VertexArray.vertexAttribPointer(0, vertexFormatElement.getElementCount(), glConstant, false, nextOffset, 0);
                        VertexArray.enableAttribArray(0);
                        break;

                    case COLOR:
                        VertexBufferObject.bindVBO(VertexBufferType.ARRAY_BUFFER, VBOCOLID);
                        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, bytebuffer, GL15.GL_DYNAMIC_DRAW);
                        VertexArray.vertexAttribPointer(1, vertexFormatElement.getElementCount(), glConstant, false, nextOffset, 0);
                        VertexArray.enableAttribArray(1);
                        break;
                }
            }

            shader.useProgram();
            GL11.glDrawArrays(vertexBuffer.getDrawMode(), 0, vertexBuffer.getVertexCount());
            shader.stopUsingProgram();

            for (int i = 0; i < list.size(); ++i)
            {
                VertexFormatElement vertexFormatElement = list.get(i);
                VertexFormatElement.EnumUsage usage = vertexFormatElement.getUsage();

                switch (usage)
                {
                    case POSITION:
                        VertexArray.disableAttribArray(0);
                        break;

                    case COLOR:
                        VertexArray.disableAttribArray(0);
                        break;

                }
            }
            VertexArray.unbindVAO();
        }

        vertexBuffer.reset();
    }

    private void setup()
    {
        GlobalOrthoMatrix.addOnMatrixChange((x0, y0, x1, y1) ->
        {
            shader.useProgram();
            shader.loadOrthoMatrix(GlobalOrthoMatrix.getGlobalOrthoMatrix());
            shader.stopUsingProgram();
        });
        VAOID = VertexArray.createVAO();
        VBOID = VertexBufferObject.createVBO();
        VBOCOLID = VertexBufferObject.createVBO();
        shader = new VertexBufferShader();
        shader.useProgram();
        shader.loadOrthoMatrix(GlobalOrthoMatrix.getGlobalOrthoMatrix());
        shader.stopUsingProgram();
    }

}
