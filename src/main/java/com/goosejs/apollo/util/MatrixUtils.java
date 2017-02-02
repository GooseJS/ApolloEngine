package com.goosejs.apollo.util;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * TODO: Documentation
 */
public class MatrixUtils
{

    private static Matrix4f transformationMatrix = new Matrix4f();

    private MatrixUtils() {}

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
    {
        createTransformationMatrix(translation, rx, ry, rz, scale, transformationMatrix);
        return transformationMatrix;
    }

    public static void createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale, Matrix4f dest)
    {
        dest.identity();
        dest.translation(translation.x, translation.y, translation.z).rotateX((float)Math.toRadians(rx)).rotateY((float)Math.toRadians(ry)).rotateZ((float)Math.toRadians(rz)).scale(scale);
    }

    public static Matrix4f createOrthoMatrix(float left, float right, float top, float bottom, float near, float far)
    {
        Matrix4f matrix = new Matrix4f();

        matrix.m00(2.0f / (right - left));
        matrix.m01(0.0f);
        matrix.m02(0.0f);
        matrix.m03(0.0f);

        matrix.m10(0.0f);
        matrix.m11(2.0f / (top - bottom));
        matrix.m12(0.0f);
        matrix.m13(0.0f);

        matrix.m20(0.0f);
        matrix.m21(0.0f);
        matrix.m22(-2.0f / (far - near));
        matrix.m23(0.0f);

        matrix.m30(-(right + left) / (right - left));
        matrix.m31(-(top + bottom) / (top - bottom));
        matrix.m32(-(far + near) / (far - near));
        matrix.m33(1.0f);

        return matrix;
    }

    public static Matrix4f createPerspectiveMatrix(float fovy, float aspect, float zNear, float zFar)
    {
        Matrix4f matrix = new Matrix4f();

        matrix.perspective((float)Math.toRadians(fovy), aspect, zNear, zFar);

        return matrix;
    }
}