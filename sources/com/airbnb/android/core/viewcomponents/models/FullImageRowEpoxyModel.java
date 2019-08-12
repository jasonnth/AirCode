package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.components.FullImageRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FullImageRowEpoxyModel extends AirEpoxyModel<FullImageRow> {
    int imageDrawableRes;
    String imageUrl;
    OnClickListener onClickListener;

    public void bind(FullImageRow view) {
        Check.state(this.imageDrawableRes == 0 || this.imageUrl == null, "Cannot set both url and drawable resource");
        super.bind(view);
        if (this.imageDrawableRes != 0) {
            view.setImageDrawable(this.imageDrawableRes);
        } else if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        }
        if (this.onClickListener != null) {
            view.setOnClickListener(this.onClickListener);
        }
    }

    public void unbind(FullImageRow view) {
        super.unbind(view);
    }
}
