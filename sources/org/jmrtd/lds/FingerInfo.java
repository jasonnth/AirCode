package org.jmrtd.lds;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.jmrtd.cbeff.BiometricDataBlock;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.cbeff.StandardBiometricHeader;

public class FingerInfo extends AbstractListInfo<FingerImageInfo> implements BiometricDataBlock {
    public static final int COMPRESSION_JPEG = 3;
    public static final int COMPRESSION_JPEG2000 = 4;
    public static final int COMPRESSION_PNG = 5;
    public static final int COMPRESSION_UNCOMPRESSED_BIT_PACKED = 1;
    public static final int COMPRESSION_UNCOMPRESSED_NO_BIT_PACKING = 0;
    public static final int COMPRESSION_WSQ = 2;
    private static final int FORMAT_IDENTIFIER = 1179210240;
    private static final int FORMAT_OWNER_VALUE = 257;
    private static final int FORMAT_TYPE_VALUE = 7;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int SCALE_UNITS_PPCM = 2;
    public static final int SCALE_UNITS_PPI = 1;
    private static final int VERSION_NUMBER = 808529920;
    private static final long serialVersionUID = 5808625058034008176L;
    private int acquisitionLevel;
    private int captureDeviceId;
    private int compressionAlgorithm;
    private int depth;
    private int imageResolutionHorizontal;
    private int imageResolutionVertical;
    private StandardBiometricHeader sbh;
    private int scaleUnits;
    private int scanResolutionHorizontal;
    private int scanResolutionVertical;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public FingerInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List<FingerImageInfo> list) {
        this(null, i, i2, i3, i4, i5, i6, i7, i8, i9, list);
    }

    public FingerInfo(StandardBiometricHeader standardBiometricHeader, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, List<FingerImageInfo> list) {
        this.sbh = standardBiometricHeader;
        this.captureDeviceId = i;
        this.acquisitionLevel = i2;
        this.scaleUnits = i3;
        this.scanResolutionHorizontal = i4;
        this.scanResolutionVertical = i5;
        this.imageResolutionHorizontal = i6;
        this.imageResolutionVertical = i7;
        this.depth = i8;
        this.compressionAlgorithm = i9;
        addAll(list);
    }

    public FingerInfo(InputStream inputStream) throws IOException {
        this(null, inputStream);
    }

    public FingerInfo(StandardBiometricHeader standardBiometricHeader, InputStream inputStream) throws IOException {
        this.sbh = standardBiometricHeader;
        readObject(inputStream);
    }

    public int getCaptureDeviceId() {
        return this.captureDeviceId;
    }

    public int getAcquisitionLevel() {
        return this.acquisitionLevel;
    }

    public int getScaleUnits() {
        return this.scaleUnits;
    }

    public int getHorizontalScanningResolution() {
        return this.scanResolutionHorizontal;
    }

    public int getVerticalScanningResolution() {
        return this.scanResolutionVertical;
    }

    public int getHorizontalImageResolution() {
        return this.imageResolutionHorizontal;
    }

    public int getVerticalImageResolution() {
        return this.imageResolutionVertical;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        int readInt = dataInputStream.readInt();
        if (readInt != FORMAT_IDENTIFIER) {
            throw new IllegalArgumentException("'FIR' marker expected! Found " + Integer.toHexString(readInt));
        }
        int readInt2 = dataInputStream.readInt();
        if (readInt2 != VERSION_NUMBER) {
            throw new IllegalArgumentException("'010' version number expected! Found " + Integer.toHexString(readInt2));
        }
        long readUnsignedLong = readUnsignedLong(dataInputStream, 6);
        this.captureDeviceId = dataInputStream.readUnsignedShort();
        this.acquisitionLevel = dataInputStream.readUnsignedShort();
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        this.scaleUnits = dataInputStream.readUnsignedByte();
        this.scanResolutionHorizontal = dataInputStream.readUnsignedShort();
        this.scanResolutionVertical = dataInputStream.readUnsignedShort();
        this.imageResolutionHorizontal = dataInputStream.readUnsignedShort();
        this.imageResolutionVertical = dataInputStream.readUnsignedShort();
        this.depth = dataInputStream.readUnsignedByte();
        this.compressionAlgorithm = dataInputStream.readUnsignedByte();
        dataInputStream.readUnsignedShort();
        long j = readUnsignedLong - 32;
        long j2 = 0;
        for (int i = 0; i < readUnsignedByte; i++) {
            FingerImageInfo fingerImageInfo = new FingerImageInfo(inputStream, this.compressionAlgorithm);
            j2 += fingerImageInfo.getRecordLength();
            add(fingerImageInfo);
        }
        if (j != j2) {
            LOGGER.warning("DEBUG: constructedDataLength and dataLength differ: dataLength = " + j + ", constructedDataLength = " + j2);
        }
    }

    public void writeObject(OutputStream outputStream) throws IOException {
        long j;
        DataOutputStream dataOutputStream;
        long j2 = 0;
        List<FingerImageInfo> subRecords = getSubRecords();
        Iterator it = subRecords.iterator();
        while (true) {
            j = j2;
            if (!it.hasNext()) {
                break;
            }
            j2 = ((FingerImageInfo) it.next()).getRecordLength() + j;
        }
        long j3 = j + 32;
        if (outputStream instanceof DataOutputStream) {
            dataOutputStream = (DataOutputStream) outputStream;
        } else {
            dataOutputStream = new DataOutputStream(outputStream);
        }
        dataOutputStream.writeInt(FORMAT_IDENTIFIER);
        dataOutputStream.writeInt(VERSION_NUMBER);
        writeLong(j3, dataOutputStream, 6);
        dataOutputStream.writeShort(this.captureDeviceId);
        dataOutputStream.writeShort(this.acquisitionLevel);
        dataOutputStream.writeByte(subRecords.size());
        dataOutputStream.writeByte(this.scaleUnits);
        dataOutputStream.writeShort(this.scanResolutionHorizontal);
        dataOutputStream.writeShort(this.scanResolutionVertical);
        dataOutputStream.writeShort(this.imageResolutionHorizontal);
        dataOutputStream.writeShort(this.imageResolutionVertical);
        dataOutputStream.writeByte(this.depth);
        dataOutputStream.writeByte(this.compressionAlgorithm);
        dataOutputStream.writeShort(0);
        for (FingerImageInfo writeObject : subRecords) {
            writeObject.writeObject(dataOutputStream);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FingerInfo [");
        for (FingerImageInfo fingerImageInfo : getSubRecords()) {
            stringBuffer.append(fingerImageInfo.toString());
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public StandardBiometricHeader getStandardBiometricHeader() {
        if (this.sbh == null) {
            byte[] bArr = {8};
            byte[] bArr2 = {(byte) getBiometricSubtype()};
            byte[] bArr3 = {1, 1};
            byte[] bArr4 = {0, 7};
            TreeMap treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(129), bArr);
            treeMap.put(Integer.valueOf(ISO781611.BIOMETRIC_SUBTYPE_TAG), bArr2);
            treeMap.put(Integer.valueOf(135), bArr3);
            treeMap.put(Integer.valueOf(136), bArr4);
            this.sbh = new StandardBiometricHeader(treeMap);
        }
        return this.sbh;
    }

    public List<FingerImageInfo> getFingerImageInfos() {
        return getSubRecords();
    }

    public void addFingerImageInfo(FingerImageInfo fingerImageInfo) {
        add(fingerImageInfo);
    }

    public void removeFingerImageInfo(int i) {
        remove(i);
    }

    private static long readUnsignedLong(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        (inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream)).readFully(bArr);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) + ((long) (bArr[i2] & 255));
        }
        return j;
    }

    private static void writeLong(long j, OutputStream outputStream, int i) throws IOException {
        if (i > 0) {
            for (int i2 = 0; i2 < i - 8; i2++) {
                outputStream.write(0);
            }
            if (i > 8) {
                i = 8;
            }
            for (int i3 = i - 1; i3 >= 0; i3--) {
                outputStream.write((byte) ((int) (((255 << (i3 * 8)) & j) >> (i3 * 8))));
            }
        }
    }

    static String toMimeType(int i) {
        switch (i) {
            case 0:
                return "image/raw";
            case 1:
                return "image/raw";
            case 2:
                return ImageInfo.WSQ_MIME_TYPE;
            case 3:
                return "image/jpeg";
            case 4:
                return "image/jpeg2000";
            case 5:
                return "image/png";
            default:
                return null;
        }
    }

    static int fromMimeType(String str) {
        if (ImageInfo.WSQ_MIME_TYPE.equals(str)) {
            return 2;
        }
        if ("image/jpeg".equals(str)) {
            return 3;
        }
        if ("image/jpeg2000".equals(str)) {
            return 4;
        }
        if ("images/png".equals(str)) {
            return 5;
        }
        throw new IllegalArgumentException("Did not recognize mimeType");
    }

    private int getBiometricSubtype() {
        int i;
        boolean z;
        boolean z2 = true;
        int i2 = 0;
        for (FingerImageInfo biometricSubtype : getSubRecords()) {
            int biometricSubtype2 = biometricSubtype.getBiometricSubtype();
            if (z2) {
                i = biometricSubtype2;
                z = false;
            } else {
                boolean z3 = z2;
                i = biometricSubtype2 & i2;
                z = z3;
            }
            i2 = i;
            z2 = z;
        }
        return i2;
    }
}
