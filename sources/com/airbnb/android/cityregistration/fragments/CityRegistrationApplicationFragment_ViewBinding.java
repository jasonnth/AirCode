package com.airbnb.android.cityregistration.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;

public class CityRegistrationApplicationFragment_ViewBinding extends CityRegistrationBaseSubmissionFragment_ViewBinding {
    private CityRegistrationApplicationFragment target;
    private View view2131755475;

    public CityRegistrationApplicationFragment_ViewBinding(final CityRegistrationApplicationFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C5630R.C5632id.save_button, "method 'onSubmit'");
        this.view2131755475 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmit();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755475.setOnClickListener(null);
        this.view2131755475 = null;
        super.unbind();
    }
}
