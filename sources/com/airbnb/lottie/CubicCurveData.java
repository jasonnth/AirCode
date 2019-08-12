package com.airbnb.lottie;

import android.graphics.PointF;

class CubicCurveData {
    private final PointF controlPoint1;
    private final PointF controlPoint2;
    private final PointF vertex;

    CubicCurveData() {
        this.controlPoint1 = new PointF();
        this.controlPoint2 = new PointF();
        this.vertex = new PointF();
    }

    CubicCurveData(PointF controlPoint12, PointF controlPoint22, PointF vertex2) {
        this.controlPoint1 = controlPoint12;
        this.controlPoint2 = controlPoint22;
        this.vertex = vertex2;
    }

    /* access modifiers changed from: 0000 */
    public void setControlPoint1(float x, float y) {
        this.controlPoint1.set(x, y);
    }

    /* access modifiers changed from: 0000 */
    public PointF getControlPoint1() {
        return this.controlPoint1;
    }

    /* access modifiers changed from: 0000 */
    public void setControlPoint2(float x, float y) {
        this.controlPoint2.set(x, y);
    }

    /* access modifiers changed from: 0000 */
    public PointF getControlPoint2() {
        return this.controlPoint2;
    }

    /* access modifiers changed from: 0000 */
    public void setVertex(float x, float y) {
        this.vertex.set(x, y);
    }

    /* access modifiers changed from: 0000 */
    public PointF getVertex() {
        return this.vertex;
    }
}
