package com.goosejs.apollo.backend.lwjgl.opengl;

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

    public static void update2DPerspectiveMatrix(int x0, int y0, int x1, int y1)
    {
        global2DPerspectiveMatrix = MatrixUtils.createOrthoMatrix(x0, x1, y0, y1, -1, 1);
        if (perspectiveMatrix2DHooks != null) perspectiveMatrix2DHooks.forEach(hook -> hook.onMatrixChange(x0, y0, x1, y1));
    }

    public static void update3DPerspectiveMatrix(int x0, int y0, int x1, int y1)
    {
        //global3DPerspectiveMatrix = MatrixUtils.createPerspectiveMatrix();
        if (perspectiveMatrix3DHooks != null) perspectiveMatrix3DHooks.forEach(hook -> hook.onMatrixChange(x0, y0, x1, y1));
    }

    public interface OnMatrixChange
    {
        void onMatrixChange(int x0, int y0, int x1, int y1);
    }

}