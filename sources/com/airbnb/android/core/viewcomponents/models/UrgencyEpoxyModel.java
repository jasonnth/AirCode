package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.UrgencyViewAnimationHelper;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.views.UrgencyView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class UrgencyEpoxyModel extends AirEpoxyModel<UrgencyView> {
    private final UrgencyViewAnimationHelper animationHelper = new UrgencyViewAnimationHelper();
    OnClickListener clickListener;
    String contextualMessage;
    boolean shouldAnimate = true;
    String subtitle;
    String title;
    UrgencyMessageType type;

    public UrgencyEpoxyModel(long id) {
        super(id);
    }

    public UrgencyEpoxyModel() {
    }

    public void bind(UrgencyView view) {
        super.bind(view);
        view.setUrgencyData(this.title, this.subtitle, this.type);
        view.setOnClickListener(this.clickListener);
        view.setClickable(this.clickListener != null);
    }

    public void onViewAttachedToWindow(UrgencyView view) {
        if (this.shouldAnimate) {
            this.animationHelper.bindUrgencyView(view);
        } else {
            view.showWithoutAnimation();
        }
    }

    public void onViewDetachedFromWindow(UrgencyView view) {
        if (this.shouldAnimate) {
            this.animationHelper.unbindUrgencyView();
        }
    }

    public void unbind(UrgencyView view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public boolean onFailedToRecycleView(UrgencyView view) {
        view.showWithoutAnimation();
        return true;
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    public boolean shouldSaveViewState() {
        return true;
    }
}
