package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenCheckInStep;

public class CheckInStep extends GenCheckInStep {
    public static final Creator<CheckInStep> CREATOR = new Creator<CheckInStep>() {
        public CheckInStep[] newArray(int size) {
            return new CheckInStep[size];
        }

        public CheckInStep createFromParcel(Parcel source) {
            CheckInStep object = new CheckInStep();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String[] SAMPLE_GUIDE_IMAGE_URLS = {"https://a0.muscache.com/im/pictures/6071fed8-e562-4652-9884-7280f82ff491.jpg", "https://a0.muscache.com/im/pictures/35574f12-4753-44f8-a75e-14e401f2b19d.jpg", "https://a0.muscache.com/im/pictures/47313c82-2e48-4f70-b07a-8e9455cac4ff.jpg"};
    private static final String[] SAMPLE_GUIDE_STEP_NOTES = {"Look for James Court on the North side of Lawnmarket street, right next to Johnstons of Elgin. (If you're looking on Google Maps street view, the view is blocked by a big blue 'Move It' truck). Go in, and walk halfway to the end.", "Halfway down James Court you'll find yourself at this spiral staicase. Take it up to the next level.", "Walk to the end of the hallway and you'll find our door! Knock loudly and we'll let you in, or you can use the smart lock. The code is the last 4 digits of your cell phone number."};
    private static final String SAMPLE_GUIDE_TRANSLATED_TEXT = "Your guide will be automatically translated into the guest's preferred language using Google Translate. Write your check-in guide in your preferred language and we'll take care of the rest!";

    public String getPictureUrl() {
        if (this.mAttachment == null) {
            return null;
        }
        return this.mAttachment.getPictureUrl();
    }

    public static CheckInStep getSampleCheckinStep(int stepNumber) {
        CheckInStep sample = new CheckInStep();
        sample.setId((long) stepNumber);
        sample.setNote(SAMPLE_GUIDE_STEP_NOTES[stepNumber % SAMPLE_GUIDE_STEP_NOTES.length]);
        sample.setLocalizedNote(SAMPLE_GUIDE_TRANSLATED_TEXT);
        String imageUrl = SAMPLE_GUIDE_IMAGE_URLS[stepNumber % SAMPLE_GUIDE_IMAGE_URLS.length];
        if (!TextUtils.isEmpty(imageUrl)) {
            sample.setAttachment(new CheckInStepAttachment(imageUrl + "?i=" + stepNumber));
        }
        return sample;
    }
}
