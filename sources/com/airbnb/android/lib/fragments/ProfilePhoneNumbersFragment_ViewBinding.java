package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.lib.views.StickyButton;

public class ProfilePhoneNumbersFragment_ViewBinding implements Unbinder {
    private ProfilePhoneNumbersFragment target;

    public ProfilePhoneNumbersFragment_ViewBinding(ProfilePhoneNumbersFragment target2, View source) {
        this.target = target2;
        target2.mListViewVerifiedNumbers = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_verified_numbers, "field 'mListViewVerifiedNumbers'", LinearListView.class);
        target2.mListViewUnverifiedNumbers = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_unverified_numbers, "field 'mListViewUnverifiedNumbers'", LinearListView.class);
        target2.mBtnAddNumber = (StickyButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_add_number, "field 'mBtnAddNumber'", StickyButton.class);
        target2.mHeaderVerified = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.header_verified, "field 'mHeaderVerified'", TextView.class);
        target2.mHeaderUnverified = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.header_unverified, "field 'mHeaderUnverified'", TextView.class);
        target2.mDividerVerified = Utils.findRequiredView(source, C0880R.C0882id.header_verified_divider, "field 'mDividerVerified'");
        target2.mDividerUnverified = Utils.findRequiredView(source, C0880R.C0882id.header_unverified_divider, "field 'mDividerUnverified'");
    }

    public void unbind() {
        ProfilePhoneNumbersFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mListViewVerifiedNumbers = null;
        target2.mListViewUnverifiedNumbers = null;
        target2.mBtnAddNumber = null;
        target2.mHeaderVerified = null;
        target2.mHeaderUnverified = null;
        target2.mDividerVerified = null;
        target2.mDividerUnverified = null;
    }
}
