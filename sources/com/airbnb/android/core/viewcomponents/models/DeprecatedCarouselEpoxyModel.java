package com.airbnb.android.core.viewcomponents.models;

import android.support.p002v7.widget.DefaultItemAnimator;
import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.Check;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.epoxy.EpoxyItemAnimator;
import java.util.List;

@Deprecated
public abstract class DeprecatedCarouselEpoxyModel<A extends EpoxyAdapter> extends AirEpoxyModel<Carousel> {
    A adapter;
    boolean hasFixedSize = true;
    LayoutManager layoutManager;
    List<? extends EpoxyModel<?>> models;
    OnScrollListener onScrollListener;
    int startingPosition;
    RecycledViewPool viewPool;

    public void bind(Carousel carousel) {
        boolean z = true;
        super.bind(carousel);
        if (this.viewPool != null) {
            carousel.setRecycledViewPool(this.viewPool);
        }
        carousel.setHasFixedSize(this.hasFixedSize);
        if (carousel.getItemAnimator() != null && carousel.getItemAnimator().getClass().equals(DefaultItemAnimator.class)) {
            carousel.setItemAnimator(new EpoxyItemAnimator());
        }
        if ((this.adapter == null) == (this.models == null)) {
            z = false;
        }
        Check.state(z, "One of adapter or models must be set.");
        if (this.adapter != null) {
            carousel.swapAdapter(this.adapter, false);
        } else {
            carousel.setModels(this.models);
        }
        if (this.layoutManager != null) {
            carousel.setLayoutManager(this.layoutManager);
        } else {
            carousel.setDefaultLayoutManager();
        }
        if (this.onScrollListener != null) {
            carousel.removeOnScrollListener(this.onScrollListener);
            carousel.addOnScrollListener(this.onScrollListener);
        }
        carousel.scrollToPosition(this.startingPosition);
    }

    public void unbind(Carousel carousel) {
        super.unbind(carousel);
        if (this.adapter != null) {
            carousel.swapAdapter(null, true);
        } else {
            carousel.clearModels();
        }
        if (this.layoutManager != null) {
            carousel.setLayoutManager(null);
        }
        if (this.onScrollListener != null) {
            carousel.removeOnScrollListener(this.onScrollListener);
        }
    }

    /* renamed from: id */
    public DeprecatedCarouselEpoxyModel<A> m4502id(long id) {
        return (DeprecatedCarouselEpoxyModel) super.mo11716id(id);
    }

    public DeprecatedCarouselEpoxyModel<A> adapter(A adapter2) {
        this.adapter = adapter2;
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> startingPosition(int startingPosition2) {
        this.startingPosition = startingPosition2;
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> layoutManager(LayoutManager layoutManager2) {
        this.layoutManager = layoutManager2;
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> onScrollListener(OnScrollListener onScrollListener2) {
        this.onScrollListener = onScrollListener2;
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> forMicroCards(boolean forMicroCards) {
        if (forMicroCards) {
            layout(C0716R.layout.view_holder_carousel_micro_cards);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> noBottomPadding(boolean noBottomPadding) {
        if (noBottomPadding) {
            layout(C0716R.layout.view_holder_carousel_no_bottom_padding);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> forCheckInCards(boolean forCheckInCards) {
        if (forCheckInCards) {
            layout(C0716R.layout.view_holder_carousel_check_in_cards);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public A adapter() {
        return this.adapter;
    }

    public int getDividerViewType() {
        return 0;
    }

    public DeprecatedCarouselEpoxyModel<A> viewPool(RecycledViewPool recycledViewPool) {
        this.viewPool = recycledViewPool;
        return this;
    }

    public DeprecatedCarouselEpoxyModel<A> hide() {
        return (DeprecatedCarouselEpoxyModel) super.hide();
    }

    public boolean shouldSaveViewState() {
        return true;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
