package com.goosejs.apollo.entity;

import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

import java.util.ArrayList;

/**
 * The base class for the Entity Component System
 * Allows for further functionality to be built on
 * top of this Entity base class
 */
public class Entity
{

    // TODO: Further documentation of this class

    private final ArrayList<EntitySubSystem> subSystems;

    protected Entity()
    {
        subSystems = new ArrayList<>();
    }

    protected boolean addSubsystem(EntitySubSystem subSystem)
    {
        return !containsSubsystem(subSystem.getSubsystemName()) && subSystems.add(subSystem);
    }

    public final boolean containsSubsystem(String subsystemName)
    {
        return getSubsystem(subsystemName) != null;
    }

    public final EntitySubSystem getSubsystem(String subsystemName)
    {
        // TODO: Look into making this method more efficient, because currently it is not practical to call this every tick
        for (EntitySubSystem subSystem : subSystems)
        {
            if (subSystem.getSubsystemName().equals(subsystemName))
                return subSystem;
        }

        return null;
    }

    public void instantiate()
    {
        for (EntitySubSystem subSystem : subSystems) subSystem.instantiate(true);
        onInstantiation();
    }

    public void destroy()
    {
        for (EntitySubSystem subSystem : subSystems) subSystem.destroy(true);
        onDestruction();
    }

    protected void onInstantiation() { /* NO OP */ }
    protected void onDestruction() { /* NO OP */ }

}