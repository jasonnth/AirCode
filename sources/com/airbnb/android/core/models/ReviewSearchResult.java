package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReviewSearchResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ReviewSearchResult extends GenReviewSearchResult {
    public static final Creator<ReviewSearchResult> CREATOR = new Creator<ReviewSearchResult>() {
        public ReviewSearchResult[] newArray(int size) {
            return new ReviewSearchResult[size];
        }

        public ReviewSearchResult createFromParcel(Parcel source) {
            ReviewSearchResult object = new ReviewSearchResult();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("attributed_texts_match_ranges")
    public void setAttributedTextsMatchRanges(List<List<Integer>> attributedTextsMatchRanges) {
        this.mAttributedTextsMatchRanges = new ArrayList();
        if (attributedTextsMatchRanges != null) {
            for (List<Integer> range : attributedTextsMatchRanges) {
                AttributedTextRange attributedTextRange = new AttributedTextRange();
                attributedTextRange.setStart(((Integer) range.get(0)).intValue());
                attributedTextRange.setLength(((Integer) range.get(1)).intValue());
                this.mAttributedTextsMatchRanges.add(attributedTextRange);
            }
        }
    }
}
