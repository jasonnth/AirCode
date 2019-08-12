package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.util.Arrays;

public class GOST28147ParameterSpec implements AlgorithmParameterSpec {
    private static Map oidMappings = new HashMap();

    /* renamed from: iv */
    private byte[] f6923iv;
    private byte[] sBox;

    public GOST28147ParameterSpec(byte[] sBox2) {
        this.f6923iv = null;
        this.sBox = null;
        this.sBox = new byte[sBox2.length];
        System.arraycopy(sBox2, 0, this.sBox, 0, sBox2.length);
    }

    public GOST28147ParameterSpec(byte[] sBox2, byte[] iv) {
        this(sBox2);
        this.f6923iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.f6923iv, 0, iv.length);
    }

    public GOST28147ParameterSpec(String sBoxName) {
        this.f6923iv = null;
        this.sBox = null;
        this.sBox = GOST28147Engine.getSBox(sBoxName);
    }

    public GOST28147ParameterSpec(String sBoxName, byte[] iv) {
        this(sBoxName);
        this.f6923iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.f6923iv, 0, iv.length);
    }

    public GOST28147ParameterSpec(ASN1ObjectIdentifier sBoxName, byte[] iv) {
        this(getName(sBoxName));
        this.f6923iv = Arrays.clone(iv);
    }

    public byte[] getSbox() {
        return Arrays.clone(this.sBox);
    }

    public byte[] getIV() {
        return Arrays.clone(this.f6923iv);
    }

    static {
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
    }

    private static String getName(ASN1ObjectIdentifier sBoxOid) {
        String sBoxName = (String) oidMappings.get(sBoxOid);
        if (sBoxName != null) {
            return sBoxName;
        }
        throw new IllegalArgumentException("unknown OID: " + sBoxOid);
    }
}
