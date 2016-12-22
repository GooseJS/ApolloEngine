package client.gui;

import com.goosejs.gchat.backend.lwjgl.glfw.CursorPosCallback;
import com.goosejs.gchat.backend.lwjgl.glfw.KeyboardCallback;
import com.goosejs.gchat.backend.lwjgl.glfw.MouseButtonCallback;
import com.goosejs.gchat.backend.lwjgl.glfw.Window;
import com.goosejs.gchat.client.GChatClient;
import com.goosejs.gchat.client.gui.elements.GuiInputField;
import com.goosejs.gchat.input.InputFieldContainer;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GuiManager implements CursorPosCallback, MouseButtonCallback, KeyboardCallback
{

    private List<GuiElement> guiElements;
    private InputFieldContainer inputFieldContainer;

    private double mouseX;
    private double mouseY;

    public GuiManager(GChatClient client)
    {
        this.guiElements = new CopyOnWriteArrayList<>();
        this.inputFieldContainer = new InputFieldContainer(client);
    }

    public void registerCallbacks(Window window)
    {
        window.getCharacterCallback().addCharacterCallback(inputFieldContainer);
        window.getKeyboardCallback().addKeyboardCallback(inputFieldContainer);
        window.getKeyboardCallback().addKeyboardCallback(this);
        window.getCursorPosCallback().addCursorPosCallback(this);
        window.getMouseButtonCallback().addMouseButtonCallback(this);
    }

    public void unregisterCallbacks(Window window)
    {
        window.getCharacterCallback().removeCharacterCallback(inputFieldContainer);
        window.getKeyboardCallback().removeKeyboardCallback(inputFieldContainer);
        window.getKeyboardCallback().removeKeyboardCallback(this);
        window.getCursorPosCallback().removeCursorPosCallbacK(this);
        window.getMouseButtonCallback().removeMouseButtonCallback(this);
    }

    public boolean addGuiElement(GuiElement element)
    {
        if (element instanceof GuiInputField)
            inputFieldContainer.addInputField((GuiInputField)element);
        element.onCreation();
        return guiElements.add(element);
    }

    public void update()
    {
        guiElements.forEach(GuiElement::update);
    }

    public boolean removeGuiElement(GuiElement element)
    {
        element.onDestruction();
        return guiElements.remove(element);
    }

    @Override
    public void invoke(long window, double xpos, double ypos)
    {
        // NOTE: CursorPosCallback
        mouseX = xpos;
        mouseY = ypos;

        guiElements.forEach(guiElement -> guiElement.onMouseMove(mouseX, mouseY));

        guiElements.stream().filter(element -> (element.getX() < mouseX && (element.getX() + element.getWidth()) > mouseX) && (element.getY() < mouseY && (element.getY() + element.getHeight()) > mouseY)).forEach(element -> element.onMouseHover(mouseX, mouseY));
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods)
    {
        // NOTE: KeyboardCallback
        if (action != GLFW.GLFW_RELEASE)
            guiElements.forEach(element -> element.onKeyPress(GLFW.glfwGetKeyName(key, scancode), key, scancode, action == GLFW.GLFW_REPEAT));
    }

    @Override
    public void invoke(long window, int button, int action, int mods)
    {
        // NOTE: MouseButtonCallback
        if (action == GLFW.GLFW_PRESS)
            guiElements.forEach(element -> element.onMouseButtonPress(button, mouseX, mouseY));

        if (action == GLFW.GLFW_PRESS)
            guiElements.stream().filter(element -> (element.getX() < mouseX && (element.getX() + element.getWidth()) > mouseX) && (element.getY() < mouseY && (element.getY() + element.getHeight()) > mouseY)).forEach(element -> element.onMouseButtonPressWhileOver(button, mouseX, mouseY));
    }

    public boolean hasElement(GuiElement element)
    {
        return this.guiElements.contains(element);
    }

    public GuiElement getElementByIdentifier(String identifier)
    {
        for (GuiElement guiElement : guiElements)
            if (guiElement.getIdentifier().equals(identifier)) return guiElement;

        return null;
    }

    public void draw()
    {
        guiElements.forEach(GuiElement::draw);
    }
}