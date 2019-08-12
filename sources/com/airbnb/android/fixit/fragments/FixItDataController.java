package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.models.FixItReport;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.functions.Action1;

public class FixItDataController {
    @State
    boolean isLoading;
    private final List<UpdateListener> listeners = new ArrayList();
    @State
    FixItReport report;

    interface UpdateListener {
        void dataUpdated();
    }

    public FixItDataController(Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addListener(UpdateListener listener) {
        this.listeners.add(listener);
        listener.dataUpdated();
    }

    public void removeListener(UpdateListener listener) {
        this.listeners.remove(listener);
    }

    public void setReport(FixItReport report2) {
        this.report = report2;
        updateListeners();
    }

    public void setItem(FixItItem item) {
        int index = getIndexOfItem(item);
        if (index == -1) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Invalid item in FixItReport"));
            return;
        }
        this.report.getItems().set(index, item);
        updateListeners();
    }

    public void setLoading(boolean isLoading2) {
        this.isLoading = isLoading2;
        updateListeners();
    }

    public FixItReport getReport() {
        return this.report;
    }

    public FixItItem getItem(long itemId) {
        return (FixItItem) FluentIterable.from((Iterable<E>) this.report.getItems()).filter(FixItDataController$$Lambda$1.lambdaFactory$(itemId)).first().orNull();
    }

    static /* synthetic */ boolean lambda$getItem$0(long itemId, FixItItem item) {
        return item != null && item.getId() == itemId;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void updateListeners() {
        notifyListeners(FixItDataController$$Lambda$2.lambdaFactory$());
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener listener : this.listeners) {
            listenerAction.call(listener);
        }
    }

    private int getIndexOfItem(FixItItem item) {
        List<FixItItem> items = this.report.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (((FixItItem) items.get(i)).getId() == item.getId()) {
                return i;
            }
        }
        return -1;
    }
}
