package com.airbnb.android.booking.fragments.androidpay;

import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

public interface AndroidPayApiLegacy {
    void changeMaskedWallet();

    boolean isAndroidPayInitialized();

    void onFullWalletLoaded(FullWallet fullWallet);

    void onMaskedWalletLoaded(MaskedWallet maskedWallet);
}
