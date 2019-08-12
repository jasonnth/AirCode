package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DeferredReleaser.Releasable;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.gestures.GestureDetector.ClickListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import java.util.concurrent.Executor;

public abstract class AbstractDraweeController<T, INFO> implements Releasable, ClickListener, DraweeController {
    private static final Class<?> TAG = AbstractDraweeController.class;
    private Object mCallerContext;
    private ControllerListener<INFO> mControllerListener;
    private Drawable mControllerOverlay;
    private DataSource<T> mDataSource;
    private final DeferredReleaser mDeferredReleaser;
    private Drawable mDrawable;
    private final DraweeEventTracker mEventTracker = new DraweeEventTracker();
    private T mFetchedImage;
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mRetainImageOnFailure;
    private RetryManager mRetryManager;
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;

    private static class InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
        private InternalForwardingListener() {
        }

        public static <INFO> InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> listener1, ControllerListener<? super INFO> listener2) {
            InternalForwardingListener<INFO> forwarder = new InternalForwardingListener<>();
            forwarder.addListener(listener1);
            forwarder.addListener(listener2);
            return forwarder;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Drawable createDrawable(T t);

    /* access modifiers changed from: protected */
    public abstract DataSource<T> getDataSource();

    /* access modifiers changed from: protected */
    public abstract INFO getImageInfo(T t);

    /* access modifiers changed from: protected */
    public abstract void releaseDrawable(Drawable drawable);

    /* access modifiers changed from: protected */
    public abstract void releaseImage(T t);

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor uiThreadImmediateExecutor, String id, Object callerContext) {
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = uiThreadImmediateExecutor;
        init(id, callerContext, true);
    }

    /* access modifiers changed from: protected */
    public void initialize(String id, Object callerContext) {
        init(id, callerContext, false);
    }

    private void init(String id, Object callerContext, boolean justConstructed) {
        this.mEventTracker.recordEvent(Event.ON_INIT_CONTROLLER);
        if (!justConstructed && this.mDeferredReleaser != null) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        releaseFetch();
        this.mRetainImageOnFailure = false;
        if (this.mRetryManager != null) {
            this.mRetryManager.init();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.init();
            this.mGestureDetector.setClickListener(this);
        }
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).clearListeners();
        } else {
            this.mControllerListener = null;
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.m1830v(TAG, "controller %x %s -> %s: initialize", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) id);
        }
        this.mId = id;
        this.mCallerContext = callerContext;
    }

    public void release() {
        this.mEventTracker.recordEvent(Event.ON_RELEASE_CONTROLLER);
        if (this.mRetryManager != null) {
            this.mRetryManager.reset();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.reset();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    private void releaseFetch() {
        boolean wasRequestSubmitted = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        if (this.mDataSource != null) {
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mDrawable != null) {
            releaseDrawable(this.mDrawable);
        }
        this.mDrawable = null;
        if (this.mFetchedImage != null) {
            logMessageAndImage("release", this.mFetchedImage);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (wasRequestSubmitted) {
            getControllerListener().onRelease(this.mId);
        }
    }

    public String getId() {
        return this.mId;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    /* access modifiers changed from: protected */
    public RetryManager getRetryManager() {
        return this.mRetryManager;
    }

    /* access modifiers changed from: protected */
    public void setRetryManager(RetryManager retryManager) {
        this.mRetryManager = retryManager;
    }

    /* access modifiers changed from: protected */
    public GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }

    /* access modifiers changed from: protected */
    public void setGestureDetector(GestureDetector gestureDetector) {
        this.mGestureDetector = gestureDetector;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setClickListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void setRetainImageOnFailure(boolean enabled) {
        this.mRetainImageOnFailure = enabled;
    }

    public void addControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).addListener(controllerListener);
        } else if (this.mControllerListener != null) {
            this.mControllerListener = InternalForwardingListener.createInternal(this.mControllerListener, controllerListener);
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    public void removeControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).removeListener(controllerListener);
        } else if (this.mControllerListener == controllerListener) {
            this.mControllerListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public ControllerListener<INFO> getControllerListener() {
        if (this.mControllerListener == null) {
            return BaseControllerListener.getNoOpListener();
        }
        return this.mControllerListener;
    }

    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }

    public void setHierarchy(DraweeHierarchy hierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.m1830v(TAG, "controller %x %s: setHierarchy: %s", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) hierarchy);
        }
        this.mEventTracker.recordEvent(hierarchy != null ? Event.ON_SET_HIERARCHY : Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (hierarchy != null) {
            Preconditions.checkArgument(hierarchy instanceof SettableDraweeHierarchy);
            this.mSettableDraweeHierarchy = (SettableDraweeHierarchy) hierarchy;
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    /* access modifiers changed from: protected */
    public void setControllerOverlay(Drawable controllerOverlay) {
        this.mControllerOverlay = controllerOverlay;
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    /* access modifiers changed from: protected */
    public Drawable getControllerOverlay() {
        return this.mControllerOverlay;
    }

    public void onAttach() {
        if (FLog.isLoggable(2)) {
            FLog.m1830v(TAG, "controller %x %s: onAttach: %s", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) this.mIsRequestSubmitted ? "request already submitted" : "request needs submit");
        }
        this.mEventTracker.recordEvent(Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            submitRequest();
        }
    }

    public void onDetach() {
        if (FLog.isLoggable(2)) {
            FLog.m1829v(TAG, "controller %x %s: onDetach", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId);
        }
        this.mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (FLog.isLoggable(2)) {
            FLog.m1830v(TAG, "controller %x %s: onTouchEvent %s", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) event);
        }
        if (this.mGestureDetector == null) {
            return false;
        }
        if (!this.mGestureDetector.isCapturingGesture() && !shouldHandleGesture()) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(event);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldHandleGesture() {
        return shouldRetryOnTap();
    }

    private boolean shouldRetryOnTap() {
        return this.mHasFetchFailed && this.mRetryManager != null && this.mRetryManager.shouldRetryOnTap();
    }

    public boolean onClick() {
        if (FLog.isLoggable(2)) {
            FLog.m1829v(TAG, "controller %x %s: onClick", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId);
        }
        if (!shouldRetryOnTap()) {
            return false;
        }
        this.mRetryManager.notifyTapToRetry();
        this.mSettableDraweeHierarchy.reset();
        submitRequest();
        return true;
    }

    /* access modifiers changed from: protected */
    public void submitRequest() {
        this.mEventTracker.recordEvent(Event.ON_DATASOURCE_SUBMIT);
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        this.mDataSource = getDataSource();
        if (FLog.isLoggable(2)) {
            FLog.m1830v(TAG, "controller %x %s: submitRequest: dataSource: %x", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        final String id = this.mId;
        final boolean wasImmediate = this.mDataSource.hasResult();
        this.mDataSource.subscribe(new BaseDataSubscriber<T>() {
            public void onNewResultImpl(DataSource<T> dataSource) {
                boolean isFinished = dataSource.isFinished();
                float progress = dataSource.getProgress();
                T image = dataSource.getResult();
                if (image != null) {
                    AbstractDraweeController.this.onNewResultInternal(id, dataSource, image, progress, isFinished, wasImmediate);
                } else if (isFinished) {
                    AbstractDraweeController.this.onFailureInternal(id, dataSource, new NullPointerException(), true);
                }
            }

            public void onFailureImpl(DataSource<T> dataSource) {
                AbstractDraweeController.this.onFailureInternal(id, dataSource, dataSource.getFailureCause(), true);
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                boolean isFinished = dataSource.isFinished();
                AbstractDraweeController.this.onProgressUpdateInternal(id, dataSource, dataSource.getProgress(), isFinished);
            }
        }, this.mUiThreadImmediateExecutor);
    }

    /* access modifiers changed from: private */
    public void onNewResultInternal(String id, DataSource<T> dataSource, T image, float progress, boolean isFinished, boolean wasImmediate) {
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndImage("ignore_old_datasource @ onNewResult", image);
            releaseImage(image);
            dataSource.close();
            return;
        }
        this.mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_RESULT : Event.ON_DATASOURCE_RESULT_INT);
        try {
            Drawable drawable = createDrawable(image);
            T previousImage = this.mFetchedImage;
            Drawable previousDrawable = this.mDrawable;
            this.mFetchedImage = image;
            this.mDrawable = drawable;
            if (isFinished) {
                try {
                    logMessageAndImage("set_final_result @ onNewResult", image);
                    this.mDataSource = null;
                    this.mSettableDraweeHierarchy.setImage(drawable, 1.0f, wasImmediate);
                    getControllerListener().onFinalImageSet(id, getImageInfo(image), getAnimatable());
                } catch (Throwable th) {
                    if (!(previousDrawable == null || previousDrawable == drawable)) {
                        releaseDrawable(previousDrawable);
                    }
                    if (!(previousImage == null || previousImage == image)) {
                        logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                        releaseImage(previousImage);
                    }
                    throw th;
                }
            } else {
                logMessageAndImage("set_intermediate_result @ onNewResult", image);
                this.mSettableDraweeHierarchy.setImage(drawable, progress, wasImmediate);
                getControllerListener().onIntermediateImageSet(id, getImageInfo(image));
            }
            if (!(previousDrawable == null || previousDrawable == drawable)) {
                releaseDrawable(previousDrawable);
            }
            if (previousImage != null && previousImage != image) {
                logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                releaseImage(previousImage);
            }
        } catch (Exception exception) {
            logMessageAndImage("drawable_failed @ onNewResult", image);
            releaseImage(image);
            onFailureInternal(id, dataSource, exception, isFinished);
        }
    }

    /* access modifiers changed from: private */
    public void onFailureInternal(String id, DataSource<T> dataSource, Throwable throwable, boolean isFinished) {
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onFailure", throwable);
            dataSource.close();
            return;
        }
        this.mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_FAILURE : Event.ON_DATASOURCE_FAILURE_INT);
        if (isFinished) {
            logMessageAndFailure("final_failed @ onFailure", throwable);
            this.mDataSource = null;
            this.mHasFetchFailed = true;
            if (this.mRetainImageOnFailure && this.mDrawable != null) {
                this.mSettableDraweeHierarchy.setImage(this.mDrawable, 1.0f, true);
            } else if (shouldRetryOnTap()) {
                this.mSettableDraweeHierarchy.setRetry(throwable);
            } else {
                this.mSettableDraweeHierarchy.setFailure(throwable);
            }
            getControllerListener().onFailure(this.mId, throwable);
            return;
        }
        logMessageAndFailure("intermediate_failed @ onFailure", throwable);
        getControllerListener().onIntermediateImageFailed(this.mId, throwable);
    }

    /* access modifiers changed from: private */
    public void onProgressUpdateInternal(String id, DataSource<T> dataSource, float progress, boolean isFinished) {
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onProgress", null);
            dataSource.close();
        } else if (!isFinished) {
            this.mSettableDraweeHierarchy.setProgress(progress, false);
        }
    }

    private boolean isExpectedDataSource(String id, DataSource<T> dataSource) {
        return id.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }

    private void logMessageAndImage(String messageAndMethod, T image) {
        if (FLog.isLoggable(2)) {
            FLog.m1833v(TAG, "controller %x %s: %s: image: %s %x", Integer.valueOf(System.identityHashCode(this)), this.mId, messageAndMethod, getImageClass(image), Integer.valueOf(getImageHash(image)));
        }
    }

    private void logMessageAndFailure(String messageAndMethod, Throwable throwable) {
        if (FLog.isLoggable(2)) {
            FLog.m1831v(TAG, "controller %x %s: %s: failure: %s", (Object) Integer.valueOf(System.identityHashCode(this)), (Object) this.mId, (Object) messageAndMethod, (Object) throwable);
        }
    }

    public Animatable getAnimatable() {
        if (this.mDrawable instanceof Animatable) {
            return (Animatable) this.mDrawable;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getImageClass(T image) {
        return image != null ? image.getClass().getSimpleName() : "<null>";
    }

    /* access modifiers changed from: protected */
    public int getImageHash(T image) {
        return System.identityHashCode(image);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", (Object) this.mEventTracker.toString()).toString();
    }
}
