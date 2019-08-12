package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Banner;
import com.airbnb.p027n2.collections.BannerContainer;
import com.airbnb.p027n2.collections.BannerContainer.BannerClickListener;
import com.airbnb.p027n2.collections.BannerContainer.BannerItem;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.List;

public abstract class BannerContainerEpoxyModel extends AirEpoxyModel<BannerContainer> {
    BannerClickListener bannerClickListener;
    List<Banner> banners;

    public void bind(BannerContainer view) {
        super.bind(view);
        view.setCarouselItems(FluentIterable.from((Iterable<E>) this.banners).transform(BannerContainerEpoxyModel$$Lambda$1.lambdaFactory$(this)).toList());
        view.setBannerClickListener(this.bannerClickListener);
    }

    /* access modifiers changed from: private */
    public BannerItem getBannerItem(Banner banner) {
        if (FeatureToggles.areVideosEnabledOnPlaylists()) {
            return new BannerItem(null, "https://a0.muscache.com/v/73/c3/73c3f370-34c1-4ff4-bafa-85c4d4a6d324/1ec3292f55e05744bef53264af590cb1_800k_2.mp4", banner.getTitle(), banner.getSubText());
        }
        return new BannerItem(banner.getBannerPicture() != null ? banner.getBannerPicture().getLargeRo() : "", null, banner.getTitle(), banner.getSubText());
    }

    public void unbind(BannerContainer view) {
        super.unbind(view);
        view.setBannerClickListener(null);
    }

    public int getDividerViewType() {
        return 4;
    }
}
