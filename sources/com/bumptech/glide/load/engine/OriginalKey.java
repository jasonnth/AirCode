package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import p005cn.jpush.android.JPushConstants;

class OriginalKey implements Key {

    /* renamed from: id */
    private final String f2933id;
    private final Key signature;

    public OriginalKey(String id, Key signature2) {
        this.f2933id = id;
        this.signature = signature2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OriginalKey that = (OriginalKey) o;
        if (!this.f2933id.equals(that.f2933id)) {
            return false;
        }
        if (!this.signature.equals(that.signature)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f2933id.hashCode() * 31) + this.signature.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.f2933id.getBytes(JPushConstants.ENCODING_UTF_8));
        this.signature.updateDiskCacheKey(messageDigest);
    }
}
