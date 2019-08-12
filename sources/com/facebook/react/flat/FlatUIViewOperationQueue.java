package com.facebook.react.flat;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.NoSuchNativeViewException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import java.util.ArrayList;

final class FlatUIViewOperationQueue extends UIViewOperationQueue {
    /* access modifiers changed from: private */
    public static final int[] MEASURE_BUFFER = new int[4];
    /* access modifiers changed from: private */
    public final FlatNativeViewHierarchyManager mNativeViewHierarchyManager;
    private final ProcessLayoutRequests mProcessLayoutRequests = new ProcessLayoutRequests();

    public final class DetachAllChildrenFromViews implements UIOperation {
        private int[] mViewsToDetachAllChildrenFrom;

        public DetachAllChildrenFromViews() {
        }

        public void setViewsToDetachAllChildrenFrom(int[] viewsToDetachAllChildrenFrom) {
            this.mViewsToDetachAllChildrenFrom = viewsToDetachAllChildrenFrom;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.detachAllChildrenFromViews(this.mViewsToDetachAllChildrenFrom);
        }
    }

    private final class DropViews implements UIOperation {
        private final SparseIntArray mViewsToDrop;

        private DropViews(ArrayList<Integer> viewsToDrop, ArrayList<Integer> parentsForViewsToDrop) {
            SparseIntArray sparseIntArray = new SparseIntArray();
            int count = viewsToDrop.size();
            for (int i = 0; i < count; i++) {
                sparseIntArray.put(((Integer) viewsToDrop.get(i)).intValue(), ((Integer) parentsForViewsToDrop.get(i)).intValue());
            }
            this.mViewsToDrop = sparseIntArray;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.dropViews(this.mViewsToDrop);
        }
    }

    private final class FindTargetForTouchOperation implements UIOperation {
        private final int[] NATIVE_VIEW_BUFFER;
        private final Callback mCallback;
        private final int mReactTag;
        private final float mTargetX;
        private final float mTargetY;

        private FindTargetForTouchOperation(int reactTag, float targetX, float targetY, Callback callback) {
            this.NATIVE_VIEW_BUFFER = new int[1];
            this.mReactTag = reactTag;
            this.mTargetX = targetX;
            this.mTargetY = targetY;
            this.mCallback = callback;
        }

