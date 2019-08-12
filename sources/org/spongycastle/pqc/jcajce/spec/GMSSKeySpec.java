package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;

public class GMSSKeySpec implements KeySpec {
    private GMSSParameters gmssParameterSet;

    protected GMSSKeySpec(GMSSParameters gmssParameterSet2) {
        this.gmssParameterSet = gmssParameterSet2;
    }

    public GMSSParameters getParameters() {
        return this.gmssParameterSet;
    }
}
