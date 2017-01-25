package com.goosejs.apollo.backend.lwjgl.opengl;

import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;

import java.util.ArrayList;

// TODO: Document this class
public class GlobalOrthoMatrix
{

    private static Matrix4f globalOrthoMatrix;
    private static ArrayList<OnMatrixChange> hooks;

    public static Matrix4f getGlobalOrthoMatrix()
    {
        return globalOrthoMatrix;
    }

    public static void addOnMatrixChange(OnMatrixChange hook)
    {
        if (hooks == null)
            hooks = new ArrayList<>();
        hooks.add(hook);
    }

    public static void updateGlobalOrthoMatrix(int x0, int y0, int x1, int y1)
    {
        globalOrthoMatrix = MatrixUtils.createOrthoMatrix(x0, x1, y0, y1, -1, 1);
        if (hooks != null) hooks.forEach(hook -> hook.onMatrixChange(x0, y0, x1, y1));
    }

    public interface OnMatrixChange
    {
        void onMatrixChange(int x0, int y0, int x1, int y1);
    }

}
