package com.google.android.exoplayer.text.eia608;

final class ClosedCaptionCtrl extends ClosedCaption {
    public final byte cc1;
    public final byte cc2;

    protected ClosedCaptionCtrl(byte cc12, byte cc22) {
        super(0);
        this.cc1 = cc12;
        this.cc2 = cc22;
    }

    public boolean isMiscCode() {
        return (this.cc1 == 20 || this.cc1 == 28) && this.cc2 >= 32 && this.cc2 <= 47;
    }

    public boolean isPreambleAddressCode() {
        return this.cc1 >= 16 && this.cc1 <= 31 && this.cc2 >= 64 && this.cc2 <= Byte.MAX_VALUE;
    }

    public boolean isRepeatable() {
        return this.cc1 >= 16 && this.cc1 <= 31;
    }
}
