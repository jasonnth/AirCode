package com.airbnb.android.core.viewcomponents.models;

import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.ImpactDisplayCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ImpactDisplayCardEpoxyModel extends AirEpoxyModel<ImpactDisplayCard> {
    int backgroundColor;
    OnClickListener clickListener;
    String imageUrl;
    int labelRes;
    boolean loading;
    int style = 1;
    CharSequence subtitle;
    CharSequence title;

    public void bind(ImpactDisplayCard card) {
        OnClickListener onClickListener = null;
        super.bind(card);
        card.setStyle(this.style);
        card.setTitleText(this.loading ? null : this.title);
        card.setSubtitleText(this.loading ? null : this.subtitle);
        card.setScrimForText(!this.loading && (!TextUtils.isEmpty(this.title) || !TextUtils.isEmpty(this.subtitle)));
        if (this.loading || this.labelRes == 0) {
            card.setLabelText((CharSequence) null);
        } else {
            card.setLabelText(this.labelRes);
        }
        card.setImageUrl(this.loading ? null : this.imageUrl);
        if (this.loading) {
            card.setBackgroundColor(ContextCompat.getColor(card.getContext(), C0716R.color.n2_loading_background));
        } else if (this.backgroundColor != 0) {
            card.setBackgroundColor(this.backgroundColor);
        } else {
            card.setBackground(null);
        }
        if (!this.loading) {
            onClickListener = this.clickListener;
        }
        card.setOnClickListener(onClickListener);
    }

    public void unbind(ImpactDisplayCard card) {
        super.unbind(card);
        card.clearImage();
        card.setOnClickListener(null);
    }

    public ImpactDisplayCardEpoxyModel style(int style2) {
        this.style = style2;
        switch (style2) {
            case 1:
                layout(getDefaultLayout());
                break;
            case 2:
            case 3:
            case 4:
                layout(C0716R.layout.view_holder_impact_display_card_carousel);
                break;
            case 5:
                layout(C0716R.layout.view_holder_impact_display_card_grid);
                break;
        }
        return this;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.style == 3 || this.style == 4) {
            return totalSpanCount;
        }
        return 1;
    }

    public int getDividerViewType() {
        return 0;
    }
}
