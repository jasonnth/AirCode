package com.airbnb.android.core.viewcomponents;

import android.os.Bundle;
import android.support.p002v7.widget.DefaultItemAnimator;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.AdapterDataObserver;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.EpoxyItemAnimator;
import icepick.Icepick;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Deprecated
public class AirEpoxyAdapter extends EpoxyAdapter {
    private final boolean autoDividerEnabled;
    private final EpoxyAutoDividerObserver epoxyAutoDividerObserver;
    private boolean filterDuplicates;
    private boolean hasAttachedToRecycler;

    public AirEpoxyAdapter() {
        this(false);
    }

    public AirEpoxyAdapter(boolean autoDividerEnabled2) {
        this.epoxyAutoDividerObserver = new EpoxyAutoDividerObserver(this, this.models);
        this.autoDividerEnabled = autoDividerEnabled2;
        addChangeListenerForDebugBuilds();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        Icepick.restoreInstanceState(this, inState);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView.getItemAnimator() != null && recyclerView.getItemAnimator().getClass().equals(DefaultItemAnimator.class)) {
            recyclerView.setItemAnimator(new EpoxyItemAnimator());
        }
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).setRecycleChildrenOnDetach(true);
        }
        if (this.autoDividerEnabled) {
            if (!this.hasAttachedToRecycler) {
                registerAdapterDataObserver(this.epoxyAutoDividerObserver);
            }
            this.epoxyAutoDividerObserver.onChanged();
        }
        this.hasAttachedToRecycler = true;
    }

    /* access modifiers changed from: protected */
    public void notifyModelsChanged() {
        if (this.filterDuplicates && !Trebuchet.launch(TrebuchetKeys.EPOXY_FILTER_DUPLICATES_KILL_SWITCH)) {
            Set<Long> modelsSet = new HashSet<>();
            Iterator<EpoxyModel<?>> modelIterator = this.models.iterator();
            while (modelIterator.hasNext()) {
                EpoxyModel<?> model = (EpoxyModel) modelIterator.next();
                if (!modelsSet.add(Long.valueOf(model.mo11715id()))) {
                    modelIterator.remove();
                    BugsnagWrapper.notify((Throwable) new IllegalStateException("Duplicate model found: " + model));
                }
            }
        }
        if (this.autoDividerEnabled && this.hasAttachedToRecycler) {
            unregisterAdapterDataObserver(this.epoxyAutoDividerObserver);
            this.epoxyAutoDividerObserver.onChanged();
        }
        super.notifyModelsChanged();
        if (this.autoDividerEnabled && this.hasAttachedToRecycler) {
            registerAdapterDataObserver(this.epoxyAutoDividerObserver);
        }
    }

    public void setSpanCount(int spanCount) {
        int oldSpanCount = getSpanCount();
        super.setSpanCount(spanCount);
        this.epoxyAutoDividerObserver.setSpanCount(spanCount);
        if (oldSpanCount != spanCount) {
            onSpanCountChanged(oldSpanCount, spanCount);
        }
    }

    public void setFilterDuplicates(boolean filterDuplicates2) {
        this.filterDuplicates = filterDuplicates2;
    }

    public void onSpanCountChanged(int oldSpanCount, int newSpanCount) {
    }

    private void addChangeListenerForDebugBuilds() {
        if (BuildHelper.isDevelopmentBuild()) {
            final String tag = getClass().getSimpleName();
            registerAdapterDataObserver(new AdapterDataObserver() {
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    Log.d(tag, "Item range changed. Start: " + positionStart + " Count: " + itemCount);
                }

                public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                    if (payload == null) {
                        onItemRangeChanged(positionStart, itemCount);
                    } else {
                        Log.d(tag, "Item range changed with payloads. Start: " + positionStart + " Count: " + itemCount);
                    }
                }

                public void onItemRangeInserted(int positionStart, int itemCount) {
                    Log.d(tag, "Item range inserted. Start: " + positionStart + " Count: " + itemCount);
                }

                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    Log.d(tag, "Item range removed. Start: " + positionStart + " Count: " + itemCount);
                }

                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    Log.d(tag, "Item moved. From: " + fromPosition + " To: " + toPosition);
                }
            });
        }
    }

    public String toString() {
        return "AirEpoxyAdapter{adapter:" + getClass().getSimpleName() + "itemCount=" + getItemCount() + '}';
    }
}
