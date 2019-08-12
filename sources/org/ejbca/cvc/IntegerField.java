package org.ejbca.cvc;

import java.math.BigInteger;

public class IntegerField extends AbstractDataField {
    private int intValue;

    IntegerField(CVCTagEnum cVCTagEnum, int i) {
        super(cVCTagEnum);
        this.intValue = i;
    }

    IntegerField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        super(cVCTagEnum);
        if (bArr == null || bArr.length <= 4) {
            this.intValue = new BigInteger(1, bArr).intValue();
            return;
        }
        throw new IllegalArgumentException("Byte array too long, max is 4, was " + bArr.length);
    }

    public void setValue(int i) {
        this.intValue = i;
    }

    public int getValue() {
        return this.intValue;
    }

    /* access modifiers changed from: protected */
    public byte[] getEncoded() {
        return toByteArray(Integer.valueOf(this.intValue));
    }

    /* access modifiers changed from: protected */
    public String valueAsText() {
        return "" + this.intValue;
    }
}
