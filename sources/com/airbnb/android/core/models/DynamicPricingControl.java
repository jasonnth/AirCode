package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.models.generated.GenDynamicPricingControl;
import com.airbnb.jitney.event.logging.SmartPricingSettingsContext.p259v1.C2744SmartPricingSettingsContext;
import com.airbnb.jitney.event.logging.SmartPricingSettingsContext.p259v1.C2744SmartPricingSettingsContext.Builder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DynamicPricingControl extends GenDynamicPricingControl {
    public static final Creator<DynamicPricingControl> CREATOR = new Creator<DynamicPricingControl>() {
        public DynamicPricingControl[] newArray(int size) {
            return new DynamicPricingControl[size];
        }

        public DynamicPricingControl createFromParcel(Parcel source) {
            DynamicPricingControl object = new DynamicPricingControl();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum DesiredHostingFrequency {
        OftenAsPossible(0, C0716R.string.manage_listing_hosting_frequency_as_often_as_possible_title, C0716R.string.manage_listing_hosting_frequency_as_often_as_possible_info),
        Frequently(1, C0716R.string.manage_listing_hosting_frequency_frequently_title, C0716R.string.manage_listing_hosting_frequency_frequently_info),
        Occasionally(2, C0716R.string.manage_listing_hosting_frequency_occasionally_title, C0716R.string.manage_listing_hosting_frequency_occasionally_info),
        PartTime(1, C0716R.string.manage_listing_hosting_frequency_part_time_title, C0716R.string.manage_listing_hosting_frequency_part_time_info);
        
        private final int descriptionStringId;
        private final int serverKey;
        private final int titleStringId;

        public static DesiredHostingFrequency[] twoOptionValues() {
            return new DesiredHostingFrequency[]{OftenAsPossible, PartTime};
        }

        public static DesiredHostingFrequency[] threeOptionValues() {
            return new DesiredHostingFrequency[]{OftenAsPossible, Frequently, Occasionally};
        }

        public static List<DesiredHostingFrequency> valuesForFrequencyVersion(DesiredHostingFrequencyVersion version) {
            switch (version) {
                case ThreeOptions:
                    return Arrays.asList(threeOptionValues());
                case TwoOptions:
                    return Arrays.asList(twoOptionValues());
                default:
                    BugsnagWrapper.notify((Throwable) new UnhandledStateException(version));
                    return Collections.emptyList();
            }
        }

        private DesiredHostingFrequency(int serverKey2, int titleStringId2, int descriptionStringId2) {
            this.serverKey = serverKey2;
            this.titleStringId = titleStringId2;
            this.descriptionStringId = descriptionStringId2;
        }

        public static DesiredHostingFrequency forServerKeyAndVersion(Integer key, DesiredHostingFrequencyVersion version) {
            if (key == null) {
                return null;
            }
            for (DesiredHostingFrequency frequency : valuesForFrequencyVersion(version)) {
                if (frequency.serverKey == key.intValue()) {
                    return frequency;
                }
            }
            return null;
        }

        public int getServerKey() {
            return this.serverKey;
        }

        public int getTitleStringId() {
            return this.titleStringId;
        }

        public int getDescriptionStringId() {
            return this.descriptionStringId;
        }
    }

    public enum DesiredHostingFrequencyVersion {
        Unhandled(-1),
        ThreeOptions(0),
        TwoOptions(1);
        
        private final int serverKey;

        private DesiredHostingFrequencyVersion(int serverKey2) {
            this.serverKey = serverKey2;
        }

        public static DesiredHostingFrequencyVersion forServerKey(int key) {
            DesiredHostingFrequencyVersion[] values;
            for (DesiredHostingFrequencyVersion version : values()) {
                if (version.serverKey == key) {
                    return version;
                }
            }
            return Unhandled;
        }
    }

    public boolean canBeEnabled() {
        return (getMaxPrice() == 0 || getMinPrice() == 0 || getDesiredHostingFrequency() == null) ? false : true;
    }

    public DesiredHostingFrequency getDesiredHostingFrequency() {
        return DesiredHostingFrequency.forServerKeyAndVersion(getDesiredHostingFrequencyKey(), getDesiredHostingFrequencyVersion());
    }

    public void setDesiredHostingFrequency(DesiredHostingFrequency frequency) {
        setDesiredHostingFrequencyKey(Integer.valueOf(frequency.getServerKey()));
    }

    public DesiredHostingFrequencyVersion getDesiredHostingFrequencyVersion() {
        return DesiredHostingFrequencyVersion.forServerKey(getHostingFrequencyVersionKey());
    }

    public boolean hasUsedDemandBasedPricing() {
        return getLastEnabledAt() != null;
    }

    public C2744SmartPricingSettingsContext toSmartPricingSettingsContext() {
        return new Builder(Long.valueOf((long) this.mMinPrice), Long.valueOf((long) this.mMaxPrice), Long.valueOf(this.mDesiredHostingFrequencyKey == null ? -1 : this.mDesiredHostingFrequencyKey.longValue()), Boolean.valueOf(this.mIsEnabled)).build();
    }
}
