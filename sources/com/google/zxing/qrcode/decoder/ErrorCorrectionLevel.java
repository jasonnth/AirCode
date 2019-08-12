package com.google.zxing.qrcode.decoder;

public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final ErrorCorrectionLevel[] FOR_BITS = null;
    private final int bits;

    static {
        FOR_BITS = new ErrorCorrectionLevel[]{M, L, H, Q};
    }

    private ErrorCorrectionLevel(int bits2) {
        this.bits = bits2;
    }

    public int getBits() {
        return this.bits;
    }
}
