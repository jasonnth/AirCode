package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.RationalNumber;
import org.apache.sanselan.common.RationalNumberUtilities;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.util.Debug;

public class FieldTypeRational extends FieldType {
    public FieldTypeRational(int type, String name) {
        super(type, 8, name);
    }

    public Object getSimpleValue(TiffField entry) {
        if (entry.length == 1) {
            return convertByteArrayToRational(this.name + " (" + entry.tagInfo.name + ")", entry.oversizeValue, entry.byteOrder);
        }
        return convertByteArrayToRationalArray(this.name + " (" + entry.tagInfo.name + ")", getRawBytes(entry), 0, entry.length, entry.byteOrder);
    }

    public byte[] writeData(Object o, int byteOrder) throws ImageWriteException {
        if (o instanceof RationalNumber) {
            return convertRationalToByteArray((RationalNumber) o, byteOrder);
        }
        if (o instanceof RationalNumber[]) {
            return convertRationalArrayToByteArray((RationalNumber[]) o, byteOrder);
        }
        if (o instanceof Number) {
            return convertRationalToByteArray(RationalNumberUtilities.getRationalNumber(((Number) o).doubleValue()), byteOrder);
        }
        if (o instanceof Number[]) {
            Number[] numbers = (Number[]) o;
            RationalNumber[] rationalNumbers = new RationalNumber[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                rationalNumbers[i] = RationalNumberUtilities.getRationalNumber(numbers[i].doubleValue());
            }
            return convertRationalArrayToByteArray(rationalNumbers, byteOrder);
        } else if (o instanceof double[]) {
            double[] numbers2 = (double[]) o;
            RationalNumber[] rationalNumbers2 = new RationalNumber[numbers2.length];
            for (int i2 = 0; i2 < numbers2.length; i2++) {
                rationalNumbers2[i2] = RationalNumberUtilities.getRationalNumber(numbers2[i2]);
            }
            return convertRationalArrayToByteArray(rationalNumbers2, byteOrder);
        } else {
            throw new ImageWriteException("Invalid data: " + o + " (" + Debug.getType(o) + ")");
        }
    }
}
