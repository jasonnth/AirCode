package com.airbnb.android.lib.payments.networking.createbill.requester;

import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters;

public interface CreateBillApi {
    void createBill(CreateBillParameters createBillParameters);
}
