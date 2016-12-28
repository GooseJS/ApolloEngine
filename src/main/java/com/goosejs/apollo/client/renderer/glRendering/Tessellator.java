package com.goosejs.apollo.client.renderer.glRendering;

public class Tessellator
{
    private final VertexBuffer worldRenderer;
    private final VertexBufferUploader vboUploader = new VertexBufferUploader();

    /** The static instance of the Tessellator. */
    private static final Tessellator INSTANCE = new Tessellator(2097152);

    public static Tessellator getInstance()
    {
        return INSTANCE;
    }

    public Tessellator(int bufferSize)
    {
        this.worldRenderer = new VertexBuffer(bufferSize);
    }

    /**
     * Draws the data set up in this tessellator and resets the com.goosejs.apollo.state to prepare for new drawing.
     */
    public void draw()
    {
        this.worldRenderer.finishDrawing();
        this.vboUploader.draw(this.worldRenderer);
    }

    public VertexBuffer getBuffer()
    {
        return this.worldRenderer;
    }

    public void loadOrthoMatrix(float x, float y, float width, float height)
    {
        vboUploader.loadOrthoMatrix(x, y, width, height);
    }

    public void resetOrthoMatrix()
    {
        vboUploader.resetOrthoMatrix();
    }
}
