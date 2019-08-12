package org.apache.sanselan.formats.jpeg.exifRewrite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryFileParser;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.common.byteSources.ByteSourceArray;
import org.apache.sanselan.formats.jpeg.JpegConstants;
import org.apache.sanselan.formats.jpeg.JpegUtils;
import org.apache.sanselan.formats.jpeg.JpegUtils.Visitor;
import org.apache.sanselan.formats.tiff.write.TiffImageWriterBase;
import org.apache.sanselan.formats.tiff.write.TiffImageWriterLossless;
import org.apache.sanselan.formats.tiff.write.TiffImageWriterLossy;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;
import org.apache.sanselan.util.Debug;

public class ExifRewriter extends BinaryFileParser implements JpegConstants {

    public static class ExifOverflowException extends ImageWriteException {
        public ExifOverflowException(String s) {
            super(s);
        }
    }

    private static abstract class JFIFPiece {
        /* access modifiers changed from: protected */
        public abstract void write(OutputStream outputStream) throws IOException;

        private JFIFPiece() {
        }
    }

    private static class JFIFPieceImageData extends JFIFPiece {
        public final byte[] imageData = null;
        public final InputStream isImageData;
        public final byte[] markerBytes;

        public JFIFPieceImageData(byte[] markerBytes2, InputStream isImageData2) {
            super();
            this.markerBytes = markerBytes2;
            this.isImageData = isImageData2;
        }

