package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.paymentinfo.PayoutCurrencySelectionView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class PayoutCurrencyFragment extends BasePaymentInfoFragment {
    private static final String ARG_PAYOUT_INFO_TYPE = "arg_payout_info_type";
    @State
    PayoutInfoType payoutInfoType;
    @BindView
    PayoutCurrencySelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static PayoutCurrencyFragment forPayoutInfoType(PayoutInfoType payoutInfoType2) {
        return (PayoutCurrencyFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PayoutCurrencyFragment()).putParcelable(ARG_PAYOUT_INFO_TYPE, payoutInfoType2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_currency, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.payoutInfoType == null) {
            this.payoutInfoType = (PayoutInfoType) getArguments().getParcelable(ARG_PAYOUT_INFO_TYPE);
        }
        this.selectionView.setPayoutCurrencies(this.payoutInfoType.getCurrencies());
        this.selectionView.setSelectionSheetOnItemClickedListener(PayoutCurrencyFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(PayoutCurrencyFragment payoutCurrencyFragment, SimpleSelectionViewItem item) {
        payoutCurrencyFragment.getPaymentInfoActivity().setPayoutCurrency((String) item.getData());
        payoutCurrencyFragment.getNavigationController().doneWithSelectPayoutCurrency(payoutCurrencyFragment.payoutInfoType);
    }
}