        public void execute() {
            try {
                FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                float containerX = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[0];
                float containerY = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[1];
                int touchTargetReactTag = TouchTargetHelper.findTargetTagForTouch(this.mTargetX, this.mTargetY, (ViewGroup) FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.getView(this.mReactTag), this.NATIVE_VIEW_BUFFER);
                try {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.NATIVE_VIEW_BUFFER[0], FlatUIViewOperationQueue.MEASURE_BUFFER);
                    NodeRegion region = NodeRegion.EMPTY;
                    boolean isNativeView = this.NATIVE_VIEW_BUFFER[0] == touchTargetReactTag;
                    if (!isNativeView) {
                        View view = FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.getView(this.NATIVE_VIEW_BUFFER[0]);
                        if (view instanceof FlatViewGroup) {
                            region = ((FlatViewGroup) view).getNodeRegionForTag(this.mReactTag);
                        }
                    }
                    this.mCallback.invoke(Integer.valueOf(region == NodeRegion.EMPTY ? touchTargetReactTag : region.mTag), Float.valueOf(PixelUtil.toDIPFromPixel((region.getLeft() + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[0])) - containerX)), Float.valueOf(PixelUtil.toDIPFromPixel((region.getTop() + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[1])) - containerY)), Float.valueOf(PixelUtil.toDIPFromPixel(isNativeView ? (float) FlatUIViewOperationQueue.MEASURE_BUFFER[2] : region.getRight() - region.getLeft())), Float.valueOf(PixelUtil.toDIPFromPixel(isNativeView ? (float) FlatUIViewOperationQueue.MEASURE_BUFFER[3] : region.getBottom() - region.getTop())));
                } catch (IllegalViewOperationException e) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException e2) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    private final class MeasureVirtualView implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;
        private final boolean mRelativeToWindow;
        private final float mScaledHeight;
        private final float mScaledWidth;
        private final float mScaledX;
        private final float mScaledY;

        private MeasureVirtualView(int reactTag, float scaledX, float scaledY, float scaledWidth, float scaledHeight, boolean relativeToWindow, Callback callback) {
            this.mReactTag = reactTag;
            this.mScaledX = scaledX;
            this.mScaledY = scaledY;
            this.mScaledWidth = scaledWidth;
            this.mScaledHeight = scaledHeight;
            this.mCallback = callback;
            this.mRelativeToWindow = relativeToWindow;
        }

        public void execute() {
            try {
                if (this.mRelativeToWindow) {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                } else {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                }
                float nativeViewY = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[1];
                float nativeViewWidth = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[2];
                float nativeViewHeight = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[3];
                float x = PixelUtil.toDIPFromPixel((this.mScaledX * nativeViewWidth) + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[0]));
                float y = PixelUtil.toDIPFromPixel((this.mScaledY * nativeViewHeight) + nativeViewY);
                float width = PixelUtil.toDIPFromPixel(this.mScaledWidth * nativeViewWidth);
                float height = PixelUtil.toDIPFromPixel(this.mScaledHeight * nativeViewHeight);
                if (this.mRelativeToWindow) {
                    this.mCallback.invoke(Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
                    return;
                }
                this.mCallback.invoke(Integer.valueOf(0), Integer.valueOf(0), Float.valueOf(width), Float.valueOf(height), Float.valueOf(x), Float.valueOf(y));
            } catch (NoSuchNativeViewException e) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    private final class ProcessLayoutRequests implements UIOperation {
        private ProcessLayoutRequests() {
        }

        public void execute() {
            FlatViewGroup.processLayoutRequests();
        }
    }

    private final class SetPadding implements UIOperation {
        private final int mPaddingBottom;
        private final int mPaddingLeft;
        private final int mPaddingRight;
        private final int mPaddingTop;
        private final int mReactTag;

        private SetPadding(int reactTag, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
            this.mReactTag = reactTag;
            this.mPaddingLeft = paddingLeft;
            this.mPaddingTop = paddingTop;
            this.mPaddingRight = paddingRight;
            this.mPaddingBottom = paddingBottom;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.setPadding(this.mReactTag, this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
        }
    }

    private final class UpdateClippingMountState implements UIOperation {
        private final AttachDetachListener[] mAttachDetachListeners;
        private final float[] mCommandMaxBot;
        private final float[] mCommandMinTop;
        private final DrawCommand[] mDrawCommands;
        private final SparseIntArray mDrawViewIndexMap;
        private final NodeRegion[] mNodeRegions;
        private final int mReactTag;
        private final float[] mRegionMaxBot;
        private final float[] mRegionMinTop;
        private final boolean mWillMountViews;

        private UpdateClippingMountState(int reactTag, DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] commandMaxBot, float[] commandMinTop, AttachDetachListener[] listeners, NodeRegion[] nodeRegions, float[] regionMaxBot, float[] regionMinTop, boolean willMountViews) {
            this.mReactTag = reactTag;
            this.mDrawCommands = drawCommands;
            this.mDrawViewIndexMap = drawViewIndexMap;
            this.mCommandMaxBot = commandMaxBot;
            this.mCommandMinTop = commandMinTop;
            this.mAttachDetachListeners = listeners;
            this.mNodeRegions = nodeRegions;
            this.mRegionMaxBot = regionMaxBot;
            this.mRegionMinTop = regionMinTop;
            this.mWillMountViews = willMountViews;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateClippingMountState(this.mReactTag, this.mDrawCommands, this.mDrawViewIndexMap, this.mCommandMaxBot, this.mCommandMinTop, this.mAttachDetachListeners, this.mNodeRegions, this.mRegionMaxBot, this.mRegionMinTop, this.mWillMountViews);
        }
    }

    private final class UpdateMountState implements UIOperation {
        private final AttachDetachListener[] mAttachDetachListeners;
        private final DrawCommand[] mDrawCommands;
        private final NodeRegion[] mNodeRegions;
        private final int mReactTag;

        private UpdateMountState(int reactTag, DrawCommand[] drawCommands, AttachDetachListener[] listeners, NodeRegion[] nodeRegions) {
            this.mReactTag = reactTag;
            this.mDrawCommands = drawCommands;
            this.mAttachDetachListeners = listeners;
            this.mNodeRegions = nodeRegions;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateMountState(this.mReactTag, this.mDrawCommands, this.mAttachDetachListeners, this.mNodeRegions);
        }
    }

    public final class UpdateViewBounds implements UIOperation {
        private final int mBottom;
        private final int mLeft;
        private final int mReactTag;
        private final int mRight;
        private final int mTop;

        private UpdateViewBounds(int reactTag, int left, int top, int right, int bottom) {
            this.mReactTag = reactTag;
            this.mLeft = left;
            this.mTop = top;
            this.mRight = right;
            this.mBottom = bottom;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewBounds(this.mReactTag, this.mLeft, this.mTop, this.mRight, this.mBottom);
        }
    }

    private final class UpdateViewGroup implements UIOperation {
        private final int mReactTag;
        private final int[] mViewsToAdd;
        private final int[] mViewsToDetach;

        private UpdateViewGroup(int reactTag, int[] viewsToAdd, int[] viewsToDetach) {
            this.mReactTag = reactTag;
            this.mViewsToAdd = viewsToAdd;
            this.mViewsToDetach = viewsToDetach;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewGroup(this.mReactTag, this.mViewsToAdd, this.mViewsToDetach);
        }
    }

    public final class ViewManagerCommand implements UIOperation {
        private final ReadableArray mArgs;
        private final int mCommand;
        private final int mReactTag;

        public ViewManagerCommand(int reactTag, int command, ReadableArray args) {
            this.mReactTag = reactTag;
            this.mCommand = command;
            this.mArgs = args;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mReactTag, this.mCommand, this.mArgs);
        }
    }

    public FlatUIViewOperationQueue(ReactApplicationContext reactContext, FlatNativeViewHierarchyManager nativeViewHierarchyManager) {
        super(reactContext, nativeViewHierarchyManager);
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
    }

    public void enqueueUpdateMountState(int reactTag, DrawCommand[] drawCommands, AttachDetachListener[] listeners, NodeRegion[] nodeRegions) {
        enqueueUIOperation(new UpdateMountState(reactTag, drawCommands, listeners, nodeRegions));
    }

    public void enqueueUpdateClippingMountState(int reactTag, DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] commandMaxBot, float[] commandMinTop, AttachDetachListener[] listeners, NodeRegion[] nodeRegions, float[] regionMaxBot, float[] regionMinTop, boolean willMountViews) {
        enqueueUIOperation(new UpdateClippingMountState(reactTag, drawCommands, drawViewIndexMap, commandMaxBot, commandMinTop, listeners, nodeRegions, regionMaxBot, regionMinTop, willMountViews));
    }

    public void enqueueUpdateViewGroup(int reactTag, int[] viewsToAdd, int[] viewsToDetach) {
        enqueueUIOperation(new UpdateViewGroup(reactTag, viewsToAdd, viewsToDetach));
    }

    public UpdateViewBounds createUpdateViewBounds(int reactTag, int left, int top, int right, int bottom) {
        return new UpdateViewBounds(reactTag, left, top, right, bottom);
    }

    public ViewManagerCommand createViewManagerCommand(int reactTag, int command, ReadableArray args) {
        return new ViewManagerCommand(reactTag, command, args);
    }

    /* access modifiers changed from: 0000 */
    public void enqueueFlatUIOperation(UIOperation operation) {
        enqueueUIOperation(operation);
    }

    public void enqueueSetPadding(int reactTag, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        enqueueUIOperation(new SetPadding(reactTag, paddingLeft, paddingTop, paddingRight, paddingBottom));
    }

    public void enqueueDropViews(ArrayList<Integer> viewsToDrop, ArrayList<Integer> parentsOfViewsToDrop) {
        enqueueUIOperation(new DropViews(viewsToDrop, parentsOfViewsToDrop));
    }

    public void enqueueMeasureVirtualView(int reactTag, float scaledX, float scaledY, float scaledWidth, float scaledHeight, boolean relativeToWindow, Callback callback) {
        enqueueUIOperation(new MeasureVirtualView(reactTag, scaledX, scaledY, scaledWidth, scaledHeight, relativeToWindow, callback));
    }

    public void enqueueProcessLayoutRequests() {
        enqueueUIOperation(this.mProcessLayoutRequests);
    }

    public DetachAllChildrenFromViews enqueueDetachAllChildrenFromViews() {
        DetachAllChildrenFromViews op = new DetachAllChildrenFromViews();
        enqueueUIOperation(op);
        return op;
    }

    public void enqueueFindTargetForTouch(int reactTag, float targetX, float targetY, Callback callback) {
        enqueueUIOperation(new FindTargetForTouchOperation(reactTag, targetX, targetY, callback));
    }
}
