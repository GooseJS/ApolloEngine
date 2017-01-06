package com.goosejs.apollo.backend.lwjgl.glfw;

import com.goosejs.apollo.backend.lwjgl.opengl.GLStateManager;
import com.goosejs.apollo.util.ApolloBufferUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * A window wrapper class to handle all GLFW related tasks
 */
public class Window
{

    /**
     * TO-DO List
     * TODO: Create a "change display mode" function to allow resizing and fullscreen on the fly
     * TODO: Clean everything up
     * TODO: Add more GLFW interfacing support
     * TODO: Add more callback support (RESIZE CALLBACKS!)
     */

    /** A reference to the window's pointer */
    private long windowPointer;

    /** The parameters of the window */
    private int width;
    private int height;
    private String title;
    private boolean fullscreen;
    private boolean resizable;

    /** The windows callbacks */
    private ExtendableKeyboardCallback keyboardCallback;
    private ExtendableMouseButtonCallback mouseButtonCallback;
    private ExtendableCursorPosCallback cursorPosCallback;
    private ExtendableCharacterCallback characterCallback;

    /** True if the window is created, false otherwise */
    private boolean init;

    /**
     * Only constructor, does not create window only sets the values
     * @param width The width of the window
     * @param height The height of the window
     * @param title The title of the window
     * @param fullscreen True if fullscreen, false if windowed
     * @param resizable  True if the window should be resizable, false otherwise
     */
    public Window(int width, int height, String title, boolean fullscreen, boolean resizable)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.fullscreen = fullscreen;
        this.resizable = !this.fullscreen && resizable;
    }

    /**
     * Will create a window with OpenGL 3.3 and v-sync enabled
     */
    public void createWindow()
    {
        createWindow(3, 3, true, 1);
    }

    /**
     * Will create the window
     */
    public void createWindow(int openGLMajorVersion, int openGLMinorVersion, boolean openGLForwardCompatible, int swapInterval)
    {
        if (!init)
        {
            if (!glfwInit())
                throw new RuntimeException("Cannot initialize GLFW!");

            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);

            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, openGLMajorVersion);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, openGLMinorVersion);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, openGLForwardCompatible ? GLFW_TRUE : GLFW_FALSE);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

            long primaryMonitor = glfwGetPrimaryMonitor();
            GLFWVidMode vidMode = glfwGetVideoMode(primaryMonitor);
            if (width == -1) width = vidMode.width();
            if (height == -1) height = vidMode.height();

            windowPointer = glfwCreateWindow(width, height, title, this.fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
            if (windowPointer == NULL)
                throw new RuntimeException("Cannot create GLFW window!");

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(windowPointer, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

            glfwMakeContextCurrent(windowPointer);

            glfwSwapInterval(swapInterval);

            GLStateManager.initializeOpenGL();
            GL11.glViewport(0, 0, width, height);

            glfwShowWindow(windowPointer);

            init = true;
        }
    }

    /**
     * @return true if the window should close, false otherwise
     */
    public boolean shouldClose()
    {
        return init && glfwWindowShouldClose(windowPointer);
    }

    /**
     * Swaps the windows buffers, should be called every frame
     */
    public void swapBuffers()
    {
        if (init) glfwSwapBuffers(windowPointer);
    }

    /**
     * Polls events, should be called every frame
     */
    public void pollEvents()
    {
        if (init) glfwPollEvents();
    }

    /**
     * Will set the keyboard callback for this window
     * @param keyboardCallback The new keyboard callback
     */
    public void setKeyboardCallback(ExtendableKeyboardCallback keyboardCallback)
    {
        if (init)
        {
            this.keyboardCallback = keyboardCallback;
            glfwSetKeyCallback(windowPointer, keyboardCallback);
        }
    }

    /**
     * @return true if the window has a keyboard callback, false otherwise
     */
    public boolean hasKeyboardCallback()
    {
        return init && keyboardCallback != null;
    }

    /**
     * @return the keyboard callback that the window is currently using
     */
    public ExtendableKeyboardCallback getKeyboardCallback()
    {
        return init ? keyboardCallback : null;
    }

    /**
     * Will set the mouse button callback for this window
     * @param mouseButtonCallback The new mouse button callback
     */
    public void setMouseButtonCallback(ExtendableMouseButtonCallback mouseButtonCallback)
    {
        if (init)
        {
            this.mouseButtonCallback = mouseButtonCallback;
            glfwSetMouseButtonCallback(windowPointer, mouseButtonCallback);
        }
    }

    /**
     * @return true if the window has a mouse button callback, false otherwise
     */
    public boolean hasMouseButtonCallback()
    {
        return init && mouseButtonCallback != null;
    }

    /**
     * @return the mouse callback the window is currently using
     */
    public ExtendableMouseButtonCallback getMouseButtonCallback()
    {
        return init ? mouseButtonCallback : null;
    }

    /**
     * Will set the cursor pos callback for this window
     * @param cursorPosCallback The new cursor pos callback
     */
    public void setCursorPosCallback(ExtendableCursorPosCallback cursorPosCallback)
    {
        if (init)
        {
            this.cursorPosCallback = cursorPosCallback;
            glfwSetCursorPosCallback(windowPointer, cursorPosCallback);
        }
    }

    public void setCharacterCallback(ExtendableCharacterCallback characterCallback)
    {
        if (init)
        {
            this.characterCallback = characterCallback;
            glfwSetCharModsCallback(windowPointer, characterCallback);
        }
    }

    /**
     * @return true if the window has a cursor pos callback, false otherwise
     */
    public boolean hasCursorPosCallback()
    {
        return init && cursorPosCallback != null;
    }

    /**
     * @return the cursor pos the window is currently using
     */
    public ExtendableCursorPosCallback getCursorPosCallback()
    {
        return init ? cursorPosCallback : null;
    }

    /**
     * Sets the cursors position, seems to be buggy on OS X
     * @param mouseX the new mouse X position
     * @param mouseY the new mouse Y position
     */
    public void setCursorPos(double mouseX, double mouseY)
    {
        if (init) glfwSetCursorPos(windowPointer, mouseX, mouseY);
    }

    /**
     * @see org.lwjgl.glfw.GLFW#glfwSetInputMode(long, int, int)
     */
    public void setInputMode(int mode, int value)
    {
        if (init) glfwSetInputMode(windowPointer, mode, value);
    }

    /**
     * Will destroy the window
     */
    public void destroyWindow()
    {
        if (init)
        {
            glfwDestroyWindow(windowPointer);
            glfwTerminate();
            init = false;
        }
    }

    /**
     * @return the width of the window
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return the height of the window
     */
    public int getHeight()
    {
        return height;
    }

    public void showWindow()
    {
        glfwShowWindow(windowPointer);
    }

    public void hideWindow()
    {
        glfwHideWindow(windowPointer);
    }

    public void setTitle(String title)
    {
        this.title = title;
        glfwSetWindowTitle(windowPointer, title);
    }

    public String getTitle()
    {
        return title;
    }

    public void setClipboard(Object object)
    {
        glfwSetClipboardString(windowPointer, object.toString());
    }

    public String getClipboardString()
    {
        return glfwGetClipboardString(windowPointer);
    }

    public long getWindowPointer()
    {
        return windowPointer;
    }

    public ExtendableCharacterCallback getCharacterCallback()
    {
        return characterCallback;
    }
}