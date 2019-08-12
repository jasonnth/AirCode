package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ShapeData {
    private boolean closed;
    private final List<CubicCurveData> curves;
    private PointF initialPoint;

    static class Factory implements com.airbnb.lottie.AnimatableValue.Factory<ShapeData> {
        static final Factory INSTANCE = new Factory();

        private Factory() {
        }

        public ShapeData valueFromObject(Object object, float scale) {
            JSONObject pointsData = null;
            if (object instanceof JSONArray) {
                Object firstObject = ((JSONArray) object).opt(0);
                if ((firstObject instanceof JSONObject) && ((JSONObject) firstObject).has("v")) {
                    pointsData = (JSONObject) firstObject;
                }
            } else if ((object instanceof JSONObject) && ((JSONObject) object).has("v")) {
                pointsData = (JSONObject) object;
            }
            if (pointsData == null) {
                return null;
            }
            JSONArray pointsArray = pointsData.optJSONArray("v");
            JSONArray inTangents = pointsData.optJSONArray("i");
            JSONArray outTangents = pointsData.optJSONArray("o");
            boolean closed = pointsData.optBoolean("c", false);
            if (pointsArray == null || inTangents == null || outTangents == null || pointsArray.length() != inTangents.length() || pointsArray.length() != outTangents.length()) {
                throw new IllegalStateException("Unable to process points array or tangents. " + pointsData);
            } else if (pointsArray.length() == 0) {
                return new ShapeData(new PointF(), false, Collections.emptyList());
            } else {
                int length = pointsArray.length();
                PointF vertex = vertexAtIndex(0, pointsArray);
                vertex.x *= scale;
                vertex.y *= scale;
                PointF initialPoint = vertex;
                List<CubicCurveData> curves = new ArrayList<>(length);
                for (int i = 1; i < length; i++) {
                    PointF vertex2 = vertexAtIndex(i, pointsArray);
                    PointF previousVertex = vertexAtIndex(i - 1, pointsArray);
                    PointF cp1 = vertexAtIndex(i - 1, outTangents);
                    PointF cp2 = vertexAtIndex(i, inTangents);
                    PointF shapeCp1 = MiscUtils.addPoints(previousVertex, cp1);
                    PointF shapeCp2 = MiscUtils.addPoints(vertex2, cp2);
                    shapeCp1.x *= scale;
                    shapeCp1.y *= scale;
                    shapeCp2.x *= scale;
                    shapeCp2.y *= scale;
                    vertex2.x *= scale;
                    vertex2.y *= scale;
                    CubicCurveData cubicCurveData = new CubicCurveData(shapeCp1, shapeCp2, vertex2);
                    curves.add(cubicCurveData);
                }
                if (closed) {
                    PointF vertex3 = vertexAtIndex(0, pointsArray);
                    PointF previousVertex2 = vertexAtIndex(length - 1, pointsArray);
                    PointF cp12 = vertexAtIndex(length - 1, outTangents);
                    PointF cp22 = vertexAtIndex(0, inTangents);
                    PointF shapeCp12 = MiscUtils.addPoints(previousVertex2, cp12);
                    PointF shapeCp22 = MiscUtils.addPoints(vertex3, cp22);
                    if (scale != 1.0f) {
                        shapeCp12.x *= scale;
                        shapeCp12.y *= scale;
                        shapeCp22.x *= scale;
                        shapeCp22.y *= scale;
                        vertex3.x *= scale;
                        vertex3.y *= scale;
                    }
                    CubicCurveData cubicCurveData2 = new CubicCurveData(shapeCp12, shapeCp22, vertex3);
                    curves.add(cubicCurveData2);
                }
                ShapeData shapeData = new ShapeData(initialPoint, closed, curves);
                return shapeData;
            }
        }

        private static PointF vertexAtIndex(int idx, JSONArray points) {
            if (idx >= points.length()) {
                throw new IllegalArgumentException("Invalid index " + idx + ". There are only " + points.length() + " points.");
            }
            JSONArray pointArray = points.optJSONArray(idx);
            Object x = pointArray.opt(0);
            Object y = pointArray.opt(1);
            return new PointF(x instanceof Double ? new Float(((Double) x).doubleValue()).floatValue() : (float) ((Integer) x).intValue(), y instanceof Double ? new Float(((Double) y).doubleValue()).floatValue() : (float) ((Integer) y).intValue());
        }
    }

    private ShapeData(PointF initialPoint2, boolean closed2, List<CubicCurveData> curves2) {
        this.curves = new ArrayList();
        this.initialPoint = initialPoint2;
        this.closed = closed2;
        this.curves.addAll(curves2);
    }

    ShapeData() {
        this.curves = new ArrayList();
    }

    private void setInitialPoint(float x, float y) {
        if (this.initialPoint == null) {
            this.initialPoint = new PointF();
        }
        this.initialPoint.set(x, y);
    }

    /* access modifiers changed from: 0000 */
    public PointF getInitialPoint() {
        return this.initialPoint;
    }

    /* access modifiers changed from: 0000 */
    public boolean isClosed() {
        return this.closed;
    }

    /* access modifiers changed from: 0000 */
    public List<CubicCurveData> getCurves() {
        return this.curves;
    }

    /* access modifiers changed from: 0000 */
    public void interpolateBetween(ShapeData shapeData1, ShapeData shapeData2, float percentage) {
        if (this.initialPoint == null) {
            this.initialPoint = new PointF();
        }
        this.closed = shapeData1.isClosed() || shapeData2.isClosed();
        if (this.curves.isEmpty() || this.curves.size() == shapeData1.getCurves().size() || this.curves.size() == shapeData2.getCurves().size()) {
            if (this.curves.isEmpty()) {
                for (int i = shapeData1.getCurves().size() - 1; i >= 0; i--) {
                    this.curves.add(new CubicCurveData());
                }
            }
            PointF initialPoint1 = shapeData1.getInitialPoint();
            PointF initialPoint2 = shapeData2.getInitialPoint();
            setInitialPoint(MiscUtils.lerp(initialPoint1.x, initialPoint2.x, percentage), MiscUtils.lerp(initialPoint1.y, initialPoint2.y, percentage));
            for (int i2 = this.curves.size() - 1; i2 >= 0; i2--) {
                CubicCurveData curve1 = (CubicCurveData) shapeData1.getCurves().get(i2);
                CubicCurveData curve2 = (CubicCurveData) shapeData2.getCurves().get(i2);
                PointF cp11 = curve1.getControlPoint1();
                PointF cp21 = curve1.getControlPoint2();
                PointF vertex1 = curve1.getVertex();
                PointF cp12 = curve2.getControlPoint1();
                PointF cp22 = curve2.getControlPoint2();
                PointF vertex2 = curve2.getVertex();
                ((CubicCurveData) this.curves.get(i2)).setControlPoint1(MiscUtils.lerp(cp11.x, cp12.x, percentage), MiscUtils.lerp(cp11.y, cp12.y, percentage));
                ((CubicCurveData) this.curves.get(i2)).setControlPoint2(MiscUtils.lerp(cp21.x, cp22.x, percentage), MiscUtils.lerp(cp21.y, cp22.y, percentage));
                ((CubicCurveData) this.curves.get(i2)).setVertex(MiscUtils.lerp(vertex1.x, vertex2.x, percentage), MiscUtils.lerp(vertex1.y, vertex2.y, percentage));
            }
            return;
        }
        throw new IllegalStateException("Curves must have the same number of control points. This: " + getCurves().size() + "\tShape 1: " + shapeData1.getCurves().size() + "\tShape 2: " + shapeData2.getCurves().size());
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.curves.size() + "closed=" + this.closed + '}';
    }
}
