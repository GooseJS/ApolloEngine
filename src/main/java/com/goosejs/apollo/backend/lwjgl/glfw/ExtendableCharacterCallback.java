package com.goosejs.apollo.backend.lwjgl.glfw;

import org.lwjgl.glfw.GLFWCharModsCallback;

import java.util.ArrayList;

public class ExtendableCharacterCallback extends GLFWCharModsCallback implements CharacterCallback
{

    /**
     * TO-DO List:
     * TODO: Add window checking support
     * TODO: Add modifier support
     * TODO: Make the callback modular (allow support for extensions)
     */

    private ArrayList<CharacterCallback> characterCallbacks = new ArrayList<>();

    @Override
    public void invoke(long window, int codepoint, int mods)
    {
        //characterCallbacks.forEach(keyCallback -> keyCallback.invoke(window, codepoint, mods));
        for (CharacterCallback callback : characterCallbacks)
        {
            callback.invoke(window, codepoint, mods);
        }
    }

    public void addCharacterCallback(CharacterCallback callback)
    {
        characterCallbacks.add(callback);
    }

    public boolean removeCharacterCallback(CharacterCallback callback)
    {
        return characterCallbacks.remove(callback);
    }
}