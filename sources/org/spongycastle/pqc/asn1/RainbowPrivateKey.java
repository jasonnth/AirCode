package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.crypto.rainbow.Layer;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;

public class RainbowPrivateKey extends ASN1Object {

    /* renamed from: b1 */
    private byte[] f7076b1;

    /* renamed from: b2 */
    private byte[] f7077b2;
    private byte[][] invA1;
    private byte[][] invA2;
    private Layer[] layers;
    private ASN1ObjectIdentifier oid;
    private ASN1Integer version;

    /* renamed from: vi */
    private byte[] f7078vi;

    private RainbowPrivateKey(ASN1Sequence seq) {
        if (seq.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(seq.getObjectAt(0));
        } else {
            this.oid = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        }
        ASN1Sequence asnA1 = (ASN1Sequence) seq.getObjectAt(1);
        this.invA1 = new byte[asnA1.size()][];
        for (int i = 0; i < asnA1.size(); i++) {
            this.invA1[i] = ((ASN1OctetString) asnA1.getObjectAt(i)).getOctets();
        }
        this.f7076b1 = ((ASN1OctetString) ((ASN1Sequence) seq.getObjectAt(2)).getObjectAt(0)).getOctets();
        ASN1Sequence asnA2 = (ASN1Sequence) seq.getObjectAt(3);
        this.invA2 = new byte[asnA2.size()][];
        for (int j = 0; j < asnA2.size(); j++) {
            this.invA2[j] = ((ASN1OctetString) asnA2.getObjectAt(j)).getOctets();
        }
        this.f7077b2 = ((ASN1OctetString) ((ASN1Sequence) seq.getObjectAt(4)).getObjectAt(0)).getOctets();
        this.f7078vi = ((ASN1OctetString) ((ASN1Sequence) seq.getObjectAt(5)).getObjectAt(0)).getOctets();
        ASN1Sequence asnLayers = (ASN1Sequence) seq.getObjectAt(6);
        byte[][][][] alphas = new byte[asnLayers.size()][][][];
        byte[][][][] betas = new byte[asnLayers.size()][][][];
        byte[][][] gammas = new byte[asnLayers.size()][][];
        byte[][] etas = new byte[asnLayers.size()][];
        for (int l = 0; l < asnLayers.size(); l++) {
            ASN1Sequence asnLayer = (ASN1Sequence) asnLayers.getObjectAt(l);
            ASN1Sequence alphas3d = (ASN1Sequence) asnLayer.getObjectAt(0);
            alphas[l] = new byte[alphas3d.size()][][];
            for (int m = 0; m < alphas3d.size(); m++) {
                ASN1Sequence alphas2d = (ASN1Sequence) alphas3d.getObjectAt(m);
                alphas[l][m] = new byte[alphas2d.size()][];
                for (int n = 0; n < alphas2d.size(); n++) {
                    alphas[l][m][n] = ((ASN1OctetString) alphas2d.getObjectAt(n)).getOctets();
                }
            }
            ASN1Sequence betas3d = (ASN1Sequence) asnLayer.getObjectAt(1);
            betas[l] = new byte[betas3d.size()][][];
            for (int mb = 0; mb < betas3d.size(); mb++) {
                ASN1Sequence betas2d = (ASN1Sequence) betas3d.getObjectAt(mb);
                betas[l][mb] = new byte[betas2d.size()][];
                for (int nb = 0; nb < betas2d.size(); nb++) {
                    betas[l][mb][nb] = ((ASN1OctetString) betas2d.getObjectAt(nb)).getOctets();
                }
            }
            ASN1Sequence gammas2d = (ASN1Sequence) asnLayer.getObjectAt(2);
            gammas[l] = new byte[gammas2d.size()][];
            for (int mg = 0; mg < gammas2d.size(); mg++) {
                gammas[l][mg] = ((ASN1OctetString) gammas2d.getObjectAt(mg)).getOctets();
            }
            etas[l] = ((ASN1OctetString) asnLayer.getObjectAt(3)).getOctets();
        }
        int numOfLayers = this.f7078vi.length - 1;
        this.layers = new Layer[numOfLayers];
        for (int i2 = 0; i2 < numOfLayers; i2++) {
            this.layers[i2] = new Layer(this.f7078vi[i2], this.f7078vi[i2 + 1], RainbowUtil.convertArray(alphas[i2]), RainbowUtil.convertArray(betas[i2]), RainbowUtil.convertArray(gammas[i2]), RainbowUtil.convertArray(etas[i2]));
        }
    }

