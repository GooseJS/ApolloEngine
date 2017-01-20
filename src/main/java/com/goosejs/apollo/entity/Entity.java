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

    public void destroy()
    {
        entityData.destroy();
        onDestruction();
    }

    protected T onInstantiation() { /* NO OP */ return null; }
    protected void customInstantiation() { shouldUseCustomInstantiation = true; }
    protected void onDestruction() { /* NO OP */ }

}