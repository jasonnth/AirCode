package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.KickerMarquee */
public class KickerMarquee extends LinearLayout {
    @BindView
    AirTextView kickerTextView;
    @BindView
    AirTextView subtitleTextView;
    @BindView
    AirTextView titleTextView;

    /* renamed from: com.airbnb.n2.components.KickerMarquee$Style */
    public enum Style {
        BABU(R.style.n2_TitleText2_Inverse, R.style.n2_LargeText_Inverse, R.style.n2_SmallText_Inverse),
        WHITE(R.style.n2_TitleText2, R.style.n2_LargeText, R.style.n2_SmallText),
        WHITELITE(R.style.n2_TitleText2, R.style.n2_RegularText, R.style.n2_SmallText);
        
        final int kickerStyleRes;
        final int subtitleStyleRes;
        final int titleStyleRes;

        private Style(int titleStyleRes2, int subtitleStyleRes2, int kickerStyleRes2) {
            this.titleStyleRes = titleStyleRes2;
            this.subtitleStyleRes = subtitleStyleRes2;
            this.kickerStyleRes = kickerStyleRes2;
        }

        public void setStyle(KickerMarquee marquee) {
            Context context = marquee.getContext();
            marquee.titleTextView.setTextAppearance(context, this.titleStyleRes);
            marquee.subtitleTextView.setTextAppearance(context, this.subtitleStyleRes);
            marquee.kickerTextView.setTextAppearance(context, this.kickerStyleRes);
        }
    }

    public KickerMarquee(Context context) {
        super(context);
        init(null);
    }

    public KickerMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public KickerMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_kicker_marquee, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_KickerMarquee, 0, 0);
        String title = a.getString(R.styleable.n2_KickerMarquee_n2_titleText);
        String subtitle = a.getString(R.styleable.n2_KickerMarquee_n2_subtitleText);
        String kicker = a.getString(R.styleable.n2_KickerMarquee_n2_kickerText);
        Style style = Style.values()[a.getInt(R.styleable.n2_KickerMarquee_n2_kickerMarqueeStyle, 0)];
        style.setStyle(this);
        setFont(style);
        setTitle(title);
        setSubtitle(subtitle);
        setKicker((CharSequence) kicker);
        a.recycle();
    }

    public void setTitle(String title) {
        this.titleTextView.setText(title);
    }

    public void setTitle(int titleResId) {
        setTitle(getResources().getString(titleResId));
    }

    public void setSubtitle(String subtitle) {
        ViewLibUtils.setVisibleIf(this.subtitleTextView, !TextUtils.isEmpty(subtitle));
        this.subtitleTextView.setText(subtitle);
    }

    public void setSubtitle(int subtitleResId) {
        setSubtitle(getResources().getString(subtitleResId));
    }

    public void setKicker(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.kickerTextView, !TextUtils.isEmpty(text));
        this.kickerTextView.setText(text);
    }

    public void setKicker(int stringRes) {
        setKicker((CharSequence) getResources().getString(stringRes));
    }

    public void setSubtitleMaxLines(int maxLines) {
        this.subtitleTextView.setMaxLines(maxLines);
        this.subtitleTextView.setEllipsize(TruncateAt.END);
    }

    private void setFont(Style style) {
        this.subtitleTextView.setFont(style == Style.WHITELITE ? Font.CircularLight : Font.CircularBook);
    }

    public static void mock(KickerMarquee marquee) {
        marquee.setTitle("Review your trip details");
        marquee.setKicker((CharSequence) "1 of 4 steps");
    }

    public static void mockOnlyTitle(KickerMarquee marquee) {
        marquee.setTitle("Review your trip details");
    }

    public static void mockTitleAndCaption(KickerMarquee marquee) {
        marquee.setTitle("Review your trip details");
        marquee.setSubtitle("Optional caption");
    }

    public static void mockEverything(KickerMarquee marquee) {
        marquee.setTitle("Review your trip details");
        marquee.setKicker((CharSequence) "1 of 4 steps");
        marquee.setSubtitle("Optional caption");
    }

    public static void mockBabu(KickerMarquee marquee) {
        marquee.setTitle("Review your trip details");
        marquee.setKicker((CharSequence) "1 of 4 steps");
    }
}
