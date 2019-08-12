package org.apache.sanselan.formats.jpeg;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.common.BinaryFileParser;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.util.Debug;

public class JpegUtils extends BinaryFileParser implements JpegConstants {

    public interface Visitor {
        boolean beginSOS();

        boolean visitSOS(int i, byte[] bArr, InputStream inputStream);

        boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException;
    }

    public JpegUtils() {
        setByteOrder(77);
    }

    public void traverseJFIF(ByteSource byteSource, Visitor visitor) throws ImageReadException, IOException {
        byte[] markerBytes;
        int marker;
        boolean doClose;
        InputStream is = null;
        try {
            is = byteSource.getInputStream();
            readAndVerifyBytes(is, SOI, "Not a Valid JPEG File: doesn't begin with 0xffd8");
            int byteOrder = getByteOrder();
            int markerCount = 0;
            while (true) {
                markerBytes = readByteArray("markerBytes", 2, is, "markerBytes");
                marker = convertByteArrayToShort("marker", markerBytes, byteOrder);
                if (marker != 65497 && marker != 65498) {
                    byte[] segmentLengthBytes = readByteArray("segmentLengthBytes", 2, is, "segmentLengthBytes");
                    int segmentLength = convertByteArrayToShort("segmentLength", segmentLengthBytes, byteOrder);
                    if (visitor.visitSegment(marker, markerBytes, segmentLength, segmentLengthBytes, readByteArray("Segment Data", segmentLength - 2, is, "Invalid Segment: insufficient data"))) {
                        markerCount++;
                    } else if (is != null && 1 != 0) {
                        try {
                            is.close();
                            return;
                        } catch (Exception e) {
                            Debug.debug((Throwable) e);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            if (visitor.beginSOS()) {
                if (!visitor.visitSOS(marker, markerBytes, is)) {
                    doClose = true;
                } else {
                    doClose = false;
                }
                if (is != null && doClose) {
                    try {
                        is.close();
                    } catch (Exception e2) {
                        Debug.debug((Throwable) e2);
                    }
                }
            } else if (is != null && 1 != 0) {
                try {
                    is.close();
                } catch (Exception e3) {
                    Debug.debug((Throwable) e3);
                }
            }
        } finally {
            if (!(is == null || 1 == 0)) {
                try {
                    is.close();
                } catch (Exception e4) {
                    Debug.debug((Throwable) e4);
                }
            }
        }
    }
}
