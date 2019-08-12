package org.ejbca.cvc;

import net.p318sf.scuba.smartcards.ISO7816;
import org.ejbca.cvc.util.StringConverter;

public class AuthorizationField extends AbstractDataField {
    private static final long serialVersionUID = -5478250843535697147L;
    private AccessRights rights;
    private AuthorizationRole role;

    AuthorizationField() {
        super(CVCTagEnum.ROLE_AND_ACCESS_RIGHTS);
    }

    AuthorizationField(AuthorizationRole authorizationRole, AccessRights accessRights) {
        this();
        this.role = authorizationRole;
        this.rights = accessRights;
    }

    AuthorizationField(AuthorizationRoleEnum authorizationRoleEnum, AccessRightEnum accessRightEnum) {
        this((AuthorizationRole) authorizationRoleEnum, (AccessRights) accessRightEnum);
    }

    AuthorizationField(byte[] bArr) {
        this();
        if (bArr.length < 1) {
            throw new IllegalArgumentException("byte array length must be at least 1");
        }
        this.role = new AuthorizationRoleRawValue(bArr[0]);
        this.rights = new AccessRightsRawValue(bArr);
    }

    @Deprecated
    public AuthorizationRoleEnum getRole() {
        if (this.role instanceof AuthorizationRoleEnum) {
            return (AuthorizationRoleEnum) this.role;
        }
        throw new UnsupportedOperationException("Attempted to use deprecated getRole method with in an AT or ST certificate chain. It handles IS only.");
    }

    public AuthorizationRole getAuthRole() {
        return this.role;
    }

    @Deprecated
    public AccessRightEnum getAccessRight() {
        if (this.rights instanceof AccessRightEnum) {
            return (AccessRightEnum) this.rights;
        }
        throw new UnsupportedOperationException("Attempted to use deprecated getAccessRight method with an AT or ST certificate chain. It handles IS only.");
    }

    public AccessRights getAccessRights() {
        return this.rights;
    }

    /* access modifiers changed from: protected */
    public byte[] getEncoded() {
        byte[] encoded = this.rights.getEncoded();
        encoded[0] = (byte) (encoded[0] | this.role.getValue());
        return encoded;
    }

    /* access modifiers changed from: protected */
    public String valueAsText() {
        return StringConverter.byteToHex(getEncoded()) + ": " + this.role + "/" + this.rights;
    }

    private static AuthorizationRole getRoleFromByte(OIDField oIDField, byte b) {
        AuthorizationRole[] values;
        byte b2 = (byte) (b & ISO7816.INS_GET_RESPONSE);
        if (CVCObjectIdentifiers.id_EAC_ePassport.equals(oIDField)) {
            values = AuthorizationRoleEnum.values();
        } else if (CVCObjectIdentifiers.id_EAC_roles_ST.equals(oIDField)) {
            values = AuthorizationRoleSignTermEnum.values();
        } else if (CVCObjectIdentifiers.id_EAC_roles_AT.equals(oIDField)) {
            values = AuthorizationRoleAuthTermEnum.values();
        } else {
            throw new IllegalArgumentException("incorrect or unsupported OID");
        }
        for (AuthorizationRole authorizationRole : values) {
            if (b2 == authorizationRole.getValue()) {
                return authorizationRole;
            }
        }
        return null;
    }

    private static AccessRights getRightsFromBytes(OIDField oIDField, byte[] bArr) {
        AccessRightEnum[] values;
        AccessRightSignTermEnum accessRightSignTermEnum = null;
        if (CVCObjectIdentifiers.id_EAC_ePassport.equals(oIDField)) {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("byte array length must be 1, was " + bArr.length);
            }
            byte b = (byte) (bArr[0] & 3);
            for (AccessRightEnum accessRightEnum : AccessRightEnum.values()) {
                if (b == accessRightEnum.getValue()) {
                    return accessRightEnum;
                }
            }
            return null;
        } else if (CVCObjectIdentifiers.id_EAC_roles_ST.equals(oIDField)) {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("byte array length must be 1, was " + bArr.length);
            }
            byte b2 = (byte) (bArr[0] & 3);
            AccessRightSignTermEnum[] values2 = AccessRightSignTermEnum.values();
            int length = values2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                AccessRightSignTermEnum accessRightSignTermEnum2 = values2[i];
                if (b2 == accessRightSignTermEnum2.getValue()) {
                    accessRightSignTermEnum = accessRightSignTermEnum2;
                    break;
                }
                i++;
            }
            return accessRightSignTermEnum;
        } else if (!CVCObjectIdentifiers.id_EAC_roles_AT.equals(oIDField)) {
            throw new IllegalArgumentException("incorrect or unsupported OID");
        } else if (bArr.length == 5) {
            return new AccessRightAuthTerm(bArr);
        } else {
            throw new IllegalArgumentException("byte array length must be 5, was " + bArr.length);
        }
    }

    /* access modifiers changed from: 0000 */
    public void fixEnumTypes(OIDField oIDField) {
        this.role = getRoleFromByte(oIDField, this.role.getValue());
        this.rights = getRightsFromBytes(oIDField, this.rights.getEncoded());
    }
}
