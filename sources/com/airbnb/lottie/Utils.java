package com.airbnb.lottie;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.Closeable;

final class Utils {
    private static final float SQRT_2 = ((float) Math.sqrt(2.0d));
    private static DisplayMetrics displayMetrics;
    private static final PathMeasure pathMeasure = new PathMeasure();
    private static final float[] points = new float[4];
    private static final Path tempPath = new Path();
    private static final Path tempPath2 = new Path();

    static Path createPath(PointF startPoint, PointF endPoint, PointF cp1, PointF cp2) {
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        if (cp1 == null || cp1.length() == 0.0f || cp2 == null || cp2.length() == 0.0f) {
            path.lineTo(endPoint.x, endPoint.y);
        } else {
            path.cubicTo(startPoint.x + cp1.x, startPoint.y + cp1.y, endPoint.x + cp2.x, endPoint.y + cp2.y, endPoint.x, endPoint.y);
        }
        return path;
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    static int getScreenWidth(Context context) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
        }
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    static int getScreenHeight(Context context) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
        }
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    static float getScale(Matrix matrix) {
        points[0] = 0.0f;
        points[1] = 0.0f;
        points[2] = SQRT_2;
        points[3] = SQRT_2;
        matrix.mapPoints(points);
        return ((float) Math.hypot((double) (points[2] - points[0]), (double) (points[3] - points[1]))) / 2.0f;
    }

    static void applyTrimPathIfNeeded(Path path, TrimPathContent trimPath) {
        if (trimPath != null) {
            applyTrimPathIfNeeded(path, ((Float) trimPath.getStart().getValue()).floatValue() / 100.0f, ((Float) trimPath.getEnd().getValue()).floatValue() / 100.0f, ((Float) trimPath.getOffset().getValue()).floatValue() / 360.0f);
        }
    }

    static void applyTrimPathIfNeeded(Path path, float startValue, float endValue, float offsetValue) {
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (length != 0.0f) {
            float start = length * startValue;
            float end = length * endValue;
            float offset = offsetValue * length;
            float newStart = Math.min(start, end) + offset;
            float newEnd = Math.max(start, end) + offset;
            if (newStart >= length && newEnd >= length) {
                newStart = (float) MiscUtils.floorMod(newStart, length);
                newEnd = (float) MiscUtils.floorMod(newEnd, length);
            }
            if (Math.abs(Math.abs(newEnd - newStart) - length) >= 1.0f) {
                if (newStart < 0.0f) {
                    newStart = (float) MiscUtils.floorMod(newStart, length);
                }
                if (newEnd < 0.0f) {
                    newEnd = (float) MiscUtils.floorMod(newEnd, length);
                }
                if (newStart == newEnd) {
                    path.reset();
                    return;
                }
                if (newStart >= newEnd) {
                    newStart -= length;
                }
                tempPath.reset();
                pathMeasure.getSegment(newStart, newEnd, tempPath, true);
                if (newEnd > length) {
                    tempPath2.reset();
                    pathMeasure.getSegment(0.0f, newEnd % length, tempPath2, true);
                    tempPath.addPath(tempPath2);
                } else if (newStart < 0.0f) {
                    tempPath2.reset();
                    pathMeasure.getSegment(length + newStart, length, tempPath2, true);
                    tempPath.addPath(tempPath2);
                }
                path.set(tempPath);
            }
        }
    }
}
