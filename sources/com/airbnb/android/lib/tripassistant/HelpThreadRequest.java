package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseRequestV2;
import java.lang.reflect.Type;

public class HelpThreadRequest extends BaseRequestV2<HelpThreadResponse> {

    /* renamed from: id */
    private final long f9665id;

    public HelpThreadRequest(long id) {
        this.f9665id = id;
    }

    public Type successResponseType() {
        return HelpThreadResponse.class;
    }

    public String getPath() {
        return "help_threads/" + this.f9665id;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }
}
