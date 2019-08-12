package com.airbnb.android.aireventlogger;

import android.content.Context;
import com.microsoft.thrifty.Struct;

public class JitneyEventHandler implements EventHandler {
    private static final String CONTENT_TYPE = "application/octet-stream";
    private final String baseUrl;
    private final CompressionType compressionType;
    private final JitneyEventTableHandler dbTableHandler;

    public JitneyEventHandler(Context context, String baseUrl2, CompressionType compressionType2) {
        this.dbTableHandler = new JitneyEventTableHandler(context);
        this.baseUrl = baseUrl2;
        this.compressionType = compressionType2;
    }

    public <T> void saveEvent(AirEvent<T> airEvent) {
        this.dbTableHandler.saveEvent(airEvent);
    }

    public void deleteEvents(int first, int last) {
        this.dbTableHandler.deleteEvents(first, last);
    }

    public PendingEvents getPendingEvents(int numToFetch) {
        Data data = this.dbTableHandler.getPendingData(numToFetch);
        if (data != null) {
            return new PendingEvents(data, new Metadata(this.baseUrl, CONTENT_TYPE, this.compressionType));
        }
        return null;
    }

    public <T> boolean supports(AirEvent<T> event) {
        return Struct.class.isAssignableFrom(Utils.getRawType(event.getEventType()));
    }
}
