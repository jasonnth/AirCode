package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.LeftIconRow */
public class LeftIconRow extends BaseComponent {
    @BindView
    AirImageView icon;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public LeftIconRow(Context context) {
        super(context);
    }

    public LeftIconRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftIconRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_left_icon_row;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text, true);
    }

    public void setImage(int drawableRes) {
        this.icon.setImageResource(drawableRes);
    }

    public void setImage(Image image) {
        this.icon.setImage(image);
    }

    public static void mockDefault(LeftIconRow row) {
        row.setImage(R.drawable.n2_ic_am_dog);
        row.setTitle((CharSequence) "Entire 3br 2ba townhouse");
        row.setSubtitle("Accomodates 8 guests");
    }

    public static void mockNoSubtitle(LeftIconRow row) {
        row.setImage(R.drawable.n2_ic_am_dog);
        row.setTitle((CharSequence) "Entire 3br 2ba townhouse");
    }
}
