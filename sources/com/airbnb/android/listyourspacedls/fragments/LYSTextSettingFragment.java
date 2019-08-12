package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import p032rx.Observer;

public class LYSTextSettingFragment extends LYSBaseFragment {
    private static final String PARAM_SETTING = "setting";
    @BindView
    AirEditTextPageView editTextPage;
    private TextSetting setting;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSTextSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSTextSettingFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSTextSettingFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static LYSTextSettingFragment create(TextSetting setting2) {
        return (LYSTextSettingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSTextSettingFragment()).putSerializable(PARAM_SETTING, setting2)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSTextSettingFragment lYSTextSettingFragment, SimpleListingResponse response) {
        lYSTextSettingFragment.controller.setListing(response.listing);
        lYSTextSettingFragment.controller.popFragment();
    }

    static /* synthetic */ void lambda$new$2(LYSTextSettingFragment lYSTextSettingFragment, Boolean success) {
        lYSTextSettingFragment.setLoadingFinished(success.booleanValue(), null);
        lYSTextSettingFragment.editTextPage.setEnabled(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.fragment_manage_listing_setting_text, container, false);
        bindViews(view);
        this.setting = (TextSetting) getArguments().getSerializable(PARAM_SETTING);
        setToolbar(this.toolbar);
        this.editTextPage.setTitle(this.setting.titleRes);
        this.editTextPage.setListener(LYSTextSettingFragment$$Lambda$4.lambdaFactory$(this));
        this.editTextPage.setHint(this.setting.hintRes);
        this.editTextPage.setMaxLength(this.setting.maxCharacters);
        this.editTextPage.setMinLength(this.setting.minCharacters);
        this.editTextPage.setSingleLine(this.setting.singleLine);
        if (savedInstanceState == null) {
            this.editTextPage.setText(this.setting.getExistingValue(this.controller.getListing()));
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
        return !Objects.equal(Strings.emptyToNull(this.editTextPage.getText().toString()), Strings.emptyToNull(this.setting.getExistingValue(this.controller.getListing()))) && this.editTextPage.isValid();
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        handleOnSavePressed(canSaveChanges());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.userAction = UserAction.GoToNext;
        handleOnSavePressed(canSaveChanges());
    }

    private void handleOnSavePressed(boolean hasChanges) {
        this.editTextPage.setEnabled(false);
        if (!hasChanges) {
            this.controller.popFragment();
            return;
        }
        setLoading(null);
        UpdateListingRequest.forFieldLYS(this.controller.getListing().getId(), this.setting.fieldKey, this.editTextPage.getText()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.setting.navigationTag;
    }

    /* access modifiers changed from: private */
    public void updateSaveButton(boolean enabled) {
        ((AirButton) Check.notNull(this.nextButton)).setEnabled(enabled);
    }
}
