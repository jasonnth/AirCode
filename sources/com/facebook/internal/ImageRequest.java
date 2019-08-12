package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import java.util.Locale;

public class ImageRequest {
    private static final String AUTHORITY = "graph.facebook.com";
    private static final String HEIGHT_PARAM = "height";
    private static final String MIGRATION_PARAM = "migration_overrides";
    private static final String MIGRATION_VALUE = "{october_2012:true}";
    private static final String PATH = "%s/picture";
    private static final String SCHEME = "https";
    public static final int UNSPECIFIED_DIMENSION = 0;
    private static final String WIDTH_PARAM = "width";
    private boolean allowCachedRedirects;
    private Callback callback;
    private Object callerTag;
    private Context context;
    private Uri imageUri;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean allowCachedRedirects;
        /* access modifiers changed from: private */
        public Callback callback;
        /* access modifiers changed from: private */
        public Object callerTag;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public Uri imageUrl;

        public Builder(Context context2, Uri imageUri) {
            Validate.notNull(imageUri, "imageUri");
            this.context = context2;
            this.imageUrl = imageUri;
        }

        public Builder setCallback(Callback callback2) {
            this.callback = callback2;
            return this;
        }

        public Builder setCallerTag(Object callerTag2) {
            this.callerTag = callerTag2;
            return this;
        }

        public Builder setAllowCachedRedirects(boolean allowCachedRedirects2) {
            this.allowCachedRedirects = allowCachedRedirects2;
            return this;
        }

        public ImageRequest build() {
            return new ImageRequest(this);
        }
    }

    public interface Callback {
        void onCompleted(ImageResponse imageResponse);
    }

    public static Uri getProfilePictureUri(String userId, int width, int height) {
        Validate.notNullOrEmpty(userId, "userId");
        int width2 = Math.max(width, 0);
        int height2 = Math.max(height, 0);
        if (width2 == 0 && height2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        android.net.Uri.Builder builder = new android.net.Uri.Builder().scheme("https").authority(AUTHORITY).path(String.format(Locale.US, PATH, new Object[]{userId}));
        if (height2 != 0) {
            builder.appendQueryParameter("height", String.valueOf(height2));
        }
        if (width2 != 0) {
            builder.appendQueryParameter("width", String.valueOf(width2));
        }
        builder.appendQueryParameter(MIGRATION_PARAM, MIGRATION_VALUE);
        return builder.build();
    }

    private ImageRequest(Builder builder) {
        this.context = builder.context;
        this.imageUri = builder.imageUrl;
        this.callback = builder.callback;
        this.allowCachedRedirects = builder.allowCachedRedirects;
        this.callerTag = builder.callerTag == null ? new Object() : builder.callerTag;
    }

    public Context getContext() {
        return this.context;
    }

    public Uri getImageUri() {
        return this.imageUri;
    }

    public Callback getCallback() {
        return this.callback;
    }

    public boolean isCachedRedirectAllowed() {
        return this.allowCachedRedirects;
    }

    public Object getCallerTag() {
        return this.callerTag;
    }
}
