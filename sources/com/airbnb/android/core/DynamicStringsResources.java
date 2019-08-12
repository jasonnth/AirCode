package com.airbnb.android.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.airbnb.android.lib.DynamicStringsHelper;
import com.airbnb.android.utils.AndroidVersion;
import java.util.Locale;

public class DynamicStringsResources extends Resources {
    private final Resources originalResources;
    private final SharedPreferences stringsPreferences;

    public DynamicStringsResources(Context context, AirbnbPreferences airbnbPreferences) {
        super(context.getAssets(), context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        this.stringsPreferences = airbnbPreferences.getStringPreferences();
        this.originalResources = context.getResources();
    }

    public String getString(int id) throws NotFoundException {
        if (DynamicStringsHelper.hasFetchedStrings(getCurrentDeviceLocale().getLanguage(), this.stringsPreferences)) {
            String result = fetchStringFromSharedPrefs(id);
            if (result != null) {
                return result;
            }
        }
        return this.originalResources.getString(id);
    }

    public String fetchStringFromSharedPrefs(int id) {
        return this.stringsPreferences.getString(String.valueOf(id), null);
    }

    public String getQuantityString(int id, int quantity, Object... formatArgs) throws NotFoundException {
        return super.getQuantityString(id, quantity, formatArgs);
    }

    @TargetApi(24)
    private Locale getCurrentDeviceLocale() {
        if (AndroidVersion.isAtLeastNougat()) {
            return getConfiguration().getLocales().get(0);
        }
        return getConfiguration().locale;
    }
}
