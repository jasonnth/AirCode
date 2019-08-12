package com.facebook.imageutils;

import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.tls.CipherSuite;

public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        return TiffUtil.getAutoRotateAngleFromOrientation(orientation);
    }

    public static int getOrientation(byte[] jpeg) {
        return getOrientation((InputStream) new ByteArrayInputStream(jpeg));
    }

    public static int getOrientation(InputStream is) {
        try {
            int length = moveToAPP1EXIF(is);
            if (length == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(is, length);
        } catch (IOException e) {
            return 0;
        }
    }

    public static boolean moveToMarker(InputStream is, int markerToFind) throws IOException {
        Preconditions.checkNotNull(is);
        while (StreamProcessor.readPackedInt(is, 1, false) == 255) {
            int marker = 255;
            while (marker == 255) {
                marker = StreamProcessor.readPackedInt(is, 1, false);
            }
            if ((markerToFind == 192 && isSOFn(marker)) || marker == markerToFind) {
                return true;
            }
            if (!(marker == 216 || marker == 1)) {
                if (marker == 217 || marker == 218) {
                    return false;
                }
                is.skip((long) (StreamProcessor.readPackedInt(is, 2, false) - 2));
            }
        }
        return false;
    }

    private static boolean isSOFn(int marker) {
        switch (marker) {
            case 192:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /*193*/:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /*194*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /*195*/:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /*197*/:
            case 198:
            case 199:
            case 201:
            case CityRegistrationController.RC_CHOOSE_PHOTO /*202*/:
            case 203:
            case 205:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    private static int moveToAPP1EXIF(InputStream is) throws IOException {
        if (moveToMarker(is, MARKER_APP1)) {
            int length = StreamProcessor.readPackedInt(is, 2, false) - 2;
            if (length > 6) {
                int magic = StreamProcessor.readPackedInt(is, 4, false);
                int length2 = length - 4;
                int zero = StreamProcessor.readPackedInt(is, 2, false);
                int length3 = length2 - 2;
                if (magic == 1165519206 && zero == 0) {
                    return length3;
                }
            }
        }
        return 0;
    }
}
