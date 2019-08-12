package org.spongycastle.jce;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DEROutputStream;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.MacData;
import org.spongycastle.asn1.pkcs.Pfx;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;

public class PKCS12Util {
    public static byte[] convertToDefiniteLength(byte[] berPKCS12File) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream dOut = new DEROutputStream(bOut);
        Pfx pfx = Pfx.getInstance(berPKCS12File);
        bOut.reset();
        dOut.writeObject(pfx);
        return bOut.toByteArray();
    }

    public static byte[] convertToDefiniteLength(byte[] berPKCS12File, char[] passwd, String provider) throws IOException {
        Pfx pfx = Pfx.getInstance(berPKCS12File);
        ContentInfo info = pfx.getAuthSafe();
        ASN1OctetString content = ASN1OctetString.getInstance(info.getContent());
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream dOut = new DEROutputStream(bOut);
        dOut.writeObject(new ASN1InputStream(content.getOctets()).readObject());
        ContentInfo contentInfo = new ContentInfo(info.getContentType(), new DEROctetString(bOut.toByteArray()));
        MacData mData = pfx.getMacData();
        try {
            int itCount = mData.getIterationCount().intValue();
            MacData macData = new MacData(new DigestInfo(new AlgorithmIdentifier(mData.getMac().getAlgorithmId().getAlgorithm(), DERNull.INSTANCE), calculatePbeMac(mData.getMac().getAlgorithmId().getAlgorithm(), mData.getSalt(), itCount, passwd, ASN1OctetString.getInstance(contentInfo.getContent()).getOctets(), provider)), mData.getSalt(), itCount);
            Pfx pfx2 = new Pfx(contentInfo, macData);
            bOut.reset();
            dOut.writeObject(pfx2);
            return bOut.toByteArray();
        } catch (Exception e) {
            throw new IOException("error constructing MAC: " + e.toString());
        }
    }

    private static byte[] calculatePbeMac(ASN1ObjectIdentifier oid, byte[] salt, int itCount, char[] password, byte[] data, String provider) throws Exception {
        SecretKeyFactory keyFact = SecretKeyFactory.getInstance(oid.getId(), provider);
        PBEParameterSpec defParams = new PBEParameterSpec(salt, itCount);
        SecretKey key = keyFact.generateSecret(new PBEKeySpec(password));
        Mac mac = Mac.getInstance(oid.getId(), provider);
        mac.init(key, defParams);
        mac.update(data);
        return mac.doFinal();
    }
}
