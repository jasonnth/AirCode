package org.jmrtd.lds;

import com.facebook.soloader.MinElf;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IrisImageInfo extends AbstractImageInfo {
    public static int IMAGE_QUAL_HIGH_HI = 100;
    public static int IMAGE_QUAL_HIGH_LO = 76;
    public static int IMAGE_QUAL_LOW_HI = 50;
    public static int IMAGE_QUAL_LOW_LO = 26;
    public static int IMAGE_QUAL_MED_HI = 75;
    public static int IMAGE_QUAL_MED_LO = 51;
    public static int IMAGE_QUAL_UNDEF = 254;
    private static final int ROT_ANGLE_UNDEF = 65535;
    private static final int ROT_UNCERTAIN_UNDEF = 65535;
    private static final long serialVersionUID = 833541246115625112L;
    private int imageFormat;
    private int imageNumber;
    private int quality;
    private int rotationAngle;
    private int rotationAngleUncertainty;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public /* bridge */ /* synthetic */ int getHeight() {
        return super.getHeight();
    }

    public /* bridge */ /* synthetic */ InputStream getImageInputStream() {
        return super.getImageInputStream();
    }

    public /* bridge */ /* synthetic */ int getImageLength() {
        return super.getImageLength();
    }

    public /* bridge */ /* synthetic */ String getMimeType() {
        return super.getMimeType();
    }

    public /* bridge */ /* synthetic */ int getType() {
        return super.getType();
    }

    public /* bridge */ /* synthetic */ int getWidth() {
        return super.getWidth();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public IrisImageInfo(int i, int i2, int i3, int i4, int i5, int i6, InputStream inputStream, int i7, int i8) throws IOException {
        super(3, i5, i6, inputStream, (long) i7, getMimeTypeFromImageFormat(i8));
        if (inputStream == null) {
            throw new IllegalArgumentException("Null image bytes");
        }
        this.imageNumber = i;
        this.quality = i2;
        this.rotationAngle = i3;
        this.rotationAngleUncertainty = i4;
    }

    public IrisImageInfo(int i, int i2, int i3, InputStream inputStream, int i4, int i5) throws IOException {
        this(i, IMAGE_QUAL_UNDEF, MinElf.PN_XNUM, MinElf.PN_XNUM, i2, i3, inputStream, i4, i5);
    }

    IrisImageInfo(InputStream inputStream, int i) throws IOException {
        super(3);
        this.imageFormat = i;
        setMimeType(getMimeTypeFromImageFormat(i));
        readObject(inputStream);
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public int getImageNumber() {
        return this.imageNumber;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    public int getRotationAngleUncertainty() {
        return this.rotationAngleUncertainty;
    }

    public long getRecordLength() {
        return (long) (getImageLength() + 11);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IrisImageInfo [");
        stringBuffer.append("image number: " + this.imageNumber + ", ");
        stringBuffer.append("quality: " + this.quality + ", ");
        stringBuffer.append("image: ");
        stringBuffer.append(getWidth() + " x " + getHeight());
        stringBuffer.append("mime-type: " + getMimeTypeFromImageFormat(this.imageFormat));
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.imageNumber = dataInputStream.readUnsignedShort();
        this.quality = dataInputStream.readUnsignedByte();
        this.rotationAngle = dataInputStream.readShort();
        this.rotationAngleUncertainty = dataInputStream.readUnsignedShort();
        readImage(inputStream, ((long) dataInputStream.readInt()) & 4294967295L);
    }

    /* access modifiers changed from: protected */
    public void writeObject(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = outputStream instanceof DataOutputStream ? (DataOutputStream) outputStream : new DataOutputStream(outputStream);
        dataOutputStream.writeShort(this.imageNumber);
        dataOutputStream.writeByte(this.quality);
        dataOutputStream.writeShort(this.rotationAngle);
        dataOutputStream.writeShort(this.rotationAngleUncertainty);
        dataOutputStream.writeInt(getImageLength());
        writeImage(dataOutputStream);
    }

    private static String getMimeTypeFromImageFormat(int i) {
        switch (i) {
            case 2:
            case 4:
                return ImageInfo.WSQ_MIME_TYPE;
            case 6:
            case 8:
            case 10:
            case 12:
                return "image/jpeg";
            case 14:
            case 16:
                return ImageInfo.JPEG2000_MIME_TYPE;
            default:
                return null;
        }
    }
}
