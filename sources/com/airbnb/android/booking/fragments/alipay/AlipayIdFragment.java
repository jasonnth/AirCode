package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.utils.PaymentUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.TextUtil;

public class AlipayIdFragment extends BaseAlipayFragment {
    @BindView
    SheetInputText inputText;
    /* access modifiers changed from: private */
    public boolean isPhone = false;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String currentInput = AlipayIdFragment.this.inputText.getText().toString();
            if (PaymentUtils.isLikelyAlipayPhone(currentInput)) {
                AlipayIdFragment.this.isPhone = true;
                AlipayIdFragment.this.nextButton.setEnabled(true);
            } else if (TextUtil.isValidEmail(currentInput)) {
                AlipayIdFragment.this.isPhone = false;
                AlipayIdFragment.this.nextButton.setEnabled(true);
            } else {
                AlipayIdFragment.this.nextButton.setEnabled(false);
            }
        }
    };
    @BindView
    AirToolbar toolbar;

    public static AlipayIdFragment newInstance() {
        return new AlipayIdFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_alipay_id, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.inputText.addTextChangedListener(this.textWatcher);
        this.inputText.postDelayed(AlipayIdFragment$$Lambda$1.lambdaFactory$(this), 200);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AlipayIdFragment alipayIdFragment) {
        if (alipayIdFragment.getActivity() != null) {
            alipayIdFragment.inputText.showSoftKeyboard();
        }
    }

    public void onDestroyView() {
        this.inputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNext() {
        alipayIdLogging();
        getAlipayActivity().setAlipayId(this.inputText.getText().toString());
        getNavigationController().doneWithAlipayId(this.isPhone);
    }

    private void alipayIdLogging() {
        if (getAlipayActivity().isQuickPay()) {
            this.quickPayJitneyLogger.alipayId();
        } else {
            KonaBookingAnalytics.trackClick("payment_options", "alipay_id", getAnalyticsData());
        }
    }
}
