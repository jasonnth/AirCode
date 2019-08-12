package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.animation.Animation;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import java.util.ArrayList;

abstract class ClippingDrawCommandManager extends DrawCommandManager {
    private final SparseArray<View> mClippedSubviews = new SparseArray<>();
    protected final Rect mClippingRect = new Rect();
    private final ArrayList<ReactClippingViewGroup> mClippingViewGroups = new ArrayList<>();
    protected float[] mCommandMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
    protected float[] mCommandMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
    private DrawCommand[] mDrawCommands = DrawCommand.EMPTY_ARRAY;
    private SparseIntArray mDrawViewIndexMap = StateBuilder.EMPTY_SPARSE_INT;
    private final FlatViewGroup mFlatViewGroup;
    private NodeRegion[] mNodeRegions = NodeRegion.EMPTY_ARRAY;
    protected float[] mRegionMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
    protected float[] mRegionMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
    private int mStart;
    private int mStop;
    private final ArrayList<View> mViewsToKeep = new ArrayList<>();
    private final SparseArray<View> mViewsToRemove = new SparseArray<>();

    /* access modifiers changed from: 0000 */
    public abstract int commandStartIndex();

    /* access modifiers changed from: 0000 */
    public abstract int commandStopIndex(int i);

    /* access modifiers changed from: 0000 */
    public abstract boolean regionAboveTouch(int i, float f, float f2);

    /* access modifiers changed from: 0000 */
    public abstract int regionStopIndex(float f, float f2);

    ClippingDrawCommandManager(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        this.mFlatViewGroup = flatViewGroup;
        initialSetup(drawCommands);
    }

    private void initialSetup(DrawCommand[] drawCommands) {
        mountDrawCommands(drawCommands, this.mDrawViewIndexMap, this.mCommandMaxBottom, this.mCommandMinTop, true);
        updateClippingRect();
    }

