package org.jmrtd.cert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.util.Collection;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;
import org.ejbca.cvc.CertificateParser;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.exception.ParseException;

public class CVCertificateFactorySpi extends CertificateFactorySpi {
    private static final int CV_CERTIFICATE_TAG = 32545;

    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        try {
            TLVInputStream tLVInputStream = new TLVInputStream(inputStream);
            int readTag = tLVInputStream.readTag();
            if (readTag != 32545) {
                throw new CertificateException("Expected CV_CERTIFICATE_TAG, found " + Integer.toHexString(readTag));
            }
            tLVInputStream.readLength();
            byte[] readValue = tLVInputStream.readValue();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
            tLVOutputStream.writeTag(32545);
            tLVOutputStream.writeValue(readValue);
            tLVOutputStream.close();
            return new CardVerifiableCertificate(CertificateParser.parseCertificate(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            throw new CertificateException(e.getMessage());
        } catch (ConstructionException e2) {
            throw new CertificateException(e2.getMessage());
        } catch (ParseException e3) {
            throw new CertificateException(e3.getMessage());
        }
    }

    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        return null;
    }

    public Collection<? extends CRL> engineGenerateCRLs(InputStream inputStream) throws CRLException {
        return null;
    }

    public Collection<? extends Certificate> engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        return null;
    }
}
