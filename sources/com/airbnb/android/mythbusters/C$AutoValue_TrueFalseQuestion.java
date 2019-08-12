package com.airbnb.android.mythbusters;

import com.airbnb.android.mythbusters.TrueFalseQuestion.TrueFalseAnswer;

/* renamed from: com.airbnb.android.mythbusters.$AutoValue_TrueFalseQuestion reason: invalid class name */
abstract class C$AutoValue_TrueFalseQuestion extends TrueFalseQuestion {
    private final String answerExplanation;
    private final TrueFalseAnswer correctAnswer;
    private final String question;

    /* renamed from: com.airbnb.android.mythbusters.$AutoValue_TrueFalseQuestion$Builder */
    static final class Builder extends com.airbnb.android.mythbusters.TrueFalseQuestion.Builder {
        private String answerExplanation;
        private TrueFalseAnswer correctAnswer;
        private String question;

        Builder() {
        }

        public com.airbnb.android.mythbusters.TrueFalseQuestion.Builder question(String question2) {
            if (question2 == null) {
                throw new NullPointerException("Null question");
            }
            this.question = question2;
            return this;
        }

        public com.airbnb.android.mythbusters.TrueFalseQuestion.Builder answerExplanation(String answerExplanation2) {
            if (answerExplanation2 == null) {
                throw new NullPointerException("Null answerExplanation");
            }
            this.answerExplanation = answerExplanation2;
            return this;
        }

        public com.airbnb.android.mythbusters.TrueFalseQuestion.Builder correctAnswer(TrueFalseAnswer correctAnswer2) {
            if (correctAnswer2 == null) {
                throw new NullPointerException("Null correctAnswer");
            }
            this.correctAnswer = correctAnswer2;
            return this;
        }

        public TrueFalseQuestion build() {
            String missing = "";
            if (this.question == null) {
                missing = missing + " question";
            }
            if (this.answerExplanation == null) {
                missing = missing + " answerExplanation";
            }
            if (this.correctAnswer == null) {
                missing = missing + " correctAnswer";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TrueFalseQuestion(this.question, this.answerExplanation, this.correctAnswer);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_TrueFalseQuestion(String question2, String answerExplanation2, TrueFalseAnswer correctAnswer2) {
        if (question2 == null) {
            throw new NullPointerException("Null question");
        }
        this.question = question2;
        if (answerExplanation2 == null) {
            throw new NullPointerException("Null answerExplanation");
        }
        this.answerExplanation = answerExplanation2;
        if (correctAnswer2 == null) {
            throw new NullPointerException("Null correctAnswer");
        }
        this.correctAnswer = correctAnswer2;
    }

    public String question() {
        return this.question;
    }

    public String answerExplanation() {
        return this.answerExplanation;
    }

    public TrueFalseAnswer correctAnswer() {
        return this.correctAnswer;
    }

    public String toString() {
        return "TrueFalseQuestion{question=" + this.question + ", answerExplanation=" + this.answerExplanation + ", correctAnswer=" + this.correctAnswer + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TrueFalseQuestion)) {
            return false;
        }
        TrueFalseQuestion that = (TrueFalseQuestion) o;
        if (!this.question.equals(that.question()) || !this.answerExplanation.equals(that.answerExplanation()) || !this.correctAnswer.equals(that.correctAnswer())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.question.hashCode()) * 1000003) ^ this.answerExplanation.hashCode()) * 1000003) ^ this.correctAnswer.hashCode();
    }
}
