package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.CipherParameters;

public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;

    /* renamed from: vi */
    private int[] f7178vi;

    public RainbowParameters() {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f7178vi = this.DEFAULT_VI;
    }

    public RainbowParameters(int[] vi) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f7178vi = vi;
        try {
            checkParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkParams() throws Exception {
        if (this.f7178vi == null) {
            throw new Exception("no layers defined.");
        } else if (this.f7178vi.length > 1) {
            for (int i = 0; i < this.f7178vi.length - 1; i++) {
                if (this.f7178vi[i] >= this.f7178vi[i + 1]) {
                    throw new Exception("v[i] has to be smaller than v[i+1]");
                }
            }
        } else {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int getNumOfLayers() {
        return this.f7178vi.length - 1;
    }

    public int getDocLength() {
        return this.f7178vi[this.f7178vi.length - 1] - this.f7178vi[0];
    }

    public int[] getVi() {
        return this.f7178vi;
    }
}
