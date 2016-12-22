package backend.lwjgl.opengl;

import org.lwjgl.opengl.GL15;

/**
 * The different types of VertexBuffers
 */
public enum VertexBufferType
{

    /** GL15.GL_ARRAY_BUFFER */
    ARRAY_BUFFER(GL15.GL_ARRAY_BUFFER),
    /** GL15.GL_ELEMENT_ARRAY_BUFFER */
    INDEX_BUFFER(GL15.GL_ELEMENT_ARRAY_BUFFER);

    /**
     * The int value of the enum
     */
    private final int intValue;

    /**
     * The enum constructor
     * @param intValue the int value
     */
    VertexBufferType(int intValue)
    {
        this.intValue = intValue;
    }

    /**
     * @return the enum
     */
    public int getIntValue()
    {
        return this.intValue;
    }

}