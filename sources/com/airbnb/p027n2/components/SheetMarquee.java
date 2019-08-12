package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.SheetMarquee */
public class SheetMarquee extends LinearLayout {
    /* access modifiers changed from: private */
    public AirTextView subtitleTextView;
    /* access modifiers changed from: private */
    public AirTextView titleTextView;

    /* renamed from: com.airbnb.n2.components.SheetMarquee$Style */
    public enum Style {
        BABU(R.style.n2_TitleText2_Inverse, R.style.n2_LargeText_Inverse),
        WHITE(R.style.n2_TitleText2, R.style.n2_LargeText);
        
        final int subtitleStyleRes;
        final int titleStyleRes;

        private Style(int titleStyleRes2, int subtitleStyleRes2) {
            this.titleStyleRes = titleStyleRes2;
            this.subtitleStyleRes = subtitleStyleRes2;
        }

        public void setStyle(SheetMarquee marquee) {
            Context context = marquee.getContext();
            marquee.titleTextView.setTextAppearance(context, this.titleStyleRes);
            marquee.subtitleTextView.setTextAppearance(context, this.subtitleStyleRes);
        }
    }

    public SheetMarquee(Context context) {
        super(context);
        init(null);
    }

    public SheetMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SheetMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_sheet_marquee, this);
        setOrientation(1);
        bindViews();
        setupAttributes(attrs);
    }

    private void bindViews() {
        this.titleTextView = (AirTextView) ViewLibUtils.findById(this, R.id.title);
        this.subtitleTextView = (AirTextView) ViewLibUtils.findById(this, R.id.subtitle);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SheetMarquee);
        String title = a.getString(R.styleable.n2_SheetMarquee_n2_titleText);
        String subtitle = a.getString(R.styleable.n2_SheetMarquee_n2_subtitleText);
        Style.values()[a.getInt(R.styleable.n2_SheetMarquee_n2_sheetStyle, 0)].setStyle(this);
        setTitle(title);
        setSubtitle(subtitle);
        a.recycle();
    }

    public void setTitle(String title) {
        this.titleTextView.setText(title);
    }

    public void setTitle(int titleResId) {
        this.titleTextView.setText(titleResId);
    }

    public void setSubtitle(String subtitle) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleTextView, (CharSequence) subtitle);
    }

    public void setSubtitle(int subtitleResId) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleTextView, subtitleResId);
    }

    public void setSubtitleMaxLines(int maxLines) {
        this.subtitleTextView.setMaxLines(maxLines);
        this.subtitleTextView.setEllipsize(TruncateAt.END);
    }

    public static void mock(SheetMarquee view) {
        view.setTitle("Sheet Title");
    }

    public static void mockWithSubtitle(SheetMarquee view) {
        view.setTitle("Sheet Title");
        view.setSubtitle("Optional Subtitle");
    }
}
