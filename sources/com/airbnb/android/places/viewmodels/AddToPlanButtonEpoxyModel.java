package com.airbnb.android.places.viewmodels;

import android.content.res.Resources;
import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.components.AddToPlanButton;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class AddToPlanButtonEpoxyModel extends AirEpoxyModel<AddToPlanButton> {
    OnClickListener clickListener;
    DisplayOptions displayOptions;
    boolean enabled = true;
    boolean selected;
    int subtitleRes;
    CharSequence subtitleText;
    int titleRes;
    CharSequence titleText;

    public void bind(AddToPlanButton view) {
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view, view.getResources().getDimensionPixelSize(C7627R.dimen.add_to_plans_side_padding));
        }
        Resources resources = view.getResources();
        view.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        view.setOptionalSubtitleText(this.subtitleRes != 0 ? resources.getString(this.subtitleRes) : this.subtitleText);
        view.setOnClickListener(this.clickListener);
        view.setSelected(this.selected);
        view.setEnabled(this.enabled);
    }

    public void unbind(AddToPlanButton view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
