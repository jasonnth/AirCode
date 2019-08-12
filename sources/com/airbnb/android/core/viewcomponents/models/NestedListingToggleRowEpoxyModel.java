package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.NestedListingToggleRow;
import com.airbnb.p027n2.components.NestedListingToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class NestedListingToggleRowEpoxyModel extends AirEpoxyModel<NestedListingToggleRow> {
    boolean checked;
    boolean enabled = true;
    int imageDrawableRes;
    String imageUrl;
    private boolean isActiveListing;
    OnCheckChangedListener listener;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(NestedListingToggleRow view) {
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
        view.setCheckChangedListener(this.listener);
        view.setChecked(this.checked);
        view.setEnabled(this.enabled);
    }

    public void unbind(NestedListingToggleRow view) {
        view.clearImage();
        super.unbind(view);
        view.setCheckChangedListener(null);
    }

    public AirEpoxyModel<NestedListingToggleRow> reset() {
        this.enabled = true;
        return super.reset();
    }

    public NestedListingToggleRowEpoxyModel isActiveListing(boolean isActiveListing2) {
        this.isActiveListing = isActiveListing2;
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
