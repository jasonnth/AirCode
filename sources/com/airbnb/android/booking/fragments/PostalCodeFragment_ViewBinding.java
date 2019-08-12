package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class PostalCodeFragment_ViewBinding implements Unbinder {
    private PostalCodeFragment target;
    private View view2131755474;

    public PostalCodeFragment_ViewBinding(final PostalCodeFragment target2, View source) {
        this.target = target2;
        target2.sheetInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0704R.C0706id.postal_code_sheetInput, "field 'sheetInput'", SheetInputText.class);
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0704R.C0706id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.next_button, "method 'onNextButtonClick'");
        this.view2131755474 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClick();
            }
        });
    }

    public void unbind() {
        PostalCodeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetInput = null;
        target2.jellyfishView = null;
        this.view2131755474.setOnClickListener(null);
        this.view2131755474 = null;
    }
}
