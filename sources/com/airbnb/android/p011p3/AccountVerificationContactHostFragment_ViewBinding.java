package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment_ViewBinding */
public class AccountVerificationContactHostFragment_ViewBinding implements Unbinder {
    private AccountVerificationContactHostFragment target;
    private View view2131755494;

    public AccountVerificationContactHostFragment_ViewBinding(final AccountVerificationContactHostFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.subTitleDescTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.subtitle_desc, "field 'subTitleDescTextView'", AirTextView.class);
        View view = Utils.findRequiredView(source, C7532R.C7534id.provide_id_button, "method 'onProvideIdClick'");
        this.view2131755494 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onProvideIdClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationContactHostFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.subTitleDescTextView = null;
        this.view2131755494.setOnClickListener(null);
        this.view2131755494 = null;
    }
}
