package com.airbnb.android.mythbusters;

import android.os.Parcelable;

public abstract class TrueFalseQuestion implements Parcelable {

    public static abstract class Builder {
        public abstract Builder answerExplanation(String str);

        public abstract TrueFalseQuestion build();

        public abstract Builder correctAnswer(TrueFalseAnswer trueFalseAnswer);

        public abstract Builder question(String str);
    }

    public enum TrueFalseAnswer {
        TRUE,
        FALSE
    }

    public abstract String answerExplanation();

    public abstract TrueFalseAnswer correctAnswer();

    public abstract String question();

    public String getQuestion() {
        return question();
    }

    public String getAnswerExplanation() {
        return answerExplanation();
    }

    public TrueFalseAnswer getCorrectAnswer() {
        return correctAnswer();
    }

    public static Builder builder() {
        return new Builder();
    }
}
