package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import net.p318sf.scuba.data.Gender;

public class FaceImageInfo extends AbstractImageInfo {
    public static final short EXPRESSION_EYES_LOOKING_AWAY = 5;
    public static final short EXPRESSION_FROWNING = 7;
    public static final short EXPRESSION_NEUTRAL = 1;
    public static final short EXPRESSION_RAISED_EYEBROWS = 4;
    public static final short EXPRESSION_SMILE_CLOSED = 2;
    public static final short EXPRESSION_SMILE_OPEN = 3;
    public static final short EXPRESSION_SQUINTING = 6;
    public static final short EXPRESSION_UNSPECIFIED = 0;
    public static final int EYE_COLOR_BLACK = 1;
    public static final int EYE_COLOR_BLUE = 2;
    public static final int EYE_COLOR_BROWN = 3;
    public static final int EYE_COLOR_GRAY = 4;
    public static final int EYE_COLOR_GREEN = 5;
    public static final int EYE_COLOR_MULTI_COLORED = 6;
    public static final int EYE_COLOR_PINK = 7;
    public static final int EYE_COLOR_UNKNOWN = 8;
    public static final int EYE_COLOR_UNSPECIFIED = 0;
    public static final int FACE_IMAGE_TYPE_BASIC = 0;
    public static final int FACE_IMAGE_TYPE_FULL_FRONTAL = 1;
    public static final int FACE_IMAGE_TYPE_TOKEN_FRONTAL = 2;
    private static final int FEATURE_BEARD_FLAG = 8;
    private static final int FEATURE_BLINK_FLAG = 32;
    private static final int FEATURE_DARK_GLASSES = 512;
    private static final int FEATURE_DISTORTING_MEDICAL_CONDITION = 1024;
    private static final int FEATURE_FEATURES_ARE_SPECIFIED_FLAG = 1;
    private static final int FEATURE_GLASSES_FLAG = 2;
    private static final int FEATURE_LEFT_EYE_PATCH_FLAG = 128;
    private static final int FEATURE_MOUSTACHE_FLAG = 4;
    private static final int FEATURE_MOUTH_OPEN_FLAG = 64;
    private static final int FEATURE_RIGHT_EYE_PATCH = 256;
    private static final int FEATURE_TEETH_VISIBLE_FLAG = 16;
    public static final int HAIR_COLOR_BALD = 1;
    public static final int HAIR_COLOR_BLACK = 2;
    public static final int HAIR_COLOR_BLONDE = 3;
    public static final int HAIR_COLOR_BLUE = 9;
    public static final int HAIR_COLOR_BROWN = 4;
    public static final int HAIR_COLOR_GRAY = 5;
    public static final int HAIR_COLOR_GREEN = 8;
    public static final int HAIR_COLOR_RED = 7;
    public static final int HAIR_COLOR_UNKNOWN = 255;
    public static final int HAIR_COLOR_UNSPECIFIED = 0;
    public static final int HAIR_COLOR_WHITE = 6;
    public static final int IMAGE_COLOR_SPACE_GRAY8 = 3;
    public static final int IMAGE_COLOR_SPACE_OTHER = 4;
    public static final int IMAGE_COLOR_SPACE_RGB24 = 1;
    public static final int IMAGE_COLOR_SPACE_UNSPECIFIED = 0;
    public static final int IMAGE_COLOR_SPACE_YUV422 = 2;
    public static final int IMAGE_DATA_TYPE_JPEG = 0;
    public static final int IMAGE_DATA_TYPE_JPEG2000 = 1;
    private static final int PITCH = 1;
    private static final int ROLL = 2;
    public static final int SOURCE_TYPE_STATIC_PHOTO_DIGITAL_CAM = 2;
    public static final int SOURCE_TYPE_STATIC_PHOTO_SCANNER = 3;
    public static final int SOURCE_TYPE_STATIC_PHOTO_UNKNOWN_SOURCE = 1;
    public static final int SOURCE_TYPE_UNKNOWN = 7;
    public static final int SOURCE_TYPE_UNSPECIFIED = 0;
    public static final int SOURCE_TYPE_VIDEO_FRAME_ANALOG_CAM = 5;
    public static final int SOURCE_TYPE_VIDEO_FRAME_DIGITAL_CAM = 6;
    public static final int SOURCE_TYPE_VIDEO_FRAME_UNKNOWN_SOURCE = 4;
    private static final int YAW = 0;
    private static final long serialVersionUID = -1751069410327594067L;
    private int colorSpace;
    private int deviceType;
    private int expression;
    private EyeColor eyeColor;
    private int faceImageType;
    private int featureMask;
    private FeaturePoint[] featurePoints;
    private Gender gender;
    private int hairColor;
    private int imageDataType;
    private int[] poseAngle;
    private int[] poseAngleUncertainty;
    private int quality;
    private long recordLength;
    private int sourceType;

