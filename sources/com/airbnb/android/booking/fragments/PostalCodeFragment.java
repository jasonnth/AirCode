package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class PostalCodeFragment extends CreditCardBaseFragment {
    @BindView
    JellyfishView jellyfishView;
    @BindView
    SheetInputText sheetInput;

    public static PostalCodeFragment newInstance() {
        return new PostalCodeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_postal_code, container, false);
        bindViews(view);
        setUpSheetInput();
        return view;
    }

    private void setUpSheetInput() {
        this.sheetInput.requestFocus();
        this.sheetInput.post(PostalCodeFragment$$Lambda$1.lambdaFactory$(this));
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClick() {
        KeyboardUtils.dismissSoftKeyboard((View) this.sheetInput);
        getCreditCardActivity().updatePostalCode(this.sheetInput.getText().toString());
        if (getCreditCardActivity().isQuickPay()) {
            this.quickPayJitneyLogger.paymentCcZip();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentPostalCode;
    }
}
