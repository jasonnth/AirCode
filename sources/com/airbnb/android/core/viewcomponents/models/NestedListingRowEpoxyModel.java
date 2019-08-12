package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.NestedListingRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class NestedListingRowEpoxyModel extends AirEpoxyModel<NestedListingRow> {
    OnClickListener clickListener;
    int imageDrawableRes;
    String imageUrl;
    private boolean isActiveListing;
    int rowDrawableRes;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(NestedListingRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubtitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        if (!this.isActiveListing) {
            view.setTitle((CharSequence) context.getString(C0716R.string.n2_nested_listings_unlisted_title, new Object[]{actualTitle}));
        } else {
            view.setTitle(actualTitle);
        }
        view.setSubtitleText(actualSubtitle);
        if (this.imageDrawableRes != 0) {
            view.setImageDrawable(this.imageDrawableRes);
        } else if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        } else {
            view.clearImage();
        }
        view.setRowDrawable(this.rowDrawableRes);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(NestedListingRow view) {
        view.clearImage();
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public NestedListingRowEpoxyModel disclosure() {
        this.rowDrawableRes = C0716R.C0717drawable.n2_standard_row_right_caret_gray;
        return this;
    }

    public NestedListingRowEpoxyModel isActiveListing(boolean isActiveListing2) {
        this.isActiveListing = isActiveListing2;
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
