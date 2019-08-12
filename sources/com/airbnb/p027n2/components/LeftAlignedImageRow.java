package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.LayoutParamsStyleApplier.Option;
import com.airbnb.paris.Paris;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.LeftAlignedImageRow */
public class LeftAlignedImageRow extends BaseDividerComponent {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public LeftAlignedImageRow(Context context) {
        super(context);
    }

    public LeftAlignedImageRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftAlignedImageRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_left_aligned_image_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LeftAlignedImageRow);
        Drawable image = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_LeftAlignedImageRow_n2_image);
        if (image != null) {
            setImage(image);
        }
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int resId) {
        setTitle((CharSequence) getResources().getString(resId));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text);
    }

    public void setSubtitle(int resId) {
        setSubtitle((CharSequence) getResources().getString(resId));
    }

    /* access modifiers changed from: 0000 */
    public void setImageStyle(int styleRes) {
        ((ViewStyleApplier) Paris.style((View) this.imageView).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImage(int resId) {
        this.imageView.setImageResource(resId);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public static void mock(LeftAlignedImageRow row) {
        row.setTitle((CharSequence) "An image row");
        row.setSubtitle((CharSequence) "An optional subtitle");
        row.setImage(R.drawable.n2_ic_am_private_entrance);
    }

    public static void mockTitleOnly(LeftAlignedImageRow row) {
        row.setTitle((CharSequence) "888 Brannan St, San Francisco, CA 94103");
        row.setImage(R.drawable.n2_ic_am_private_entrance);
    }

    public static void mockLongTitle(LeftAlignedImageRow row) {
        row.setTitle((CharSequence) "30 Lawnmarket Flat, 7/11 James Court Edinburgh, Midlothian, EH1 2PB, UK");
        row.setImage(R.drawable.n2_ic_am_private_entrance);
    }
}
