package org.spongycastle.asn1.smime;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cms.Attribute;
import org.spongycastle.asn1.cms.IssuerAndSerialNumber;
import org.spongycastle.asn1.cms.RecipientKeyIdentifier;

public class SMIMEEncryptionKeyPreferenceAttribute extends Attribute {
    public SMIMEEncryptionKeyPreferenceAttribute(IssuerAndSerialNumber issAndSer) {
        super(SMIMEAttributes.encrypKeyPref, new DERSet((ASN1Encodable) new DERTaggedObject(false, 0, issAndSer)));
    }

    public SMIMEEncryptionKeyPreferenceAttribute(RecipientKeyIdentifier rKeyId) {
        super(SMIMEAttributes.encrypKeyPref, new DERSet((ASN1Encodable) new DERTaggedObject(false, 1, rKeyId)));
    }

    public SMIMEEncryptionKeyPreferenceAttribute(ASN1OctetString sKeyId) {
        super(SMIMEAttributes.encrypKeyPref, new DERSet((ASN1Encodable) new DERTaggedObject(false, 2, sKeyId)));
    }
}
