package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;

public class UserProfileVerificationsFragment_ViewBinding implements Unbinder {
    private UserProfileVerificationsFragment target;
    private View view2131756663;

    public UserProfileVerificationsFragment_ViewBinding(final UserProfileVerificationsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.start_verification_button, "field 'startVerificationsButton' and method 'onStartVerificationClick'");
        target2.startVerificationsButton = (PrimaryButton) Utils.castView(view, C0880R.C0882id.start_verification_button, "field 'startVerificationsButton'", PrimaryButton.class);
        this.view2131756663 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStartVerificationClick();
            }
        });
    }

    public void unbind() {
        UserProfileVerificationsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.startVerificationsButton = null;
        this.view2131756663.setOnClickListener(null);
        this.view2131756663 = null;
    }
}
