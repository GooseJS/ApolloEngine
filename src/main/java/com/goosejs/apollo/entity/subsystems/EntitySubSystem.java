package com.goosejs.apollo.entity.subsystems;

/**
 * The base class that all Entity Subsystems should extend
 * It allows you to extend the functionality of a basic entity
 */
public abstract class EntitySubSystem
{

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
     * Should be called when the parent entity has this subsystem applied to it, or if it is applied before
     * the entity is instantiated, then it should be called when the parent entity is instantiated, whichever
     * comes first
     * @param relationToEntity if true, then it was called with the entities instantiate method (when the entity was
     *                         instantiated) (I.E. the subsystem was not added from the entity)
     *                         if false, then it was called when the subsystem was added from the entity
     */
    public final void instantiate(boolean relationToEntity)
    {
        onInstantiation(relationToEntity);
    }

    /**
     * Should be called when the parent entity has the subsystem removed from it, or if it is still attached
     * when the entity is destroyed, then it should be called when the parent entity is destroyed, whichever
     * comes first
     * @param relationToEntity if true, then it was called with the entities destroy method (when the entity was
     *                         destroyed) (I.E. the subsystem was not removed from the entity)
     *                         if false, then it was called when the subsystem was removed from the entity
     */
    public final void destroy(boolean relationToEntity)
    {
        onDestruction(relationToEntity);
    }

    /**
     * @return the name of the subsystem
     */
    public final String getSubsystemName()
    {
        return this.subsystemName;
    }

    /**
     * Called once the specified number of ticks have elapsed since the last update
     */
    protected abstract void doUpdate();

    /**
     * Called on instantiation of subsystem
     * @param relationToEntity if true, then it was called with the entities instantiate method (when the entity was
     *                         instantiated) (I.E. the subsystem was not added from the entity)
     *                         if false, then it was called when the subsystem was added from the entity
     */
    protected void onInstantiation(boolean relationToEntity) { /* NO OP */ }

    /**
     * Called on destruction of subsystem
     * @param relationToEntity if true, then it was called with the entities destroy method (when the entit
     *                         destroyed) (I.E. the subsystem was not removed from the entity)
     *                         if false, then it was called when the subsystem was removed from the entity
     */
    protected void onDestruction(boolean relationToEntity) { /* NO OP */ }


}