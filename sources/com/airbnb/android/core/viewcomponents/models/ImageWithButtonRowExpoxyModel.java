package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.components.ImageWithButtonRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ImageWithButtonRowExpoxyModel extends AirEpoxyModel<ImageWithButtonRow> {
    OnClickListener buttonClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    int imageDrawableRes;
    String imageUrl;

    public void bind(ImageWithButtonRow view) {
        boolean z = false;
        Check.state(this.imageDrawableRes == 0 || this.imageUrl == null, "Cannot set both url and drawable resource");
        if (this.buttonTextRes == 0 || this.buttonText == null) {
            z = true;
        }
        Check.state(z, "Cannot set both string resource and text");
        super.bind(view);
        if (this.imageDrawableRes != 0) {
            view.setImageDrawable(this.imageDrawableRes);
        } else if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        }
        if (this.buttonTextRes != 0) {
            view.setButtonText(this.buttonTextRes);
        } else {
            view.setButtonText(this.buttonText);
        }
        view.setButtonOnClickListener(this.buttonClickListener);
    }

    public void unbind(ImageWithButtonRow view) {
        super.unbind(view);
        view.setButtonOnClickListener(null);
    }
}
