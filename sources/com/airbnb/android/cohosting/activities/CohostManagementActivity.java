package com.airbnb.android.cohosting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.enums.CohostManagementLaunchType;
import com.airbnb.android.cohosting.executors.CohostManagementActionExecutor;
import com.airbnb.android.cohosting.fragments.CohostingContractFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingListingLevelNotificationSettingFragment;
import com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment;
import com.airbnb.android.cohosting.fragments.CohostingPaymentTypeFragment;
import com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment;
import com.airbnb.android.cohosting.fragments.CohostingShareEarningsWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment;
import com.airbnb.android.cohosting.fragments.ListingManagerDetailsFragment;
import com.airbnb.android.cohosting.fragments.ListingManagersPickerFragment;
import com.airbnb.android.cohosting.fragments.PendingCohostDetailsFragment;
import com.airbnb.android.cohosting.fragments.RemoveCohostFragment;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.CohostingNotification;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CohostInvitationsRequest;
import com.airbnb.android.core.requests.CohostingNotificationRequest;
import com.airbnb.android.core.requests.ListingManagersRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CohostInvitationsResponse;
import com.airbnb.android.core.responses.CohostingNotificationResponse;
import com.airbnb.android.core.responses.ListingManagersResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.CohostingDeepLink;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class CohostManagementActivity extends CohostingBaseActivity {
    protected final CohostManagementActionExecutor actionExecutor = new CohostManagementActionExecutor() {
        public void cohostingServiceIntro() {
            CohostManagementActivity.this.showModal(CohostingServicesIntroFragment.create(CohostManagementActivity.this.controller.getListing(), new ArrayList(CohostManagementActivity.this.controller.getListingManagers()), CohostManagementActivity.this.controller.getSourceFlow()));
        }

        public void listingManagerDetails(String managerId) {
            CohostManagementActivity.this.showFragment(ListingManagerDetailsFragment.create(managerId));
        }

        public void pendingCohostDetails(long invitationId) {
            CohostManagementActivity.this.showFragment(PendingCohostDetailsFragment.create(invitationId));
        }

        public void makePrimaryHost(String listingManagerId) {
            CohostManagementActivity.this.showModal(CohostingMakePrimaryHostFragment.create(listingManagerId));
        }

        public void removeCohost(ListingManager manager) {
            CohostManagementActivity.this.showModal(RemoveCohostFragment.create(manager.getId()));
        }

        public void shareEarnings(String listingManagerId) {
            CohostManagementActivity.this.showFragment(CohostingShareEarningsWithFeeOptionFragment.create(listingManagerId));
        }

        public void stopShareEarnings(String listingManagerId) {
            CohostManagementActivity.this.showFragment(CohostingStopShareEarningsFragment.create(listingManagerId));
        }

        public void inviteFriend() {
            CohostManagementActivity.this.startActivityForResult(ModalActivity.intentForFragment(CohostManagementActivity.this, CohostingInviteFriendWithFeeOptionFragment.create(CohostManagementActivity.this.controller.getListing(), new ArrayList(CohostManagementActivity.this.controller.getListingManagers()), new ArrayList(CohostManagementActivity.this.controller.getCohostInvitations()), CohostManagementActivity.this.controller.getSourceFlow())), 1002);
        }

        public void cohostingContractDetails(long contractId, String managerId) {
            CohostManagementActivity.this.showFragment(CohostingContractFragment.create(contractId, managerId));
        }

        public void showListingManager(long userId) {
            listingManagerDetails(CohostManagementActivity.this.controller.getListingManagerIdFromUserId(userId));
        }

        public void choosePaymentType(FeeType feeType, String managerFirstName) {
            CohostManagementActivity.this.startActivityForResult(ModalActivity.intentForFragment(CohostManagementActivity.this, CohostingPaymentTypeFragment.create(feeType, managerFirstName)), 1001);
        }

        public void setNotification(String managerId) {
            CohostManagementActivity.this.showFragment(CohostingListingLevelNotificationSettingFragment.create(managerId));
        }
    };
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CohostManagementActivity$$Lambda$1.lambdaFactory$(this)).onError(CohostManagementActivity$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    protected CohostManagementDataController controller;
    @BindView
    RefreshLoader fullLoader;
    @State
    Listing listing;
    @State
    long listingId;
    @BindView
    AirToolbar toolbar;

    public CohostManagementDataController getCohostManagementDataController() {
        return this.controller;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.listing = (Listing) getIntent().getParcelableExtra("listing");
        if (this.listing != null) {
            this.listingId = this.listing.getId();
        } else {
            this.listingId = getIntent().getLongExtra("listing_id", -1);
        }
        this.controller = new CohostManagementDataController(this, this.actionExecutor, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_simple_fragment_with_toolbar);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CohostManagementActivity$$Lambda$3.lambdaFactory$(this));
        ((CohostingGraph) CoreApplication.instance(this).component()).inject(this);
        if (savedInstanceState == null) {
            fetchData();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1001:
                    this.controller.setFeeType((FeeType) data.getSerializableExtra("fee_type"));
                    return;
                case 1002:
                    this.controller.appendCohostInvitation((CohostInvitation) data.getParcelableExtra(CohostingIntents.INTENT_EXTRA_INVITATION));
                    return;
                default:
                    return;
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.controller = null;
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        this.fullLoader.setVisibility(8);
        this.controller.setLoading(false);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(findViewById(C5658R.C5660id.root_container), e, CohostManagementActivity$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void responseFinished(AirBatchResponse batchResponse) {
        this.fullLoader.setVisibility(8);
        this.toolbar.setVisibility(8);
        List<ListingManager> listingManagers = ((ListingManagersResponse) batchResponse.singleResponse(ListingManagersResponse.class)).listingManagers;
        List<CohostInvitation> cohostInvitations = ((CohostInvitationsResponse) batchResponse.singleResponse(CohostInvitationsResponse.class)).cohostInvitations;
        CohostingNotification cohostingNotification = ((CohostingNotificationResponse) batchResponse.singleResponse(CohostingNotificationResponse.class)).cohostingNotification;
        Check.notNull(listingManagers);
        Check.notNull(cohostInvitations);
        if (this.listing == null) {
            this.listing = ((ListingResponse) batchResponse.singleResponse(ListingResponse.class)).listing;
        }
        Check.notNull(this.listing);
        this.controller.setData(this.listing, listingManagers, cohostInvitations, cohostingNotification, CohostManagementLaunchType.ManageListing);
        if (shouldShowDeepLinkingFragment()) {
            showDeepLinking();
        } else {
            showFragment(ListingManagersPickerFragment.create());
        }
    }

    /* access modifiers changed from: private */
    public void fetchData() {
        this.controller.setLoading(true);
        this.fullLoader.setVisibility(0);
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        ListingManagersRequest listingManagersRequest = (ListingManagersRequest) ListingManagersRequest.forListing(this.listingId).skipCache();
        CohostInvitationsRequest cohostInvitationsRequest = (CohostInvitationsRequest) CohostInvitationsRequest.forListing(this.listingId, this.accountManager.getCurrentUserId()).skipCache();
        CohostingNotificationRequest cohostingNotificationRequest = new CohostingNotificationRequest(this.listingId);
        requests.add(listingManagersRequest);
        requests.add(cohostInvitationsRequest);
        requests.add(cohostingNotificationRequest);
        if (this.listing == null) {
            requests.add((ListingRequest) ListingRequest.forV1LegacyManageListing(this.listingId).skipCache());
        }
        new AirBatchRequest(requests, this.batchListener).execute(this.requestManager);
    }

    private boolean shouldShowDeepLinkingFragment() {
        return getIntent().getExtras().containsKey("deep_link");
    }

    private void showDeepLinking() {
        this.fullLoader.setVisibility(8);
        Bundle extras = getIntent().getExtras();
        CohostingDeepLink deepLink = (CohostingDeepLink) extras.getSerializable("deep_link");
        switch (deepLink) {
            case ListingManager:
                this.actionExecutor.showListingManager(extras.getLong(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER_USER_ID));
                return;
            default:
                throw new UnhandledStateException(deepLink);
        }
    }
}
