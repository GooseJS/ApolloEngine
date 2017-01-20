package com.goosejs.apollo.entity.data;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

public interface IEntityData
{

    boolean addSubSystem(EntitySubSystem subSystem, boolean force);

    boolean containsSubSystem(String subSystem);

    EntitySubSystem removeSubSystem(String subSystem);

    EntitySubSystem getSubSystem(String subSystem);

    void instantiate(Entity newInstance);

    void destroy();
}