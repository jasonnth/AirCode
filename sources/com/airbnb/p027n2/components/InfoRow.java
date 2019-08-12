package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InfoRow */
public class InfoRow extends BaseDividerComponent {
    @BindView
    AirTextView infoText;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public InfoRow(Context context) {
        super(context);
    }

    public InfoRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_info_row;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text, true);
    }

    public void setInfo(CharSequence text) {
        this.infoText.setText(text);
    }

    public static void mock(InfoRow row) {
        row.setTitle("Info row");
        row.setInfo("Info text");
    }

    public static void mockLongTitle(InfoRow row) {
        row.setTitle("Info row with a very long title that truncates");
        row.setInfo("Info text");
    }

    public static void mockLongInfo(InfoRow row) {
        row.setTitle("Info row");
        row.setInfo("Info text with a very long info text that truncates");
    }

    public static void mockWithSubTitle(InfoRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText("Optional subtitle");
        row.setOnClickListener(InfoRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWithRichSubTitle(InfoRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", InfoRow$$Lambda$2.lambdaFactory$()).build());
        row.setOnClickListener(InfoRow$$Lambda$3.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(InfoRow row) {
        row.setTitle("Info row +");
        row.setInfo("Info text");
        row.setSubtitleText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
    }
}
