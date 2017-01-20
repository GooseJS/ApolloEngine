package com.goosejs.apollo.entity.data;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

import java.util.HashMap;

public class EntityDataHashmap implements IEntityData
{

    private final HashMap<String, EntitySubSystem> subSystems;
    private final Entity parentEntity;

    public EntityDataHashmap(Entity parentEntity)
    {
        this.parentEntity = parentEntity;
        this.subSystems = new HashMap<>();
    }

    @Override
    public boolean addSubSystem(EntitySubSystem subSystem, boolean force)
    {
        if (containsSubSystem(subSystem.getSubSystemName()))
            if (!force)
                return false;

        subSystem.setParentEntity(parentEntity);
        subSystem.onCreate();
        subSystems.put(subSystem.getSubSystemName().toLowerCase(), subSystem);

        return true;
    }

    @Override
    public boolean containsSubSystem(String subSystem)
    {
        return subSystems.containsKey(subSystem);
    }

    @Override
    public EntitySubSystem removeSubSystem(String subSystem)
    {
        if (containsSubSystem(subSystem))
            return subSystems.remove(subSystem);

        return null;
    }

    @Override
    public EntitySubSystem getSubSystem(String subSystem)
    {
        return subSystems.get(subSystem);
    }
}
