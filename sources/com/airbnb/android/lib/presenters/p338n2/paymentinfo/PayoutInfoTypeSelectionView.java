package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.PayoutInfoTypeSelectionView */
public class PayoutInfoTypeSelectionView extends BaseSelectionView<SimpleSelectionViewItem> {
    public List<PayoutInfoType> payoutInfoTypes;

    public PayoutInfoTypeSelectionView(Context context) {
        this(context, null);
    }

    public PayoutInfoTypeSelectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayoutInfoTypeSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPayoutInfoTypes(List<PayoutInfoType> payoutInfoTypes2) {
        this.payoutInfoTypes = payoutInfoTypes2;
        setItems(FluentIterable.from((Iterable<E>) payoutInfoTypes2).transform(PayoutInfoTypeSelectionView$$Lambda$1.lambdaFactory$()).toList());
    }

    static /* synthetic */ SimpleSelectionViewItem lambda$setPayoutInfoTypes$0(PayoutInfoType payoutInfoType) {
        return new SimpleSelectionViewItem(payoutInfoType.getPayoutMethodText(), payoutInfoType);
    }
}
