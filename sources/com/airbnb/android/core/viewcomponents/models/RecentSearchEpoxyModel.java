package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.RecentSearchCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.TextUtil;

public abstract class RecentSearchEpoxyModel extends AirEpoxyModel<RecentSearchCard> {
    OnClickListener clickListener;
    SavedSearch savedSearch;

    public void bind(RecentSearchCard searchCard) {
        super.bind(searchCard);
        Context context = searchCard.getContext();
        searchCard.setMaxWidth(Carousel.getCarouselCardWidth(context, 1));
        searchCard.setLocationText(TextUtil.trimTextToFirstComma(this.savedSearch.getSearchParams().getLocation()));
        StringBuilder sb = new StringBuilder();
        sb.append(this.savedSearch.getSearchParams().getTimeText(context));
        if (this.savedSearch.getSearchParams().getGuests() > 0) {
            sb.append(context.getString(C0716R.string.bullet_with_space));
            sb.append(GuestDetailsPresenter.formatGuestCountLabel(context, this.savedSearch.getSearchParams().getGuestDetails()));
        }
        searchCard.setSubtitleText(sb.toString());
        searchCard.setOnClickListener(this.clickListener);
    }

    public void unbind(RecentSearchCard searchCard) {
        super.unbind(searchCard);
        searchCard.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return -1;
    }
}
