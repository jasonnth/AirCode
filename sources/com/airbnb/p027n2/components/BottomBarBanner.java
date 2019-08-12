package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BottomBarBanner */
public class BottomBarBanner extends FrameLayout {
    @BindView
    AirTextView textView;

    /* renamed from: com.airbnb.n2.components.BottomBarBanner$Style */
    public enum Style {
        ARCHES(1, R.color.n2_text_color_main_inverted, R.color.n2_arches);
        
        final int backgroundColor;
        final int textColor;
        final int value;

        private Style(int value2, int textColor2, int backgroundColor2) {
            this.value = value2;
            this.textColor = textColor2;
            this.backgroundColor = backgroundColor2;
        }

        static Style fromValue(int value2) {
            Style[] values;
            for (Style style : values()) {
                if (style.value == value2) {
                    return style;
                }
            }
            throw new IllegalArgumentException("Invalid style value");
        }
    }

    public BottomBarBanner(Context context) {
        this(context, null, 0);
    }

    public BottomBarBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.n2_bottom_bar_banner, this);
        ButterKnife.bind((View) this);
        this.textView.setSelected(true);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_BottomBarBanner, defStyleAttr, R.style.n2_BottomBarBanner);
        setText(ta.getString(R.styleable.n2_BottomBarBanner_n2_text));
        setStyle(Style.fromValue(ta.getInt(R.styleable.n2_BottomBarBanner_n2_bottomBarBannerStyle, Style.ARCHES.value)));
        ta.recycle();
    }

    public void setText(CharSequence body) {
        this.textView.setText(body);
    }

    public void setStyle(Style style) {
        switch (style) {
            case ARCHES:
                this.textView.setTextColor(ContextCompat.getColorStateList(getContext(), style.textColor));
                setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColor));
                return;
            default:
                return;
        }
    }

    public static void mock(BottomBarBanner banner) {
        banner.setText("You are banned forever!");
        banner.setStyle(Style.ARCHES);
    }
}
