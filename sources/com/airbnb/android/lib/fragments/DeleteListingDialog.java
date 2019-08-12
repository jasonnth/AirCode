package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;

public class DeleteListingDialog extends ZenDialog {
    private CheckBox mConfirmDeleteCheck;

    public static DeleteListingDialog newInstance(int requestCodeToConfirmDelete, Fragment targetFragment) {
        return (DeleteListingDialog) new ZenBuilder(new DeleteListingDialog()).withTitle(C0880R.string.ml_delete_listing).withCustomLayout().withDualButton(C0880R.string.cancel, 0, C0880R.string.deactivate, requestCodeToConfirmDelete, targetFragment).create();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        if (this.mConfirmDeleteCheck.isChecked()) {
            super.clickRightButton(requestCodeRight);
        } else {
            Toast.makeText(getActivity(), C0880R.string.ml_delete_listing_confirm_toast, 0).show();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.confirm_delete_frame, container, false);
        this.mConfirmDeleteCheck = (CheckBox) ButterKnife.findById(frame, C0880R.C0882id.checkbox);
        setCustomView(frame);
        return view;
    }
}
