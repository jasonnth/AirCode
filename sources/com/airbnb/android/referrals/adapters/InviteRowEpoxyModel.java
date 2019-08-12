package com.airbnb.android.referrals.adapters;

import android.content.res.Resources;
import android.net.Uri;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InviteRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InviteRowEpoxyModel extends AirEpoxyModel<InviteRow> {
    int buttonRes;
    CharSequence buttonText;
    OnClickListener clickListener;
    int descriptionRes;
    CharSequence descriptionText;
    String photoUri;
    int titleRes;
    CharSequence titleText;

    public void bind(InviteRow view) {
        super.bind(view);
        Resources resources = view.getResources();
        if (this.photoUri != null) {
            view.setPhoto(Uri.parse(this.photoUri));
        }
        view.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        view.setDescriptionText(this.descriptionRes != 0 ? resources.getString(this.descriptionRes) : this.descriptionText);
        view.setButtonText((CharSequence) this.buttonRes != 0 ? resources.getString(this.buttonRes) : this.buttonText.toString());
        view.setButtonClickListener(this.clickListener);
    }

    public void unbind(InviteRow view) {
        super.unbind(view);
        view.setButtonClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
