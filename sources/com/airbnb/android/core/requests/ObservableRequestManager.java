package com.airbnb.android.core.requests;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.rxgroups.GroupLifecycleManager;
import com.airbnb.rxgroups.ObservableGroup;
import com.airbnb.rxgroups.Preconditions;
import com.airbnb.rxgroups.RequestSubscription;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.android.schedulers.AndroidSchedulers;

public class ObservableRequestManager extends RequestManager {
    private final AirRequestInitializer initializer;
    private final GroupLifecycleManager lifecycleManager;

    public static ObservableRequestManager onCreate(AirRequestInitializer initializer2, Object target, Bundle savedInstanceState) {
        return new ObservableRequestManager(initializer2, GroupLifecycleManager.onCreate(initializer2.observableManager(), savedInstanceState, target));
    }

    public ObservableRequestManager(AirRequestInitializer initializer2, GroupLifecycleManager lifecycleManager2) {
        super(initializer2, lifecycleManager2);
        this.initializer = initializer2;
        this.lifecycleManager = lifecycleManager2;
    }

    public AirRequestInitializer requestInitializer() {
        return this.initializer;
    }

    public GroupLifecycleManager lifecycleManager() {
        return this.lifecycleManager;
    }

    public <T> Observable<? extends AirResponse<T>> adapt(BaseRequest<T> request) {
        return this.initializer.adapt(request);
    }

    public <T> RequestSubscription subscribe(Observable<? extends AirResponse<T>> observable, Observer<AirResponse<T>> observer, String tag) {
        ObservableGroup group = this.lifecycleManager.group();
        Preconditions.checkState(!group.isDestroyed(), "Cannot execute request. Group is already destroyed! id=" + group.mo26652id());
        observable.observeOn(AndroidSchedulers.mainThread()).compose(this.lifecycleManager.transform(observer, tag)).subscribe(observer);
        return group.subscription(observer, tag);
    }
}
