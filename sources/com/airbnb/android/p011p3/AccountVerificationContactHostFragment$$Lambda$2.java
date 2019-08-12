package com.airbnb.android.p011p3;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment$$Lambda$2 */
final /* synthetic */ class AccountVerificationContactHostFragment$$Lambda$2 implements Action1 {
    private final AccountVerificationContactHostFragment arg$1;

    private AccountVerificationContactHostFragment$$Lambda$2(AccountVerificationContactHostFragment accountVerificationContactHostFragment) {
        this.arg$1 = accountVerificationContactHostFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationContactHostFragment accountVerificationContactHostFragment) {
        return new AccountVerificationContactHostFragment$$Lambda$2(accountVerificationContactHostFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastGenericNetworkError(this.arg$1.getContext());
    }
}
