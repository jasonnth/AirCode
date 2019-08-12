package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CheckinNoteTextSettingFragment_ViewBinding implements Unbinder {
    private CheckinNoteTextSettingFragment target;
    private View view2131755497;

    public CheckinNoteTextSettingFragment_ViewBinding(final CheckinNoteTextSettingFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editTextPage = (AirEditTextPageView) Utils.findRequiredViewAsType(source, C7368R.C7370id.edit_text_page, "field 'editTextPage'", AirEditTextPageView.class);
        View view = Utils.findRequiredView(source, C7368R.C7370id.next_btn, "field 'saveButton' and method 'saveClicked'");
        target2.saveButton = (AirButton) Utils.castView(view, C7368R.C7370id.next_btn, "field 'saveButton'", AirButton.class);
        this.view2131755497 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.saveClicked();
            }
        });
    }

    public void unbind() {
        CheckinNoteTextSettingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editTextPage = null;
        target2.saveButton = null;
        this.view2131755497.setOnClickListener(null);
        this.view2131755497 = null;
    }
}
