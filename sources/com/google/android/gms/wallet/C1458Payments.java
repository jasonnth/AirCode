package com.google.android.gms.wallet;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* renamed from: com.google.android.gms.wallet.Payments */
public interface C1458Payments {
    void changeMaskedWallet(GoogleApiClient googleApiClient, String str, String str2, int i);

    @Deprecated
    PendingResult<BooleanResult> isReadyToPay(GoogleApiClient googleApiClient);

    void loadFullWallet(GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i);

    void loadMaskedWallet(GoogleApiClient googleApiClient, MaskedWalletRequest maskedWalletRequest, int i);
}
