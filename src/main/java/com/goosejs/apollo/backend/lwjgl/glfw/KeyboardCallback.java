package com.goosejs.apollo.backend.lwjgl.glfw;

/**
 * The Keyboard Callback implementation to check for key events
 */
public interface KeyboardCallback
{

    /**
     * Called by GLFW every time is a key is pressed
     * @param window the window that was active when the key was pressed
     * @param key the keycode that was pressed
     * @param scancode the scancode that was pressed
     * @param action the action that occurred (Pressed, released, repeated)
     * @param mods any modifiers that were active on the key (shift, control, alt, etc.)
     */
    void invoke(long window, int key, int scancode, int action, int mods);

}
