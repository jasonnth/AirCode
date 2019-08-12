package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LoadingText */
public class LoadingText extends FrameLayout {
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView messageText;

    public LoadingText(Context context) {
        super(context);
        init(null);
    }

    public LoadingText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_loading_text, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LoadingText, 0, 0);
        setMessageText(a.getString(R.styleable.n2_LoadingText_n2_messageText));
        if (a.hasValue(R.styleable.n2_LoadingText_n2_messageTextColor)) {
            setMessageTextColor(a.getColor(R.styleable.n2_LoadingText_n2_messageTextColor, 0));
        }
        if (a.hasValue(R.styleable.n2_LoadingText_n2_loadingViewColor)) {
            setLoadingViewColor(a.getColor(R.styleable.n2_LoadingText_n2_loadingViewColor, 0));
        }
        a.recycle();
    }

    public void setLoadingViewColor(int color) {
        this.loadingView.setColor(color);
    }

    public void setMessageTextColor(int color) {
        this.messageText.setTextColor(color);
    }

    public void setMessageText(CharSequence string) {
        ViewLibUtils.setGoneIf(this.messageText, TextUtils.isEmpty(string));
        this.messageText.setText(string);
    }

    public static void mock(LoadingText loadingText) {
        loadingText.setLoadingViewColor(-1);
        loadingText.setMessageTextColor(-1);
        loadingText.setMessageText("Loading");
    }
}
