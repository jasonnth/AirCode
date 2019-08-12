package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ArticleDocumentMarquee */
public class ArticleDocumentMarquee extends LinearLayout {
    @BindView
    AirTextView captionTextView;
    @BindView
    View divider;
    @BindView
    AirTextView kickerTextView;
    @BindView
    AirTextView linkTextView;
    @BindView
    AirTextView titleTextView;

    public ArticleDocumentMarquee(Context context) {
        super(context);
        init();
    }

    public ArticleDocumentMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArticleDocumentMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_article_document_marquee, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setTitleText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.titleTextView, TextUtils.isEmpty(text));
        this.titleTextView.setText(text);
    }

    public void setCaptionText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.captionTextView, TextUtils.isEmpty(text));
        this.captionTextView.setText(text);
    }

    public void setLinkText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.linkTextView, TextUtils.isEmpty(text));
        this.linkTextView.setText(text);
    }

    public void setKickerText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.kickerTextView, TextUtils.isEmpty(text));
        this.kickerTextView.setText(text);
    }

    public static void mock(ArticleDocumentMarquee view) {
        view.setKickerText("kicker");
        view.setTitleText("Title");
        view.setCaptionText("and here is some caption text");
    }

    public static void mockWithoutKicker(ArticleDocumentMarquee view) {
        view.setTitleText("Title");
        view.setCaptionText("and here is some caption text");
    }

    public static void mockWithoutCaption(ArticleDocumentMarquee view) {
        view.setKickerText("kicker");
        view.setTitleText("Title");
    }
}
