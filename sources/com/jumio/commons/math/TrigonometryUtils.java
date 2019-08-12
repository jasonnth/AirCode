package com.jumio.commons.math;

import android.graphics.Point;

public class TrigonometryUtils {
    public static double getAreaFromTrianlge(Point p1, Point p2, Point p3) {
        double area = ((double) (((p1.x * (p2.y - p3.y)) + (p2.x * (p3.y - p1.y))) + (p3.x * (p1.y - p2.y)))) / 2.0d;
        if (area < 0.0d) {
            return area * -1.0d;
        }
        return area;
    }

    public static double getAreaFromQuadrangle(Point p1, Point p2, Point p3, Point p4) {
        return getAreaFromTrianlge(p1, p2, p4) + getAreaFromTrianlge(p2, p3, p4);
    }

    public static double getDistanceBetweenTwoPoints(Point p1, Point p2) {
        int a = p1.x - p2.x;
        int b = p1.y - p2.y;
        return Math.sqrt((double) ((a * a) + (b * b)));
    }
}
