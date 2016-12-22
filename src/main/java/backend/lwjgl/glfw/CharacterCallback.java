package backend.lwjgl.glfw;

public interface CharacterCallback
{

    void invoke(long window, int codepoint, int mods);

}
