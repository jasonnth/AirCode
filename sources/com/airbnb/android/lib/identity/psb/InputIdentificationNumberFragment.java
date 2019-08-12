package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class InputIdentificationNumberFragment extends BaseCreateIdentificationFragment {
    private static final String ARG_IDENTIFICATION_TYPE = "identification_type";
    @BindView
    View bookingNextButton;
    @BindView
    SheetInputText identificationNumberInput;
    private final TextWatcher identificationNumberTextWatcher = TextWatcherUtils.createEmptyWatcher(InputIdentificationNumberFragment$$Lambda$1.lambdaFactory$(this));
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View nextButton;
    @BindView
    SheetMarquee sheetHeader;

    public static InputIdentificationNumberFragment forIdentificationType(Type type) {
        return (InputIdentificationNumberFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InputIdentificationNumberFragment()).putParcelable(ARG_IDENTIFICATION_TYPE, type)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int sheetHeaderTitleResId;
        boolean haveIdentificationNumber = false;
        View view = inflater.inflate(C0880R.layout.sheet_with_input_and_next_button, container, false);
        bindViews(view);
        Type identificationType = (Type) getArguments().getParcelable(ARG_IDENTIFICATION_TYPE);
        Check.notNull(identificationType, "identification type must not be null");
        switch (identificationType) {
            case Passport:
                sheetHeaderTitleResId = C0880R.string.enter_passport_number;
                break;
            case ChineseNationalID:
                sheetHeaderTitleResId = C0880R.string.enter_government_id_number;
                break;
            default:
                throw new IllegalArgumentException("unknown identification type: " + identificationType.name());
        }
        this.sheetHeader.setTitle(sheetHeaderTitleResId);
        this.identificationNumberInput.setHint(identificationType.getHintText(getContext()));
        String identificationNumber = this.callback.getIdentificationNumber();
        if (!TextUtils.isEmpty(identificationNumber)) {
            haveIdentificationNumber = true;
        }
        if (haveIdentificationNumber) {
            this.identificationNumberInput.setText(identificationNumber);
        }
        this.nextButton.setEnabled(haveIdentificationNumber);
        this.bookingNextButton.setEnabled(haveIdentificationNumber);
        this.identificationNumberInput.addTextChangedListener(this.identificationNumberTextWatcher);
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.callback.isP4Redesign());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.sheetHeader);
        style.inputStyle.setStyle(this.identificationNumberInput);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf(this.nextButton, style.nextButtonVisible);
        ViewUtils.setVisibleIf(this.bookingNextButton, style.bookingNextButtonVisible);
    }

    public void onDestroyView() {
        this.identificationNumberInput.removeTextChangedListener(this.identificationNumberTextWatcher);
        super.onDestroyView();
    }

    public void onPause() {
        KeyboardUtils.dismissSoftKeyboard((View) this.identificationNumberInput);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void textWatch(boolean empty) {
        boolean z;
        boolean z2 = true;
        View view = this.nextButton;
        if (!empty) {
            z = true;
        } else {
            z = false;
        }
        view.setEnabled(z);
        View view2 = this.bookingNextButton;
        if (empty) {
            z2 = false;
        }
        view2.setEnabled(z2);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        this.callback.setIdentificationNumber(this.identificationNumberInput.getText().toString());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNextClick() {
        this.callback.bookingJitneyLogger.clickNavigation(this.callback.reservationDetails, this.callback.isInstantBookable, C2467P4FlowPage.ChinaGuestGovernmentIdNumber, C2466P4FlowNavigationMethod.NextButton);
        onNextClick();
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.callback.isP4Redesign());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestGovernmentIdNumber;
    }
}
