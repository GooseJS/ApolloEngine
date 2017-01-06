package com.goosejs.apollo.application.applicationLoop;

import com.goosejs.apollo.application.LoopingApplicationBase;

/**
 * Covers the basic functions of an application loop
 * Using in the {@link LoopingApplicationBase}
 */
public interface IApplicationLoop
{
    /** Will set the application base that is being used by this loop */
    void setApplicationBase(LoopingApplicationBase applicationBase);

    /** Will start looping the application */
    void startLoop();

    /** Will stop looping the application */
    void stopLoop();

    /** Will return the delta time for the current loop */
    double getDeltaTime();
}
