package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.TriStateSwitchRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.TriStateSwitch.TriStateSwitchStyle;

public abstract class TriStateSwitchRowEpoxyModel extends AirEpoxyModel<TriStateSwitchRow> {
    OnCheckedChangeListener checkedChangeListener;
    String description;
    boolean enabled = true;
    TriStateSwitchStyle style = TriStateSwitchStyle.Outlined;
    String title;
    int titleRes;
    ToggleState toggleState = ToggleState.NEITHER;

    public void bind(TriStateSwitchRow row) {
        super.bind(row);
        if (this.title != null) {
            row.setTitle((CharSequence) this.title);
        }
        if (this.titleRes != 0) {
            row.setTitle(this.titleRes);
        }
        row.setDescription(this.description);
        row.setOnCheckedChangeListener(TriStateSwitchRowEpoxyModel$$Lambda$1.lambdaFactory$(this));
        row.setState(this.toggleState);
        row.setEnabled(this.enabled);
    }

    public void unbind(TriStateSwitchRow row) {
        super.unbind(row);
        row.setOnCheckedChangeListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }

    public TriStateSwitchRowEpoxyModel style(TriStateSwitchStyle style2) {
        int layoutRes;
        switch (style2) {
            case Sheet:
                layoutRes = C0716R.layout.view_holder_tri_state_switch_row_sheet;
                break;
            default:
                layoutRes = C0716R.layout.view_holder_tri_state_switch_row_outlined;
                break;
        }
        layout(layoutRes);
        return this;
    }

    /* access modifiers changed from: private */
    public void checkedChangedListenerWrapper(TriStateSwitch switchView, ToggleState toggleState2) {
        this.toggleState = toggleState2;
        if (this.checkedChangeListener != null) {
            this.checkedChangeListener.onCheckedChanged(switchView, toggleState2);
        }
    }
}
