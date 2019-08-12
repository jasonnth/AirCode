package com.airbnb.airrequest;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.rxgroups.GroupLifecycleManager;
import com.airbnb.rxgroups.ObservableGroup;
import com.airbnb.rxgroups.RequestSubscription;
import com.airbnb.rxgroups.TaggedObserver;
import com.google.common.base.Preconditions;
import p032rx.android.schedulers.AndroidSchedulers;

public class RequestManager implements RequestExecutor {
    private final AirRequestInitializer initializer;
    private final GroupLifecycleManager lifecycleManager;

    protected RequestManager(AirRequestInitializer initializer2, GroupLifecycleManager lifecycleManager2) {
        this.initializer = initializer2;
        this.lifecycleManager = lifecycleManager2;
    }

    public <T> boolean hasRequest(BaseRequestListener<T> listener, Class<? extends BaseRequest<T>> baseRequestClass) {
        return hasRequest(listener, TagFactory.requestTag(baseRequestClass));
    }

    public boolean hasRequest(BaseRequestListener<?> listener, String tag) {
        return this.lifecycleManager.hasObservable(listener, tag);
    }

    public <T> boolean hasRequests(BaseRequestListener<T> listener) {
        return this.lifecycleManager.hasObservables(listener);
    }

    public void subscribe(Object target) {
        this.lifecycleManager.initializeAutoTaggingAndResubscription(target);
    }

    public <T> void resubscribe(TaggedObserver<T> observer, Class<? extends AirRequest> requestKlass) {
        resubscribe(observer, TagFactory.requestTag(requestKlass));
    }

    public <T> void resubscribe(TaggedObserver<T> listener, String tag) {
        this.lifecycleManager.group().resubscribe(listener, tag);
    }

    public <T> void resubscribe(TaggedObserver<T> listener) {
        this.lifecycleManager.group().resubscribeAll(listener);
    }

    public <T> void executeOrResubscribe(BaseRequest<T> request, TaggedObserver<AirResponse<T>> resubscribableListener) {
        Preconditions.checkArgument(request.observer() == resubscribableListener, "Request configured with a different list");
        if (this.lifecycleManager.group().hasObservable(request.observer(), TagFactory.requestTag((AirRequest) request))) {
            resubscribe(resubscribableListener, request.getClass());
        } else {
            execute(request);
        }
    }

    public <T> RequestSubscription execute(BaseRequest<T> request) {
        return executeWithTag(request, TagFactory.requestTag((AirRequest) request));
    }

    public <T> RequestSubscription executeWithTag(BaseRequest<T> request, String tag) {
        ObservableGroup group = this.lifecycleManager.group();
        Preconditions.checkState(!group.isDestroyed(), "Cannot execute request. Group is already destroyed! id=" + group.mo26652id());
        Preconditions.checkNotNull(request.observer(), "Cannot execute a request without an observer.");
        if (!isDebugFeaturesEnabled() || !(request.observer() instanceof TaggedObserver) || ((TaggedObserver) request.observer()).getTag() != null) {
            this.initializer.adapt(request).observeOn(AndroidSchedulers.mainThread()).compose(this.lifecycleManager.transform(request.observer(), tag)).subscribe(request.observer());
            return group.subscription(request.observer(), tag);
        }
        throw new RuntimeException("Observer tag is null for " + request.getClass().getSimpleName() + ". Did you forget to annotate with @AutoResubscribe, or to call RequestManager#subscribe()? If this Observer should not be resubscribed use a NonResubscribableRequestListener.");
    }

    public void cancelRequests(BaseRequestListener<?> observer) {
        this.lifecycleManager.cancelAllObservablesForObserver(observer);
    }

    public void cancelRequest(BaseRequestListener<?> observer, String requestTag) {
        this.lifecycleManager.cancelAndRemove(observer, requestTag);
    }

    public <T> void cancelRequest(BaseRequestListener<T> observer, Class<? extends BaseRequest<T>> requestClass) {
        cancelRequest(observer, TagFactory.requestTag(requestClass));
    }

    public <T> void cancelRequest(BaseRequestListener<T> observer, BaseRequest<T> baseRequest) {
        cancelRequest(observer, TagFactory.requestTag((AirRequest) baseRequest));
    }

    public static RequestManager onCreate(AirRequestInitializer initializer2, Object target, Bundle savedInstanceState) {
        return new RequestManager(initializer2, GroupLifecycleManager.onCreate(initializer2.observableManager(), savedInstanceState, target));
    }

    public boolean isDebugFeaturesEnabled() {
        return this.initializer.isDebugFeaturesEnabled();
    }

    public void onDestroy(Activity activity) {
        this.lifecycleManager.onDestroy(activity);
    }

    public void onDestroy(Fragment fragment) {
        onDestroy((Activity) fragment.getActivity());
    }

    public void onResume() {
        this.lifecycleManager.onResume();
    }

    public void onPause() {
        this.lifecycleManager.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.lifecycleManager.onSaveInstanceState(outState);
    }
}
