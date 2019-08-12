package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class ManifestValidator {
    public static ActivityInfo getActivityInfo(Context context, Class klass) {
        try {
            ActivityInfo[] activities = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            if (activities != null) {
                for (ActivityInfo activityInfo : activities) {
                    if (activityInfo.name.equals(klass.getName())) {
                        return activityInfo;
                    }
                }
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }
}
