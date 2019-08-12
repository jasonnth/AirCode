package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest {
    private final Date zzcR;
    private final Gender zzcS;
    private final Set<String> zzcT;
    private final boolean zzcU;
    private final Location zzcV;

    public MediationAdRequest(Date date, Gender gender, Set<String> set, boolean z, Location location) {
        this.zzcR = date;
        this.zzcS = gender;
        this.zzcT = set;
        this.zzcU = z;
        this.zzcV = location;
    }
}
