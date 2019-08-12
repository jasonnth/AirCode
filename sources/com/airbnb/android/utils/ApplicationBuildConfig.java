package com.airbnb.android.utils;

public final class ApplicationBuildConfig {
    public static String APPLICATION_ID;
    public static String BUILD_TYPE;
    public static String CHINA_INSTALL_TAG;
    public static String CODE_PUSH_DEPLOY_KEY;
    public static boolean DEBUG;
    public static String GIT_BRANCH;
    public static String GIT_SHA;
    public static int VERSION_CODE;
    public static String VERSION_NAME;

    static {
        DEBUG = false;
        BUILD_TYPE = "debug";
        VERSION_NAME = "";
        APPLICATION_ID = "";
        CODE_PUSH_DEPLOY_KEY = "";
        CHINA_INSTALL_TAG = "";
        GIT_SHA = "";
        GIT_BRANCH = "";
        VERSION_CODE = 0;
        try {
            Class<?> clazz = Class.forName("com.airbnb.android.BuildConfig");
            DEBUG = ((Boolean) clazz.getField("DEBUG").get(null)).booleanValue();
            BUILD_TYPE = (String) clazz.getField("BUILD_TYPE").get(null);
            VERSION_NAME = (String) clazz.getField("VERSION_NAME").get(null);
            VERSION_CODE = ((Integer) clazz.getField("VERSION_CODE").get(null)).intValue();
            APPLICATION_ID = (String) clazz.getField("APPLICATION_ID").get(null);
            CODE_PUSH_DEPLOY_KEY = (String) clazz.getField("CODE_PUSH_DEPLOY_KEY").get(null);
            CHINA_INSTALL_TAG = (String) clazz.getField("CHINA_INSTALL_TAG").get(null);
            GIT_SHA = (String) clazz.getField("GIT_SHA").get(null);
            GIT_BRANCH = (String) clazz.getField("GIT_BRANCH").get(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
        }
    }
}
