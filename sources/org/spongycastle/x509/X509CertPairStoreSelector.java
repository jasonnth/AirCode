package org.spongycastle.x509;

import org.spongycastle.util.Selector;

public class X509CertPairStoreSelector implements Selector {
    private X509CertificatePair certPair;
    private X509CertStoreSelector forwardSelector;
    private X509CertStoreSelector reverseSelector;

    public X509CertificatePair getCertPair() {
        return this.certPair;
    }

    public void setCertPair(X509CertificatePair certPair2) {
        this.certPair = certPair2;
    }

    public void setForwardSelector(X509CertStoreSelector forwardSelector2) {
        this.forwardSelector = forwardSelector2;
    }

    public void setReverseSelector(X509CertStoreSelector reverseSelector2) {
        this.reverseSelector = reverseSelector2;
    }

    public Object clone() {
        X509CertPairStoreSelector cln = new X509CertPairStoreSelector();
        cln.certPair = this.certPair;
        if (this.forwardSelector != null) {
            cln.setForwardSelector((X509CertStoreSelector) this.forwardSelector.clone());
        }
        if (this.reverseSelector != null) {
            cln.setReverseSelector((X509CertStoreSelector) this.reverseSelector.clone());
        }
        return cln;
    }

    public boolean match(Object obj) {
        try {
            if (!(obj instanceof X509CertificatePair)) {
                return false;
            }
            X509CertificatePair pair = (X509CertificatePair) obj;
            if (this.forwardSelector != null && !this.forwardSelector.match((Object) pair.getForward())) {
                return false;
            }
            if (this.reverseSelector != null && !this.reverseSelector.match((Object) pair.getReverse())) {
                return false;
            }
            if (this.certPair != null) {
                return this.certPair.equals(obj);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public X509CertStoreSelector getForwardSelector() {
        return this.forwardSelector;
    }

    public X509CertStoreSelector getReverseSelector() {
        return this.reverseSelector;
    }
}
