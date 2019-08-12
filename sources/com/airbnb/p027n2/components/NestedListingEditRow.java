package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
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

/* renamed from: com.airbnb.n2.components.NestedListingEditRow */
public class NestedListingEditRow extends RelativeLayout implements DividerView {
    @BindView
    AirTextView actionText;
    @BindView
    View divider;
    @BindView
    AirImageView imageDrawable;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public NestedListingEditRow(Context context) {
        super(context);
        init(null);
    }

    public NestedListingEditRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NestedListingEditRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public NestedListingEditRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_nested_listing_edit_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_NestedListingEditRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_NestedListingEditRow_n2_titleText);
        String subtitleText2 = a.getString(R.styleable.n2_NestedListingEditRow_n2_subtitleText);
        Drawable imageDrawable2 = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_NestedListingEditRow_n2_image);
        setTitle((CharSequence) titleText2);
        setSubtitleText((CharSequence) subtitleText2);
        setImageDrawable(imageDrawable2);
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

    public void setActionText(int stringId) {
        setActionText((CharSequence) getResources().getString(stringId));
    }

    public void setActionText(CharSequence text) {
        this.actionText.setText(text);
    }

    public void setSubtitleText(int stringId) {
        setSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(NestedListingEditRow view) {
        view.setTitle((CharSequence) "The base listing that you want to link other listings to");
        view.setSubtitleText((CharSequence) "Private Room");
        view.setActionText((CharSequence) "Edit");
        view.setImageDrawable(R.drawable.n2_ic_camera);
    }
}
