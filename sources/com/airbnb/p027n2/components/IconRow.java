package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
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

/* renamed from: com.airbnb.n2.components.IconRow */
public final class IconRow extends BaseDividerComponent {
    @BindView
    AirImageView badge;
    @BindView
    AirImageView icon;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public IconRow(Context context) {
        super(context);
    }

    public IconRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_icon_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_IconRow, 0, 0);
        Drawable icon2 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_IconRow_n2_image);
        if (icon2 != null) {
            setIcon(icon2);
        }
        Drawable badge2 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_IconRow_n2_badge);
        if (badge2 != null) {
            setBadge(badge2);
        }
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text);
    }

    public void setSubtitleText(int textRes) {
        setSubtitleText((CharSequence) getResources().getString(textRes));
    }

    public void setIcon(Drawable icon2) {
        this.icon.setImageDrawable(icon2);
    }

    public void setIcon(int iconRes) {
        this.icon.setImageResource(iconRes);
    }

    public void setBadge(Drawable badge2) {
        ViewLibUtils.setVisibleIf(this.badge, badge2 != null);
        this.badge.setImageDrawable(badge2);
    }

    public void setBadge(int badgeRes) {
        ViewLibUtils.setVisibleIf(this.badge, badgeRes != 0);
        this.badge.setImageResource(badgeRes);
    }

    public void showDefaultBadge(boolean showBadge) {
        ViewLibUtils.setVisibleIf(this.badge, showBadge);
        if (showBadge) {
            setBadge(R.drawable.n2_icon_row_default_badge);
        }
    }

    public static void mockTitle(IconRow row) {
        row.setTitle((CharSequence) "Title");
        row.setIcon(R.drawable.n2_icon_alert);
    }

    public static void mockLongTitle(IconRow row) {
        row.setTitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        row.setIcon(R.drawable.n2_icon_alert);
    }

    public static void mockWithSubtitle(IconRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Optional subtitle");
        row.setIcon(R.drawable.n2_icon_alert);
        row.setOnClickListener(IconRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockBadged(IconRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Optional subtitle");
        row.setIcon(R.drawable.n2_icon_alert);
        row.showDefaultBadge(true);
        row.setOnClickListener(IconRow$$Lambda$2.lambdaFactory$());
    }

    public static void mockWithRichSubtitle(IconRow row) {
        row.setTitle((CharSequence) "Title");
        row.setIcon(R.drawable.n2_icon_alert);
        row.setSubtitleText(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", IconRow$$Lambda$3.lambdaFactory$()).build());
        row.setOnClickListener(IconRow$$Lambda$4.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(IconRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
        row.setIcon(R.drawable.n2_icon_alert);
    }
}
