package com.airbnb.android.cohosting.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog.Builder;
import android.view.View;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.activities.CohostManagementActivity;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController.UpdateListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.utils.KeyboardUtils;

public abstract class CohostManagementBaseFragment extends AirFragment implements UpdateListener {
    protected CohostManagementDataController controller;

    /* access modifiers changed from: protected */
    public abstract boolean canSaveChanges();

    public void onAttach(Context context) {
        super.onAttach(context);
        this.controller = ((CohostManagementActivity) getActivity()).getCohostManagementDataController();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAirActivity().setOnBackPressedListener(CohostManagementBaseFragment$$Lambda$1.lambdaFactory$(this));
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
        new Builder(getContext(), C5658R.C5663style.Theme_Airbnb_Dialog_Babu).setTitle(C5658R.string.listing_unsaved_changes_dialog_title).setMessage(C5658R.string.listing_unsaved_changes_dialog_message).setPositiveButton(C5658R.string.listing_unsaved_changes_dialog_confirm_button, CohostManagementBaseFragment$$Lambda$2.lambdaFactory$(this)).setNegativeButton(C5658R.string.listing_unsaved_changes_dialog_cancel_button, (OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onUnsavedChangesDiscarded() {
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: protected */
    public long getListingId() {
        return this.controller.getListing().getId();
    }
}
