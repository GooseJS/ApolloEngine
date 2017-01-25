package com.goosejs.apollo.client.renderer.glRendering;

import com.goosejs.apollo.util.Logger;
import com.goosejs.apollo.util.MathUtils;

import java.nio.*;
import java.util.Arrays;
import java.util.BitSet;

public class VertexBuffer
{
    private ByteBuffer byteBuffer;
    private IntBuffer rawIntBuffer;
    private ShortBuffer rawShortBuffer;
    private FloatBuffer rawFloatBuffer;
    private int vertexCount;
    private VertexFormatElement vertexFormatElement;
    private int vertexFormatIndex;

    private boolean noColor;
    private int drawMode;
    private double xOffset;
    private double yOffset;
    private double zOffset;
    private VertexFormat vertexFormat;
    private boolean isDrawing;

    public VertexBuffer(int bufferSizeIn)
    {
        this.byteBuffer = GLAllocation.createDirectByteBuffer(bufferSizeIn * 4);
        this.rawIntBuffer = this.byteBuffer.asIntBuffer();
        this.rawShortBuffer = this.byteBuffer.asShortBuffer();
        this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
    }

    private void growBuffer(int newSize)
    {
        // Checks if the current size is greater than or equal to the new size, and if its not
        // then it actually grows the buffer
        if (MathUtils.roundUp(newSize, 4) / 4 > this.rawIntBuffer.remaining() || this.vertexCount * this.vertexFormat.getNextOffset() + newSize > this.byteBuffer.capacity())
        {
            int currentCapacity = this.byteBuffer.capacity();
            int newCapacity = currentCapacity + MathUtils.roundUp(newSize, 2097152);
            Logger.debug("Needed to grow BufferBuilder buffer: Old size {} bytes, new size {} bytes.", new Object[] {Integer.valueOf(currentCapacity), Integer.valueOf(newCapacity)});
            int currentPosition = this.rawIntBuffer.position();
            ByteBuffer bytebuffer = GLAllocation.createDirectByteBuffer(newCapacity);
            this.byteBuffer.position(0);
            bytebuffer.put(this.byteBuffer);
            bytebuffer.rewind();
            this.byteBuffer = bytebuffer;
            this.rawFloatBuffer = this.byteBuffer.asFloatBuffer().asReadOnlyBuffer();
            this.rawIntBuffer = this.byteBuffer.asIntBuffer();
            this.rawIntBuffer.position(currentPosition);
            this.rawShortBuffer = this.byteBuffer.asShortBuffer();
            this.rawShortBuffer.position(currentPosition << 1);
        }
    }

    public void sortVertexData(float par1float, float par2float, float par4float)
    {
        int intCount = this.vertexCount / 4;
        final float[] afloat = new float[intCount];

        for (int i = 0; i < intCount; ++i)
        {
            afloat[i] = getDistanceSq(this.rawFloatBuffer, (float)((double)par1float + this.xOffset), (float)((double)par2float + this.yOffset), (float)((double)par4float + this.zOffset), this.vertexFormat.getIntegerSize(), i * this.vertexFormat.getNextOffset());
        }

        Integer[] ainteger = new Integer[intCount];

        for (int i = 0; i < ainteger.length; ++i)
        {
            ainteger[i] = i;
        }

        Arrays.sort(ainteger, (p_compare_1_, p_compare_2_) -> {
            if (afloat[p_compare_2_] < afloat[p_compare_1_])
                return -1;
            else if (afloat[p_compare_1_] < afloat[p_compare_2_])
                return 1;
            else
                return 0;
        });

        BitSet bitset = new BitSet();
        int nextOffset = this.vertexFormat.getNextOffset();
        int[] aint = new int[nextOffset];

        for (int i = bitset.nextClearBit(0); i < ainteger.length; i = bitset.nextClearBit(i + 1))
        {
            int currentDistance = ainteger[i];

            if (currentDistance != i)
            {
                this.rawIntBuffer.limit(currentDistance * nextOffset + nextOffset);
                this.rawIntBuffer.position(currentDistance * nextOffset);
                this.rawIntBuffer.get(aint);
                int currentDistanceToChange = currentDistance;

                for (int i1 = ainteger[currentDistance]; currentDistanceToChange != i; i1 = ainteger[i1])
                {
                    this.rawIntBuffer.limit(i1 * nextOffset + nextOffset);
                    this.rawIntBuffer.position(i1 * nextOffset);
                    IntBuffer intbuffer = this.rawIntBuffer.slice();
                    this.rawIntBuffer.limit(currentDistanceToChange * nextOffset + nextOffset);
                    this.rawIntBuffer.position(currentDistanceToChange * nextOffset);
                    this.rawIntBuffer.put(intbuffer);
                    bitset.set(currentDistanceToChange);
                    currentDistanceToChange = i1;
                }

                this.rawIntBuffer.limit(i * nextOffset + nextOffset);
                this.rawIntBuffer.position(i * nextOffset);
                this.rawIntBuffer.put(aint);
            }

            bitset.set(i);
        }
    }

