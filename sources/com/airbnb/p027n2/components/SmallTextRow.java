package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.p027n2.utils.AirTextBuilder;

/* renamed from: com.airbnb.n2.components.SmallTextRow */
public class SmallTextRow extends TextRow {
    public SmallTextRow(Context context) {
        super(context);
    }

    public SmallTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void mockCollapsed(SmallTextRow view) {
        view.setReadMoreText("read more");
        view.setText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.");
        view.setMaxLines(2);
    }

    public static void mockExpanded(SmallTextRow view) {
        view.setText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.");
    }

    public static void mockRichText(SmallTextRow view) {
        view.setReadMoreText("read more");
        view.setText(new AirTextBuilder(view.getContext()).append("Lorem ipsum ").appendBold("dolor sit amet,").appendItalic(" consectetur adipiscing elit. ").appendLink("Cras consectetur", SmallTextRow$$Lambda$1.lambdaFactory$()).append(" ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.").build());
        view.setMaxLines(4);
    }
}
