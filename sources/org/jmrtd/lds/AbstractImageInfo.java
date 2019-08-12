package org.jmrtd.lds;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Logger;
import org.jmrtd.p321io.SplittableInputStream;

abstract class AbstractImageInfo implements ImageInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2870092217269116309L;
    private int height;
    private byte[] imageBytes;
    int imageLength;
    int imagePositionInInputStream;
    private String mimeType;
    private SplittableInputStream splittableInputStream;
    private int type;
    private int width;

    public abstract long getRecordLength();

    /* access modifiers changed from: protected */
    public abstract void readObject(InputStream inputStream) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void writeObject(OutputStream outputStream) throws IOException;

    AbstractImageInfo() {
    }

    AbstractImageInfo(int i) {
        this.type = i;
    }

    AbstractImageInfo(int i, String str) {
        this(i);
        this.mimeType = str;
    }

    private AbstractImageInfo(int i, int i2, int i3, String str) {
        this(i, str);
        this.width = i2;
        this.height = i3;
    }

    public AbstractImageInfo(int i, int i2, int i3, InputStream inputStream, long j, String str) throws IOException {
        this(i, i2, i3, str);
        readImage(inputStream, j);
    }

    public int getType() {
        return this.type;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getImageLength() {
        if (this.splittableInputStream != null) {
            return this.imageLength;
        }
        if (this.imageBytes != null) {
            return this.imageBytes.length;
        }
        throw new IllegalStateException("DEBUG");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getSimpleName());
        stringBuffer.append(" [");
        stringBuffer.append("type: " + typeToString(this.type) + ", ");
        stringBuffer.append("size: " + getImageLength());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        return ((this.mimeType == null ? 1337 : this.mimeType.hashCode()) * 5) + 7 + ((this.type * 5) - 591263623) + (getImageLength() * 7) + 11;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        try {
            if (!obj.getClass().equals(getClass())) {
                return false;
            }
            AbstractImageInfo abstractImageInfo = (AbstractImageInfo) obj;
            if (!Arrays.equals(getImageBytes(), abstractImageInfo.getImageBytes()) || ((!(this.mimeType == null && abstractImageInfo.mimeType == null) && (this.mimeType == null || !this.mimeType.equals(abstractImageInfo.mimeType))) || this.type != abstractImageInfo.type)) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return false;
        }
    }

    public byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeObject(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    public InputStream getImageInputStream() {
        if (this.splittableInputStream != null) {
            return this.splittableInputStream.getInputStream(this.imagePositionInInputStream);
        }
        if (this.imageBytes != null) {
            return new ByteArrayInputStream(this.imageBytes);
        }
        throw new IllegalStateException("DEBUG");
    }

    /* access modifiers changed from: protected */
    public void readImage(InputStream inputStream, long j) throws IOException {
        if (inputStream instanceof SplittableInputStream) {
            this.imageBytes = null;
            this.splittableInputStream = (SplittableInputStream) inputStream;
            this.imagePositionInInputStream = this.splittableInputStream.getPosition();
            this.imageLength = (int) j;
            long j2 = 0;
            while (j2 < j) {
                j2 += this.splittableInputStream.skip(j - j2);
            }
            return;
        }
        this.splittableInputStream = null;
        this.imageBytes = new byte[((int) j)];
        new DataInputStream(inputStream).readFully(this.imageBytes);
    }

    /* access modifiers changed from: protected */
    public void writeImage(OutputStream outputStream) throws IOException {
        outputStream.write(getImageBytes());
    }

    /* access modifiers changed from: protected */
    public final void setMimeType(String str) {
        this.mimeType = str;
    }

    /* access modifiers changed from: protected */
    public final void setType(int i) {
        this.type = i;
    }

    /* access modifiers changed from: protected */
    public final void setWidth(int i) {
        this.width = i;
    }

    /* access modifiers changed from: protected */
    public final void setHeight(int i) {
        this.height = i;
    }

    /* access modifiers changed from: protected */
    public final void setImageBytes(byte[] bArr) {
        try {
            readImage(new ByteArrayInputStream(bArr), (long) bArr.length);
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
        }
    }

    private byte[] getImageBytes() throws IOException {
        byte[] bArr = new byte[getImageLength()];
        new DataInputStream(getImageInputStream()).readFully(bArr);
        return bArr;
    }

    private static String typeToString(int i) {
        switch (i) {
            case 0:
                return "Portrait";
            case 1:
                return "Signature or usual mark";
            case 2:
                return "Finger";
            case 3:
                return "Iris";
            default:
                throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
        }
    }
}
