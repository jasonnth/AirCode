package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.p027n2.components.ParticipantRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ParticipantRowModel extends AirEpoxyModel<ParticipantRow> {
    OnClickListener imageClickListener;
    String imageUrl;
    CharSequence name;
    boolean removable;
    OnClickListener removeClickListener;

    public ParticipantRowModel(User user) {
        this.name = user.getName();
        this.imageUrl = user.getPictureUrl();
        this.imageClickListener = ParticipantRowModel$$Lambda$1.lambdaFactory$(user);
        mo11716id(user.getId());
    }

    public void bind(ParticipantRow view) {
        super.bind(view);
        view.setRemovable(this.removable);
        view.setNameText(this.name);
        view.setImageUrl(this.imageUrl);
        view.setImageClickListener(this.imageClickListener);
        view.setRemoveClickListener(this.removeClickListener);
    }

    public void unbind(ParticipantRow view) {
        super.bind(view);
        view.setImageClickListener(null);
        view.setRemoveClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
