package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;

class Notifier implements Streamable {
    private static final Notifier instance = new Notifier();
    private String name = "Android Bugsnag Notifier";
    private String url = "https://bugsnag.com";
    private String version = "3.9.0";

    public static Notifier getInstance() {
        return instance;
    }

    Notifier() {
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("name").value(this.name);
        writer.name("version").value(this.version);
        writer.name("url").value(this.url);
        writer.endObject();
    }

    public void setVersion(String version2) {
        this.version = version2;
    }

    public void setURL(String url2) {
        this.url = url2;
    }

    public void setName(String name2) {
        this.name = name2;
    }
}
