package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;

class User implements Streamable {
    private String email;

    /* renamed from: id */
    private String f2924id;
    private String name;

    User() {
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("id").value(this.f2924id);
        writer.name("email").value(this.email);
        writer.name("name").value(this.name);
        writer.endObject();
    }

    public void setId(String id) {
        this.f2924id = id;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public void setName(String name2) {
        this.name = name2;
    }
}
