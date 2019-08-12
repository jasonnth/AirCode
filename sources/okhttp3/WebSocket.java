package okhttp3;

import okio.ByteString;

public interface WebSocket {
    boolean close(int i, String str);

    boolean send(String str);

    boolean send(ByteString byteString);
}
