package com.airbnb.android.identity;

import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;

final /* synthetic */ class AccountVerificationPhoneNumberConfirmationFragment$$Lambda$8 implements BottomSheetItemClickListener {
    private final AccountVerificationPhoneNumberConfirmationFragment arg$1;

    private AccountVerificationPhoneNumberConfirmationFragment$$Lambda$8(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        this.arg$1 = accountVerificationPhoneNumberConfirmationFragment;
    }

    public static BottomSheetItemClickListener lambdaFactory$(AccountVerificationPhoneNumberConfirmationFragment accountVerificationPhoneNumberConfirmationFragment) {
        return new AccountVerificationPhoneNumberConfirmationFragment$$Lambda$8(accountVerificationPhoneNumberConfirmationFragment);
    }

    public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
        AccountVerificationPhoneNumberConfirmationFragment.lambda$createDialog$7(this.arg$1, bottomSheetMenuItem);
    }
}
