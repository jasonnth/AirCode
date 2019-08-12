package okhttp3.internal.p320ws;

/* renamed from: okhttp3.internal.ws.WebSocketProtocol */
public final class WebSocketProtocol {
    static void toggleMask(byte[] buffer, long byteCount, byte[] key, long frameBytesRead) {
        int keyLength = key.length;
        int i = 0;
        while (((long) i) < byteCount) {
            buffer[i] = (byte) (buffer[i] ^ key[(int) (frameBytesRead % ((long) keyLength))]);
            i++;
            frameBytesRead++;
        }
    }

    static String closeCodeExceptionMessage(int code) {
        if (code < 1000 || code >= 5000) {
            return "Code must be in range [1000,5000): " + code;
        }
        if ((code < 1004 || code > 1006) && (code < 1012 || code > 2999)) {
            return null;
        }
        return "Code " + code + " is reserved and may not be used.";
    }

    static void validateCloseCode(int code) {
        String message = closeCodeExceptionMessage(code);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
    }
}
