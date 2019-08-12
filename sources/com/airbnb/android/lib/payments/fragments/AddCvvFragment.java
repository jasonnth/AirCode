package com.airbnb.android.lib.payments.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.fragments.PaymentManagerFragment;
import com.airbnb.android.booking.fragments.PaymentManagerFragment.PaymentManagerListener;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.activities.AddCvvActivity;
import com.airbnb.android.lib.payments.activities.PaymentOptionsActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;

public class AddCvvFragment extends AirFragment {
    private static final int REQUEST_CODE_PAYMENT_OPTION = 1738;
    @State
    QuickPayClientType clientType;
    @State
    SheetTheme currentTheme;
    /* access modifiers changed from: private */
    public CvvNonceCreatedListener cvvNonceCreatedListener;
    @BindView
    SheetMarquee marquee;
    @BindView
    AirButton nextButton;
    private PaymentManagerFragment paymentManagerFragment;
    private final PaymentManagerListener paymentManagerListener = new PaymentManagerListener() {
        public void onAndroidPayConfigured() {
        }

        public void onPaymentManagerError(int errorCode, Exception error) {
            AddCvvFragment.this.nextButton.setEnabled(true);
        }

        public void onNonceCreated(OldPaymentInstrument instrument) {
            AddCvvFragment.this.cvvNonceCreatedListener.onCvvNonceCreated(AddCvvFragment.this.selectedPaymentOption, ((BraintreeCreditCard) instrument).getNonce());
            AddCvvFragment.this.nextButton.setState(AirButton.State.Normal);
            AddCvvFragment.this.nextButton.setEnabled(true);
        }

        public void onNonceError() {
            AddCvvFragment.this.nextButton.setState(AirButton.State.Normal);
            AddCvvFragment.this.nextButton.setEnabled(true);
        }
    };
    @State
    ArrayList<PaymentOption> paymentOptions;
    @BindView
    ViewGroup rootView;
    @BindView
    AirButton selectNewPayment;
    @State
    PaymentOption selectedPaymentOption;
    @BindView
    SheetInputText sheetInput;
    @BindView
    AirToolbar toolbar;
    @State
    Price totalPrice;
    private final TextWatcher validationWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            AddCvvFragment.this.validateInput(s.toString());
        }
    };

    public interface CvvNonceCreatedListener {
        void onCvvNonceCreated(PaymentOption paymentOption, String str);
    }

    public enum SheetTheme {
        BABU(C0880R.color.n2_babu),
        ARCHES(C0880R.color.n2_arches);
        
        final int backgroundColorRes;

        private SheetTheme(int background) {
            this.backgroundColorRes = background;
        }
    }

    public static AddCvvFragment newInstance(ArrayList<PaymentOption> paymentOptions2, PaymentOption selectedPaymentOption2, QuickPayClientType clientType2, Price totalPrice2) {
        return (AddCvvFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AddCvvFragment()).putParcelableArrayList(AddCvvActivity.EXTRA_PAYMENT_OPTIONS, paymentOptions2)).putParcelable(AddCvvActivity.EXTRA_SELECTED_PAYMENT_OPTION, selectedPaymentOption2)).putSerializable(AddCvvActivity.EXTRA_CLIENT_TYPE, clientType2)).putParcelable(AddCvvActivity.EXTRA_TOTAL_PRICE, totalPrice2)).build();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.cvvNonceCreatedListener = (CvvNonceCreatedListener) getActivity();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.paymentOptions = getArguments().getParcelableArrayList(AddCvvActivity.EXTRA_PAYMENT_OPTIONS);
            this.selectedPaymentOption = (PaymentOption) getArguments().getParcelable(AddCvvActivity.EXTRA_SELECTED_PAYMENT_OPTION);
            this.clientType = (QuickPayClientType) getArguments().getSerializable(AddCvvActivity.EXTRA_CLIENT_TYPE);
            this.totalPrice = (Price) getArguments().getParcelable(AddCvvActivity.EXTRA_TOTAL_PRICE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_quick_pay_security_code, container, false);
        bindViews(view);
        setMarqueeTitle(this.selectedPaymentOption);
        initializePaymentManagerFragment();
        setToolbar(this.toolbar);
        this.nextButton.setEnabled(false);
        this.sheetInput.addTextChangedListener(this.validationWatcher);
        this.toolbar.setNavigationOnClickListener(AddCvvFragment$$Lambda$1.lambdaFactory$(this));
        setSheetTheme(this.currentTheme == null ? SheetTheme.BABU : this.currentTheme, false);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AddCvvFragment addCvvFragment, View v) {
        addCvvFragment.sheetInput.hideSoftKeyboard();
        addCvvFragment.getActivity().onBackPressed();
    }

    public void onStart() {
        super.onStart();
        this.sheetInput.setMaxLength(CardType.getCardType(this.selectedPaymentOption.getCreditCardType()).getCCVLength());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == REQUEST_CODE_PAYMENT_OPTION) {
            this.selectedPaymentOption = (PaymentOption) data.getParcelableExtra("result_extra_payment_option");
            setMarqueeTitle(this.selectedPaymentOption);
            String cvvNonce = data.getStringExtra("result_extra_cvv_payload");
            if (cvvNonce != null) {
                this.cvvNonceCreatedListener.onCvvNonceCreated(this.selectedPaymentOption, cvvNonce);
                this.nextButton.setEnabled(true);
                return;
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onDestroyView() {
        this.sheetInput.removeTextChangedListener(this.validationWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickSelectNewPaymentButton() {
        startActivityForResult(PaymentOptionsActivity.intentForPaymentOptionsWithQuickPayClient(getActivity(), this.paymentOptions, this.selectedPaymentOption, this.clientType, this.totalPrice), REQUEST_CODE_PAYMENT_OPTION);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        if (!this.sheetInput.getText().toString().isEmpty()) {
            this.paymentManagerFragment.tokenizeCvv(this.sheetInput.getText().toString());
            this.nextButton.setState(AirButton.State.Loading);
            this.sheetInput.hideSoftKeyboard();
        }
    }

    private void setMarqueeTitle(PaymentOption paymentOption) {
        this.marquee.setTitle(getString(C0880R.string.quick_pay_add_security_code_for_last_four, paymentOption.getDisplayName(getActivity())));
    }

    /* access modifiers changed from: private */
    public void validateInput(String securityCode) {
        if (CardType.isSecurityCodeFullLength(securityCode, CardType.getCardType(this.selectedPaymentOption.getCreditCardType()))) {
            this.nextButton.setEnabled(true);
            this.sheetInput.setState(SheetInputText.State.Valid);
            return;
        }
        this.nextButton.setEnabled(false);
        this.sheetInput.setState(SheetInputText.State.Normal);
    }

    private void initializePaymentManagerFragment() {
        this.paymentManagerFragment = PaymentManagerFragment.newInstance(getAppCompatActivity(), null);
        this.paymentManagerFragment.setPaymentManagerListener(this.paymentManagerListener);
    }

    private void setSheetTheme(SheetTheme newTheme, boolean animate) {
        if (animate) {
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{new ColorDrawable(getResources().getColor(this.currentTheme.backgroundColorRes)), new ColorDrawable(getResources().getColor(newTheme.backgroundColorRes))});
            this.rootView.setBackground(td);
            td.startTransition(250);
        } else {
            this.rootView.setBackgroundResource(newTheme.backgroundColorRes);
        }
        this.currentTheme = newTheme;
    }
}
