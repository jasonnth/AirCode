package com.airbnb.android.core.enums;

public enum CancellationPolicyLabel {
    Flexible("flexible"),
    Moderate("moderate"),
    Strict("strict"),
    SuperStrict30("super_strict_30"),
    SuperStrict60("super_strict_60"),
    NoRefunds("no_refunds"),
    LongTerm("long_term"),
    FlexibleNew("flexible_new"),
    ModerateNew("moderate_new"),
    StrictNew("strict_new"),
    SuperStrict30New("super_strict_30_new"),
    SuperStrict60New("super_strict_60_new"),
    LongTermNew("long_term_new");
    
    private final String serverKey;

    private CancellationPolicyLabel(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public static CancellationPolicyLabel fromServerKey(String serverKey2) {
        CancellationPolicyLabel[] values;
        for (CancellationPolicyLabel type : values()) {
            if (type.serverKey.equals(serverKey2)) {
                return type;
            }
        }
        return null;
    }
}
