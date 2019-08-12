package com.airbnb.android.core.models;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.payments.models.BillProductConstants;
import com.google.common.collect.FluentIterable;

public enum MagicalTripAttachmentType {
    ExperienceInquiry("MtExperienceInquiry"),
    ScheduledTemplate("MtScheduledTemplate"),
    Trip(BillProductConstants.TRIP_SERVER_KEY),
    Unknown("");
    
    private final String serverName;

    private MagicalTripAttachmentType(String serverName2) {
        this.serverName = serverName2;
    }

    public static MagicalTripAttachmentType getType(String s) {
        MagicalTripAttachmentType type = (MagicalTripAttachmentType) FluentIterable.m1283of(values()).firstMatch(MagicalTripAttachmentType$$Lambda$1.lambdaFactory$(s)).mo41059or(Unknown);
        if (type == Unknown) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown type"));
        }
        return type;
    }
}
