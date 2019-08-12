package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.NestedListingRow */
public class NestedListingRow extends RelativeLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirImageView imageDrawable;
    @BindView
    AirImageView rowDrawable;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public NestedListingRow(Context context) {
        super(context);
        init(null);
    }

    public NestedListingRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NestedListingRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_nested_listing_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_NestedListingRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_NestedListingRow_n2_titleText);
        String subtitleText2 = a.getString(R.styleable.n2_NestedListingRow_n2_subtitleText);
        Drawable imageDrawable2 = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_NestedListingRow_n2_image);
        Drawable rowDrawable2 = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_NestedListingRow_n2_rowDrawable);
        setTitle((CharSequence) titleText2);
        setSubtitleText((CharSequence) subtitleText2);
        setImageDrawable(imageDrawable2);
        setRowDrawable(rowDrawable2);
        a.recycle();
    }

    public void setImageUrl(String imageUrl) {
        this.imageDrawable.setImageUrl(imageUrl);
    }

    public void setImageDrawable(int drawableRes) {
        this.imageDrawable.setImageResource(drawableRes);
    }

    public void setImageDrawable(Drawable drawable) {
        this.imageDrawable.setImageDrawable(drawable);
    }

    public void setRowDrawable(int drawableRes) {
        this.rowDrawable.setImageResource(drawableRes);
    }

    public void setRowDrawable(Drawable drawable) {
        this.rowDrawable.setImageDrawable(drawable);
    }

    public void clearImage() {
        this.imageDrawable.clear();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setSubtitleText(int stringId) {
        setSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(NestedListingRow view) {
        view.setTitle((CharSequence) "The base listing that you want to link other listings to");
        view.setSubtitleText((CharSequence) "Private Room");
        view.setImageDrawable(R.drawable.n2_ic_camera);
        view.setRowDrawable(R.drawable.n2_standard_row_right_caret_gray);
    }
}
