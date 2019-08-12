package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.constants.AllTagConstants;

abstract class TiffOutputItem implements AllTagConstants {
    private int offset = -1;

    public static class Value extends TiffOutputItem {
        private final byte[] bytes;
        private final String name;

        public Value(String name2, byte[] bytes2) {
            this.name = name2;
            this.bytes = bytes2;
        }

        public int getItemLength() {
            return this.bytes.length;
        }

        public void updateValue(byte[] bytes2) throws ImageWriteException {
            if (this.bytes.length != bytes2.length) {
                throw new ImageWriteException("Updated data size mismatch: " + this.bytes.length + " vs. " + bytes2.length);
            }
            System.arraycopy(bytes2, 0, this.bytes, 0, bytes2.length);
        }

        public void writeItem(BinaryOutputStream bos) throws IOException, ImageWriteException {
            bos.write(this.bytes);
        }
    }

    public abstract int getItemLength();

    public abstract void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException;

    TiffOutputItem() {
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return this.offset;
    }

    /* access modifiers changed from: protected */
    public void setOffset(int offset2) {
        this.offset = offset2;
    }
}
