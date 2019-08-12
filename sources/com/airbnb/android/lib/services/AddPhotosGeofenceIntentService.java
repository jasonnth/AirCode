package com.airbnb.android.lib.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.app.NotificationCompat;
import android.support.p002v7.app.NotificationCompat.Builder;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.listyourspace.LYSAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;

public class AddPhotosGeofenceIntentService extends IntentService {
    public AddPhotosGeofenceIntentService() {
        super(AddPhotosGeofenceIntentService.class.getSimpleName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv("action", "photo_geofence_failure").mo11637kv("error_code", geofencingEvent.getErrorCode()));
            return;
        }
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        if (geofenceTransition == 1 || geofenceTransition == 4) {
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            if (triggeringGeofences != null && !triggeringGeofences.isEmpty()) {
                showNotification((Geofence) triggeringGeofences.get(0));
            }
        }
    }

    private void showNotification(Geofence geofence) {
        long listingId = Long.valueOf(geofence.getRequestId()).longValue();
        ((NotificationManager) getSystemService("notification")).notify(AirbnbConstants.TAKE_PHOTOS_NOTIFICATION_ID, new Builder(this).setColor(getResources().getColor(C0880R.color.c_rausch)).setAutoCancel(true).setDefaults(1).setCategory(NotificationCompat.CATEGORY_MESSAGE).setSmallIcon(C0880R.C0881drawable.ic_stat_notify).setContentTitle(getString(C0880R.string.push_notification_title_add_photos)).setTicker(getString(C0880R.string.push_notification_title_add_photos)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent("android.intent.action.VIEW", Uri.parse("airbnb://d/managelistings?step=photos&listing_id=" + listingId)), 0)).build());
        LYSAnalytics.trackPhotoAction(listingId, "add_photos_notification_displayed");
    }
}
