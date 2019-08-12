package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

public abstract class SwitchRowEpoxyModel extends AirEpoxyModel<SwitchRow> {
    boolean checked;
    OnCheckedChangeListener checkedChangeListener;
    private final OnCheckedChangeListener checkedChangeListenerWrapper = new OnCheckedChangeListener() {
        public void onCheckedChanged(SwitchRowInterface switchView, boolean isChecked) {
            SwitchRowEpoxyModel.this.checked = isChecked;
            if (SwitchRowEpoxyModel.this.checkedChangeListener != null) {
                SwitchRowEpoxyModel.this.checkedChangeListener.onCheckedChanged(switchView, isChecked);
            }
        }
    };
    int descriptionRes;
    boolean enabled = true;
    SwitchStyle style = SwitchStyle.Filled;
    String title;
    int titleRes;
    boolean updateModelWithCheckedState = true;

    public void bind(SwitchRow row) {
        super.bind(row);
        row.setChecked(this.checked, false);
        if (this.title == null) {
            row.setTitle(this.titleRes);
        } else {
            row.setTitle((CharSequence) this.title);
        }
        row.setDescription(this.descriptionRes);
        row.setEnabled(this.enabled);
        if (this.updateModelWithCheckedState) {
            row.setOnCheckedChangeListener(this.checkedChangeListenerWrapper);
        } else {
            row.setOnCheckedChangeListener(this.checkedChangeListener);
        }
    }

    public void unbind(SwitchRow row) {
        super.unbind(row);
        row.setOnCheckedChangeListener(null);
    }

    public SwitchRowEpoxyModel style(SwitchStyle style2) {
        int layoutRes;
        switch (style2) {
            case Sheet:
                layoutRes = C0716R.layout.view_holder_switch_row_sheet;
                break;
            case Outlined:
                layoutRes = C0716R.layout.view_holder_switch_row_outlined;
                break;
            default:
                layoutRes = C0716R.layout.view_holder_switch_row_filled;
                break;
        }
        layout(layoutRes);
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
