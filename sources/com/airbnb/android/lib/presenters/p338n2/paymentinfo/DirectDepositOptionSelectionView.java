package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.DirectDepositOptionSelectionView */
public class DirectDepositOptionSelectionView extends BaseSelectionView<SimpleSelectionViewItem> {

    /* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.DirectDepositOptionSelectionView$DirectDepositOption */
    public enum DirectDepositOption {
        Checking(C0880R.string.payout_direct_deposit_option_checking),
        Savings(C0880R.string.payout_direct_deposit_option_savings);
        
        public final int stringResId;

        private DirectDepositOption(int stringResId2) {
            this.stringResId = stringResId2;
        }
    }

    public DirectDepositOptionSelectionView(Context context) {
        this(context, null);
    }

    public DirectDepositOptionSelectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DirectDepositOptionSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItems(FluentIterable.m1283of(DirectDepositOption.values()).transform(DirectDepositOptionSelectionView$$Lambda$1.lambdaFactory$(this)).toList());
    }

    static /* synthetic */ SimpleSelectionViewItem lambda$new$0(DirectDepositOptionSelectionView directDepositOptionSelectionView, DirectDepositOption directDepositOption) {
        return new SimpleSelectionViewItem(directDepositOptionSelectionView.getContext().getString(directDepositOption.stringResId), directDepositOption);
    }
}
