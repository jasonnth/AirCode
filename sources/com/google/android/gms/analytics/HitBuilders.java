package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zztc;
import com.google.android.gms.internal.zztm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HitBuilders {

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    protected static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> zzabN = new HashMap();
        ProductAction zzabO;
        Map<String, List<Product>> zzabP = new HashMap();
        List<Promotion> zzabQ = new ArrayList();
        List<Product> zzabR = new ArrayList();

        protected HitBuilder() {
        }

        private T zzo(String str, String str2) {
            if (str == null) {
                zztc.zzbh("HitBuilder.setIfNonNull() called with a null paramName.");
            } else if (str2 != null) {
                this.zzabN.put(str, str2);
            }
            return this;
        }

        public Map<String, String> build() {
            HashMap hashMap = new HashMap(this.zzabN);
            if (this.zzabO != null) {
                hashMap.putAll(this.zzabO.build());
            }
            int i = 1;
            for (Promotion zzbM : this.zzabQ) {
                hashMap.putAll(zzbM.zzbM(zzc.zzat(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzbM2 : this.zzabR) {
                hashMap.putAll(zzbM2.zzbM(zzc.zzar(i2)));
                i2++;
            }
            int i3 = 1;
            for (Entry entry : this.zzabP.entrySet()) {
                List<Product> list = (List) entry.getValue();
                String zzaw = zzc.zzaw(i3);
                int i4 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzaw);
                    String valueOf2 = String.valueOf(zzc.zzav(i4));
                    hashMap.putAll(product.zzbM(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    String valueOf3 = String.valueOf(zzaw);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry.getKey());
                }
                i3++;
            }
            return hashMap;
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.zzabN.put(str, str2);
            } else {
                zztc.zzbh("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String str) {
            String zzcg = zztm.zzcg(str);
            if (!TextUtils.isEmpty(zzcg)) {
                Map zzce = zztm.zzce(zzcg);
                zzo("&cc", (String) zzce.get("utm_content"));
                zzo("&cm", (String) zzce.get("utm_medium"));
                zzo("&cn", (String) zzce.get("utm_campaign"));
                zzo("&cs", (String) zzce.get("utm_source"));
                zzo("&ck", (String) zzce.get("utm_term"));
                zzo("&ci", (String) zzce.get("utm_id"));
                zzo("&anid", (String) zzce.get("anid"));
                zzo("&gclid", (String) zzce.get("gclid"));
                zzo("&dclid", (String) zzce.get("dclid"));
                zzo("&aclid", (String) zzce.get("aclid"));
                zzo("&gmob_t", (String) zzce.get("gmob_t"));
            }
            return this;
        }
    }
}