    public enum Expression {
        UNSPECIFIED,
        NEUTRAL,
        SMILE_CLOSED,
        SMILE_OPEN,
        RAISED_EYEBROWS,
        EYES_LOOKING_AWAY,
        SQUINTING,
        FROWNING
    }

    public enum EyeColor {
        UNSPECIFIED {
            public int toInt() {
                return 0;
            }
        },
        BLACK {
            public int toInt() {
                return 1;
            }
        },
        BLUE {
            public int toInt() {
                return 2;
            }
        },
        BROWN {
            public int toInt() {
                return 3;
            }
        },
        GRAY {
            public int toInt() {
                return 4;
            }
        },
        GREEN {
            public int toInt() {
                return 5;
            }
        },
        MULTI_COLORED {
            public int toInt() {
                return 6;
            }
        },
        PINK {
            public int toInt() {
                return 7;
            }
        },
        UNKNOWN {
            public int toInt() {
                return 8;
            }
        };

        public abstract int toInt();

        static EyeColor toEyeColor(int i) {
            EyeColor[] values;
            for (EyeColor eyeColor : values()) {
                if (eyeColor.toInt() == i) {
                    return eyeColor;
                }
            }
            return null;
        }
    }

    public enum FaceImageType {
        BASIC,
        FULL_FRONTAL,
        TOKEN_FRONTAL
    }

    public static class FeaturePoint {
        private int majorCode;
        private int minorCode;
        private int type;

        /* renamed from: x */
        private int f6334x;

        /* renamed from: y */
        private int f6335y;

        public FeaturePoint(int i, int i2, int i3, int i4, int i5) {
            this.type = i;
            this.majorCode = i2;
            this.minorCode = i3;
            this.f6334x = i4;
            this.f6335y = i5;
        }

        FeaturePoint(int i, byte b, int i2, int i3) {
            this(i, (b & 240) >> 4, b & 15, i2, i3);
        }

        public int getMajorCode() {
            return this.majorCode;
        }

        public int getMinorCode() {
            return this.minorCode;
        }

        public int getType() {
            return this.type;
        }

        public int getX() {
            return this.f6334x;
        }

        public int getY() {
            return this.f6335y;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("( point: ");
            stringBuffer.append(getMajorCode());
            stringBuffer.append(".");
            stringBuffer.append(getMinorCode());
            stringBuffer.append(", ");
            stringBuffer.append("type: ");
            stringBuffer.append(Integer.toHexString(this.type));
            stringBuffer.append(", ");
            stringBuffer.append("(");
            stringBuffer.append(this.f6334x);
            stringBuffer.append(", ");
            stringBuffer.append(this.f6335y);
            stringBuffer.append(")");
            stringBuffer.append(")");
            return stringBuffer.toString();
        }
    }

    public enum Features {
        FEATURES_ARE_SPECIFIED,
        GLASSES,
        MOUSTACHE,
        BEARD,
        TEETH_VISIBLE,
        BLINK,
        MOUTH_OPEN,
        LEFT_EYE_PATCH,
        RIGHT_EYE_PATCH,
        DARK_GLASSES,
        DISTORTING_MEDICAL_CONDITION
    }

    public enum HairColor {
        UNSPECIFIED,
        BALD,
        BLACK,
        BLONDE,
        BROWN,
        GRAY,
        WHITE,
        RED,
        GREEN,
        BLUE,
        UNKNOWN
    }

    public enum ImageColorSpace {
        UNSPECIFIED,
        RGB24,
        YUV422,
        GRAY8,
        OTHER
    }

    public enum ImageDataType {
        TYPE_JPEG,
        TYPE_JPEG2000
    }

    public enum SourceType {
        UNSPECIFIED,
        STATIC_PHOTO_UNKNOWN_SOURCE,
        STATIC_PHOTO_DIGITAL_CAM,
        STATIC_PHOTO_SCANNER,
        VIDEO_FRAME_UNKNOWN_SOURCE,
        VIDEO_FRAME_ANALOG_CAM,
        VIDEO_FRAME_DIGITAL_CAM,
        UNKNOWN
    }

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

