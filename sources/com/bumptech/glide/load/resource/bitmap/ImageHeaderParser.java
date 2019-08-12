package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.facebook.soloader.MinElf;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import p005cn.jpush.android.JPushConstants;

public class ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
    private final StreamReader streamReader;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);
        
        private final boolean hasAlpha;

        private ImageType(boolean hasAlpha2) {
            this.hasAlpha = hasAlpha2;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    private static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] data2) {
            this.data = ByteBuffer.wrap(data2);
            this.data.order(ByteOrder.BIG_ENDIAN);
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }

        public int length() {
            return this.data.array().length;
        }

        public int getInt32(int offset) {
            return this.data.getInt(offset);
        }

        public short getInt16(int offset) {
            return this.data.getShort(offset);
        }
    }

    private static class StreamReader {

        /* renamed from: is */
        private final InputStream f2935is;

        public StreamReader(InputStream is) {
            this.f2935is = is;
        }

        public int getUInt16() throws IOException {
            return ((this.f2935is.read() << 8) & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK) | (this.f2935is.read() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (this.f2935is.read() & 255);
        }

        public long skip(long total) throws IOException {
            if (total < 0) {
                return 0;
            }
            long toSkip = total;
            while (toSkip > 0) {
                long skipped = this.f2935is.skip(toSkip);
                if (skipped > 0) {
                    toSkip -= skipped;
                } else if (this.f2935is.read() == -1) {
                    break;
                } else {
                    toSkip--;
                }
            }
            return total - toSkip;
        }

        public int read(byte[] buffer) throws IOException {
            int toRead = buffer.length;
            while (toRead > 0) {
                int read = this.f2935is.read(buffer, buffer.length - toRead, toRead);
                if (read == -1) {
                    break;
                }
                toRead -= read;
            }
            return buffer.length - toRead;
        }

        public int getByte() throws IOException {
            return this.f2935is.read();
        }
    }

    static {
        byte[] bytes = new byte[0];
        try {
            bytes = "Exif\u0000\u0000".getBytes(JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = bytes;
    }

    public ImageHeaderParser(InputStream is) {
        this.streamReader = new StreamReader(is);
    }

    public boolean hasAlpha() throws IOException {
        return getType().hasAlpha();
    }

    public ImageType getType() throws IOException {
        int firstTwoBytes = this.streamReader.getUInt16();
        if (firstTwoBytes == 65496) {
            return ImageType.JPEG;
        }
        int firstFourBytes = ((firstTwoBytes << 16) & -65536) | (this.streamReader.getUInt16() & MinElf.PN_XNUM);
        if (firstFourBytes == -1991225785) {
            this.streamReader.skip(21);
            return this.streamReader.getByte() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((firstFourBytes >> 8) == 4671814) {
            return ImageType.GIF;
        } else {
            return ImageType.UNKNOWN;
        }
    }

    public int getOrientation() throws IOException {
        if (!handles(this.streamReader.getUInt16())) {
            return -1;
        }
        byte[] exifData = getExifSegment();
        boolean hasJpegExifPreamble = exifData != null && exifData.length > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (hasJpegExifPreamble) {
            int i = 0;
            while (true) {
                if (i >= JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length) {
                    break;
                } else if (exifData[i] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[i]) {
                    hasJpegExifPreamble = false;
                    break;
                } else {
                    i++;
                }
            }
        }
        if (hasJpegExifPreamble) {
            return parseExifSegment(new RandomAccessReader(exifData));
        }
        return -1;
    }

    private byte[] getExifSegment() throws IOException {
        short segmentType;
        int segmentLength;
        long skipped;
        do {
            short segmentId = this.streamReader.getUInt8();
            if (segmentId != 255) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Unknown segmentId=" + segmentId);
                }
                return null;
            }
            segmentType = this.streamReader.getUInt8();
            if (segmentType == 218) {
                return null;
            }
            if (segmentType == 217) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return null;
            }
            segmentLength = this.streamReader.getUInt16() - 2;
            if (segmentType != 225) {
                skipped = this.streamReader.skip((long) segmentLength);
            } else {
                byte[] segmentData = new byte[segmentLength];
                int read = this.streamReader.read(segmentData);
                if (read == segmentLength) {
                    return segmentData;
                }
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Unable to read segment data, type: " + segmentType + ", length: " + segmentLength + ", actually read: " + read);
                }
                return null;
            }
        } while (skipped == ((long) segmentLength));
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            Log.d("ImageHeaderParser", "Unable to skip enough data, type: " + segmentType + ", wanted to skip: " + segmentLength + ", but actually skipped: " + skipped);
        }
        return null;
    }

    private static int parseExifSegment(RandomAccessReader segmentData) {
        ByteOrder byteOrder;
        int headerOffsetSize = "Exif\u0000\u0000".length();
        short byteOrderIdentifier = segmentData.getInt16(headerOffsetSize);
        if (byteOrderIdentifier == 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (byteOrderIdentifier == 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Unknown endianness = " + byteOrderIdentifier);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        segmentData.order(byteOrder);
        int firstIfdOffset = segmentData.getInt32(headerOffsetSize + 4) + headerOffsetSize;
        int tagCount = segmentData.getInt16(firstIfdOffset);
        for (int i = 0; i < tagCount; i++) {
            int tagOffset = calcTagOffset(firstIfdOffset, i);
            int tagType = segmentData.getInt16(tagOffset);
            if (tagType == 274) {
                int formatCode = segmentData.getInt16(tagOffset + 2);
                if (formatCode >= 1 && formatCode <= 12) {
                    int componentCount = segmentData.getInt32(tagOffset + 4);
                    if (componentCount >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got tagIndex=" + i + " tagType=" + tagType + " formatCode=" + formatCode + " componentCount=" + componentCount);
                        }
                        int byteCount = componentCount + BYTES_PER_FORMAT[formatCode];
                        if (byteCount <= 4) {
                            int tagValueOffset = tagOffset + 8;
                            if (tagValueOffset < 0 || tagValueOffset > segmentData.length()) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal tagValueOffset=" + tagValueOffset + " tagType=" + tagType);
                                }
                            } else if (byteCount >= 0 && tagValueOffset + byteCount <= segmentData.length()) {
                                return segmentData.getInt16(tagValueOffset);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + tagType);
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + formatCode);
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                        Log.d("ImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Got invalid format code=" + formatCode);
                }
            }
        }
        return -1;
    }

    private static int calcTagOffset(int ifdOffset, int tagIndex) {
        return ifdOffset + 2 + (tagIndex * 12);
    }

    private static boolean handles(int imageMagicNumber) {
        return (imageMagicNumber & 65496) == 65496 || imageMagicNumber == 19789 || imageMagicNumber == 18761;
    }
}
