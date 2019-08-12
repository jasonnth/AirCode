package com.google.android.gms.internal;

import android.os.Bundle;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.zzlx.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzlz implements zza<zzgv> {
    private final boolean zzRc;
    private final boolean zzRd;

    public zzlz(boolean z, boolean z2) {
        this.zzRc = z;
        this.zzRd = z2;
    }

    /* renamed from: zzb */
    public zzgv zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        List<zzqm> zza = zzlx.zza(jSONObject, "images", true, this.zzRc, this.zzRd);
        zzqm zza2 = zzlx.zza(jSONObject, "app_icon", true, this.zzRc);
        zzqm zzc = zzlx.zzc(jSONObject, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        zzqm zzd = zzlx.zzd(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzqm zzqm : zza) {
            arrayList.add((zzgu) zzqm.get());
        }
        zzqw zzb = zzlx.zzb(zzc);
        return new zzgv(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzhf) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble(UpdateReviewRequest.KEY_OVERALL, -1.0d), jSONObject.optString("store"), jSONObject.optString(EditPriceFragment.RESULT_PRICE), (zzgs) zzd.get(), new Bundle(), zzb != null ? zzb.zzlG() : null, zzb != null ? zzb.getView() : null);
    }
}