    public FaceImageInfo(Gender gender2, EyeColor eyeColor2, int i, int i2, int i3, int[] iArr, int[] iArr2, int i4, int i5, int i6, int i7, int i8, FeaturePoint[] featurePointArr, int i9, int i10, InputStream inputStream, int i11, int i12) throws IOException {
        super(0, i9, i10, inputStream, (long) i11, toMimeType(i12));
        if (inputStream == null) {
            throw new IllegalArgumentException("Null image");
        }
        this.gender = gender2;
        this.eyeColor = eyeColor2;
        this.featureMask = i;
        this.hairColor = i2;
        this.expression = i3;
        this.colorSpace = i5;
        this.sourceType = i6;
        this.deviceType = i7;
        int length = featurePointArr == null ? 0 : featurePointArr.length;
        this.featurePoints = new FeaturePoint[length];
        if (length > 0) {
            System.arraycopy(featurePointArr, 0, this.featurePoints, 0, length);
        }
        this.poseAngle = new int[3];
        System.arraycopy(iArr, 0, this.poseAngle, 0, 3);
        this.poseAngleUncertainty = new int[3];
        System.arraycopy(iArr2, 0, this.poseAngleUncertainty, 0, 3);
        this.imageDataType = i12;
        this.recordLength = (long) ((length * 8) + 20 + 12 + i11);
        this.faceImageType = i4;
        this.colorSpace = i5;
        this.sourceType = i6;
        this.deviceType = i7;
        this.quality = i8;
    }

    public FaceImageInfo(InputStream inputStream) throws IOException {
        super(0);
        readObject(inputStream);
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream;
        if (inputStream instanceof DataInputStream) {
            dataInputStream = (DataInputStream) inputStream;
        } else {
            dataInputStream = new DataInputStream(inputStream);
        }
        this.recordLength = ((long) dataInputStream.readInt()) & 4294967295L;
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        this.gender = Gender.getInstance(dataInputStream.readUnsignedByte());
        this.eyeColor = EyeColor.toEyeColor(dataInputStream.readUnsignedByte());
        this.hairColor = dataInputStream.readUnsignedByte();
        this.featureMask = dataInputStream.readUnsignedByte();
        this.featureMask = (this.featureMask << 16) | dataInputStream.readUnsignedShort();
        this.expression = dataInputStream.readShort();
        this.poseAngle = new int[3];
        this.poseAngle[0] = dataInputStream.readUnsignedByte();
        this.poseAngle[1] = dataInputStream.readUnsignedByte();
        this.poseAngle[2] = dataInputStream.readUnsignedByte();
        this.poseAngleUncertainty = new int[3];
        this.poseAngleUncertainty[0] = dataInputStream.readUnsignedByte();
        this.poseAngleUncertainty[1] = dataInputStream.readUnsignedByte();
        this.poseAngleUncertainty[2] = dataInputStream.readUnsignedByte();
        this.featurePoints = new FeaturePoint[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            byte readByte = dataInputStream.readByte();
            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
            int readUnsignedShort3 = dataInputStream.readUnsignedShort();
            for (long j = 0; j < 2; j += dataInputStream.skip(2)) {
            }
            this.featurePoints[i] = new FeaturePoint(readUnsignedByte, readByte, readUnsignedShort2, readUnsignedShort3);
        }
        this.faceImageType = dataInputStream.readUnsignedByte();
        this.imageDataType = dataInputStream.readUnsignedByte();
        setWidth(dataInputStream.readUnsignedShort());
        setHeight(dataInputStream.readUnsignedShort());
        this.colorSpace = dataInputStream.readUnsignedByte();
        this.sourceType = dataInputStream.readUnsignedByte();
        this.deviceType = dataInputStream.readUnsignedShort();
        this.quality = dataInputStream.readUnsignedShort();
        if (getWidth() <= 0) {
            setWidth(800);
        }
        if (getHeight() <= 0) {
            setHeight(600);
        }
        setMimeType(toMimeType(this.imageDataType));
        readImage(inputStream, ((this.recordLength - 20) - ((long) (readUnsignedShort * 8))) - 12);
    }

