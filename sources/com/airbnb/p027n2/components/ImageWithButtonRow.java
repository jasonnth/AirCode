package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ImageWithButtonRow */
public class ImageWithButtonRow extends LinearLayout {
    @BindView
    AirButton buttonView;
    @BindView
    AirImageView imageView;

    public ImageWithButtonRow(Context context) {
        super(context);
        init(null);
    }

    public ImageWithButtonRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImageWithButtonRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_image_with_button_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setImageDrawable(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public void setButtonText(CharSequence text) {
        this.buttonView.setText(text);
    }

    public void setButtonText(int stringRes) {
        setButtonText((CharSequence) getResources().getString(stringRes));
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.buttonView.setOnClickListener(listener);
    }

    public static void mock(ImageWithButtonRow row) {
        row.setButtonText((CharSequence) "Button");
    }
}
