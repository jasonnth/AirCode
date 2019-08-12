package org.ejbca.cvc;

import java.math.BigInteger;
import org.ejbca.cvc.util.StringConverter;

public class ByteField extends AbstractDataField {
    private byte[] data;
    private boolean showBitLength;

    ByteField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
        this.showBitLength = false;
    }

    ByteField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        this(cVCTagEnum, bArr, false);
    }

    ByteField(CVCTagEnum cVCTagEnum, byte[] bArr, boolean z) {
        super(cVCTagEnum);
        this.showBitLength = false;
        this.data = bArr;
        this.showBitLength = z;
    }

    public boolean isShowBitLength() {
        return this.showBitLength;
    }

    public void setShowBitLength(boolean z) {
        this.showBitLength = z;
    }

    public byte[] getData() {
        return this.data;
    }

    /* access modifiers changed from: protected */
    public byte[] getEncoded() {
        return this.data;
    }

    /* access modifiers changed from: protected */
    public String valueAsText() {
        String str = "";
        if (this.showBitLength) {
            int i = 0;
            if (this.data != null) {
                i = new BigInteger(1, this.data).bitLength();
            }
            str = "[" + i + "]  ";
        }
        return str + StringConverter.byteToHex(this.data);
    }
}
