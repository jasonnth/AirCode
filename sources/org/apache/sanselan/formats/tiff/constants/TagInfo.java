package org.apache.sanselan.formats.tiff.constants;

import java.io.UnsupportedEncodingException;
import net.p318sf.scuba.smartcards.ISO7816;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryFileFunctions;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffDirectoryConstants.ExifDirectoryType;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.util.Debug;
import org.spongycastle.i18n.LocalizedMessage;
import p005cn.jpush.android.JPushConstants;

public class TagInfo implements TiffDirectoryConstants, TiffFieldTypeConstants {
    public final FieldType[] dataTypes;
    public final ExifDirectoryType directoryType;
    public final int length;
    public final String name;
    public final int tag;

    public static class Offset extends TagInfo {
        public Offset(String name, int tag, FieldType[] dataTypes, int length, ExifDirectoryType exifDirectory) {
            super(name, tag, dataTypes, length, exifDirectory);
        }

        public Offset(String name, int tag, FieldType dataType, int length, ExifDirectoryType exifDirectory) {
            super(name, tag, dataType, length, exifDirectory);
        }
    }

    public static final class Text extends TagInfo {
        private static final TextEncoding[] TEXT_ENCODINGS = {TEXT_ENCODING_ASCII, TEXT_ENCODING_JIS, TEXT_ENCODING_UNICODE, TEXT_ENCODING_UNDEFINED};
        private static final TextEncoding TEXT_ENCODING_ASCII = new TextEncoding(new byte[]{65, 83, 67, 73, 73, 0, 0, 0}, "US-ASCII");
        private static final TextEncoding TEXT_ENCODING_JIS = new TextEncoding(new byte[]{74, 73, 83, 0, 0, 0, 0, 0}, "JIS");
        private static final TextEncoding TEXT_ENCODING_UNDEFINED = new TextEncoding(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, LocalizedMessage.DEFAULT_ENCODING);
        private static final TextEncoding TEXT_ENCODING_UNICODE = new TextEncoding(new byte[]{85, 78, 73, 67, 79, ISO7816.INS_REHABILITATE_CHV, 69, 0}, JPushConstants.ENCODING_UTF_8);

        private static final class TextEncoding {
            public final String encodingName;
            public final byte[] prefix;

            public TextEncoding(byte[] prefix2, String encodingName2) {
                this.prefix = prefix2;
                this.encodingName = encodingName2;
            }
        }

        public Text(String name, int tag, FieldType dataType, int length, ExifDirectoryType exifDirectory) {
            super(name, tag, dataType, length, exifDirectory);
        }

        public Text(String name, int tag, FieldType[] dataTypes, int length, ExifDirectoryType exifDirectory) {
            super(name, tag, dataTypes, length, exifDirectory);
        }

        public byte[] encodeValue(FieldType fieldType, Object value, int byteOrder) throws ImageWriteException {
            if (!(value instanceof String)) {
                throw new ImageWriteException("Text value not String: " + value + " (" + Debug.getType(value) + ")");
            }
            String s = (String) value;
            try {
                byte[] asciiBytes = s.getBytes(TEXT_ENCODING_ASCII.encodingName);
                if (new String(asciiBytes, TEXT_ENCODING_ASCII.encodingName).equals(s)) {
                    byte[] result = new byte[(asciiBytes.length + TEXT_ENCODING_ASCII.prefix.length)];
                    System.arraycopy(TEXT_ENCODING_ASCII.prefix, 0, result, 0, TEXT_ENCODING_ASCII.prefix.length);
                    System.arraycopy(asciiBytes, 0, result, TEXT_ENCODING_ASCII.prefix.length, asciiBytes.length);
                    return result;
                }
                byte[] unicodeBytes = s.getBytes(TEXT_ENCODING_UNICODE.encodingName);
                byte[] result2 = new byte[(unicodeBytes.length + TEXT_ENCODING_UNICODE.prefix.length)];
                System.arraycopy(TEXT_ENCODING_UNICODE.prefix, 0, result2, 0, TEXT_ENCODING_UNICODE.prefix.length);
                System.arraycopy(unicodeBytes, 0, result2, TEXT_ENCODING_UNICODE.prefix.length, unicodeBytes.length);
                return result2;
            } catch (UnsupportedEncodingException e) {
                throw new ImageWriteException(e.getMessage(), e);
            }
        }

