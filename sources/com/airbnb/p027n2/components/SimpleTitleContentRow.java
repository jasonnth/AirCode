package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.AirTextBuilder;

/* renamed from: com.airbnb.n2.components.SimpleTitleContentRow */
public class SimpleTitleContentRow extends BaseDividerComponent {
    @BindView
    AirTextView content;
    @BindView
    AirTextView title;

    public SimpleTitleContentRow(Context context) {
        super(context);
    }

    public SimpleTitleContentRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTitleContentRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_simple_title_content_row;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setContentText(CharSequence text) {
        this.content.setText(text);
    }

    public void setContentText(int textRes) {
        this.content.setText(getResources().getString(textRes));
    }

    public static void mockTitle(SimpleTitleContentRow row) {
        row.setTitle((CharSequence) "Title");
    }

    public static void mockLongTitle(SimpleTitleContentRow row) {
        row.setTitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }

    public static void mockWithSubtitle(SimpleTitleContentRow row) {
        row.setTitle((CharSequence) "Title");
        row.setContentText((CharSequence) "Optional subtitle");
        row.setOnClickListener(SimpleTitleContentRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWithRichSubtitle(SimpleTitleContentRow row) {
        row.setTitle((CharSequence) "Title");
        row.setContentText(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", SimpleTitleContentRow$$Lambda$2.lambdaFactory$()).build());
        row.setOnClickListener(SimpleTitleContentRow$$Lambda$3.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(SimpleTitleContentRow row) {
        row.setTitle((CharSequence) "Title");
        row.setContentText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
    }
}
