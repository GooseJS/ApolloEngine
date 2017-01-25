package com.goosejs.apollo.util;

import java.util.ArrayList;

public class GLUtils
{

    private GLUtils() {}

    public static void addDataToArrayList(ArrayList vertices, int x0, int y0, int x1, int y1)
    {
        addDataToArrayList(vertices, x0, y0, x1, y1);
    }

    public static void addDataToArrayList(ArrayList vertices, ArrayList texCoord, int x0, int y0, int x1, int y1, int s0, int t0, int s1, int t1)
    {
        addDataToArrayList(vertices, texCoord, x0, y0, x1, y1, s0, t0, s1, t1);
    }

    public static void addDataToArrayList(ArrayList vertices, ArrayList texCoord, float x0, float y0, float x1, float y1, float s0, float t0, float s1, float t1)
    {
        addDataToArrayList(vertices, x0, y0, x1, y1);
        addDataToArrayList(texCoord, s0, t0, s1, t1);
    }

    public static void addDataToArrayList(ArrayList output, float x0, float y0, float x1, float y1)
    {
        output.add(x1);
        output.add(y0);

        output.add(x1);
        output.add(y1);

        output.add(x0);
        output.add(y1);

        output.add(x0);
        output.add(y1);

        output.add(x0);
        output.add(y0);

        output.add(x1);
        output.add(y0);
    }
}