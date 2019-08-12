package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.CityRegistrationCheckmarkRow */
public class CityRegistrationCheckmarkRow extends BaseDividerComponent {
    @BindView
    AirImageView icon;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public CityRegistrationCheckmarkRow(Context context) {
        super(context);
    }

    public CityRegistrationCheckmarkRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityRegistrationCheckmarkRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_city_registration_checkmark_row;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        this.title.setText(getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.setText((TextView) this.subtitle, text, true);
    }

    public void setSubtitle(int textRes) {
        this.subtitle.setText(getResources().getString(textRes));
    }

    public void setIcon(Drawable drawable) {
        this.icon.setImageDrawable(drawable);
    }

    public void setIcon(int iconRes) {
        this.icon.setImageResource(iconRes);
    }

    public void clearIcon() {
        this.icon.clear();
    }

    public static void mock(CityRegistrationCheckmarkRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "subtitle");
    }

    public static void mockWithRichSubtitle(CityRegistrationCheckmarkRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", CityRegistrationCheckmarkRow$$Lambda$1.lambdaFactory$()).build());
    }

    public static void mockWithLongTitle(CityRegistrationCheckmarkRow row) {
        row.setTitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper.");
        row.setSubtitle((CharSequence) "This is a row with a very long title that should span multiple lines.");
    }
}
