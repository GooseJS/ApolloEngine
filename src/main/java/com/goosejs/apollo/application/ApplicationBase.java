package com.goosejs.apollo.application;

/**
 * An ApplicationBase that all applications will extend
 * It is possible to not use this class and write everything from scratch,
 * however, there is no promise that addons will be initialized in the proper
 * order unless using the provided {@link #preInit()}, {@link #init()}, and
 * {@link #postInit()} functions
 */
public abstract class ApplicationBase
{
    /**
     * Called first after preInit of all addons
     * @return if preInit was successful
     * */
    public boolean preInit() { return true; }

    /**
     * Called after preInit and init of all addons
     * @return if init was successful
     * */
    public boolean init() { return true; }

    /**
     * Called after init and postInit of all addons
     * @return if postInit was successful
     * */
    public boolean postInit() { return true; }
}
