package com.facebook.react.flat;

import android.graphics.Rect;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.annotations.ReactProp;

class FlatShadowNode extends LayoutShadowNode {
    static final FlatShadowNode[] EMPTY_ARRAY = new FlatShadowNode[0];
    private static final DrawView EMPTY_DRAW_VIEW = new DrawView(0);
    private static final Rect LOGICAL_OFFSET_EMPTY = new Rect();
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_DECOMPOSED_MATRIX = "decomposedMatrix";
    protected static final String PROP_HORIZONTAL = "horizontal";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    private static final String PROP_OPACITY = "opacity";
    protected static final String PROP_REMOVE_CLIPPED_SUBVIEWS = "removeClippedSubviews";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private AttachDetachListener[] mAttachDetachListeners = AttachDetachListener.EMPTY_ARRAY;
    private boolean mBackingViewIsCreated;
    private float mClipBottom;
    private float mClipLeft;
    float mClipRadius;
    private float mClipRight;
    boolean mClipToBounds = false;
    private float mClipTop;
    private DrawBackgroundColor mDrawBackground;
    private DrawCommand[] mDrawCommands = DrawCommand.EMPTY_ARRAY;
    private DrawView mDrawView;
    private boolean mForceMountChildrenToView;
    private boolean mIsUpdated = true;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private int mLayoutX;
    private int mLayoutY;
    private Rect mLogicalOffset = LOGICAL_OFFSET_EMPTY;
    private FlatShadowNode[] mNativeChildren = EMPTY_ARRAY;
    private int mNativeParentTag;
    private NodeRegion mNodeRegion = NodeRegion.EMPTY;
    private NodeRegion[] mNodeRegions = NodeRegion.EMPTY_ARRAY;
    private boolean mOverflowsContainer;
    private int mViewBottom;
    private int mViewLeft;
    private int mViewRight;
    private int mViewTop;

    FlatShadowNode() {
    }

