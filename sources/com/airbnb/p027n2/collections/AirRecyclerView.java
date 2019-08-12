package com.airbnb.p027n2.collections;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.InlineEpoxyController;

/* renamed from: com.airbnb.n2.collections.AirRecyclerView */
public class AirRecyclerView extends RecyclerView {
    private boolean removeAdapterWhenDetachedFromWindow = true;
    private Adapter removedAdapter;

    /* renamed from: com.airbnb.n2.collections.AirRecyclerView$RecyclerViewException */
    private class RecyclerViewException extends RuntimeException {
        public RecyclerViewException(RuntimeException e) {
            super(AirRecyclerView.this.buildMessage(e), e);
        }
    }

    public AirRecyclerView(Context context) {
        super(context);
    }

    public AirRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AirRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRemoveAdapterWhenDetachedFromWindow(boolean removeAdapterWhenDetachedFromWindow2) {
        this.removeAdapterWhenDetachedFromWindow = removeAdapterWhenDetachedFromWindow2;
    }

    public void setEpoxyController(EpoxyController controller) {
        setAdapter(controller.getAdapter());
    }

    public void setEpoxyControllerAndBuildModels(EpoxyController controller) {
        controller.requestModelBuild();
        setAdapter(controller.getAdapter());
    }

    public void setStaticModels(EpoxyModel<?>... models) {
        setEpoxyControllerAndBuildModels(new InlineEpoxyController(AirRecyclerView$$Lambda$1.lambdaFactory$(models)));
    }

    static /* synthetic */ void lambda$setStaticModels$0(EpoxyModel[] models, InlineEpoxyController controller) {
        for (int index = 0; index < models.length; index++) {
            models[index].mo11716id((long) index).addTo(controller);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthSpec, int heightSpec) {
        try {
            super.onMeasure(widthSpec, heightSpec);
        } catch (RuntimeException e) {
            throw new RecyclerViewException(e);
        }
    }

    /* access modifiers changed from: private */
    public String buildMessage(RuntimeException e) {
        return (("adapter=" + getAdapter()) + "parent=" + getParent()) + " rootCause=" + e.getClass().getSimpleName() + ": " + e.getMessage();
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.removedAdapter = null;
    }

    public void swapAdapter(Adapter adapter, boolean removeAndRecycleExistingViews) {
        super.swapAdapter(adapter, removeAndRecycleExistingViews);
        this.removedAdapter = null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.removedAdapter != null) {
            swapAdapter(this.removedAdapter, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.removeAdapterWhenDetachedFromWindow) {
            Adapter currentAdapter = getAdapter();
            if (currentAdapter != null) {
                swapAdapter(null, true);
                this.removedAdapter = currentAdapter;
            }
        }
    }
}