        public Object getValue(TiffField entry) throws ImageReadException {
            if (entry.type == FIELD_TYPE_ASCII.type) {
                return FIELD_TYPE_ASCII.getSimpleValue(entry);
            }
            if (entry.type == FIELD_TYPE_UNDEFINED.type || entry.type == FIELD_TYPE_BYTE.type) {
                byte[] bytes = entry.fieldType.getRawBytes(entry);
                if (bytes.length < 8) {
                    try {
                        return new String(bytes, "US-ASCII");
                    } catch (UnsupportedEncodingException e) {
                        throw new ImageReadException("Text field missing encoding prefix.");
                    }
                } else {
                    int i = 0;
                    while (i < TEXT_ENCODINGS.length) {
                        TextEncoding encoding = TEXT_ENCODINGS[i];
                        if (BinaryFileFunctions.compareBytes(bytes, 0, encoding.prefix, 0, encoding.prefix.length)) {
                            try {
                                return new String(bytes, encoding.prefix.length, bytes.length - encoding.prefix.length, encoding.encodingName);
                            } catch (UnsupportedEncodingException e2) {
                                throw new ImageReadException(e2.getMessage(), e2);
                            }
                        } else {
                            i++;
                        }
                    }
                    try {
                        return new String(bytes, "US-ASCII");
                    } catch (UnsupportedEncodingException e3) {
                        throw new ImageReadException("Unknown text encoding prefix.");
                    }
                }
            } else {
                Debug.debug("entry.type", entry.type);
                Debug.debug("entry.directoryType", entry.directoryType);
                Debug.debug("entry.type", entry.getDescriptionWithoutValue());
                Debug.debug("entry.type", (Object) entry.fieldType);
                throw new ImageReadException("Text field not encoded as bytes.");
            }
        }
    }

    public static final class Unknown extends TagInfo {
        public Unknown(String name, int tag, FieldType[] dataTypes, int length, ExifDirectoryType exifDirectory) {
            super(name, tag, dataTypes, length, exifDirectory);
        }

        public byte[] encodeValue(FieldType fieldType, Object value, int byteOrder) throws ImageWriteException {
            return TagInfo.super.encodeValue(fieldType, value, byteOrder);
        }

        public Object getValue(TiffField entry) throws ImageReadException {
            return TagInfo.super.getValue(entry);
        }
    }

    public TagInfo(String name2, int tag2, FieldType dataType, int length2, ExifDirectoryType exifDirectory) {
        this(name2, tag2, new FieldType[]{dataType}, length2, exifDirectory);
    }

    public TagInfo(String name2, int tag2, FieldType[] dataTypes2, int length2, ExifDirectoryType exifDirectory) {
        this.name = name2;
        this.tag = tag2;
        this.dataTypes = dataTypes2;
        this.length = length2;
        this.directoryType = exifDirectory;
    }

    public Object getValue(TiffField entry) throws ImageReadException {
        return entry.fieldType.getSimpleValue(entry);
    }

    public byte[] encodeValue(FieldType fieldType, Object value, int byteOrder) throws ImageWriteException {
        return fieldType.writeData(value, byteOrder);
    }

    public String getDescription() {
        return this.tag + " (0x" + Integer.toHexString(this.tag) + ": " + this.name + "): ";
    }

    public String toString() {
        return "[TagInfo. tag: " + this.tag + " (0x" + Integer.toHexString(this.tag) + ", name: " + this.name + "]";
    }
}
