package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.DocumentMarquee */
public class DocumentMarquee extends LinearLayout {
    @BindView
    AirTextView captionTextView;
    @BindView
    AirTextView linkTextView;
    @BindView
    AirTextView titleTextView;
    @BindView
    HaloImageView userImage;

    public DocumentMarquee(Context context) {
        super(context);
        init(null);
    }

    public DocumentMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DocumentMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_document_marquee, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_DocumentMarquee, 0, 0);
        String titleString = a.getString(R.styleable.n2_DocumentMarquee_n2_titleText);
        String captionString = a.getString(R.styleable.n2_DocumentMarquee_n2_captionText);
        int textColor = a.getColor(R.styleable.n2_DocumentMarquee_n2_textColor, ContextCompat.getColor(getContext(), R.color.n2_text_color_main));
        setTitle((CharSequence) titleString);
        setCaption((CharSequence) captionString);
        setTextColor(textColor);
        a.recycle();
    }

    public void setTitle(CharSequence string) {
        this.titleTextView.setText(string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void setCaption(CharSequence caption) {
        ViewLibUtils.bindOptionalTextView((TextView) this.captionTextView, caption, true);
    }

    public void setCaption(int stringRes) {
        setCaption((CharSequence) getResources().getString(stringRes));
    }

    public void setLinkText(CharSequence linkText) {
        this.linkTextView.setText(linkText);
        setLinkVisibility(!TextUtils.isEmpty(linkText));
    }

    public void setLinkText(int stringRes) {
        setLinkText((CharSequence) getResources().getString(stringRes));
        setLinkVisibility(stringRes != 0);
    }

    public void setLinkClickListener(OnClickListener listener) {
        this.linkTextView.setOnClickListener(listener);
    }

    private void setLinkVisibility(boolean visible) {
        ViewLibUtils.setVisibleIf(this.linkTextView, visible);
    }

    public AirTextView getCaptionTextView() {
        return this.captionTextView;
    }

    public void setTitleMaxLines(int maxLines) {
        this.titleTextView.setMaxLines(maxLines);
        this.titleTextView.setEllipsize(TruncateAt.END);
    }

    public void setCaptionMaxLines(int maxLines) {
        this.captionTextView.setMaxLines(maxLines);
        this.captionTextView.setEllipsize(TruncateAt.END);
    }

    @Deprecated
    public void setTextColor(int textColor) {
        this.titleTextView.setTextColor(textColor);
        this.captionTextView.setTextColor(textColor);
    }

    @Deprecated
    public void setUserImageUrl(String userImageUrl) {
        ViewLibUtils.setVisibleIf(this.userImage, !TextUtils.isEmpty(userImageUrl));
        this.userImage.setImageUrl(userImageUrl);
    }

    public static void mock(DocumentMarquee marquee) {
        marquee.setTitle((CharSequence) "Document Marquee");
    }

    public static void mockLongTitle(DocumentMarquee marquee) {
        marquee.setTitle((CharSequence) "Document Marquee That Wraps");
    }

    public static void mockPlusCaption(DocumentMarquee marquee) {
        marquee.setTitle((CharSequence) "Document Marquee");
        marquee.setCaption((CharSequence) "Optional caption");
    }

    public static void mockPlusLink(DocumentMarquee marquee) {
        marquee.setTitle((CharSequence) "DocumentMarquee");
        marquee.setLinkText((CharSequence) "Optional link");
    }

    public static void mockRickCaption(DocumentMarquee marquee) {
        marquee.setTitle((CharSequence) "Document Marquee");
        marquee.setCaption(new AirTextBuilder(marquee.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", DocumentMarquee$$Lambda$1.lambdaFactory$()).build());
    }
}
