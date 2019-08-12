package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.NestedListingEditRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class NestedListingEditRowEpoxyModel extends AirEpoxyModel<NestedListingEditRow> {
    private static final int EDIT_ACTION_TEXT = C0716R.string.edit;
    OnClickListener clickListener;
    int imageDrawableRes;
    String imageUrl;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(NestedListingEditRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence titleText = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence subtitleText = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        view.setTitle(titleText);
        view.setSubtitleText(subtitleText);
        view.setActionText(context.getString(EDIT_ACTION_TEXT));
        if (this.imageDrawableRes != 0) {
            view.setImageDrawable(this.imageDrawableRes);
        } else if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        } else {
            view.clearImage();
        }
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(NestedListingEditRow view) {
        super.unbind(view);
        view.setImageUrl(null);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
