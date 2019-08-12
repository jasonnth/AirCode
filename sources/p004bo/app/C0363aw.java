package p004bo.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.services.AppboyLocationService;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;

@SuppressLint({"MissingPermission"})
/* renamed from: bo.app.aw */
public final class C0363aw implements C0379bh {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f111a = AppboyLogger.getAppboyLogTag(C0363aw.class);

    /* renamed from: b */
    private final Context f112b;

    /* renamed from: c */
    private final String f113c;

    /* renamed from: d */
    private final LocationManager f114d;

    /* renamed from: e */
    private final C0375bd f115e;

    /* renamed from: f */
    private final boolean f116f;

    /* renamed from: g */
    private final boolean f117g;

    /* renamed from: h */
    private boolean f118h = false;

    /* renamed from: i */
    private long f119i = 3600000;

    /* renamed from: j */
    private float f120j = 50.0f;

    /* renamed from: k */
    private String f121k;

    public C0363aw(Context context, C0375bd bdVar, AppboyConfigurationProvider appboyConfigurationProvider, C0448dk dkVar) {
        this.f112b = context;
        this.f113c = context.getPackageName();
        this.f115e = bdVar;
        this.f114d = (LocationManager) context.getSystemService("location");
        this.f116f = m81a(appboyConfigurationProvider);
        this.f118h = m83b(appboyConfigurationProvider, dkVar);
        this.f117g = m86e();
        m80a(appboyConfigurationProvider, dkVar);
        C03641 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    AppboyLogger.m1735e(C0363aw.f111a, "Location broadcast receiver received null intent.");
                    return;
                }
                String action = intent.getAction();
                if (action.endsWith(".SINGLE_APPBOY_LOCATION_UPDATE")) {
                    C0363aw.this.m78a(intent);
                } else if (action.endsWith(".REQUEST_INIT_APPBOY_LOCATION_SERVICE")) {
                    C0363aw.this.mo6759b();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(this.f113c + ".SINGLE_APPBOY_LOCATION_UPDATE");
        intentFilter.addAction(this.f113c + ".REQUEST_INIT_APPBOY_LOCATION_SERVICE");
        this.f112b.registerReceiver(r0, intentFilter);
        if (!PermissionUtils.hasPermission(this.f112b, "android.permission.ACCESS_FINE_LOCATION")) {
            m85d();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m78a(Intent intent) {
        try {
            AppboyLogger.m1737i(f111a, String.format("Single location update received from %s: %s", new Object[]{intent.getStringExtra("origin"), intent.getAction()}));
            Location location = (Location) intent.getExtras().get("location");
            if (location != null) {
                mo6758a(new C0403cd(location.getLatitude(), location.getLongitude(), Double.valueOf(location.getAltitude()), Double.valueOf((double) location.getAccuracy())));
            } else {
                AppboyLogger.m1739w(f111a, "Failed to process location update. Received location was null.");
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(f111a, "Failed to process location update.", e);
        }
    }

    /* renamed from: a */
    private boolean m82a(String str) {
        if (!this.f117g) {
            AppboyLogger.m1737i(f111a, String.format("Appboy Location service is not available. Did not send intent to service: %s", new Object[]{str}));
            return false;
        }
        Intent intent = new Intent(str).setClass(this.f112b, AppboyLocationService.class);
        if (str.equals(this.f113c + ".REQUEST_APPBOY_LOCATION_UPDATES")) {
            intent.putExtra("distance", this.f120j);
            intent.putExtra("time", this.f119i);
        }
        this.f112b.startService(intent);
        return true;
    }

    /* renamed from: d */
    private void m85d() {
        if (!this.f117g) {
            AppboyLogger.m1737i(f111a, "Did not attempt to stop service. Appboy Location service is not available.");
            return;
        }
        AppboyLogger.m1737i(f111a, "Stopping Appboy location service if currently running.");
        this.f112b.stopService(new Intent().setClass(this.f112b, AppboyLocationService.class));
    }

    /* renamed from: e */
    private boolean m86e() {
        if (C0462du.m536a(this.f112b, AppboyLocationService.class)) {
            return true;
        }
        AppboyLogger.m1737i(f111a, "Appboy location service is not available. Declare <service android:name=\"com.appboy.services.AppboyLocationService\"/> in your AndroidManifest.xml to enable Appboy location service.");
        return false;
    }

    /* renamed from: a */
    public boolean mo6758a(C0403cd cdVar) {
        try {
            this.f115e.mo6769a((C0386bo) C0397bz.m278a(cdVar));
            return true;
        } catch (Exception e) {
            AppboyLogger.m1740w(f111a, "Failed to log location recorded event.", e);
            return false;
        }
    }

    @TargetApi(9)
    /* renamed from: a */
    public boolean mo6757a() {
        String f;
        if (!this.f116f) {
            AppboyLogger.m1737i(f111a, "Did not request single location update. Location collection is disabled.");
            return false;
        } else if (PermissionUtils.hasPermission(this.f112b, "android.permission.ACCESS_FINE_LOCATION") || PermissionUtils.hasPermission(this.f112b, "android.permission.ACCESS_COARSE_LOCATION")) {
            if (PermissionUtils.hasPermission(this.f112b, "android.permission.ACCESS_FINE_LOCATION")) {
                f = "passive";
            } else {
                f = m87f();
            }
            if (StringUtils.isNullOrBlank(f)) {
                AppboyLogger.m1733d(f111a, "Could not request single location update. Android location provider not found.");
                return false;
            }
            try {
                AppboyLogger.m1733d(f111a, "Requesting single location update.");
                Intent intent = new Intent(this.f113c + ".SINGLE_APPBOY_LOCATION_UPDATE");
                intent.putExtra("origin", "Appboy location manager");
                this.f114d.requestSingleUpdate(f, PendingIntent.getBroadcast(this.f112b, 0, intent, 134217728));
                return true;
            } catch (SecurityException e) {
                AppboyLogger.m1740w(f111a, "Failed to request single location update due to security exception from insufficient permissions.", e);
                return false;
            } catch (Exception e2) {
                AppboyLogger.m1740w(f111a, "Failed to request single location update due to exception.", e2);
                return false;
            }
        } else {
            AppboyLogger.m1737i(f111a, "Did not request single location update. Fine grained location permissions not found.");
            return false;
        }
    }

    /* renamed from: a */
    public void mo6756a(C0394bw bwVar) {
        if (bwVar == null) {
            AppboyLogger.m1739w(f111a, "Could not reset background location collection interval. Server config was null.");
            return;
        }
        if (bwVar.mo6873h() >= 0) {
            this.f119i = bwVar.mo6873h();
            AppboyLogger.m1737i(f111a, "Time interval override reset via server configuration for background location collection: " + (this.f119i / 1000) + "s.");
        }
        if (bwVar.mo6874i() >= 0.0f) {
            this.f120j = bwVar.mo6874i();
            AppboyLogger.m1737i(f111a, "Distance threshold override reset via server configuration for background location collection: " + this.f120j + "m.");
        }
        if (!bwVar.mo6872g()) {
            return;
        }
        if (bwVar.mo6871f()) {
            this.f118h = true;
            AppboyLogger.m1737i(f111a, "Background location collection enabled via server configuration. Requesting location updates.");
            mo6759b();
            return;
        }
        this.f118h = false;
        AppboyLogger.m1737i(f111a, "Background location collection disabled via server configuration. Stopping any active Appboy location service.");
        m85d();
    }

    /* renamed from: b */
    public boolean mo6759b() {
        boolean z = false;
        if (!this.f116f) {
            AppboyLogger.m1737i(f111a, "Did not request background location updates. Location collection is disabled.");
            return z;
        } else if (!this.f118h) {
            AppboyLogger.m1737i(f111a, "Did not request background location updates. Background location collection is disabled.");
            return z;
        } else if (!PermissionUtils.hasPermission(this.f112b, "android.permission.ACCESS_FINE_LOCATION")) {
            AppboyLogger.m1737i(f111a, "Did not request background location updates. Fine grained location permissions not found.");
            return z;
        } else {
            try {
                m82a(this.f113c + ".REQUEST_REMOVE_APPBOY_LOCATION_UPDATES");
                return m82a(this.f113c + ".REQUEST_APPBOY_LOCATION_UPDATES");
            } catch (Exception e) {
                AppboyLogger.m1740w(f111a, "Could not request location updates due to exception.", e);
                return z;
            }
        }
    }

    /* renamed from: f */
    private String m87f() {
        if (this.f121k != null) {
            return this.f121k;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(2);
        criteria.setPowerRequirement(1);
        this.f121k = this.f114d.getBestProvider(criteria, true);
        return this.f121k;
    }

    /* renamed from: a */
    private void m80a(AppboyConfigurationProvider appboyConfigurationProvider, C0448dk dkVar) {
        if (dkVar.mo6970d() >= 0) {
            this.f119i = dkVar.mo6970d();
            AppboyLogger.m1737i(f111a, "Time interval override set via server configuration for background location collection: " + (this.f119i / 1000) + "s.");
        } else if (appboyConfigurationProvider.getLocationUpdateTimeIntervalInMillis() > 300000) {
            this.f119i = appboyConfigurationProvider.getLocationUpdateTimeIntervalInMillis();
            AppboyLogger.m1737i(f111a, "Time interval override set via appboy configuration for background location collection: " + (this.f119i / 1000) + "s.");
        } else {
            this.f119i = 3600000;
            AppboyLogger.m1737i(f111a, "Time interval override set to default for background location collection: " + (this.f119i / 1000) + "s.");
        }
        if (dkVar.mo6971e() >= 0.0f) {
            this.f120j = dkVar.mo6971e();
            AppboyLogger.m1737i(f111a, "Distance threshold override set via server configuration for background location collection: " + this.f120j + "m.");
        } else if (appboyConfigurationProvider.getLocationUpdateDistanceInMeters() > 50.0f) {
            this.f120j = appboyConfigurationProvider.getLocationUpdateDistanceInMeters();
            AppboyLogger.m1737i(f111a, "Distance threshold override set via appboy configuration for background location collection: " + this.f120j + "m.");
        } else {
            this.f120j = 50.0f;
            AppboyLogger.m1737i(f111a, "Distance threshold override set to default for background location collection: " + this.f120j + "m.");
        }
    }

    /* renamed from: b */
    private boolean m83b(AppboyConfigurationProvider appboyConfigurationProvider, C0448dk dkVar) {
        if (dkVar.mo6968b()) {
            if (dkVar.mo6969c()) {
                AppboyLogger.m1737i(f111a, "Background location collection enabled via server configuration.");
                return true;
            }
            AppboyLogger.m1737i(f111a, "Background location collection disabled via server configuration.");
            return false;
        } else if (appboyConfigurationProvider.isBackgroundLocationCollectionEnabled()) {
            AppboyLogger.m1737i(f111a, "Background location collection enabled via appboy.xml configuration.");
            return true;
        } else {
            AppboyLogger.m1737i(f111a, "Background location collection disabled via appboy.xml configuration.");
            return false;
        }
    }

    /* renamed from: a */
    private boolean m81a(AppboyConfigurationProvider appboyConfigurationProvider) {
        if (appboyConfigurationProvider.isLocationCollectionEnabled()) {
            AppboyLogger.m1737i(f111a, "Location collection enabled via appboy.xml configuration.");
            return true;
        }
        AppboyLogger.m1737i(f111a, "Location collection disabled via appboy.xml configuration.");
        return false;
    }
}
