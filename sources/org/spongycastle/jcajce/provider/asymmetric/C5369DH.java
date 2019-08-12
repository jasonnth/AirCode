package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.p329dh.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.DH */
public class C5369DH {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.asymmetric.dh.";

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.DH$Mappings */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider provider) {
            provider.addAlgorithm("KeyPairGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyPairGeneratorSpi");
            provider.addAlgorithm("Alg.Alias.KeyPairGenerator.DIFFIEHELLMAN", "DH");
            provider.addAlgorithm("KeyAgreement.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi");
            provider.addAlgorithm("Alg.Alias.KeyAgreement.DIFFIEHELLMAN", "DH");
            provider.addAlgorithm("KeyAgreement", PKCSObjectIdentifiers.id_alg_ESDH, "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi$DHwithRFC2631KDF");
            provider.addAlgorithm("KeyAgreement", PKCSObjectIdentifiers.id_alg_SSDH, "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi$DHwithRFC2631KDF");
            provider.addAlgorithm("KeyFactory.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyFactorySpi");
            provider.addAlgorithm("Alg.Alias.KeyFactory.DIFFIEHELLMAN", "DH");
            provider.addAlgorithm("AlgorithmParameters.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParametersSpi");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.DIFFIEHELLMAN", "DH");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.DIFFIEHELLMAN", "DH");
            provider.addAlgorithm("AlgorithmParameterGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParameterGeneratorSpi");
            provider.addAlgorithm("Cipher.IES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IES");
            provider.addAlgorithm("Cipher.IESwithAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            provider.addAlgorithm("Cipher.IESWITHAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            provider.addAlgorithm("Cipher.IESWITHDESEDE", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithDESede");
            provider.addAlgorithm("Cipher.DHIES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IES");
            provider.addAlgorithm("Cipher.DHIESwithAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            provider.addAlgorithm("Cipher.DHIESWITHAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            provider.addAlgorithm("Cipher.DHIESWITHDESEDE", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithDESede");
            provider.addAlgorithm("Cipher.OLDDHIES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$OldIES");
            provider.addAlgorithm("Cipher.OLDDHIESwithAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$OldIESwithAES");
            provider.addAlgorithm("Cipher.OLDDHIESWITHAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$OldIESwithAES");
            provider.addAlgorithm("Cipher.OLDDHIESWITHDESEDE", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$OldIESwithDESede");
            registerOid(provider, PKCSObjectIdentifiers.dhKeyAgreement, "DH", new KeyFactorySpi());
            registerOid(provider, X9ObjectIdentifiers.dhpublicnumber, "DH", new KeyFactorySpi());
        }
    }
}
