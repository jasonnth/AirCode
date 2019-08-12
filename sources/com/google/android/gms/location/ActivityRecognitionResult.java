package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;
import java.util.List;
import org.spongycastle.asn1.eac.EACTags;

public class ActivityRecognitionResult extends zza implements ReflectedParcelable {
    public static final Creator<ActivityRecognitionResult> CREATOR = new zzc();
    Bundle extras;
    List<DetectedActivity> zzbjp;
    long zzbjq;
    long zzbjr;
    int zzbjs;

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this(Collections.singletonList(detectedActivity), j, j2, i, bundle);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2, int i, Bundle bundle) {
        boolean z = true;
        zzac.zzb(list != null && list.size() > 0, (Object) "Must have at least 1 detected activity");
        if (j <= 0 || j2 <= 0) {
            z = false;
        }
        zzac.zzb(z, (Object) "Must set times");
        this.zzbjp = list;
        this.zzbjq = j;
        this.zzbjr = j2;
        this.zzbjs = i;
        this.extras = bundle;
    }

    public static ActivityRecognitionResult extractResult(Intent intent) {
        ActivityRecognitionResult zzu = zzu(intent);
        if (zzu != null) {
            return zzu;
        }
        List zzw = zzw(intent);
        if (zzw == null || zzw.isEmpty()) {
            return null;
        }
        return (ActivityRecognitionResult) zzw.get(zzw.size() - 1);
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (zzt(intent)) {
            return true;
        }
        List zzw = zzw(intent);
        return zzw != null && !zzw.isEmpty();
    }

    private static boolean zzc(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle == null && bundle2 != null) || (bundle != null && bundle2 == null)) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            if (bundle.get(str) == null) {
                if (bundle2.get(str) != null) {
                    return false;
                }
            } else if (bundle.get(str) instanceof Bundle) {
                if (!zzc(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else if (!bundle.get(str).equals(bundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzt(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    }

    private static ActivityRecognitionResult zzu(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        Object obj = intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
        if (obj instanceof byte[]) {
            return (ActivityRecognitionResult) zzd.zza((byte[]) obj, CREATOR);
        }
        if (obj instanceof ActivityRecognitionResult) {
            return (ActivityRecognitionResult) obj;
        }
        return null;
    }

    public static boolean zzv(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST");
    }

    public static List<ActivityRecognitionResult> zzw(Intent intent) {
        if (!zzv(intent)) {
            return null;
        }
        return zzd.zzb(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.zzbjq == activityRecognitionResult.zzbjq && this.zzbjr == activityRecognitionResult.zzbjr && this.zzbjs == activityRecognitionResult.zzbjs && zzaa.equal(this.zzbjp, activityRecognitionResult.zzbjp) && zzc(this.extras, activityRecognitionResult.extras);
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.zzbjp) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzbjr;
    }

    public DetectedActivity getMostProbableActivity() {
        return (DetectedActivity) this.zzbjp.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zzbjp;
    }

    public long getTime() {
        return this.zzbjq;
    }

    public int hashCode() {
        return zzaa.hashCode(Long.valueOf(this.zzbjq), Long.valueOf(this.zzbjr), Integer.valueOf(this.zzbjs), this.zzbjp, this.extras);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zzbjp);
        long j = this.zzbjq;
        return new StringBuilder(String.valueOf(valueOf).length() + EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE).append("ActivityRecognitionResult [probableActivities=").append(valueOf).append(", timeMillis=").append(j).append(", elapsedRealtimeMillis=").append(this.zzbjr).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
