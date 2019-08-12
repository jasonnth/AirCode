package com.airbnb.android.lib.utils;

import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.interfaces.EditProfileInterface.Gender;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.ProfilePhoneNumber;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.TextUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class UserProfileUtils {
    public static String getValueForDisplay(User currentUser, ProfileSection section) {
        return getValue(currentUser, section, false);
    }

    public static String getValueForEdit(User currentUser, ProfileSection section) {
        return getValue(currentUser, section, true);
    }

    private static String getValue(User currentUser, ProfileSection section, boolean forEdit) {
        boolean hasLanguages = false;
        switch (section) {
            case Name:
                return currentUser.getName();
            case About:
                if (!forEdit || TextUtils.isEmpty(currentUser.getUnscrubbedAbout())) {
                    return currentUser.getAbout();
                }
                return currentUser.getUnscrubbedAbout();
            case Gender:
                Gender gender = Gender.findGender(currentUser.getGender());
                if (gender != null) {
                    return AirbnbApplication.appContext().getString(gender.getDisplayId());
                }
                return null;
            case BirthDate:
                AirDate birthDate = currentUser.getBirthdate();
                if (birthDate != null) {
                    return birthDate.formatDate(SimpleDateFormat.getDateInstance(3, Locale.getDefault()));
                }
                return null;
            case Email:
                return currentUser.getEmailAddress();
            case Phone:
                List<ProfilePhoneNumber> phoneNumbers = currentUser.getPhoneNumbers();
                if (phoneNumbers == null || phoneNumbers.isEmpty()) {
                    return null;
                }
                return ((ProfilePhoneNumber) phoneNumbers.get(0)).getNumberFormatted();
            case GovernmentID:
                return AirbnbApplication.appContext().getString(currentUser.hasProvidedGovernmentID() ? C0880R.string.remove : C0880R.string.edit_profile_provide_verification);
            case Live:
                return currentUser.getLocation();
            case School:
                return currentUser.getSchool();
            case Work:
                return currentUser.getWork();
            case TimeZone:
                return currentUser.getTimezone();
            case Languages:
                List<String> languages = currentUser.getLanguages();
                if (languages != null && !languages.isEmpty()) {
                    hasLanguages = true;
                }
                if (hasLanguages) {
                    return TextUtil.join(languages);
                }
                return null;
            default:
                throw new IllegalArgumentException("Unsupported profile section type: " + section);
        }
    }

    public static boolean updateCurrentUser(User currentUser, UserResponse response) {
        User updatedUser = response.user;
        if (updatedUser == null) {
            return false;
        }
        currentUser.setAbout(updatedUser.getAbout());
        currentUser.setUnscrubbedAbout(updatedUser.getUnscrubbedAbout());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setGender(updatedUser.getGender());
        currentUser.setBirthdate(updatedUser.getBirthdate());
        currentUser.setEmailAddress(updatedUser.getEmailAddress());
        currentUser.setSchool(updatedUser.getSchool());
        currentUser.setWork(updatedUser.getWork());
        currentUser.setLocation(updatedUser.getLocation());
        currentUser.setLanguages(updatedUser.getLanguages());
        currentUser.setCountry(updatedUser.getCountry());
        return true;
    }
}
