package org.jmrtd.cert;

import org.ejbca.cvc.AccessRightEnum;
import org.ejbca.cvc.AuthorizationRoleEnum;

public class CVCAuthorizationTemplate {
    private Permission accessRight;
    private Role role;

    public enum Permission {
        READ_ACCESS_NONE(0),
        READ_ACCESS_DG3(1),
        READ_ACCESS_DG4(2),
        READ_ACCESS_DG3_AND_DG4(3);
        
        /* access modifiers changed from: private */
        public byte value;

        private Permission(int i) {
            this.value = (byte) i;
        }

        public boolean implies(Permission permission) {
            boolean z = false;
            switch (this) {
                case READ_ACCESS_NONE:
                    if (permission != READ_ACCESS_NONE) {
                        return false;
                    }
                    return true;
                case READ_ACCESS_DG3:
                    if (permission != READ_ACCESS_DG3) {
                        return false;
                    }
                    return true;
                case READ_ACCESS_DG4:
                    if (permission != READ_ACCESS_DG4) {
                        return false;
                    }
                    return true;
                case READ_ACCESS_DG3_AND_DG4:
                    if (permission == READ_ACCESS_DG3 || permission == READ_ACCESS_DG4 || permission == READ_ACCESS_DG3_AND_DG4) {
                        z = true;
                    }
                    return z;
                default:
                    return false;
            }
        }

        public byte getValue() {
            return this.value;
        }
    }

    public enum Role {
        CVCA(192),
        DV_D(128),
        DV_F(64),
        IS(0);
        
        /* access modifiers changed from: private */
        public byte value;

        private Role(int i) {
            this.value = (byte) i;
        }

        public byte getValue() {
            return this.value;
        }
    }

    protected CVCAuthorizationTemplate(org.ejbca.cvc.CVCAuthorizationTemplate cVCAuthorizationTemplate) {
        try {
            switch (cVCAuthorizationTemplate.getAuthorizationField().getRole()) {
                case CVCA:
                    this.role = Role.CVCA;
                    break;
                case DV_D:
                    this.role = Role.DV_D;
                    break;
                case DV_F:
                    this.role = Role.DV_F;
                    break;
                case IS:
                    this.role = Role.IS;
                    break;
            }
            switch (cVCAuthorizationTemplate.getAuthorizationField().getAccessRight()) {
                case READ_ACCESS_NONE:
                    this.accessRight = Permission.READ_ACCESS_NONE;
                    return;
                case READ_ACCESS_DG3:
                    this.accessRight = Permission.READ_ACCESS_DG3;
                    return;
                case READ_ACCESS_DG4:
                    this.accessRight = Permission.READ_ACCESS_DG4;
                    return;
                case READ_ACCESS_DG3_AND_DG4:
                    this.accessRight = Permission.READ_ACCESS_DG3_AND_DG4;
                    return;
                default:
                    return;
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Error getting role from AuthZ template");
        }
    }

    public CVCAuthorizationTemplate(Role role2, Permission permission) {
        this.role = role2;
        this.accessRight = permission;
    }

    public Role getRole() {
        return this.role;
    }

    public Permission getAccessRight() {
        return this.accessRight;
    }

    public String toString() {
        return this.role.toString() + this.accessRight.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        CVCAuthorizationTemplate cVCAuthorizationTemplate = (CVCAuthorizationTemplate) obj;
        if (!(this.role == cVCAuthorizationTemplate.role && this.accessRight == cVCAuthorizationTemplate.accessRight)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (this.role.value * 2) + (this.accessRight.value * 3) + 61;
    }

    static AccessRightEnum fromPermission(Permission permission) {
        try {
            switch (permission) {
                case READ_ACCESS_NONE:
                    return AccessRightEnum.READ_ACCESS_NONE;
                case READ_ACCESS_DG3:
                    return AccessRightEnum.READ_ACCESS_DG3;
                case READ_ACCESS_DG4:
                    return AccessRightEnum.READ_ACCESS_DG4;
                case READ_ACCESS_DG3_AND_DG4:
                    return AccessRightEnum.READ_ACCESS_DG3_AND_DG4;
            }
        } catch (Exception e) {
        }
        throw new IllegalArgumentException("Error getting permission from AuthZ template");
    }

    static AuthorizationRoleEnum fromRole(Role role2) {
        try {
            switch (role2) {
                case CVCA:
                    return AuthorizationRoleEnum.CVCA;
                case DV_D:
                    return AuthorizationRoleEnum.DV_D;
                case DV_F:
                    return AuthorizationRoleEnum.DV_F;
                case IS:
                    return AuthorizationRoleEnum.IS;
            }
        } catch (Exception e) {
        }
        throw new IllegalArgumentException("Error getting role from AuthZ template");
    }
}
