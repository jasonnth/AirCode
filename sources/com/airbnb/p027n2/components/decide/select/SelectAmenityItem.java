package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.n2.components.decide.select.SelectAmenityItem */
public class SelectAmenityItem extends LinearLayout {
    @BindView
    AirTextView amenityNameTextView;
    @BindView
    AirImageView imageView;

    public SelectAmenityItem(Context context) {
        super(context);
        init(null);
    }

    public SelectAmenityItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SelectAmenityItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_select_amenity_item, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setAmenityName(CharSequence text) {
        this.amenityNameTextView.setText(text);
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public static void mock(SelectAmenityItem card) {
        card.setImage(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large"));
        card.setAmenityName("Amenity");
    }
}
