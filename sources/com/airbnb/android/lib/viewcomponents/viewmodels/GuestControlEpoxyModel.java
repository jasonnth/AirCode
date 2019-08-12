package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.lib.views.GuestControlToggleView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class GuestControlEpoxyModel extends AirEpoxyModel<GuestControlToggleView> {
    Boolean isAllowed;
    OnClickListener noClickListener;
    GuestControlType type;
    OnClickListener yesClickListener;

    public void bind(GuestControlToggleView view) {
        super.bind(view);
        view.setTitle(this.type.getLabelId());
        view.setSelection(this.isAllowed);
        view.setYesButtonClickListener(this.yesClickListener);
        view.setNoButtonClickListener(this.noClickListener);
    }

    public void unbind(GuestControlToggleView view) {
        super.unbind(view);
        view.setYesButtonClickListener(null);
        view.setNoButtonClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
