package com.mparticle.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import com.mparticle.MPEvent;
import com.mparticle.MParticle.IdentityType;
import com.mparticle.commerce.CommerceEvent;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public interface KitManager {
    void checkForDeepLink();

    String getActiveModuleIds();

    WeakReference<Activity> getCurrentActivity();

    Object getKitInstance(int i);

    Set<Integer> getSupportedKits();

    Uri getSurveyUrl(int i, Map<String, String> map, Map<String, List<String>> map2);

    boolean isKitActive(int i);

    void leaveBreadcrumb(String str);

    void logCommerceEvent(CommerceEvent commerceEvent);

    void logError(String str, Map<String, String> map);

    void logEvent(MPEvent mPEvent);

    void logException(Exception exc, Map<String, String> map, String str);

    void logNetworkPerformance(String str, long j, String str2, long j2, long j3, long j4, String str3, int i);

    void logScreen(MPEvent mPEvent);

    void logout();

    void onActivityCreated(Activity activity, Bundle bundle);

    void onActivityDestroyed(Activity activity);

    void onActivityPaused(Activity activity);

    void onActivityResumed(Activity activity);

    void onActivitySaveInstanceState(Activity activity, Bundle bundle);

    void onActivityStarted(Activity activity);

    void onActivityStopped(Activity activity);

    boolean onMessageReceived(Context context, Intent intent);

    boolean onPushRegistration(String str, String str2);

    void removeUserAttribute(String str);

    void removeUserIdentity(IdentityType identityType);

    void setLocation(Location location);

    void setOptOut(boolean z);

    void setUserAttribute(String str, String str2);

    void setUserAttributeList(String str, List<String> list);

    void setUserIdentity(String str, IdentityType identityType);

    void updateKits(JSONArray jSONArray);
}
