package com.facebook.react.uimanager;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import java.util.ArrayList;

@ReactPropertyHolder
public class ReactShadowNode {
    private ArrayList<ReactShadowNode> mChildren;
    private final Spacing mDefaultPadding = new Spacing(0.0f);
    private boolean mIsLayoutOnly;
    private ArrayList<ReactShadowNode> mNativeChildren;
    private ReactShadowNode mNativeParent;
    private boolean mNodeUpdated = true;
    private final Spacing mPadding = new Spacing(Float.NaN);
    private ReactShadowNode mParent;
    private int mReactTag;
    private ReactShadowNode mRootNode;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mScreenX;
    private int mScreenY;
    private boolean mShouldNotifyOnLayout;
    private ThemedReactContext mThemedContext;
    private int mTotalNativeChildren = 0;
    private String mViewClassName;
    private final YogaNode mYogaNode;

    public ReactShadowNode() {
        if (!isVirtual()) {
            YogaNode node = (YogaNode) YogaNodePool.get().acquire();
            if (node == null) {
                node = new YogaNode();
            }
            this.mYogaNode = node;
            return;
        }
        this.mYogaNode = null;
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return false;
    }

    public final String getViewClass() {
        return (String) Assertions.assertNotNull(this.mViewClassName);
    }

    public final boolean hasUpdates() {
        return this.mNodeUpdated || hasNewLayout() || isDirty();
    }

    public final void markUpdateSeen() {
        this.mNodeUpdated = false;
        if (hasNewLayout()) {
            markLayoutSeen();
        }
    }

    public void markUpdated() {
        if (!this.mNodeUpdated) {
            this.mNodeUpdated = true;
            ReactShadowNode parent = getParent();
            if (parent != null) {
                parent.markUpdated();
            }
        }
    }

    public final boolean hasUnseenUpdates() {
        return this.mNodeUpdated;
    }

    public void dirty() {
        if (!isVirtual()) {
            this.mYogaNode.dirty();
        }
    }

    public final boolean isDirty() {
        return this.mYogaNode != null && this.mYogaNode.isDirty();
    }

    public void addChildAt(ReactShadowNode child, int i) {
        if (child.mParent != null) {
            throw new IllegalViewOperationException("Tried to add child that already has a parent! Remove it from its parent first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<>(4);
        }
        this.mChildren.add(i, child);
        child.mParent = this;
        if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
            YogaNode childYogaNode = child.mYogaNode;
            if (childYogaNode == null) {
                throw new RuntimeException("Cannot add a child that doesn't have a CSS node to a node without a measure function!");
            }
            this.mYogaNode.addChildAt(childYogaNode, i);
        }
        markUpdated();
        int increase = child.mIsLayoutOnly ? child.mTotalNativeChildren : 1;
        this.mTotalNativeChildren += increase;
        updateNativeChildrenCountInParent(increase);
    }

