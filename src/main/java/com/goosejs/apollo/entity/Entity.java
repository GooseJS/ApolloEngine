package com.goosejs.apollo.entity;

import com.goosejs.apollo.entity.subsystems.EntitySubSystem;

import java.util.ArrayList;

/**
 * The base class for the Entity Component System
 * Allows for further functionality to be built on
 * top of this Entity base class
 */
public class Entity<T extends Entity>
{

    // TODO: Further documentation of this class

    private final ArrayList<EntitySubSystem> subSystems;

    private boolean shouldUseCustomInstantiation;

    protected Entity()
    {
        subSystems = new ArrayList<>();
    }

    protected boolean addSubsystem(EntitySubSystem subSystem)
    {
        return addSubsystem(subSystem, false);
    }

    protected boolean addSubsystem(EntitySubSystem subSystem, boolean force)
    {
        if (containsSubsystem(subSystem.getSubsystemName()))
        {
            if (!force)
                return false;
            else
            {
                subSystems.remove(getSubsystem(subSystem.getSubsystemName()));
            }
        }

        subSystem.setParentEntity(this);
        subSystem.onCreate();
        return subSystems.add(subSystem);
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

    public T instantiate()
    {
        T newInstance;

        if (shouldUseCustomInstantiation) newInstance = onInstantiation();
        else try
        {
            newInstance = (T) getClass().newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            e.printStackTrace();
            return null;
            // TODO: Handle this
        }

        for (EntitySubSystem subSystem : subSystems) newInstance.addSubsystem(subSystem.instantiate(), true);

        return newInstance;
    }

    public void destroy()
    {
        for (EntitySubSystem subSystem : subSystems) subSystem.onDestroy();
        onDestruction();
    }

    protected T onInstantiation() { /* NO OP */ return null; }
    protected void customInstantiation() { shouldUseCustomInstantiation = true; }
    protected void onDestruction() { /* NO OP */ }

}