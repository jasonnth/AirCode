package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHostRow */
public class SelectHostRow extends BaseDividerComponent {
    @BindView
    AirButton contactHostButton;
    @BindView
    HaloImageView hostAvatar;
    @BindView
    AirTextView hostJoinedTextView;
    @BindView
    AirTextView hostRowDescription;

    public SelectHostRow(Context context) {
        super(context);
    }

    public SelectHostRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectHostRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_select_host_row;
    }

    public void setTitle(CharSequence text) {
        this.hostJoinedTextView.setText(text);
    }

    public void setDescriptionText(CharSequence text) {
        this.hostRowDescription.setText(text);
    }

    public void setContactHostButtonText(CharSequence text) {
        this.contactHostButton.setText(text);
    }

    public void setHostImage(Image image) {
        this.hostAvatar.setImage(image);
    }

    public void setContactHostButtonClickListener(OnClickListener listener) {
        this.contactHostButton.setOnClickListener(listener);
    }

    public static void mock(SelectHostRow row) {
        row.setTitle("Joined April 2015");
        row.setDescriptionText("Your host will be on-site and available for anything you need during your stay.");
        row.setContactHostButtonText("Contact host");
        row.setHostImage(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=profile_x_medium"));
    }
}
