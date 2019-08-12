package org.ejbca.cvc.example;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CVCAuthenticatedRequest;
import org.ejbca.cvc.CVCertificate;
import org.ejbca.cvc.CertificateGenerator;
import org.ejbca.cvc.HolderReferenceField;

public final class GenerateRequest {
    private GenerateRequest() {
    }

    public static void main(String[] strArr) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA", "BC");
            instance.initialize(1024, new SecureRandom());
            KeyPair generateKeyPair = instance.generateKeyPair();
            CAReferenceField cAReferenceField = new CAReferenceField("SE", "PASSRD1", "00008");
            String str = "SHA256WITHRSAANDMGF1";
            CVCertificate createRequest = CertificateGenerator.createRequest(generateKeyPair, "SHA256WITHRSAANDMGF1", new HolderReferenceField("SE", "PASSRD1", "00009"));
            System.out.println(createRequest.getAsText());
            CVCAuthenticatedRequest createAuthenticatedRequest = CertificateGenerator.createAuthenticatedRequest(createRequest, generateKeyPair, "SHA256WITHRSAANDMGF1", cAReferenceField);
            System.out.println(createAuthenticatedRequest.getAsText());
            FileHelper.writeFile(new File("C:/cv_certs/request1.cvcert"), createAuthenticatedRequest.getDEREncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
