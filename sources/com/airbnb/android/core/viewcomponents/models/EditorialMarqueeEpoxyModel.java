package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.n2.R;
import com.airbnb.p027n2.components.EditorialMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.paris.Paris;

public abstract class EditorialMarqueeEpoxyModel extends AirEpoxyModel<EditorialMarquee> {
    CharSequence description;
    int descriptionTextStyle = R.style.n2_LargeText;
    boolean hideImage = false;
    String imageUrl;
    String kicker;
    int kickerStyle = R.style.n2_SmallText_PlusPlus;
    boolean scrimImage;
    CharSequence title;
    int titleRes;
    int titleTextStyle = R.style.n2_TitleText2;
    int videoPosition;
    String videoUrl;

    public void bind(EditorialMarquee view) {
        super.bind(view);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        view.setDescription(this.description);
        view.setKicker(this.kicker);
        view.setImageUrl(this.imageUrl);
        Paris.style(view).titleTextView().apply(this.titleTextStyle);
        Paris.style(view).descriptionTextView().apply(this.descriptionTextStyle);
        Paris.style(view).kickerTextView().apply(this.kickerStyle);
        view.setVideoUrlWithPosition(this.videoUrl, this.videoPosition);
        view.setScrimEnabled(this.scrimImage);
        view.hideImage(this.hideImage);
    }

    public int getDividerViewType() {
        return 2;
    }
}
