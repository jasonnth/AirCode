package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LinearListView;

public class VerificationsFragment_ViewBinding implements Unbinder {
    private VerificationsFragment target;
    private View view2131756867;

    public VerificationsFragment_ViewBinding(final VerificationsFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_verify_your_identity, "field 'mVerifyYourIdentity' and method 'onClickVerifyYourIdentity'");
        target2.mVerifyYourIdentity = (TextView) Utils.castView(view, C0880R.C0882id.btn_verify_your_identity, "field 'mVerifyYourIdentity'", TextView.class);
        this.view2131756867 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickVerifyYourIdentity();
            }
        });
        target2.mVerifyYourIdentityInfo = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.no_verifications_text_view, "field 'mVerifyYourIdentityInfo'", TextView.class);
        target2.mVerificationsListView = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.verifications_list, "field 'mVerificationsListView'", LinearListView.class);
        target2.mNoVerificationsText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_verify_your_identity_info, "field 'mNoVerificationsText'", TextView.class);
    }

    public void unbind() {
        VerificationsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mVerifyYourIdentity = null;
        target2.mVerifyYourIdentityInfo = null;
        target2.mVerificationsListView = null;
        target2.mNoVerificationsText = null;
        this.view2131756867.setOnClickListener(null);
        this.view2131756867 = null;
    }
}
