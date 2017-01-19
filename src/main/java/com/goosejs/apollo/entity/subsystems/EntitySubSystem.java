package com.goosejs.apollo.entity.subsystems;

import com.goosejs.apollo.entity.Entity;

/**
 * The base class that all Entity Subsystems should extend
 * It allows you to extend the functionality of a basic entity
 */
public abstract class EntitySubSystem<T extends EntitySubSystem>
{

    /** The parent entity that this subsystem is attached to */
    private Entity parentEntity;

    /** The name of the subsystem */
    private final String subsystemName;
    /** How many ticks elapse before the subsystem gets the {@link #doUpdate()}  method run */
    private final int updateInterval;
    /** How many ticks have elapsed since the last update (this is counting down so actual
     * ticks would be updateInterval - lastUpdate)
     */
    private int lastUpdate;

    /**
     * @param subsystemName the name of the subsystem
     * @param updateInterval how many ticks should elapse before the subsystem gets the {@link #doUpdate()} method run
     */
    protected EntitySubSystem(String subsystemName, int updateInterval)
    {
        this.subsystemName = subsystemName;
        if (updateInterval < 0) updateInterval = 0;
        this.updateInterval = updateInterval;
        this.lastUpdate = updateInterval;
    }

    /**
     * Should be called every tick, however will only do complex things once it is an "update tick", meaning
     * the correct amount of ticks have elapsed since the subsystem was last updated
     */
    public final void update()
    {
        // TODO: Look into efficiency of doing this every tick, it may be nothing however with many Entities with many subsystems it could be resource intensive
        if (updateInterval != 0)
        {
            if (lastUpdate < 0)
            {
                lastUpdate = updateInterval;
                doUpdate();
            }
            lastUpdate--;
        }
    }

    /**
     * @see #parentEntity
     * @implNote this should be called AS SOON AS THE SUBSYSTEM IS ADDED TO AN ENTITY TO INSURE IT IS NOT NULL
     *           if this is null you're gonna have a bad time...
     */
    public final void setParentEntity(Entity parentEntity)
    {
        this.parentEntity = parentEntity;
    }

    /**
     * @see #parentEntity
     */
    public final Entity getParentEntity()
    {
        return this.parentEntity;
    }

    /**
     * @return the name of the subsystem
     */
    public final String getSubsystemName()
    {
        return this.subsystemName;
    }

    //region Abstract Methods

    /**
     * Called once the specified number of ticks have elapsed since the last update
     */
    protected abstract void doUpdate();

    /**
     * Should be called when the parent entity has this subsystem applied to it, or if it is applied before
     * the entity is instantiated, then it should be called when the parent entity is instantiated, whichever
     * comes first
     */
    public abstract T instantiate();

    //endregion

    //region Overloadable Methods

    /**
     * Can be overloaded, will be called when the subsystem is being created in order to allocate memory and initialize
     * objects
     */
    public void onCreate() { /* NO OP */ }

    /**
     * Can be overloaded, will be called when the subsystem is being destroyed in order to free any memory
     */
    public void onDestroy() { /* NO OP */ }

    //endregion


}