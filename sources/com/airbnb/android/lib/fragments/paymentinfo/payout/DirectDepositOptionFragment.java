package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.paymentinfo.DirectDepositOptionSelectionView;
import com.airbnb.android.lib.presenters.p338n2.paymentinfo.DirectDepositOptionSelectionView.DirectDepositOption;
import com.airbnb.p027n2.components.AirToolbar;

public class DirectDepositOptionFragment extends BasePaymentInfoFragment {
    @BindView
    DirectDepositOptionSelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static DirectDepositOptionFragment newInstance() {
        return new DirectDepositOptionFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_direct_deposit_option, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.selectionView.setSelectionSheetOnItemClickedListener(DirectDepositOptionFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(DirectDepositOptionFragment directDepositOptionFragment, SimpleSelectionViewItem item) {
        if (item.getData() == DirectDepositOption.Checking) {
            directDepositOptionFragment.getNavigationController().doneWithSelectAccountType();
        } else if (item.getData() == DirectDepositOption.Savings) {
            directDepositOptionFragment.getNavigationController().doneWithSelectAccountType();
        } else {
            throw new IllegalStateException("Unhandled payout option.");
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClick() {
        getNavigationController().doneWithSelectAccountType();
    }
}