    /* access modifiers changed from: 0000 */
    public void handleUpdateProperties(ReactStylesDiffMap styles) {
        if (mountsToView()) {
            return;
        }
        if (styles.hasKey(PROP_DECOMPOSED_MATRIX) || styles.hasKey(PROP_OPACITY) || styles.hasKey(PROP_RENDER_TO_HARDWARE_TEXTURE) || styles.hasKey("testID") || styles.hasKey(PROP_ACCESSIBILITY_LABEL) || styles.hasKey(PROP_ACCESSIBILITY_COMPONENT_TYPE) || styles.hasKey(PROP_ACCESSIBILITY_LIVE_REGION) || styles.hasKey(PROP_TRANSFORM) || styles.hasKey(PROP_IMPORTANT_FOR_ACCESSIBILITY) || styles.hasKey("removeClippedSubviews")) {
            forceMountToView();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void forceMountChildrenToView() {
        if (!this.mForceMountChildrenToView) {
            this.mForceMountChildrenToView = true;
            int childCount = getChildCount();
            for (int i = 0; i != childCount; i++) {
                ReactShadowNode child = getChildAt(i);
                if (child instanceof FlatShadowNode) {
                    ((FlatShadowNode) child).forceMountToView();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void collectState(StateBuilder stateBuilder, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        if (this.mDrawBackground != null) {
            this.mDrawBackground = (DrawBackgroundColor) this.mDrawBackground.updateBoundsAndFreeze(left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
            stateBuilder.addDrawCommand(this.mDrawBackground);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean doesDraw() {
        return (this.mDrawView == null && this.mDrawBackground == null) ? false : true;
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(int backgroundColor) {
        this.mDrawBackground = backgroundColor == 0 ? null : new DrawBackgroundColor(backgroundColor);
        invalidate();
    }

    public void setOverflow(String overflow) {
        super.setOverflow(overflow);
        this.mClipToBounds = PushNotificationConstants.EXTRA_IS_HIDDEN.equals(overflow);
        if (this.mClipToBounds) {
            this.mOverflowsContainer = false;
            if (this.mClipRadius > 0.5f) {
                forceMountToView();
            }
        } else {
            updateOverflowsContainer();
        }
        invalidate();
    }

    public final boolean clipToBounds() {
        return this.mClipToBounds;
    }

    public final int getScreenX() {
        return this.mViewLeft;
    }

    public final int getScreenY() {
        return this.mViewTop;
    }

    public final int getScreenWidth() {
        if (mountsToView()) {
            return this.mViewRight - this.mViewLeft;
        }
        return Math.round(this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
    }

    public final int getScreenHeight() {
        if (mountsToView()) {
            return this.mViewBottom - this.mViewTop;
        }
        return Math.round(this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
    }

    public void addChildAt(ReactShadowNode child, int i) {
        super.addChildAt(child, i);
        if (this.mForceMountChildrenToView && (child instanceof FlatShadowNode)) {
            ((FlatShadowNode) child).forceMountToView();
        }
    }

    /* access modifiers changed from: protected */
    public final void invalidate() {
        FlatShadowNode node = this;
        while (true) {
            if (node.mountsToView()) {
                if (!node.mIsUpdated) {
                    node.mIsUpdated = true;
                } else {
                    return;
                }
            }
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                node = (FlatShadowNode) parent;
            } else {
                return;
            }
        }
    }

    public void markUpdated() {
        super.markUpdated();
        this.mIsUpdated = true;
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public final boolean isUpdated() {
        return this.mIsUpdated;
    }

    /* access modifiers changed from: 0000 */
    public final void resetUpdated() {
        this.mIsUpdated = false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean clipBoundsChanged(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        return (this.mClipLeft == clipLeft && this.mClipTop == clipTop && this.mClipRight == clipRight && this.mClipBottom == clipBottom) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public final void setClipBounds(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        this.mClipLeft = clipLeft;
        this.mClipTop = clipTop;
        this.mClipRight = clipRight;
        this.mClipBottom = clipBottom;
    }

    /* access modifiers changed from: 0000 */
    public final DrawCommand[] getDrawCommands() {
        return this.mDrawCommands;
    }

    /* access modifiers changed from: 0000 */
    public final void setDrawCommands(DrawCommand[] drawCommands) {
        this.mDrawCommands = drawCommands;
    }

    /* access modifiers changed from: 0000 */
    public final void setAttachDetachListeners(AttachDetachListener[] listeners) {
        this.mAttachDetachListeners = listeners;
    }

    /* access modifiers changed from: 0000 */
    public final AttachDetachListener[] getAttachDetachListeners() {
        return this.mAttachDetachListeners;
    }

    /* access modifiers changed from: 0000 */
    public final FlatShadowNode[] getNativeChildren() {
        return this.mNativeChildren;
    }

    /* access modifiers changed from: 0000 */
    public final void setNativeChildren(FlatShadowNode[] nativeChildren) {
        this.mNativeChildren = nativeChildren;
    }

    /* access modifiers changed from: 0000 */
    public final int getNativeParentTag() {
        return this.mNativeParentTag;
    }

    /* access modifiers changed from: 0000 */
    public final void setNativeParentTag(int nativeParentTag) {
        this.mNativeParentTag = nativeParentTag;
    }

    /* access modifiers changed from: 0000 */
    public final NodeRegion[] getNodeRegions() {
        return this.mNodeRegions;
    }

    /* access modifiers changed from: 0000 */
    public final void setNodeRegions(NodeRegion[] nodeRegion) {
        this.mNodeRegions = nodeRegion;
        updateOverflowsContainer();
    }

    /* access modifiers changed from: 0000 */
    public final void updateOverflowsContainer() {
        boolean overflowsContainer = false;
        int width = (int) (this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
        int height = (int) (this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
        float leftBound = 0.0f;
        float rightBound = (float) width;
        float topBound = 0.0f;
        float bottomBound = (float) height;
        Rect logicalOffset = null;
        if (!this.mClipToBounds && height > 0 && width > 0) {
            NodeRegion[] nodeRegionArr = this.mNodeRegions;
            int length = nodeRegionArr.length;
            for (int i = 0; i < length; i++) {
                NodeRegion region = nodeRegionArr[i];
                if (region.getLeft() < leftBound) {
                    leftBound = region.getLeft();
                    overflowsContainer = true;
                }
                if (region.getRight() > rightBound) {
                    rightBound = region.getRight();
                    overflowsContainer = true;
                }
                if (region.getTop() < topBound) {
                    topBound = region.getTop();
                    overflowsContainer = true;
                }
                if (region.getBottom() > bottomBound) {
                    bottomBound = region.getBottom();
                    overflowsContainer = true;
                }
            }
            if (overflowsContainer) {
                logicalOffset = new Rect((int) leftBound, (int) topBound, (int) (rightBound - ((float) width)), (int) (bottomBound - ((float) height)));
            }
        }
        if (!overflowsContainer && this.mNodeRegion != NodeRegion.EMPTY) {
            int children = getChildCount();
            for (int i2 = 0; i2 < children; i2++) {
                ReactShadowNode node = getChildAt(i2);
                if ((node instanceof FlatShadowNode) && ((FlatShadowNode) node).mOverflowsContainer) {
                    Rect childLogicalOffset = ((FlatShadowNode) node).mLogicalOffset;
                    if (logicalOffset == null) {
                        logicalOffset = new Rect();
                    }
                    logicalOffset.union(childLogicalOffset);
                    overflowsContainer = true;
                }
            }
        }
        if (this.mOverflowsContainer != overflowsContainer) {
            this.mOverflowsContainer = overflowsContainer;
            if (logicalOffset == null) {
                logicalOffset = LOGICAL_OFFSET_EMPTY;
            }
            this.mLogicalOffset = logicalOffset;
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateNodeRegion(float left, float top, float right, float bottom, boolean isVirtual) {
        if (!this.mNodeRegion.matches(left, top, right, bottom, isVirtual)) {
            setNodeRegion(new NodeRegion(left, top, right, bottom, getReactTag(), isVirtual));
        }
    }

    /* access modifiers changed from: protected */
    public final void setNodeRegion(NodeRegion nodeRegion) {
        this.mNodeRegion = nodeRegion;
        updateOverflowsContainer();
    }

    /* access modifiers changed from: 0000 */
    public final NodeRegion getNodeRegion() {
        return this.mNodeRegion;
    }

    /* access modifiers changed from: 0000 */
    public final void setViewBounds(int left, int top, int right, int bottom) {
        this.mViewLeft = left;
        this.mViewTop = top;
        this.mViewRight = right;
        this.mViewBottom = bottom;
    }

    /* access modifiers changed from: 0000 */
    public final int getViewLeft() {
        return this.mViewLeft;
    }

    /* access modifiers changed from: 0000 */
    public final int getViewTop() {
        return this.mViewTop;
    }

    /* access modifiers changed from: 0000 */
    public final int getViewRight() {
        return this.mViewRight;
    }

    /* access modifiers changed from: 0000 */
    public final int getViewBottom() {
        return this.mViewBottom;
    }

    /* access modifiers changed from: 0000 */
    public final void forceMountToView() {
        if (!isVirtual() && this.mDrawView == null) {
            this.mDrawView = EMPTY_DRAW_VIEW;
            invalidate();
            this.mNodeRegion = NodeRegion.EMPTY;
        }
    }

    /* access modifiers changed from: 0000 */
    public final DrawView collectDrawView(float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        Assertions.assumeNotNull(this.mDrawView);
        if (this.mDrawView == EMPTY_DRAW_VIEW) {
            this.mDrawView = new DrawView(getReactTag());
        }
        this.mDrawView = this.mDrawView.collectDrawView(left, top, right, bottom, left + ((float) this.mLogicalOffset.left), top + ((float) this.mLogicalOffset.top), right + ((float) this.mLogicalOffset.right), bottom + ((float) this.mLogicalOffset.bottom), clipLeft, clipTop, clipRight, clipBottom, this.mClipToBounds ? this.mClipRadius : 0.0f);
        return this.mDrawView;
    }

    /* access modifiers changed from: 0000 */
    public final OnLayoutEvent obtainLayoutEvent(int x, int y, int width, int height) {
        if (this.mLayoutX == x && this.mLayoutY == y && this.mLayoutWidth == width && this.mLayoutHeight == height) {
            return null;
        }
        this.mLayoutX = x;
        this.mLayoutY = y;
        this.mLayoutWidth = width;
        this.mLayoutHeight = height;
        return OnLayoutEvent.obtain(getReactTag(), x, y, width, height);
    }

    /* access modifiers changed from: 0000 */
    public final boolean mountsToView() {
        return this.mDrawView != null;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isBackingViewCreated() {
        return this.mBackingViewIsCreated;
    }

    /* access modifiers changed from: 0000 */
    public final void signalBackingViewIsCreated() {
        this.mBackingViewIsCreated = true;
    }

    public boolean clipsSubviews() {
        return false;
    }

    public boolean isHorizontal() {
        return false;
    }
}
