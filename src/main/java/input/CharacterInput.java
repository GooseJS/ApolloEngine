package input;

import com.goosejs.gchat.backend.lwjgl.glfw.CharacterCallback;
import com.goosejs.gchat.backend.lwjgl.glfw.KeyboardCallback;
import org.lwjgl.glfw.GLFW;

import java.nio.charset.Charset;

public abstract class CharacterInput implements CharacterCallback, KeyboardCallback
{
    private Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public void invoke(long window, int codepoint, int mods)
    {
        combineCallbacks(codepoint, codepoint, GLFW.GLFW_PRESS, mods, false);
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods)
    {
        combineCallbacks(key, key, action, mods, true);
    }

    public void combineCallbacks(int codepoint, int key, int action, int mods, boolean keyboard)
    {
        char rawCharacter = 0;
        String rawString = "";

        if (keyboard)
        {
            if (action == GLFW.GLFW_RELEASE)
                return;

            if (key == GLFW.GLFW_KEY_ENTER)
            {
                rawCharacter = '\n';
                rawString = "KEY_ENTER";
            }
            else if (key == GLFW.GLFW_KEY_BACKSPACE)
            {
                rawCharacter = '\b';
                rawString = "KEY_BACKSPACE";
            }
            else if (key == GLFW.GLFW_KEY_UP)
            {
                rawCharacter = 0;
                rawString = "KEY_UP";
            }
            else if (key == GLFW.GLFW_KEY_DOWN)
            {
                rawCharacter = 0;
                rawString = "KEY_DOWN";
            }
            else if (key == GLFW.GLFW_KEY_LEFT)
            {
                rawCharacter = 0;
                rawString = "KEY_LEFT";
            }
            else if (key == GLFW.GLFW_KEY_RIGHT)
            {
                rawCharacter = 0;
                rawString = "KEY_RIGHT";
            }
            else if (key == GLFW.GLFW_KEY_TAB)
            {
                rawCharacter = '\t';
                rawString = "KEY_TAB";
            }
            else if ((mods & GLFW.GLFW_MOD_CONTROL) == GLFW.GLFW_MOD_CONTROL /** || (mods & GLFW.GLFW_MOD_ALT) == GLFW.GLFW_MOD_ALT */)
            {
                boolean caps = false;
                if (((mods & GLFW.GLFW_MOD_SHIFT) == GLFW.GLFW_MOD_SHIFT))
                    caps = true;

                rawString = GLFW.glfwGetKeyName(key, key);

                if (!caps && rawString != null)
                    rawString = rawString.toLowerCase();

                if (rawString != null)
                {
                    if (rawString.length() > 0)
                        rawCharacter = rawString.charAt(0);
                    else
                        rawCharacter = 0;
                }
                else
                    rawString= "";
            }

            if (!rawString.equals(""))
            {
                keyPressed(rawCharacter, rawString, true, mods);
            }
        }
        else
        {
            rawString = new String(new byte[] { (byte)codepoint }, UTF8);
            rawCharacter = rawString.charAt(0);
            keyPressed(rawCharacter, rawString, false, mods);
        }
    }

    public abstract void keyPressed(char rawCharacter, String rawString, boolean specialKey, int mods);
}
