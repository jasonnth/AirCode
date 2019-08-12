package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.TagWithImageAndText */
public class TagWithImageAndText extends LinearLayout {
    @BindView
    AirImageView image;
    @BindView
    AirTextView label;

    public TagWithImageAndText(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_tag_with_image_and_text, this);
        ButterKnife.bind((View) this);
        setOrientation(0);
    }

    public void setImage(int drawable) {
        setImage(AppCompatResources.getDrawable(getContext(), drawable));
    }

    public void setImage(Drawable drawable) {
        if (drawable != null) {
            this.image.setImageDrawable(drawable);
            this.image.setVisibility(0);
        }
    }

    public void setLabel(int stringRes) {
        setLabel((CharSequence) getResources().getString(stringRes));
    }

    public void setLabel(CharSequence text) {
        this.label.setText(text);
    }
}
