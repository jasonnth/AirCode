package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LabelDocumentMarquee */
public class LabelDocumentMarquee extends BaseComponent {
    @BindView
    AirTextView captionTextView;
    @BindView
    AirTextView labelTextView;
    @BindView
    AirTextView titleTextView;

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_label_document_marquee;
    }

    public LabelDocumentMarquee(Context context) {
        super(context);
    }

    public LabelDocumentMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelDocumentMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitleText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.titleTextView, TextUtils.isEmpty(text));
        this.titleTextView.setText(text);
    }

    public void setCaptionText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.captionTextView, TextUtils.isEmpty(text));
        this.captionTextView.setText(text);
    }

    public void setLabelText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.labelTextView, TextUtils.isEmpty(text));
        this.labelTextView.setText(text);
    }

    public static void mock(LabelDocumentMarquee view) {
        view.setTitleText("Title");
        view.setCaptionText("and some caption text");
        view.setLabelText("status");
    }

    public static void mockWithoutLabel(LabelDocumentMarquee view) {
        view.setTitleText("Title");
        view.setCaptionText("and some caption text");
    }

    public static void mockWithoutCaption(LabelDocumentMarquee view) {
        view.setTitleText("Title");
        view.setLabelText("status");
    }
}
