package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.File;
import java.io.IOException;

public class Report implements Streamable {
    private String apiKey;
    private Error error;
    private final File errorFile;
    private Notifier notifier;

    Report(String apiKey2, File errorFile2) {
        this.apiKey = apiKey2;
        this.error = null;
        this.errorFile = errorFile2;
        this.notifier = Notifier.getInstance();
    }

    Report(String apiKey2, Error error2) {
        this.apiKey = apiKey2;
        this.error = error2;
        this.errorFile = null;
        this.notifier = Notifier.getInstance();
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("apiKey").value(this.apiKey);
        writer.name("notifier").value((Streamable) this.notifier);
        writer.name("events").beginArray();
        if (this.error != null) {
            writer.value((Streamable) this.error);
        }
        if (this.errorFile != null) {
            writer.value(this.errorFile);
        }
        writer.endArray();
        writer.endObject();
    }

    public Error getError() {
        return this.error;
    }

    public void setNotifierVersion(String version) {
        this.notifier.setVersion(version);
    }

    public void setNotifierName(String name) {
        this.notifier.setName(name);
    }

    public void setNotifierURL(String url) {
        this.notifier.setURL(url);
    }
}
