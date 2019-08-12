package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.models.generated.GenListingRegistrationQuestion;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

public class ListingRegistrationQuestion extends GenListingRegistrationQuestion {
    public static final Creator<ListingRegistrationQuestion> CREATOR = new Creator<ListingRegistrationQuestion>() {
        public ListingRegistrationQuestion[] newArray(int size) {
            return new ListingRegistrationQuestion[size];
        }

        public ListingRegistrationQuestion createFromParcel(Parcel source) {
            ListingRegistrationQuestion object = new ListingRegistrationQuestion();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getRadioSelectedText() {
        if (getInputType() != ListingRegistrationInputType.Radio) {
            return null;
        }
        return getAnswerText();
    }

    public String getCheckmarkSelectedTexts() {
        if (getInputType() == ListingRegistrationInputType.Checkbox && !TextUtils.isEmpty(getInputAnswer())) {
            return FluentIterable.from((E[]) getInputAnswer().split(",")).transform(ListingRegistrationQuestion$$Lambda$1.lambdaFactory$(this)).join(Joiner.m1896on(", "));
        }
        return null;
    }

    static /* synthetic */ String lambda$getCheckmarkSelectedTexts$0(ListingRegistrationQuestion listingRegistrationQuestion, String a) {
        ListingRegistrationAnswer l = listingRegistrationQuestion.getAnswer(a);
        return l != null ? l.getText() : "";
    }

    public String getAnswerText() {
        if (TextUtils.isEmpty(getInputAnswer())) {
            return null;
        }
        ListingRegistrationAnswer answer = getAnswer(getInputAnswer());
        if (answer != null) {
            return answer.getText();
        }
        return null;
    }

    public ListingRegistrationAnswer getAnswer(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        return (ListingRegistrationAnswer) FluentIterable.from((Iterable<E>) getAnswers()).filter(ListingRegistrationQuestion$$Lambda$2.lambdaFactory$(value)).first().orNull();
    }
}
