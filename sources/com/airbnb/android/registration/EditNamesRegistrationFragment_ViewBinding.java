package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class EditNamesRegistrationFragment_ViewBinding implements Unbinder {
    private EditNamesRegistrationFragment target;
    private View view2131755537;

    public EditNamesRegistrationFragment_ViewBinding(final EditNamesRegistrationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.nameSheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_name_sheet_header, "field 'nameSheetMarquee'", SheetMarquee.class);
        target2.editFirstName = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_edit_first_name, "field 'editFirstName'", SheetInputText.class);
        target2.editLastName = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_edit_last_name, "field 'editLastName'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.registration_edit_names_btn_next, "field 'nextButton' and method 'next'");
        target2.nextButton = (AirButton) Utils.castView(view, C1562R.C1564id.registration_edit_names_btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755537 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.next();
            }
        });
    }

    public void unbind() {
        EditNamesRegistrationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.nameSheetMarquee = null;
        target2.editFirstName = null;
        target2.editLastName = null;
        target2.nextButton = null;
        this.view2131755537.setOnClickListener(null);
        this.view2131755537 = null;
    }
}
