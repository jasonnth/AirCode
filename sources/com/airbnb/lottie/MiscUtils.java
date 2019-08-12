package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;

class MiscUtils {
    static PointF addPoints(PointF p1, PointF p2) {
        return new PointF(p1.x + p2.x, p1.y + p2.y);
    }

    static void getPathFromData(ShapeData shapeData, Path outPath) {
        outPath.reset();
        PointF initialPoint = shapeData.getInitialPoint();
        outPath.moveTo(initialPoint.x, initialPoint.y);
        for (int i = 0; i < shapeData.getCurves().size(); i++) {
            CubicCurveData curveData = (CubicCurveData) shapeData.getCurves().get(i);
            outPath.cubicTo(curveData.getControlPoint1().x, curveData.getControlPoint1().y, curveData.getControlPoint2().x, curveData.getControlPoint2().y, curveData.getVertex().x, curveData.getVertex().y);
        }
        if (shapeData.isClosed()) {
            outPath.close();
        }
    }

    static float lerp(float a, float b, float percentage) {
        return ((b - a) * percentage) + a;
    }

    static double lerp(double a, double b, double percentage) {
        return ((b - a) * percentage) + a;
    }

    static int lerp(int a, int b, float percentage) {
        return (int) (((float) a) + (((float) (b - a)) * percentage));
    }

    static int floorMod(float x, float y) {
        return floorMod((int) x, (int) y);
    }

    static int floorMod(int x, int y) {
        return x - (floorDiv(x, y) * y);
    }

    private static int floorDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) >= 0 || r * y == x) {
            return r;
        }
        return r - 1;
    }

    static float clamp(float number, float min, float max) {
        return Math.max(min, Math.min(max, number));
    }
}
