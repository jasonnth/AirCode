package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.util.Rfc822Tokenizer;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.ReservationUser;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateReservationUserRequest;
import com.airbnb.android.core.requests.DeleteReservationUserRequest;
import com.airbnb.android.core.requests.ReservationUsersRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ReservationUserResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.ReservationUserAdapter;
import com.airbnb.android.lib.analytics.ShareItineraryAnalytics;
import com.airbnb.android.lib.views.LoaderRecyclerView;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.android.p305ex.chips.BaseRecipientAdapter;
import com.android.p305ex.chips.RecipientEditTextView;
import com.android.p305ex.chips.recipientchip.DrawableRecipientChip;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class ShareItineraryFragment extends ZenDialog {
    private static final String BUTTON_ENABLED = "button_enabled";
    private static final int DIALOG_REQ_DELETE_CONFIRM = 5530;
    private static final int DIALOG_REQ_FINISH = 9422;
    private static final int DIALOG_REQ_SEND = 9421;
    private static final int DONE_DIALOG_DISPLAY_MS = 1500;
    private static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    private static final String EXTRA_NEXT_INTENT = "next_intent";
    private static final String RESERVATION_USERS = "reservation_users";
    /* access modifiers changed from: private */
    public final ReservationUserAdapter adapter = new ReservationUserAdapter(ShareItineraryFragment$$Lambda$1.lambdaFactory$(this));
    private String confirmationCode;
    @BindView
    View footer;
    @State
    boolean hasLoadedReservationUsers = false;
    @BindView
    TextView infoHeaderSubtext;
    @BindView
    TextView infoHeaderText;
    @BindView
    LoaderRecyclerView loaderRecyclerView;
    @BindView
    RecipientEditTextView recipEditText;
    @BindView
    AirButton sendButton;
    @BindView
    AirTextView skipButton;

    public static ShareItineraryFragment newInstance(Context context, String confirmationCode2, Intent intent, String trackingSource) {
        if (trackingSource != null) {
            ShareItineraryAnalytics.setSource(context, trackingSource, confirmationCode2);
        }
        return (ShareItineraryFragment) new ZenBuilder(new ShareItineraryFragment()).withArguments(makeBundle(confirmationCode2, intent)).withCustomLayout().create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setCustomView(inflater.inflate(C0880R.layout.fragment_share_itinerary, container, false));
        ButterKnife.bind(this, view);
        this.confirmationCode = getArguments().getString("confirmation_code");
        RecyclerView recyclerView = this.loaderRecyclerView.getRecyclerView();
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        if (savedInstanceState != null) {
            this.sendButton.setEnabled(savedInstanceState.getBoolean(BUTTON_ENABLED));
        }
        if (this.hasLoadedReservationUsers) {
            List<ReservationUser> users = savedInstanceState.getParcelableArrayList(RESERVATION_USERS);
            if (users != null) {
                this.adapter.setReservationUsers(users);
                this.loaderRecyclerView.finishLoaderImmediate();
            } else {
                loadReservationUsers(true);
            }
        } else {
            loadReservationUsers(true);
        }
        this.recipEditText.setTokenizer(new Rfc822Tokenizer());
        this.recipEditText.setAdapter(new BaseRecipientAdapter(getActivity()));
        this.recipEditText.setMaxLines(10);
        this.recipEditText.setSingleLine(false);
        return view;
    }

    private static Bundle makeBundle(String confirmationCode2, Intent intent) {
        return ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("confirmation_code", confirmationCode2)).putParcelable(EXTRA_NEXT_INTENT, intent)).toBundle();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.hasLoadedReservationUsers) {
            outState.putParcelableArrayList(RESERVATION_USERS, this.adapter.getReservationUsers());
        }
        outState.putBoolean(BUTTON_ENABLED, this.sendButton.isEnabled());
    }

    /* access modifiers changed from: private */
    public void loadReservationUsers(boolean showLoader) {
        new ReservationUsersRequest(getArguments().getString("confirmation_code"), new NonResubscribableRequestListener<ReservationUserResponse>() {
            public void onResponse(ReservationUserResponse response) {
                List<ReservationUser> users = response.reservationUsers;
                ShareItineraryFragment.this.skipButton.setText(users.size() > 0 ? C0880R.string.done : C0880R.string.action_skip);
                ShareItineraryFragment.this.adapter.setReservationUsers(users);
                ShareItineraryFragment.this.hasLoadedReservationUsers = true;
                ShareItineraryFragment.this.loaderRecyclerView.getLoaderFrame().finish();
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(ShareItineraryFragment.this.getActivity());
                ShareItineraryFragment.this.loaderRecyclerView.getLoaderFrame().finish();
            }
        }).execute(this.requestManager);
        if (showLoader) {
            this.loaderRecyclerView.getLoaderFrame().startAnimation();
        }
    }

    private void deleteReservationUser(long reservationUserId) {
        if (reservationUserId <= 0) {
            BugsnagWrapper.notify(new Throwable("attempting to delete an invalid reservation user id" + reservationUserId));
        }
        new DeleteReservationUserRequest(reservationUserId, new NonResubscribableRequestListener<Object>() {
            public void onResponse(Object response) {
                if (ShareItineraryFragment.this.getActivity() != null) {
                    ShareItineraryFragment.this.loadReservationUsers(true);
                }
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(ShareItineraryFragment.this.getActivity());
                if (e.statusCode() == 404) {
                    ShareItineraryFragment.this.loadReservationUsers(false);
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: 0000 */
    @OnFocusChange
    public void onFocusChange(boolean focused) {
        this.sendButton.setEnabled(true);
        if (focused) {
            toggleAddGuestsMode(true);
        } else {
            KeyboardUtils.dismissSoftKeyboard((View) this.recipEditText);
        }
    }

    /* access modifiers changed from: private */
    public void toggleAddGuestsMode(boolean showAddGuestsMode) {
        int i;
        int i2 = 8;
        if (!MiscUtils.isTabletScreen(getContext())) {
            TextView textView = this.infoHeaderText;
            if (showAddGuestsMode) {
                i = 8;
            } else {
                i = 0;
            }
            textView.setVisibility(i);
            TextView textView2 = this.infoHeaderSubtext;
            if (!showAddGuestsMode) {
                i2 = 0;
            }
            textView2.setVisibility(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void finish() {
        ShareItineraryAnalytics.trackSkip();
        if (getValidChips().isEmpty()) {
            dismiss();
        } else {
            ZenDialog.builder().withBodyText(C0880R.string.share_itinerary_confirm_exit).withDualButton(C0880R.string.cancel, 0, C0880R.string.exit, (int) DIALOG_REQ_FINISH, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void promptConfirmSendInvites() {
        this.recipEditText.onEditorAction(6);
        if (!getValidChips().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(C0880R.string.share_itinerary_invite_confirm));
            for (DrawableRecipientChip chip : getValidChips()) {
                sb.append("\n");
                sb.append(chip.getValue());
            }
            ZenDialog.builder().withBodyText(sb.toString()).withDualButton(C0880R.string.cancel, 0, C0880R.string.send, (int) DIALOG_REQ_SEND, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_SEND) {
            sendInvites();
        } else if (requestCode == DIALOG_REQ_FINISH) {
            dismiss();
        } else if (requestCode == DIALOG_REQ_DELETE_CONFIRM) {
            deleteReservationUser(data.getLongExtra(DeleteReservationUserDialog.KEY_RESERVATION_USER_ID, 0));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Intent intent = (Intent) getArguments().getParcelable(EXTRA_NEXT_INTENT);
        if (intent != null && getActivity() != null) {
            startActivity(intent);
            getActivity().finish();
        }
    }

    private void sendInvites() {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        for (DrawableRecipientChip chip : getValidChips()) {
            requests.add(new CreateReservationUserRequest(chip.getDisplay().toString(), chip.getValue().toString(), this.confirmationCode, true, "", null));
        }
        ShareItineraryAnalytics.trackSend(getValidChips().size(), true, false);
        final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.sending, 0);
        progressDialog.setProgressDialogListener(new ProgressDialogListener() {
            public void onProgressCompleted() {
                ShareItineraryFragment.this.getActivity().setResult(-1);
                ShareItineraryFragment.this.loadReservationUsers(false);
                ShareItineraryFragment.this.toggleAddGuestsMode(false);
            }

            public void onProgressCancelled() {
                ShareItineraryFragment.this.toggleAddGuestsMode(false);
            }
        });
        progressDialog.show(getFragmentManager(), (String) null);
        new AirBatchRequest(requests, false, (NonResubscribableRequestListener<AirBatchResponse>) new NonResubscribableRequestListener<AirBatchResponse>() {
            public void onResponse(AirBatchResponse response) {
                if (ShareItineraryFragment.this.getActivity() != null) {
                    progressDialog.onProgressComplete(C0880R.string.referrals_show_referrals_sent, 0, C0880R.C0881drawable.icon_complete, 1500);
                    ShareItineraryFragment.this.recipEditText.setText("");
                }
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(ShareItineraryFragment.this.getActivity());
                progressDialog.dismissAllowingStateLoss();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    private List<DrawableRecipientChip> getValidChips() {
        DrawableRecipientChip[] recipients;
        ArrayList<DrawableRecipientChip> chips = new ArrayList<>();
        for (DrawableRecipientChip chip : this.recipEditText.getRecipients()) {
            if (Patterns.EMAIL_ADDRESS.matcher(chip.getValue()).matches()) {
                chips.add(chip);
            }
        }
        return chips;
    }
}