        /* access modifiers changed from: protected */
        public void write(OutputStream os) throws IOException {
            os.write(this.markerBytes);
            if (this.imageData != null) {
                os.write(this.imageData);
                return;
            }
            byte[] buffer = new byte[1024];
            while (true) {
                int read = this.isImageData.read(buffer);
                if (read > 0) {
                    os.write(buffer, 0, read);
                } else {
                    try {
                        this.isImageData.close();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        }
    }

    private static class JFIFPieceSegment extends JFIFPiece {
        public final int marker;
        public final byte[] markerBytes;
        public final byte[] markerLengthBytes;
        public final byte[] segmentData;

        public JFIFPieceSegment(int marker2, byte[] markerBytes2, byte[] markerLengthBytes2, byte[] segmentData2) {
            super();
            this.marker = marker2;
            this.markerBytes = markerBytes2;
            this.markerLengthBytes = markerLengthBytes2;
            this.segmentData = segmentData2;
        }

        /* access modifiers changed from: protected */
        public void write(OutputStream os) throws IOException {
            os.write(this.markerBytes);
            os.write(this.markerLengthBytes);
            os.write(this.segmentData);
        }
    }

    private static class JFIFPieceSegmentExif extends JFIFPieceSegment {
        public JFIFPieceSegmentExif(int marker, byte[] markerBytes, byte[] markerLengthBytes, byte[] segmentData) {
            super(marker, markerBytes, markerLengthBytes, segmentData);
        }
    }

    private static class JFIFPieces {
        public final List exifPieces;
        public final List pieces;

        public JFIFPieces(List pieces2, List exifPieces2) {
            this.pieces = pieces2;
            this.exifPieces = exifPieces2;
        }
    }

    public ExifRewriter() {
        setByteOrder(77);
    }

    private JFIFPieces analyzeJFIF(ByteSource byteSource) throws ImageReadException, IOException {
        final ArrayList pieces = new ArrayList();
        final List exifPieces = new ArrayList();
        new JpegUtils().traverseJFIF(byteSource, new Visitor() {
            public boolean beginSOS() {
                return true;
            }

            public boolean visitSOS(int marker, byte[] markerBytes, InputStream is) {
                pieces.add(new JFIFPieceImageData(markerBytes, is));
                return true;
            }

            public boolean visitSegment(int marker, byte[] markerBytes, int markerLength, byte[] markerLengthBytes, byte[] segmentData) throws ImageReadException, IOException {
                if (marker != 65505) {
                    pieces.add(new JFIFPieceSegment(marker, markerBytes, markerLengthBytes, segmentData));
                } else if (!BinaryFileParser.byteArrayHasPrefix(segmentData, JpegConstants.EXIF_IDENTIFIER_CODE)) {
                    pieces.add(new JFIFPieceSegment(marker, markerBytes, markerLengthBytes, segmentData));
                } else {
                    JFIFPiece piece = new JFIFPieceSegmentExif(marker, markerBytes, markerLengthBytes, segmentData);
                    pieces.add(piece);
                    exifPieces.add(piece);
                }
                return true;
            }
        });
        return new JFIFPieces(pieces, exifPieces);
    }

    public void updateExifMetadataLossless(byte[] src, OutputStream os, TiffOutputSet outputSet) throws ImageReadException, IOException, ImageWriteException {
        updateExifMetadataLossless(new ByteSourceArray(src), os, outputSet);
    }

    public void updateExifMetadataLossless(ByteSource byteSource, OutputStream os, TiffOutputSet outputSet) throws ImageReadException, IOException, ImageWriteException {
        TiffImageWriterBase writer;
        JFIFPieces jfifPieces = analyzeJFIF(byteSource);
        List pieces = jfifPieces.pieces;
        if (jfifPieces.exifPieces.size() > 0) {
            writer = new TiffImageWriterLossless(outputSet.byteOrder, getByteArrayTail("trimmed exif bytes", ((JFIFPieceSegment) jfifPieces.exifPieces.get(0)).segmentData, 6));
        } else {
            writer = new TiffImageWriterLossy(outputSet.byteOrder);
        }
        writeSegmentsReplacingExif(os, pieces, writeExifSegment(writer, outputSet, true));
    }

    private void writeSegmentsReplacingExif(OutputStream os, List segments, byte[] newBytes) throws ImageWriteException, IOException {
        int byteOrder = getByteOrder();
        try {
            os.write(SOI);
            boolean hasExif = false;
            for (int i = 0; i < segments.size(); i++) {
                if (((JFIFPiece) segments.get(i)) instanceof JFIFPieceSegmentExif) {
                    hasExif = true;
                }
            }
            if (!hasExif && newBytes != null) {
                byte[] markerBytes = convertShortToByteArray(65505, byteOrder);
                if (newBytes.length > 65535) {
                    throw new ExifOverflowException("APP1 Segment is too long: " + newBytes.length);
                }
                byte[] markerLengthBytes = convertShortToByteArray(newBytes.length + 2, byteOrder);
                if (((JFIFPieceSegment) segments.get(0)).marker == 65504) {
                }
                segments.add(0, new JFIFPieceSegmentExif(65505, markerBytes, markerLengthBytes, newBytes));
            }
            boolean APP1Written = false;
            for (int i2 = 0; i2 < segments.size(); i2++) {
                JFIFPiece piece = (JFIFPiece) segments.get(i2);
                if (!(piece instanceof JFIFPieceSegmentExif)) {
                    piece.write(os);
                } else if (APP1Written) {
                    continue;
                } else {
                    APP1Written = true;
                    if (newBytes != null) {
                        byte[] markerBytes2 = convertShortToByteArray(65505, byteOrder);
                        if (newBytes.length > 65535) {
                            throw new ExifOverflowException("APP1 Segment is too long: " + newBytes.length);
                        }
                        byte[] markerLengthBytes2 = convertShortToByteArray(newBytes.length + 2, byteOrder);
                        os.write(markerBytes2);
                        os.write(markerLengthBytes2);
                        os.write(newBytes);
                    } else {
                        continue;
                    }
                }
            }
            try {
            } catch (Exception e) {
                Debug.debug((Throwable) e);
            }
        } finally {
            try {
                os.close();
            } catch (Exception e2) {
                Debug.debug((Throwable) e2);
            }
        }
    }

    private byte[] writeExifSegment(TiffImageWriterBase writer, TiffOutputSet outputSet, boolean includeEXIFPrefix) throws IOException, ImageWriteException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        if (includeEXIFPrefix) {
            os.write(EXIF_IDENTIFIER_CODE);
            os.write(0);
            os.write(0);
        }
        writer.write(os, outputSet);
        return os.toByteArray();
    }
}
