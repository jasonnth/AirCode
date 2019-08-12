package com.jumio.commons.math;

import android.graphics.PointF;

public class MathUtils {
    public static double rad2deg(double _rad) {
        return 57.29577951308232d * _rad;
    }

    public static double deg2rad(double _deg) {
        return (3.141592653589793d * _deg) / 180.0d;
    }

    public static PointF[] inflatePolygon(PointF tl, PointF tr, PointF br, PointF bl, int marginWidthPixel) {
        return inflatePolygon(new PointF[]{tl, tr, br, bl}, marginWidthPixel);
    }

    public static PointF[] inflatePolygon(PointF[] polyPoints, int marginWidthPixel) {
        if (marginWidthPixel == 0) {
            return polyPoints;
        }
        if (polyPoints.length != 4) {
            throw new IllegalArgumentException("Only quadrangular polygons are supported at the moment!");
        }
        float tlx = polyPoints[0].x;
        float tly = polyPoints[0].y;
        float trx = polyPoints[1].x;
        float tryy = polyPoints[1].y;
        float brx = polyPoints[2].x;
        float bry = polyPoints[2].y;
        float blx = polyPoints[3].x;
        float bly = polyPoints[3].y;
        PointF[] result = new PointF[4];
        Vector2D vector2D = new Vector2D(tlx, tly, trx, tryy);
        Vector2D tr_ = vector2D.getNormalRight().unit().scale((double) marginWidthPixel).add(trx, tryy);
        float k1 = vector2D.getSlope();
        float d1 = (float) (tr_.getY() - (((double) k1) * tr_.getX()));
        Vector2D vector2D2 = new Vector2D(trx, tryy, brx, bry);
        Vector2D br_ = vector2D2.getNormalRight().unit().scale((double) marginWidthPixel).add(brx, bry);
        float k2 = vector2D2.getSlope();
        float d2 = (float) (br_.getY() - (((double) k2) * br_.getX()));
        float x = (d2 - d1) / (k1 - k2);
        PointF pointF = new PointF(x, (k2 * x) + d2);
        result[1] = pointF;
        Vector2D vector2D3 = new Vector2D(brx, bry, blx, bly);
        Vector2D bl_ = vector2D3.getNormalRight().unit().scale((double) marginWidthPixel).add(blx, bly);
        float k3 = vector2D3.getSlope();
        float d3 = (float) (bl_.getY() - (((double) k3) * bl_.getX()));
        float x2 = (d3 - d2) / (k2 - k3);
        PointF pointF2 = new PointF(x2, (k3 * x2) + d3);
        result[2] = pointF2;
        Vector2D vector2D4 = new Vector2D(blx, bly, tlx, tly);
        Vector2D tl_ = vector2D4.getNormalRight().unit().scale((double) marginWidthPixel).add(tlx, tly);
        float k4 = vector2D4.getSlope();
        float d4 = (float) (tl_.getY() - (((double) k4) * tl_.getX()));
        float x3 = (d4 - d3) / (k3 - k4);
        PointF pointF3 = new PointF(x3, (k4 * x3) + d4);
        result[3] = pointF3;
        float x4 = (d1 - d4) / (k4 - k1);
        PointF pointF4 = new PointF(x4, (k1 * x4) + d1);
        result[0] = pointF4;
        return result;
    }

    public static float min(float... _numbers) {
        float min = _numbers[0];
        for (float _number : _numbers) {
            min = Math.min(min, _number);
        }
        return min;
    }

    public static float max(float... _numbers) {
        float max = _numbers[0];
        for (float _number : _numbers) {
            max = Math.max(max, _number);
        }
        return max;
    }
}
