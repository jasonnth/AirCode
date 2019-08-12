package com.airbnb.p027n2.epoxy;

import android.os.Bundle;
import android.support.p002v7.widget.DefaultItemAnimator;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.N2Context;
import icepick.Icepick;

/* renamed from: com.airbnb.n2.epoxy.AirEpoxyController */
public abstract class AirEpoxyController extends EpoxyController {
    private final EpoxyAutoDividerInterceptor autoDividerInterceptor = new EpoxyAutoDividerInterceptor(this);

    public AirEpoxyController() {
        addInterceptor(this.autoDividerInterceptor);
        setDebugLoggingEnabled(N2Context.instance().graph().mo11971n2().isDebugBuild());
    }

    /* access modifiers changed from: protected */
    public void disableAutoDividers() {
        if (this.autoDividerInterceptor.hasIntercepted()) {
            throw new IllegalStateException("Auto dividers should only be disabled in the constructor, before models are built");
        }
        removeInterceptor(this.autoDividerInterceptor);
    }

    /* access modifiers changed from: protected */
    public void onExceptionSwallowed(RuntimeException exception) {
        N2Context.instance().graph().mo11971n2().throwOrNotify(exception);
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
    }

    public String toString() {
        return "AirEpoxyController{controller:" + getClass().getSimpleName() + "itemCount=" + getAdapter().getItemCount() + '}';
    }
}
