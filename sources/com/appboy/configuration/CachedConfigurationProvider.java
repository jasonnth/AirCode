package com.appboy.configuration;

import android.content.Context;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p004bo.app.C0614i;

public class CachedConfigurationProvider {

    /* renamed from: a */
    private static final String f2761a = AppboyLogger.getAppboyLogTag(CachedConfigurationProvider.class);

    /* renamed from: b */
    private final Context f2762b;
    protected final Map<String, Object> mConfigurationCache = Collections.synchronizedMap(new HashMap());
    protected final C0614i mRuntimeAppConfigurationProvider;

    public CachedConfigurationProvider(Context context) {
        this.f2762b = context;
        this.mRuntimeAppConfigurationProvider = new C0614i(context);
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanValue(String key, boolean defaultValue) {
        if (this.mConfigurationCache.containsKey(key)) {
            return ((Boolean) this.mConfigurationCache.get(key)).booleanValue();
        }
        if (this.mRuntimeAppConfigurationProvider.mo7301a(key)) {
            boolean a = this.mRuntimeAppConfigurationProvider.mo7302a(key, defaultValue);
            this.mConfigurationCache.put(key, Boolean.valueOf(a));
            AppboyLogger.m1733d(f2761a, String.format("Using runtime override value for key: %s and value: %s", new Object[]{key, Boolean.valueOf(a)}));
            return a;
        }
        boolean readBooleanResourceValue = readBooleanResourceValue(key, defaultValue);
        this.mConfigurationCache.put(key, Boolean.valueOf(readBooleanResourceValue));
        AppboyLogger.m1733d(f2761a, String.format("Defaulting to using xml value for key: %s and value: %s", new Object[]{key, Boolean.valueOf(readBooleanResourceValue)}));
        return readBooleanResourceValue;
    }

    /* access modifiers changed from: protected */
    public String getStringValue(String key, String defaultValue) {
        if (this.mConfigurationCache.containsKey(key)) {
            return (String) this.mConfigurationCache.get(key);
        }
        if (this.mRuntimeAppConfigurationProvider.mo7301a(key)) {
            String a = this.mRuntimeAppConfigurationProvider.mo7300a(key, defaultValue);
            this.mConfigurationCache.put(key, a);
            AppboyLogger.m1733d(f2761a, String.format("Using runtime override value for key: %s and value: %s", new Object[]{key, a}));
            return a;
        }
        String readStringResourceValue = readStringResourceValue(key, defaultValue);
        this.mConfigurationCache.put(key, readStringResourceValue);
        AppboyLogger.m1733d(f2761a, String.format("Defaulting to using xml value for key: %s and value: %s", new Object[]{key, readStringResourceValue}));
        return readStringResourceValue;
    }

    public int getIntValue(String key, int defaultValue) {
        if (this.mConfigurationCache.containsKey(key)) {
            return ((Integer) this.mConfigurationCache.get(key)).intValue();
        }
        if (this.mRuntimeAppConfigurationProvider.mo7301a(key)) {
            int a = this.mRuntimeAppConfigurationProvider.mo7299a(key, defaultValue);
            this.mConfigurationCache.put(key, Integer.valueOf(a));
            AppboyLogger.m1733d(f2761a, String.format("Using runtime override value for key: %s and value: %s", new Object[]{key, Integer.valueOf(a)}));
            return a;
        }
        int readIntegerResourceValue = readIntegerResourceValue(key, defaultValue);
        this.mConfigurationCache.put(key, Integer.valueOf(readIntegerResourceValue));
        AppboyLogger.m1733d(f2761a, String.format("Defaulting to using xml value for key: %s and value: %s", new Object[]{key, Integer.valueOf(readIntegerResourceValue)}));
        return readIntegerResourceValue;
    }

    /* access modifiers changed from: protected */
    public int readIntegerResourceValue(String key, int defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.f2762b.getResources().getIdentifier(key, "integer", PackageUtils.getResourcePackageName(this.f2762b));
            if (identifier != 0) {
                return this.f2762b.getResources().getInteger(identifier);
            }
            AppboyLogger.m1733d(f2761a, String.format("Unable to find the xml integer configuration value with key %s. Using default value '%d'.", new Object[]{key, Integer.valueOf(defaultValue)}));
            return defaultValue;
        } catch (Exception e) {
            AppboyLogger.m1733d(f2761a, String.format("Unexpected exception retrieving the xml integer configuration value with key %s. Using default value '%d'.", new Object[]{key, Integer.valueOf(defaultValue)}));
            return defaultValue;
        }
    }

    /* access modifiers changed from: protected */
    public boolean readBooleanResourceValue(String key, boolean defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.f2762b.getResources().getIdentifier(key, "bool", PackageUtils.getResourcePackageName(this.f2762b));
            if (identifier != 0) {
                return this.f2762b.getResources().getBoolean(identifier);
            }
            AppboyLogger.m1733d(f2761a, String.format("Unable to find the xml boolean configuration value with key %s. Using default value '%b'.", new Object[]{key, Boolean.valueOf(defaultValue)}));
            return defaultValue;
        } catch (Exception e) {
            AppboyLogger.m1733d(f2761a, String.format("Unexpected exception retrieving the xml boolean configuration value with key %s. Using default value '%b'.", new Object[]{key, Boolean.valueOf(defaultValue)}));
            return defaultValue;
        }
    }

    /* access modifiers changed from: protected */
    public String readStringResourceValue(String key, String defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.f2762b.getResources().getIdentifier(key, "string", PackageUtils.getResourcePackageName(this.f2762b));
            if (identifier != 0) {
                return this.f2762b.getResources().getString(identifier);
            }
            AppboyLogger.m1733d(f2761a, String.format("Unable to find the xml string configuration value with key %s. Using default value '%s'.", new Object[]{key, defaultValue}));
            return defaultValue;
        } catch (Exception e) {
            AppboyLogger.m1733d(f2761a, String.format("Unexpected exception retrieving the xml string configuration value with key %s. Using default value '%s'.", new Object[]{key, defaultValue}));
            return defaultValue;
        }
    }

    /* access modifiers changed from: protected */
    public String[] readStringArrayResourceValue(String key, String[] defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        try {
            int identifier = this.f2762b.getResources().getIdentifier(key, "array", PackageUtils.getResourcePackageName(this.f2762b));
            if (identifier != 0) {
                return this.f2762b.getResources().getStringArray(identifier);
            }
            AppboyLogger.m1733d(f2761a, String.format("Unable to find the xml string array configuration value with key %s. Using default value '%s'.", new Object[]{key, Arrays.toString(defaultValue)}));
            return defaultValue;
        } catch (Exception e) {
            AppboyLogger.m1733d(f2761a, String.format("Unexpected exception retrieving the xml string array configuration value with key %s. Using default value '%s'.", new Object[]{key, Arrays.toString(defaultValue)}));
            return defaultValue;
        }
    }
}
