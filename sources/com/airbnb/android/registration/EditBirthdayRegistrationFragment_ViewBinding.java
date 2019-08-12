package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class EditBirthdayRegistrationFragment_ViewBinding implements Unbinder {
    private EditBirthdayRegistrationFragment target;
    private View view2131755480;
    private View view2131755488;

    public EditBirthdayRegistrationFragment_ViewBinding(final EditBirthdayRegistrationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.birthday_input_field_text, "field 'editBirthday' and method 'launchBirthdayPicker'");
        target2.editBirthday = (SheetInputText) Utils.castView(view, C1562R.C1564id.birthday_input_field_text, "field 'editBirthday'", SheetInputText.class);
        this.view2131755480 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchBirthdayPicker();
            }
        });
        View view2 = Utils.findRequiredView(source, C1562R.C1564id.registration_edit_birthday_btn_next, "field 'nextButton' and method 'next'");
        target2.nextButton = (AirButton) Utils.castView(view2, C1562R.C1564id.registration_edit_birthday_btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755488 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.next();
            }
        });
    }

    public void unbind() {
        EditBirthdayRegistrationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.editBirthday = null;
        target2.nextButton = null;
        this.view2131755480.setOnClickListener(null);
        this.view2131755480 = null;
        this.view2131755488.setOnClickListener(null);
        this.view2131755488 = null;
    }
}
