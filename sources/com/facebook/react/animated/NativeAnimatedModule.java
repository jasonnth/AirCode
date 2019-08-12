package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;

@ReactModule(name = "NativeAnimatedModule")
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, OnBatchCompleteListener {
    /* access modifiers changed from: private */
    public GuardedChoreographerFrameCallback mAnimatedFrameCallback;
    private ArrayList<UIThreadOperation> mOperations = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Object mOperationsCopyLock = new Object();
    /* access modifiers changed from: private */
    public ReactChoreographer mReactChoreographer;
    /* access modifiers changed from: private */
    public volatile ArrayList<UIThreadOperation> mReadyOperations = null;

    private interface UIThreadOperation {
        void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager);
    }

    public NativeAnimatedModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        ReactApplicationContext reactCtx = getReactApplicationContext();
        final NativeAnimatedNodesManager nodesManager = new NativeAnimatedNodesManager((UIManagerModule) reactCtx.getNativeModule(UIManagerModule.class));
        this.mAnimatedFrameCallback = new GuardedChoreographerFrameCallback(reactCtx) {
            /* access modifiers changed from: protected */
            public void doFrameGuarded(long frameTimeNanos) {
                ArrayList<UIThreadOperation> operations;
                synchronized (NativeAnimatedModule.this.mOperationsCopyLock) {
                    operations = NativeAnimatedModule.this.mReadyOperations;
                    NativeAnimatedModule.this.mReadyOperations = null;
                }
                if (operations != null) {
                    int size = operations.size();
                    for (int i = 0; i < size; i++) {
                        ((UIThreadOperation) operations.get(i)).execute(nodesManager);
                    }
                }
                if (nodesManager.hasActiveAnimations()) {
                    nodesManager.runUpdates(frameTimeNanos);
                }
                ((ReactChoreographer) Assertions.assertNotNull(NativeAnimatedModule.this.mReactChoreographer)).postFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.this.mAnimatedFrameCallback);
            }
        };
        reactCtx.addLifecycleEventListener(this);
    }

    public void onBatchComplete() {
        ArrayList<UIThreadOperation> operations = this.mOperations.isEmpty() ? null : this.mOperations;
        if (operations != null) {
            this.mOperations = new ArrayList<>();
            synchronized (this.mOperationsCopyLock) {
                if (this.mReadyOperations == null) {
                    this.mReadyOperations = operations;
                } else {
                    this.mReadyOperations.addAll(operations);
                }
            }
        }
    }

    public void onHostResume() {
        enqueueFrameCallback();
    }

    public void onHostPause() {
        clearFrameCallback();
    }

    public void onHostDestroy() {
    }

    public String getName() {
        return "NativeAnimatedModule";
    }

    private void clearFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    @ReactMethod
    public void createAnimatedNode(final int tag, final ReadableMap config) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.createAnimatedNode(tag, config);
            }
        });
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int tag) {
        final AnimatedNodeValueListener listener = new AnimatedNodeValueListener() {
            public void onValueUpdate(double value) {
                WritableMap onAnimatedValueData = Arguments.createMap();
                onAnimatedValueData.putInt("tag", tag);
                onAnimatedValueData.putDouble("value", value);
                ((RCTDeviceEventEmitter) NativeAnimatedModule.this.getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", onAnimatedValueData);
            }
        };
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.startListeningToAnimatedNodeValue(tag, listener);
            }
        });
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int tag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.stopListeningToAnimatedNodeValue(tag);
            }
        });
    }

    @ReactMethod
    public void dropAnimatedNode(final int tag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.dropAnimatedNode(tag);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeValue(final int tag, final double value) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.setAnimatedNodeValue(tag, value);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeOffset(final int tag, final double value) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.setAnimatedNodeOffset(tag, value);
            }
        });
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(final int tag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.flattenAnimatedNodeOffset(tag);
            }
        });
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(final int tag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.extractAnimatedNodeOffset(tag);
            }
        });
    }

    @ReactMethod
    public void startAnimatingNode(int animationId, int animatedNodeTag, ReadableMap animationConfig, Callback endCallback) {
        final int i = animationId;
        final int i2 = animatedNodeTag;
        final ReadableMap readableMap = animationConfig;
        final Callback callback = endCallback;
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.startAnimatingNode(i, i2, readableMap, callback);
            }
        });
    }

    @ReactMethod
    public void stopAnimation(final int animationId) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.stopAnimation(animationId);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodes(final int parentNodeTag, final int childNodeTag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.connectAnimatedNodes(parentNodeTag, childNodeTag);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodes(final int parentNodeTag, final int childNodeTag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.disconnectAnimatedNodes(parentNodeTag, childNodeTag);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodeToView(final int animatedNodeTag, final int viewTag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.connectAnimatedNodeToView(animatedNodeTag, viewTag);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int animatedNodeTag, final int viewTag) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.disconnectAnimatedNodeFromView(animatedNodeTag, viewTag);
            }
        });
    }

    @ReactMethod
    public void addAnimatedEventToView(final int viewTag, final String eventName, final ReadableMap eventMapping) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.addAnimatedEventToView(viewTag, eventName, eventMapping);
            }
        });
    }

    @ReactMethod
    public void removeAnimatedEventFromView(final int viewTag, final String eventName) {
        this.mOperations.add(new UIThreadOperation() {
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                animatedNodesManager.removeAnimatedEventFromView(viewTag, eventName);
            }
        });
    }
}
