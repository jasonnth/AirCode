package com.airbnb.p027n2;

/* renamed from: com.airbnb.n2.DLSStyleWrapperImpl */
public class DLSStyleWrapperImpl implements DLSStyleWrapper {
    private final int backgroundRes;

    public static DLSStyleWrapperImpl from(DLSStyle baseStyle) {
        return new DLSStyleWrapperImpl(baseStyle);
    }

    private DLSStyleWrapperImpl(DLSStyle baseStyle) {
        switch (baseStyle) {
            case Sheet:
                this.backgroundRes = R.color.n2_babu;
                return;
            default:
                this.backgroundRes = R.color.n2_white;
                return;
        }
    }

    public int backgroundRes() {
        return this.backgroundRes;
    }
}
