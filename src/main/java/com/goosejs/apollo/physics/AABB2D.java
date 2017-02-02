package com.goosejs.apollo.physics;

public class AABB2D
{

    private float x;
    private float y;
    private float width;
    private float height;

    public AABB2D(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    public float getWidth()
    {
        return this.width;
    }

    public float getHeight()
    {
        return this.height;
    }

    public float getCenterX()
    {
        return (this.width / 2f) + x;
    }

    public float getCenterY()
    {
        return (this.height / 2f) + y;
    }

    public float getRadiusX()
    {
        return getWidth() * 0.5f;
    }

    public float getRadiusY()
    {
        return getHeight() * 0.5f;
    }

}