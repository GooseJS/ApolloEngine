package com.goosejs.apollo.util;

public class MathUtils
{

    private MathUtils() {}

    public static int roundUp(int number, int interval)
    {
        if (interval == 0)
        {
            return 0;
        }
        else if (number == 0)
        {
            return interval;
        }
        else
        {
            if (number < 0)
            {
                interval *= -1;
            }

            int i = number % interval;
            return i == 0 ? number : number + interval - i;
        }
    }

    public static int clamp_int(int num, int min, int max)
    {
        return num < min ? min : (num > max ? max : num);
    }
}