package org.ejbca.cvc;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractDataField extends CVCObject {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public abstract byte[] getEncoded();

    /* access modifiers changed from: protected */
    public abstract String valueAsText();

    public AbstractDataField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
    }

    /* access modifiers changed from: protected */
    public int encode(DataOutputStream dataOutputStream) throws IOException {
        int size = dataOutputStream.size();
        dataOutputStream.write(toByteArray(Integer.valueOf(getTag().getValue())));
        byte[] encoded = getEncoded();
        dataOutputStream.write(encodeLength(encoded.length));
        dataOutputStream.write(encoded);
        return dataOutputStream.size() - size;
    }

    public String getAsText(String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.getAsText(str, z)).append(valueAsText());
        return stringBuffer.toString();
    }
}
