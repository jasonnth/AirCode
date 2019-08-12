package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CohostingNotification;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CohostingNotificationRequest;
import com.airbnb.android.core.requests.SetPrimaryHostRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CohostingNotificationResponse;
import com.airbnb.android.core.responses.SetPrimaryHostResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import java.util.Arrays;

public class CohostingMakePrimaryHostFragment extends CohostManagementBaseFragment {
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CohostingMakePrimaryHostFragment$$Lambda$1.lambdaFactory$(this)).onError(CohostingMakePrimaryHostFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    AirButton confirmButton;
    @BindView
    AirTextView descriptionPart1;
    @BindView
    SimpleTextRow descriptionPart2;
    @State
    boolean isCheckedOnUpdateNotifCheckbox;
    @State
    ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    @BindView
    DocumentMarquee titleMarquee;
    @BindView
    AirToolbar toolbar;
    @BindView
    CityRegistrationToggleRow updateNotificationToggle;

    public static CohostingMakePrimaryHostFragment create(String listManagerId) {
        return (CohostingMakePrimaryHostFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingMakePrimaryHostFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, listManagerId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_make_primary_host, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
            this.isCheckedOnUpdateNotifCheckbox = true;
        }
        Check.state(this.listingManager != null, "Listing manager can not be null");
        setupTitle();
        setupDescription();
        setupToggleRow();
        view.setBackgroundColor(-1);
        this.logger.logMakePrimaryHostModalImpression(this.controller.getCohostingContext(), this.listingManager);
        return view;
    }

    public void toggleNotification() {
        this.isCheckedOnUpdateNotifCheckbox = this.updateNotificationToggle.isChecked();
    }

    @OnClick
    public void makePrimaryHost() {
        SetPrimaryHostRequest setPrimaryHostRequest;
        MuteType muteType = (!canUpdateNotification() || !this.isCheckedOnUpdateNotifCheckbox) ? null : MuteType.MUTED;
        this.logger.logSetPrimaryHostButtonClicked(this.controller.getCohostingContext(), this.listingManager, muteType);
        this.confirmButton.setState(AirButton.State.Loading);
        if (muteType == null) {
            setPrimaryHostRequest = new SetPrimaryHostRequest(this.listingManager.getId());
        } else {
            setPrimaryHostRequest = new SetPrimaryHostRequest(this.listingManager.getId(), muteType);
        }
        new AirBatchRequest(Arrays.asList(new BaseRequestV2[]{setPrimaryHostRequest, new CohostingNotificationRequest(this.controller.getListingId())}), this.batchListener).execute(this.requestManager);
    }

    private void setupTitle() {
        boolean isCurrentUser;
        if (this.listingManager.getUser().getId() == this.mAccountManager.getCurrentUserId()) {
            isCurrentUser = true;
        } else {
            isCurrentUser = false;
        }
        this.titleMarquee.setTitle((CharSequence) getString(C5658R.string.cohosting_make_primary_host_title, isCurrentUser ? getString(C5658R.string.yourself_text) : this.listingManager.getUser().getFirstName()));
    }

    private void setupDescription() {
        if (FeatureToggles.showCohostingNotification()) {
            this.descriptionPart1.setText(C5658R.string.cohosting_primary_host_explanation_part1_with_notif);
            this.descriptionPart2.setText(C5658R.string.cohosting_primary_host_explanation_part2_with_notif);
        }
    }

    private void setupToggleRow() {
        if (canUpdateNotification()) {
            this.descriptionPart2.showDivider(true);
            this.updateNotificationToggle.setVisibility(0);
            this.updateNotificationToggle.setTitle(C5658R.string.cohosting_primary_host_notification_title);
            this.updateNotificationToggle.setSubtitleText((CharSequence) getString(C5658R.string.cohosting_primary_host_notification_subtitle, this.listingManager.getUser().getFirstName()));
            this.updateNotificationToggle.setCheckChangedListener(CohostingMakePrimaryHostFragment$$Lambda$3.lambdaFactory$(this));
            this.updateNotificationToggle.showDivider(false);
            this.updateNotificationToggle.setChecked(this.isCheckedOnUpdateNotifCheckbox);
        }
    }

    private boolean canUpdateNotification() {
        return FeatureToggles.showCohostingNotification() && !this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: private */
    public void responseFinished(AirBatchResponse batchResponse) {
        this.confirmButton.setState(AirButton.State.Success);
        ListingManager listingManager2 = ((SetPrimaryHostResponse) batchResponse.singleResponse(SetPrimaryHostResponse.class)).listingManager;
        CohostingNotification notification = ((CohostingNotificationResponse) batchResponse.singleResponse(CohostingNotificationResponse.class)).cohostingNotification;
        this.controller.makeListingManagerPrimaryHost(listingManager2);
        this.controller.setListingPrimaryHost(listingManager2);
        this.controller.updateMuteType(notification);
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        this.confirmButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }
}
