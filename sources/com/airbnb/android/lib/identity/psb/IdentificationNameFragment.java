package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.ChinaGuestNameSection.p056v1.C1923ChinaGuestNameSection;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import dagger.Lazy;

public class IdentificationNameFragment extends BaseCreateIdentificationFragment {
    private static final String ARG_PREFILL_NAME = "prefill_name";
    Lazy<AirbnbAccountManager> accountManager;
    @BindView
    View bookingNextButton;
    @BindView
    SheetInputText givenNamesInput;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View nextButton;
    @BindView
    SheetMarquee sheetHeaderMarquee;
    @BindView
    SheetInputText surnameInput;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean enabled = true;
            if (TextUtils.isEmpty(IdentificationNameFragment.this.givenNamesInput.getText().toString()) || TextUtils.isEmpty(IdentificationNameFragment.this.surnameInput.getText().toString())) {
                enabled = false;
            }
            IdentificationNameFragment.this.nextButton.setEnabled(enabled);
            IdentificationNameFragment.this.bookingNextButton.setEnabled(enabled);
        }
    };

    public static IdentificationNameFragment newInstance(boolean prefillNameWithUsersInfo) {
        return (IdentificationNameFragment) ((FragmentBundleBuilder) FragmentBundler.make(new IdentificationNameFragment()).putBoolean(ARG_PREFILL_NAME, prefillNameWithUsersInfo)).build();
    }

    private void log(C1923ChinaGuestNameSection section) {
        if (this.callback.isP4Redesign()) {
            this.callback.bookingJitneyLogger.chinaGuestFocusNameSection(this.callback.reservationDetails, this.callback.isInstantBookable, section);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_identification_input_name, container, false);
        bindViews(view);
        this.givenNamesInput.addTextChangedListener(this.textWatcher);
        this.surnameInput.addTextChangedListener(this.textWatcher);
        this.givenNamesInput.setOnFocusChangeListener(IdentificationNameFragment$$Lambda$1.lambdaFactory$(this));
        this.surnameInput.setOnFocusChangeListener(IdentificationNameFragment$$Lambda$2.lambdaFactory$(this));
        String givenName = this.callback.getGivenNames();
        if (!TextUtils.isEmpty(givenName)) {
            this.givenNamesInput.setText(givenName);
        }
        String surname = this.callback.getSurname();
        if (!TextUtils.isEmpty(surname)) {
            this.surnameInput.setText(surname);
        }
        boolean prefillCurrentUsersName = getArguments().getBoolean(ARG_PREFILL_NAME, false);
        if (prefillCurrentUsersName) {
            User currentUser = ((AirbnbAccountManager) this.accountManager.get()).getCurrentUser();
            this.givenNamesInput.setText(currentUser.getFirstName());
            this.surnameInput.setText(currentUser.getLastName());
            this.givenNamesInput.setSelection(this.givenNamesInput.getText().length());
        }
        this.sheetHeaderMarquee.setTitle(prefillCurrentUsersName ? C0880R.string.enter_identification_name_title_current_user : C0880R.string.enter_identification_name_title_other_guests);
        setStyle(view);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(IdentificationNameFragment identificationNameFragment, View v, boolean onFocus) {
        if (onFocus) {
            identificationNameFragment.log(C1923ChinaGuestNameSection.FirstName);
        }
    }

    static /* synthetic */ void lambda$onCreateView$1(IdentificationNameFragment identificationNameFragment, View v, boolean onFocus) {
        if (onFocus) {
            identificationNameFragment.log(C1923ChinaGuestNameSection.LastName);
        }
    }

    private void setStyle(View view) {
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.callback.isP4Redesign());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.sheetHeaderMarquee);
        style.inputStyle.setStyle(this.givenNamesInput);
        style.inputStyle.setStyle(this.surnameInput);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf(this.nextButton, style.nextButtonVisible);
        ViewUtils.setVisibleIf(this.bookingNextButton, style.bookingNextButtonVisible);
    }

    public void onDestroyView() {
        this.givenNamesInput.removeTextChangedListener(this.textWatcher);
        this.surnameInput.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public void onPause() {
        KeyboardUtils.dismissSoftKeyboard((View) this.givenNamesInput);
        super.onPause();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        KeyboardUtils.dismissSoftKeyboard((View) this.givenNamesInput);
        this.callback.setName(this.givenNamesInput.getText().toString(), this.surnameInput.getText().toString());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNextClick() {
        this.callback.bookingJitneyLogger.clickNavigation(this.callback.reservationDetails, this.callback.isInstantBookable, C2467P4FlowPage.ChinaGuestName, C2466P4FlowNavigationMethod.NextButton);
        onNextClick();
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.callback.isP4Redesign());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestName;
    }
}
