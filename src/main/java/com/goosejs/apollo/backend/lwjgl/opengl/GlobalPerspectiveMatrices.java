package com.goosejs.apollo.backend.lwjgl.opengl;

import com.goosejs.apollo.backend.lwjgl.glfw.Window;
import com.goosejs.apollo.util.MatrixUtils;
import org.joml.Matrix4f;

import java.util.ArrayList;

// TODO: Document this class
public class GlobalPerspectiveMatrices
{

    private static Matrix4f global2DPerspectiveMatrix;
    private static ArrayList<OnMatrixChange> perspectiveMatrix2DHooks;

    private static Matrix4f global3DPerspectiveMatrix;
    private static ArrayList<OnMatrixChange> perspectiveMatrix3DHooks;

    public static Matrix4f getGlobal2DPerspectiveMatrix()
    {
        return global2DPerspectiveMatrix;
    }

    public static void add2DPerspectiveOnChange(OnMatrixChange hook)
    {
        if (perspectiveMatrix2DHooks == null)
            perspectiveMatrix2DHooks = new ArrayList<>();
        perspectiveMatrix2DHooks.add(hook);
    }

    public static void add3DPerspectiveOnChange(OnMatrixChange hook)
    {
        if (perspectiveMatrix3DHooks == null)
            perspectiveMatrix3DHooks = new ArrayList<>();
        perspectiveMatrix3DHooks.add(hook);
    }

    public static void update2DPerspectiveMatrix(Window window)
    {
        update2DPerspectiveMatrix(0, window.getHeight(), window.getWidth(), 0);
    }

    public static void update2DPerspectiveMatrix(int x0, int y0, int x1, int y1)
    {
        global2DPerspectiveMatrix = MatrixUtils.createOrthoMatrix(x0, x1, y0, y1, -1, 1);
        if (perspectiveMatrix2DHooks != null) perspectiveMatrix2DHooks.forEach(hook -> hook.onMatrixChange(x0, y0, x1, y1));
    }

    public static void update3DPerspectiveMatrix(float fovy, float aspect, float zNear, float zFar)
    {
        global3DPerspectiveMatrix = MatrixUtils.createPerspectiveMatrix(fovy, aspect, zNear, zFar);
        if (perspectiveMatrix3DHooks != null) perspectiveMatrix3DHooks.forEach(hook -> hook.onMatrixChange(fovy, aspect, zNear, zFar));
    }

    public static boolean has2DBeenInit()
    {
        return global2DPerspectiveMatrix != null;
    }

    public static boolean has3DBeenInit()
    {
        return global3DPerspectiveMatrix != null;
    }

    public interface OnMatrixChange
    {
        void onMatrixChange(float x0, float y0, float x1, float y1);
    }

}