    public State getVertexState()
    {
        this.rawIntBuffer.rewind();
        int bufferSize = this.getBufferSize();
        this.rawIntBuffer.limit(bufferSize);
        int[] aint = new int[bufferSize];
        this.rawIntBuffer.get(aint);
        this.rawIntBuffer.limit(this.rawIntBuffer.capacity());
        this.rawIntBuffer.position(bufferSize);
        return new State(aint, new VertexFormat(this.vertexFormat));
    }

    private int getBufferSize()
    {
        return this.vertexCount * this.vertexFormat.getIntegerSize();
    }

    private static float getDistanceSq(FloatBuffer floatBuffer, float par1float, float par2float, float par3float, int par5float, int par6float)
    {
        float f = floatBuffer.get(par6float);
        float f1 = floatBuffer.get(par6float + 1);
        float f2 = floatBuffer.get(par6float + 2);
        float f3 = floatBuffer.get(par6float + par5float);
        float f4 = floatBuffer.get(par6float + par5float + 1);
        float f5 = floatBuffer.get(par6float + par5float + 2);
        float f6 = floatBuffer.get(par6float + par5float * 2);
        float f7 = floatBuffer.get(par6float + par5float * 2 + 1);
        float f8 = floatBuffer.get(par6float + par5float * 2 + 2);
        float f9 = floatBuffer.get(par6float + par5float * 3);
        float f10 = floatBuffer.get(par6float + par5float * 3 + 1);
        float f11 = floatBuffer.get(par6float + par5float * 3 + 2);
        float f12 = (f + f3 + f6 + f9) * 0.25F - par1float;
        float f13 = (f1 + f4 + f7 + f10) * 0.25F - par2float;
        float f14 = (f2 + f5 + f8 + f11) * 0.25F - par3float;
        return f12 * f12 + f13 * f13 + f14 * f14;
    }

    public void setVertexState(State state)
    {
        this.rawIntBuffer.clear();
        this.growBuffer(state.getRawBuffer().length * 4);
        this.rawIntBuffer.put(state.getRawBuffer());
        this.vertexCount = state.getVertexCount();
        this.vertexFormat = new VertexFormat(state.getVertexFormat());
    }

    public void reset()
    {
        this.vertexCount = 0;
        this.vertexFormatElement = null;
        this.vertexFormatIndex = 0;
    }

    public void begin(int glMode, VertexFormat format)
    {
        if (this.isDrawing)
        {
            throw new IllegalStateException("Already building!");
        }
        else
        {
            this.isDrawing = true;
            this.reset();
            this.drawMode = glMode;
            this.vertexFormat = format;
            this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
            this.noColor = false;
            this.byteBuffer.limit(this.byteBuffer.capacity());
        }
    }

    public VertexBuffer tex(double u, double v)
    {
        int offset = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(offset, (float)u);
                this.byteBuffer.putFloat(offset + 4, (float)v);
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(offset, (int)u);
                this.byteBuffer.putInt(offset + 4, (int)v);
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(offset, (short)((int)v));
                this.byteBuffer.putShort(offset + 2, (short)((int)u));
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(offset, (byte)((int)v));
                this.byteBuffer.put(offset + 1, (byte)((int)u));
        }

