package org.jmrtd.cert;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Date;
import org.ejbca.cvc.AccessRightEnum;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CertificateGenerator;
import org.ejbca.cvc.HolderReferenceField;
import org.ejbca.cvc.exception.ConstructionException;
import org.jmrtd.cert.CVCAuthorizationTemplate.Permission;
import org.jmrtd.cert.CVCAuthorizationTemplate.Role;

public class CVCertificateBuilder {
    public static CardVerifiableCertificate createCertificate(PublicKey publicKey, PrivateKey privateKey, String str, CVCPrincipal cVCPrincipal, CVCPrincipal cVCPrincipal2, CVCAuthorizationTemplate cVCAuthorizationTemplate, Date date, Date date2, String str2) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, ConstructionException {
        return new CardVerifiableCertificate(CertificateGenerator.createCertificate(publicKey, privateKey, str, new CAReferenceField(cVCPrincipal.getCountry().toAlpha2Code(), cVCPrincipal.getMnemonic(), cVCPrincipal.getSeqNumber()), new HolderReferenceField(cVCPrincipal2.getCountry().toAlpha2Code(), cVCPrincipal2.getMnemonic(), cVCPrincipal2.getSeqNumber()), getRole(cVCAuthorizationTemplate.getRole()), getAccessRight(cVCAuthorizationTemplate.getAccessRight()), date, date2, str2));
    }

    private static AuthorizationRoleEnum getRole(Role role) {
        switch (role) {
            case CVCA:
                return AuthorizationRoleEnum.CVCA;
            case DV_D:
                return AuthorizationRoleEnum.DV_D;
            case DV_F:
                return AuthorizationRoleEnum.DV_F;
            case IS:
                return AuthorizationRoleEnum.IS;
            default:
                throw new NumberFormatException("Cannot decode role " + role);
        }
    }

    private static AccessRightEnum getAccessRight(Permission permission) {
        switch (permission) {
            case READ_ACCESS_NONE:
                return AccessRightEnum.READ_ACCESS_NONE;
            case READ_ACCESS_DG3:
                return AccessRightEnum.READ_ACCESS_DG3;
            case READ_ACCESS_DG4:
                return AccessRightEnum.READ_ACCESS_DG4;
            case READ_ACCESS_DG3_AND_DG4:
                return AccessRightEnum.READ_ACCESS_DG3_AND_DG4;
            default:
                throw new NumberFormatException("Cannot decode access right " + permission);
        }
    }
}
