package com.airbnb.android.places.viewmodels;

import com.airbnb.android.core.models.User;
import com.airbnb.android.places.views.LocalPerspectiveView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LocalPerspectiveEpoxyModel extends AirEpoxyModel<LocalPerspectiveView> {
    String description;
    String sectionTitle;
    String tagline;
    User user;

    public void bind(LocalPerspectiveView view) {
        super.bind(view);
        view.setSectionTitle(this.sectionTitle);
        view.setDescription(this.description);
        view.setUserInfo(this.user != null ? this.user.getName() : "", this.tagline, this.user != null ? this.user.getPictureUrl() : "");
    }

    public int getDividerViewType() {
        return 0;
    }
}
