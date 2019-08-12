package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.requests.PayoutInfoTypesRequest;
import com.airbnb.android.core.responses.PayoutInfoTypesResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p032rx.Observer;

public class PayoutAddressFragment extends BasePaymentInfoFragment {
    @BindView
    SheetInputText addressOneInput;
    @BindView
    SheetInputText addressTwoInput;
    @BindView
    SheetInputText cityInput;
    @State
    String countryCode;
    @BindView
    SheetInputText countryInput;
    @BindView
    AirButton nextButton;
    final RequestListener<PayoutInfoTypesResponse> payoutInfoTypesListener = new C0699RL().onResponse(PayoutAddressFragment$$Lambda$1.lambdaFactory$(this)).onError(PayoutAddressFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SheetInputText postalCodeInput;
    /* access modifiers changed from: private */
    public final List<SheetInputText> remainingRequiredFields = new ArrayList();
    private final List<SheetInputText> requiredFields = new ArrayList();
    private final int[] requiredInputIds = {C0880R.C0882id.address_street_one_input, C0880R.C0882id.address_city_input, C0880R.C0882id.address_postal_code_input, C0880R.C0882id.address_country_input};
    @BindView
    FrameLayout sheet;
    @BindView
    SheetInputText stateInput;
    @BindView
    AirToolbar toolbar;

    public static class AddressFieldOnFocusChangeListener implements OnFocusChangeListener {
        private final SheetInputText addressField;

        public AddressFieldOnFocusChangeListener(SheetInputText addressField2) {
            this.addressField = addressField2;
        }

        public void onFocusChange(View view, boolean isFocused) {
        }

        public SheetInputText getSheetInputTextAddressField() {
            return this.addressField;
        }
    }

    public static PayoutAddressFragment newInstance() {
        return new PayoutAddressFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_address, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.requiredFields.clear();
        for (int sheetInputId : this.requiredInputIds) {
            this.requiredFields.add(ViewLibUtils.findById(view, sheetInputId));
        }
        if (this.countryCode == null) {
            this.countryCode = Locale.getDefault().getCountry();
            this.countryInput.setText(Locale.getDefault().getDisplayCountry());
        } else {
            this.countryInput.setText(LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), this.countryCode));
        }
        this.countryInput.setActionOnClickListener(PayoutAddressFragment$$Lambda$3.lambdaFactory$(this));
        return view;
    }

    public void onDestroyView() {
        for (SheetInputText sheetInputText : this.requiredFields) {
            sheetInputText.setOnFocusChangeListener(null);
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClick() {
        if (areRequiredFieldsPopulated()) {
            getPaymentInfoActivity().setAddress(AirAddress.builder().streetAddressOne(this.addressOneInput.getText().toString()).streetAddressTwo(this.addressTwoInput.getText().toString()).city(this.cityInput.getText().toString()).state(this.stateInput.getText().toString()).postalCode(this.postalCodeInput.getText().toString()).country(this.countryInput.getText().toString()).countryCode(this.countryCode).build());
            this.nextButton.setState(AirButton.State.Loading);
            PayoutInfoTypesRequest.forCountry(this.countryCode).withListener((Observer) this.payoutInfoTypesListener).execute(this.requestManager);
            return;
        }
        showRequiredFields();
    }

    static /* synthetic */ void lambda$new$1(PayoutAddressFragment payoutAddressFragment, PayoutInfoTypesResponse response) {
        payoutAddressFragment.nextButton.setState(AirButton.State.Normal);
        payoutAddressFragment.getPaymentInfoActivity().setPayoutInfoTypes(response.payoutInfoTypes);
        payoutAddressFragment.getNavigationController().doneWithAddress();
    }

    static /* synthetic */ void lambda$new$2(PayoutAddressFragment payoutAddressFragment, AirRequestNetworkException error) {
        payoutAddressFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(payoutAddressFragment.getView(), error);
    }

    public void setCountryCode(String countryCode2) {
        this.countryCode = countryCode2;
        this.countryInput.setText(LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), countryCode2));
    }

    private boolean areRequiredFieldsPopulated() {
        return !FluentIterable.from((Iterable<E>) this.requiredFields).anyMatch(PayoutAddressFragment$$Lambda$4.lambdaFactory$());
    }

    private void showRequiredFields() {
        this.sheet.setBackgroundColor(ContextCompat.getColor(getContext(), C0880R.color.n2_arches));
        this.remainingRequiredFields.clear();
        for (SheetInputText sheetInputText : this.requiredFields) {
            if (TextUtils.isEmpty(sheetInputText.getText())) {
                this.remainingRequiredFields.add(sheetInputText);
                sheetInputText.setState(SheetInputText.State.Error);
                sheetInputText.setOnFocusChangeListener(new AddressFieldOnFocusChangeListener(sheetInputText) {
                    public void onFocusChange(View view, boolean isFocused) {
                        boolean didLeaveFieldBlank;
                        boolean isSheetErrorState;
                        SheetInputText addressField = getSheetInputTextAddressField();
                        PayoutAddressFragment.this.remainingRequiredFields.remove(addressField);
                        if (isFocused || !TextUtils.isEmpty(addressField.getText())) {
                            didLeaveFieldBlank = false;
                        } else {
                            didLeaveFieldBlank = true;
                        }
                        if (!PayoutAddressFragment.this.remainingRequiredFields.isEmpty() || didLeaveFieldBlank) {
                            isSheetErrorState = true;
                        } else {
                            isSheetErrorState = false;
                        }
                        PayoutAddressFragment.this.sheet.setBackgroundColor(ContextCompat.getColor(PayoutAddressFragment.this.getActivity(), isSheetErrorState ? C0880R.color.n2_arches : C0880R.color.n2_babu));
                        if (didLeaveFieldBlank) {
                            addressField.setState(SheetInputText.State.Error);
                            PayoutAddressFragment.this.remainingRequiredFields.add(addressField);
                            return;
                        }
                        addressField.setState(SheetInputText.State.Normal);
                    }
                });
            }
        }
        ErrorUtils.showErrorUsingSnackbar(getView(), C0880R.string.payout_address_error_required_fields);
    }
}
