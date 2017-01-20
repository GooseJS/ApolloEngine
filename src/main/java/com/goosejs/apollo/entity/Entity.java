package com.goosejs.apollo.entity;

import com.goosejs.apollo.entity.data.EntityDataHashmap;
import com.goosejs.apollo.entity.data.IEntityData;
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

    private boolean shouldUseCustomInstantiation;
    private IEntityData entityData;

    protected Entity()
    {
        entityData = new EntityDataHashmap(this);
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

        entityData.instantiate(newInstance);

        return newInstance;
    }

    protected final void customInstantiation() { shouldUseCustomInstantiation = true; }

    public final void addSubSystem(EntitySubSystem subSystem)
    {
        addSubSystem(subSystem, false);
    }

    public final boolean addSubSystem(EntitySubSystem subSystem, boolean force)
    {
        return entityData.addSubSystem(subSystem, force);
    }

    public final EntitySubSystem removeSubSystem(String subSystem)
    {
        return entityData.removeSubSystem(subSystem);
    }

    public final boolean containsSubSystem(String subSystem)
    {
        return entityData.containsSubSystem(subSystem);
    }

    public final EntitySubSystem getSubSystem(String subSystem)
    {
        return entityData.getSubSystem(subSystem);
    }

    public final void destroy()
    {
        entityData.destroy();
        onDestruction();
    }

    protected T onInstantiation() { /* NO OP */ return null; }
    protected void onDestruction() { /* NO OP */ }

}