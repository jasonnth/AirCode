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

/* renamed from: com.airbnb.n2.components.BasicRow */
public final class BasicRow extends BaseDividerComponent {
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public BasicRow(Context context) {
        super(context);
    }

    public BasicRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_basic_row;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text, true);
    }

    public void setSubtitleText(int textRes) {
        setSubtitleText((CharSequence) getResources().getString(textRes));
    }

    public static void mockTitle(BasicRow row) {
        row.setTitle((CharSequence) "Title");
    }

    public static void mockLongTitle(BasicRow row) {
        row.setTitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }

    public static void mockWithSubtitle(BasicRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Optional subtitle");
        row.setOnClickListener(BasicRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWithRichSubtitle(BasicRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", BasicRow$$Lambda$2.lambdaFactory$()).build());
        row.setOnClickListener(BasicRow$$Lambda$3.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(BasicRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
    }
}
