package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.core.models.generated.GenListingPersonaInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.Arrays;
import java.util.List;

public class ListingPersonaInput extends GenListingPersonaInput {
    public static final Creator<ListingPersonaInput> CREATOR = new Creator<ListingPersonaInput>() {
        public ListingPersonaInput[] newArray(int size) {
            return new ListingPersonaInput[size];
        }

        public ListingPersonaInput createFromParcel(Parcel source) {
            ListingPersonaInput object = new ListingPersonaInput();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum ListingPersonaAnswer implements Parcelable {
        FIRST_TIME_EXPERIENCE_ANSWER(1, C0716R.string.lys_new_host),
        PREVIOUS_EXPERIENCE_ANSWER(2, C0716R.string.lys_old_host),
        HOST_UNSURE_OCCUPANCY_ANSWER(3, C0716R.string.lys_dls_hosting_frequency_not_sure),
        HOST_SOMETIMES_OCCUPANCY_ANSWER(4, DesiredHostingFrequency.PartTime.getTitleStringId()),
        HOST_OFTEN_OCCUPANCY_ANSWER(5, DesiredHostingFrequency.OftenAsPossible.getTitleStringId());
        
        public static final Creator<ListingPersonaAnswer> CREATOR = null;
        private final int serverKey;
        private final int titleRes;

        static {
            CREATOR = new Creator<ListingPersonaAnswer>() {
                public ListingPersonaAnswer createFromParcel(Parcel source) {
                    return ListingPersonaAnswer.values()[source.readInt()];
                }

                public ListingPersonaAnswer[] newArray(int size) {
                    return new ListingPersonaAnswer[size];
                }
            };
        }

        private ListingPersonaAnswer(int serverKey2, int titleRes2) {
            this.serverKey = serverKey2;
            this.titleRes = titleRes2;
        }

        public static ListingPersonaAnswer fromServerKey(int serverKey2) {
            return (ListingPersonaAnswer) FluentIterable.from((E[]) values()).filter(ListingPersonaInput$ListingPersonaAnswer$$Lambda$1.lambdaFactory$(serverKey2)).first().orNull();
        }

        public int getServerKey() {
            return this.serverKey;
        }

        public int getTitleRes() {
            return this.titleRes;
        }

        public boolean isNewHost() {
            return this == FIRST_TIME_EXPERIENCE_ANSWER;
        }

        public static List<ListingPersonaAnswer> getOccupanyAnswers() {
            return Arrays.asList(new ListingPersonaAnswer[]{HOST_OFTEN_OCCUPANCY_ANSWER, HOST_SOMETIMES_OCCUPANCY_ANSWER, HOST_UNSURE_OCCUPANCY_ANSWER});
        }

        public static List<ListingPersonaAnswer> getExperienceAnswers() {
            return Arrays.asList(new ListingPersonaAnswer[]{PREVIOUS_EXPERIENCE_ANSWER, FIRST_TIME_EXPERIENCE_ANSWER});
        }

        public static DesiredHostingFrequency getHostingFrequencyFromAnswer(ListingPersonaAnswer answer) {
            switch (answer) {
                case HOST_OFTEN_OCCUPANCY_ANSWER:
                    return DesiredHostingFrequency.OftenAsPossible;
                case HOST_SOMETIMES_OCCUPANCY_ANSWER:
                    return DesiredHostingFrequency.PartTime;
                default:
                    return null;
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum ListingPersonaQuestion implements Parcelable {
        EXPERIENCE_QUESTION(1),
        OCCUPANCY_QUESTION(2);
        
        public static final Creator<ListingPersonaQuestion> CREATOR = null;
        private final int serverKey;

        static {
            CREATOR = new Creator<ListingPersonaQuestion>() {
                public ListingPersonaQuestion createFromParcel(Parcel source) {
                    return ListingPersonaQuestion.values()[source.readInt()];
                }

                public ListingPersonaQuestion[] newArray(int size) {
                    return new ListingPersonaQuestion[size];
                }
            };
        }

        private ListingPersonaQuestion(int serverKey2) {
            this.serverKey = serverKey2;
        }

        public static ListingPersonaQuestion fromServerKey(int serverKey2) {
            return (ListingPersonaQuestion) FluentIterable.from((E[]) values()).filter(ListingPersonaInput$ListingPersonaQuestion$$Lambda$1.lambdaFactory$(serverKey2)).first().orNull();
        }

        public int getServerKey() {
            return this.serverKey;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    @JsonProperty("question")
    public void setQuestion(int serverKey) {
        this.mQuestion = ListingPersonaQuestion.fromServerKey(serverKey);
    }

    @JsonProperty("answer")
    public void setAnswer(int serverKey) {
        this.mAnswer = ListingPersonaAnswer.fromServerKey(serverKey);
    }
}
