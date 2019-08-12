package com.airbnb.android.mythbusters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.mythbusters.TrueFalseQuestion.TrueFalseAnswer;

final class AutoValue_TrueFalseQuestion extends C$AutoValue_TrueFalseQuestion {
    public static final Creator<AutoValue_TrueFalseQuestion> CREATOR = new Creator<AutoValue_TrueFalseQuestion>() {
        public AutoValue_TrueFalseQuestion createFromParcel(Parcel in) {
            return new AutoValue_TrueFalseQuestion(in.readString(), in.readString(), TrueFalseAnswer.valueOf(in.readString()));
        }

        public AutoValue_TrueFalseQuestion[] newArray(int size) {
            return new AutoValue_TrueFalseQuestion[size];
        }
    };

    AutoValue_TrueFalseQuestion(String question, String answerExplanation, TrueFalseAnswer correctAnswer) {
        super(question, answerExplanation, correctAnswer);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question());
        dest.writeString(answerExplanation());
        dest.writeString(correctAnswer().name());
    }

    public int describeContents() {
        return 0;
    }
}
