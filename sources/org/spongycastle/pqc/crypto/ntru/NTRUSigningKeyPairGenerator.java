package org.spongycastle.pqc.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters.Basis;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Resultant;

public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    private class BasisGenerationTask implements Callable<Basis> {
        private BasisGenerationTask() {
        }

        public Basis call() throws Exception {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public class FGBasis extends Basis {

        /* renamed from: F */
        public IntegerPolynomial f7158F;

        /* renamed from: G */
        public IntegerPolynomial f7159G;

        FGBasis(Polynomial f, Polynomial fPrime, IntegerPolynomial h, IntegerPolynomial F, IntegerPolynomial G, NTRUSigningKeyGenerationParameters params) {
            super(f, fPrime, h, params);
            this.f7158F = F;
            this.f7159G = G;
        }

        /* access modifiers changed from: 0000 */
        public boolean isNormOk() {
            double keyNormBoundSq = this.params.keyNormBoundSq;
            int q = this.params.f7157q;
            return ((double) this.f7158F.centeredNormSq(q)) < keyNormBoundSq && ((double) this.f7159G.centeredNormSq(q)) < keyNormBoundSq;
        }
    }

    public void init(KeyGenerationParameters param) {
        this.params = (NTRUSigningKeyGenerationParameters) param;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters pub = null;
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Basis>> bases = new ArrayList<>();
        for (int k = this.params.f7151B; k >= 0; k--) {
            bases.add(executor.submit(new BasisGenerationTask()));
        }
        executor.shutdown();
        List<Basis> basises = new ArrayList<>();
        int k2 = this.params.f7151B;
        while (k2 >= 0) {
            Future<Basis> basis = (Future) bases.get(k2);
            try {
                basises.add(basis.get());
                if (k2 == this.params.f7151B) {
                    pub = new NTRUSigningPublicKeyParameters(((Basis) basis.get()).f7168h, this.params.getSigningParameters());
                }
                k2--;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) pub, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(basises, pub));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        List<Basis> basises = new ArrayList<>();
        NTRUSigningPublicKeyParameters pub = null;
        for (int k = this.params.f7151B; k >= 0; k--) {
            Basis basis = generateBoundedBasis();
            basises.add(basis);
            if (k == 0) {
                pub = new NTRUSigningPublicKeyParameters(basis.f7168h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) pub, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(basises, pub));
    }

    private void minimizeFG(IntegerPolynomial f, IntegerPolynomial g, IntegerPolynomial F, IntegerPolynomial G, int N) {
        int E = 0;
        for (int j = 0; j < N; j++) {
            E += N * 2 * ((f.coeffs[j] * f.coeffs[j]) + (g.coeffs[j] * g.coeffs[j]));
        }
        int E2 = E - 4;
        IntegerPolynomial u = (IntegerPolynomial) f.clone();
        IntegerPolynomial v = (IntegerPolynomial) g.clone();
        int j2 = 0;
        int k = 0;
        int maxAdjustment = N;
        while (k < maxAdjustment && j2 < N) {
            int D = 0;
            for (int i = 0; i < N; i++) {
                D += N * 4 * ((F.coeffs[i] * f.coeffs[i]) + (G.coeffs[i] * g.coeffs[i]));
            }
            int D2 = D - ((F.sumCoeffs() + G.sumCoeffs()) * 4);
            if (D2 > E2) {
                F.sub(u);
                G.sub(v);
                k++;
                j2 = 0;
            } else if (D2 < (-E2)) {
                F.add(u);
                G.add(v);
                k++;
                j2 = 0;
            }
            j2++;
            u.rotate1();
            v.rotate1();
        }
    }

    private FGBasis generateBasis() {
        Polynomial f;
        IntegerPolynomial fInt;
        IntegerPolynomial fq;
        Polynomial g;
        IntegerPolynomial gInt;
        Resultant rg;
        BigIntEuclidean r;
        BigIntPolynomial C;
        Polynomial fPrime;
        IntegerPolynomial h;
        int N = this.params.f7152N;
        int q = this.params.f7157q;
        int d = this.params.f7153d;
        int d1 = this.params.f7154d1;
        int d2 = this.params.f7155d2;
        int d3 = this.params.f7156d3;
        int basisType = this.params.basisType;
        int _2n1 = (N * 2) + 1;
        boolean primeCheck = this.params.primeCheck;
        while (true) {
            if (this.params.polyType == 0) {
                f = DenseTernaryPolynomial.generateRandom(N, d + 1, d, new SecureRandom());
            } else {
                f = ProductFormPolynomial.generateRandom(N, d1, d2, d3 + 1, d3, new SecureRandom());
            }
            fInt = f.toIntegerPolynomial();
            if (!primeCheck || !fInt.resultant(_2n1).res.equals(BigInteger.ZERO)) {
                fq = fInt.invertFq(q);
                if (fq != null) {
                    break;
                }
            }
        }
        Resultant rf = fInt.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                g = DenseTernaryPolynomial.generateRandom(N, d + 1, d, new SecureRandom());
            } else {
                g = ProductFormPolynomial.generateRandom(N, d1, d2, d3 + 1, d3, new SecureRandom());
            }
            gInt = g.toIntegerPolynomial();
            if ((!primeCheck || !gInt.resultant(_2n1).res.equals(BigInteger.ZERO)) && gInt.invertFq(q) != null) {
                rg = gInt.resultant();
                r = BigIntEuclidean.calculate(rf.res, rg.res);
                if (r.gcd.equals(BigInteger.ONE)) {
                    break;
                }
            }
        }
        BigIntPolynomial A = (BigIntPolynomial) rf.rho.clone();
        A.mult(r.f7234x.multiply(BigInteger.valueOf((long) q)));
        BigIntPolynomial B = (BigIntPolynomial) rg.rho.clone();
        B.mult(r.f7235y.multiply(BigInteger.valueOf((long) (-q))));
        if (this.params.keyGenAlg == 0) {
            int[] fRevCoeffs = new int[N];
            int[] gRevCoeffs = new int[N];
            fRevCoeffs[0] = fInt.coeffs[0];
            gRevCoeffs[0] = gInt.coeffs[0];
            for (int i = 1; i < N; i++) {
                fRevCoeffs[i] = fInt.coeffs[N - i];
                gRevCoeffs[i] = gInt.coeffs[N - i];
            }
            IntegerPolynomial fRev = new IntegerPolynomial(fRevCoeffs);
            IntegerPolynomial gRev = new IntegerPolynomial(gRevCoeffs);
            IntegerPolynomial t = f.mult(fRev);
            t.add(g.mult(gRev));
            Resultant rt = t.resultant();
            BigIntPolynomial C2 = fRev.mult(B);
            C2.add(gRev.mult(A));
            C = C2.mult(rt.rho);
            C.div(rt.res);
        } else {
            int log10N = 0;
            for (int i2 = 1; i2 < N; i2 *= 10) {
                log10N++;
            }
            BigDecimalPolynomial fInv = rf.rho.div(new BigDecimal(rf.res), B.getMaxCoeffLength() + 1 + log10N);
            BigDecimalPolynomial gInv = rg.rho.div(new BigDecimal(rg.res), A.getMaxCoeffLength() + 1 + log10N);
            BigDecimalPolynomial Cdec = fInv.mult(B);
            Cdec.add(gInv.mult(A));
            Cdec.halve();
            C = Cdec.round();
        }
        BigIntPolynomial F = (BigIntPolynomial) B.clone();
        F.sub(f.mult(C));
        BigIntPolynomial G = (BigIntPolynomial) A.clone();
        G.sub(g.mult(C));
        IntegerPolynomial FInt = new IntegerPolynomial(F);
        IntegerPolynomial GInt = new IntegerPolynomial(G);
        minimizeFG(fInt, gInt, FInt, GInt, N);
        if (basisType == 0) {
            fPrime = FInt;
            h = g.mult(fq, q);
        } else {
            fPrime = g;
            h = FInt.mult(fq, q);
        }
        h.modPositive(q);
        return new FGBasis(f, fPrime, h, FInt, GInt, this.params);
    }

    public Basis generateBoundedBasis() {
        FGBasis basis;
        do {
            basis = generateBasis();
        } while (!basis.isNormOk());
        return basis;
    }
}
