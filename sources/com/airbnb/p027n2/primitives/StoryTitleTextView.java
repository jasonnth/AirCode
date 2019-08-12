package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.TextUtil;

/* renamed from: com.airbnb.n2.primitives.StoryTitleTextView */
public class StoryTitleTextView extends AirTextView {
    public StoryTitleTextView(Context context) {
        super(context);
    }

    public StoryTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoryTitleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void bindData(String titleText, String tagText) {
        String fullTitle = tagText + " " + titleText;
        if (TextUtils.isEmpty(tagText)) {
            setText(titleText);
        } else {
            setText(TextUtil.changeFontFamily(getContext(), Font.CircularBold, fullTitle, tagText));
        }
    }
}
