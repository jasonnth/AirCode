package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class EditPasswordRegistrationFragment_ViewBinding implements Unbinder {
    private EditPasswordRegistrationFragment target;
    private View view2131755490;

    public EditPasswordRegistrationFragment_ViewBinding(final EditPasswordRegistrationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.editPassword = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_edit_password, "field 'editPassword'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.registration_edit_password_btn_next, "field 'nextButton' and method 'next'");
        target2.nextButton = (AirButton) Utils.castView(view, C1562R.C1564id.registration_edit_password_btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755490 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.next();
            }
        });
    }

    public void unbind() {
        EditPasswordRegistrationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.editPassword = null;
        target2.nextButton = null;
        this.view2131755490.setOnClickListener(null);
        this.view2131755490 = null;
    }
}
