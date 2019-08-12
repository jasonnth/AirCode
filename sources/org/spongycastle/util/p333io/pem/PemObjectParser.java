package org.spongycastle.util.p333io.pem;

import java.io.IOException;

/* renamed from: org.spongycastle.util.io.pem.PemObjectParser */
public interface PemObjectParser {
    Object parseObject(PemObject pemObject) throws IOException;
}
