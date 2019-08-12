package com.facebook.react.flat;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import java.util.ArrayList;
import java.util.List;

final class FlatNativeViewHierarchyManager extends NativeViewHierarchyManager implements ViewResolver {
    FlatNativeViewHierarchyManager(ViewManagerRegistry viewManagers) {
        super(viewManagers, new FlatRootViewManager());
    }

    public View getView(int reactTag) {
        return super.resolveView(reactTag);
    }

    public void addRootView(int tag, SizeMonitoringFrameLayout view, ThemedReactContext themedContext) {
        FlatViewGroup root = new FlatViewGroup(themedContext);
        view.addView(root);
        view.setId(tag);
        addRootViewGroup(tag, root, themedContext);
    }

    /* access modifiers changed from: 0000 */
    public void updateMountState(int reactTag, DrawCommand[] drawCommands, AttachDetachListener[] listeners, NodeRegion[] nodeRegions) {
        FlatViewGroup view = (FlatViewGroup) resolveView(reactTag);
        if (drawCommands != null) {
            view.mountDrawCommands(drawCommands);
        }
        if (listeners != null) {
            view.mountAttachDetachListeners(listeners);
        }
        if (nodeRegions != null) {
            view.mountNodeRegions(nodeRegions);
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateClippingMountState(int reactTag, DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] commandMaxBot, float[] commandMinTop, AttachDetachListener[] listeners, NodeRegion[] nodeRegions, float[] regionMaxBot, float[] regionMinTop, boolean willMountViews) {
        FlatViewGroup view = (FlatViewGroup) resolveView(reactTag);
        if (drawCommands != null) {
            view.mountClippingDrawCommands(drawCommands, drawViewIndexMap, commandMaxBot, commandMinTop, willMountViews);
        }
        if (listeners != null) {
            view.mountAttachDetachListeners(listeners);
        }
        if (nodeRegions != null) {
            view.mountClippingNodeRegions(nodeRegions, regionMaxBot, regionMinTop);
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateViewGroup(int reactTag, int[] viewsToAdd, int[] viewsToDetach) {
        View view = resolveView(reactTag);
        if (view instanceof FlatViewGroup) {
            ((FlatViewGroup) view).mountViews(this, viewsToAdd, viewsToDetach);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        ViewGroupManager viewManager = (ViewGroupManager) resolveViewManager(reactTag);
        List<View> listOfViews = new ArrayList<>(viewsToAdd.length);
        for (int viewIdToAdd : viewsToAdd) {
            listOfViews.add(resolveView(Math.abs(viewIdToAdd)));
        }
        viewManager.addViews(viewGroup, listOfViews);
    }

    /* access modifiers changed from: 0000 */
    public void updateViewBounds(int reactTag, int left, int top, int right, int bottom) {
        View view = resolveView(reactTag);
        int width = right - left;
        int height = bottom - top;
        if (view.getWidth() == width && view.getHeight() == height) {
            view.offsetLeftAndRight(left - view.getLeft());
            view.offsetTopAndBottom(top - view.getTop());
            return;
        }
        view.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
        view.layout(left, top, right, bottom);
    }

    /* access modifiers changed from: 0000 */
    public void setPadding(int reactTag, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        resolveView(reactTag).setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /* access modifiers changed from: 0000 */
    public void dropViews(SparseIntArray viewsToDrop) {
        int count = viewsToDrop.size();
        for (int i = 0; i < count; i++) {
            int viewToDrop = viewsToDrop.keyAt(i);
            View view = null;
            if (viewToDrop > 0) {
                try {
                    view = resolveView(viewToDrop);
                    dropView(view);
                } catch (Exception e) {
                }
            } else {
                removeRootView(-viewToDrop);
            }
            int parentTag = viewsToDrop.valueAt(i);
            if (parentTag > 0 && view != null && view.getParent() == null) {
                View parent = resolveView(parentTag);
                if (parent instanceof FlatViewGroup) {
                    ((FlatViewGroup) parent).onViewDropped(view);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dropView(View view) {
        super.dropView(view);
        if (view instanceof FlatViewGroup) {
            FlatViewGroup flatViewGroup = (FlatViewGroup) view;
            if (flatViewGroup.getRemoveClippedSubviews()) {
                SparseArray<View> detachedViews = flatViewGroup.getDetachedViews();
                int size = detachedViews.size();
                for (int i = 0; i < size; i++) {
                    View detachedChild = (View) detachedViews.valueAt(i);
                    try {
                        dropView(detachedChild);
                    } catch (Exception e) {
                    }
                    flatViewGroup.removeDetachedView(detachedChild);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void detachAllChildrenFromViews(int[] viewsToDetachAllChildrenFrom) {
        for (int viewTag : viewsToDetachAllChildrenFrom) {
            View view = resolveView(viewTag);
            if (view instanceof FlatViewGroup) {
                ((FlatViewGroup) view).detachAllViewsFromParent();
            } else {
                ((ViewGroupManager) resolveViewManager(viewTag)).removeAllViews((ViewGroup) view);
            }
        }
    }
}
