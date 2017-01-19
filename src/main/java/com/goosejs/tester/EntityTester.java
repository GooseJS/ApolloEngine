package com.goosejs.tester;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystemPosition;

public class EntityTester extends Entity
{

    public EntityTester()
    {
        addSubsystem(new EntitySubSystemPosition(0, 0, 0));
    }

}