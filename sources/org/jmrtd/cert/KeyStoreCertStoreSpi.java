package org.jmrtd.cert;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

public class KeyStoreCertStoreSpi extends CertStoreSpi {
    private KeyStore keyStore;

    public KeyStoreCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        this.keyStore = ((KeyStoreCertStoreParameters) certStoreParameters).getKeyStore();
    }

    public Collection<? extends Certificate> engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        try {
            ArrayList arrayList = new ArrayList(this.keyStore.size());
            Enumeration aliases = this.keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String str = (String) aliases.nextElement();
                if (this.keyStore.isCertificateEntry(str)) {
                    Certificate certificate = this.keyStore.getCertificate(str);
                    if (certSelector.match(certificate)) {
                        arrayList.add(certificate);
                    }
                }
            }
            return arrayList;
        } catch (KeyStoreException e) {
            throw new CertStoreException(e.getMessage());
        }
    }

    public Collection<? extends CRL> engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        return new ArrayList(0);
    }
}
