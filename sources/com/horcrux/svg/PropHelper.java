package com.horcrux.svg;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import com.facebook.react.bridge.ReadableArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PropHelper {

    static class RNSVGBrush {
        private ReadableArray mColors;
        private ReadableArray mPoints;
        private GradientType mType = GradientType.LINEAR_GRADIENT;

        public enum GradientType {
            LINEAR_GRADIENT(0),
            RADIAL_GRADIENT(1);
            
            final int nativeInt;

            private GradientType(int ni) {
                this.nativeInt = ni;
            }
        }

        public RNSVGBrush(GradientType type, ReadableArray points, ReadableArray colors) {
            this.mType = type;
            this.mPoints = points;
            this.mColors = colors;
        }

        private static void parseGradientStops(ReadableArray value, int stopsCount, float[] stops, int[] stopsColors, float opacity) {
            int startStops = value.size() - stopsCount;
            for (int i = 0; i < stopsCount; i++) {
                stops[i] = (float) value.getDouble(startStops + i);
                stopsColors[i] = Color.argb((int) (value.getDouble((i * 4) + 3) * 255.0d * ((double) opacity)), (int) (value.getDouble(i * 4) * 255.0d), (int) (value.getDouble((i * 4) + 1) * 255.0d), (int) (value.getDouble((i * 4) + 2) * 255.0d));
            }
        }

        public void setupPaint(Paint paint, RectF box, float scale, float opacity) {
            float height = box.height();
            float width = box.width();
            float offsetX = box.centerX() - (width / 2.0f);
            float offsetY = box.centerY() - (height / 2.0f);
            int stopsCount = this.mColors.size() / 5;
            int[] stopsColors = new int[stopsCount];
            float[] stops = new float[stopsCount];
            parseGradientStops(this.mColors, stopsCount, stops, stopsColors, opacity);
            if (this.mType == GradientType.LINEAR_GRADIENT) {
                paint.setShader(new LinearGradient(PropHelper.fromPercentageToFloat(this.mPoints.getString(0), width, offsetX, scale), PropHelper.fromPercentageToFloat(this.mPoints.getString(1), height, offsetY, scale), PropHelper.fromPercentageToFloat(this.mPoints.getString(2), width, offsetX, scale), PropHelper.fromPercentageToFloat(this.mPoints.getString(3), height, offsetY, scale), stopsColors, stops, TileMode.CLAMP));
                return;
            }
            float rx = PropHelper.fromPercentageToFloat(this.mPoints.getString(2), width, 0.0f, scale);
            float ry = PropHelper.fromPercentageToFloat(this.mPoints.getString(3), height, 0.0f, scale);
            Shader radialGradient = new RadialGradient(PropHelper.fromPercentageToFloat(this.mPoints.getString(4), width, offsetX, scale), PropHelper.fromPercentageToFloat(this.mPoints.getString(5), height, offsetY, scale) / (ry / rx), rx, stopsColors, stops, TileMode.CLAMP);
            Matrix radialMatrix = new Matrix();
            radialMatrix.preScale(1.0f, ry / rx);
            radialGradient.setLocalMatrix(radialMatrix);
            paint.setShader(radialGradient);
        }
    }

    static float[] toFloatArray(ReadableArray value) {
        if (value == null) {
            return null;
        }
        float[] result = new float[value.size()];
        toFloatArray(value, result);
        return result;
    }

    static int toFloatArray(ReadableArray value, float[] into) {
        int length = value.size() > into.length ? into.length : value.size();
        for (int i = 0; i < length; i++) {
            into[i] = (float) value.getDouble(i);
        }
        return value.size();
    }

    static float fromPercentageToFloat(String percentage, float relative, float offset, float scale) {
        Matcher matched = Pattern.compile("^(\\-?\\d+(?:\\.\\d+)?)%$").matcher(percentage);
        if (matched.matches()) {
            return ((Float.valueOf(matched.group(1)).floatValue() / 100.0f) * relative) + offset;
        }
        return Float.valueOf(percentage).floatValue() * scale;
    }

    static boolean isPercentage(String string) {
        return Pattern.compile("^(\\-?\\d+(?:\\.\\d+)?)%$").matcher(string).matches();
    }
}