        this.nextVertexFormatIndex();
        return this;
    }

    public void putPosition(double x, double y)
    {
        double z = 0;
        int vertexFormatSize = this.vertexFormat.getIntegerSize();
        int fullVertexFormatSize = (this.vertexCount - 4) * vertexFormatSize;

        for (int i = 0; i < 4; ++i)
        {
            int xOffset = fullVertexFormatSize + i * vertexFormatSize;
            int yOffset = xOffset + 1;
            int zOffset = yOffset + 1;
            this.rawIntBuffer.put(xOffset, Float.floatToRawIntBits((float)(x + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(xOffset))));
            this.rawIntBuffer.put(yOffset, Float.floatToRawIntBits((float)(y + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(yOffset))));
            this.rawIntBuffer.put(zOffset, Float.floatToRawIntBits((float)(z + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(zOffset))));
        }
    }

    /**
     * Gets the color index.
     */
    private int getColorIndex(int index)
    {
        return ((this.vertexCount - index) * this.vertexFormat.getNextOffset() + this.vertexFormat.getColorOffset()) / 4;
    }

    public void putColorMultiplier(float red, float green, float blue, int index)
    {
        int colorIndex = this.getColorIndex(index);
        int color = -1;

        if (!this.noColor)
        {
            color = this.rawIntBuffer.get(colorIndex);

            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
            {
                int redValue = (int)((float)(color & 255) * red);
                int greenValue = (int)((float)(color >> 8 & 255) * green);
                int blueValue = (int)((float)(color >> 16 & 255) * blue);
                color = color & -16777216;
                color = color | blueValue << 16 | greenValue << 8 | redValue;
            }
            else
            {
                int redValue = (int)((float)(color >> 24 & 255) * red);
                int greenValue = (int)((float)(color >> 16 & 255) * green);
                int blueValue = (int)((float)(color >> 8 & 255) * blue);
                color = color & 255;
                color = color | redValue << 24 | greenValue << 16 | blueValue << 8;
            }
        }

        this.rawIntBuffer.put(colorIndex, color);
    }

    private void putColor(int argb, int index)
    {
        int colorIndex = this.getColorIndex(index);
        int red = argb >> 16 & 255;
        int green = argb >> 8 & 255;
        int blue = argb & 255;
        int alpha = argb >> 24 & 255;
        this.putColorRGBA(colorIndex, red, green, blue, alpha);
    }

    public void putColorRGB_F(float red, float green, float blue, int index)
    {
        int colorIndex = this.getColorIndex(index);
        int redValue = MathUtils.clamp_int((int)(red * 255.0F), 0, 255);
        int greenValue = MathUtils.clamp_int((int)(green * 255.0F), 0, 255);
        int blueValue = MathUtils.clamp_int((int)(blue * 255.0F), 0, 255);
        this.putColorRGBA(colorIndex, redValue, greenValue, blueValue, 255);
    }

    private void putColorRGBA(int index, int red, int green, int blue, int alpha)
    {
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
        {
            this.rawIntBuffer.put(index, alpha << 24 | blue << 16 | green << 8 | red);
        }
        else
        {
            this.rawIntBuffer.put(index, red << 24 | green << 16 | blue << 8 | alpha);
        }
    }

    /**
     * Disables color processing.
     */
    public void noColor()
    {
        this.noColor = true;
    }

    public VertexBuffer color(float red, float green, float blue, float alpha)
    {
        return this.color((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), (int)(alpha * 255.0F));
    }

    public VertexBuffer color(int red, int green, int blue, int alpha)
    {
        if (this.noColor)
        {
            return this;
        }
        else
        {
            int offset = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);

            switch (this.vertexFormatElement.getType())
            {
                case FLOAT:
                    this.byteBuffer.putFloat(offset, (float)red / 255.0F);
                    this.byteBuffer.putFloat(offset + 4, (float)green / 255.0F);
                    this.byteBuffer.putFloat(offset + 8, (float)blue / 255.0F);
                    this.byteBuffer.putFloat(offset + 12, (float)alpha / 255.0F);
                    break;

                case UINT:
                case INT:
                    this.byteBuffer.putFloat(offset, (float)red);
                    this.byteBuffer.putFloat(offset + 4, (float)green);
                    this.byteBuffer.putFloat(offset + 8, (float)blue);
                    this.byteBuffer.putFloat(offset + 12, (float)alpha);
                    break;

                case USHORT:
                case SHORT:
                    this.byteBuffer.putShort(offset, (short)red);
                    this.byteBuffer.putShort(offset + 2, (short)green);
                    this.byteBuffer.putShort(offset + 4, (short)blue);
                    this.byteBuffer.putShort(offset + 6, (short)alpha);
                    break;

                case UBYTE:
                case BYTE:
                    if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
                    {
                        this.byteBuffer.put(offset, (byte)red);
                        this.byteBuffer.put(offset + 1, (byte)green);
                        this.byteBuffer.put(offset + 2, (byte)blue);
                        this.byteBuffer.put(offset + 3, (byte)alpha);
                    }
                    else
                    {
                        this.byteBuffer.put(offset, (byte)alpha);
                        this.byteBuffer.put(offset + 1, (byte)blue);
                        this.byteBuffer.put(offset + 2, (byte)green);
                        this.byteBuffer.put(offset + 3, (byte)red);
                    }
            }

            this.nextVertexFormatIndex();
            return this;
        }
    }

    public void addVertexData(int[] vertexData)
    {
        this.growBuffer(vertexData.length * 4);
        this.rawIntBuffer.position(this.getBufferSize());
        this.rawIntBuffer.put(vertexData);
        this.vertexCount += vertexData.length / this.vertexFormat.getIntegerSize();
    }

    public void endVertex()
    {
        ++this.vertexCount;
        this.growBuffer(this.vertexFormat.getNextOffset());
    }

    public VertexBuffer pos(double x, double y)
    {
        double z = 0;
        int offset = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.getOffset(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(offset, (float)(x + this.xOffset));
                this.byteBuffer.putFloat(offset + 4, (float)(y + this.yOffset));
                this.byteBuffer.putFloat(offset + 8, (float)(z + this.zOffset));
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(offset, Float.floatToRawIntBits((float)(x + this.xOffset)));
                this.byteBuffer.putInt(offset + 4, Float.floatToRawIntBits((float)(y + this.yOffset)));
                this.byteBuffer.putInt(offset + 8, Float.floatToRawIntBits((float)(z + this.zOffset)));
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(offset, (short)((int)(x + this.xOffset)));
                this.byteBuffer.putShort(offset + 2, (short)((int)(y + this.yOffset)));
                this.byteBuffer.putShort(offset + 4, (short)((int)(z + this.zOffset)));
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(offset, (byte)((int)(x + this.xOffset)));
                this.byteBuffer.put(offset + 1, (byte)((int)(y + this.yOffset)));
                this.byteBuffer.put(offset + 2, (byte)((int)(z + this.zOffset)));
        }

        this.nextVertexFormatIndex();
        return this;
    }

    private void nextVertexFormatIndex()
    {
        ++this.vertexFormatIndex;
        this.vertexFormatIndex %= this.vertexFormat.getElementCount();
        this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);

        if (this.vertexFormatElement.getUsage() == VertexFormatElement.EnumUsage.PADDING)
        {
            this.nextVertexFormatIndex();
        }
    }

    public void setTranslation(double x, double y)
    {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = 0;
    }

    public void finishDrawing()
    {
        if (!this.isDrawing)
        {
            throw new IllegalStateException("Not building!");
        }
        else
        {
            this.isDrawing = false;
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.getBufferSize() * 4);
        }
    }

    public ByteBuffer getByteBuffer()
    {
        return this.byteBuffer;
    }

    public VertexFormat getVertexFormat()
    {
        return this.vertexFormat;
    }

    public int getVertexCount()
    {
        return this.vertexCount;
    }

    public int getDrawMode()
    {
        return this.drawMode;
    }

    public void putColor4(int argb)
    {
        for (int i = 0; i < 4; ++i)
        {
            this.putColor(argb, i + 1);
        }
    }

    public void putColorRGB_F4(float red, float green, float blue)
    {
        for (int i = 0; i < 4; ++i)
        {
            this.putColorRGB_F(red, green, blue, i + 1);
        }
    }

    public class State
    {
        private final int[] stateRawBuffer;
        private final VertexFormat stateVertexFormat;

        public State(int[] buffer, VertexFormat format)
        {
            this.stateRawBuffer = buffer;
            this.stateVertexFormat = format;
        }

        public int[] getRawBuffer()
        {
            return this.stateRawBuffer;
        }

        public int getVertexCount()
        {
            return this.stateRawBuffer.length / this.stateVertexFormat.getIntegerSize();
        }

        public VertexFormat getVertexFormat()
        {
            return this.stateVertexFormat;
        }
    }
}