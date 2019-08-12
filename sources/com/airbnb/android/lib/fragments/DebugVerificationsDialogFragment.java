package com.airbnb.android.lib.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.models.verifications.VerificationsState;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.VerificationsRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerificationsActivity;
import p032rx.Observer;

public class DebugVerificationsDialogFragment extends AirDialogFragment {
    public static DebugVerificationsDialogFragment newInstance() {
        return new DebugVerificationsDialogFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(C0880R.layout.dialog_debug_verifications, null);
        ButterKnife.bind(this, view);
        return new Builder(getActivity()).setView(view).setCancelable(true).create();
    }

    @OnClick
    public void startDefault() {
        executeVerificationsRequest(null, VerificationFlow.Onboarding);
    }

    @OnClick
    public void startContactHost() {
        executeListingsRequestForVerifications(VerificationFlow.ContactHost);
    }

    @OnClick
    public void startBooking() {
        executeListingsRequestForVerifications(VerificationFlow.Booking);
    }

    private void executeListingsRequestForVerifications(final VerificationFlow flow) {
        ListingRequest.forListingId(7226004).withListener((Observer) new NonResubscribableRequestListener<ListingResponse>() {
            public void onResponse(ListingResponse response) {
                DebugVerificationsDialogFragment.this.executeVerificationsRequest(response.listing, flow);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(DebugVerificationsDialogFragment.this.getActivity());
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void executeVerificationsRequest(final Listing listing, final VerificationFlow flow) {
        new VerificationsRequest(flow.getRequestFilter(), new NonResubscribableRequestListener<VerificationsResponse>() {
            public void onResponse(VerificationsResponse response) {
                Intent intent;
                VerificationRequirements requirements = response.accountActivationVerifications;
                if (!requirements.isAccountActivationComplete()) {
                    VerificationsState verificationsState = VerificationsState.initialize(requirements.getVerificationGroups().getAccountActivation().getItems());
                    if (listing == null) {
                        intent = VerificationsActivity.intent(DebugVerificationsDialogFragment.this.getActivity(), verificationsState);
                    } else {
                        intent = VerificationsActivity.intentForListing(DebugVerificationsDialogFragment.this.getActivity(), listing, verificationsState, flow);
                    }
                    intent.putExtra(VerificationsActivity.EXTRA_DEBUG_FLAG, true);
                    DebugVerificationsDialogFragment.this.startActivity(intent);
                    DebugVerificationsDialogFragment.this.dismiss();
                    return;
                }
                Toast.makeText(DebugVerificationsDialogFragment.this.getActivity(), "Already activated for this flow", 0).show();
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(DebugVerificationsDialogFragment.this.getActivity());
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }
}
