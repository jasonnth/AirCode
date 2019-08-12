package com.facebook.react.flat;

class NodeRegion {
    static final NodeRegion EMPTY = new NodeRegion(0.0f, 0.0f, 0.0f, 0.0f, -1, false);
    static final NodeRegion[] EMPTY_ARRAY = new NodeRegion[0];
    private final float mBottom;
    final boolean mIsVirtual;
    private final float mLeft;
    private final float mRight;
    final int mTag;
    private final float mTop;

    NodeRegion(float left, float top, float right, float bottom, int tag, boolean isVirtual) {
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
        this.mTag = tag;
        this.mIsVirtual = isVirtual;
    }

    /* access modifiers changed from: 0000 */
    public final boolean matches(float left, float top, float right, float bottom, boolean isVirtual) {
        return left == this.mLeft && top == this.mTop && right == this.mRight && bottom == this.mBottom && isVirtual == this.mIsVirtual;
    }

    /* access modifiers changed from: 0000 */
    public final float getLeft() {
        return this.mLeft;
    }

    /* access modifiers changed from: 0000 */
    public final float getTop() {
        return this.mTop;
    }

    /* access modifiers changed from: 0000 */
    public final float getRight() {
        return this.mRight;
    }

    /* access modifiers changed from: 0000 */
    public final float getBottom() {
        return this.mBottom;
    }

    /* access modifiers changed from: 0000 */
    public float getTouchableLeft() {
        return getLeft();
    }

    /* access modifiers changed from: 0000 */
    public float getTouchableTop() {
        return getTop();
    }

    /* access modifiers changed from: 0000 */
    public float getTouchableRight() {
        return getRight();
    }

    /* access modifiers changed from: 0000 */
    public float getTouchableBottom() {
        return getBottom();
    }

    /* access modifiers changed from: 0000 */
    public boolean withinBounds(float touchX, float touchY) {
        return this.mLeft <= touchX && touchX < this.mRight && this.mTop <= touchY && touchY < this.mBottom;
    }

    /* access modifiers changed from: 0000 */
    public int getReactTag(float touchX, float touchY) {
        return this.mTag;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesTag(int tag) {
        return this.mTag == tag;
    }
}
