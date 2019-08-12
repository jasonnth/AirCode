package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.FullImageRow */
public class FullImageRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirImageView imageView;

    public FullImageRow(Context context) {
        super(context);
        init(null);
    }

    public FullImageRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FullImageRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_full_image_row, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setImageDrawable(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public static void mock(FullImageRow row) {
        row.setImageDrawable(R.drawable.n2_ic_camera);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setClickListener(OnClickListener listener) {
        this.imageView.setOnClickListener(listener);
    }
}
