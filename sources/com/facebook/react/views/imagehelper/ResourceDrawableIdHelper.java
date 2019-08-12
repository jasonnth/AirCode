package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import java.util.HashMap;
import java.util.Map;

public class ResourceDrawableIdHelper {
    private static final String LOCAL_RESOURCE_SCHEME = "res";
    private static ResourceDrawableIdHelper sResourceDrawableIdHelper;
    private Map<String, Integer> mResourceDrawableIdMap = new HashMap();

    private ResourceDrawableIdHelper() {
    }

    public static ResourceDrawableIdHelper getInstance() {
        if (sResourceDrawableIdHelper == null) {
            sResourceDrawableIdHelper = new ResourceDrawableIdHelper();
        }
        return sResourceDrawableIdHelper;
    }

    public void clear() {
        this.mResourceDrawableIdMap.clear();
    }

    public int getResourceDrawableId(Context context, String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        String name2 = name.toLowerCase().replace("-", "_");
        if (this.mResourceDrawableIdMap.containsKey(name2)) {
            return ((Integer) this.mResourceDrawableIdMap.get(name2)).intValue();
        }
        int id = context.getResources().getIdentifier(name2, "drawable", context.getPackageName());
        this.mResourceDrawableIdMap.put(name2, Integer.valueOf(id));
        return id;
    }

    public Drawable getResourceDrawable(Context context, String name) {
        int resId = getResourceDrawableId(context, name);
        if (resId > 0) {
            return context.getResources().getDrawable(resId);
        }
        return null;
    }

    public Uri getResourceDrawableUri(Context context, String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? new Builder().scheme("res").path(String.valueOf(resId)).build() : Uri.EMPTY;
    }
}
