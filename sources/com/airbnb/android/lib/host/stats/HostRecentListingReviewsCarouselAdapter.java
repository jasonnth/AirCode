package com.airbnb.android.lib.host.stats;

import android.view.View;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Collection;
import java.util.List;

public class HostRecentListingReviewsCarouselAdapter extends AirEpoxyAdapter {
    private final CarouselItemClickListener carouselClickListener;

    public interface CarouselItemClickListener {
        void onCarouselItemClicked(View view, Listing listing, int i);
    }

    public HostRecentListingReviewsCarouselAdapter(CarouselItemClickListener carouselClickListener2) {
        this.carouselClickListener = carouselClickListener2;
    }

    public void setItems(List<Listing> listings) {
        this.models.clear();
        if (!ListUtils.isEmpty((Collection<?>) listings)) {
            int index = 0;
            for (Listing data : listings) {
                int index2 = index + 1;
                this.models.add(buildModel(data, index));
                index = index2;
            }
        }
        notifyDataSetChanged();
    }

    private EpoxyModel<?> buildModel(Listing listing, int index) {
        return new ListingReviewScoreCardEpoxyModel_(listing, index).carouselClickListener(this.carouselClickListener).m6035id(listing.getId());
    }
}
