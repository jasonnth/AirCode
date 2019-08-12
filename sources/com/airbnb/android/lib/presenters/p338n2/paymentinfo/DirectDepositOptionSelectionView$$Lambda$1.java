package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import com.airbnb.android.lib.presenters.p338n2.paymentinfo.DirectDepositOptionSelectionView.DirectDepositOption;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.DirectDepositOptionSelectionView$$Lambda$1 */
final /* synthetic */ class DirectDepositOptionSelectionView$$Lambda$1 implements Function {
    private final DirectDepositOptionSelectionView arg$1;

    private DirectDepositOptionSelectionView$$Lambda$1(DirectDepositOptionSelectionView directDepositOptionSelectionView) {
        this.arg$1 = directDepositOptionSelectionView;
    }

    public static Function lambdaFactory$(DirectDepositOptionSelectionView directDepositOptionSelectionView) {
        return new DirectDepositOptionSelectionView$$Lambda$1(directDepositOptionSelectionView);
    }

    public Object apply(Object obj) {
        return DirectDepositOptionSelectionView.lambda$new$0(this.arg$1, (DirectDepositOption) obj);
    }
}