    public ReactShadowNode removeChildAt(int i) {
        if (this.mChildren == null) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
        }
        ReactShadowNode removed = (ReactShadowNode) this.mChildren.remove(i);
        removed.mParent = null;
        if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
            this.mYogaNode.removeChildAt(i);
        }
        markUpdated();
        int decrease = removed.mIsLayoutOnly ? removed.mTotalNativeChildren : 1;
        this.mTotalNativeChildren -= decrease;
        updateNativeChildrenCountInParent(-decrease);
        return removed;
    }

    public final int getChildCount() {
        if (this.mChildren == null) {
            return 0;
        }
        return this.mChildren.size();
    }

    public final ReactShadowNode getChildAt(int i) {
        if (this.mChildren != null) {
            return (ReactShadowNode) this.mChildren.get(i);
        }
        throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
    }

    public final int indexOf(ReactShadowNode child) {
        if (this.mChildren == null) {
            return -1;
        }
        return this.mChildren.indexOf(child);
    }

    public void removeAndDisposeAllChildren() {
        int i;
        if (getChildCount() != 0) {
            int decrease = 0;
            for (int i2 = getChildCount() - 1; i2 >= 0; i2--) {
                if (this.mYogaNode != null && !this.mYogaNode.isMeasureDefined()) {
                    this.mYogaNode.removeChildAt(i2);
                }
                ReactShadowNode toRemove = getChildAt(i2);
                toRemove.mParent = null;
                toRemove.dispose();
                if (toRemove.mIsLayoutOnly) {
                    i = toRemove.mTotalNativeChildren;
                } else {
                    i = 1;
                }
                decrease += i;
            }
            ((ArrayList) Assertions.assertNotNull(this.mChildren)).clear();
            markUpdated();
            this.mTotalNativeChildren -= decrease;
            updateNativeChildrenCountInParent(-decrease);
        }
    }

    private void updateNativeChildrenCountInParent(int delta) {
        if (this.mIsLayoutOnly) {
            ReactShadowNode parent = getParent();
            while (parent != null) {
                parent.mTotalNativeChildren += delta;
                if (parent.mIsLayoutOnly) {
                    parent = parent.getParent();
                } else {
                    return;
                }
            }
        }
    }

    public void onBeforeLayout() {
    }

    public final void updateProperties(ReactStylesDiffMap props) {
        ViewManagerPropertyUpdater.updateProps(this, props);
        onAfterUpdateTransaction();
    }

    public void onAfterUpdateTransaction() {
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
    }

    /* access modifiers changed from: 0000 */
    public boolean dispatchUpdates(float absoluteX, float absoluteY, UIViewOperationQueue uiViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        if (this.mNodeUpdated) {
            onCollectExtraUpdates(uiViewOperationQueue);
        }
        if (!hasNewLayout()) {
            return false;
        }
        float layoutX = getLayoutX();
        float layoutY = getLayoutY();
        int newAbsoluteLeft = Math.round(absoluteX + layoutX);
        int newAbsoluteTop = Math.round(absoluteY + layoutY);
        int newAbsoluteRight = Math.round(absoluteX + layoutX + getLayoutWidth());
        int newAbsoluteBottom = Math.round(absoluteY + layoutY + getLayoutHeight());
        int newScreenX = Math.round(layoutX);
        int newScreenY = Math.round(layoutY);
        int newScreenWidth = newAbsoluteRight - newAbsoluteLeft;
        int newScreenHeight = newAbsoluteBottom - newAbsoluteTop;
        boolean layoutHasChanged = (newScreenX == this.mScreenX && newScreenY == this.mScreenY && newScreenWidth == this.mScreenWidth && newScreenHeight == this.mScreenHeight) ? false : true;
        this.mScreenX = newScreenX;
        this.mScreenY = newScreenY;
        this.mScreenWidth = newScreenWidth;
        this.mScreenHeight = newScreenHeight;
        if (!layoutHasChanged) {
            return layoutHasChanged;
        }
        nativeViewHierarchyOptimizer.handleUpdateLayout(this);
        return layoutHasChanged;
    }

    public final int getReactTag() {
        return this.mReactTag;
    }

    public void setReactTag(int reactTag) {
        this.mReactTag = reactTag;
    }

    public final ReactShadowNode getRootNode() {
        return (ReactShadowNode) Assertions.assertNotNull(this.mRootNode);
    }

    /* access modifiers changed from: 0000 */
    public final void setRootNode(ReactShadowNode rootNode) {
        this.mRootNode = rootNode;
    }

    /* access modifiers changed from: 0000 */
    public final void setViewClassName(String viewClassName) {
        this.mViewClassName = viewClassName;
    }

    public final ReactShadowNode getParent() {
        return this.mParent;
    }

    public final ThemedReactContext getThemedContext() {
        return (ThemedReactContext) Assertions.assertNotNull(this.mThemedContext);
    }

    public void setThemedContext(ThemedReactContext themedContext) {
        this.mThemedContext = themedContext;
    }

    public final boolean shouldNotifyOnLayout() {
        return this.mShouldNotifyOnLayout;
    }

    public void calculateLayout() {
        this.mYogaNode.calculateLayout();
    }

    public final boolean hasNewLayout() {
        if (this.mYogaNode == null) {
            return false;
        }
        return this.mYogaNode.hasNewLayout();
    }

    public final void markLayoutSeen() {
        if (this.mYogaNode != null) {
            this.mYogaNode.markLayoutSeen();
        }
    }

    public final void addNativeChildAt(ReactShadowNode child, int nativeIndex) {
        boolean z = true;
        Assertions.assertCondition(!this.mIsLayoutOnly);
        if (child.mIsLayoutOnly) {
            z = false;
        }
        Assertions.assertCondition(z);
        if (this.mNativeChildren == null) {
            this.mNativeChildren = new ArrayList<>(4);
        }
        this.mNativeChildren.add(nativeIndex, child);
        child.mNativeParent = this;
    }

    public final ReactShadowNode removeNativeChildAt(int i) {
        Assertions.assertNotNull(this.mNativeChildren);
        ReactShadowNode removed = (ReactShadowNode) this.mNativeChildren.remove(i);
        removed.mNativeParent = null;
        return removed;
    }

    public final void removeAllNativeChildren() {
        if (this.mNativeChildren != null) {
            for (int i = this.mNativeChildren.size() - 1; i >= 0; i--) {
                ((ReactShadowNode) this.mNativeChildren.get(i)).mNativeParent = null;
            }
            this.mNativeChildren.clear();
        }
    }

    public final int getNativeChildCount() {
        if (this.mNativeChildren == null) {
            return 0;
        }
        return this.mNativeChildren.size();
    }

    public final int indexOfNativeChild(ReactShadowNode nativeChild) {
        Assertions.assertNotNull(this.mNativeChildren);
        return this.mNativeChildren.indexOf(nativeChild);
    }

    public final ReactShadowNode getNativeParent() {
        return this.mNativeParent;
    }

    public final void setIsLayoutOnly(boolean isLayoutOnly) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (getParent() == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.assertCondition(z, "Must remove from no opt parent first");
        if (this.mNativeParent == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Assertions.assertCondition(z2, "Must remove from native parent first");
        if (getNativeChildCount() != 0) {
            z3 = false;
        }
        Assertions.assertCondition(z3, "Must remove all native children first");
        this.mIsLayoutOnly = isLayoutOnly;
    }

    public final boolean isLayoutOnly() {
        return this.mIsLayoutOnly;
    }

    public final int getTotalNativeChildren() {
        return this.mTotalNativeChildren;
    }

    public final int getNativeOffsetForChild(ReactShadowNode child) {
        int i;
        int index = 0;
        boolean found = false;
        int i2 = 0;
        while (true) {
            if (i2 >= getChildCount()) {
                break;
            }
            ReactShadowNode current = getChildAt(i2);
            if (child == current) {
                found = true;
                break;
            }
            if (current.mIsLayoutOnly) {
                i = current.getTotalNativeChildren();
            } else {
                i = 1;
            }
            index += i;
            i2++;
        }
        if (found) {
            return index;
        }
        throw new RuntimeException("Child " + child.mReactTag + " was not a child of " + this.mReactTag);
    }

    public final float getLayoutX() {
        return this.mYogaNode.getLayoutX();
    }

    public final float getLayoutY() {
        return this.mYogaNode.getLayoutY();
    }

    public final float getLayoutWidth() {
        return this.mYogaNode.getLayoutWidth();
    }

    public final float getLayoutHeight() {
        return this.mYogaNode.getLayoutHeight();
    }

    public int getScreenX() {
        return this.mScreenX;
    }

    public int getScreenY() {
        return this.mScreenY;
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public final YogaDirection getLayoutDirection() {
        return this.mYogaNode.getLayoutDirection();
    }

    public void setLayoutDirection(YogaDirection direction) {
        this.mYogaNode.setDirection(direction);
    }

    public final float getStyleWidth() {
        return this.mYogaNode.getWidth().value;
    }

    public void setStyleWidth(float widthPx) {
        this.mYogaNode.setWidth(widthPx);
    }

    public void setStyleMinWidth(float widthPx) {
        this.mYogaNode.setMinWidth(widthPx);
    }

    public void setStyleMaxWidth(float widthPx) {
        this.mYogaNode.setMaxWidth(widthPx);
    }

    public final float getStyleHeight() {
        return this.mYogaNode.getHeight().value;
    }

    public void setStyleHeight(float heightPx) {
        this.mYogaNode.setHeight(heightPx);
    }

    public void setStyleMinHeight(float widthPx) {
        this.mYogaNode.setMinHeight(widthPx);
    }

    public void setStyleMaxHeight(float widthPx) {
        this.mYogaNode.setMaxHeight(widthPx);
    }

    public void setFlex(float flex) {
        this.mYogaNode.setFlex(flex);
    }

    public void setFlexGrow(float flexGrow) {
        this.mYogaNode.setFlexGrow(flexGrow);
    }

    public void setFlexShrink(float flexShrink) {
        this.mYogaNode.setFlexShrink(flexShrink);
    }

    public void setFlexBasis(float flexBasis) {
        this.mYogaNode.setFlexBasis(flexBasis);
    }

    public void setStyleAspectRatio(float aspectRatio) {
        this.mYogaNode.setAspectRatio(aspectRatio);
    }

    public void setFlexDirection(YogaFlexDirection flexDirection) {
        this.mYogaNode.setFlexDirection(flexDirection);
    }

    public void setFlexWrap(YogaWrap wrap) {
        this.mYogaNode.setWrap(wrap);
    }

    public void setAlignSelf(YogaAlign alignSelf) {
        this.mYogaNode.setAlignSelf(alignSelf);
    }

    public void setAlignItems(YogaAlign alignItems) {
        this.mYogaNode.setAlignItems(alignItems);
    }

    public void setJustifyContent(YogaJustify justifyContent) {
        this.mYogaNode.setJustifyContent(justifyContent);
    }

    public void setOverflow(YogaOverflow overflow) {
        this.mYogaNode.setOverflow(overflow);
    }

    public void setMargin(int spacingType, float margin) {
        this.mYogaNode.setMargin(YogaEdge.fromInt(spacingType), margin);
    }

    public final float getPadding(int spacingType) {
        return this.mYogaNode.getPadding(YogaEdge.fromInt(spacingType)).value;
    }

    public void setDefaultPadding(int spacingType, float padding) {
        this.mDefaultPadding.set(spacingType, padding);
        updatePadding();
    }

    public void setPadding(int spacingType, float padding) {
        this.mPadding.set(spacingType, padding);
        updatePadding();
    }

    private void updatePadding() {
        for (int spacingType = 0; spacingType <= 8; spacingType++) {
            if (spacingType == 0 || spacingType == 2 || spacingType == 4 || spacingType == 5) {
                if (!YogaConstants.isUndefined(this.mPadding.getRaw(spacingType)) || !YogaConstants.isUndefined(this.mPadding.getRaw(6)) || !YogaConstants.isUndefined(this.mPadding.getRaw(8))) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding.getRaw(spacingType));
                } else {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
                }
            } else if (spacingType == 1 || spacingType == 3) {
                if (!YogaConstants.isUndefined(this.mPadding.getRaw(spacingType)) || !YogaConstants.isUndefined(this.mPadding.getRaw(7)) || !YogaConstants.isUndefined(this.mPadding.getRaw(8))) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding.getRaw(spacingType));
                } else {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
                }
            } else if (YogaConstants.isUndefined(this.mPadding.getRaw(spacingType))) {
                this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
            } else {
                this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding.getRaw(spacingType));
            }
        }
    }

    public void setBorder(int spacingType, float borderWidth) {
        this.mYogaNode.setBorder(YogaEdge.fromInt(spacingType), borderWidth);
    }

    public void setPosition(int spacingType, float position) {
        this.mYogaNode.setPosition(YogaEdge.fromInt(spacingType), position);
    }

    public void setPositionType(YogaPositionType positionType) {
        this.mYogaNode.setPositionType(positionType);
    }

    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        this.mShouldNotifyOnLayout = shouldNotifyOnLayout;
    }

    public void setMeasureFunction(YogaMeasureFunction measureFunction) {
        if (!((measureFunction == null) ^ this.mYogaNode.isMeasureDefined()) || getChildCount() == 0) {
            this.mYogaNode.setMeasureFunction(measureFunction);
            return;
        }
        throw new RuntimeException("Since a node with a measure function does not add any native yoga children, it's not safe to transition to/from having a measure function unless a node has no children");
    }

    public String toString() {
        return this.mYogaNode.toString();
    }

    public void dispose() {
        if (this.mYogaNode != null) {
            this.mYogaNode.reset();
            YogaNodePool.get().release(this.mYogaNode);
        }
    }
}
