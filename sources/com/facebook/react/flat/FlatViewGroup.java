package com.facebook.react.flat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.airbnb.android.airmapview.AirMapInterface;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.image.ImageLoadEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.spongycastle.asn1.eac.CertificateBody;

final class FlatViewGroup extends ViewGroup implements FlatMeasuredViewGroup, ReactHitSlopView, ReactInterceptingViewGroup, ReactClippingViewGroup, ReactCompoundViewGroup, ReactPointerEventsView {
    private static final boolean DEBUG_DRAW = false;
    private static final boolean DEBUG_DRAW_TEXT = false;
    static final boolean DEBUG_HIGHLIGHT_PERFORMANCE_ISSUES = false;
    private static final SparseArray<View> EMPTY_DETACHED_VIEWS = new SparseArray<>(0);
    private static final ArrayList<FlatViewGroup> LAYOUT_REQUESTS = new ArrayList<>();
    private static final Rect VIEW_BOUNDS = new Rect();
    private static Paint sDebugCornerPaint;
    private static Rect sDebugRect;
    private static Paint sDebugRectPaint;
    private static Paint sDebugTextBackgroundPaint;
    private static Paint sDebugTextPaint;
    private boolean mAndroidDebugDraw;
    private AttachDetachListener[] mAttachDetachListeners = AttachDetachListener.EMPTY_ARRAY;
    private int mDrawChildIndex = 0;
    private DrawCommandManager mDrawCommandManager;
    private DrawCommand[] mDrawCommands = DrawCommand.EMPTY_ARRAY;
    private Rect mHitSlopRect;
    private Drawable mHotspot;
    private InvalidateCallback mInvalidateCallback;
    private boolean mIsAttached = false;
    private boolean mIsLayoutRequested = false;
    private long mLastTouchDownTime;
    private boolean mNeedsOffscreenAlphaCompositing = false;
    private NodeRegion[] mNodeRegions = NodeRegion.EMPTY_ARRAY;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private PointerEvents mPointerEvents = PointerEvents.AUTO;

    static final class InvalidateCallback extends WeakReference<FlatViewGroup> {
        private InvalidateCallback(FlatViewGroup view) {
            super(view);
        }

        public void invalidate() {
            FlatViewGroup view = (FlatViewGroup) get();
            if (view != null) {
                view.invalidate();
            }
        }

        public void dispatchImageLoadEvent(int reactTag, int imageLoadEvent) {
            FlatViewGroup view = (FlatViewGroup) get();
            if (view != null) {
                ((UIManagerModule) ((ReactContext) view.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ImageLoadEvent(reactTag, imageLoadEvent));
            }
        }
    }

    FlatViewGroup(Context context) {
        super(context);
        setClipChildren(false);
    }

