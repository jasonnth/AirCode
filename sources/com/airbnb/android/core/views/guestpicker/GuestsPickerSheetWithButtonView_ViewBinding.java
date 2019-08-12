package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class GuestsPickerSheetWithButtonView_ViewBinding implements Unbinder {
    private GuestsPickerSheetWithButtonView target;
    private View view2131755523;

    public GuestsPickerSheetWithButtonView_ViewBinding(GuestsPickerSheetWithButtonView target2) {
        this(target2, target2);
    }

    public GuestsPickerSheetWithButtonView_ViewBinding(final GuestsPickerSheetWithButtonView target2, View source) {
        this.target = target2;
        target2.guestsPickerView = (GuestsPickerView) Utils.findRequiredViewAsType(source, C0716R.C0718id.base_guests_picker, "field 'guestsPickerView'", GuestsPickerView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.save_button, "field 'saveButton' and method 'saveSelection'");
        target2.saveButton = (FixedActionFooter) Utils.castView(view, C0716R.C0718id.save_button, "field 'saveButton'", FixedActionFooter.class);
        this.view2131755523 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.saveSelection();
            }
        });
        target2.infantDescriptionText = (TextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.infant_description_text, "field 'infantDescriptionText'", TextView.class);
    }

    public void unbind() {
        GuestsPickerSheetWithButtonView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.guestsPickerView = null;
        target2.saveButton = null;
        target2.infantDescriptionText = null;
        this.view2131755523.setOnClickListener(null);
        this.view2131755523 = null;
    }
}
