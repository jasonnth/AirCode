package com.airbnb.android.managelisting.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog.Builder;
import android.view.View;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;

public abstract class ManageListingBaseFragment extends AirFragment implements UpdateListener {
    protected ManageListingDataController controller;

    /* access modifiers changed from: protected */
    public abstract boolean canSaveChanges();

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = ((DlsManageListingActivity) getActivity()).getManageListingDataController();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAirActivity().setOnBackPressedListener(ManageListingBaseFragment$$Lambda$1.lambdaFactory$(this));
    }

    public void onResume() {
        super.onResume();
        this.controller.addListener(this);
    }

    public void onPause() {
        super.onPause();
        this.controller.removeListener(this);
    }

    public void onDestroyView() {
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
        getAirActivity().setOnBackPressedListener(null);
        super.onDestroyView();
    }

    public void onDetach() {
        super.onDetach();
        this.controller = null;
    }

    public void dataLoading(boolean loading) {
    }

    public void dataUpdated() {
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (!canSaveChanges()) {
            return false;
        }
        showBackButtonDialog();
        return true;
    }

    /* access modifiers changed from: protected */
    public void showBackButtonDialog() {
        new Builder(getContext(), C7368R.C7373style.Theme_Airbnb_Dialog_Babu).setTitle(C7368R.string.listing_unsaved_changes_dialog_title).setMessage(C7368R.string.listing_unsaved_changes_dialog_message).setPositiveButton(C7368R.string.listing_unsaved_changes_dialog_confirm_button, ManageListingBaseFragment$$Lambda$2.lambdaFactory$(this)).setNegativeButton(C7368R.string.listing_unsaved_changes_dialog_cancel_button, (OnClickListener) null).show();
    }

    /* access modifiers changed from: protected */
    public void onUnsavedChangesDiscarded() {
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: protected */
    public long getListingId() {
        return this.controller.getListing().getId();
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11638kv("listing_id", this.controller.getListingId());
    }
}
