package com.airbnb.rxgroups;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.UUID;
import p032rx.Observable.Transformer;
import p032rx.Observer;

public class GroupLifecycleManager {
    private final ObservableGroup group;
    private boolean hasSavedState;
    private final ObservableManager observableManager;

    static class State implements Parcelable {
        public static final Creator<State> CREATOR = new Creator<State>() {
            public State[] newArray(int size) {
                return new State[size];
            }

            public State createFromParcel(Parcel source) {
                return new State((UUID) source.readSerializable(), source.readLong());
            }
        };
        final long groupId;
        final UUID managerId;

        State(UUID managerId2, long groupId2) {
            this.managerId = managerId2;
            this.groupId = groupId2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeSerializable(this.managerId);
            dest.writeLong(this.groupId);
        }
    }

    private GroupLifecycleManager(ObservableManager observableManager2, ObservableGroup group2) {
        this.observableManager = observableManager2;
        this.group = group2;
    }

    public static GroupLifecycleManager onCreate(ObservableManager observableManager2, Bundle savedState, Object target) {
        ObservableGroup group2;
        if (savedState != null) {
            State state = (State) savedState.getParcelable("KEY_GROUPLIFECYCLEMANAGER_STATE");
            Preconditions.checkState(state != null, "Must call onSaveInstanceState() first");
            if (state.managerId != observableManager2.mo26670id()) {
                group2 = observableManager2.newGroup();
            } else {
                group2 = observableManager2.getGroup(state.groupId);
            }
        } else {
            group2 = observableManager2.newGroup();
        }
        group2.lock();
        GroupLifecycleManager manager = new GroupLifecycleManager(observableManager2, group2);
        if (target != null) {
            manager.initializeAutoTaggingAndResubscription(target);
        }
        return manager;
    }

    public ObservableGroup group() {
        return this.group;
    }

    public <T> Transformer<? super T, T> transform(Observer<? super T> observer, String observableTag) {
        return this.group.transform(observer, observableTag);
    }

    public boolean hasObservables(Observer<?> observer) {
        return this.group.hasObservables(observer);
    }

    public boolean hasObservable(Observer<?> observer, String observableTag) {
        return this.group.hasObservable(observer, observableTag);
    }

    public void initializeAutoTaggingAndResubscription(Object target) {
        Preconditions.checkNotNull(target, "Target cannot be null");
        this.group.initializeAutoTaggingAndResubscription(target);
    }

    public void cancelAllObservablesForObserver(Observer<?> observer) {
        this.group.cancelAllObservablesForObserver(observer);
    }

    public void cancelAndRemove(Observer<?> observer, String observableTag) {
        this.group.cancelAndRemove(observer, observableTag);
    }

    private void onDestroy(boolean isFinishing) {
        if (isFinishing) {
            this.observableManager.destroy(this.group);
            return;
        }
        group().removeNonResubscribableObservers();
        this.group.unsubscribe();
    }

    public void onDestroy(Activity activity) {
        onDestroy(!this.hasSavedState || (activity != null && activity.isFinishing() && !activity.isChangingConfigurations()));
    }

    public void onResume() {
        this.hasSavedState = false;
        unlock();
    }

    public void onPause() {
        lock();
    }

    private void lock() {
        this.group.lock();
    }

    private void unlock() {
        this.group.unlock();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.hasSavedState = true;
        outState.putParcelable("KEY_GROUPLIFECYCLEMANAGER_STATE", new State(this.observableManager.mo26670id(), this.group.mo26652id()));
    }
}
