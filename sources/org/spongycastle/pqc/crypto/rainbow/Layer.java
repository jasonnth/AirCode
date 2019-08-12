package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.util.Arrays;

public class Layer {
    private short[][][] coeff_alpha;
    private short[][][] coeff_beta;
    private short[] coeff_eta;
    private short[][] coeff_gamma;

    /* renamed from: oi */
    private int f7170oi;

    /* renamed from: vi */
    private int f7171vi;
    private int viNext;

    public Layer(byte vi, byte viNext2, short[][][] coeffAlpha, short[][][] coeffBeta, short[][] coeffGamma, short[] coeffEta) {
        this.f7171vi = vi & 255;
        this.viNext = viNext2 & 255;
        this.f7170oi = this.viNext - this.f7171vi;
        this.coeff_alpha = coeffAlpha;
        this.coeff_beta = coeffBeta;
        this.coeff_gamma = coeffGamma;
        this.coeff_eta = coeffEta;
    }

    public Layer(int vi, int viNext2, SecureRandom sr) {
        this.f7171vi = vi;
        this.viNext = viNext2;
        this.f7170oi = viNext2 - vi;
        this.coeff_alpha = (short[][][]) Array.newInstance(Short.TYPE, new int[]{this.f7170oi, this.f7170oi, this.f7171vi});
        this.coeff_beta = (short[][][]) Array.newInstance(Short.TYPE, new int[]{this.f7170oi, this.f7171vi, this.f7171vi});
        this.coeff_gamma = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.f7170oi, this.viNext});
        this.coeff_eta = new short[this.f7170oi];
        int numOfPoly = this.f7170oi;
        for (int k = 0; k < numOfPoly; k++) {
            for (int i = 0; i < this.f7170oi; i++) {
                for (int j = 0; j < this.f7171vi; j++) {
                    this.coeff_alpha[k][i][j] = (short) (sr.nextInt() & 255);
                }
            }
        }
        for (int k2 = 0; k2 < numOfPoly; k2++) {
            for (int i2 = 0; i2 < this.f7171vi; i2++) {
                for (int j2 = 0; j2 < this.f7171vi; j2++) {
                    this.coeff_beta[k2][i2][j2] = (short) (sr.nextInt() & 255);
                }
            }
        }
        for (int k3 = 0; k3 < numOfPoly; k3++) {
            for (int i3 = 0; i3 < this.viNext; i3++) {
                this.coeff_gamma[k3][i3] = (short) (sr.nextInt() & 255);
            }
        }
        for (int k4 = 0; k4 < numOfPoly; k4++) {
            this.coeff_eta[k4] = (short) (sr.nextInt() & 255);
        }
    }

    public short[][] plugInVinegars(short[] x) {
        short[][] coeff = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.f7170oi, this.f7170oi + 1});
        short[] sum = new short[this.f7170oi];
        for (int k = 0; k < this.f7170oi; k++) {
            for (int i = 0; i < this.f7171vi; i++) {
                for (int j = 0; j < this.f7171vi; j++) {
                    sum[k] = GF2Field.addElem(sum[k], GF2Field.multElem(GF2Field.multElem(this.coeff_beta[k][i][j], x[i]), x[j]));
                }
            }
        }
        for (int k2 = 0; k2 < this.f7170oi; k2++) {
            for (int i2 = 0; i2 < this.f7170oi; i2++) {
                for (int j2 = 0; j2 < this.f7171vi; j2++) {
                    coeff[k2][i2] = GF2Field.addElem(coeff[k2][i2], GF2Field.multElem(this.coeff_alpha[k2][i2][j2], x[j2]));
                }
            }
        }
        for (int k3 = 0; k3 < this.f7170oi; k3++) {
            for (int i3 = 0; i3 < this.f7171vi; i3++) {
                sum[k3] = GF2Field.addElem(sum[k3], GF2Field.multElem(this.coeff_gamma[k3][i3], x[i3]));
            }
        }
        for (int k4 = 0; k4 < this.f7170oi; k4++) {
            for (int i4 = this.f7171vi; i4 < this.viNext; i4++) {
                coeff[k4][i4 - this.f7171vi] = GF2Field.addElem(this.coeff_gamma[k4][i4], coeff[k4][i4 - this.f7171vi]);
            }
        }
        for (int k5 = 0; k5 < this.f7170oi; k5++) {
            sum[k5] = GF2Field.addElem(sum[k5], this.coeff_eta[k5]);
        }
        for (int k6 = 0; k6 < this.f7170oi; k6++) {
            coeff[k6][this.f7170oi] = sum[k6];
        }
        return coeff;
    }

    public int getVi() {
        return this.f7171vi;
    }

    public int getViNext() {
        return this.viNext;
    }

    public int getOi() {
        return this.f7170oi;
    }

    public short[][][] getCoeffAlpha() {
        return this.coeff_alpha;
    }

    public short[][][] getCoeffBeta() {
        return this.coeff_beta;
    }

    public short[][] getCoeffGamma() {
        return this.coeff_gamma;
    }

    public short[] getCoeffEta() {
        return this.coeff_eta;
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Layer)) {
            return false;
        }
        Layer otherLayer = (Layer) other;
        if (this.f7171vi != otherLayer.getVi() || this.viNext != otherLayer.getViNext() || this.f7170oi != otherLayer.getOi() || !RainbowUtil.equals(this.coeff_alpha, otherLayer.getCoeffAlpha()) || !RainbowUtil.equals(this.coeff_beta, otherLayer.getCoeffBeta()) || !RainbowUtil.equals(this.coeff_gamma, otherLayer.getCoeffGamma()) || !RainbowUtil.equals(this.coeff_eta, otherLayer.getCoeffEta())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((this.f7171vi * 37) + this.viNext) * 37) + this.f7170oi) * 37) + Arrays.hashCode(this.coeff_alpha)) * 37) + Arrays.hashCode(this.coeff_beta)) * 37) + Arrays.hashCode(this.coeff_gamma)) * 37) + Arrays.hashCode(this.coeff_eta);
    }
}
