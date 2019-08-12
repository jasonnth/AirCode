package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

public class RainbowParameterSpec implements AlgorithmParameterSpec {
    private static final int[] DEFAULT_VI = {6, 12, 17, 22, 33};

    /* renamed from: vi */
    private int[] f7221vi;

    public RainbowParameterSpec() {
        this.f7221vi = DEFAULT_VI;
    }

    public RainbowParameterSpec(int[] vi) {
        this.f7221vi = vi;
        try {
            checkParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkParams() throws Exception {
        if (this.f7221vi == null) {
            throw new IllegalArgumentException("no layers defined.");
        } else if (this.f7221vi.length > 1) {
            for (int i = 0; i < this.f7221vi.length - 1; i++) {
                if (this.f7221vi[i] >= this.f7221vi[i + 1]) {
                    throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
                }
            }
        } else {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int getNumOfLayers() {
        return this.f7221vi.length - 1;
    }

    public int getDocumentLength() {
        return this.f7221vi[this.f7221vi.length - 1] - this.f7221vi[0];
    }

    public int[] getVi() {
        return Arrays.clone(this.f7221vi);
    }
}
