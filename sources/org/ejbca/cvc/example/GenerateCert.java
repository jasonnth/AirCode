package org.ejbca.cvc.example;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CertificateGenerator;
import org.ejbca.cvc.CertificateParser;
import org.ejbca.cvc.HolderReferenceField;

public final class GenerateCert {
    private GenerateCert() {
    }

    public static void main(String[] strArr) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA", "BC");
            instance.initialize(1024, new SecureRandom());
            KeyPair generateKeyPair = instance.generateKeyPair();
            CAReferenceField cAReferenceField = new CAReferenceField("SE", "PASS-CVCA", "00111");
            byte[] dEREncoded = CertificateGenerator.createTestCertificate(generateKeyPair.getPublic(), generateKeyPair.getPrivate(), cAReferenceField, new HolderReferenceField(cAReferenceField.getCountry(), cAReferenceField.getMnemonic(), cAReferenceField.getSequence()), "SHA1WithRSA", AuthorizationRoleEnum.IS).getDEREncoded();
            String str = "C:/cv_certs/mycert1.cvcert";
            FileHelper.writeFile(new File(str), dEREncoded);
            System.out.println(CertificateParser.parseCertificate(FileHelper.loadFile(new File(str))).getAsText(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
