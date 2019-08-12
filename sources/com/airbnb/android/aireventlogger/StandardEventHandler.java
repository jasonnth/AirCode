package com.airbnb.android.aireventlogger;

import android.content.Context;
import com.airbnb.android.aireventlogger.Converter.Factory;

public class StandardEventHandler implements EventHandler {
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    private final String baseUrl;
    private final CompressionType compressionType;
    private final StandardTableHandler dbTableHandler;

    public StandardEventHandler(Context context, Factory converterFactory, String baseUrl2, CompressionType compressionType2, String tableName) {
        this.compressionType = compressionType2;
        this.dbTableHandler = new StandardTableHandler(context, converterFactory, tableName);
        this.baseUrl = baseUrl2;
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

    public <T> boolean supports(AirEvent<T> airEvent) {
        return true;
    }
}
