package com.airbnb.android.cohosting.fragments;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.app.AlertDialog.Builder;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController.Listener;
import com.airbnb.android.cohosting.shared.CohostingPaymentSettings;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.cohosting.utils.CohostingLoggingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.SendCohostInvitationRequest;
import com.airbnb.android.core.responses.SendCohostInvitationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class CohostingInviteFriendWithFeeOptionFragment extends AirFragment implements OnBackListener {
    private final int REQUEST_CODE_FOR_SELECT_EMAIL = 1000;
    @State
    String amountCurrency;
    @State
    ArrayList<CohostInvitation> cohostInvitations;
    @State
    String email;
    private CohostingInviteFriendEpoxyController epoxyController;
    @State
    FeeType feeType;
    @State
    int fixedFee;
    @State
    boolean includeCleaningFee;
    @BindView
    AirButton inviteButton;
    private final Listener listener = new Listener() {
        public void enableInviteButton(boolean enabled) {
            CohostingInviteFriendWithFeeOptionFragment.this.inviteButton.setEnabled(enabled);
        }

        public void pickContact() {
            CohostingInviteFriendWithFeeOptionFragmentPermissionsDispatcher.askForPermissionAndPickContactWithCheck(CohostingInviteFriendWithFeeOptionFragment.this);
        }

        public void openTermsOfServiceLink() {
            CohostingInviteFriendWithFeeOptionFragment.this.getContext().startActivity(WebViewIntentBuilder.newBuilder(CohostingInviteFriendWithFeeOptionFragment.this.getContext(), CohostingInviteFriendWithFeeOptionFragment.this.getContext().getString(C5658R.string.cohosting_terms_url)).authenticate().toIntent());
        }

        public void choosePaymentType(FeeType feeType, String managerFirstName) {
            CohostingInviteFriendWithFeeOptionFragment.this.startActivityForResult(ModalActivity.intentForFragment(CohostingInviteFriendWithFeeOptionFragment.this.getContext(), CohostingPaymentTypeFragment.create(feeType, managerFirstName)), 1001);
        }
    };
    @State
    Listing listing;
    @State
    ArrayList<ListingManager> listingManagers;
    CohostingManagementJitneyLogger logger;
    @State
    String message;
    @State
    int minimumFee;
    @State
    int percentage;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<SendCohostInvitationResponse> sendCohostInvitationListener = new C0699RL().onResponse(CohostingInviteFriendWithFeeOptionFragment$$Lambda$1.lambdaFactory$(this)).onError(CohostingInviteFriendWithFeeOptionFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean shareEarnings;
    @State
    C1958CohostingSourceFlow sourceFlow;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment, SendCohostInvitationResponse response) {
        cohostingInviteFriendWithFeeOptionFragment.inviteButton.setState(AirButton.State.Success);
        cohostingInviteFriendWithFeeOptionFragment.getActivity().setResult(-1, new Intent().putExtra(CohostingIntents.INTENT_EXTRA_INVITATION, response.cohostInvitation));
        cohostingInviteFriendWithFeeOptionFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$1(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment, AirRequestNetworkException e) {
        cohostingInviteFriendWithFeeOptionFragment.inviteButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cohostingInviteFriendWithFeeOptionFragment.getView(), e);
    }

    public static CohostingInviteFriendWithFeeOptionFragment create(Listing listing2, ArrayList<ListingManager> listingManagers2, ArrayList<CohostInvitation> cohostInvitations2, C1958CohostingSourceFlow flow) {
        return (CohostingInviteFriendWithFeeOptionFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingInviteFriendWithFeeOptionFragment()).putParcelable("listing", listing2)).putParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS, listingManagers2)).putParcelableArrayList(CohostingIntents.INTENT_EXTRA_INVITATIONS, cohostInvitations2)).putSerializable(CohostingIntents.INTENT_EXTRA_SOURCE_FLOW_TO_INVITE_PAGE, flow)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listing = (Listing) getArguments().getParcelable("listing");
        this.listingManagers = getArguments().getParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS);
        this.cohostInvitations = getArguments().getParcelableArrayList(CohostingIntents.INTENT_EXTRA_INVITATIONS);
        this.sourceFlow = (C1958CohostingSourceFlow) getArguments().getSerializable(CohostingIntents.INTENT_EXTRA_SOURCE_FLOW_TO_INVITE_PAGE);
        this.epoxyController = new CohostingInviteFriendEpoxyController(getContext(), this.listener, this.listing, this.listingManagers, this.cohostInvitations, this.mCurrencyHelper, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_invite_friend, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        getAirActivity().setOnBackPressedListener(this);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.logger.logInviteModalImpression(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), this.sourceFlow);
        this.toolbar.setNavigationOnClickListener(CohostingInviteFriendWithFeeOptionFragment$$Lambda$3.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$2(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment, View v) {
        cohostingInviteFriendWithFeeOptionFragment.onBackPressed();
        if (!cohostingInviteFriendWithFeeOptionFragment.canSaveChanges()) {
            cohostingInviteFriendWithFeeOptionFragment.getFragmentManager().popBackStack();
        }
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    @OnClick
    public void sendInvitation() {
        this.inviteButton.setState(AirButton.State.Loading);
        CohostingPaymentSettings cohostingPaymentSettings = this.epoxyController.getCohostingPaymentSettings();
        this.email = this.epoxyController.getEmail();
        this.message = this.epoxyController.getMessage();
        final int percentage2 = cohostingPaymentSettings.actualPercentage();
        final int minimumFee2 = cohostingPaymentSettings.actualMinimumFee();
        final int fixedFee2 = cohostingPaymentSettings.actualFixedAmount();
        final String amountCurrency2 = cohostingPaymentSettings.amountCurrency();
        final boolean includeCleaningFee2 = cohostingPaymentSettings.includeCleaningFee();
        new SendCohostInvitationRequest(this.listing.getId(), "email", this.email, percentage2, minimumFee2, fixedFee2, amountCurrency2, includeCleaningFee2, this.message).withListener((Observer) this.sendCohostInvitationListener).execute(this.requestManager);
        this.logger.logInviteButtonFromInvitationPageClicked(new ArrayMap<String, Object>() {
            {
                put(CohostingManagementJitneyLogger.INVITED_USER_EMAIL, CohostingInviteFriendWithFeeOptionFragment.this.email);
                put("percent_of_shared_earnings", Integer.valueOf(percentage2));
                put("minimum_fee", Integer.valueOf(minimumFee2));
                put("fixed_fee", Integer.valueOf(fixedFee2));
                put("amount_currency", amountCurrency2);
                put("pay_cleaning_fees", Boolean.valueOf(includeCleaningFee2));
            }
        }, this.sourceFlow, CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers));
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    public void askForPermissionAndPickContact() {
        this.logger.logAddressBookButtonClicked(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), this.sourceFlow);
        startActivityForResult(new Intent("android.intent.action.PICK", Email.CONTENT_URI), 1000);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        CohostingInviteFriendWithFeeOptionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1000:
                    this.epoxyController.setSelectedEmail(getContactEmail(data));
                    return;
                case 1001:
                    this.epoxyController.changeFeeTypeIfPossible((FeeType) data.getSerializableExtra("fee_type"));
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onBackPressed() {
        if (canSaveChanges()) {
            new Builder(getContext(), C5658R.C5663style.Theme_Airbnb_Dialog_Babu).setTitle(C5658R.string.listing_unsaved_changes_dialog_title).setMessage(C5658R.string.listing_unsaved_changes_dialog_message).setPositiveButton(C5658R.string.listing_unsaved_changes_dialog_confirm_button, CohostingInviteFriendWithFeeOptionFragment$$Lambda$4.lambdaFactory$(this)).setNegativeButton(C5658R.string.listing_unsaved_changes_dialog_cancel_button, (OnClickListener) null).show();
            return true;
        }
        dismiss();
        return false;
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        getActivity().finish();
        logDismissEvent();
    }

    private boolean canSaveChanges() {
        return this.epoxyController.hasChanged();
    }

    private void logDismissEvent() {
        this.logger.logInvitationModalDismissed(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), this.sourceFlow);
    }

    private String getContactEmail(Intent data) {
        Cursor cursor = getContext().getContentResolver().query(data.getData(), null, null, null, null);
        cursor.moveToFirst();
        String email2 = cursor.getString(cursor.getColumnIndex("data1"));
        cursor.close();
        return email2;
    }
}
