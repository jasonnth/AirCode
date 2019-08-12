package com.airbnb.android.identity;

import android.content.Context;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.AirToolbar;

public abstract class BaseAccountVerificationFragment extends AirFragment {
    public static final String ARG_VERIFICATION_FLOW = "arg_verification_flow";
    protected AccountVerificationController controller;
    protected final Handler handler = new Handler();

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AccountVerificationController) {
            this.controller = (AccountVerificationController) context;
            return;
        }
        throw new IllegalStateException("BaseAccountVerificationFragment must attach to an AccountVerificationController");
    }

    public void onResume() {
        super.onResume();
        AirToolbar airToolbar = this.controller.getAirToolbar();
        airToolbar.setNavigationIcon(getNavigationIcon());
        airToolbar.setTheme(getAirToolbarTheme());
    }

    public void onDetach() {
        super.onDetach();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* access modifiers changed from: protected */
    public int getNavigationIcon() {
        if (!this.controller.isModal() || !this.controller.isFirstStep()) {
            return 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public int getAirToolbarTheme() {
        return getVerificationFlow().isWhiteBackground() ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    public final void setSheetState(SheetState sheetState) {
        getView().setBackgroundColor(ContextCompat.getColor(getContext(), sheetState.backgroundColor));
        this.controller.setState(sheetState);
    }

    /* access modifiers changed from: protected */
    public final VerificationFlow getVerificationFlow() {
        if (getArguments() != null && getArguments().containsKey("arg_verification_flow")) {
            return (VerificationFlow) getArguments().getSerializable("arg_verification_flow");
        }
        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("getVerificationFlow() called without a passed in param in " + getClass().getCanonicalName()));
        return VerificationFlow.Booking;
    }
}
