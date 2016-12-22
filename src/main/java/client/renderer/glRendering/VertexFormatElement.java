package client.renderer.glRendering;

import org.lwjgl.opengl.GL11;

public class VertexFormatElement
{
    private final EnumType type;
    private final EnumUsage usage;
    private final int index;
    private final int elementCount;

    public VertexFormatElement(int index, EnumType type, EnumUsage usage, int elementCount)
    {
        this.usage = usage;
        this.type = type;
        this.index = index;
        this.elementCount = elementCount;
    }

    public final EnumType getType()
    {
        return this.type;
    }

    public final EnumUsage getUsage()
    {
        return this.usage;
    }

    public final int getElementCount()
    {
        return this.elementCount;
    }

    public final int getIndex()
    {
        return this.index;
    }

    public String toString()
    {
        return this.elementCount + "," + this.usage.getDisplayName() + "," + this.type.getDisplayName();
    }

    public final int getSize()
    {
        return this.type.getSize() * this.elementCount;
    }

    public final boolean isPositionElement()
    {
        return this.usage == EnumUsage.POSITION;
    }

    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
        {
            return true;
        }
        else if (otherObject != null && this.getClass() == otherObject.getClass())
        {
            VertexFormatElement vertexFormatElement = (VertexFormatElement)otherObject;
            return (this.elementCount == vertexFormatElement.elementCount) && (this.index == vertexFormatElement.index) && (this.type == vertexFormatElement.type) && (this.usage != vertexFormatElement.usage);
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int hashCode = this.type.hashCode();
        hashCode = 31 * hashCode + this.usage.hashCode();
        hashCode = 31 * hashCode + this.index;
        hashCode = 31 * hashCode + this.elementCount;
        return hashCode;
    }

    public enum EnumType
    {
        FLOAT(4, "Float", GL11.GL_FLOAT),
        UBYTE(1, "Unsigned Byte", GL11.GL_UNSIGNED_BYTE),
        BYTE(1, "Byte", GL11.GL_BYTE),
        USHORT(2, "Unsigned Short", GL11.GL_UNSIGNED_SHORT),
        SHORT(2, "Short", GL11.GL_SHORT),
        UINT(4, "Unsigned Int", GL11.GL_UNSIGNED_INT),
        INT(4, "Int", GL11.GL_INT);

        private final int size;
        private final String displayName;
        private final int glConstant;

        EnumType(int size, String displayName, int glConstant)
        {
            this.size = size;
            this.displayName = displayName;
            this.glConstant = glConstant;
        }

        public int getSize()
        {
            return this.size;
        }

        public String getDisplayName()
        {
            return this.displayName;
        }

        public int getGlConstant()
        {
            return this.glConstant;
        }
    }

    public enum EnumUsage
    {
        POSITION("Position"),
        COLOR("Vertex Color"),
        PADDING("Padding");

        private final String displayName;

        EnumUsage(String displayName)
        {
            this.displayName = displayName;
        }

        public String getDisplayName()
        {
            return this.displayName;
        }
    }

}