package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.EntryMarquee */
public class EntryMarquee extends LinearLayout implements DividerView {
    @BindView
    AirTextView captionTextView;
    @BindView
    View divider;
    @BindView
    AirTextView titleTextView;

    public EntryMarquee(Context context) {
        super(context);
        init(null);
    }

    public EntryMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EntryMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_entry_marquee, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_Marquee, 0, 0);
        String titleString = a.getString(R.styleable.n2_Marquee_n2_titleText);
        String captionString = a.getString(R.styleable.n2_Marquee_n2_captionText);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_Marquee_n2_showDivider, true);
        setTitle((CharSequence) titleString);
        setCaption((CharSequence) captionString);
        showDivider(dividerVisible);
        a.recycle();
    }

    public void setTitle(CharSequence string) {
        this.titleTextView.setText(string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void setCaption(CharSequence caption) {
        ViewLibUtils.setVisibleIf(this.captionTextView, !TextUtils.isEmpty(caption));
        this.captionTextView.setText(caption);
    }

    public void setCaption(int stringRes) {
        setCaption((CharSequence) getResources().getString(stringRes));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(EntryMarquee marquee) {
        marquee.setTitle((CharSequence) "Title");
        marquee.setCaption((CharSequence) "Caption");
    }
}
