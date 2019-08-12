package com.airbnb.android.core.viewcomponents.models;

import android.support.p002v7.widget.DefaultItemAnimator;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.Check;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.epoxy.EpoxyItemAnimator;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import java.util.List;

public abstract class CarouselModel extends AirEpoxyModel<Carousel> {
    boolean hasFixedSize = true;
    LayoutManager layoutManager;
    List<? extends EpoxyModel<?>> models;
    OnScrollListener onScrollListener;
    RecycledViewPool viewPool;

    public void bind(Carousel carousel) {
        super.bind(carousel);
        if (this.viewPool != null) {
            carousel.setRecycledViewPool(this.viewPool);
        }
        carousel.setHasFixedSize(this.hasFixedSize);
        if (carousel.getItemAnimator() != null && carousel.getItemAnimator().getClass().equals(DefaultItemAnimator.class)) {
            carousel.setItemAnimator(new EpoxyItemAnimator());
        }
        Check.state(this.models != null, "Models cannot be null");
        carousel.setModels(this.models);
        if (this.layoutManager != null) {
            carousel.setLayoutManager(this.layoutManager);
        } else {
            carousel.setDefaultLayoutManager();
        }
        LayoutManager layoutManager2 = carousel.getLayoutManager();
        if ((layoutManager2 instanceof LinearLayoutManager) && !this.models.isEmpty()) {
            EpoxyModel<?> epoxyModel = (EpoxyModel) this.models.get(0);
            if (epoxyModel instanceof AirEpoxyModel) {
                NumCarouselItemsShown numCarouselItemsShown = ((AirEpoxyModel) epoxyModel).numCarouselItemsShown();
                if (numCarouselItemsShown != null) {
                    ((LinearLayoutManager) layoutManager2).setInitialPrefetchItemCount((int) Math.ceil((double) numCarouselItemsShown.forCurrentScreen(carousel.getContext())));
                }
            }
        }
        if (this.onScrollListener != null) {
            carousel.removeOnScrollListener(this.onScrollListener);
            carousel.addOnScrollListener(this.onScrollListener);
        }
    }

    public void unbind(Carousel carousel) {
        carousel.clearModels();
        if (this.layoutManager != null) {
            carousel.setLayoutManager(null);
        }
        if (this.onScrollListener != null) {
            carousel.removeOnScrollListener(this.onScrollListener);
        }
    }

    public CarouselModel forMicroCards(boolean forMicroCards) {
        if (forMicroCards) {
            layout(C0716R.layout.view_holder_carousel_micro_cards);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public CarouselModel noBottomPadding(boolean noBottomPadding) {
        if (noBottomPadding) {
            layout(C0716R.layout.view_holder_carousel_no_bottom_padding);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public CarouselModel forCheckInCards(boolean forCheckInCards) {
        if (forCheckInCards) {
            layout(C0716R.layout.view_holder_carousel_check_in_cards);
        } else {
            layout(getDefaultLayout());
        }
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }

    public boolean shouldSaveViewState() {
        return true;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
