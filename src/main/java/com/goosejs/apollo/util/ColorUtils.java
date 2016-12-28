package com.goosejs.apollo.util;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class ColorUtils
{

    private ColorUtils() {}

    public static Vector4f intColorToVector4f(int color)
    {
        float r = (float)(color >> 24 & 255) / 255.0f;
        float g = (float)(color >> 16 & 255) / 255.0f;
        float b = (float)(color >> 8 & 255) / 255.0f;
        float a = (float)(color & 255) / 255.0f;

        return new Vector4f(r, g, b, a);
    }

    public static Vector3f intColorToVector3f(int color)
    {
        float r = (float)(color >> 24 & 255) / 255.0f;
        float g = (float)(color >> 16 & 255) / 255.0f;
        float b = (float)(color >> 8 & 255) / 255.0f;

        return new Vector3f(r, g, b);
    }

    public static int rgbToIntColor(int r, int g, int b)
    {
        return Integer.valueOf(String.format("%02x%02x%02x", r, g, b), 16);
    }

}