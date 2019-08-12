package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.write.TiffOutputItem.Value;

public class TiffOutputField implements TiffConstants {
    private static final String newline = System.getProperty("line.separator");
    private byte[] bytes;
    public final int count;
    public final FieldType fieldType;
    private final Value separateValueItem;
    private int sortHint;
    public final int tag;
    public final TagInfo tagInfo;

    public TiffOutputField(TagInfo tagInfo2, FieldType tagtype, int count2, byte[] bytes2) {
        this(tagInfo2.tag, tagInfo2, tagtype, count2, bytes2);
    }

    public TiffOutputField(int tag2, TagInfo tagInfo2, FieldType fieldType2, int count2, byte[] bytes2) {
        this.sortHint = -1;
        this.tag = tag2;
        this.tagInfo = tagInfo2;
        this.fieldType = fieldType2;
        this.count = count2;
        this.bytes = bytes2;
        if (isLocalValue()) {
            this.separateValueItem = null;
        } else {
            this.separateValueItem = new Value("Field Seperate value (" + tagInfo2.getDescription() + ")", bytes2);
        }
    }

    protected static final TiffOutputField createOffsetField(TagInfo tagInfo2, int byteOrder) throws ImageWriteException {
        return new TiffOutputField(tagInfo2, FIELD_TYPE_LONG, 1, FIELD_TYPE_LONG.writeData(new int[]{0}, byteOrder));
    }

    /* access modifiers changed from: protected */
    public void writeField(BinaryOutputStream bos) throws IOException, ImageWriteException {
        bos.write2Bytes(this.tag);
        bos.write2Bytes(this.fieldType.type);
        bos.write4Bytes(this.count);
        if (isLocalValue()) {
            if (this.separateValueItem != null) {
                throw new ImageWriteException("Unexpected separate value item.");
            } else if (this.bytes.length > 4) {
                throw new ImageWriteException("Local value has invalid length: " + this.bytes.length);
            } else {
                bos.writeByteArray(this.bytes);
                int remainder = 4 - this.bytes.length;
                for (int i = 0; i < remainder; i++) {
                    bos.write(0);
                }
            }
        } else if (this.separateValueItem == null) {
            throw new ImageWriteException("Missing separate value item.");
        } else {
            bos.write4Bytes(this.separateValueItem.getOffset());
        }
    }

    /* access modifiers changed from: protected */
    public TiffOutputItem getSeperateValue() {
        return this.separateValueItem;
    }

    /* access modifiers changed from: protected */
    public boolean isLocalValue() {
        return this.bytes.length <= 4;
    }

    /* access modifiers changed from: protected */
    public void setData(byte[] bytes2) throws ImageWriteException {
        if (this.bytes.length != bytes2.length) {
            throw new ImageWriteException("Cannot change size of value.");
        }
        this.bytes = bytes2;
        if (this.separateValueItem != null) {
            this.separateValueItem.updateValue(bytes2);
        }
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        StringBuffer result = new StringBuffer();
        result.append(prefix);
        result.append(this.tagInfo);
        result.append(newline);
        result.append(prefix);
        result.append("count: " + this.count);
        result.append(newline);
        result.append(prefix);
        result.append(this.fieldType);
        result.append(newline);
        return result.toString();
    }

    public int getSortHint() {
        return this.sortHint;
    }
}
