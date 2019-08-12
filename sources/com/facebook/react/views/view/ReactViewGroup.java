package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactPointerEventsView;

public class ReactViewGroup extends ViewGroup implements ReactHitSlopView, ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final LayoutParams sDefaultLayoutParam = new LayoutParams(0, 0);
    private static final Rect sHelperRect = new Rect();
    private View[] mAllChildren = null;
    private int mAllChildrenCount;
    private ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    private Rect mClippingRect;
    private Rect mHitSlopRect;
    private ReadableMap mNativeBackground;
    private boolean mNeedsOffscreenAlphaCompositing = false;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private PointerEvents mPointerEvents = PointerEvents.AUTO;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private boolean mRemoveClippedSubviews = false;

    private static final class ChildrenLayoutChangeListener implements OnLayoutChangeListener {
        private final ReactViewGroup mParent;

        private ChildrenLayoutChangeListener(ReactViewGroup parent) {
            this.mParent = parent;
        }

        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (this.mParent.getRemoveClippedSubviews()) {
                this.mParent.updateSubviewClipStatus(v);
            }
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    public void requestLayout() {
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.mReactBackgroundDrawable != null) {
            getOrCreateReactViewBackground().setColor(color);
        }
    }

    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    public void setNativeBackground(ReadableMap nativeBackground) {
        this.mNativeBackground = nativeBackground;
        refreshTranslucentBackgroundDrawable();
    }

    public void refreshTranslucentBackgroundDrawable() {
        Drawable background = null;
        if (this.mNativeBackground != null) {
            float[] cornerRadii = null;
            if (this.mReactBackgroundDrawable != null) {
                cornerRadii = this.mReactBackgroundDrawable.getBorderRadii();
            }
            background = ReactDrawableHelper.createDrawableFromJSDescription(getContext(), this.mNativeBackground, cornerRadii);
        }
        super.setBackground(null);
        if (this.mReactBackgroundDrawable != null && background != null) {
            super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
        } else if (this.mReactBackgroundDrawable != null && background == null) {
            super.setBackground(this.mReactBackgroundDrawable);
        } else if (background != null) {
            super.setBackground(background);
        }
    }

    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener listener) {
        this.mOnInterceptTouchEventListener = listener;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if ((this.mOnInterceptTouchEventListener != null && this.mOnInterceptTouchEventListener.onInterceptTouchEvent(this, ev)) || this.mPointerEvents == PointerEvents.NONE || this.mPointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mPointerEvents == PointerEvents.NONE || this.mPointerEvents == PointerEvents.BOX_NONE) {
            return false;
        }
        return true;
    }

    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean needsOffscreenAlphaCompositing) {
        this.mNeedsOffscreenAlphaCompositing = needsOffscreenAlphaCompositing;
    }

    public void setBorderWidth(int position, float width) {
        getOrCreateReactViewBackground().setBorderWidth(position, width);
    }

    public void setBorderColor(int position, float rgb, float alpha) {
        getOrCreateReactViewBackground().setBorderColor(position, rgb, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        getOrCreateReactViewBackground().setRadius(borderRadius);
        refreshTranslucentBackgroundDrawable();
    }

    public void setBorderRadius(float borderRadius, int position) {
        getOrCreateReactViewBackground().setRadius(borderRadius, position);
        refreshTranslucentBackgroundDrawable();
    }

    public void setBorderStyle(String style) {
        getOrCreateReactViewBackground().setBorderStyle(style);
    }

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        if (removeClippedSubviews != this.mRemoveClippedSubviews) {
            this.mRemoveClippedSubviews = removeClippedSubviews;
            if (removeClippedSubviews) {
                this.mClippingRect = new Rect();
                ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
                this.mAllChildrenCount = getChildCount();
                this.mAllChildren = new View[Math.max(12, this.mAllChildrenCount)];
                this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener();
                for (int i = 0; i < this.mAllChildrenCount; i++) {
                    View child = getChildAt(i);
                    this.mAllChildren[i] = child;
                    child.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
                }
                updateClippingRect();
                return;
            }
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
            for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
                this.mAllChildren[i2].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            getDrawingRect(this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
            this.mAllChildren = null;
            this.mClippingRect = null;
            this.mAllChildrenCount = 0;
            this.mChildrenLayoutChangeListener = null;
        }
    }

    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    public void getClippingRect(Rect outClippingRect) {
        outClippingRect.set(this.mClippingRect);
    }

    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
        }
    }

    private void updateClippingToRect(Rect clippingRect) {
        Assertions.assertNotNull(this.mAllChildren);
        int clippedSoFar = 0;
        for (int i = 0; i < this.mAllChildrenCount; i++) {
            updateSubviewClipStatus(clippingRect, i, clippedSoFar);
            if (this.mAllChildren[i].getParent() == null) {
                clippedSoFar++;
            }
        }
    }

    private void updateSubviewClipStatus(Rect clippingRect, int idx, int clippedSoFar) {
        View child = ((View[]) Assertions.assertNotNull(this.mAllChildren))[idx];
        sHelperRect.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        boolean intersects = clippingRect.intersects(sHelperRect.left, sHelperRect.top, sHelperRect.right, sHelperRect.bottom);
        boolean needUpdateClippingRecursive = false;
        Animation animation = child.getAnimation();
        boolean isAnimating = animation != null && !animation.hasEnded();
        if (!intersects && child.getParent() != null && !isAnimating) {
            super.removeViewsInLayout(idx - clippedSoFar, 1);
            needUpdateClippingRecursive = true;
        } else if (intersects && child.getParent() == null) {
            super.addViewInLayout(child, idx - clippedSoFar, sDefaultLayoutParam, true);
            invalidate();
            needUpdateClippingRecursive = true;
        } else if (intersects) {
            needUpdateClippingRecursive = true;
        }
        if (needUpdateClippingRecursive && (child instanceof ReactClippingViewGroup)) {
            ReactClippingViewGroup clippingChild = (ReactClippingViewGroup) child;
            if (clippingChild.getRemoveClippedSubviews()) {
                clippingChild.updateClippingRect();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSubviewClipStatus(View subview) {
        if (this.mRemoveClippedSubviews && getParent() != null) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            sHelperRect.set(subview.getLeft(), subview.getTop(), subview.getRight(), subview.getBottom());
            if (this.mClippingRect.intersects(sHelperRect.left, sHelperRect.top, sHelperRect.right, sHelperRect.bottom) != (subview.getParent() != null)) {
                int clippedSoFar = 0;
                for (int i = 0; i < this.mAllChildrenCount; i++) {
                    if (this.mAllChildren[i] == subview) {
                        updateSubviewClipStatus(this.mClippingRect, i, clippedSoFar);
                        return;
                    }
                    if (this.mAllChildren[i].getParent() == null) {
                        clippedSoFar++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean pressed) {
    }

    /* access modifiers changed from: 0000 */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    /* access modifiers changed from: 0000 */
    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    /* access modifiers changed from: 0000 */
    public View getChildAtWithSubviewClippingEnabled(int index) {
        return ((View[]) Assertions.assertNotNull(this.mAllChildren))[index];
    }

    /* access modifiers changed from: 0000 */
    public void addViewWithSubviewClippingEnabled(View child, int index) {
        addViewWithSubviewClippingEnabled(child, index, sDefaultLayoutParam);
    }

    /* access modifiers changed from: 0000 */
    public void addViewWithSubviewClippingEnabled(View child, int index, LayoutParams params) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        addInArray(child, index);
        int clippedSoFar = 0;
        for (int i = 0; i < index; i++) {
            if (this.mAllChildren[i].getParent() == null) {
                clippedSoFar++;
            }
        }
        updateSubviewClipStatus(this.mClippingRect, index, clippedSoFar);
        child.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
    }

    /* access modifiers changed from: 0000 */
    public void removeViewWithSubviewClippingEnabled(View view) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int index = indexOfChildInAllChildren(view);
        if (this.mAllChildren[index].getParent() != null) {
            int clippedSoFar = 0;
            for (int i = 0; i < index; i++) {
                if (this.mAllChildren[i].getParent() == null) {
                    clippedSoFar++;
                }
            }
            super.removeViewsInLayout(index - clippedSoFar, 1);
        }
        removeFromArray(index);
    }

    /* access modifiers changed from: 0000 */
    public void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < this.mAllChildrenCount; i++) {
            this.mAllChildren[i].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }

    private int indexOfChildInAllChildren(View child) {
        int count = this.mAllChildrenCount;
        View[] children = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < count; i++) {
            if (children[i] == child) {
                return i;
            }
        }
        return -1;
    }

    private void addInArray(View child, int index) {
        View[] children = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int count = this.mAllChildrenCount;
        int size = children.length;
        if (index == count) {
            if (size == count) {
                this.mAllChildren = new View[(size + 12)];
                System.arraycopy(children, 0, this.mAllChildren, 0, size);
                children = this.mAllChildren;
            }
            int i = this.mAllChildrenCount;
            this.mAllChildrenCount = i + 1;
            children[i] = child;
        } else if (index < count) {
            if (size == count) {
                this.mAllChildren = new View[(size + 12)];
                System.arraycopy(children, 0, this.mAllChildren, 0, index);
                System.arraycopy(children, index, this.mAllChildren, index + 1, count - index);
                children = this.mAllChildren;
            } else {
                System.arraycopy(children, index, children, index + 1, count - index);
            }
            children[index] = child;
            this.mAllChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + index + " count=" + count);
        }
    }

    private void removeFromArray(int index) {
        View[] children = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int count = this.mAllChildrenCount;
        if (index == count - 1) {
            int i = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i;
            children[i] = null;
        } else if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(children, index + 1, children, index, (count - index) - 1);
            int i2 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i2;
            children[i2] = null;
        }
    }

    @VisibleForTesting
    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((ReactViewBackgroundDrawable) getBackground()).getColor();
        }
        return 0;
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.mReactBackgroundDrawable);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, backgroundDrawable}));
            }
        }
        return this.mReactBackgroundDrawable;
    }

    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    public void setHitSlopRect(Rect rect) {
        this.mHitSlopRect = rect;
    }
}
