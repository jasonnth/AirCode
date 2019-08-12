package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.views.ListingsTray;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class ListingsTrayEpoxyModel extends AirEpoxyModel<ListingsTray> {
    CarouselItemClickListener carouselItemClickListener;
    List<ListingTrayItem> items;
    boolean showSmallTitle;
    OnSnapToPositionListener snapToPositionListener;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(ListingsTray view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence title2 = this.title;
        if (TextUtils.isEmpty(title2) && this.titleRes > 0) {
            title2 = context.getString(this.titleRes);
        }
        view.setup(title2, this.items, this.carouselItemClickListener);
        if (this.subtitleRes > 0) {
            view.subtitle(context.getString(this.subtitleRes));
        } else {
            view.subtitle(this.subtitle);
        }
        view.setOnSnapToPositionListener(this.snapToPositionListener);
        view.showHeader(!TextUtils.isEmpty(title2));
    }

    public int getDefaultLayout() {
        return this.showSmallTitle ? C0716R.layout.view_holder_listing_tray_small_title : C0716R.layout.view_holder_kona_listing_tray;
    }
}