    public void mountDrawCommands(DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] maxBottom, float[] minTop, boolean willMountViews) {
        this.mDrawCommands = drawCommands;
        this.mCommandMaxBottom = maxBottom;
        this.mCommandMinTop = minTop;
        this.mDrawViewIndexMap = drawViewIndexMap;
        if (this.mClippingRect.bottom != this.mClippingRect.top) {
            this.mStart = commandStartIndex();
            this.mStop = commandStopIndex(this.mStart);
            if (!willMountViews) {
                updateClippingToCurrentRect();
            }
        }
    }

    public void mountNodeRegions(NodeRegion[] nodeRegions, float[] maxBottom, float[] minTop) {
        this.mNodeRegions = nodeRegions;
        this.mRegionMaxBottom = maxBottom;
        this.mRegionMinTop = minTop;
    }

    public NodeRegion virtualNodeRegionWithinBounds(float touchX, float touchY) {
        int i = regionStopIndex(touchX, touchY);
        while (true) {
            int i2 = i;
            i = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            NodeRegion nodeRegion = this.mNodeRegions[i];
            if (nodeRegion.mIsVirtual) {
                if (regionAboveTouch(i, touchX, touchY)) {
                    break;
                } else if (nodeRegion.withinBounds(touchX, touchY)) {
                    return nodeRegion;
                }
            }
        }
        return null;
    }

    public NodeRegion anyNodeRegionWithinBounds(float touchX, float touchY) {
        int i = regionStopIndex(touchX, touchY);
        while (true) {
            int i2 = i;
            i = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            NodeRegion nodeRegion = this.mNodeRegions[i];
            if (regionAboveTouch(i, touchX, touchY)) {
                break;
            } else if (nodeRegion.withinBounds(touchX, touchY)) {
                return nodeRegion;
            }
        }
        return null;
    }

    private void clip(int id, View view) {
        this.mClippedSubviews.put(id, view);
    }

    private void unclip(int id) {
        this.mClippedSubviews.remove(id);
    }

    private boolean isClipped(int id) {
        return this.mClippedSubviews.get(id) != null;
    }

    private boolean isNotClipped(int id) {
        return this.mClippedSubviews.get(id) == null;
    }

    /* access modifiers changed from: 0000 */
    public void onClippedViewDropped(View view) {
        unclip(view.getId());
        this.mFlatViewGroup.removeDetachedView(view);
    }

    public void mountViews(ViewResolver viewResolver, int[] viewsToAdd, int[] viewsToDetach) {
        boolean newView;
        this.mClippingViewGroups.clear();
        for (int viewToAdd : viewsToAdd) {
            if (viewToAdd > 0) {
                newView = true;
            } else {
                newView = false;
            }
            if (!newView) {
                viewToAdd = -viewToAdd;
            }
            int commandArrayIndex = this.mDrawViewIndexMap.get(viewToAdd);
            DrawView drawView = (DrawView) this.mDrawCommands[commandArrayIndex];
            View view = viewResolver.getView(drawView.reactTag);
            ensureViewHasNoParent(view);
            if ((view instanceof ReactClippingViewGroup) && ((ReactClippingViewGroup) view).getRemoveClippedSubviews()) {
                this.mClippingViewGroups.add((ReactClippingViewGroup) view);
            }
            if (newView) {
                drawView.mWasMounted = true;
                if (animating(view) || withinBounds(commandArrayIndex)) {
                    this.mFlatViewGroup.addViewInLayout(view);
                } else {
                    clip(drawView.reactTag, view);
                }
            } else if (!drawView.mWasMounted) {
                drawView.mWasMounted = true;
                if (animating(view) || withinBounds(commandArrayIndex)) {
                    if (isClipped(drawView.reactTag)) {
                        this.mFlatViewGroup.addViewInLayout(view);
                        unclip(drawView.reactTag);
                    } else {
                        this.mFlatViewGroup.attachViewToParent(view);
                    }
                } else if (isNotClipped(drawView.reactTag)) {
                    this.mFlatViewGroup.removeDetachedView(view);
                    clip(drawView.reactTag, view);
                }
            } else if (isNotClipped(drawView.reactTag)) {
                this.mFlatViewGroup.attachViewToParent(view);
            }
        }
        for (int viewToDetach : viewsToDetach) {
            View view2 = viewResolver.getView(viewToDetach);
            if (view2.getParent() != null) {
                throw new RuntimeException("Trying to remove view not owned by FlatViewGroup");
            }
            this.mFlatViewGroup.removeDetachedView(view2);
            unclip(viewToDetach);
        }
    }

    private static boolean animating(View view) {
        Animation animation = view.getAnimation();
        return animation != null && !animation.hasEnded();
    }

    private boolean withinBounds(int i) {
        return this.mStart <= i && i < this.mStop;
    }

    public boolean updateClippingRect() {
        ReactClippingViewGroupHelper.calculateClippingRect(this.mFlatViewGroup, this.mClippingRect);
        if (this.mFlatViewGroup.getParent() == null || this.mClippingRect.top == this.mClippingRect.bottom) {
            return false;
        }
        int start = commandStartIndex();
        int stop = commandStopIndex(start);
        if (this.mStart > start || stop > this.mStop) {
            this.mStart = start;
            this.mStop = stop;
            updateClippingToCurrentRect();
            updateClippingRecursively();
            return true;
        }
        updateClippingRecursively();
        return false;
    }

    private void updateClippingRecursively() {
        int children = this.mClippingViewGroups.size();
        for (int i = 0; i < children; i++) {
            ReactClippingViewGroup view = (ReactClippingViewGroup) this.mClippingViewGroups.get(i);
            if (isNotClipped(((View) view).getId())) {
                view.updateClippingRect();
            }
        }
    }

    private void updateClippingToCurrentRect() {
        int childIndex;
        int childIndex2;
        int size = this.mFlatViewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = this.mFlatViewGroup.getChildAt(i);
            if (withinBounds(this.mDrawViewIndexMap.get(view.getId())) || animating(view)) {
                this.mViewsToKeep.add(view);
            } else {
                this.mViewsToRemove.append(i, view);
                clip(view.getId(), view);
            }
        }
        int removeSize = this.mViewsToRemove.size();
        boolean removeAll = removeSize > 2;
        if (!removeAll) {
            while (true) {
                int removeSize2 = removeSize;
                removeSize = removeSize2 - 1;
                if (removeSize2 <= 0) {
                    break;
                }
                this.mFlatViewGroup.removeViewsInLayout(this.mViewsToRemove.keyAt(removeSize), 1);
            }
        } else {
            this.mFlatViewGroup.detachAllViewsFromParent();
            for (int i2 = 0; i2 < removeSize; i2++) {
                this.mFlatViewGroup.removeDetachedView((View) this.mViewsToRemove.valueAt(i2));
            }
        }
        this.mViewsToRemove.clear();
        int current = this.mStart;
        int childIndex3 = 0;
        int size2 = this.mViewsToKeep.size();
        for (int i3 = 0; i3 < size2; i3++) {
            View view2 = (View) this.mViewsToKeep.get(i3);
            int commandIndex = this.mDrawViewIndexMap.get(view2.getId());
            if (current <= commandIndex) {
                int childIndex4 = childIndex;
                while (current != commandIndex) {
                    if (this.mDrawCommands[current] instanceof DrawView) {
                        DrawView drawView = (DrawView) this.mDrawCommands[current];
                        childIndex2 = childIndex4 + 1;
                        this.mFlatViewGroup.addViewInLayout((View) Assertions.assumeNotNull(this.mClippedSubviews.get(drawView.reactTag)), childIndex4);
                        unclip(drawView.reactTag);
                    } else {
                        childIndex2 = childIndex4;
                    }
                    current++;
                    childIndex4 = childIndex2;
                }
                current++;
                childIndex = childIndex4;
            }
            if (removeAll) {
                this.mFlatViewGroup.attachViewToParent(view2, childIndex);
            }
            childIndex3 = childIndex + 1;
        }
        this.mViewsToKeep.clear();
        while (true) {
            int childIndex5 = childIndex;
            if (current < this.mStop) {
                if (this.mDrawCommands[current] instanceof DrawView) {
                    DrawView drawView2 = (DrawView) this.mDrawCommands[current];
                    childIndex = childIndex5 + 1;
                    this.mFlatViewGroup.addViewInLayout((View) Assertions.assumeNotNull(this.mClippedSubviews.get(drawView2.reactTag)), childIndex5);
                    unclip(drawView2.reactTag);
                } else {
                    childIndex = childIndex5;
                }
                current++;
            } else {
                return;
            }
        }
    }

    public void getClippingRect(Rect outClippingRect) {
        outClippingRect.set(this.mClippingRect);
    }

    public SparseArray<View> getDetachedViews() {
        return this.mClippedSubviews;
    }

    public void draw(Canvas canvas) {
        int commandIndex = this.mStart;
        int size = this.mFlatViewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            int viewIndex = this.mDrawViewIndexMap.get(this.mFlatViewGroup.getChildAt(i).getId());
            if (this.mStop < viewIndex) {
                while (commandIndex < this.mStop) {
                    int commandIndex2 = commandIndex + 1;
                    this.mDrawCommands[commandIndex].draw(this.mFlatViewGroup, canvas);
                    commandIndex = commandIndex2;
                }
            } else if (commandIndex <= viewIndex) {
                int commandIndex3 = commandIndex;
                while (commandIndex3 < viewIndex) {
                    int commandIndex4 = commandIndex3 + 1;
                    this.mDrawCommands[commandIndex3].draw(this.mFlatViewGroup, canvas);
                    commandIndex3 = commandIndex4;
                }
                commandIndex = commandIndex3 + 1;
            }
            this.mDrawCommands[viewIndex].draw(this.mFlatViewGroup, canvas);
        }
        while (commandIndex < this.mStop) {
            int commandIndex5 = commandIndex + 1;
            this.mDrawCommands[commandIndex].draw(this.mFlatViewGroup, canvas);
            commandIndex = commandIndex5;
        }
    }

    /* access modifiers changed from: 0000 */
    public void debugDraw(Canvas canvas) {
        DrawCommand[] drawCommandArr;
        for (DrawCommand drawCommand : this.mDrawCommands) {
            if (!(drawCommand instanceof DrawView)) {
                drawCommand.debugDraw(this.mFlatViewGroup, canvas);
            } else if (isNotClipped(((DrawView) drawCommand).reactTag)) {
                drawCommand.debugDraw(this.mFlatViewGroup, canvas);
            }
        }
    }
}
