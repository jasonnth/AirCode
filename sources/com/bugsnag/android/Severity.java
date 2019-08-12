package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;

public enum Severity implements Streamable {
    ERROR("error"),
    WARNING("warning"),
    INFO("info");
    
    private final String name;

    private Severity(String name2) {
        this.name = name2;
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.value(this.name);
    }
}
