package org.ejbca.cvc;

public enum AccessRightEnum implements AccessRights {
    READ_ACCESS_NONE(0),
    READ_ACCESS_DG3(1),
    READ_ACCESS_DG4(2),
    READ_ACCESS_DG3_AND_DG4(3);
    
    private byte value;

    private AccessRightEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean hasDG3() {
        return (this.value & READ_ACCESS_DG3.value) != 0;
    }

    public boolean hasDG4() {
        return (this.value & READ_ACCESS_DG4.value) != 0;
    }

    public byte[] getEncoded() {
        return new byte[]{this.value};
    }

    public String toString() {
        switch (this) {
            case READ_ACCESS_DG3_AND_DG4:
                return "DG3+DG4";
            case READ_ACCESS_DG4:
                return "DG4";
            case READ_ACCESS_DG3:
                return "DG3";
            case READ_ACCESS_NONE:
                return "none";
            default:
                throw new IllegalStateException("Enum case not handled");
        }
    }
}
