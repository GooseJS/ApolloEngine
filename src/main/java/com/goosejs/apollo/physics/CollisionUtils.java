package com.goosejs.apollo.physics;

public class CollisionUtils
{

    private CollisionUtils() {}

    public static boolean testAABBAABB(AABB2D firstAABB, AABB2D secondAABB)
    {
        return Math.abs(firstAABB.getCenterX() - secondAABB.getCenterX()) <= (firstAABB.getRadiusX() + secondAABB.getRadiusX()) && Math.abs(firstAABB.getCenterY() - secondAABB.getCenterY()) <= (firstAABB.getRadiusY() + secondAABB.getRadiusY());
    }

}
