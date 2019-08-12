package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

/* renamed from: com.airbnb.n2.components.InfoPanelRow */
public class InfoPanelRow extends FrameLayout {
    @BindView
    AirTextView contentText;
    @BindView
    AirTextView titleText;

    public InfoPanelRow(Context context) {
        super(context);
        init(null);
    }

    public InfoPanelRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InfoPanelRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_info_panel_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InfoPanelRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_InfoPanelRow_n2_titleText);
        String contentText2 = a.getString(R.styleable.n2_InfoPanelRow_n2_contentText);
        setTitle((CharSequence) titleText2);
        setContent((CharSequence) contentText2);
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setContent(CharSequence text) {
        this.contentText.setText(text);
    }

    public void setContent(int stringId) {
        setContent((CharSequence) getResources().getString(stringId));
    }

    public AirTextView getContentView() {
        return this.contentText;
    }

    public void setLinkClickListener(LinkOnClickListener listener, String fullText, String linkedText) {
        ClickableLinkUtils.setupClickableTextView((TextView) this.contentText, fullText, linkedText, R.color.n2_canonical_press_darken, listener);
    }

    public static void mock(InfoPanelRow row) {
        row.setTitle((CharSequence) "Title");
        row.setContent((CharSequence) "Content");
    }
}
