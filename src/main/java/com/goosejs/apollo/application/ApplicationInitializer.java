package com.goosejs.apollo.application;

public class ApplicationInitializer
{

    private ApplicationInitializer() {}

    public static boolean initApplicationAndPlugins(ApplicationBase applicationBase)
    {
        // TODO: Implement plugin support
        if (applicationBase.preInit())
            if (applicationBase.init())
                if (applicationBase.postInit())
                        return true;

        return false;
    }

}