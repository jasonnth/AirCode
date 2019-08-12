package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FingerImageInfo extends AbstractImageInfo {
    private static final byte[] FORMAT_TYPE_VALUE = {0, 9};
    public static final int IMPRESSION_TYPE_LATENT = 7;
    public static final int IMPRESSION_TYPE_LIVE_SCAN_CONTACTLESS = 9;
    public static final int IMPRESSION_TYPE_LIVE_SCAN_PLAIN = 0;
    public static final int IMPRESSION_TYPE_LIVE_SCAN_ROLLED = 1;
    public static final int IMPRESSION_TYPE_NON_LIVE_SCAN_PLAIN = 2;
    public static final int IMPRESSION_TYPE_NON_LIVE_SCAN_ROLLED = 3;
    public static final int IMPRESSION_TYPE_SWIPE = 8;
    public static final int POSITION_LEFT_FULL_PALM = 23;
    public static final int POSITION_LEFT_HYPOTHENAR = 36;
    public static final int POSITION_LEFT_INDEX_FINGER = 7;
    public static final int POSITION_LEFT_INTERDIGITAL = 34;
    public static final int POSITION_LEFT_LITTLE_FINGER = 10;
    public static final int POSITION_LEFT_LOWER_PALM = 27;
    public static final int POSITION_LEFT_MIDDLE_FINGER = 8;
    public static final int POSITION_LEFT_OTHER = 30;
    public static final int POSITION_LEFT_RING_FINGER = 9;
    public static final int POSITION_LEFT_THENAR = 35;
    public static final int POSITION_LEFT_THUMB = 6;
    public static final int POSITION_LEFT_UPPER_PALM = 28;
    public static final int POSITION_LEFT_WRITER_S_PALM = 24;
    public static final int POSITION_PLAIN_LEFT_FOUR_FINGERS = 14;
    public static final int POSITION_PLAIN_RIGHT_FOUR_FINGERS = 13;
    public static final int POSITION_PLAIN_THUMBS = 15;
    public static final int POSITION_RIGHT_FULL_PALM = 21;
    public static final int POSITION_RIGHT_HYPOTHENAR = 33;
    public static final int POSITION_RIGHT_INDEX_FINGER = 2;
    public static final int POSITION_RIGHT_INTERDIGITAL = 31;
    public static final int POSITION_RIGHT_LITTLE_FINGER = 5;
    public static final int POSITION_RIGHT_LOWER_PALM = 25;
    public static final int POSITION_RIGHT_MIDDLE_FINGER = 3;
    public static final int POSITION_RIGHT_OTHER = 29;
    public static final int POSITION_RIGHT_RING_FINGER = 4;
    public static final int POSITION_RIGHT_THENAR = 32;
    public static final int POSITION_RIGHT_THUMB = 1;
    public static final int POSITION_RIGHT_UPPER_PALM = 26;
    public static final int POSITION_RIGHT_WRITER_S_PALM = 22;
    public static final int POSITION_UNKNOWN_FINGER = 0;
    public static final int POSITION_UNKNOWN_PALM = 20;
    private static final long serialVersionUID = -6625447389275461027L;
    private int compressionAlgorithm;
    private int impressionType;
    private int position;
    private int quality;
    private long recordLength;
    private int viewCount;
    private int viewNumber;

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

    public FingerImageInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, InputStream inputStream, int i8, int i9) throws IOException {
        super(2, i6, i7, inputStream, (long) i8, FingerInfo.toMimeType(i9));
        if (i4 < 0 || i4 > 100) {
            throw new IllegalArgumentException("Quality needs to be a number between 0 and 100");
        } else if (inputStream == null) {
            throw new IllegalArgumentException("Null image");
        } else {
            this.position = i;
            this.viewCount = i2;
            this.viewNumber = i3;
            this.quality = i4;
            this.impressionType = i5;
            this.compressionAlgorithm = i9;
            this.recordLength = (long) (i8 + 14);
        }
    }

    public FingerImageInfo(InputStream inputStream, int i) throws IOException {
        super(2, FingerInfo.toMimeType(i));
        this.compressionAlgorithm = i;
        this.compressionAlgorithm = i;
        readObject(inputStream);
    }

    public int getQuality() {
        return this.quality;
    }

    public int getPosition() {
        return this.position;
    }

    public int getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public int getViewNumber() {
        return this.viewNumber;
    }

    public int getImpressionType() {
        return this.impressionType;
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.recordLength = ((long) dataInputStream.readInt()) & 4294967295L;
        this.position = dataInputStream.readUnsignedByte();
        this.viewCount = dataInputStream.readUnsignedByte();
        this.viewNumber = dataInputStream.readUnsignedByte();
        this.quality = dataInputStream.readUnsignedByte();
        this.impressionType = dataInputStream.readUnsignedByte();
        setWidth(dataInputStream.readUnsignedShort());
        setHeight(dataInputStream.readUnsignedShort());
        dataInputStream.readUnsignedByte();
        readImage(inputStream, this.recordLength - 14);
    }

    /* access modifiers changed from: protected */
    public void writeObject(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeImage(byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        long length = (long) (byteArray.length + 14);
        DataOutputStream dataOutputStream = outputStream instanceof DataOutputStream ? (DataOutputStream) outputStream : new DataOutputStream(outputStream);
        dataOutputStream.writeInt((int) (length & 4294967295L));
        dataOutputStream.writeByte(this.position);
        dataOutputStream.writeByte(this.viewCount);
        dataOutputStream.writeByte(this.viewNumber);
        dataOutputStream.writeByte(this.quality);
        dataOutputStream.writeByte(this.impressionType);
        dataOutputStream.writeShort(getWidth());
        dataOutputStream.writeShort(getHeight());
        dataOutputStream.writeByte(0);
        dataOutputStream.write(byteArray);
        dataOutputStream.flush();
    }

    public long getRecordLength() {
        return this.recordLength;
    }

    public byte[] getFormatType() {
        return FORMAT_TYPE_VALUE;
    }

    public int getBiometricSubtype() {
        return toBiometricSubtype(this.position);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FingerImageInfo [");
        stringBuffer.append("quality: " + this.quality + ", ");
        stringBuffer.append("position: " + positionToString(this.position) + ", ");
        stringBuffer.append("impression type: " + impressionTypeToString(this.impressionType) + ", ");
        stringBuffer.append("horizontal line length: " + getWidth() + ", ");
        stringBuffer.append("vertical line length: " + getHeight() + ", ");
        stringBuffer.append("image: ");
        stringBuffer.append(getWidth() + " x " + getHeight());
        stringBuffer.append(" \"" + FingerInfo.toMimeType(this.compressionAlgorithm) + "\"");
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private static String positionToString(int i) {
        switch (i) {
            case 0:
                return "Unknown finger";
            case 1:
                return "Right thumb";
            case 2:
                return "Right index finger";
            case 3:
                return "Right middle finger";
            case 4:
                return "Right ring finger";
            case 5:
                return "Right little finger";
            case 6:
                return "Left thumb";
            case 7:
                return "Left index finger";
            case 8:
                return "Left middle finger";
            case 9:
                return "Left ring finger";
            case 10:
                return "Left little finger";
            case 13:
                return "Right four fingers";
            case 14:
                return "Left four fingers";
            case 15:
                return "Plain thumbs";
            case 20:
                return "Unknown palm";
            case 21:
                return "Right full palm";
            case 22:
                return "Right writer's palm";
            case 23:
                return "Left full palm";
            case 24:
                return "Left writer's palm";
            case 25:
                return "Right lower palm";
            case 26:
                return "Right upper palm";
            case 27:
                return "Left lower palm";
            case 28:
                return "Left upper palm";
            case 29:
                return "Right other";
            case 30:
                return "Left other";
            case 31:
                return "Right interdigital";
            case 32:
                return "Right thenar";
            case 33:
                return "Right hypothenar";
            case 34:
                return "Left interdigital";
            case 35:
                return "Left thenar";
            case 36:
                return "Left hypothenar";
            default:
                return null;
        }
    }

    private static String impressionTypeToString(int i) {
        switch (i) {
            case 0:
                return "Live scan plain";
            case 1:
                return "Live scan rolled";
            case 2:
                return "Non-live scan plain";
            case 3:
                return "Non-live scan rolled";
            case 7:
                return "Latent";
            case 8:
                return "Swipe";
            case 9:
                return "Live scan contactless";
            default:
                return null;
        }
    }

    private static int toBiometricSubtype(int i) {
        switch (i) {
            case 1:
                return 5;
            case 2:
                return 9;
            case 3:
                return 13;
            case 4:
                return 17;
            case 5:
                return 21;
            case 6:
                return 6;
            case 7:
                return 10;
            case 8:
                return 14;
            case 9:
                return 18;
            case 10:
                return 22;
            case 13:
                return 1;
            case 14:
                return 2;
            case 15:
                return 4;
            case 21:
                return 1;
            case 23:
                return 2;
            case 24:
                return 2;
            case 25:
                return 1;
            case 26:
                return 1;
            case 27:
                return 2;
            case 28:
                return 2;
            case 29:
                return 1;
            case 30:
                return 2;
            case 31:
                return 1;
            case 32:
                return 1;
            case 33:
                return 1;
            case 34:
                return 2;
            case 35:
                return 2;
            case 36:
                return 2;
            default:
                return 0;
        }
    }
}
