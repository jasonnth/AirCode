package com.appboy.services;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;

@SuppressLint({"MissingPermission"})
public class AppboyLocationService extends Service {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f2903a = AppboyLogger.getAppboyLogTag(AppboyLocationService.class);

    /* renamed from: b */
    private LocationListener f2904b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public LocationManager f2905c;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            AppboyLogger.m1737i(f2903a, "Null intent received. Initializing Appboy.");
            Appboy.getInstance(getApplicationContext());
        } else {
            String action = intent.getAction();
            if (action == null) {
                m1724a(intent);
            } else if (action.equals(getPackageName() + ".REQUEST_APPBOY_LOCATION_UPDATES")) {
                m1726b(intent);
            } else if (action.contains(getPackageName() + ".REQUEST_REMOVE_APPBOY_LOCATION_UPDATES")) {
                m1729c(intent);
            } else {
                m1730d(intent);
            }
        }
        return 1;
    }

    /* renamed from: a */
    private void m1724a(Intent intent) {
        AppboyLogger.m1739w(f2903a, "Null intent action received in Appboy location service: " + intent.getDataString());
    }

    /* renamed from: b */
    private void m1726b(Intent intent) {
        AppboyLogger.m1733d(f2903a, "Requesting background location updates: " + intent.getAction());
        if (this.f2905c == null) {
            this.f2905c = (LocationManager) getApplicationContext().getSystemService("location");
        }
        if (this.f2904b == null) {
            this.f2904b = m1728c();
        }
        float floatExtra = intent.getFloatExtra("distance", 50.0f);
        long longExtra = intent.getLongExtra("time", 3600000);
        if (this.f2904b != null) {
            try {
                this.f2905c.requestLocationUpdates("passive", longExtra, floatExtra, this.f2904b);
                AppboyLogger.m1737i(f2903a, String.format("Collecting locations using %s provider with time interval %ds and update distance %.1fm.", new Object[]{"passive", Long.valueOf(longExtra / 1000), Float.valueOf(floatExtra)}));
            } catch (SecurityException e) {
                AppboyLogger.m1740w(f2903a, "Could not request background location updates. Security exception from insufficient permissions", e);
            }
        } else {
            AppboyLogger.m1739w(f2903a, "Could not request background location updates. Appboy location listener was null.");
        }
    }

    /* renamed from: c */
    private void m1729c(Intent intent) {
        AppboyLogger.m1733d(f2903a, "Removing current location updates: " + intent.getAction());
        m1725b();
    }

    /* renamed from: d */
    private void m1730d(Intent intent) {
        AppboyLogger.m1739w(f2903a, "Unknown intent received: " + intent.getAction());
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1725b() {
        if (this.f2904b != null) {
            try {
                this.f2905c.removeUpdates(this.f2904b);
            } catch (SecurityException e) {
                AppboyLogger.m1740w(f2903a, "Could not remove background location updates. Security exception from insufficient permissions", e);
            }
        }
    }

    /* renamed from: c */
    private LocationListener m1728c() {
        return new LocationListener() {
            public void onLocationChanged(Location location) {
                if (location != null) {
                    AppboyLogger.m1733d(AppboyLocationService.f2903a, "Requesting single location update.");
                    Intent intent = new Intent(AppboyLocationService.this.getApplicationContext().getPackageName() + ".SINGLE_APPBOY_LOCATION_UPDATE");
                    intent.putExtra("location", location);
                    intent.putExtra("origin", "Appboy location service");
                    try {
                        AppboyLocationService.this.f2905c.requestSingleUpdate("passive", PendingIntent.getBroadcast(AppboyLocationService.this.getApplicationContext(), 0, intent, 134217728));
                    } catch (SecurityException e) {
                        AppboyLogger.m1740w(AppboyLocationService.f2903a, "Could not request single location update. Security exception from insufficient permissions", e);
                    }
                }
            }

            public void onStatusChanged(String provider, int status, Bundle bundle) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
                if (provider != null && provider.equals("passive")) {
                    AppboyLocationService.this.m1725b();
                }
            }
        };
    }

    public void onDestroy() {
        super.onDestroy();
        m1725b();
    }

    public static void requestInitialization(Context context) {
        AppboyLogger.m1733d(f2903a, "Location permissions were granted. Re-checking location permissions.");
        context.sendBroadcast(new Intent(context.getPackageName() + ".REQUEST_INIT_APPBOY_LOCATION_SERVICE"));
    }
}
