package com.goosejs.apollo.entity.data;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityDataArrayList implements IEntityData
{

    private final ArrayList<EntitySubSystem> subSystems;
    private final Entity parentEntity;

    public EntityDataArrayList(Entity parentEntity)
    {
        subSystems = new ArrayList<>();
        this.parentEntity = parentEntity;
    }

    @Override
    public final boolean addSubSystem(EntitySubSystem subSystem, boolean force)
    {
        if (containsSubSystem(subSystem.getSubSystemName()))
        {
            if (!force)
                return false;
            else
            {
                subSystems.remove(getSubSystem(subSystem.getSubSystemName()));
            }
        }

        subSystem.setParentEntity(parentEntity);
        subSystem.onCreate();
        return subSystems.add(subSystem);
    }

    @Override
    public final EntitySubSystem getSubSystem(String subsystemName)
    {
        // TODO: Look into making this method more efficient, because currently it is not practical to call this every tick
        for (EntitySubSystem subSystem : subSystems)
        {
            if (subSystem.getSubSystemName().toLowerCase().equals(subsystemName.toLowerCase()))
                return subSystem;
        }

        return null;
    }

    @Override
    public final boolean containsSubSystem(String subsystemName)
    {
        return getSubSystem(subsystemName) != null;
    }

    @Override
    public EntitySubSystem removeSubSystem(String subSystem)
    {
        Iterator<EntitySubSystem> subSystemIterator = subSystems.iterator();

        while (subSystemIterator.hasNext())
        {
            EntitySubSystem iteratedSubSystem = subSystemIterator.next();
            if (iteratedSubSystem.getSubSystemName().toLowerCase().equals(subSystem.toLowerCase()))
            {
                subSystemIterator.remove();
                return iteratedSubSystem;
            }
        }

        return null;
    }

}
