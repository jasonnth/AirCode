package com.mparticle.internal;

import android.location.Location;
import com.mparticle.MParticle;
import com.mparticle.commerce.CommerceEvent;
import com.mparticle.commerce.Impression;
import com.mparticle.commerce.Product;
import com.mparticle.commerce.Promotion;
import com.mparticle.commerce.TransactionAttributes;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.mparticle.internal.g */
public class C4609g extends JSONObject {

    /* renamed from: com.mparticle.internal.g$a */
    public static class C4611a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public final String f3751a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final Session f3752b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public CommerceEvent f3753c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public long f3754d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f3755e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public JSONObject f3756f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public Location f3757g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public String f3758h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public Double f3759i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public Map<String, List<String>> f3760j;

        public C4611a(String str, Session session, Location location) {
            this.f3753c = null;
            this.f3759i = null;
            this.f3751a = str;
            this.f3752b = new Session(session);
            this.f3757g = location;
        }

        public C4611a(CommerceEvent commerceEvent, Session session, Location location) {
            this("cm", session, location);
            this.f3753c = commerceEvent;
        }

        /* renamed from: a */
        public C4611a mo44877a(long j) {
            this.f3754d = j;
            return this;
        }

        /* renamed from: a */
        public C4611a mo44879a(String str) {
            this.f3755e = str;
            return this;
        }

        /* renamed from: a */
        public C4611a mo44881a(JSONObject jSONObject) {
            this.f3756f = jSONObject;
            return this;
        }

        /* renamed from: a */
        public C4609g mo44882a() throws JSONException {
            return new C4609g(this);
        }

        /* renamed from: b */
        public C4611a mo44883b(String str) {
            this.f3758h = str;
            return this;
        }

        /* renamed from: a */
        public C4611a mo44878a(Double d) {
            this.f3759i = d;
            return this;
        }

