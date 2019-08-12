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

public class IrisInfo extends AbstractListInfo<IrisBiometricSubtypeInfo> implements BiometricDataBlock {
    public static final int CAPTURE_DEVICE_UNDEF = 0;
    private static final int FORMAT_IDENTIFIER = 1229541888;
    private static final int FORMAT_OWNER_VALUE = 257;
    private static final int FORMAT_TYPE_VALUE = 9;
    public static final int IMAGEFORMAT_MONO_JPEG = 6;
    public static final int IMAGEFORMAT_MONO_JPEG2000 = 14;
    public static final int IMAGEFORMAT_MONO_JPEG_LS = 10;
    public static final int IMAGEFORMAT_MONO_RAW = 2;
    public static final int IMAGEFORMAT_RGB_JPEG = 8;
    public static final int IMAGEFORMAT_RGB_JPEG2000 = 16;
    public static final int IMAGEFORMAT_RGB_JPEG_LS = 12;
    public static final int IMAGEFORMAT_RGB_RAW = 4;
    public static final int INTENSITY_DEPTH_UNDEF = 0;
    public static final int IRBNDY_PROCESSED = 1;
    public static final int IRBNDY_UNDEF = 0;
    public static final int IROCC_PROCESSED = 1;
    public static final int IROCC_UNDEF = 0;
    public static final int IROCC_ZEROFILL = 0;
    public static final int IROC_UNITFILL = 1;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int ORIENTATION_BASE = 1;
    public static final int ORIENTATION_FLIPPED = 2;
    public static final int ORIENTATION_UNDEF = 0;
    public static final int SCAN_TYPE_CORRECTED = 4;
    public static final int SCAN_TYPE_INTERLACE_FIELD = 3;
    public static final int SCAN_TYPE_INTERLACE_FRAME = 2;
    public static final int SCAN_TYPE_PROGRESSIVE = 1;
    public static final int SCAN_TYPE_UNDEF = 0;
    public static final int TRANS_STD = 1;
    public static final int TRANS_UNDEF = 0;
    private static final int VERSION_NUMBER = 808529920;
    private static final long serialVersionUID = -3415309711643815511L;
    private int boundaryExtraction;
    private int captureDeviceId;
    private byte[] deviceUniqueId;
    private int horizontalOrientation;
    private int imageFormat;
    private int imageTransformation;
    private int intensityDepth;
    private int irisDiameter;
    private int irisOcclusion;
    private int occlusionFilling;
    private int rawImageHeight;
    private int rawImageWidth;
    private long recordLength;
    private StandardBiometricHeader sbh;
    private int scanType;
    private int verticalOrientation;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public IrisInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr, List<IrisBiometricSubtypeInfo> list) {
        this(null, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, bArr, list);
    }

    public IrisInfo(StandardBiometricHeader standardBiometricHeader, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr, List<IrisBiometricSubtypeInfo> list) {
        this.sbh = standardBiometricHeader;
        if (list == null) {
            throw new IllegalArgumentException("Null irisBiometricSubtypeInfos");
        }
        this.captureDeviceId = i;
        this.horizontalOrientation = i2;
        this.verticalOrientation = i3;
        this.scanType = i4;
        this.irisOcclusion = i5;
        this.occlusionFilling = i6;
        this.boundaryExtraction = i7;
        this.irisDiameter = i8;
        this.imageFormat = i9;
        this.rawImageWidth = i10;
        this.rawImageHeight = i11;
        this.intensityDepth = i12;
        this.imageTransformation = i13;
        long j = 0;
        for (IrisBiometricSubtypeInfo irisBiometricSubtypeInfo : list) {
            j += irisBiometricSubtypeInfo.getRecordLength();
            add(irisBiometricSubtypeInfo);
        }
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("deviceUniqueId invalid");
        }
        this.deviceUniqueId = new byte[16];
        System.arraycopy(bArr, 0, this.deviceUniqueId, 0, bArr.length);
        this.recordLength = 45 + j;
    }

    public IrisInfo(InputStream inputStream) throws IOException {
        this(null, inputStream);
    }

    public IrisInfo(StandardBiometricHeader standardBiometricHeader, InputStream inputStream) throws IOException {
        this.sbh = standardBiometricHeader;
        readObject(inputStream);
    }

    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        int readInt = dataInputStream.readInt();
        if (readInt != FORMAT_IDENTIFIER) {
            throw new IllegalArgumentException("'IIR' marker expected! Found " + Integer.toHexString(readInt));
        }
        int readInt2 = dataInputStream.readInt();
        if (readInt2 != VERSION_NUMBER) {
            throw new IllegalArgumentException("'010' version number expected! Found " + Integer.toHexString(readInt2));
        }
        this.recordLength = (long) dataInputStream.readInt();
        long j = this.recordLength - 45;
        this.captureDeviceId = dataInputStream.readUnsignedShort();
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        if (((long) readUnsignedShort) != 45) {
            throw new IllegalArgumentException("Expected header length " + 45 + ", found " + readUnsignedShort);
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        this.horizontalOrientation = readUnsignedShort2 & 3;
        this.verticalOrientation = (readUnsignedShort2 & 12) >> 2;
        this.scanType = (readUnsignedShort2 & 112) >> 4;
        this.irisOcclusion = (readUnsignedShort2 & 128) >> 7;
        this.occlusionFilling = (readUnsignedShort2 & 256) >> 8;
        this.boundaryExtraction = (readUnsignedShort2 & 512) >> 9;
        this.irisDiameter = dataInputStream.readUnsignedShort();
        this.imageFormat = dataInputStream.readUnsignedShort();
        this.rawImageWidth = dataInputStream.readUnsignedShort();
        this.rawImageHeight = dataInputStream.readUnsignedShort();
        this.intensityDepth = dataInputStream.readUnsignedByte();
        this.imageTransformation = dataInputStream.readUnsignedByte();
        this.deviceUniqueId = new byte[16];
        dataInputStream.readFully(this.deviceUniqueId);
        long j2 = 0;
        for (int i = 0; i < readUnsignedByte; i++) {
            IrisBiometricSubtypeInfo irisBiometricSubtypeInfo = new IrisBiometricSubtypeInfo(inputStream, this.imageFormat);
            j2 += irisBiometricSubtypeInfo.getRecordLength();
            add(irisBiometricSubtypeInfo);
        }
        if (j != j2) {
            LOGGER.warning("DEBUG: constructedDataLength and dataLength differ: dataLength = " + j + ", constructedDataLength = " + j2);
        }
    }

    public void writeObject(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream;
        List<IrisBiometricSubtypeInfo> subRecords = getSubRecords();
        int i = 0;
        for (IrisBiometricSubtypeInfo recordLength2 : subRecords) {
            i = (int) (recordLength2.getRecordLength() + ((long) i));
        }
        int i2 = i + 45;
        if (outputStream instanceof DataOutputStream) {
            dataOutputStream = (DataOutputStream) outputStream;
        } else {
            dataOutputStream = new DataOutputStream(outputStream);
        }
        dataOutputStream.writeInt(FORMAT_IDENTIFIER);
        dataOutputStream.writeInt(VERSION_NUMBER);
        dataOutputStream.writeInt(i2);
        dataOutputStream.writeShort(this.captureDeviceId);
        dataOutputStream.writeByte(subRecords.size());
        dataOutputStream.writeShort(45);
        dataOutputStream.writeShort((this.horizontalOrientation & 3) | 0 | ((this.verticalOrientation << 2) & 12) | ((this.scanType << 4) & 112) | ((this.irisOcclusion << 7) & 128) | ((this.occlusionFilling << 8) & 256) | ((this.boundaryExtraction << 9) & 512));
        dataOutputStream.writeShort(this.irisDiameter);
        dataOutputStream.writeShort(this.imageFormat);
        dataOutputStream.writeShort(this.rawImageWidth);
        dataOutputStream.writeShort(this.rawImageHeight);
        dataOutputStream.writeByte(this.intensityDepth);
        dataOutputStream.writeByte(this.imageTransformation);
        dataOutputStream.write(this.deviceUniqueId);
        for (IrisBiometricSubtypeInfo writeObject : subRecords) {
            writeObject.writeObject(outputStream);
        }
    }

    public int getCaptureDeviceId() {
        return this.captureDeviceId;
    }

    public int getHorizontalOrientation() {
        return this.horizontalOrientation;
    }

    public int getVerticalOrientation() {
        return this.verticalOrientation;
    }

    public int getScanType() {
        return this.scanType;
    }

    public int getIrisOcclusion() {
        return this.irisOcclusion;
    }

    public int getOcclusionFilling() {
        return this.occlusionFilling;
    }

    public int getBoundaryExtraction() {
        return this.boundaryExtraction;
    }

    public int getIrisDiameter() {
        return this.irisDiameter;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public int getRawImageWidth() {
        return this.rawImageWidth;
    }

    public int getRawImageHeight() {
        return this.rawImageHeight;
    }

    public int getIntensityDepth() {
        return this.intensityDepth;
    }

    public int getImageTransformation() {
        return this.imageTransformation;
    }

    public byte[] getDeviceUniqueId() {
        return this.deviceUniqueId;
    }

    public StandardBiometricHeader getStandardBiometricHeader() {
        if (this.sbh == null) {
            byte[] bArr = {8};
            byte[] bArr2 = {(byte) getBiometricSubtype()};
            byte[] bArr3 = {1, 1};
            byte[] bArr4 = {0, 9};
            TreeMap treeMap = new TreeMap();
            treeMap.put(Integer.valueOf(129), bArr);
            treeMap.put(Integer.valueOf(ISO781611.BIOMETRIC_SUBTYPE_TAG), bArr2);
            treeMap.put(Integer.valueOf(135), bArr3);
            treeMap.put(Integer.valueOf(136), bArr4);
            this.sbh = new StandardBiometricHeader(treeMap);
        }
        return this.sbh;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IrisInfo [");
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public List<IrisBiometricSubtypeInfo> getIrisBiometricSubtypeInfos() {
        return getSubRecords();
    }

    public void addIrisBiometricSubtypeInfo(IrisBiometricSubtypeInfo irisBiometricSubtypeInfo) {
        add(irisBiometricSubtypeInfo);
    }

    public void removeIrisBiometricSubtypeInfo(int i) {
        remove(i);
    }

    private int getBiometricSubtype() {
        int i = 0;
        Iterator it = getSubRecords().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((IrisBiometricSubtypeInfo) it.next()).getBiometricSubtype() & i2;
        }
    }
}
