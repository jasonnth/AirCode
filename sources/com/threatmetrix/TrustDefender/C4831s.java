package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.facebook.internal.ServerProtocol;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.s */
class C4831s {

    /* renamed from: a */
    private static final String f4647a = C4834w.m2892a(C4831s.class);

    C4831s() {
    }

    @TargetApi(22)
    /* renamed from: a */
    static Map<String, String> m2883a(Context context) {
        Map<String, String> result = new HashMap<>();
        if (C4800a.f4363c >= 22 && C4813n.m2815f()) {
            List<String> actives = new ArrayList<>();
            SubscriptionManager subscriptionManager = SubscriptionManager.from(context);
            if (subscriptionManager != null) {
                try {
                    List<SubscriptionInfo> activeSims = subscriptionManager.getActiveSubscriptionInfoList();
                    if (activeSims != null && activeSims.size() > 0) {
                        for (SubscriptionInfo info : activeSims) {
                            int idx = info.getSimSlotIndex();
                            C4770ai.m2627a(info.getCarrierName().toString(), true, "cna" + idx, result);
                            C4770ai.m2627a(info.getDisplayName().toString(), true, "dna" + idx, result);
                            C4770ai.m2627a(String.valueOf(info.getIccId()), true, "ssa" + idx, result);
                            C4770ai.m2627a(info.getNumber(), true, "na" + idx, result);
                            C4770ai.m2627a(info.getCountryIso(), true, "ca" + idx, result);
                            result.put("ra" + idx, info.getDataRoaming() == 0 ? "disabled" : "enabled");
                            actives.add(info.getIccId() + info.getNumber());
                        }
                    }
                    Method getAllSubscribersInfo = C4787at.m2746b(SubscriptionManager.class, "getAllSubscriptionInfoList", new Class[0]);
                    if (getAllSubscribersInfo != null) {
                        Object list = C4787at.m2741a((Object) subscriptionManager, getAllSubscribersInfo, new Object[0]);
                        if (list != null && (list instanceof List)) {
                            List<SubscriptionInfo> list2 = (List) list;
                            if (activeSims == null || activeSims.size() < list2.size()) {
                                int idx2 = 0;
                                for (SubscriptionInfo info2 : list2) {
                                    if (!actives.contains(info2.getIccId() + info2.getNumber())) {
                                        C4770ai.m2627a(info2.getCarrierName().toString(), true, "cno" + idx2, result);
                                        C4770ai.m2627a(info2.getDisplayName().toString(), true, "dno" + idx2, result);
                                        C4770ai.m2627a(String.valueOf(info2.getIccId()), true, new StringBuilder(ServerProtocol.DIALOG_PARAM_SSO_DEVICE).append(idx2).toString(), result);
                                        C4770ai.m2627a(info2.getNumber(), true, "no" + idx2, result);
                                        C4770ai.m2627a(info2.getCountryIso(), true, "co" + idx2, result);
                                        result.put("ro" + idx2, info2.getDataRoaming() == 0 ? "disabled" : "enabled");
                                        idx2++;
                                    }
                                }
                            }
                        }
                    }
                } catch (SecurityException e) {
                    String str = f4647a;
                } catch (Exception e2) {
                    C4834w.m2901c(f4647a, e2.getMessage());
                }
            }
        }
        return result;
    }
}
