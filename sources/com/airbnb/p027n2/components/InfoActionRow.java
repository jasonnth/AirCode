package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InfoActionRow */
public final class InfoActionRow extends InfoRow {
    public InfoActionRow(Context context) {
        super(context);
    }

    public InfoActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this.infoText).applyLargeActionable();
    }

    public static void mock(InfoActionRow row) {
        row.setTitle("Info row");
        row.setInfo("Info text");
        row.setOnClickListener(InfoActionRow$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mock$0(View v) {
    }

    public static void mockLongTitle(InfoActionRow row) {
        row.setTitle("Info row with a very long title that truncates");
        row.setInfo("Info text");
        row.setOnClickListener(InfoActionRow$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockLongTitle$1(View v) {
    }

    public static void mockLongInfo(InfoActionRow row) {
        row.setTitle("Info row");
        row.setInfo("Info text with a very long info text that truncates");
        row.setOnClickListener(InfoActionRow$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockLongInfo$2(View v) {
    }

    public static void mockWithSubTitle(InfoActionRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText("Optional subtitle");
        row.setOnClickListener(InfoActionRow$$Lambda$4.lambdaFactory$());
    }

    public static void mockWithRichSubTitle(InfoActionRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", InfoActionRow$$Lambda$5.lambdaFactory$()).build());
        row.setOnClickListener(InfoActionRow$$Lambda$6.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(InfoActionRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
        row.setOnClickListener(InfoActionRow$$Lambda$7.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockWithLongSubtitle$6(View v) {
    }
}
