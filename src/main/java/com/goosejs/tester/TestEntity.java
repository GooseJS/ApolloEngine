package com.goosejs.tester;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystemPosition;

public class TestEntity extends Entity
{

    public TestEntity()
    {
        addSubSystem(new EntitySubSystemPosition(0, 0, 0));
    }

    public void setX(float x)
    {
        this.getPositionSubSystem().setX(x);
    }

    public void setY(float y)
    {
        this.getPositionSubSystem().setY(y);
    }

    public void setZ(float z)
    {
        this.getPositionSubSystem().setZ(z);
    }

    public float getX()
    {
        return getPositionSubSystem().getX();
    }

    public float getY()
    {
        return getPositionSubSystem().getY();
    }

    public float getZ()
    {
        return getPositionSubSystem().getZ();
    }

    private EntitySubSystemPosition getPositionSubSystem()
    {
        return (EntitySubSystemPosition) getSubSystem("Position");
    }

}