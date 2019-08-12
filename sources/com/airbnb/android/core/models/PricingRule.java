package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPricingRule;

public class PricingRule extends GenPricingRule {
    public static final Creator<PricingRule> CREATOR = new Creator<PricingRule>() {
        public PricingRule[] newArray(int size) {
            return new PricingRule[size];
        }

        public PricingRule createFromParcel(Parcel source) {
            PricingRule object = new PricingRule();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum PriceChangeType {
        Absolute("ABSOLUTE"),
        Percent("PERCENT");
        
        /* access modifiers changed from: private */
        public final String serverKey;

        private PriceChangeType(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public static PriceChangeType fromServerKey(String serverKey2) {
            PriceChangeType[] values;
            for (PriceChangeType type : values()) {
                if (type.serverKey.equals(serverKey2)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum RuleType {
        LengthOfStay("STAYED_AT_LEAST_X_DAYS"),
        EarlyBird("BOOKED_BEYOND_AT_LEAST_X_DAYS"),
        LastMinute("BOOKED_WITHIN_AT_MOST_X_DAYS");
        
        /* access modifiers changed from: private */
        public final String serverKey;

        private RuleType(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public static RuleType fromServerKey(String serverKey2) {
            RuleType[] values;
            for (RuleType type : values()) {
                if (type.serverKey.equals(serverKey2)) {
                    return type;
                }
            }
            return null;
        }
    }

    private PricingRule() {
    }

    public PricingRule(RuleType ruleType, int threshold, int priceChange, PriceChangeType priceChangeType) {
        setRuleType(ruleType.serverKey);
        setThresholdOne(Integer.valueOf(threshold));
        setPriceChange(Integer.valueOf(priceChange));
        setPriceChangeType(priceChangeType.serverKey);
    }
}
