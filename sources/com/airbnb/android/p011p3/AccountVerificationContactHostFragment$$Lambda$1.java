package com.airbnb.android.p011p3;

import com.airbnb.android.core.responses.GuestReservationsResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment$$Lambda$1 */
final /* synthetic */ class AccountVerificationContactHostFragment$$Lambda$1 implements Action1 {
    private final AccountVerificationContactHostFragment arg$1;

    private AccountVerificationContactHostFragment$$Lambda$1(AccountVerificationContactHostFragment accountVerificationContactHostFragment) {
        this.arg$1 = accountVerificationContactHostFragment;
    }

    public static Action1 lambdaFactory$(AccountVerificationContactHostFragment accountVerificationContactHostFragment) {
        return new AccountVerificationContactHostFragment$$Lambda$1(accountVerificationContactHostFragment);
    }

    public void call(Object obj) {
        AccountVerificationContactHostFragment.lambda$new$0(this.arg$1, (GuestReservationsResponse) obj);
    }
}
