package com.jumio.commons.math;

public class Vector2D {

    /* renamed from: x */
    private double f3199x;

    /* renamed from: y */
    private double f3200y;

    public Vector2D() {
    }

    public Vector2D(double _x, double _y) {
        this.f3199x = _x;
        this.f3200y = _y;
    }

    public Vector2D(float _x1, float _y1, float _x2, float _y2) {
        this.f3199x = (double) (_x2 - _x1);
        this.f3200y = (double) (_y2 - _y1);
    }

    protected Vector2D(Vector2D _vector2d) {
        this.f3199x = _vector2d.f3199x;
        this.f3200y = _vector2d.f3200y;
    }

    public Vector2D scale(double _scaleFactor) {
        return new Vector2D(this.f3199x * _scaleFactor, this.f3200y * _scaleFactor);
    }

    public double length() {
        return Math.sqrt(Math.pow(this.f3199x, 2.0d) + Math.pow(this.f3200y, 2.0d));
    }

    public Vector2D unit() {
        Vector2D vec = new Vector2D();
        double l = length();
        vec.f3199x = this.f3199x / l;
        vec.f3200y = this.f3200y / l;
        return vec;
    }

    public boolean isUnit() {
        return length() == 1.0d;
    }

    public double angleDeg(Vector2D _other) {
        Vector2D thisUnit = unit();
        Vector2D otherUnit = _other.unit();
        if (equals(_other)) {
            return 0.0d;
        }
        return MathUtils.rad2deg(Math.acos(thisUnit.dotProduct(otherUnit)));
    }

    public double angleDeg() {
        return angleDeg(new Vector2D(1.0d, 0.0d));
    }

    public double dotProduct(Vector2D _other) {
        return (this.f3199x * _other.f3199x) + (this.f3200y * _other.f3200y);
    }

    public Quadrant getQuadrant() {
        if (this.f3199x >= 0.0d) {
            if (this.f3200y >= 0.0d) {
                return Quadrant.UPPER_RIGHT;
            }
            return Quadrant.LOWER_RIGHT;
        } else if (this.f3200y >= 0.0d) {
            return Quadrant.UPPER_LEFT;
        } else {
            return Quadrant.LOWER_LEFT;
        }
    }

    public double getX() {
        return this.f3199x;
    }

    public double getY() {
        return this.f3200y;
    }

    public void setX(double _x) {
        this.f3199x = _x;
    }

    public void setY(double _y) {
        this.f3200y = _y;
    }

    public Vector2D add(Vector2D _vector2d) {
        Vector2D n = new Vector2D(this);
        n.f3199x += _vector2d.f3199x;
        n.f3200y += _vector2d.f3200y;
        return n;
    }

    public Vector2D subtract(Vector2D _a) {
        Vector2D res = new Vector2D(this);
        res.f3199x -= _a.f3199x;
        res.f3200y -= _a.f3200y;
        return res;
    }

    public Vector2D add(float _x, float _y) {
        return add(new Vector2D((double) _x, (double) _y));
    }

    public float getSlope() {
        return (float) (this.f3200y / this.f3199x);
    }

    public Vector2D getNormalRight() {
        return new Vector2D(this.f3200y, -this.f3199x);
    }

    public Vector2D getNormalLeft() {
        return new Vector2D(-this.f3200y, this.f3199x);
    }
}