    public RainbowPrivateKey(short[][] invA12, short[] b1, short[][] invA22, short[] b2, int[] vi, Layer[] layers2) {
        this.version = new ASN1Integer(1);
        this.invA1 = RainbowUtil.convertArray(invA12);
        this.f7076b1 = RainbowUtil.convertArray(b1);
        this.invA2 = RainbowUtil.convertArray(invA22);
        this.f7077b2 = RainbowUtil.convertArray(b2);
        this.f7078vi = RainbowUtil.convertIntArray(vi);
        this.layers = layers2;
    }

    public static RainbowPrivateKey getInstance(Object o) {
        if (o instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey) o;
        }
        if (o != null) {
            return new RainbowPrivateKey(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public short[][] getInvA1() {
        return RainbowUtil.convertArray(this.invA1);
    }

    public short[] getB1() {
        return RainbowUtil.convertArray(this.f7076b1);
    }

    public short[] getB2() {
        return RainbowUtil.convertArray(this.f7077b2);
    }

    public short[][] getInvA2() {
        return RainbowUtil.convertArray(this.invA2);
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return RainbowUtil.convertArraytoInt(this.f7078vi);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.version != null) {
            v.add(this.version);
        } else {
            v.add(this.oid);
        }
        ASN1EncodableVector asnA1 = new ASN1EncodableVector();
        for (int i = 0; i < this.invA1.length; i++) {
            asnA1.add(new DEROctetString(this.invA1[i]));
        }
        DERSequence dERSequence = new DERSequence(asnA1);
        v.add(dERSequence);
        ASN1EncodableVector asnb1 = new ASN1EncodableVector();
        asnb1.add(new DEROctetString(this.f7076b1));
        DERSequence dERSequence2 = new DERSequence(asnb1);
        v.add(dERSequence2);
        ASN1EncodableVector asnA2 = new ASN1EncodableVector();
        for (int i2 = 0; i2 < this.invA2.length; i2++) {
            asnA2.add(new DEROctetString(this.invA2[i2]));
        }
        DERSequence dERSequence3 = new DERSequence(asnA2);
        v.add(dERSequence3);
        ASN1EncodableVector asnb2 = new ASN1EncodableVector();
        asnb2.add(new DEROctetString(this.f7077b2));
        DERSequence dERSequence4 = new DERSequence(asnb2);
        v.add(dERSequence4);
        ASN1EncodableVector asnvi = new ASN1EncodableVector();
        asnvi.add(new DEROctetString(this.f7078vi));
        DERSequence dERSequence5 = new DERSequence(asnvi);
        v.add(dERSequence5);
        ASN1EncodableVector asnLayers = new ASN1EncodableVector();
        for (int l = 0; l < this.layers.length; l++) {
            ASN1EncodableVector aLayer = new ASN1EncodableVector();
            byte[][][] alphas = RainbowUtil.convertArray(this.layers[l].getCoeffAlpha());
            ASN1EncodableVector alphas3d = new ASN1EncodableVector();
            for (int i3 = 0; i3 < alphas.length; i3++) {
                ASN1EncodableVector alphas2d = new ASN1EncodableVector();
                for (int j = 0; j < alphas[i3].length; j++) {
                    alphas2d.add(new DEROctetString(alphas[i3][j]));
                }
                DERSequence dERSequence6 = new DERSequence(alphas2d);
                alphas3d.add(dERSequence6);
            }
            DERSequence dERSequence7 = new DERSequence(alphas3d);
            aLayer.add(dERSequence7);
            byte[][][] betas = RainbowUtil.convertArray(this.layers[l].getCoeffBeta());
            ASN1EncodableVector betas3d = new ASN1EncodableVector();
            for (int i4 = 0; i4 < betas.length; i4++) {
                ASN1EncodableVector betas2d = new ASN1EncodableVector();
                for (int j2 = 0; j2 < betas[i4].length; j2++) {
                    betas2d.add(new DEROctetString(betas[i4][j2]));
                }
                DERSequence dERSequence8 = new DERSequence(betas2d);
                betas3d.add(dERSequence8);
            }
            DERSequence dERSequence9 = new DERSequence(betas3d);
            aLayer.add(dERSequence9);
            byte[][] gammas = RainbowUtil.convertArray(this.layers[l].getCoeffGamma());
            ASN1EncodableVector asnG = new ASN1EncodableVector();
            for (int i5 = 0; i5 < gammas.length; i5++) {
                asnG.add(new DEROctetString(gammas[i5]));
            }
            DERSequence dERSequence10 = new DERSequence(asnG);
            aLayer.add(dERSequence10);
            aLayer.add(new DEROctetString(RainbowUtil.convertArray(this.layers[l].getCoeffEta())));
            DERSequence dERSequence11 = new DERSequence(aLayer);
            asnLayers.add(dERSequence11);
        }
        DERSequence dERSequence12 = new DERSequence(asnLayers);
        v.add(dERSequence12);
        DERSequence dERSequence13 = new DERSequence(v);
        return dERSequence13;
    }
}