    public void writeObject(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeFacialRecordData(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        long length = (long) (byteArray.length + 4);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt((int) length);
        dataOutputStream.write(byteArray);
        dataOutputStream.flush();
    }

    public long getRecordLength() {
        return this.recordLength;
    }

    public FeaturePoint[] getFeaturePoints() {
        return this.featurePoints;
    }

    public int getExpression() {
        return this.expression;
    }

    public EyeColor getEyeColor() {
        return this.eyeColor;
    }

    public Gender getGender() {
        return this.gender;
    }

    public int getHairColor() {
        return this.hairColor;
    }

    public int getFaceImageType() {
        return this.faceImageType;
    }

    public int getFeatureMask() {
        return this.featureMask;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public int getImageDataType() {
        return this.imageDataType;
    }

    public int getColorSpace() {
        return this.colorSpace;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public int[] getPoseAngle() {
        int[] iArr = new int[3];
        System.arraycopy(this.poseAngle, 0, iArr, 0, iArr.length);
        return iArr;
    }

    public int[] getPoseAngleUncertainty() {
        int[] iArr = new int[3];
        System.arraycopy(this.poseAngleUncertainty, 0, iArr, 0, iArr.length);
        return iArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Image size: ");
        stringBuffer.append(getWidth() + " x " + getHeight());
        stringBuffer.append("\n");
        stringBuffer.append("Gender: ");
        stringBuffer.append(this.gender);
        stringBuffer.append("\n");
        stringBuffer.append("Eye color: ");
        stringBuffer.append(this.eyeColor);
        stringBuffer.append("\n");
        stringBuffer.append("Hair color: ");
        stringBuffer.append(hairColorToString());
        stringBuffer.append("\n");
        stringBuffer.append("Feature mask: ");
        stringBuffer.append(featureMaskToString());
        stringBuffer.append("\n");
        stringBuffer.append("Expression: ");
        stringBuffer.append(expressionToString());
        stringBuffer.append("\n");
        stringBuffer.append("Pose angle: ");
        stringBuffer.append(poseAngleToString());
        stringBuffer.append("\n");
        stringBuffer.append("Face image type: ");
        stringBuffer.append(faceImageTypeToString());
        stringBuffer.append("\n");
        stringBuffer.append("Source type: ");
        stringBuffer.append(sourceTypeToString());
        stringBuffer.append("\n");
        stringBuffer.append("Feature points: ");
        stringBuffer.append("\n");
        if (this.featurePoints == null || this.featurePoints.length == 0) {
            stringBuffer.append("   (none)\n");
        } else {
            for (FeaturePoint featurePoint : this.featurePoints) {
                stringBuffer.append("   ");
                stringBuffer.append(featurePoint.toString());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    private void writeFacialRecordData(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeShort(this.featurePoints.length);
        dataOutputStream.writeByte(this.gender.toInt());
        dataOutputStream.writeByte(this.eyeColor.toInt());
        dataOutputStream.writeByte(this.hairColor);
        dataOutputStream.writeByte((byte) ((int) ((((long) this.featureMask) & 16711680) >> 16)));
        dataOutputStream.writeByte((byte) ((int) ((((long) this.featureMask) & 65280) >> 8)));
        dataOutputStream.writeByte((byte) ((int) (((long) this.featureMask) & 255)));
        dataOutputStream.writeShort(this.expression);
        for (int i = 0; i < 3; i++) {
            dataOutputStream.writeByte(this.poseAngle[i]);
        }
        for (int i2 = 0; i2 < 3; i2++) {
            dataOutputStream.writeByte(this.poseAngleUncertainty[i2]);
        }
        for (FeaturePoint featurePoint : this.featurePoints) {
            dataOutputStream.writeByte(featurePoint.getType());
            dataOutputStream.writeByte((featurePoint.getMajorCode() << 4) | featurePoint.getMinorCode());
            dataOutputStream.writeShort(featurePoint.getX());
            dataOutputStream.writeShort(featurePoint.getY());
            dataOutputStream.writeShort(0);
        }
        dataOutputStream.writeByte(this.faceImageType);
        dataOutputStream.writeByte(this.imageDataType);
        dataOutputStream.writeShort(getWidth());
        dataOutputStream.writeShort(getHeight());
        dataOutputStream.writeByte(this.colorSpace);
        dataOutputStream.writeByte(this.sourceType);
        dataOutputStream.writeShort(this.deviceType);
        dataOutputStream.writeShort(this.quality);
        writeImage(dataOutputStream);
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    private String hairColorToString() {
        switch (this.hairColor) {
            case 0:
                return "unspecified";
            case 1:
                return "bald";
            case 2:
                return "black";
            case 3:
                return "blonde";
            case 4:
                return "brown";
            case 5:
                return "gray";
            case 6:
                return "white";
            case 7:
                return "red";
            case 8:
                return "green";
            case 9:
                return "blue";
            default:
                return "unknown";
        }
    }

    private String featureMaskToString() {
        if ((this.featureMask & 1) == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        if ((this.featureMask & 2) != 0) {
            arrayList.add("glasses");
        }
        if ((this.featureMask & 4) != 0) {
            arrayList.add("moustache");
        }
        if ((this.featureMask & 8) != 0) {
            arrayList.add("beard");
        }
        if ((this.featureMask & 16) != 0) {
            arrayList.add("teeth visible");
        }
        if ((this.featureMask & 32) != 0) {
            arrayList.add("blink");
        }
        if ((this.featureMask & 64) != 0) {
            arrayList.add("mouth open");
        }
        if ((this.featureMask & 128) != 0) {
            arrayList.add("left eye patch");
        }
        if ((this.featureMask & 256) != 0) {
            arrayList.add("right eye patch");
        }
        if ((this.featureMask & 512) != 0) {
            arrayList.add("dark glasses");
        }
        if ((this.featureMask & 1024) != 0) {
            arrayList.add("distorting medical condition (which could impact feature point detection)");
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(((String) it.next()).toString());
            if (it.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.toString();
    }

    private String expressionToString() {
        switch (this.expression) {
            case 0:
                return "unspecified";
            case 1:
                return "neutral (non-smiling) with both eyes open and mouth closed";
            case 2:
                return "a smile where the inside of the mouth and/or teeth is not exposed (closed jaw)";
            case 3:
                return "a smile where the inside of the mouth and/or teeth is exposed";
            case 4:
                return "raised eyebrows";
            case 5:
                return "eyes looking away from the camera";
            case 6:
                return "squinting";
            case 7:
                return "frowning";
            default:
                return "unknown";
        }
    }

    private String poseAngleToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        stringBuffer.append("y: ");
        stringBuffer.append(this.poseAngle[0]);
        if (this.poseAngleUncertainty[0] != 0) {
            stringBuffer.append(" (");
            stringBuffer.append(this.poseAngleUncertainty[0]);
            stringBuffer.append(")");
        }
        stringBuffer.append(", ");
        stringBuffer.append("p:");
        stringBuffer.append(this.poseAngle[1]);
        if (this.poseAngleUncertainty[1] != 0) {
            stringBuffer.append(" (");
            stringBuffer.append(this.poseAngleUncertainty[1]);
            stringBuffer.append(")");
        }
        stringBuffer.append(", ");
        stringBuffer.append("r: ");
        stringBuffer.append(this.poseAngle[2]);
        if (this.poseAngleUncertainty[2] != 0) {
            stringBuffer.append(" (");
            stringBuffer.append(this.poseAngleUncertainty[2]);
            stringBuffer.append(")");
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    private String faceImageTypeToString() {
        switch (this.faceImageType) {
            case 0:
                return "basic";
            case 1:
                return "full frontal";
            case 2:
                return "token frontal";
            default:
                return "unknown";
        }
    }

    private String sourceTypeToString() {
        switch (this.sourceType) {
            case 0:
                return "unspecified";
            case 1:
                return "static photograph from an unknown source";
            case 2:
                return "static photograph from a digital still-image camera";
            case 3:
                return "static photograph from a scanner";
            case 4:
                return "single video frame from an unknown source";
            case 5:
                return "single video frame from an analogue camera";
            case 6:
                return "single video frame from a digital camera";
            default:
                return "unknown";
        }
    }

    private static String toMimeType(int i) {
        switch (i) {
            case 0:
                return "image/jpeg";
            case 1:
                return ImageInfo.JPEG2000_MIME_TYPE;
            default:
                return null;
        }
    }

    private static String toMimeType(ImageDataType imageDataType2) {
        switch (imageDataType2) {
            case TYPE_JPEG:
                return "image/jpeg";
            case TYPE_JPEG2000:
                return "image/jpeg2000";
            default:
                return null;
        }
    }

    private static int fromMimeType(String str) {
        if ("image/jpeg".equals(str)) {
            return 0;
        }
        if ("image/jpeg2000".equals(str) || ImageInfo.JPEG2000_MIME_TYPE.equals(str)) {
            return 1;
        }
        throw new IllegalArgumentException("Did not recognize mimeType");
    }
}
