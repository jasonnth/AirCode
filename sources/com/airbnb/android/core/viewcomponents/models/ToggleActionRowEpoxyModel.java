package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ToggleActionRowEpoxyModel extends AirEpoxyModel<ToggleActionRow> {
    boolean checked;
    OnCheckedChangeListener checkedChangedlistener;
    OnClickListener clickListener;
    boolean enabled = true;
    CharSequence label;
    int labelRes;
    boolean radio;
    boolean readOnly;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(ToggleActionRow view) {
        super.bind(view);
        if (this.title != null) {
            view.setTitle(this.title);
        }
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        }
        if (this.title == null && this.titleRes == 0) {
            view.setTitle((CharSequence) null);
        }
        if (this.subtitle != null) {
            view.setSubtitle(this.subtitle);
        }
        if (this.subtitleRes != 0) {
            view.setSubtitle(this.subtitleRes);
        }
        if (this.subtitle == null && this.subtitleRes == 0) {
            view.setSubtitle((CharSequence) null);
        }
        if (this.label != null) {
            view.setLabel(this.label);
        }
        if (this.labelRes != 0) {
            view.setLabel(this.labelRes);
        }
        if (this.label == null && this.labelRes == 0) {
            view.setLabel((CharSequence) null);
        }
        view.radio(this.radio);
        view.readOnly(this.readOnly);
        view.setChecked(this.checked);
        view.setEnabled(this.enabled);
        view.setOnCheckedChangeListener(this.checkedChangedlistener);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(ToggleActionRow view) {
        super.unbind(view);
        view.setOnCheckedChangeListener(null);
        view.setOnClickListener(null);
    }

    public AirEpoxyModel<ToggleActionRow> reset() {
        this.enabled = true;
        return super.reset();
    }

    public int getDividerViewType() {
        return 0;
    }
}
