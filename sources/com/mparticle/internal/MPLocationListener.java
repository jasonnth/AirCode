package com.mparticle.internal;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.mparticle.MParticle;

public final class MPLocationListener implements LocationListener {

    /* renamed from: a */
    private final MParticle f3695a;

    public MPLocationListener(MParticle mParticle) {
        this.f3695a = mParticle;
    }

    public void onLocationChanged(Location location) {
        if (this.f3695a != null) {
            this.f3695a.setLocation(location);
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
