package android.support.media;

import android.content.res.AssetManager.AssetInputStream;
import android.util.Log;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.jumio.analytics.MobileEvents;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.regex.Pattern;
import net.p318sf.scuba.smartcards.ISO7816;

public class ExifInterface {
    /* access modifiers changed from: private */
    public static final Charset ASCII = Charset.forName("US-ASCII");
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    private static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    /* access modifiers changed from: private */
    public static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final ExifTag[] EXIF_POINTER_TAGS = {new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
    private static final ExifTag[][] EXIF_TAGS = {IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
    private static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static final ExifTag[] IFD_EXIF_TAGS = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("ISOSpeedRatings", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
    /* access modifiers changed from: private */
    public static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    /* access modifiers changed from: private */
    public static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    private static final ExifTag[] IFD_GPS_TAGS = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = {new ExifTag("InteroperabilityIndex", 1, 2)};
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", (int) MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", (int) HelpCenterArticle.SET_RESERVATION_REQUIREMENTS, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", (int) TiffUtil.TIFF_TAG_ORIENTATION, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", HelpCenterArticle.HOST_GUARANTEE, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", (int) MobileEvents.EVENTTYPE_USERACTION, 3), new ExifTag("Software", (int) MobileEvents.EVENTTYPE_EXCEPTION, 2), new ExifTag("DateTime", (int) MobileEvents.EVENTTYPE_SDKPARAMETERS, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", (int) HelpCenterArticle.GUEST_TAXES, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
    private static final ExifTag[] IFD_TIFF_TAGS = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", (int) MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", (int) HelpCenterArticle.SET_RESERVATION_REQUIREMENTS, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", (int) TiffUtil.TIFF_TAG_ORIENTATION, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", HelpCenterArticle.HOST_GUARANTEE, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", (int) MobileEvents.EVENTTYPE_USERACTION, 3), new ExifTag("Software", (int) MobileEvents.EVENTTYPE_EXCEPTION, 2), new ExifTag("DateTime", (int) MobileEvents.EVENTTYPE_SDKPARAMETERS, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", (int) HelpCenterArticle.GUEST_TAXES, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7)};
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", 514, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", 513, 4);
    private static final byte[] JPEG_SIGNATURE = {-1, ISO7816.INS_LOAD_KEY_FILE, -1};
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = {new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = {new ExifTag("AspectFrame", 4371, 3)};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)};
    private static final ExifTag[] PEF_TAGS = {new ExifTag("ColorSpace", 55, 3)};
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
    private static final HashMap sExifPointerTagMap = new HashMap();
    private static final HashMap[] sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
    private static final HashMap[] sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
    private static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList(new String[]{"FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"}));
    private final AssetInputStream mAssetInputStream;
    private final HashMap[] mAttributes = new HashMap[EXIF_TAGS.length];
    private ByteOrder mExifByteOrder = ByteOrder.BIG_ENDIAN;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        /* access modifiers changed from: private */
        public final int mLength;
        /* access modifiers changed from: private */
        public int mPosition;

        public ByteOrderedDataInputStream(InputStream in) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mDataInputStream = new DataInputStream(in);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
        }

        public ByteOrderedDataInputStream(byte[] bytes) throws IOException {
            this((InputStream) new ByteArrayInputStream(bytes));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long byteCount) throws IOException {
            if (((long) this.mPosition) > byteCount) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                byteCount -= (long) this.mPosition;
            }
            if (skipBytes((int) byteCount) != ((int) byteCount)) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int bytesRead = this.mDataInputStream.read(b, off, len);
            this.mPosition += bytesRead;
            return bytesRead;
        }

        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public String readLine() throws IOException {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public void readFully(byte[] buffer, int offset, int length) throws IOException {
            this.mPosition += length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(buffer, offset, length) != length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public void readFully(byte[] buffer) throws IOException {
            this.mPosition += buffer.length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(buffer, 0, buffer.length) != buffer.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public byte readByte() throws IOException {
            this.mPosition++;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch = this.mDataInputStream.read();
            if (ch >= 0) {
                return (byte) ch;
            }
            throw new EOFException();
        }

        public short readShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            if ((ch1 | ch2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (short) ((ch2 << 8) + ch1);
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (short) ((ch1 << 8) + ch2);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int readInt() throws IOException {
            this.mPosition += 4;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            int ch3 = this.mDataInputStream.read();
            int ch4 = this.mDataInputStream.read();
            if ((ch1 | ch2 | ch3 | ch4) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + ch1;
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int skipBytes(int byteCount) throws IOException {
            int totalSkip = Math.min(byteCount, this.mLength - this.mPosition);
            int skipped = 0;
            while (skipped < totalSkip) {
                skipped += this.mDataInputStream.skipBytes(totalSkip - skipped);
            }
            this.mPosition += skipped;
            return skipped;
        }

        public int readUnsignedShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            if ((ch1 | ch2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (ch2 << 8) + ch1;
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (ch1 << 8) + ch2;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public long readUnsignedInt() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        public long readLong() throws IOException {
            this.mPosition += 8;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            int ch3 = this.mDataInputStream.read();
            int ch4 = this.mDataInputStream.read();
            int ch5 = this.mDataInputStream.read();
            int ch6 = this.mDataInputStream.read();
            int ch7 = this.mDataInputStream.read();
            int ch8 = this.mDataInputStream.read();
            if ((ch1 | ch2 | ch3 | ch4 | ch5 | ch6 | ch7 | ch8) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (((long) ch8) << 56) + (((long) ch7) << 48) + (((long) ch6) << 40) + (((long) ch5) << 32) + (((long) ch4) << 24) + (((long) ch3) << 16) + (((long) ch2) << 8) + ((long) ch1);
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (((long) ch1) << 56) + (((long) ch2) << 48) + (((long) ch3) << 40) + (((long) ch4) << 32) + (((long) ch5) << 24) + (((long) ch6) << 16) + (((long) ch7) << 8) + ((long) ch8);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }
    }

    private static class ExifAttribute {
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int format2, int numberOfComponents2, byte[] bytes2) {
            this.format = format2;
            this.numberOfComponents = numberOfComponents2;
            this.bytes = bytes2;
        }

        public static ExifAttribute createUShort(int[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * values.length)]);
            buffer.order(byteOrder);
            for (int value : values) {
                buffer.putShort((short) value);
            }
            return new ExifAttribute(3, values.length, buffer.array());
        }

        public static ExifAttribute createUShort(int value, ByteOrder byteOrder) {
            return createUShort(new int[]{value}, byteOrder);
        }

        public static ExifAttribute createULong(long[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * values.length)]);
            buffer.order(byteOrder);
            for (long value : values) {
                buffer.putInt((int) value);
            }
            return new ExifAttribute(4, values.length, buffer.array());
        }

        public static ExifAttribute createULong(long value, ByteOrder byteOrder) {
            return createULong(new long[]{value}, byteOrder);
        }

        public static ExifAttribute createString(String value) {
            byte[] ascii = (value + 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, ascii.length, ascii);
        }

        public static ExifAttribute createURational(Rational[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * values.length)]);
            buffer.order(byteOrder);
            for (Rational value : values) {
                buffer.putInt((int) value.numerator);
                buffer.putInt((int) value.denominator);
            }
            return new ExifAttribute(5, values.length, buffer.array());
        }

        public static ExifAttribute createURational(Rational value, ByteOrder byteOrder) {
            return createURational(new Rational[]{value}, byteOrder);
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        /* JADX WARNING: type inference failed for: r0v5, types: [double[]] */
        /* JADX WARNING: type inference failed for: r0v8, types: [double[]] */
        /* JADX WARNING: type inference failed for: r0v15, types: [int[]] */
        /* JADX WARNING: type inference failed for: r0v18, types: [int[]] */
        /* JADX WARNING: type inference failed for: r0v24, types: [long[]] */
        /* JADX WARNING: type inference failed for: r0v27, types: [int[]] */
        /* JADX WARNING: type inference failed for: r16v13 */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:187:0x0294 A[SYNTHETIC, Splitter:B:187:0x0294] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00cf A[SYNTHETIC, Splitter:B:55:0x00cf] */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x00f8 A[SYNTHETIC, Splitter:B:73:0x00f8] */
        /* JADX WARNING: Unknown variable types count: 7 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(java.nio.ByteOrder r21) {
            /*
                r20 = this;
                r12 = 0
                android.support.media.ExifInterface$ByteOrderedDataInputStream r13 = new android.support.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ IOException -> 0x02a8 }
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x02a8 }
                r13.<init>(r3)     // Catch:{ IOException -> 0x02a8 }
                r0 = r21
                r13.setByteOrder(r0)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r0 = r20
                int r3 = r0.format     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                switch(r3) {
                    case 1: goto L_0x001f;
                    case 2: goto L_0x008b;
                    case 3: goto L_0x0114;
                    case 4: goto L_0x013f;
                    case 5: goto L_0x016a;
                    case 6: goto L_0x001f;
                    case 7: goto L_0x008b;
                    case 8: goto L_0x019f;
                    case 9: goto L_0x01ca;
                    case 10: goto L_0x01f5;
                    case 11: goto L_0x022c;
                    case 12: goto L_0x025a;
                    default: goto L_0x0016;
                }
            L_0x0016:
                r16 = 0
                if (r13 == 0) goto L_0x001d
                r13.close()     // Catch:{ IOException -> 0x0285 }
            L_0x001d:
                r12 = r13
            L_0x001e:
                return r16
            L_0x001f:
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r3 = r3.length     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 1
                if (r3 != r8) goto L_0x006a
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 0
                byte r3 = r3[r8]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r3 < 0) goto L_0x006a
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 0
                byte r3 = r3[r8]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 1
                if (r3 > r8) goto L_0x006a
                java.lang.String r16 = new java.lang.String     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r3 = 1
                char[] r3 = new char[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 0
                r0 = r20
                byte[] r0 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r17 = r0
                r18 = 0
                byte r17 = r17[r18]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r17 = r17 + 48
                r0 = r17
                char r0 = (char) r0     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r17 = r0
                r3[r8] = r17     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r0 = r16
                r0.<init>(r3)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r13 == 0) goto L_0x005d
                r13.close()     // Catch:{ IOException -> 0x005f }
            L_0x005d:
                r12 = r13
                goto L_0x001e
            L_0x005f:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x005d
            L_0x006a:
                java.lang.String r16 = new java.lang.String     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                java.nio.charset.Charset r8 = android.support.media.ExifInterface.ASCII     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r0 = r16
                r0.<init>(r3, r8)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r13 == 0) goto L_0x007e
                r13.close()     // Catch:{ IOException -> 0x0080 }
            L_0x007e:
                r12 = r13
                goto L_0x001e
            L_0x0080:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x007e
            L_0x008b:
                r11 = 0
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                byte[] r8 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r8 = r8.length     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r3 < r8) goto L_0x00b6
                r14 = 1
                r10 = 0
            L_0x0099:
                byte[] r3 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r3 = r3.length     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x00af
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                byte r3 = r3[r10]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                byte[] r8 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                byte r8 = r8[r10]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r3 == r8) goto L_0x00d5
                r14 = 0
            L_0x00af:
                if (r14 == 0) goto L_0x00b6
                byte[] r3 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r11 = r3.length     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
            L_0x00b6:
                java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r15.<init>()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
            L_0x00bb:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r11 >= r3) goto L_0x00c9
                r0 = r20
                byte[] r3 = r0.bytes     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                byte r2 = r3[r11]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r2 != 0) goto L_0x00d8
            L_0x00c9:
                java.lang.String r16 = r15.toString()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r13 == 0) goto L_0x00d2
                r13.close()     // Catch:{ IOException -> 0x0109 }
            L_0x00d2:
                r12 = r13
                goto L_0x001e
            L_0x00d5:
                int r10 = r10 + 1
                goto L_0x0099
            L_0x00d8:
                r3 = 32
                if (r2 < r3) goto L_0x00e3
                char r3 = (char) r2
                r15.append(r3)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
            L_0x00e0:
                int r11 = r11 + 1
                goto L_0x00bb
            L_0x00e3:
                r3 = 63
                r15.append(r3)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                goto L_0x00e0
            L_0x00e9:
                r9 = move-exception
                r12 = r13
            L_0x00eb:
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred during reading a value"
                android.util.Log.w(r3, r8, r9)     // Catch:{ all -> 0x0291 }
                r16 = 0
                if (r12 == 0) goto L_0x001e
                r12.close()     // Catch:{ IOException -> 0x00fd }
                goto L_0x001e
            L_0x00fd:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x001e
            L_0x0109:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x00d2
            L_0x0114:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int[] r0 = new int[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x011d:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x012c
                int r3 = r13.readUnsignedShort()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x011d
            L_0x012c:
                if (r13 == 0) goto L_0x0131
                r13.close()     // Catch:{ IOException -> 0x0134 }
            L_0x0131:
                r12 = r13
                goto L_0x001e
            L_0x0134:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x0131
            L_0x013f:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                long[] r0 = new long[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x0148:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x0157
                long r18 = r13.readUnsignedInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r18     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x0148
            L_0x0157:
                if (r13 == 0) goto L_0x015c
                r13.close()     // Catch:{ IOException -> 0x015f }
            L_0x015c:
                r12 = r13
                goto L_0x001e
            L_0x015f:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x015c
            L_0x016a:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                android.support.media.ExifInterface$Rational[] r0 = new android.support.media.ExifInterface.Rational[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x0173:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x018c
                long r4 = r13.readUnsignedInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                long r6 = r13.readUnsignedInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                android.support.media.ExifInterface$Rational r3 = new android.support.media.ExifInterface$Rational     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 0
                r3.<init>(r4, r6)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x0173
            L_0x018c:
                if (r13 == 0) goto L_0x0191
                r13.close()     // Catch:{ IOException -> 0x0194 }
            L_0x0191:
                r12 = r13
                goto L_0x001e
            L_0x0194:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x0191
            L_0x019f:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int[] r0 = new int[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x01a8:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x01b7
                short r3 = r13.readShort()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x01a8
            L_0x01b7:
                if (r13 == 0) goto L_0x01bc
                r13.close()     // Catch:{ IOException -> 0x01bf }
            L_0x01bc:
                r12 = r13
                goto L_0x001e
            L_0x01bf:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x01bc
            L_0x01ca:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int[] r0 = new int[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x01d3:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x01e2
                int r3 = r13.readInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x01d3
            L_0x01e2:
                if (r13 == 0) goto L_0x01e7
                r13.close()     // Catch:{ IOException -> 0x01ea }
            L_0x01e7:
                r12 = r13
                goto L_0x001e
            L_0x01ea:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x01e7
            L_0x01f5:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                android.support.media.ExifInterface$Rational[] r0 = new android.support.media.ExifInterface.Rational[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x01fe:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x0219
                int r3 = r13.readInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                long r4 = (long) r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r3 = r13.readInt()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                long r6 = (long) r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                android.support.media.ExifInterface$Rational r3 = new android.support.media.ExifInterface$Rational     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r8 = 0
                r3.<init>(r4, r6)     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x01fe
            L_0x0219:
                if (r13 == 0) goto L_0x021e
                r13.close()     // Catch:{ IOException -> 0x0221 }
            L_0x021e:
                r12 = r13
                goto L_0x001e
            L_0x0221:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x021e
            L_0x022c:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                double[] r0 = new double[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x0235:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x0247
                float r3 = r13.readFloat()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                double r0 = (double) r3     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r18 = r0
                r16[r10] = r18     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x0235
            L_0x0247:
                if (r13 == 0) goto L_0x024c
                r13.close()     // Catch:{ IOException -> 0x024f }
            L_0x024c:
                r12 = r13
                goto L_0x001e
            L_0x024f:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x024c
            L_0x025a:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                double[] r0 = new double[r3]     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16 = r0
                r10 = 0
            L_0x0263:
                r0 = r20
                int r3 = r0.numberOfComponents     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                if (r10 >= r3) goto L_0x0272
                double r18 = r13.readDouble()     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                r16[r10] = r18     // Catch:{ IOException -> 0x00e9, all -> 0x02a5 }
                int r10 = r10 + 1
                goto L_0x0263
            L_0x0272:
                if (r13 == 0) goto L_0x0277
                r13.close()     // Catch:{ IOException -> 0x027a }
            L_0x0277:
                r12 = r13
                goto L_0x001e
            L_0x027a:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x0277
            L_0x0285:
                r9 = move-exception
                java.lang.String r3 = "ExifInterface"
                java.lang.String r8 = "IOException occurred while closing InputStream"
                android.util.Log.e(r3, r8, r9)
                goto L_0x001d
            L_0x0291:
                r3 = move-exception
            L_0x0292:
                if (r12 == 0) goto L_0x0297
                r12.close()     // Catch:{ IOException -> 0x0298 }
            L_0x0297:
                throw r3
            L_0x0298:
                r9 = move-exception
                java.lang.String r8 = "ExifInterface"
                java.lang.String r17 = "IOException occurred while closing InputStream"
                r0 = r17
                android.util.Log.e(r8, r0, r9)
                goto L_0x0297
            L_0x02a5:
                r3 = move-exception
                r12 = r13
                goto L_0x0292
            L_0x02a8:
                r9 = move-exception
                goto L_0x00eb
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] array = (long[]) value;
                    if (array.length == 1) {
                        return (double) array[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] array2 = (int[]) value;
                    if (array2.length == 1) {
                        return (double) array2[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof double[]) {
                    double[] array3 = (double[]) value;
                    if (array3.length == 1) {
                        return array3[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof Rational[]) {
                    Rational[] array4 = (Rational[]) value;
                    if (array4.length == 1) {
                        return array4[0].calculate();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] array = (long[]) value;
                    if (array.length == 1) {
                        return (int) array[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] array2 = (int[]) value;
                    if (array2.length == 1) {
                        return array2[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
        }

        public String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (value instanceof long[]) {
                long[] array = (long[]) value;
                for (int i = 0; i < array.length; i++) {
                    stringBuilder.append(array[i]);
                    if (i + 1 != array.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            } else if (value instanceof int[]) {
                int[] array2 = (int[]) value;
                for (int i2 = 0; i2 < array2.length; i2++) {
                    stringBuilder.append(array2[i2]);
                    if (i2 + 1 != array2.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            } else if (value instanceof double[]) {
                double[] array3 = (double[]) value;
                for (int i3 = 0; i3 < array3.length; i3++) {
                    stringBuilder.append(array3[i3]);
                    if (i3 + 1 != array3.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            } else if (!(value instanceof Rational[])) {
                return null;
            } else {
                Rational[] array4 = (Rational[]) value;
                for (int i4 = 0; i4 < array4.length; i4++) {
                    stringBuilder.append(array4[i4].numerator);
                    stringBuilder.append('/');
                    stringBuilder.append(array4[i4].denominator);
                    if (i4 + 1 != array4.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            }
        }
    }

    private static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        private ExifTag(String name2, int number2, int format) {
            this.name = name2;
            this.number = number2;
            this.primaryFormat = format;
            this.secondaryFormat = -1;
        }

        private ExifTag(String name2, int number2, int primaryFormat2, int secondaryFormat2) {
            this.name = name2;
            this.number = number2;
            this.primaryFormat = primaryFormat2;
            this.secondaryFormat = secondaryFormat2;
        }
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(long numerator2, long denominator2) {
            if (denominator2 == 0) {
                this.numerator = 0;
                this.denominator = 1;
                return;
            }
            this.numerator = numerator2;
            this.denominator = denominator2;
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        public double calculate() {
            return ((double) this.numerator) / ((double) this.denominator);
        }
    }

    static {
        ExifTag[] exifTagArr;
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int ifdType = 0; ifdType < EXIF_TAGS.length; ifdType++) {
            sExifTagMapsForReading[ifdType] = new HashMap();
            sExifTagMapsForWriting[ifdType] = new HashMap();
            for (ExifTag tag : EXIF_TAGS[ifdType]) {
                sExifTagMapsForReading[ifdType].put(Integer.valueOf(tag.number), tag);
                sExifTagMapsForWriting[ifdType].put(tag.name, tag);
            }
        }
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), Integer.valueOf(5));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), Integer.valueOf(1));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), Integer.valueOf(2));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), Integer.valueOf(3));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), Integer.valueOf(7));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), Integer.valueOf(8));
    }

    public ExifInterface(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        this.mFilename = null;
        if (inputStream instanceof AssetInputStream) {
            this.mAssetInputStream = (AssetInputStream) inputStream;
        } else {
            this.mAssetInputStream = null;
        }
        loadAttributes(inputStream);
    }

    private ExifAttribute getExifAttribute(String tag) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            Object value = this.mAttributes[i].get(tag);
            if (value != null) {
                return (ExifAttribute) value;
            }
        }
        return null;
    }

    public String getAttribute(String tag) {
        ExifAttribute attribute = getExifAttribute(tag);
        if (attribute == null) {
            return null;
        }
        if (!sTagSetForCompatibility.contains(tag)) {
            return attribute.getStringValue(this.mExifByteOrder);
        }
        if (!tag.equals("GPSTimeStamp")) {
            try {
                return Double.toString(attribute.getDoubleValue(this.mExifByteOrder));
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (attribute.format == 5 || attribute.format == 10) {
            Rational[] array = (Rational[]) attribute.getValue(this.mExifByteOrder);
            if (array == null || array.length != 3) {
                Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(array));
                return null;
            }
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) array[0].numerator) / ((float) array[0].denominator))), Integer.valueOf((int) (((float) array[1].numerator) / ((float) array[1].denominator))), Integer.valueOf((int) (((float) array[2].numerator) / ((float) array[2].denominator)))});
        } else {
            Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + attribute.format);
            return null;
        }
    }

    public int getAttributeInt(String tag, int defaultValue) {
        ExifAttribute exifAttribute = getExifAttribute(tag);
        if (exifAttribute == null) {
            return defaultValue;
        }
        try {
            return exifAttribute.getIntValue(this.mExifByteOrder);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private void loadAttributes(InputStream in) throws IOException {
        int i = 0;
        while (i < EXIF_TAGS.length) {
            try {
                this.mAttributes[i] = new HashMap();
                i++;
            } catch (IOException e) {
                try {
                    this.mIsSupportedFile = false;
                    addDefaultValuesForCompatibility();
                    return;
                } catch (Throwable th) {
                    th = th;
                    addDefaultValuesForCompatibility();
                    throw th;
                }
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in, 5000);
        try {
            this.mMimeType = getMimeType(bufferedInputStream);
            ByteOrderedDataInputStream inputStream = new ByteOrderedDataInputStream((InputStream) bufferedInputStream);
            switch (this.mMimeType) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case 8:
                case 11:
                    getRawAttributes(inputStream);
                    break;
                case 4:
                    getJpegAttributes(inputStream, 0, 0);
                    break;
                case 7:
                    getOrfAttributes(inputStream);
                    break;
                case 9:
                    getRafAttributes(inputStream);
                    break;
                case 10:
                    getRw2Attributes(inputStream);
                    break;
            }
            setThumbnailData(inputStream);
            this.mIsSupportedFile = true;
            addDefaultValuesForCompatibility();
            BufferedInputStream bufferedInputStream2 = bufferedInputStream;
        } catch (IOException e2) {
            BufferedInputStream bufferedInputStream3 = bufferedInputStream;
        } catch (Throwable th2) {
            th = th2;
            BufferedInputStream bufferedInputStream4 = bufferedInputStream;
            addDefaultValuesForCompatibility();
            throw th;
        }
    }

    private int getMimeType(BufferedInputStream in) throws IOException {
        in.mark(5000);
        byte[] signatureCheckBytes = new byte[5000];
        if (in.read(signatureCheckBytes) != 5000) {
            throw new EOFException();
        }
        in.reset();
        if (isJpegFormat(signatureCheckBytes)) {
            return 4;
        }
        if (isRafFormat(signatureCheckBytes)) {
            return 9;
        }
        if (isOrfFormat(signatureCheckBytes)) {
            return 7;
        }
        if (isRw2Format(signatureCheckBytes)) {
            return 10;
        }
        return 0;
    }

    private static boolean isJpegFormat(byte[] signatureCheckBytes) throws IOException {
        for (int i = 0; i < JPEG_SIGNATURE.length; i++) {
            if (signatureCheckBytes[i] != JPEG_SIGNATURE[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isRafFormat(byte[] signatureCheckBytes) throws IOException {
        byte[] rafSignatureBytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i = 0; i < rafSignatureBytes.length; i++) {
            if (signatureCheckBytes[i] != rafSignatureBytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrfFormat(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
        this.mExifByteOrder = readByteOrder(signatureInputStream);
        signatureInputStream.setByteOrder(this.mExifByteOrder);
        short orfSignature = signatureInputStream.readShort();
        signatureInputStream.close();
        return orfSignature == 20306 || orfSignature == 21330;
    }

    private boolean isRw2Format(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
        this.mExifByteOrder = readByteOrder(signatureInputStream);
        signatureInputStream.setByteOrder(this.mExifByteOrder);
        short signatureByte = signatureInputStream.readShort();
        signatureInputStream.close();
        return signatureByte == 85;
    }

    private void getJpegAttributes(ByteOrderedDataInputStream in, int jpegOffset, int imageType) throws IOException {
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        in.seek((long) jpegOffset);
        int bytesRead = jpegOffset;
        byte marker = in.readByte();
        if (marker != -1) {
            throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
        }
        int bytesRead2 = bytesRead + 1;
        if (in.readByte() != -40) {
            throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
        }
        int bytesRead3 = bytesRead2 + 1;
        while (true) {
            byte marker2 = in.readByte();
            if (marker2 != -1) {
                throw new IOException("Invalid marker:" + Integer.toHexString(marker2 & 255));
            }
            int bytesRead4 = bytesRead3 + 1;
            byte marker3 = in.readByte();
            int bytesRead5 = bytesRead4 + 1;
            if (marker3 == -39 || marker3 == -38) {
                in.setByteOrder(this.mExifByteOrder);
            } else {
                int length = in.readUnsignedShort() - 2;
                int bytesRead6 = bytesRead5 + 2;
                if (length < 0) {
                    throw new IOException("Invalid length");
                }
                switch (marker3) {
                    case -64:
                    case -63:
                    case -62:
                    case -61:
                    case -59:
                    case -58:
                    case -57:
                    case -55:
                    case -54:
                    case -53:
                    case -51:
                    case -50:
                    case -49:
                        if (in.skipBytes(1) == 1) {
                            this.mAttributes[imageType].put("ImageLength", ExifAttribute.createULong((long) in.readUnsignedShort(), this.mExifByteOrder));
                            this.mAttributes[imageType].put("ImageWidth", ExifAttribute.createULong((long) in.readUnsignedShort(), this.mExifByteOrder));
                            length -= 5;
                            break;
                        } else {
                            throw new IOException("Invalid SOFx");
                        }
                    case -31:
                        if (length >= 6) {
                            byte[] identifier = new byte[6];
                            if (in.read(identifier) == 6) {
                                bytesRead6 += 6;
                                length -= 6;
                                if (Arrays.equals(identifier, IDENTIFIER_EXIF_APP1)) {
                                    if (length > 0) {
                                        this.mExifOffset = bytesRead6;
                                        byte[] bytes = new byte[length];
                                        if (in.read(bytes) == length) {
                                            bytesRead6 += length;
                                            length = 0;
                                            readExifSegment(bytes, imageType);
                                            break;
                                        } else {
                                            throw new IOException("Invalid exif");
                                        }
                                    } else {
                                        throw new IOException("Invalid exif");
                                    }
                                }
                            } else {
                                throw new IOException("Invalid exif");
                            }
                        }
                        break;
                    case -2:
                        byte[] bytes2 = new byte[length];
                        if (in.read(bytes2) == length) {
                            length = 0;
                            if (getAttribute("UserComment") == null) {
                                this.mAttributes[1].put("UserComment", ExifAttribute.createString(new String(bytes2, ASCII)));
                                break;
                            }
                        } else {
                            throw new IOException("Invalid exif");
                        }
                        break;
                }
                if (length < 0) {
                    throw new IOException("Invalid length");
                } else if (in.skipBytes(length) != length) {
                    throw new IOException("Invalid JPEG segment");
                } else {
                    bytesRead3 = bytesRead6 + length;
                }
            }
        }
        in.setByteOrder(this.mExifByteOrder);
    }

    private void getRawAttributes(ByteOrderedDataInputStream in) throws IOException {
        parseTiffHeaders(in, in.available());
        readImageFileDirectory(in, 0);
        updateImageSizeValues(in, 0);
        updateImageSizeValues(in, 5);
        updateImageSizeValues(in, 4);
        validateImages(in);
        if (this.mMimeType == 8) {
            ExifAttribute makerNoteAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote");
            if (makerNoteAttribute != null) {
                ByteOrderedDataInputStream makerNoteDataInputStream = new ByteOrderedDataInputStream(makerNoteAttribute.bytes);
                makerNoteDataInputStream.setByteOrder(this.mExifByteOrder);
                makerNoteDataInputStream.seek(6);
                readImageFileDirectory(makerNoteDataInputStream, 9);
                ExifAttribute colorSpaceAttribute = (ExifAttribute) this.mAttributes[9].get("ColorSpace");
                if (colorSpaceAttribute != null) {
                    this.mAttributes[1].put("ColorSpace", colorSpaceAttribute);
                }
            }
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream in) throws IOException {
        in.skipBytes(84);
        byte[] jpegOffsetBytes = new byte[4];
        byte[] cfaHeaderOffsetBytes = new byte[4];
        in.read(jpegOffsetBytes);
        in.skipBytes(4);
        in.read(cfaHeaderOffsetBytes);
        int rafJpegOffset = ByteBuffer.wrap(jpegOffsetBytes).getInt();
        int rafCfaHeaderOffset = ByteBuffer.wrap(cfaHeaderOffsetBytes).getInt();
        getJpegAttributes(in, rafJpegOffset, 5);
        in.seek((long) rafCfaHeaderOffset);
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        int numberOfDirectoryEntry = in.readInt();
        for (int i = 0; i < numberOfDirectoryEntry; i++) {
            int tagNumber = in.readUnsignedShort();
            int numberOfBytes = in.readUnsignedShort();
            if (tagNumber == TAG_RAF_IMAGE_SIZE.number) {
                int imageLength = in.readShort();
                int imageWidth = in.readShort();
                ExifAttribute imageLengthAttribute = ExifAttribute.createUShort(imageLength, this.mExifByteOrder);
                ExifAttribute imageWidthAttribute = ExifAttribute.createUShort(imageWidth, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", imageLengthAttribute);
                this.mAttributes[0].put("ImageWidth", imageWidthAttribute);
                return;
            }
            in.skipBytes(numberOfBytes);
        }
    }

    private void getOrfAttributes(ByteOrderedDataInputStream in) throws IOException {
        getRawAttributes(in);
        ExifAttribute makerNoteAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote");
        if (makerNoteAttribute != null) {
            ByteOrderedDataInputStream makerNoteDataInputStream = new ByteOrderedDataInputStream(makerNoteAttribute.bytes);
            makerNoteDataInputStream.setByteOrder(this.mExifByteOrder);
            byte[] makerNoteHeader1Bytes = new byte[ORF_MAKER_NOTE_HEADER_1.length];
            makerNoteDataInputStream.readFully(makerNoteHeader1Bytes);
            makerNoteDataInputStream.seek(0);
            byte[] makerNoteHeader2Bytes = new byte[ORF_MAKER_NOTE_HEADER_2.length];
            makerNoteDataInputStream.readFully(makerNoteHeader2Bytes);
            if (Arrays.equals(makerNoteHeader1Bytes, ORF_MAKER_NOTE_HEADER_1)) {
                makerNoteDataInputStream.seek(8);
            } else if (Arrays.equals(makerNoteHeader2Bytes, ORF_MAKER_NOTE_HEADER_2)) {
                makerNoteDataInputStream.seek(12);
            }
            readImageFileDirectory(makerNoteDataInputStream, 6);
            ExifAttribute imageLengthAttribute = (ExifAttribute) this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute bitsPerSampleAttribute = (ExifAttribute) this.mAttributes[7].get("PreviewImageLength");
            if (!(imageLengthAttribute == null || bitsPerSampleAttribute == null)) {
                this.mAttributes[5].put("JPEGInterchangeFormat", imageLengthAttribute);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", bitsPerSampleAttribute);
            }
            ExifAttribute aspectFrameAttribute = (ExifAttribute) this.mAttributes[8].get("AspectFrame");
            if (aspectFrameAttribute != null) {
                int[] aspectFrameValues = (int[]) aspectFrameAttribute.getValue(this.mExifByteOrder);
                if (aspectFrameValues == null || aspectFrameValues.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(aspectFrameValues));
                } else if (aspectFrameValues[2] > aspectFrameValues[0] && aspectFrameValues[3] > aspectFrameValues[1]) {
                    int primaryImageWidth = (aspectFrameValues[2] - aspectFrameValues[0]) + 1;
                    int primaryImageLength = (aspectFrameValues[3] - aspectFrameValues[1]) + 1;
                    if (primaryImageWidth < primaryImageLength) {
                        int primaryImageWidth2 = primaryImageWidth + primaryImageLength;
                        primaryImageLength = primaryImageWidth2 - primaryImageLength;
                        primaryImageWidth = primaryImageWidth2 - primaryImageLength;
                    }
                    ExifAttribute primaryImageWidthAttribute = ExifAttribute.createUShort(primaryImageWidth, this.mExifByteOrder);
                    ExifAttribute primaryImageLengthAttribute = ExifAttribute.createUShort(primaryImageLength, this.mExifByteOrder);
                    this.mAttributes[0].put("ImageWidth", primaryImageWidthAttribute);
                    this.mAttributes[0].put("ImageLength", primaryImageLengthAttribute);
                }
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream in) throws IOException {
        getRawAttributes(in);
        if (((ExifAttribute) this.mAttributes[0].get("JpgFromRaw")) != null) {
            getJpegAttributes(in, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute rw2IsoAttribute = (ExifAttribute) this.mAttributes[0].get("ISO");
        ExifAttribute exifIsoAttribute = (ExifAttribute) this.mAttributes[1].get("ISOSpeedRatings");
        if (rw2IsoAttribute != null && exifIsoAttribute == null) {
            this.mAttributes[1].put("ISOSpeedRatings", rw2IsoAttribute);
        }
    }

    private void readExifSegment(byte[] exifBytes, int imageType) throws IOException {
        ByteOrderedDataInputStream dataInputStream = new ByteOrderedDataInputStream(exifBytes);
        parseTiffHeaders(dataInputStream, exifBytes.length);
        readImageFileDirectory(dataInputStream, imageType);
    }

    private void addDefaultValuesForCompatibility() {
        String valueOfDateTimeOriginal = getAttribute("DateTimeOriginal");
        if (valueOfDateTimeOriginal != null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(valueOfDateTimeOriginal));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream dataInputStream) throws IOException {
        short byteOrder = dataInputStream.readShort();
        switch (byteOrder) {
            case 18761:
                return ByteOrder.LITTLE_ENDIAN;
            case 19789:
                return ByteOrder.BIG_ENDIAN;
            default:
                throw new IOException("Invalid byte order: " + Integer.toHexString(byteOrder));
        }
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream dataInputStream, int exifBytesLength) throws IOException {
        this.mExifByteOrder = readByteOrder(dataInputStream);
        dataInputStream.setByteOrder(this.mExifByteOrder);
        int startCode = dataInputStream.readUnsignedShort();
        if (this.mMimeType == 7 || this.mMimeType == 10 || startCode == 42) {
            int firstIfdOffset = dataInputStream.readInt();
            if (firstIfdOffset < 8 || firstIfdOffset >= exifBytesLength) {
                throw new IOException("Invalid first Ifd offset: " + firstIfdOffset);
            }
            int firstIfdOffset2 = firstIfdOffset - 8;
            if (firstIfdOffset2 > 0 && dataInputStream.skipBytes(firstIfdOffset2) != firstIfdOffset2) {
                throw new IOException("Couldn't jump to first Ifd: " + firstIfdOffset2);
            }
            return;
        }
        throw new IOException("Invalid start code: " + Integer.toHexString(startCode));
    }

    private void readImageFileDirectory(ByteOrderedDataInputStream dataInputStream, int ifdType) throws IOException {
        if (dataInputStream.mPosition + 2 <= dataInputStream.mLength) {
            short numberOfDirectoryEntry = dataInputStream.readShort();
            if (dataInputStream.mPosition + (numberOfDirectoryEntry * 12) <= dataInputStream.mLength) {
                for (short i = 0; i < numberOfDirectoryEntry; i = (short) (i + 1)) {
                    int tagNumber = dataInputStream.readUnsignedShort();
                    int dataFormat = dataInputStream.readUnsignedShort();
                    int numberOfComponents = dataInputStream.readInt();
                    long nextEntryOffset = (long) (dataInputStream.peek() + 4);
                    ExifTag tag = (ExifTag) sExifTagMapsForReading[ifdType].get(Integer.valueOf(tagNumber));
                    long byteCount = 0;
                    boolean valid = false;
                    if (tag == null) {
                        Log.w("ExifInterface", "Skip the tag entry since tag number is not defined: " + tagNumber);
                    } else if (dataFormat <= 0 || dataFormat >= IFD_FORMAT_BYTES_PER_FORMAT.length) {
                        Log.w("ExifInterface", "Skip the tag entry since data format is invalid: " + dataFormat);
                    } else {
                        byteCount = ((long) numberOfComponents) * ((long) IFD_FORMAT_BYTES_PER_FORMAT[dataFormat]);
                        if (byteCount < 0 || byteCount > 2147483647L) {
                            Log.w("ExifInterface", "Skip the tag entry since the number of components is invalid: " + numberOfComponents);
                        } else {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        dataInputStream.seek(nextEntryOffset);
                    } else {
                        if (byteCount > 4) {
                            int offset = dataInputStream.readInt();
                            if (this.mMimeType == 7) {
                                if ("MakerNote".equals(tag.name)) {
                                    this.mOrfMakerNoteOffset = offset;
                                } else if (ifdType == 6 && "ThumbnailImage".equals(tag.name)) {
                                    this.mOrfThumbnailOffset = offset;
                                    this.mOrfThumbnailLength = numberOfComponents;
                                    ExifAttribute compressionAttribute = ExifAttribute.createUShort(6, this.mExifByteOrder);
                                    ExifAttribute jpegInterchangeFormatAttribute = ExifAttribute.createULong((long) this.mOrfThumbnailOffset, this.mExifByteOrder);
                                    ExifAttribute jpegInterchangeFormatLengthAttribute = ExifAttribute.createULong((long) this.mOrfThumbnailLength, this.mExifByteOrder);
                                    this.mAttributes[4].put("Compression", compressionAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormat", jpegInterchangeFormatAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormatLength", jpegInterchangeFormatLengthAttribute);
                                }
                            } else if (this.mMimeType == 10 && "JpgFromRaw".equals(tag.name)) {
                                this.mRw2JpgFromRawOffset = offset;
                            }
                            if (((long) offset) + byteCount <= ((long) dataInputStream.mLength)) {
                                dataInputStream.seek((long) offset);
                            } else {
                                Log.w("ExifInterface", "Skip the tag entry since data offset is invalid: " + offset);
                                dataInputStream.seek(nextEntryOffset);
                            }
                        }
                        Object nextIfdType = sExifPointerTagMap.get(Integer.valueOf(tagNumber));
                        if (nextIfdType != null) {
                            long offset2 = -1;
                            switch (dataFormat) {
                                case 3:
                                    offset2 = (long) dataInputStream.readUnsignedShort();
                                    break;
                                case 4:
                                    offset2 = dataInputStream.readUnsignedInt();
                                    break;
                                case 8:
                                    offset2 = (long) dataInputStream.readShort();
                                    break;
                                case 9:
                                case 13:
                                    offset2 = (long) dataInputStream.readInt();
                                    break;
                            }
                            if (offset2 <= 0 || offset2 >= ((long) dataInputStream.mLength)) {
                                Log.w("ExifInterface", "Skip jump into the IFD since its offset is invalid: " + offset2);
                            } else {
                                dataInputStream.seek(offset2);
                                readImageFileDirectory(dataInputStream, ((Integer) nextIfdType).intValue());
                            }
                            dataInputStream.seek(nextEntryOffset);
                        } else {
                            byte[] bytes = new byte[((int) byteCount)];
                            dataInputStream.readFully(bytes);
                            ExifAttribute attribute = new ExifAttribute(dataFormat, numberOfComponents, bytes);
                            this.mAttributes[ifdType].put(tag.name, attribute);
                            if ("DNGVersion".equals(tag.name)) {
                                this.mMimeType = 3;
                            }
                            if ((("Make".equals(tag.name) || "Model".equals(tag.name)) && attribute.getStringValue(this.mExifByteOrder).contains("PENTAX")) || ("Compression".equals(tag.name) && attribute.getIntValue(this.mExifByteOrder) == 65535)) {
                                this.mMimeType = 8;
                            }
                            if (((long) dataInputStream.peek()) != nextEntryOffset) {
                                dataInputStream.seek(nextEntryOffset);
                            }
                        }
                    }
                }
                if (dataInputStream.peek() + 4 <= dataInputStream.mLength) {
                    int nextIfdOffset = dataInputStream.readInt();
                    if (nextIfdOffset > 8 && nextIfdOffset < dataInputStream.mLength) {
                        dataInputStream.seek((long) nextIfdOffset);
                        if (this.mAttributes[4].isEmpty()) {
                            readImageFileDirectory(dataInputStream, 4);
                        } else if (this.mAttributes[5].isEmpty()) {
                            readImageFileDirectory(dataInputStream, 5);
                        }
                    }
                }
            }
        }
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream in, int imageType) throws IOException {
        ExifAttribute imageWidthAttribute = (ExifAttribute) this.mAttributes[imageType].get("ImageWidth");
        if (((ExifAttribute) this.mAttributes[imageType].get("ImageLength")) == null || imageWidthAttribute == null) {
            ExifAttribute jpegInterchangeFormatAttribute = (ExifAttribute) this.mAttributes[imageType].get("JPEGInterchangeFormat");
            if (jpegInterchangeFormatAttribute != null) {
                getJpegAttributes(in, jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder), imageType);
            }
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream in) throws IOException {
        HashMap thumbnailData = this.mAttributes[4];
        ExifAttribute compressionAttribute = (ExifAttribute) thumbnailData.get("Compression");
        if (compressionAttribute != null) {
            this.mThumbnailCompression = compressionAttribute.getIntValue(this.mExifByteOrder);
            switch (this.mThumbnailCompression) {
                case 1:
                case 7:
                    if (isSupportedDataType(thumbnailData)) {
                        handleThumbnailFromStrips(in, thumbnailData);
                        return;
                    }
                    return;
                case 6:
                    handleThumbnailFromJfif(in, thumbnailData);
                    return;
                default:
                    return;
            }
        } else {
            this.mThumbnailCompression = 6;
            handleThumbnailFromJfif(in, thumbnailData);
        }
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream in, HashMap thumbnailData) throws IOException {
        ExifAttribute jpegInterchangeFormatAttribute = (ExifAttribute) thumbnailData.get("JPEGInterchangeFormat");
        ExifAttribute jpegInterchangeFormatLengthAttribute = (ExifAttribute) thumbnailData.get("JPEGInterchangeFormatLength");
        if (jpegInterchangeFormatAttribute != null && jpegInterchangeFormatLengthAttribute != null) {
            int thumbnailOffset = jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder);
            int thumbnailLength = Math.min(jpegInterchangeFormatLengthAttribute.getIntValue(this.mExifByteOrder), in.available() - thumbnailOffset);
            if (this.mMimeType == 4 || this.mMimeType == 9 || this.mMimeType == 10) {
                thumbnailOffset += this.mExifOffset;
            } else if (this.mMimeType == 7) {
                thumbnailOffset += this.mOrfMakerNoteOffset;
            }
            if (thumbnailOffset > 0 && thumbnailLength > 0) {
                this.mHasThumbnail = true;
                this.mThumbnailOffset = thumbnailOffset;
                this.mThumbnailLength = thumbnailLength;
                if (this.mFilename == null && this.mAssetInputStream == null) {
                    byte[] thumbnailBytes = new byte[thumbnailLength];
                    in.seek((long) thumbnailOffset);
                    in.readFully(thumbnailBytes);
                    this.mThumbnailBytes = thumbnailBytes;
                }
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream in, HashMap thumbnailData) throws IOException {
        ExifAttribute stripOffsetsAttribute = (ExifAttribute) thumbnailData.get("StripOffsets");
        ExifAttribute stripByteCountsAttribute = (ExifAttribute) thumbnailData.get("StripByteCounts");
        if (stripOffsetsAttribute != null && stripByteCountsAttribute != null) {
            long[] stripOffsets = convertToLongArray(stripOffsetsAttribute.getValue(this.mExifByteOrder));
            long[] stripByteCounts = convertToLongArray(stripByteCountsAttribute.getValue(this.mExifByteOrder));
            if (stripOffsets == null) {
                Log.w("ExifInterface", "stripOffsets should not be null.");
            } else if (stripByteCounts == null) {
                Log.w("ExifInterface", "stripByteCounts should not be null.");
            } else {
                long totalStripByteCount = 0;
                for (int i = 0; i < stripByteCounts.length; i++) {
                    totalStripByteCount += stripByteCounts[i];
                }
                byte[] totalStripBytes = new byte[((int) totalStripByteCount)];
                int bytesRead = 0;
                int bytesAdded = 0;
                for (int i2 = 0; i2 < stripOffsets.length; i2++) {
                    int stripByteCount = (int) stripByteCounts[i2];
                    int skipBytes = ((int) stripOffsets[i2]) - bytesRead;
                    if (skipBytes < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                    }
                    in.seek((long) skipBytes);
                    int bytesRead2 = bytesRead + skipBytes;
                    byte[] stripBytes = new byte[stripByteCount];
                    in.read(stripBytes);
                    bytesRead = bytesRead2 + stripByteCount;
                    System.arraycopy(stripBytes, 0, totalStripBytes, bytesAdded, stripBytes.length);
                    bytesAdded += stripBytes.length;
                }
                this.mHasThumbnail = true;
                this.mThumbnailBytes = totalStripBytes;
                this.mThumbnailLength = totalStripBytes.length;
            }
        }
    }

    private boolean isSupportedDataType(HashMap thumbnailData) throws IOException {
        ExifAttribute bitsPerSampleAttribute = (ExifAttribute) thumbnailData.get("BitsPerSample");
        if (bitsPerSampleAttribute != null) {
            int[] bitsPerSampleValue = (int[]) bitsPerSampleAttribute.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, bitsPerSampleValue)) {
                return true;
            }
            if (this.mMimeType == 3) {
                ExifAttribute photometricInterpretationAttribute = (ExifAttribute) thumbnailData.get("PhotometricInterpretation");
                if (photometricInterpretationAttribute != null) {
                    int photometricInterpretationValue = photometricInterpretationAttribute.getIntValue(this.mExifByteOrder);
                    if ((photometricInterpretationValue == 1 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_GREYSCALE_2)) || (photometricInterpretationValue == 6 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_RGB))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isThumbnail(HashMap map) throws IOException {
        ExifAttribute imageLengthAttribute = (ExifAttribute) map.get("ImageLength");
        ExifAttribute imageWidthAttribute = (ExifAttribute) map.get("ImageWidth");
        if (!(imageLengthAttribute == null || imageWidthAttribute == null)) {
            int imageLengthValue = imageLengthAttribute.getIntValue(this.mExifByteOrder);
            int imageWidthValue = imageWidthAttribute.getIntValue(this.mExifByteOrder);
            if (imageLengthValue <= 512 && imageWidthValue <= 512) {
                return true;
            }
        }
        return false;
    }

    private void validateImages(InputStream in) throws IOException {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute pixelXDimAttribute = (ExifAttribute) this.mAttributes[1].get("PixelXDimension");
        ExifAttribute pixelYDimAttribute = (ExifAttribute) this.mAttributes[1].get("PixelYDimension");
        if (!(pixelXDimAttribute == null || pixelYDimAttribute == null)) {
            this.mAttributes[0].put("ImageWidth", pixelXDimAttribute);
            this.mAttributes[0].put("ImageLength", pixelYDimAttribute);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            this.mAttributes[4] = this.mAttributes[5];
            this.mAttributes[5] = new HashMap();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream in, int imageType) throws IOException {
        ExifAttribute defaultCropSizeXAttribute;
        ExifAttribute defaultCropSizeYAttribute;
        ExifAttribute defaultCropSizeAttribute = (ExifAttribute) this.mAttributes[imageType].get("DefaultCropSize");
        ExifAttribute topBorderAttribute = (ExifAttribute) this.mAttributes[imageType].get("SensorTopBorder");
        ExifAttribute leftBorderAttribute = (ExifAttribute) this.mAttributes[imageType].get("SensorLeftBorder");
        ExifAttribute bottomBorderAttribute = (ExifAttribute) this.mAttributes[imageType].get("SensorBottomBorder");
        ExifAttribute rightBorderAttribute = (ExifAttribute) this.mAttributes[imageType].get("SensorRightBorder");
        if (defaultCropSizeAttribute != null) {
            if (defaultCropSizeAttribute.format == 5) {
                Rational[] defaultCropSizeValue = (Rational[]) defaultCropSizeAttribute.getValue(this.mExifByteOrder);
                if (defaultCropSizeValue == null || defaultCropSizeValue.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(defaultCropSizeValue));
                    return;
                } else {
                    defaultCropSizeXAttribute = ExifAttribute.createURational(defaultCropSizeValue[0], this.mExifByteOrder);
                    defaultCropSizeYAttribute = ExifAttribute.createURational(defaultCropSizeValue[1], this.mExifByteOrder);
                }
            } else {
                int[] defaultCropSizeValue2 = (int[]) defaultCropSizeAttribute.getValue(this.mExifByteOrder);
                if (defaultCropSizeValue2 == null || defaultCropSizeValue2.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(defaultCropSizeValue2));
                    return;
                } else {
                    defaultCropSizeXAttribute = ExifAttribute.createUShort(defaultCropSizeValue2[0], this.mExifByteOrder);
                    defaultCropSizeYAttribute = ExifAttribute.createUShort(defaultCropSizeValue2[1], this.mExifByteOrder);
                }
            }
            this.mAttributes[imageType].put("ImageWidth", defaultCropSizeXAttribute);
            this.mAttributes[imageType].put("ImageLength", defaultCropSizeYAttribute);
        } else if (topBorderAttribute == null || leftBorderAttribute == null || bottomBorderAttribute == null || rightBorderAttribute == null) {
            retrieveJpegImageSize(in, imageType);
        } else {
            int topBorderValue = topBorderAttribute.getIntValue(this.mExifByteOrder);
            int bottomBorderValue = bottomBorderAttribute.getIntValue(this.mExifByteOrder);
            int rightBorderValue = rightBorderAttribute.getIntValue(this.mExifByteOrder);
            int leftBorderValue = leftBorderAttribute.getIntValue(this.mExifByteOrder);
            if (bottomBorderValue > topBorderValue && rightBorderValue > leftBorderValue) {
                int width = rightBorderValue - leftBorderValue;
                ExifAttribute imageLengthAttribute = ExifAttribute.createUShort(bottomBorderValue - topBorderValue, this.mExifByteOrder);
                ExifAttribute imageWidthAttribute = ExifAttribute.createUShort(width, this.mExifByteOrder);
                this.mAttributes[imageType].put("ImageLength", imageLengthAttribute);
                this.mAttributes[imageType].put("ImageWidth", imageWidthAttribute);
            }
        }
    }

    private void swapBasedOnImageSize(int firstIfdType, int secondIfdType) throws IOException {
        if (!this.mAttributes[firstIfdType].isEmpty() && !this.mAttributes[secondIfdType].isEmpty()) {
            ExifAttribute firstImageLengthAttribute = (ExifAttribute) this.mAttributes[firstIfdType].get("ImageLength");
            ExifAttribute firstImageWidthAttribute = (ExifAttribute) this.mAttributes[firstIfdType].get("ImageWidth");
            ExifAttribute secondImageLengthAttribute = (ExifAttribute) this.mAttributes[secondIfdType].get("ImageLength");
            ExifAttribute secondImageWidthAttribute = (ExifAttribute) this.mAttributes[secondIfdType].get("ImageWidth");
            if (firstImageLengthAttribute != null && firstImageWidthAttribute != null && secondImageLengthAttribute != null && secondImageWidthAttribute != null) {
                int firstImageLengthValue = firstImageLengthAttribute.getIntValue(this.mExifByteOrder);
                int firstImageWidthValue = firstImageWidthAttribute.getIntValue(this.mExifByteOrder);
                int secondImageLengthValue = secondImageLengthAttribute.getIntValue(this.mExifByteOrder);
                int secondImageWidthValue = secondImageWidthAttribute.getIntValue(this.mExifByteOrder);
                if (firstImageLengthValue < secondImageLengthValue && firstImageWidthValue < secondImageWidthValue) {
                    HashMap tempMap = this.mAttributes[firstIfdType];
                    this.mAttributes[firstIfdType] = this.mAttributes[secondIfdType];
                    this.mAttributes[secondIfdType] = tempMap;
                }
            }
        }
    }

    private static long[] convertToLongArray(Object inputObj) {
        if (inputObj instanceof int[]) {
            int[] input = (int[]) inputObj;
            long[] result = new long[input.length];
            for (int i = 0; i < input.length; i++) {
                result[i] = (long) input[i];
            }
            return result;
        } else if (inputObj instanceof long[]) {
            return (long[]) inputObj;
        } else {
            return null;
        }
    }
}
