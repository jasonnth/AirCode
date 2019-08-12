package com.airbnb.android.core.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.AccountVerification;
import com.facebook.places.model.PlaceFields;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public interface EditProfileInterface {

    public enum Gender implements Parcelable {
        Male(C0716R.string.edit_profile_gender_male, "Male"),
        Female(C0716R.string.edit_profile_gender_female, "Female"),
        Other(C0716R.string.edit_profile_gender_other, "Other");
        
        public static final Creator<Gender> CREATOR = null;
        private static final Map<String, Gender> mMap = null;
        private final int mDisplayId;
        private final String mJsonValue;

        static {
            int i;
            Gender[] values;
            mMap = new HashMap(values().length);
            for (Gender status : values()) {
                mMap.put(status.getJsonValue(), status);
            }
            CREATOR = new Creator<Gender>() {
                public Gender createFromParcel(Parcel source) {
                    return Gender.values()[source.readInt()];
                }

                public Gender[] newArray(int size) {
                    return new Gender[size];
                }
            };
        }

        private Gender(int displayId, String jsonValue) {
            this.mDisplayId = displayId;
            this.mJsonValue = jsonValue;
        }

        public static Gender findGender(String value) {
            if (TextUtils.isEmpty(value)) {
                return null;
            }
            return (Gender) mMap.get(value);
        }

        public int getDisplayId() {
            return this.mDisplayId;
        }

        public String getJsonValue() {
            return this.mJsonValue;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    }

    public enum ProfileSection implements Parcelable {
        FirstName(C0716R.string.first_name, 0, 0, CohostingConstants.FIRST_NAME_FIELD),
        LastName(C0716R.string.last_name, 0, 0, "last_name"),
        Name(C0716R.string.edit_profile_name, 0, 0, "name"),
        About(C0716R.string.edit_profile_edit_about_me, 0, C0716R.string.edit_profile_about_description, PlaceFields.ABOUT),
        Gender(C0716R.string.edit_profile_gender, 0, C0716R.string.edit_profile_gender_description, "gender"),
        BirthDate(C0716R.string.edit_profile_birth_date, 0, C0716R.string.edit_profile_birth_date_description, "birthdate"),
        Email(C0716R.string.edit_profile_email, 0, C0716R.string.edit_profile_email_description, "email"),
        Phone(C0716R.string.edit_profile_phone, 0, C0716R.string.edit_profile_phone_description, "phone"),
        GovernmentID(C0716R.string.government_id, 0, 0, AccountVerification.SCANID),
        Live(C0716R.string.edit_profile_live, C0716R.string.edit_profile_live_hint, 0, "location"),
        School(C0716R.string.edit_profile_school, C0716R.string.edit_profile_school_hint, 0, "school"),
        Work(C0716R.string.edit_profile_work, C0716R.string.edit_profile_work_hint, 0, "work"),
        Languages(C0716R.string.edit_profile_languages, C0716R.string.edit_profile_languages_hint, 0, "languages"),
        TimeZone(C0716R.string.edit_profile_time_zone, C0716R.string.edit_profile_time_zone_hint, 0, "timezone");
        
        public static final Creator<ProfileSection> CREATOR = null;
        public static final EnumSet<ProfileSection> OPTIONAL_DETAILS = null;
        public static final EnumSet<ProfileSection> PRIVATE_DETAILS = null;
        private final int mDescriptionId;
        private final int mHintId;
        private final String mJsonKey;
        private final int mTitleId;

        static {
            PRIVATE_DETAILS = EnumSet.range(Gender, GovernmentID);
            OPTIONAL_DETAILS = EnumSet.range(Live, Languages);
            CREATOR = new Creator<ProfileSection>() {
                public ProfileSection createFromParcel(Parcel source) {
                    return ProfileSection.values()[source.readInt()];
                }

                public ProfileSection[] newArray(int size) {
                    return new ProfileSection[size];
                }
            };
        }

        private ProfileSection(int title, int hint, int description, String jsonKey) {
            this.mTitleId = title;
            this.mHintId = hint;
            this.mDescriptionId = description;
            this.mJsonKey = jsonKey;
        }

        public int getTitleId() {
            return this.mTitleId;
        }

        public int getHintId() {
            return this.mHintId;
        }

        public int getDescriptionId() {
            return this.mDescriptionId;
        }

        public boolean hasHint() {
            return this.mHintId > 0;
        }

        public boolean hasDescription() {
            return this.mDescriptionId > 0;
        }

        public String getJsonKey() {
            return this.mJsonKey;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    }

    void doneEdit(ProfileSection profileSection, Object obj);

    void doneEditName(String str, String str2);

    void onNameSectionSelected();

    void onPhoneNumbersSelected();

    void onProfileSectionSelected(ProfileSection profileSection);

    void unregisterOnBackListener();
}
