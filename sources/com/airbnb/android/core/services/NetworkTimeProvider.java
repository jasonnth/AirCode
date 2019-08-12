package com.airbnb.android.core.services;

import java.io.IOException;
import java.net.InetAddress;
import org.apache.commons.net.ntp.NTPUDPClient;

public class NetworkTimeProvider {
    private static final String TIME_SERVER = "pool.ntp.org";
    private final NTPUDPClient timeClient;

    public NetworkTimeProvider(NTPUDPClient timeClient2) {
        this.timeClient = timeClient2;
    }

    public long getNetworkTime() throws IOException {
        return this.timeClient.getTime(InetAddress.getByName(TIME_SERVER)).getMessage().getReceiveTimeStamp().getTime();
    }
}