        /* renamed from: a */
        public C4611a mo44880a(Map<String, List<String>> map) {
            this.f3760j = map;
            return this;
        }
    }

    private C4609g() {
    }

    private C4609g(C4611a aVar) throws JSONException {
        put("dt", aVar.f3751a);
        put("ct", aVar.f3754d);
        if ("ss" == aVar.f3751a) {
            put("id", aVar.f3752b.f3728b);
        } else {
            put("sid", aVar.f3752b.f3728b);
            if (aVar.f3752b.f3729c > 0) {
                put("sct", aVar.f3752b.f3729c);
            }
        }
        if (aVar.f3755e != null) {
            put("n", aVar.f3755e);
        }
        if (aVar.f3760j != null) {
            JSONObject jSONObject = new JSONObject();
            for (Entry entry : aVar.f3760j.entrySet()) {
                jSONObject.put((String) entry.getKey(), new JSONArray((List) entry.getValue()));
            }
            put("flags", jSONObject);
        }
        if (aVar.f3759i != null) {
            put("el", aVar.f3759i);
            if (aVar.f3756f == null) {
                aVar.f3756f = new JSONObject();
            }
            if (!aVar.f3756f.has("EventLength")) {
                aVar.f3756f.put("EventLength", Integer.toString(aVar.f3759i.intValue()));
            }
        }
        if (aVar.f3756f != null) {
            put("attrs", aVar.f3756f);
        }
        if (aVar.f3758h != null) {
            put("dct", aVar.f3758h);
        }
        if ((!"x".equals(aVar.f3751a) || "o".equals(aVar.f3751a)) && aVar.f3757g != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lat", aVar.f3757g.getLatitude());
            jSONObject2.put("lng", aVar.f3757g.getLongitude());
            jSONObject2.put("acc", (double) aVar.f3757g.getAccuracy());
            put("lc", jSONObject2);
        }
        if (aVar.f3753c != null) {
            m2235a(this, aVar.f3753c);
        }
    }

    /* renamed from: a */
    public static void m2235a(C4609g gVar, CommerceEvent commerceEvent) {
        try {
            if (commerceEvent.getScreen() != null) {
                gVar.put("sn", commerceEvent.getScreen());
            }
            if (commerceEvent.getNonInteraction() != null) {
                gVar.put("ni", commerceEvent.getNonInteraction().booleanValue());
            }
            if (commerceEvent.getCurrency() != null) {
                gVar.put("cu", commerceEvent.getCurrency());
            }
            if (commerceEvent.getCustomAttributes() != null) {
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : commerceEvent.getCustomAttributes().entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
                gVar.put("attrs", jSONObject);
            }
            if (commerceEvent.getProductAction() != null) {
                JSONObject jSONObject2 = new JSONObject();
                gVar.put("pd", jSONObject2);
                jSONObject2.put("an", commerceEvent.getProductAction());
                if (commerceEvent.getCheckoutStep() != null) {
                    jSONObject2.put("cs", commerceEvent.getCheckoutStep());
                }
                if (commerceEvent.getCheckoutOptions() != null) {
                    jSONObject2.put("co", commerceEvent.getCheckoutOptions());
                }
                if (commerceEvent.getProductListName() != null) {
                    jSONObject2.put("pal", commerceEvent.getProductListName());
                }
                if (commerceEvent.getProductListSource() != null) {
                    jSONObject2.put("pls", commerceEvent.getProductListSource());
                }
                if (commerceEvent.getTransactionAttributes() != null) {
                    TransactionAttributes transactionAttributes = commerceEvent.getTransactionAttributes();
                    if (transactionAttributes.getId() != null) {
                        jSONObject2.put("ti", transactionAttributes.getId());
                    }
                    if (transactionAttributes.getAffiliation() != null) {
                        jSONObject2.put("ta", transactionAttributes.getAffiliation());
                    }
                    if (transactionAttributes.getRevenue() != null) {
                        jSONObject2.put("tr", transactionAttributes.getRevenue());
                    }
                    if (transactionAttributes.getTax() != null) {
                        jSONObject2.put("tt", transactionAttributes.getTax());
                    }
                    if (transactionAttributes.getShipping() != null) {
                        jSONObject2.put("ts", transactionAttributes.getShipping());
                    }
                    if (transactionAttributes.getCouponCode() != null) {
                        jSONObject2.put("tcc", transactionAttributes.getCouponCode());
                    }
                }
                if (commerceEvent.getProducts() != null && commerceEvent.getProducts().size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < commerceEvent.getProducts().size(); i++) {
                        jSONArray.put(new JSONObject(((Product) commerceEvent.getProducts().get(i)).toString()));
                    }
                    jSONObject2.put("pl", jSONArray);
                }
            }
            if (commerceEvent.getPromotionAction() != null) {
                JSONObject jSONObject3 = new JSONObject();
                gVar.put("pm", jSONObject3);
                jSONObject3.put("an", commerceEvent.getPromotionAction());
                if (commerceEvent.getPromotions() != null && commerceEvent.getPromotions().size() > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < commerceEvent.getPromotions().size(); i2++) {
                        jSONArray2.put(m2234a((Promotion) commerceEvent.getPromotions().get(i2)));
                    }
                    jSONObject3.put("pl", jSONArray2);
                }
            }
            if (commerceEvent.getImpressions() != null && commerceEvent.getImpressions().size() > 0) {
                JSONArray jSONArray3 = new JSONArray();
                for (Impression impression : commerceEvent.getImpressions()) {
                    JSONObject jSONObject4 = new JSONObject();
                    if (impression.getListName() != null) {
                        jSONObject4.put("pil", impression.getListName());
                    }
                    if (impression.getProducts() != null && impression.getProducts().size() > 0) {
                        JSONArray jSONArray4 = new JSONArray();
                        jSONObject4.put("pl", jSONArray4);
                        for (Product product : impression.getProducts()) {
                            jSONArray4.put(new JSONObject(product.toString()));
                        }
                    }
                    if (jSONObject4.length() > 0) {
                        jSONArray3.put(jSONObject4);
                    }
                }
                if (jSONArray3.length() > 0) {
                    gVar.put("pi", jSONArray3);
                }
            }
            JSONObject jSONObject5 = new JSONObject(MParticle.getInstance().Commerce().cart().toString());
            if (jSONObject5.length() > 0) {
                gVar.put("sc", jSONObject5);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static JSONObject m2234a(Promotion promotion) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!MPUtility.isEmpty(promotion.getId())) {
                jSONObject.put("id", promotion.getId());
            }
            if (!MPUtility.isEmpty(promotion.getName())) {
                jSONObject.put("nm", promotion.getName());
            }
            if (!MPUtility.isEmpty(promotion.getCreative())) {
                jSONObject.put("cr", promotion.getCreative());
            }
            if (!MPUtility.isEmpty(promotion.getPosition())) {
                jSONObject.put("ps", promotion.getPosition());
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public long mo44871a() {
        try {
            return getLong("ct");
        } catch (JSONException e) {
            return 0;
        }
    }

    /* renamed from: b */
    public String mo44872b() {
        if ("ss".equals(mo44873c())) {
            return optString("id", "NO-SESSION");
        }
        return optString("sid", "NO-SESSION");
    }

    /* renamed from: c */
    public String mo44873c() {
        return optString("dt");
    }

    /* renamed from: d */
    public int mo44874d() {
        return MPUtility.mpHash(mo44875e() + mo44876f());
    }

    /* renamed from: e */
    public String mo44875e() {
        return optString("dt");
    }

    /* renamed from: f */
    public String mo44876f() {
        return optString("n");
    }
}
