package com.airbnb.android.lib.glide;

import android.util.Base64;
import com.airbnb.p027n2.utils.Base64Model;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class Base64StreamDataFetcher implements DataFetcher<InputStream> {
    private final Base64Model base64;

    public Base64StreamDataFetcher(Base64Model base642) {
        this.base64 = base642;
    }

    public InputStream loadData(Priority priority) throws Exception {
        if (this.base64.isEmpty()) {
            return null;
        }
        return new ByteArrayInputStream(Base64.decode(this.base64.getBase64(), 0));
    }

    public String getId() {
        return this.base64.getBase64();
    }

    public void cancel() {
    }

    public void cleanup() {
    }
}
