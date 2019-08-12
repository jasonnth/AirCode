package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import com.airbnb.android.core.models.generated.GenInboxSearchResult;

public class InboxSearchResult extends GenInboxSearchResult {
    public static final Creator<InboxSearchResult> CREATOR = new Creator<InboxSearchResult>() {
        public InboxSearchResult[] newArray(int size) {
            return new InboxSearchResult[size];
        }

        public InboxSearchResult createFromParcel(Parcel source) {
            InboxSearchResult object = new InboxSearchResult();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String TYPE_EMPHASIZED = "emphasized";

    public CharSequence getAttributedTextsSpannable() {
        CharSequence finalString = "";
        for (AttributedText at : getAttributedTexts()) {
            SpannableString s = new SpannableString(at.getText());
            if (TYPE_EMPHASIZED.equals(at.getType())) {
                s.setSpan(new StyleSpan(1), 0, s.length(), 33);
            }
            finalString = TextUtils.concat(new CharSequence[]{finalString, s});
        }
        return finalString;
    }
}
