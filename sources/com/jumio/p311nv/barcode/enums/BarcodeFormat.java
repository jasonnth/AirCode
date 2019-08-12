package com.jumio.p311nv.barcode.enums;

/* renamed from: com.jumio.nv.barcode.enums.BarcodeFormat */
public enum BarcodeFormat {
    UPCA(1),
    UPCE(2),
    ITF(3),
    EAN8(4),
    EAN13(5),
    CODE39(6),
    CODE128(7),
    QR(8),
    PDF417(9);
    
    private int type;

    private BarcodeFormat(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
