package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.MarketingCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class MarketingCardEpoxyModel extends AirEpoxyModel<MarketingCard> {
    CharSequence action;
    OnClickListener actionClickListener;
    int actionRes;
    Drawable image;
    int imageRes;
    String imageUrl;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(MarketingCard view) {
        super.bind(view);
        Context context = view.getContext();
        view.setTitle(this.titleRes != 0 ? context.getString(this.titleRes) : this.title);
        view.setSubtitle(this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle);
        view.setAction(this.actionRes != 0 ? context.getString(this.actionRes) : this.action);
        if (!TextUtils.isEmpty(this.imageUrl)) {
            view.setImageUrl(this.imageUrl);
        }
        if (this.imageRes != 0) {
            view.setImage(this.imageRes);
        }
        if (this.image != null) {
            view.setImage(this.image);
        }
        view.setActionOnClickListener(this.actionClickListener);
    }

    public void unbind(MarketingCard view) {
        view.setActionOnClickListener(null);
        super.unbind(view);
    }

    public MarketingCardEpoxyModel title(CharSequence title2) {
        this.titleRes = 0;
        this.title = title2;
        return this;
    }

    public MarketingCardEpoxyModel title(int titleRes2) {
        this.titleRes = titleRes2;
        return this;
    }

    public MarketingCardEpoxyModel subtitle(CharSequence subtitle2) {
        this.subtitleRes = 0;
        this.subtitle = subtitle2;
        return this;
    }

    public MarketingCardEpoxyModel subtitle(int subtitleRes2) {
        this.subtitleRes = subtitleRes2;
        return this;
    }

    public MarketingCardEpoxyModel action(CharSequence action2) {
        this.subtitleRes = 0;
        this.action = action2;
        return this;
    }

    public MarketingCardEpoxyModel action(int actionRes2) {
        this.actionRes = actionRes2;
        return this;
    }

    public MarketingCardEpoxyModel image(int imageRes2) {
        this.imageRes = imageRes2;
        this.image = null;
        this.imageUrl = null;
        return this;
    }

    public MarketingCardEpoxyModel image(Drawable image2) {
        this.imageRes = 0;
        this.image = image2;
        this.imageUrl = null;
        return this;
    }

    public MarketingCardEpoxyModel imageUrl(String imageUrl2) {
        this.imageRes = 0;
        this.image = null;
        this.imageUrl = imageUrl2;
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
