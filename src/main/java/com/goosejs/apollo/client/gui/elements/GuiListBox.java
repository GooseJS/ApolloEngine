//package com.goosejs.apollo.client.gui.elements;
//
//import com.goosejs.apollo.client.gui.GuiElement;
//import com.goosejs.apollo.client.gui.GuiManager;
//import com.goosejs.apollo.renderer.glRendering.Tessellation;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class GuiListBox extends GuiElement
//{
//    private List<GuiButton> elements;
//
//    public GuiListBox(int x, int y, int width, int height)
//    {
//        this(null, x, y, width, height);
//    }
//
//    public GuiListBox(List<GuiButton> elements, int x, int y, int width, int height)
//    {
//        super(x, y, width, height);
//        // TODO: Add scrolling support
//        if (elements == null)
//            this.elements = new CopyOnWriteArrayList<>();
//    }
//
//    @Override
//    public void onDraw()
//    {
//        renderOutline();
//    }
//
//    private void updateGuiLayout()
//    {
//        int currentY = getY() + 1;
//        int currentX = getX();
//
//        for (GuiButton button : elements)
//        {
//            button.setX(currentX + 1);
//            button.setY(currentY);
//            button.setWidth(getWidth() - 2);
//            currentY += button.getHeight();
//        }
//    }
//
//    public void clearButtons(GuiManager guiManager)
//    {
//        elements.forEach(guiManager::removeGuiElement);
//        this.elements.clear();
//    }
//
//    public void addButton(GuiButton button, GuiManager guiManager)
//    {
//        this.elements.add(button);
//        if (!guiManager.hasElement(button))
//            guiManager.addGuiElement(button);
//
//        updateGuiLayout();
//    }
//
//    public boolean removeButton(GuiButton button, GuiManager guiManager)
//    {
//        updateGuiLayout();
//
//        guiManager.removeGuiElement(button);
//        return this.elements.remove(button);
//    }
//
//    public GuiButton removeButton(int elementID)
//    {
//        return this.elements.remove(elementID);
//    }
//
//    public List<GuiButton> getButtons()
//    {
//        return this.elements;
//    }
//
//    public void renderOutline()
//    {
//        Tessellation.drawRectOutline(1, 1, getWidth(), getHeight() - 1, 1, 1, 1, 1);
//    }
//}
