package com.airbnb.android.core.events;

import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;

public class ProfileUpdatedEvent {
    private final ProfileSection mSection;

    public ProfileUpdatedEvent(ProfileSection section) {
        this.mSection = section;
    }

    public ProfileSection getSection() {
        return this.mSection;
    }
}
