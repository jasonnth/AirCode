package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenListingRegistrationSubmission;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingRegistrationSubmission extends GenListingRegistrationSubmission {
    public static final Creator<ListingRegistrationSubmission> CREATOR = new Creator<ListingRegistrationSubmission>() {
        public ListingRegistrationSubmission[] newArray(int size) {
            return new ListingRegistrationSubmission[size];
        }

        public ListingRegistrationSubmission createFromParcel(Parcel source) {
            ListingRegistrationSubmission object = new ListingRegistrationSubmission();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String IMAGE_FILE_TYPE = "image/jpeg";

    public enum AddressField {
        apt,
        city,
        country,
        country_code,
        state,
        street,
        zipcode
    }

    public enum FileField {
        content,
        file_type,
        url,
        value
    }

    public boolean hasAnswer(String question) {
        if (this.mAnswers == null) {
            return false;
        }
        return this.mAnswers.containsKey(question);
    }

    public String getStringAnswer(String question) {
        return (String) this.mAnswers.get(question);
    }

    public ListingRegistrationFileAnswer getFileAnswer(String inputKey) {
        if (this.mAnswers.get(inputKey) == null) {
            return null;
        }
        return ListingRegistrationFileAnswer.getInstance((Map) this.mAnswers.get(inputKey));
    }

    public Map getAnswerMap(String inputKey) {
        return (Map) this.mAnswers.get(inputKey);
    }

    public void putFileAnswer(String inputKey, String content, String fileType, String value) {
        Map map = new HashMap();
        map.put(FileField.content.name(), content);
        map.put(FileField.file_type.name(), fileType);
        map.put(FileField.value.name(), value);
        this.mAnswers.put(inputKey, map);
    }

    public boolean putStringAnswer(String inputKey, String inputAnswer) {
        String currentAnswer = (String) this.mAnswers.get(inputKey);
        if ((currentAnswer != null || inputAnswer == null) && (currentAnswer == null || currentAnswer.equals(inputAnswer))) {
            return false;
        }
        this.mAnswers.put(inputKey, inputAnswer);
        return true;
    }

    public boolean putListAnswer(String inputKey, String inputAnswer) {
        boolean hasAnswersToSave = true;
        if (inputAnswer == null) {
            if (this.mAnswers.get(inputKey) == null) {
                hasAnswersToSave = false;
            }
            this.mAnswers.put(inputKey, null);
        } else {
            List<String> listStrs = Arrays.asList(inputAnswer.split(","));
            List<String> prevListStrs = (List) this.mAnswers.get(inputKey);
            if (prevListStrs != null && listStrs.size() == prevListStrs.size() && listStrs.containsAll(prevListStrs)) {
                hasAnswersToSave = false;
            }
            this.mAnswers.put(inputKey, listStrs);
        }
        return hasAnswersToSave;
    }

    public String getListAnswerString(String inputKey) {
        List<String> list = (List) this.mAnswers.get(inputKey);
        if (list == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    public List<String> getAnswerStringList(String inputKey) {
        return (List) this.mAnswers.get(inputKey);
    }

    public AirAddress getAddressAnswer(String inputKey) {
        Map map = (Map) this.mAnswers.get(inputKey);
        if (map == null) {
            return null;
        }
        return AirAddress.builder().streetAddressTwo((String) map.get(AddressField.apt.name())).city((String) map.get(AddressField.city.name())).country((String) map.get(AddressField.country.name())).countryCode((String) map.get(AddressField.country_code.name())).state((String) map.get(AddressField.state.name())).streetAddressOne((String) map.get(AddressField.street.name())).postalCode((String) map.get(AddressField.zipcode.name())).build();
    }

    private boolean putAddressAnswer(Map map, String key, String newValue) {
        if (CityRegistrationUtils.equals(newValue, (String) map.get(key))) {
            return false;
        }
        map.put(key, newValue);
        return true;
    }

    public boolean putAddressAnswer(String inputKey, AirAddress address) {
        boolean foundChanges;
        boolean foundChanges2;
        boolean foundChanges3;
        boolean foundChanges4;
        boolean foundChanges5;
        Map map = (Map) this.mAnswers.get(inputKey);
        if (map == null) {
            map = new HashMap();
        }
        boolean foundChanges6 = putAddressAnswer(map, AddressField.city.name(), address.city()) || putAddressAnswer(map, AddressField.apt.name(), address.streetAddressTwo());
        if (putAddressAnswer(map, AddressField.country.name(), address.country()) || foundChanges6) {
            foundChanges = true;
        } else {
            foundChanges = false;
        }
        if (putAddressAnswer(map, AddressField.country_code.name(), address.countryCode()) || foundChanges) {
            foundChanges2 = true;
        } else {
            foundChanges2 = false;
        }
        if (putAddressAnswer(map, AddressField.state.name(), address.state()) || foundChanges2) {
            foundChanges3 = true;
        } else {
            foundChanges3 = false;
        }
        if (putAddressAnswer(map, AddressField.street.name(), address.streetAddressOne()) || foundChanges3) {
            foundChanges4 = true;
        } else {
            foundChanges4 = false;
        }
        if (putAddressAnswer(map, AddressField.zipcode.name(), address.postalCode()) || foundChanges4) {
            foundChanges5 = true;
        } else {
            foundChanges5 = false;
        }
        this.mAnswers.put(inputKey, map);
        return foundChanges5;
    }

    public void removeAnswer(String inputKey) {
        this.mAnswers.remove(inputKey);
    }
}