    /* access modifiers changed from: protected */
    public void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
    }

    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
        if (!this.mIsLayoutRequested) {
            this.mIsLayoutRequested = true;
            LAYOUT_REQUESTS.add(this);
        }
    }

    public int reactTagForTouch(float touchX, float touchY) {
        SoftAssertions.assertCondition(this.mPointerEvents != PointerEvents.NONE, "TouchTargetHelper should not allow calling this method when pointer events are NONE");
        if (this.mPointerEvents != PointerEvents.BOX_ONLY) {
            NodeRegion nodeRegion = virtualNodeRegionWithinBounds(touchX, touchY);
            if (nodeRegion != null) {
                return nodeRegion.getReactTag(touchX, touchY);
            }
        }
        return getId();
    }

    public boolean interceptsTouchEvent(float touchX, float touchY) {
        NodeRegion nodeRegion = anyNodeRegionWithinBounds(touchX, touchY);
        return nodeRegion != null && nodeRegion.mIsVirtual;
    }

    /* access modifiers changed from: protected */
    public void onDebugDraw(Canvas canvas) {
        this.mAndroidDebugDraw = true;
    }

    public void dispatchDraw(Canvas canvas) {
        this.mAndroidDebugDraw = false;
        super.dispatchDraw(canvas);
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.draw(canvas);
        } else {
            for (DrawCommand drawCommand : this.mDrawCommands) {
                drawCommand.draw(this, canvas);
            }
        }
        if (this.mDrawChildIndex != getChildCount()) {
            throw new RuntimeException("Did not draw all children: " + this.mDrawChildIndex + " / " + getChildCount());
        }
        this.mDrawChildIndex = 0;
        if (this.mAndroidDebugDraw) {
            initDebugDrawResources();
            debugDraw(canvas);
        }
        if (this.mHotspot != null) {
            this.mHotspot.draw(canvas);
        }
    }

    private void debugDraw(Canvas canvas) {
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.debugDraw(canvas);
        } else {
            for (DrawCommand drawCommand : this.mDrawCommands) {
                drawCommand.debugDraw(this, canvas);
            }
        }
        this.mDrawChildIndex = 0;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void debugDrawNextChild(Canvas canvas) {
        View child = getChildAt(this.mDrawChildIndex);
        debugDrawRect(canvas, child instanceof FlatViewGroup ? -12303292 : -65536, (float) child.getLeft(), (float) child.getTop(), (float) child.getRight(), (float) child.getBottom());
        this.mDrawChildIndex++;
    }

    /* access modifiers changed from: 0000 */
    public int dipsToPixels(int dips) {
        return (int) ((((float) dips) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static void fillRect(Canvas canvas, Paint paint, float x1, float y1, float x2, float y2) {
        if (x1 != x2 && y1 != y2) {
            if (x1 > x2) {
                float tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            if (y1 > y2) {
                float tmp2 = y1;
                y1 = y2;
                y2 = tmp2;
            }
            canvas.drawRect(x1, y1, x2, y2, paint);
        }
    }

    private static int sign(float x) {
        return x >= 0.0f ? 1 : -1;
    }

    private static void drawCorner(Canvas c, Paint paint, float x1, float y1, float dx, float dy, float lw) {
        fillRect(c, paint, x1, y1, x1 + dx, y1 + (((float) sign(dy)) * lw));
        fillRect(c, paint, x1, y1, x1 + (((float) sign(dx)) * lw), y1 + dy);
    }

    private static void drawRectCorners(Canvas canvas, float x1, float y1, float x2, float y2, Paint paint, int lineLength, int lineWidth) {
        drawCorner(canvas, paint, x1, y1, (float) lineLength, (float) lineLength, (float) lineWidth);
        drawCorner(canvas, paint, x1, y2, (float) lineLength, (float) (-lineLength), (float) lineWidth);
        drawCorner(canvas, paint, x2, y1, (float) (-lineLength), (float) lineLength, (float) lineWidth);
        drawCorner(canvas, paint, x2, y2, (float) (-lineLength), (float) (-lineLength), (float) lineWidth);
    }

    private void initDebugDrawResources() {
        if (sDebugTextPaint == null) {
            sDebugTextPaint = new Paint();
            sDebugTextPaint.setTextAlign(Align.RIGHT);
            sDebugTextPaint.setTextSize((float) dipsToPixels(9));
            sDebugTextPaint.setTypeface(Typeface.MONOSPACE);
            sDebugTextPaint.setAntiAlias(true);
            sDebugTextPaint.setColor(-65536);
        }
        if (sDebugTextBackgroundPaint == null) {
            sDebugTextBackgroundPaint = new Paint();
            sDebugTextBackgroundPaint.setColor(-1);
            sDebugTextBackgroundPaint.setAlpha(200);
            sDebugTextBackgroundPaint.setStyle(Style.FILL);
        }
        if (sDebugRectPaint == null) {
            sDebugRectPaint = new Paint();
            sDebugRectPaint.setAlpha(100);
            sDebugRectPaint.setStyle(Style.STROKE);
        }
        if (sDebugCornerPaint == null) {
            sDebugCornerPaint = new Paint();
            sDebugCornerPaint.setAlpha(200);
            sDebugCornerPaint.setColor(Color.rgb(63, CertificateBody.profileType, 255));
            sDebugCornerPaint.setStyle(Style.FILL);
        }
        if (sDebugRect == null) {
            sDebugRect = new Rect();
        }
    }

    private void debugDrawRect(Canvas canvas, int color, float left, float top, float right, float bottom) {
        debugDrawNamedRect(canvas, color, "", left, top, right, bottom);
    }

    /* access modifiers changed from: 0000 */
    public void debugDrawNamedRect(Canvas canvas, int color, String name, float left, float top, float right, float bottom) {
        sDebugRectPaint.setColor((sDebugRectPaint.getColor() & AirMapInterface.CIRCLE_BORDER_COLOR) | (16777215 & color));
        sDebugRectPaint.setAlpha(100);
        canvas.drawRect(left, top, right - 1.0f, bottom - 1.0f, sDebugRectPaint);
        drawRectCorners(canvas, left, top, right, bottom, sDebugCornerPaint, dipsToPixels(8), dipsToPixels(1));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"MissingSuperCall"})
    public boolean verifyDrawable(Drawable who) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        if (!this.mIsAttached) {
            this.mIsAttached = true;
            super.onAttachedToWindow();
            dispatchOnAttached(this.mAttachDetachListeners);
            updateClippingRect();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (!this.mIsAttached) {
            throw new RuntimeException("Double detach");
        }
        this.mIsAttached = false;
        super.onDetachedFromWindow();
        dispatchOnDetached(this.mAttachDetachListeners);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.mHotspot != null) {
            this.mHotspot.setBounds(0, 0, w, h);
            invalidate();
        }
        updateClippingRect();
    }

    public void dispatchDrawableHotspotChanged(float x, float y) {
        if (this.mHotspot != null) {
            this.mHotspot.setHotspot(x, y);
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mHotspot != null && this.mHotspot.isStateful()) {
            this.mHotspot.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mHotspot != null) {
            this.mHotspot.jumpToCurrentState();
        }
    }

    public void invalidate() {
        invalidate(0, 0, getWidth() + 1, getHeight() + 1);
    }

    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener listener) {
        this.mOnInterceptTouchEventListener = listener;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        long downTime = ev.getDownTime();
        if (downTime != this.mLastTouchDownTime) {
            this.mLastTouchDownTime = downTime;
            if (interceptsTouchEvent(ev.getX(), ev.getY())) {
                return true;
            }
        }
        if ((this.mOnInterceptTouchEventListener != null && this.mOnInterceptTouchEventListener.onInterceptTouchEvent(this, ev)) || this.mPointerEvents == PointerEvents.NONE || this.mPointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mPointerEvents == PointerEvents.NONE) {
            return false;
        }
        if (this.mPointerEvents == PointerEvents.BOX_NONE && virtualNodeRegionWithinBounds(ev.getX(), ev.getY()) == null) {
            return false;
        }
        return true;
    }

    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    /* access modifiers changed from: 0000 */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    /* access modifiers changed from: 0000 */
    public void setNeedsOffscreenAlphaCompositing(boolean needsOffscreenAlphaCompositing) {
        this.mNeedsOffscreenAlphaCompositing = needsOffscreenAlphaCompositing;
    }

    /* access modifiers changed from: 0000 */
    public void setHotspot(Drawable hotspot) {
        if (this.mHotspot != null) {
            this.mHotspot.setCallback(null);
            unscheduleDrawable(this.mHotspot);
        }
        if (hotspot != null) {
            hotspot.setCallback(this);
            if (hotspot.isStateful()) {
                hotspot.setState(getDrawableState());
            }
        }
        this.mHotspot = hotspot;
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void drawNextChild(Canvas canvas) {
        View child = getChildAt(this.mDrawChildIndex);
        if (child instanceof FlatViewGroup) {
            super.drawChild(canvas, child, getDrawingTime());
        } else {
            canvas.save(2);
            child.getHitRect(VIEW_BOUNDS);
            canvas.clipRect(VIEW_BOUNDS);
            super.drawChild(canvas, child, getDrawingTime());
            canvas.restore();
        }
        this.mDrawChildIndex++;
    }

    /* access modifiers changed from: 0000 */
    public void mountDrawCommands(DrawCommand[] drawCommands) {
        this.mDrawCommands = drawCommands;
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void mountClippingDrawCommands(DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] maxBottom, float[] minTop, boolean willMountViews) {
        ((DrawCommandManager) Assertions.assertNotNull(this.mDrawCommandManager)).mountDrawCommands(drawCommands, drawViewIndexMap, maxBottom, minTop, willMountViews);
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void onViewDropped(View view) {
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.onClippedViewDropped(view);
        }
    }

    /* access modifiers changed from: 0000 */
    public NodeRegion getNodeRegionForTag(int reactTag) {
        NodeRegion[] nodeRegionArr;
        for (NodeRegion region : this.mNodeRegions) {
            if (region.matchesTag(reactTag)) {
                return region;
            }
        }
        return NodeRegion.EMPTY;
    }

    /* access modifiers changed from: 0000 */
    public SparseArray<View> getDetachedViews() {
        if (this.mDrawCommandManager == null) {
            return EMPTY_DETACHED_VIEWS;
        }
        return this.mDrawCommandManager.getDetachedViews();
    }

    /* access modifiers changed from: 0000 */
    public void removeDetachedView(View view) {
        removeDetachedView(view, false);
    }

    public void removeAllViewsInLayout() {
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: 0000 */
    public void mountAttachDetachListeners(AttachDetachListener[] listeners) {
        if (this.mIsAttached) {
            dispatchOnAttached(listeners);
            dispatchOnDetached(this.mAttachDetachListeners);
        }
        this.mAttachDetachListeners = listeners;
    }

    /* access modifiers changed from: 0000 */
    public void mountNodeRegions(NodeRegion[] nodeRegions) {
        this.mNodeRegions = nodeRegions;
    }

    /* access modifiers changed from: 0000 */
    public void mountClippingNodeRegions(NodeRegion[] nodeRegions, float[] maxBottom, float[] minTop) {
        this.mNodeRegions = nodeRegions;
        ((DrawCommandManager) Assertions.assertNotNull(this.mDrawCommandManager)).mountNodeRegions(nodeRegions, maxBottom, minTop);
    }

    /* access modifiers changed from: 0000 */
    public void mountViews(ViewResolver viewResolver, int[] viewsToAdd, int[] viewsToDetach) {
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.mountViews(viewResolver, viewsToAdd, viewsToDetach);
        } else {
            for (int viewToAdd : viewsToAdd) {
                if (viewToAdd > 0) {
                    View view = viewResolver.getView(viewToAdd);
                    ensureViewHasNoParent(view);
                    addViewInLayout(view);
                } else {
                    View view2 = viewResolver.getView(-viewToAdd);
                    ensureViewHasNoParent(view2);
                    attachViewToParent(view2);
                }
            }
            for (int viewToDetach : viewsToDetach) {
                View view3 = viewResolver.getView(viewToDetach);
                if (view3.getParent() != null) {
                    throw new RuntimeException("Trying to remove view not owned by FlatViewGroup");
                }
                removeDetachedView(view3, false);
            }
        }
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void addViewInLayout(View view) {
        addViewInLayout(view, -1, ensureLayoutParams(view.getLayoutParams()), true);
    }

    /* access modifiers changed from: 0000 */
    public void addViewInLayout(View view, int index) {
        addViewInLayout(view, index, ensureLayoutParams(view.getLayoutParams()), true);
    }

    /* access modifiers changed from: 0000 */
    public void attachViewToParent(View view) {
        attachViewToParent(view, -1, ensureLayoutParams(view.getLayoutParams()));
    }

    /* access modifiers changed from: 0000 */
    public void attachViewToParent(View view, int index) {
        attachViewToParent(view, index, ensureLayoutParams(view.getLayoutParams()));
    }

    private void processLayoutRequest() {
        this.mIsLayoutRequested = false;
        int childCount = getChildCount();
        for (int i = 0; i != childCount; i++) {
            View child = getChildAt(i);
            if (child.isLayoutRequested()) {
                child.measure(MeasureSpec.makeMeasureSpec(child.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(child.getHeight(), 1073741824));
                child.layout(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            }
        }
    }

    static void processLayoutRequests() {
        int numLayoutRequests = LAYOUT_REQUESTS.size();
        for (int i = 0; i != numLayoutRequests; i++) {
            ((FlatViewGroup) LAYOUT_REQUESTS.get(i)).processLayoutRequest();
        }
        LAYOUT_REQUESTS.clear();
    }

    public Rect measureWithCommands() {
        DrawCommand[] drawCommandArr;
        int childCount = getChildCount();
        if (childCount == 0 && this.mDrawCommands.length == 0) {
            return new Rect(0, 0, 0, 0);
        }
        int left = Integer.MAX_VALUE;
        int top = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int bottom = Integer.MIN_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            left = Math.min(left, child.getLeft());
            top = Math.min(top, child.getTop());
            right = Math.max(right, child.getRight());
            bottom = Math.max(bottom, child.getBottom());
        }
        for (DrawCommand mDrawCommand : this.mDrawCommands) {
            if (mDrawCommand instanceof AbstractDrawCommand) {
                AbstractDrawCommand drawCommand = (AbstractDrawCommand) mDrawCommand;
                left = Math.min(left, Math.round(drawCommand.getLeft()));
                top = Math.min(top, Math.round(drawCommand.getTop()));
                right = Math.max(right, Math.round(drawCommand.getRight()));
                bottom = Math.max(bottom, Math.round(drawCommand.getBottom()));
            }
        }
        return new Rect(left, top, right, bottom);
    }

    private NodeRegion virtualNodeRegionWithinBounds(float touchX, float touchY) {
        if (this.mDrawCommandManager != null) {
            return this.mDrawCommandManager.virtualNodeRegionWithinBounds(touchX, touchY);
        }
        for (int i = this.mNodeRegions.length - 1; i >= 0; i--) {
            NodeRegion nodeRegion = this.mNodeRegions[i];
            if (nodeRegion.mIsVirtual && nodeRegion.withinBounds(touchX, touchY)) {
                return nodeRegion;
            }
        }
        return null;
    }

    private NodeRegion anyNodeRegionWithinBounds(float touchX, float touchY) {
        if (this.mDrawCommandManager != null) {
            return this.mDrawCommandManager.anyNodeRegionWithinBounds(touchX, touchY);
        }
        for (int i = this.mNodeRegions.length - 1; i >= 0; i--) {
            NodeRegion nodeRegion = this.mNodeRegions[i];
            if (nodeRegion.withinBounds(touchX, touchY)) {
                return nodeRegion;
            }
        }
        return null;
    }

    private static void ensureViewHasNoParent(View view) {
        ViewParent oldParent = view.getParent();
        if (oldParent != null) {
            throw new RuntimeException("Cannot add view " + view + " to FlatViewGroup while it has a parent " + oldParent);
        }
    }

    private void dispatchOnAttached(AttachDetachListener[] listeners) {
        if (listeners.length != 0) {
            InvalidateCallback callback = getInvalidateCallback();
            for (AttachDetachListener listener : listeners) {
                listener.onAttached(callback);
            }
        }
    }

    private InvalidateCallback getInvalidateCallback() {
        if (this.mInvalidateCallback == null) {
            this.mInvalidateCallback = new InvalidateCallback();
        }
        return this.mInvalidateCallback;
    }

    private static void dispatchOnDetached(AttachDetachListener[] listeners) {
        for (AttachDetachListener listener : listeners) {
            listener.onDetached();
        }
    }

    private LayoutParams ensureLayoutParams(LayoutParams lp) {
        return checkLayoutParams(lp) ? lp : generateDefaultLayoutParams();
    }

    public void updateClippingRect() {
        if (this.mDrawCommandManager != null && this.mDrawCommandManager.updateClippingRect()) {
            invalidate();
        }
    }

    public void getClippingRect(Rect outClippingRect) {
        if (this.mDrawCommandManager == null) {
            throw new RuntimeException("Trying to get the clipping rect for a non-clipping FlatViewGroup");
        }
        this.mDrawCommandManager.getClippingRect(outClippingRect);
    }

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        boolean currentlyClipping = getRemoveClippedSubviews();
        if (removeClippedSubviews != currentlyClipping) {
            if (currentlyClipping) {
                throw new RuntimeException("Trying to transition FlatViewGroup from clipping to non-clipping state");
            }
            this.mDrawCommandManager = DrawCommandManager.getVerticalClippingInstance(this, this.mDrawCommands);
            this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        }
    }

    public boolean getRemoveClippedSubviews() {
        return this.mDrawCommandManager != null;
    }

    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    /* access modifiers changed from: 0000 */
    public void setHitSlopRect(Rect rect) {
        this.mHitSlopRect = rect;
    }
}
