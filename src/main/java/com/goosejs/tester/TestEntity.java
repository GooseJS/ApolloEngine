package com.goosejs.tester;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystemPosition;

public class TestEntity extends Entity
{

    private EntitySubSystemPosition position;

    public TestEntity()
    {
        addSubsystem(new EntitySubSystemPosition(0, 0, 0));
        this.position = (EntitySubSystemPosition) getSubsystem("Position");
    }

    public void setX(float x)
    {
        this.position.setX(x);
    }

    public void setY(float y)
    {
        this.position.setY(y);
    }

    public void setZ(float z)
    {
        this.position.setZ(z);
    }

    public float getX()
    {
        return position.getX();
    }

    public float getY()
    {
        return position.getY();
    }

    public float getZ()
    {
        return position.getZ();
    }

}