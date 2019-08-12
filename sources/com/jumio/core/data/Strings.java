package com.jumio.core.data;

import android.content.Context;
import java.util.HashMap;

public abstract class Strings {
    private static SDKStringFactory factory;
    private static Strings instance;
    protected String mPrefix;
    protected HashMap<String, String> stringProvider = new HashMap<>();

    public interface SDKStringFactory {
        Strings createInstance();
    }

    public static synchronized void setFactory(SDKStringFactory fact) {
        synchronized (Strings.class) {
            instance = null;
            factory = fact;
        }
    }

    public static synchronized Strings getInstance() {
        Strings strings;
        synchronized (Strings.class) {
            if (factory == null) {
                throw new IllegalStateException("need to call Strings.setFactory()!");
            }
            if (instance == null) {
                instance = factory.createInstance();
            }
            strings = instance;
        }
        return strings;
    }

    public static String getExternalString(Context c, String key) {
        try {
            int resId = c.getResources().getIdentifier(getInstance().mPrefix + key, "string", c.getPackageName());
            if (resId == 0) {
                return getFallbackString(key);
            }
            return c.getString(resId);
        } catch (Exception e) {
            return getFallbackString(key);
        }
    }

    private HashMap<String, String> getStringProvider() {
        return this.stringProvider;
    }

    private static String getFallbackString(String key) {
        if (getInstance().getStringProvider() != null) {
            return (String) getInstance().getStringProvider().get(key);
        }
        return "";
    }
}
