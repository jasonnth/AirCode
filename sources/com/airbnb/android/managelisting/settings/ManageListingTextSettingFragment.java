package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateBookingSettingsRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.UpdateSelectListingRequest;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import com.airbnb.android.core.responses.SelectListingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import java.util.Arrays;
import p032rx.Observer;

public class ManageListingTextSettingFragment extends ManageListingBaseFragment {
    private static final String PARAM_SETTING = "setting";
    @BindView
    AirEditTextPageView editTextPage;
    @BindView
    AirButton saveButton;
    private TextSetting setting;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BookingSettingsResponse> updateBookingSettingsListener = new C0699RL().onResponse(ManageListingTextSettingFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingTextSettingFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingTextSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingTextSettingFragment$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<SelectListingResponse> updateSelectListingListener = new C0699RL().onResponse(ManageListingTextSettingFragment$$Lambda$5.lambdaFactory$(this)).onError(ManageListingTextSettingFragment$$Lambda$6.lambdaFactory$(this)).build();

    public static ManageListingTextSettingFragment forListingField(TextSetting setting2) {
        return (ManageListingTextSettingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingTextSettingFragment()).putSerializable(PARAM_SETTING, setting2)).build();
    }

    static /* synthetic */ void lambda$new$0(ManageListingTextSettingFragment manageListingTextSettingFragment, SimpleListingResponse response) {
        manageListingTextSettingFragment.saveButton.setState(State.Success);
        manageListingTextSettingFragment.controller.setListing(response.listing);
        manageListingTextSettingFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingTextSettingFragment manageListingTextSettingFragment, BookingSettingsResponse response) {
        manageListingTextSettingFragment.saveButton.setState(State.Success);
        manageListingTextSettingFragment.controller.setBookingSettings(response.bookingSettings);
        manageListingTextSettingFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$2(ManageListingTextSettingFragment manageListingTextSettingFragment, SelectListingResponse response) {
        manageListingTextSettingFragment.saveButton.setState(State.Success);
        manageListingTextSettingFragment.controller.setSelectListing(response.selectListing);
        manageListingTextSettingFragment.getFragmentManager().popBackStack();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_setting_text, container, false);
        bindViews(view);
        this.setting = (TextSetting) getArguments().getSerializable(PARAM_SETTING);
        setToolbar(this.toolbar);
        this.editTextPage.setTitle(this.setting.titleRes);
        this.editTextPage.setListener(ManageListingTextSettingFragment$$Lambda$7.lambdaFactory$(this));
        this.editTextPage.setHint(this.setting.hintRes);
        this.editTextPage.setMaxLength(this.setting.maxCharacters);
        this.editTextPage.setMinLength(this.setting.minCharacters);
        this.editTextPage.setSingleLine(this.setting.singleLine);
        if (savedInstanceState == null) {
            this.editTextPage.setText(this.setting.getExistingValue(this.controller.getListing(), this.controller.getSelectListing()));
        }
        updateSaveButton(this.editTextPage.isValid());
        this.editTextPage.setPadding(this.editTextPage.getPaddingLeft(), this.editTextPage.getPaddingTop(), this.editTextPage.getPaddingRight(), this.editTextPage.getPaddingBottom());
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
        return !Objects.equal(Strings.emptyToNull(this.editTextPage.getText().toString()), Strings.emptyToNull(this.setting.getExistingValue(this.controller.getListing(), this.controller.getSelectListing()))) && this.editTextPage.isValid();
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
        switch (this.setting.requestType) {
            case UpdateListing:
                UpdateListingRequest.forField(this.controller.getListing().getId(), this.setting.fieldKey, this.editTextPage.getText()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
                return;
            case UpdateBookingSettings:
                new UpdateBookingSettingsRequest(this.controller.getListing().getId(), Arrays.asList(this.editTextPage.getText().toString().split("\\n+")), this.controller.getListing().getBookingStandardQuestions()).withListener((Observer) this.updateBookingSettingsListener).execute(this.requestManager);
                return;
            case UpdateSelectListing:
                UpdateSelectListingRequest.forField(this.controller.getListingId(), this.setting.fieldKey, this.editTextPage.getText()).withListener((Observer) this.updateSelectListingListener).execute(this.requestManager);
                return;
            default:
                return;
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.setting.navigationTag;
    }

    /* access modifiers changed from: private */
    public void updateSaveButton(boolean enabled) {
        this.saveButton.setEnabled(enabled);
    }

    /* access modifiers changed from: private */
    public void onListenerError(AirRequestNetworkException exception) {
        this.editTextPage.setEnabled(true);
        this.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), exception);
    }
}
