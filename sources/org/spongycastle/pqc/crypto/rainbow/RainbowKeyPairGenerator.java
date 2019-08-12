package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: A1 */
    private short[][] f7172A1;
    private short[][] A1inv;

    /* renamed from: A2 */
    private short[][] f7173A2;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f7174b1;

    /* renamed from: b2 */
    private short[] f7175b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;

    /* renamed from: sr */
    private SecureRandom f7176sr;

    /* renamed from: vi */
    private int[] f7177vi;

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(this.f7177vi[this.f7177vi.length - 1] - this.f7177vi[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) new RainbowPrivateKeyParameters(this.A1inv, this.f7174b1, this.A2inv, this.f7175b2, this.f7177vi, this.layers));
    }

    public void initialize(KeyGenerationParameters param) {
        this.rainbowParams = (RainbowKeyGenerationParameters) param;
        this.f7176sr = new SecureRandom();
        this.f7177vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    private void generateL1() {
        int dim = this.f7177vi[this.f7177vi.length - 1] - this.f7177vi[0];
        this.f7172A1 = (short[][]) Array.newInstance(Short.TYPE, new int[]{dim, dim});
        this.A1inv = null;
        ComputeInField c = new ComputeInField();
        while (this.A1inv == null) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    this.f7172A1[i][j] = (short) (this.f7176sr.nextInt() & 255);
                }
            }
            this.A1inv = c.inverse(this.f7172A1);
        }
        this.f7174b1 = new short[dim];
        for (int i2 = 0; i2 < dim; i2++) {
            this.f7174b1[i2] = (short) (this.f7176sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int dim = this.f7177vi[this.f7177vi.length - 1];
        this.f7173A2 = (short[][]) Array.newInstance(Short.TYPE, new int[]{dim, dim});
        this.A2inv = null;
        ComputeInField c = new ComputeInField();
        while (this.A2inv == null) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    this.f7173A2[i][j] = (short) (this.f7176sr.nextInt() & 255);
                }
            }
            this.A2inv = c.inverse(this.f7173A2);
        }
        this.f7175b2 = new short[dim];
        for (int i2 = 0; i2 < dim; i2++) {
            this.f7175b2[i2] = (short) (this.f7176sr.nextInt() & 255);
        }
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        for (int i = 0; i < this.numOfLayers; i++) {
            this.layers[i] = new Layer(this.f7177vi[i], this.f7177vi[i + 1], this.f7176sr);
        }
    }

    private void computePublicKey() {
        ComputeInField c = new ComputeInField();
        int rows = this.f7177vi[this.f7177vi.length - 1] - this.f7177vi[0];
        int vars = this.f7177vi[this.f7177vi.length - 1];
        short[][][] coeff_quadratic_3dim = (short[][][]) Array.newInstance(Short.TYPE, new int[]{rows, vars, vars});
        this.pub_singular = (short[][]) Array.newInstance(Short.TYPE, new int[]{rows, vars});
        this.pub_scalar = new short[rows];
        int crnt_row = 0;
        short[] sArr = new short[vars];
        for (int l = 0; l < this.layers.length; l++) {
            short[][][] coeff_alpha = this.layers[l].getCoeffAlpha();
            short[][][] coeff_beta = this.layers[l].getCoeffBeta();
            short[][] coeff_gamma = this.layers[l].getCoeffGamma();
            short[] coeff_eta = this.layers[l].getCoeffEta();
            int oils = coeff_alpha[0].length;
            int vins = coeff_beta[0].length;
            for (int p = 0; p < oils; p++) {
                for (int x1 = 0; x1 < oils; x1++) {
                    for (int x2 = 0; x2 < vins; x2++) {
                        short[] vect_tmp = c.multVect(coeff_alpha[p][x1][x2], this.f7173A2[x1 + vins]);
                        coeff_quadratic_3dim[crnt_row + p] = c.addSquareMatrix(coeff_quadratic_3dim[crnt_row + p], c.multVects(vect_tmp, this.f7173A2[x2]));
                        this.pub_singular[crnt_row + p] = c.addVect(c.multVect(this.f7175b2[x2], vect_tmp), this.pub_singular[crnt_row + p]);
                        this.pub_singular[crnt_row + p] = c.addVect(c.multVect(this.f7175b2[x1 + vins], c.multVect(coeff_alpha[p][x1][x2], this.f7173A2[x2])), this.pub_singular[crnt_row + p]);
                        this.pub_scalar[crnt_row + p] = GF2Field.addElem(this.pub_scalar[crnt_row + p], GF2Field.multElem(GF2Field.multElem(coeff_alpha[p][x1][x2], this.f7175b2[x1 + vins]), this.f7175b2[x2]));
                    }
                }
                for (int x12 = 0; x12 < vins; x12++) {
                    for (int x22 = 0; x22 < vins; x22++) {
                        short[] vect_tmp2 = c.multVect(coeff_beta[p][x12][x22], this.f7173A2[x12]);
                        coeff_quadratic_3dim[crnt_row + p] = c.addSquareMatrix(coeff_quadratic_3dim[crnt_row + p], c.multVects(vect_tmp2, this.f7173A2[x22]));
                        this.pub_singular[crnt_row + p] = c.addVect(c.multVect(this.f7175b2[x22], vect_tmp2), this.pub_singular[crnt_row + p]);
                        this.pub_singular[crnt_row + p] = c.addVect(c.multVect(this.f7175b2[x12], c.multVect(coeff_beta[p][x12][x22], this.f7173A2[x22])), this.pub_singular[crnt_row + p]);
                        this.pub_scalar[crnt_row + p] = GF2Field.addElem(this.pub_scalar[crnt_row + p], GF2Field.multElem(GF2Field.multElem(coeff_beta[p][x12][x22], this.f7175b2[x12]), this.f7175b2[x22]));
                    }
                }
                for (int n = 0; n < vins + oils; n++) {
                    this.pub_singular[crnt_row + p] = c.addVect(c.multVect(coeff_gamma[p][n], this.f7173A2[n]), this.pub_singular[crnt_row + p]);
                    this.pub_scalar[crnt_row + p] = GF2Field.addElem(this.pub_scalar[crnt_row + p], GF2Field.multElem(coeff_gamma[p][n], this.f7175b2[n]));
                }
                this.pub_scalar[crnt_row + p] = GF2Field.addElem(this.pub_scalar[crnt_row + p], coeff_eta[p]);
            }
            crnt_row += oils;
        }
        short[][][] tmp_c_quad = (short[][][]) Array.newInstance(Short.TYPE, new int[]{rows, vars, vars});
        short[][] tmp_c_sing = (short[][]) Array.newInstance(Short.TYPE, new int[]{rows, vars});
        short[] tmp_c_scal = new short[rows];
        for (int r = 0; r < rows; r++) {
            for (int q = 0; q < this.f7172A1.length; q++) {
                tmp_c_quad[r] = c.addSquareMatrix(tmp_c_quad[r], c.multMatrix(this.f7172A1[r][q], coeff_quadratic_3dim[q]));
                tmp_c_sing[r] = c.addVect(tmp_c_sing[r], c.multVect(this.f7172A1[r][q], this.pub_singular[q]));
                tmp_c_scal[r] = GF2Field.addElem(tmp_c_scal[r], GF2Field.multElem(this.f7172A1[r][q], this.pub_scalar[q]));
            }
            tmp_c_scal[r] = GF2Field.addElem(tmp_c_scal[r], this.f7174b1[r]);
        }
        short[][][] coeff_quadratic_3dim2 = tmp_c_quad;
        this.pub_singular = tmp_c_sing;
        this.pub_scalar = tmp_c_scal;
        compactPublicKey(coeff_quadratic_3dim2);
    }

    private void compactPublicKey(short[][][] coeff_quadratic_to_compact) {
        int polynomials = coeff_quadratic_to_compact.length;
        int n = coeff_quadratic_to_compact[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance(Short.TYPE, new int[]{polynomials, ((n + 1) * n) / 2});
        for (int p = 0; p < polynomials; p++) {
            int offset = 0;
            for (int x = 0; x < n; x++) {
                for (int y = x; y < n; y++) {
                    if (y == x) {
                        this.pub_quadratic[p][offset] = coeff_quadratic_to_compact[p][x][y];
                    } else {
                        this.pub_quadratic[p][offset] = GF2Field.addElem(coeff_quadratic_to_compact[p][x][y], coeff_quadratic_to_compact[p][y][x]);
                    }
                    offset++;
                }
            }
        }
    }

    public void init(KeyGenerationParameters param) {
        initialize(param);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }
}
