package com.airbnb.android.core.models.payments;

public abstract class BraintreePaymentInstrument extends OldPaymentInstrument {
    private String deviceData;
    private String nonce;

    public String getNonce() {
        return this.nonce;
    }

    public void setNonce(String nonce2) {
        this.nonce = nonce2;
    }

    public String getDeviceData() {
        return this.deviceData;
    }

    public void setDeviceData(String deviceData2) {
        this.deviceData = deviceData2;
    }
}
