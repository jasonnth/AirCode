package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.google.common.base.Strings;

public abstract class UserDetailsActionRowEpoxyModel extends AirEpoxyModel<UserDetailsActionRow> {
    OnClickListener clickListener;
    String extraText;
    int imageDrawableRes;
    String imageUrl;
    String label;
    String subTitle;
    String title;

    public void bind(UserDetailsActionRow row) {
        super.bind(row);
        row.setTitleText(this.title);
        row.setSubtitleText(this.subTitle);
        row.setLabelText(this.label);
        row.setExtraText(this.extraText);
        if (!Strings.isNullOrEmpty(this.imageUrl)) {
            row.setUserImageUrl(this.imageUrl);
        }
        if (this.imageDrawableRes != 0) {
            row.setImageResource(this.imageDrawableRes);
        }
        row.setOnClickListener(this.clickListener);
    }

    public void unbind(UserDetailsActionRow row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
