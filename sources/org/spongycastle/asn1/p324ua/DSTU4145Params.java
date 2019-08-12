package org.spongycastle.asn1.p324ua;

import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145Params */
public class DSTU4145Params extends ASN1Object {
    private static final byte[] DEFAULT_DKE = {-87, ISO7816.INS_UPDATE_BINARY, -21, 69, -15, 60, ISO7816.INS_MANAGE_CHANNEL, -126, ISOFileInfo.DATA_BYTES1, -60, -106, 123, 35, 31, 94, -83, -10, 88, -21, -92, ISO7816.INS_GET_RESPONSE, 55, 41, PassportService.SF_SOD, 56, -39, 107, -16, 37, ISO7816.INS_GET_DATA, 78, 23, -8, -23, 114, 13, -58, 21, ISO7816.INS_READ_BINARY_STAMPED, 58, 40, -105, 95, PassportService.SF_DG11, -63, -34, -93, ISOFileInfo.FMD_BYTE, 56, -75, ISOFileInfo.FMD_BYTE, -22, ISO7816.INS_UNBLOCK_CHV, 23, -97, ISO7816.INS_WRITE_BINARY, 18, 62, 109, -72, -6, -59, 121, 4};
    private byte[] dke = DEFAULT_DKE;
    private DSTU4145ECBinary ecbinary;
    private ASN1ObjectIdentifier namedCurve;

    public DSTU4145Params(ASN1ObjectIdentifier namedCurve2) {
        this.namedCurve = namedCurve2;
    }

    public DSTU4145Params(ASN1ObjectIdentifier namedCurve2, byte[] dke2) {
        this.namedCurve = namedCurve2;
        this.dke = Arrays.clone(dke2);
    }

    public DSTU4145Params(DSTU4145ECBinary ecbinary2) {
        this.ecbinary = ecbinary2;
    }

    public boolean isNamedCurve() {
        return this.namedCurve != null;
    }

    public DSTU4145ECBinary getECBinary() {
        return this.ecbinary;
    }

    public byte[] getDKE() {
        return this.dke;
    }

    public static byte[] getDefaultDKE() {
        return DEFAULT_DKE;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.namedCurve;
    }

    public static DSTU4145Params getInstance(Object obj) {
        DSTU4145Params params;
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj != null) {
            ASN1Sequence seq = ASN1Sequence.getInstance(obj);
            if (seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) {
                params = new DSTU4145Params(ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0)));
            } else {
                params = new DSTU4145Params(DSTU4145ECBinary.getInstance(seq.getObjectAt(0)));
            }
            if (seq.size() == 2) {
                params.dke = ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets();
                if (params.dke.length != DEFAULT_DKE.length) {
                    throw new IllegalArgumentException("object parse error");
                }
            }
            return params;
        }
        throw new IllegalArgumentException("object parse error");
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.namedCurve != null) {
            v.add(this.namedCurve);
        } else {
            v.add(this.ecbinary);
        }
        if (!Arrays.areEqual(this.dke, DEFAULT_DKE)) {
            v.add(new DEROctetString(this.dke));
        }
        return new DERSequence(v);
    }
}
