package com.airbnb.android.managelisting.settings;

import com.google.common.base.Predicate;

final /* synthetic */ class CancellationPolicyAdapter$$Lambda$2 implements Predicate {
    private final CancellationPolicyAdapter arg$1;

    private CancellationPolicyAdapter$$Lambda$2(CancellationPolicyAdapter cancellationPolicyAdapter) {
        this.arg$1 = cancellationPolicyAdapter;
    }

    public static Predicate lambdaFactory$(CancellationPolicyAdapter cancellationPolicyAdapter) {
        return new CancellationPolicyAdapter$$Lambda$2(cancellationPolicyAdapter);
    }

    public boolean apply(Object obj) {
        return CancellationPolicyAdapter.lambda$addCancellationPolicyModels$0(this.arg$1, (String) obj);
    }
}
