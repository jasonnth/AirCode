package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.RowMeasurementUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.StandardRow */
public class StandardRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    Space extraSubtitleSpace;
    @BindView
    AirTextView extraSubtitleText;
    @BindDimen
    int minInputTextWidth;
    @BindView
    AirImageView rowBadge;
    @BindView
    AirImageView rowDrawable;
    private boolean showRowBadge;
    @BindView
    Space subtitleSpace;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView text;
    @BindView
    ViewGroup textContainer;
    private int titleResourceId;
    @BindView
    AirTextView titleText;

    public StandardRow(Context context) {
        super(context);
        init(null);
    }

    public StandardRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StandardRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_standard_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_StandardRow, 0, 0);
        this.titleResourceId = a.getResourceId(R.styleable.n2_StandardRow_n2_titleText, 0);
        String titleText2 = a.getString(R.styleable.n2_StandardRow_n2_titleText);
        String subtitleText2 = a.getString(R.styleable.n2_StandardRow_n2_subtitleText);
        String inputText = a.getString(R.styleable.n2_StandardRow_n2_inputText);
        String infoText = a.getString(R.styleable.n2_StandardRow_n2_infoText);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_StandardRow_n2_showDivider, true);
        Drawable rowDrawable2 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_StandardRow_n2_image);
        setTitle((CharSequence) titleText2);
        setSubtitleText((CharSequence) subtitleText2);
        if (!TextUtils.isEmpty(inputText)) {
            setActionText((CharSequence) inputText);
        } else if (!TextUtils.isEmpty(infoText)) {
            setInfoText((CharSequence) infoText);
        }
        showDivider(dividerVisible);
        setRowDrawable(rowDrawable2);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (RowMeasurementUtils.resizeRowText(this.textContainer, this.titleText, this.text, this.minInputTextWidth, this.rowDrawable.getMeasuredWidth())) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setTitle(CharSequence text2) {
        this.titleText.setText(text2);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    private void setText(CharSequence text2, int color) {
        showText(!TextUtils.isEmpty(text2));
        this.text.setText(text2);
        this.text.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setActionText(CharSequence inputText) {
        setText(inputText, R.color.n2_text_color_actionable);
    }

    public void setActionText(int stringId) {
        setActionText((CharSequence) getResources().getString(stringId));
    }

    public void setInfoText(CharSequence infoText) {
        setText(infoText, R.color.n2_text_color_main);
    }

    public void setInfoText(int stringId) {
        setInfoText((CharSequence) getResources().getString(stringId));
    }

    public void setPlaceholderText(CharSequence placeholderText) {
        setText(placeholderText, R.color.n2_text_color_muted);
    }

    public void setPlaceholderText(int stringId) {
        setPlaceholderText((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitleText(CharSequence text2) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text2));
        this.subtitleText.setText(text2);
    }

    public void setSubtitleText(int stringId) {
        setSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void setExtraSubtitleText(CharSequence text2) {
        ViewLibUtils.setVisibleIf(this.extraSubtitleText, !TextUtils.isEmpty(text2));
        this.extraSubtitleText.setText(text2);
    }

    public void setExtraSubtitleText(int stringId) {
        setExtraSubtitleText((CharSequence) getResources().getString(stringId));
    }

    private void showText(boolean enable) {
        ViewLibUtils.setVisibleIf(this.text, enable);
        if (enable) {
            this.rowDrawable.setVisibility(8);
            updateRowBadgeVisibility();
        }
    }

    private void showDrawable(boolean enable) {
        ViewLibUtils.setVisibleIf(this.rowDrawable, enable);
        if (enable) {
            this.text.setVisibility(8);
        }
        updateRowBadgeVisibility();
    }

    public void hideRowDrawable(boolean hideDrawable) {
        this.rowDrawable.setVisibility(hideDrawable ? 4 : 0);
        updateRowBadgeVisibility();
    }

    public void setRowDrawable(Drawable drawable) {
        showDrawable(drawable != null);
        this.rowDrawable.setImageDrawable(drawable);
    }

    public void setRowDrawableRes(int drawableRes) {
        showDrawable(drawableRes != 0);
        this.rowDrawable.setImageResource(drawableRes);
    }

    public void showBadge(boolean showBadge) {
        this.showRowBadge = showBadge;
        updateRowBadgeVisibility();
    }

    private void updateRowBadgeVisibility() {
        if (!this.showRowBadge || this.rowDrawable.getVisibility() != 0) {
            this.rowBadge.setVisibility(8);
        } else {
            this.rowBadge.setVisibility(0);
        }
    }

    public void setBackground(int drawableRes) {
        setBackgroundResource(drawableRes);
    }

    public void setRowDrawableClickListener(OnClickListener listener) {
        this.rowDrawable.setOnClickListener(listener);
        boolean clickable = listener != null;
        this.rowDrawable.setClickable(clickable);
        if (clickable) {
            this.rowDrawable.setBackgroundResource(ViewLibUtils.getSelectableItemBackgroundBorderlessResource(getContext()));
        } else {
            this.rowDrawable.setBackground(null);
        }
    }

    public void setFont(Font font) {
        this.text.setFont(font);
        this.titleText.setFont(font);
        this.subtitleText.setFont(font);
        this.extraSubtitleText.setFont(font);
    }

    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        setClickable(l != null);
    }

    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);
        setLongClickable(l != null);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setSubtitleMaxLine(int maxLine) {
        boolean z = true;
        AirTextView airTextView = this.subtitleText;
        if (maxLine != 1) {
            z = false;
        }
        airTextView.setSingleLine(z);
        this.subtitleText.setMaxLines(maxLine);
    }

    public void setTitleMaxLine(int maxLine) {
        boolean z = true;
        AirTextView airTextView = this.titleText;
        if (maxLine != 1) {
            z = false;
        }
        airTextView.setSingleLine(z);
        this.titleText.setMaxLines(maxLine);
    }

    public int getTitleResourceId() {
        return this.titleResourceId;
    }

    public AirTextView getTitleTextView() {
        return this.titleText;
    }

    public void setFullWidthSubtitle(boolean fullWidth) {
        this.subtitleSpace.setVisibility(fullWidth ? 8 : 0);
    }

    public void setFullWidthExtraSubtitle(boolean fullWidth) {
        this.extraSubtitleSpace.setVisibility(fullWidth ? 8 : 0);
    }

    public static void mockBasic(StandardRow row) {
        row.setTitle((CharSequence) "Title");
    }

    public static void mockBasicPlus(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Optional subtitle");
    }

    public static void mockBasicPlusIcon(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setRowDrawableRes(R.drawable.n2_icon_alert);
    }

    public static void mockBasicPlusIconWithBadge(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setRowDrawableRes(R.drawable.n2_icon_alert);
        row.showBadge(true);
    }

    public static void mockInfo(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setInfoText((CharSequence) "Info");
    }

    public static void mockInfoPlus(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setInfoText((CharSequence) "Info");
        row.setSubtitleText((CharSequence) "Optional subtitle");
    }

    public static void mockAction(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setActionText((CharSequence) "Action");
    }

    public static void mockActionPlus(StandardRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Subtitle");
        row.setActionText((CharSequence) "Action");
    }
}
