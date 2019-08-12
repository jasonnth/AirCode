package com.airbnb.android.lib.china5a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.china5a.BaseVerificationPresenter;

public abstract class BaseVerificationFragment<P extends BaseVerificationPresenter<?, ?>> extends AirFragment {
    private static final int RC_CANCEL_EXIT = 1001;
    private static final int RC_CONFIRM_EXIT = 1002;
    /* access modifiers changed from: protected */
    public P presenter;

    /* access modifiers changed from: protected */
    public abstract P createPresenter(FiveAxiomRepository fiveAxiomRepository);

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.presenter = createPresenter(((FiveAxiomActivity) getActivity()).getRepository());
        this.presenter.start();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAirActivity().setOnBackPressedListener(BaseVerificationFragment$$Lambda$1.lambdaFactory$(this));
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.presenter.stop();
        getAirActivity().setOnBackPressedListener(null);
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        FiveAxiomAnalytics.trackClick("exit_dialog");
        ZenDialog.builder().withTitle(this.resourceManager.getString(C0880R.string.china_account_verification_exit_dialog_body)).withDualButton(C0880R.string.china_account_verification_exit_dialog_secondary_action, 1002, C0880R.string.china_account_verification_exit_dialog_primary_action, 1001).create().show(getFragmentManager(), (String) null);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002) {
            this.presenter.cancel();
            FiveAxiomAnalytics.trackClick("exit_confirm");
        } else if (requestCode == 1001) {
            FiveAxiomAnalytics.trackClick("exit_cancel");
        }
    }
}
