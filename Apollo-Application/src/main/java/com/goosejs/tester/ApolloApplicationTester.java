package com.goosejs.tester;

import com.goosejs.apollo.application.Application;

public class ApolloApplicationTester
{

    public static void main(String args[])
    {
        Application app = new Application();
        app.openWindow();

        while (!app.shouldCloseWindow())
        {
            app.doShit();
        }

        app.shutdown();
    }

}