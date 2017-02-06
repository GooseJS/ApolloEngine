package com.goosejs.apollo.input;

import com.goosejs.apollo.client.gui.elements.GuiInputField;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputFieldContainer extends CharacterInput
{
    private ArrayList<GuiInputField> inputFields;
    private int activeInputField;

    public InputFieldContainer()
    {
        inputFields = new ArrayList<>();
        Keybinds.registerBasicKeybinds(this);
    }

    public GuiInputField getInputField(int element)
    {
        if (element < inputFields.size())
            return inputFields.get(element);
        else
            return null;
    }

    public int getInputFieldElementID(GuiInputField inputField)
    {
        return inputFields.indexOf(inputField);
    }

    public GuiInputField getActiveInputField()
    {
        if (inputFields.size() > activeInputField)
            return inputFields.get(activeInputField);
        return null;
    }

    public int addInputField(GuiInputField inputField)
    {
        inputFields.add(inputField);
        return inputFields.indexOf(inputField);
    }

    public boolean removeInputField(GuiInputField inputField)
    {
        return inputFields.remove(inputField);
    }

    public GuiInputField removeInputField(int element)
    {
        return inputFields.remove(element);
    }

    private Map<String, Map<String, ArrayList<TypingInput.OnKeyTyped>>> hookedCommands = new HashMap<>();

    @Override
    public void keyPressed(char rawCharacter, String rawString, boolean specialKey, int mods)
    {
        String currentLine = getActiveInputField().getEditor().getCurrentLine();

        Map<String, ArrayList<TypingInput.OnKeyTyped>> currentCommandWithoutMod = hookedCommands.get(rawString);

        if (currentCommandWithoutMod != null)
        {
            String modifier = "";

            if ((mods & GLFW.GLFW_MOD_ALT) == GLFW.GLFW_MOD_ALT)
                modifier = "ALT";
            else if ((mods & GLFW.GLFW_MOD_SHIFT) == GLFW.GLFW_MOD_SHIFT)
                modifier = "SHIFT";
            else if ((mods & GLFW.GLFW_MOD_CONTROL) == GLFW.GLFW_MOD_CONTROL)
                modifier = "CONTROL";
            else if ((mods & GLFW.GLFW_MOD_SUPER) == GLFW.GLFW_MOD_SUPER)
                modifier = "SUPER";

            ArrayList<TypingInput.OnKeyTyped> currentCommand = currentCommandWithoutMod.get(modifier);
            if (currentCommand != null) currentCommand.forEach(command -> command.invoke(currentLine, this));
        }
    }

    public void registerCommandWithAllMods(String key, TypingInput.OnKeyTyped command)
    {
        registerCommand(key, command);
        registerCommand(key, "SHIFT", command);
        registerCommand(key, "CONTROL", command);
        registerCommand(key, "ALT", command);
        registerCommand(key, "SUPER", command);
    }

    public void registerCommand(String key, TypingInput.OnKeyTyped command)
    {
        registerCommand(key, "", command);
    }

    public void registerCommand(String key, String modifier, TypingInput.OnKeyTyped command)
    {
        Map<String, ArrayList<TypingInput.OnKeyTyped>> tempCommand = hookedCommands.get(key);

        if (tempCommand == null)
        {
            tempCommand = new HashMap<>();
            hookedCommands.put(key, tempCommand);
        }

        ArrayList<TypingInput.OnKeyTyped> commandArray = tempCommand.get(modifier);

        if (commandArray == null)
        {
            commandArray = new ArrayList<>();
            tempCommand.put(modifier, commandArray);
        }

        commandArray.add(command);
    }

    public void typeCharacter(String character)
    {
        getActiveInputField().typeCharacter(character);
    }

    public void typeCharacter(char character)
    {
        getActiveInputField().typeCharacter(character);
    }

    public void newLine()
    {
        getActiveInputField().newLine();
    }

    public void tab()
    {
        getActiveInputField().tab();
    }

    public void backspace()
    {
        getActiveInputField().backspace();
    }

    public void cursorUp()
    {
        getActiveInputField().cursorUp();
    }

    public void cursorDown()
    {
        getActiveInputField().cursorDown();
    }

    public void cursorBack()
    {
        getActiveInputField().cursorBack();
    }

    public void cursorForward()
    {
        getActiveInputField().cursorForward();
    }
}