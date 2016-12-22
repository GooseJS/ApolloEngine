package backend.lwjgl.glfw;

import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The Keyboard Callback implementation to check for key events
 * Allows for adding more callbacks by calling {@link #addKeyboardCallback}, will be fired every time a key is pressed
 */
public class ExtendableKeyboardCallback extends GLFWKeyCallback implements KeyboardCallback
{

    /**
     * TO-DO List:
     * TODO: Add window checking support
     * TODO: Add modifier support
     * TODO: Make the callback modular (allow support for extensions)
     */

    /**
     * Holds the additional Keyboard Callbacks
     */
    private ArrayList<KeyboardCallback> keyboardCallbacks = new ArrayList<>();

    /** An array of all the possible keys that could be pressed (holds repeat events) */
    private boolean[] keys = new boolean[4096];
    /** An array of all the possible keys that could be pressed (holds single-press events) */
    private boolean[] repeatKeys = new boolean[4096];

    /**
     * Called by GLFW every time is a key is pressed
     * @param window the window that was active when the key was pressed
     * @param key the keycode that was pressed
     * @param scancode the scancode that was pressed
     * @param action the action that occurred (Pressed, released, repeated)
     * @param mods any modifiers that were active on the key (shift, control, alt, etc.)
     */
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods)
    {
        if (key >= 0)
        {
            keys[key] = action != GLFW_RELEASE;
            repeatKeys[key] = action != GLFW_RELEASE && action != GLFW_REPEAT;

            keyboardCallbacks.forEach(keyCallback -> keyCallback.invoke(window, key, scancode, action, mods));
        }
    }

    /**
     * Adds a keyboard callback that will be fired every time a key is pressed
     * @param callback the callback to add
     */
    public void addKeyboardCallback(KeyboardCallback callback)
    {
        keyboardCallbacks.add(callback);
    }

    /**
     * Removes a keyboard callback so it is no longer fired every time
     * @param callback the callback to remove
     */
    public boolean removeKeyboardCallback(KeyboardCallback callback)
    {
        return keyboardCallbacks.remove(callback);
    }

    /**
     * Called to check if a key is currently held down
     * @param key the key to check (GLFW key values)
     * @return true if they key is down, false otherwise
     */
    public boolean isKeyDown(int key)
    {
        return key < keys.length && keys[key];
    }

    /**
     * Called to check if a key was pressed down for a single update
     * @param key the key to check (GLFW key values)
     * @return true if the key was down, false otherwise
     */
    public boolean isKeyJustDown(int key)
    {
        if (key < repeatKeys.length)
        {
            if (repeatKeys[key])
            {
                repeatKeys[key] = false;
                return true;
            }
        }
        return false;
    }

    /**
     * @return the keys arrays
     */
    public boolean[] getKeys()
    {
        return keys;
    }

    /**
     * @return the repeat keys array
     */
    public boolean[] getRepeatKeys()
    {
        return repeatKeys;
    }

}