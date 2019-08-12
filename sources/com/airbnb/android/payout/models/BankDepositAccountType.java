package com.airbnb.android.payout.models;

import com.airbnb.android.payout.C7552R;

public enum BankDepositAccountType {
    Checking(C7552R.string.bank_deposit_account_type_checking),
    Savings(C7552R.string.bank_deposit_account_type_savings);
    
    private final int serverKey;

    private BankDepositAccountType(int serverKey2) {
        this.serverKey = serverKey2;
    }

    public int getServerKey() {
        return this.serverKey;
    }
}
