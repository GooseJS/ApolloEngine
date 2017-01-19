package com.goosejs.apollo.entity.subsystems;

public class EntitySubSystemPosition extends EntitySubSystem
{
    private float x;
    private float y;
    private float z;

    public EntitySubSystemPosition(float x, float y, float z)
    {
        super("Position", 0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    public float getZ()
    {
        return this.z;
    }

    @Override
    public void doUpdate()
    {
        // NO OP
    }
}