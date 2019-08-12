package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryFileFunctions;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public abstract class FieldType extends BinaryFileFunctions implements TiffConstants {
    public final int length;
    public final String name;
    public final int type;

    public abstract Object getSimpleValue(TiffField tiffField) throws ImageReadException;

    public abstract byte[] writeData(Object obj, int i) throws ImageWriteException;

    public FieldType(int type2, int length2, String name2) {
        this.type = type2;
        this.length = length2;
        this.name = name2;
    }

    public boolean isLocalValue(TiffField entry) {
        return this.length > 0 && this.length * entry.length <= 4;
    }

    public static final byte[] getStubLocalValue() {
        return new byte[4];
    }

    public final byte[] getRawBytes(TiffField entry) {
        if (!isLocalValue(entry)) {
            return entry.oversizeValue;
        }
        int rawLength = this.length * entry.length;
        byte[] result = new byte[rawLength];
        System.arraycopy(entry.valueOffsetBytes, 0, result, 0, rawLength);
        return result;
    }

    public String toString() {
        return "[" + getClass().getName() + ". type: " + this.type + ", name: " + this.name + ", length: " + this.length + "]";
    }
}
