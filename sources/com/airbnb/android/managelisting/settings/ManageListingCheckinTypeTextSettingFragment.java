package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.ListingAmenityInformation;
import com.airbnb.android.core.requests.CheckInInformationRequest;
import com.airbnb.android.core.requests.CreateCheckInInformationRequest;
import com.airbnb.android.core.requests.DeleteCheckInInformationRequest;
import com.airbnb.android.core.requests.UpdateCheckInInformationRequest;
import com.airbnb.android.core.responses.CheckinAmenityResponse;
import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.listing.CheckinDisplay;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.base.Objects;
import p032rx.Observer;

public class ManageListingCheckinTypeTextSettingFragment extends ManageListingBaseFragment {
    private static final String PARAM_CHECKIN_SETTING = "checkin_setting";
    private static final String PARAM_SHOW_MENU_OPTION = "checkin_remove_menu_option";
    private CheckInInformation checkinType;
    @BindView
    AirEditTextPageView editTextPage;
    CheckInJitneyLogger jitneyLogger;
    final RequestListener<ListingCheckInInformationResponse> refetchInformationListener = new C0699RL().onResponse(ManageListingCheckinTypeTextSettingFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingCheckinTypeTextSettingFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<CheckinAmenityResponse> updateAmenityListener = new C0699RL().onResponse(ManageListingCheckinTypeTextSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingCheckinTypeTextSettingFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingCheckinTypeTextSettingFragment forCheckinInformation(CheckInInformation checkinInformation, boolean shouldShowMenu) {
        return (ManageListingCheckinTypeTextSettingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingCheckinTypeTextSettingFragment()).putParcelable(PARAM_CHECKIN_SETTING, checkinInformation)).putBoolean(PARAM_SHOW_MENU_OPTION, shouldShowMenu)).build();
    }

    static /* synthetic */ void lambda$new$1(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment, AirRequestNetworkException e) {
        manageListingCheckinTypeTextSettingFragment.editTextPage.setEnabled(true);
        manageListingCheckinTypeTextSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingCheckinTypeTextSettingFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$2(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment, ListingCheckInInformationResponse response) {
        manageListingCheckinTypeTextSettingFragment.saveButton.setState(State.Success);
        manageListingCheckinTypeTextSettingFragment.controller.setCheckInInformation(response.checkInInformation);
        manageListingCheckinTypeTextSettingFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$4(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment, AirRequestNetworkException error) {
        manageListingCheckinTypeTextSettingFragment.editTextPage.setEnabled(true);
        manageListingCheckinTypeTextSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingCheckinTypeTextSettingFragment.getView(), (NetworkException) error, ManageListingCheckinTypeTextSettingFragment$$Lambda$6.lambdaFactory$(manageListingCheckinTypeTextSettingFragment));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_setting_text, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(getArguments().getBoolean(PARAM_SHOW_MENU_OPTION));
        this.checkinType = (CheckInInformation) getArguments().getParcelable(PARAM_CHECKIN_SETTING);
        this.editTextPage.setTitle((CharSequence) CheckinDisplay.getCheckinTypeEditPageTitle(this.checkinType));
        this.editTextPage.setCaption((CharSequence) CheckinDisplay.getCheckinTypeEditPageInstructions(this.checkinType));
        this.editTextPage.setHint((CharSequence) CheckinDisplay.getCheckinTypeEditPageHintText(this.checkinType));
        this.editTextPage.setListener(ManageListingCheckinTypeTextSettingFragment$$Lambda$5.lambdaFactory$(this));
        this.editTextPage.setMinLength(1);
        if (savedInstanceState == null) {
            this.editTextPage.setText(this.checkinType.getInstruction());
        }
        updateSaveButton(this.editTextPage.isValid());
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.editTextPage.isEmpty()) {
            this.editTextPage.requestFocusAndKeyboard();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !Objects.equal(this.editTextPage.getText().toString(), this.checkinType.getInstruction()) && this.editTextPage.isValid();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.editTextPage.setEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        ListingAmenityInformation amenity = this.checkinType.getAmenity();
        if (amenity.getListingAmenityId() != null) {
            new UpdateCheckInInformationRequest(amenity.getListingAmenityId().longValue(), this.editTextPage.getText().toString()).withListener((Observer) this.updateAmenityListener).execute(this.requestManager);
            this.jitneyLogger.logCheckinGuideEditCheckinMethodInfoEvent((long) amenity.getAmenityId(), getListingId());
            return;
        }
        new CreateCheckInInformationRequest((long) amenity.getAmenityId(), this.editTextPage.getText().toString(), this.controller.getListingId()).withListener((Observer) this.updateAmenityListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void refreshCheckinInformation() {
        CheckInInformationRequest.forAllMethods(this.controller.getListingId()).withListener((Observer) this.refetchInformationListener).execute(this.requestManager);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (SanitizeUtils.zeroIfNull(this.checkinType.getAmenity().getListingAmenityId()) > 0) {
            inflater.inflate(C7368R.C7371menu.checkin_method_note_options, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7368R.C7370id.menu_remove_setting) {
            return super.onOptionsItemSelected(item);
        }
        this.saveButton.setState(State.Loading);
        this.editTextPage.setEnabled(false);
        new DeleteCheckInInformationRequest(this.checkinType.getAmenity().getListingAmenityId().longValue()).withListener((Observer) this.updateAmenityListener).execute(this.requestManager);
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingEditCheckinMethod;
    }

    /* access modifiers changed from: private */
    public void updateSaveButton(boolean enabled) {
        this.saveButton.setEnabled(enabled);
    }
}
