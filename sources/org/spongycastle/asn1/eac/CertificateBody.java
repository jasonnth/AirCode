package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

public class CertificateBody extends ASN1Object {
    private static final int CAR = 2;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    private static final int CHA = 16;
    private static final int CHR = 8;
    private static final int CPI = 1;

    /* renamed from: PK */
    private static final int f6372PK = 4;
    public static final int profileType = 127;
    public static final int requestType = 13;
    private DERApplicationSpecific certificateEffectiveDate;
    private DERApplicationSpecific certificateExpirationDate;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private DERApplicationSpecific certificateHolderReference;
    private DERApplicationSpecific certificateProfileIdentifier;
    private int certificateType = 0;
    private DERApplicationSpecific certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    ASN1InputStream seq;

    private void setIso7816CertificateBody(ASN1ApplicationSpecific appSpe) throws IOException {
        if (appSpe.getApplicationTag() == 78) {
            ASN1InputStream aIS = new ASN1InputStream(appSpe.getContents());
            while (true) {
                ASN1Primitive obj = aIS.readObject();
                if (obj == null) {
                    return;
                }
                if (obj instanceof DERApplicationSpecific) {
                    DERApplicationSpecific aSpe = (DERApplicationSpecific) obj;
                    switch (aSpe.getApplicationTag()) {
                        case 2:
                            setCertificationAuthorityReference(aSpe);
                            break;
                        case 32:
                            setCertificateHolderReference(aSpe);
                            break;
                        case 36:
                            setCertificateExpirationDate(aSpe);
                            break;
                        case 37:
                            setCertificateEffectiveDate(aSpe);
                            break;
                        case 41:
                            setCertificateProfileIdentifier(aSpe);
                            break;
                        case 73:
                            setPublicKey(PublicKeyDataObject.getInstance(aSpe.getObject(16)));
                            break;
                        case 76:
                            setCertificateHolderAuthorization(new CertificateHolderAuthorization(aSpe));
                            break;
                        default:
                            this.certificateType = 0;
                            throw new IOException("Not a valid iso7816 DERApplicationSpecific tag " + aSpe.getApplicationTag());
                    }
                } else {
                    throw new IOException("Not a valid iso7816 content : not a DERApplicationSpecific Object :" + EACTags.encodeTag(appSpe) + obj.getClass());
                }
            }
        } else {
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        }
    }

    public CertificateBody(DERApplicationSpecific certificateProfileIdentifier2, CertificationAuthorityReference certificationAuthorityReference2, PublicKeyDataObject publicKey2, CertificateHolderReference certificateHolderReference2, CertificateHolderAuthorization certificateHolderAuthorization2, PackedDate certificateEffectiveDate2, PackedDate certificateExpirationDate2) {
        setCertificateProfileIdentifier(certificateProfileIdentifier2);
        setCertificationAuthorityReference(new DERApplicationSpecific(2, certificationAuthorityReference2.getEncoded()));
        setPublicKey(publicKey2);
        setCertificateHolderReference(new DERApplicationSpecific(32, certificateHolderReference2.getEncoded()));
        setCertificateHolderAuthorization(certificateHolderAuthorization2);
        try {
            setCertificateEffectiveDate(new DERApplicationSpecific(false, 37, (ASN1Encodable) new DEROctetString(certificateEffectiveDate2.getEncoding())));
            setCertificateExpirationDate(new DERApplicationSpecific(false, 36, (ASN1Encodable) new DEROctetString(certificateExpirationDate2.getEncoding())));
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to encode dates: " + e.getMessage());
        }
    }

    private CertificateBody(ASN1ApplicationSpecific obj) throws IOException {
        setIso7816CertificateBody(obj);
    }

    private ASN1Primitive profileToASN1Object() throws IOException {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.certificateProfileIdentifier);
        v.add(this.certificationAuthorityReference);
        v.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        v.add(this.certificateHolderReference);
        v.add(this.certificateHolderAuthorization);
        v.add(this.certificateEffectiveDate);
        v.add(this.certificateExpirationDate);
        return new DERApplicationSpecific(78, v);
    }

    private void setCertificateProfileIdentifier(DERApplicationSpecific certificateProfileIdentifier2) throws IllegalArgumentException {
        if (certificateProfileIdentifier2.getApplicationTag() == 41) {
            this.certificateProfileIdentifier = certificateProfileIdentifier2;
            this.certificateType |= 1;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :" + EACTags.encodeTag(certificateProfileIdentifier2));
    }

    private void setCertificateHolderReference(DERApplicationSpecific certificateHolderReference2) throws IllegalArgumentException {
        if (certificateHolderReference2.getApplicationTag() == 32) {
            this.certificateHolderReference = certificateHolderReference2;
            this.certificateType |= 8;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
    }

    private void setCertificationAuthorityReference(DERApplicationSpecific certificationAuthorityReference2) throws IllegalArgumentException {
        if (certificationAuthorityReference2.getApplicationTag() == 2) {
            this.certificationAuthorityReference = certificationAuthorityReference2;
            this.certificateType |= 2;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
    }

    private void setPublicKey(PublicKeyDataObject publicKey2) {
        this.publicKey = PublicKeyDataObject.getInstance(publicKey2);
        this.certificateType |= 4;
    }

    private ASN1Primitive requestToASN1Object() throws IOException {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.certificateProfileIdentifier);
        v.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        v.add(this.certificateHolderReference);
        return new DERApplicationSpecific(78, v);
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            if (this.certificateType == 127) {
                return profileToASN1Object();
            }
            if (this.certificateType == 13) {
                return requestToASN1Object();
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public static CertificateBody getInstance(Object obj) throws IOException {
        if (obj instanceof CertificateBody) {
            return (CertificateBody) obj;
        }
        if (obj != null) {
            return new CertificateBody(ASN1ApplicationSpecific.getInstance(obj));
        }
        return null;
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.certificateType & 32) == 32) {
            return new PackedDate(this.certificateEffectiveDate.getContents());
        }
        return null;
    }

    private void setCertificateEffectiveDate(DERApplicationSpecific ced) throws IllegalArgumentException {
        if (ced.getApplicationTag() == 37) {
            this.certificateEffectiveDate = ced;
            this.certificateType |= 32;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :" + EACTags.encodeTag(ced));
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.certificateType & 64) == 64) {
            return new PackedDate(this.certificateExpirationDate.getContents());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    private void setCertificateExpirationDate(DERApplicationSpecific ced) throws IllegalArgumentException {
        if (ced.getApplicationTag() == 36) {
            this.certificateExpirationDate = ced;
            this.certificateType |= 64;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.certificateType & 16) == 16) {
            return this.certificateHolderAuthorization;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization cha) {
        this.certificateHolderAuthorization = cha;
        this.certificateType |= 16;
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(this.certificateHolderReference.getContents());
    }

    public DERApplicationSpecific getCertificateProfileIdentifier() {
        return this.certificateProfileIdentifier;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.certificateType & 2) == 2) {
            return new CertificationAuthorityReference(this.certificationAuthorityReference.getContents());
        }
        throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey() {
        return this.publicKey;
    }
}
