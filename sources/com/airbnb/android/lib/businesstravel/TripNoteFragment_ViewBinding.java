package com.airbnb.android.lib.businesstravel;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class TripNoteFragment_ViewBinding implements Unbinder {
    private TripNoteFragment target;
    private View view2131756164;

    public TripNoteFragment_ViewBinding(final TripNoteFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.save_button, "field 'saveButton' and method 'onClickSaveTripNote'");
        target2.saveButton = (AirButton) Utils.castView(view, C0880R.C0882id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131756164 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSaveTripNote();
            }
        });
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editText'", AirEditTextView.class);
    }

    public void unbind() {
        TripNoteFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.saveButton = null;
        target2.editText = null;
        this.view2131756164.setOnClickListener(null);
        this.view2131756164 = null;
    }
}
