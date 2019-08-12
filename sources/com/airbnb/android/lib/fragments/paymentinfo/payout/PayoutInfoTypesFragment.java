package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.paymentinfo.PayoutInfoTypeSelectionView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayoutInfoTypesFragment extends BasePaymentInfoFragment {
    private static final String ARG_PAYOUT_INFO_TYPES = "arg_payout_info_types";
    private final List<C6200PaymentInstrumentType> acceptablePayoutTypes = Arrays.asList(new C6200PaymentInstrumentType[]{C6200PaymentInstrumentType.ACH, C6200PaymentInstrumentType.PayPal, C6200PaymentInstrumentType.BankTransfer, C6200PaymentInstrumentType.PayoneerBankTransfer});
    @State
    ArrayList<PayoutInfoType> payoutInfoTypes;
    @BindView
    PayoutInfoTypeSelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static PayoutInfoTypesFragment forPayoutTypes(ArrayList<PayoutInfoType> payoutInfoTypes2) {
        return (PayoutInfoTypesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PayoutInfoTypesFragment()).putParcelableArrayList(ARG_PAYOUT_INFO_TYPES, payoutInfoTypes2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_info_types, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.payoutInfoTypes == null) {
            this.payoutInfoTypes = getArguments().getParcelableArrayList(ARG_PAYOUT_INFO_TYPES);
            this.payoutInfoTypes = new ArrayList<>(FluentIterable.from((Iterable<E>) this.payoutInfoTypes).filter(PayoutInfoTypesFragment$$Lambda$1.lambdaFactory$(this)).toList());
        }
        this.selectionView.setPayoutInfoTypes(this.payoutInfoTypes);
        this.selectionView.setSelectionSheetOnItemClickedListener(PayoutInfoTypesFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(PayoutInfoTypesFragment payoutInfoTypesFragment, SimpleSelectionViewItem selectionViewItem) {
        PayoutInfoType payoutInfoType = (PayoutInfoType) selectionViewItem.getData();
        if (payoutInfoType.getCurrencies().size() == 1) {
            payoutInfoTypesFragment.getPaymentInfoActivity().setPayoutCurrency((String) payoutInfoType.getCurrencies().get(0));
            payoutInfoTypesFragment.getNavigationController().doneWithSelectPayoutCurrency(payoutInfoType);
            return;
        }
        payoutInfoTypesFragment.getNavigationController().doneWithSelectPayoutInfoType((PayoutInfoType) selectionViewItem.getData());
    }
}
