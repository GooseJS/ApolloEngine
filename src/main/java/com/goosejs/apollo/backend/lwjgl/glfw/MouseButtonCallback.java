package com.goosejs.apollo.backend.lwjgl.glfw;

public interface MouseButtonCallback
{

    void invoke(long window, int button, int action, int mods);

}