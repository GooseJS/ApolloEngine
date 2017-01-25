package com.goosejs.apollo.entity.data;

import com.goosejs.apollo.entity.Entity;
import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

import java.util.HashMap;

public class EntitySubSystemInformationHashmap implements IEntitySubSystemInformation
{
    private final HashMap<String, EntitySubSystem> subSystems;
    private final Entity parentEntity;

    public EntitySubSystemInformationHashmap(Entity parentEntity)
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
        return subSystems.containsKey(subSystem.toLowerCase());
    }

    @Override
    public EntitySubSystem removeSubSystem(String subSystem)
    {
        if (containsSubSystem(subSystem))
            return subSystems.remove(subSystem.toLowerCase());

        return null;
    }

    @Override
    public EntitySubSystem getSubSystem(String subSystem)
    {
        return subSystems.get(subSystem.toLowerCase());
    }

    @Override
    public void instantiate(Entity newInstance)
    {
        for (String subSystemName : subSystems.keySet()) newInstance.addSubSystem(subSystems.get(subSystemName.toLowerCase()).instantiate(), true);
    }

    @Override
    public void destroy()
    {
        for (String subSystemName : subSystems.keySet())
        {
            subSystems.get(subSystemName.toLowerCase()).onDestroy();
            subSystems.remove(subSystemName.toLowerCase());
        }
    }
}
