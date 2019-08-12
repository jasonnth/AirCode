package com.facebook.react.flat;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.yoga.YogaDirection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlatUIImplementation extends UIImplementation {
    private final MoveProxy mMoveProxy = new MoveProxy();
    private final ReactApplicationContext mReactContext;
    private ReactImageManager mReactImageManager;
    private final StateBuilder mStateBuilder;

    public static FlatUIImplementation createInstance(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        ReactImageManager reactImageManager = findReactImageManager(viewManagers);
        if (reactImageManager != null) {
            Object callerContext = reactImageManager.getCallerContext();
            if (callerContext != null) {
                RCTImageView.setCallerContext(callerContext);
            }
        }
        DraweeRequestHelper.setResources(reactContext.getResources());
        TypefaceCache.setAssetManager(reactContext.getAssets());
        List<ViewManager> viewManagers2 = new ArrayList<>(viewManagers);
        viewManagers2.add(new RCTViewManager());
        viewManagers2.add(new RCTTextManager());
        viewManagers2.add(new RCTRawTextManager());
        viewManagers2.add(new RCTVirtualTextManager());
        viewManagers2.add(new RCTTextInlineImageManager());
        viewManagers2.add(new RCTImageViewManager());
        viewManagers2.add(new RCTTextInputManager());
        viewManagers2.add(new RCTViewPagerManager());
        viewManagers2.add(new FlatARTSurfaceViewManager());
        viewManagers2.add(new RCTModalHostManager());
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(viewManagers2);
        return new FlatUIImplementation(reactContext, reactImageManager, viewManagerRegistry, new FlatUIViewOperationQueue(reactContext, new FlatNativeViewHierarchyManager(viewManagerRegistry)), eventDispatcher);
    }

    private FlatUIImplementation(ReactApplicationContext reactContext, ReactImageManager reactImageManager, ViewManagerRegistry viewManagers, FlatUIViewOperationQueue operationsQueue, EventDispatcher eventDispatcher) {
        super(reactContext, viewManagers, operationsQueue, eventDispatcher);
        this.mReactContext = reactContext;
        this.mStateBuilder = new StateBuilder(operationsQueue);
        this.mReactImageManager = reactImageManager;
    }

    /* access modifiers changed from: protected */
    public ReactShadowNode createRootShadowNode() {
        if (this.mReactImageManager != null) {
            DraweeRequestHelper.setDraweeControllerBuilder(this.mReactImageManager.getDraweeControllerBuilder());
            this.mReactImageManager = null;
        }
        ReactShadowNode node = new FlatRootShadowNode();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            node.setLayoutDirection(YogaDirection.RTL);
        }
        return node;
    }

    /* access modifiers changed from: protected */
    public ReactShadowNode createShadowNode(String className) {
        ReactShadowNode cssNode = super.createShadowNode(className);
        return ((cssNode instanceof FlatShadowNode) || cssNode.isVirtual()) ? cssNode : new NativeViewWrapper(resolveViewManager(className));
    }

    /* access modifiers changed from: protected */
    public void handleCreateView(ReactShadowNode cssNode, int rootViewTag, ReactStylesDiffMap styles) {
        if (cssNode instanceof FlatShadowNode) {
            FlatShadowNode node = (FlatShadowNode) cssNode;
            if (styles != null) {
                node.handleUpdateProperties(styles);
            }
            if (node.mountsToView()) {
                this.mStateBuilder.enqueueCreateOrUpdateView(node, styles);
                return;
            }
            return;
        }
        super.handleCreateView(cssNode, rootViewTag, styles);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateView(ReactShadowNode cssNode, String className, ReactStylesDiffMap styles) {
        if (cssNode instanceof FlatShadowNode) {
            FlatShadowNode node = (FlatShadowNode) cssNode;
            node.handleUpdateProperties(styles);
            if (node.mountsToView()) {
                this.mStateBuilder.enqueueCreateOrUpdateView(node, styles);
                return;
            }
            return;
        }
        super.handleUpdateView(cssNode, className, styles);
    }

    public void manageChildren(int viewTag, ReadableArray moveFrom, ReadableArray moveTo, ReadableArray addChildTags, ReadableArray addAtIndices, ReadableArray removeFrom) {
        ReactShadowNode parentNode = resolveShadowNode(viewTag);
        removeChildren(parentNode, moveFrom, moveTo, removeFrom);
        addChildren(parentNode, addChildTags, addAtIndices);
    }

    public void setChildren(int viewTag, ReadableArray children) {
        ReactShadowNode parentNode = resolveShadowNode(viewTag);
        for (int i = 0; i < children.size(); i++) {
            addChildAt(parentNode, resolveShadowNode(children.getInt(i)), i, i - 1);
        }
    }

    public void measure(int reactTag, Callback callback) {
        measureHelper(reactTag, false, callback);
    }

    private void measureHelper(int reactTag, boolean relativeToWindow, Callback callback) {
        FlatShadowNode node = (FlatShadowNode) resolveShadowNode(reactTag);
        if (node.mountsToView()) {
            this.mStateBuilder.ensureBackingViewIsCreated(node);
            if (relativeToWindow) {
                super.measureInWindow(reactTag, callback);
            } else {
                super.measure(reactTag, callback);
            }
        } else {
            while (node != null && node.isVirtual()) {
                node = (FlatShadowNode) node.getParent();
            }
            if (node != null) {
                float width = node.getLayoutWidth();
                float height = node.getLayoutHeight();
                boolean nodeMountsToView = node.mountsToView();
                float xInParent = nodeMountsToView ? node.getLayoutX() : 0.0f;
                float yInParent = nodeMountsToView ? node.getLayoutY() : 0.0f;
                while (!node.mountsToView()) {
                    if (!node.isVirtual()) {
                        xInParent += node.getLayoutX();
                        yInParent += node.getLayoutY();
                    }
                    node = (FlatShadowNode) Assertions.assumeNotNull((FlatShadowNode) node.getParent());
                }
                float parentWidth = node.getLayoutWidth();
                float parentHeight = node.getLayoutHeight();
                this.mStateBuilder.getOperationsQueue().enqueueMeasureVirtualView(node.getReactTag(), xInParent / parentWidth, yInParent / parentHeight, width / parentWidth, height / parentHeight, relativeToWindow, callback);
            }
        }
    }

    private void ensureMountsToViewAndBackingViewIsCreated(int reactTag) {
        FlatShadowNode node = (FlatShadowNode) resolveShadowNode(reactTag);
        if (!node.isBackingViewCreated()) {
            node.forceMountToView();
            this.mStateBuilder.ensureBackingViewIsCreated(node);
        }
    }

    public void findSubviewIn(int reactTag, float targetX, float targetY, Callback callback) {
        ensureMountsToViewAndBackingViewIsCreated(reactTag);
        super.findSubviewIn(reactTag, targetX, targetY, callback);
    }

    public void measureInWindow(int reactTag, Callback callback) {
        measureHelper(reactTag, true, callback);
    }

    public void addAnimation(int reactTag, int animationID, Callback onSuccess) {
        ensureMountsToViewAndBackingViewIsCreated(reactTag);
        super.addAnimation(reactTag, animationID, onSuccess);
    }

    public void dispatchViewManagerCommand(int reactTag, int commandId, ReadableArray commandArgs) {
        ensureMountsToViewAndBackingViewIsCreated(reactTag);
        this.mStateBuilder.enqueueViewManagerCommand(reactTag, commandId, commandArgs);
    }

    public void showPopupMenu(int reactTag, ReadableArray items, Callback error, Callback success) {
        ensureMountsToViewAndBackingViewIsCreated(reactTag);
        super.showPopupMenu(reactTag, items, error, success);
    }

    public void sendAccessibilityEvent(int reactTag, int eventType) {
        ensureMountsToViewAndBackingViewIsCreated(reactTag);
        super.sendAccessibilityEvent(reactTag, eventType);
    }

    private void removeChildren(ReactShadowNode parentNode, ReadableArray moveFrom, ReadableArray moveTo, ReadableArray removeFrom) {
        int removeFromIndex;
        int removeFromChildIndex;
        int prevIndex = Integer.MAX_VALUE;
        this.mMoveProxy.setup(moveFrom, moveTo);
        int moveFromIndex = this.mMoveProxy.size() - 1;
        int moveFromChildIndex = moveFromIndex == -1 ? -1 : this.mMoveProxy.getMoveFrom(moveFromIndex);
        int numToRemove = removeFrom == null ? 0 : removeFrom.size();
        int[] indicesToRemove = new int[numToRemove];
        if (numToRemove > 0) {
            Assertions.assertNotNull(removeFrom);
            for (int i = 0; i < numToRemove; i++) {
                indicesToRemove[i] = removeFrom.getInt(i);
            }
        }
        Arrays.sort(indicesToRemove);
        if (removeFrom == null) {
            removeFromIndex = -1;
            removeFromChildIndex = -1;
        } else {
            removeFromIndex = indicesToRemove.length - 1;
            removeFromChildIndex = indicesToRemove[removeFromIndex];
        }
        while (true) {
            if (moveFromChildIndex > removeFromChildIndex) {
                moveChild(removeChildAt(parentNode, moveFromChildIndex, prevIndex), moveFromIndex);
                prevIndex = moveFromChildIndex;
                moveFromIndex--;
                if (moveFromIndex == -1) {
                    moveFromChildIndex = -1;
                } else {
                    moveFromChildIndex = this.mMoveProxy.getMoveFrom(moveFromIndex);
                }
            } else if (removeFromChildIndex > moveFromChildIndex) {
                removeChild(removeChildAt(parentNode, removeFromChildIndex, prevIndex), parentNode);
                prevIndex = removeFromChildIndex;
                removeFromIndex--;
                removeFromChildIndex = removeFromIndex == -1 ? -1 : indicesToRemove[removeFromIndex];
            } else {
                return;
            }
        }
    }

    private void removeChild(ReactShadowNode child, ReactShadowNode parentNode) {
        dropNativeViews(child, parentNode);
        removeShadowNode(child);
    }

    private void dropNativeViews(ReactShadowNode child, ReactShadowNode parentNode) {
        if (child instanceof FlatShadowNode) {
            FlatShadowNode node = (FlatShadowNode) child;
            if (node.mountsToView() && node.isBackingViewCreated()) {
                int tag = -1;
                ReactShadowNode tmpNode = parentNode;
                while (true) {
                    if (tmpNode == null) {
                        break;
                    }
                    if (tmpNode instanceof FlatShadowNode) {
                        FlatShadowNode flatTmpNode = (FlatShadowNode) tmpNode;
                        if (flatTmpNode.mountsToView() && flatTmpNode.isBackingViewCreated() && flatTmpNode.getParent() != null) {
                            tag = flatTmpNode.getReactTag();
                            break;
                        }
                    }
                    tmpNode = tmpNode.getParent();
                }
                this.mStateBuilder.dropView(node, tag);
                return;
            }
        }
        int childCount = child.getChildCount();
        for (int i = 0; i != childCount; i++) {
            dropNativeViews(child.getChildAt(i), child);
        }
    }

    private void moveChild(ReactShadowNode child, int moveFromIndex) {
        this.mMoveProxy.setChildMoveFrom(moveFromIndex, child);
    }

    private void addChildren(ReactShadowNode parentNode, ReadableArray addChildTags, ReadableArray addAtIndices) {
        int moveToIndex;
        int moveToChildIndex;
        int numNodesToAdd;
        int addToIndex;
        int addToChildIndex;
        int prevIndex = -1;
        if (this.mMoveProxy.size() == 0) {
            moveToIndex = Integer.MAX_VALUE;
            moveToChildIndex = Integer.MAX_VALUE;
        } else {
            moveToIndex = 0;
            moveToChildIndex = this.mMoveProxy.getMoveTo(0);
        }
        if (addAtIndices == null) {
            numNodesToAdd = 0;
            addToIndex = Integer.MAX_VALUE;
            addToChildIndex = Integer.MAX_VALUE;
        } else {
            numNodesToAdd = addAtIndices.size();
            addToIndex = 0;
            addToChildIndex = addAtIndices.getInt(0);
        }
        while (true) {
            if (addToChildIndex < moveToChildIndex) {
                addChildAt(parentNode, resolveShadowNode(addChildTags.getInt(addToIndex)), addToChildIndex, prevIndex);
                prevIndex = addToChildIndex;
                addToIndex++;
                if (addToIndex == numNodesToAdd) {
                    addToChildIndex = Integer.MAX_VALUE;
                } else {
                    addToChildIndex = addAtIndices.getInt(addToIndex);
                }
            } else if (moveToChildIndex < addToChildIndex) {
                addChildAt(parentNode, this.mMoveProxy.getChildMoveTo(moveToIndex), moveToChildIndex, prevIndex);
                prevIndex = moveToChildIndex;
                moveToIndex++;
                if (moveToIndex == this.mMoveProxy.size()) {
                    moveToChildIndex = Integer.MAX_VALUE;
                } else {
                    moveToChildIndex = this.mMoveProxy.getMoveTo(moveToIndex);
                }
            } else {
                return;
            }
        }
    }

    private static ReactShadowNode removeChildAt(ReactShadowNode parentNode, int index, int prevIndex) {
        if (index < prevIndex) {
            return parentNode.removeChildAt(index);
        }
        throw new RuntimeException("Invariant failure, needs sorting! " + index + " >= " + prevIndex);
    }

    private static void addChildAt(ReactShadowNode parentNode, ReactShadowNode childNode, int index, int prevIndex) {
        if (index <= prevIndex) {
            throw new RuntimeException("Invariant failure, needs sorting! " + index + " <= " + prevIndex);
        }
        parentNode.addChildAt(childNode, index);
    }

    /* access modifiers changed from: protected */
    public void updateViewHierarchy() {
        super.updateViewHierarchy();
        this.mStateBuilder.afterUpdateViewHierarchy(this.mEventDispatcher);
    }

    /* access modifiers changed from: protected */
    public void applyUpdatesRecursive(ReactShadowNode cssNode, float absoluteX, float absoluteY) {
        this.mStateBuilder.applyUpdates((FlatRootShadowNode) cssNode);
    }

    public void removeRootView(int rootViewTag) {
        this.mStateBuilder.removeRootView(rootViewTag);
    }

    public void setJSResponder(int possiblyVirtualReactTag, boolean blockNativeResponder) {
        ReactShadowNode node = resolveShadowNode(possiblyVirtualReactTag);
        while (node.isVirtual()) {
            node = node.getParent();
        }
        int tag = node.getReactTag();
        while ((node instanceof FlatShadowNode) && !((FlatShadowNode) node).mountsToView()) {
            node = node.getParent();
        }
        FlatUIViewOperationQueue operationsQueue = this.mStateBuilder.getOperationsQueue();
        if (node != null) {
            tag = node.getReactTag();
        }
        operationsQueue.enqueueSetJSResponder(tag, possiblyVirtualReactTag, blockNativeResponder);
    }

    private static ReactImageManager findReactImageManager(List<ViewManager> viewManagers) {
        int size = viewManagers.size();
        for (int i = 0; i != size; i++) {
            if (viewManagers.get(i) instanceof ReactImageManager) {
                return (ReactImageManager) viewManagers.get(i);
            }
        }
        return null;
    }
}
