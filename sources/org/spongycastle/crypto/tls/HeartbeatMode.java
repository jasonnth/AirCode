package org.spongycastle.crypto.tls;

public class HeartbeatMode {
    public static final short peer_allowed_to_send = 1;
    public static final short peer_not_allowed_to_send = 2;

    public static boolean isValid(short heartbeatMode) {
        return heartbeatMode >= 1 && heartbeatMode <= 2;
    }
}
