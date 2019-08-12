package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseComponent;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeRoomItem */
public class SelectHomeRoomItem extends BaseComponent {
    @BindView
    AirTextView descriptionTextView;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView titleTextView;

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_select_home_rooms_item;
    }

    public SelectHomeRoomItem(Context context) {
        super(context);
    }

    public SelectHomeRoomItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectHomeRoomItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public void setOnClickListener(OnClickListener listener) {
        super.setOnClickListener(listener);
        setClickable(listener != null);
    }

    public void setRoomTitle(CharSequence text) {
        this.titleTextView.setText(text);
    }

    public void setRoomDescription(CharSequence text) {
        this.descriptionTextView.setText(text);
    }

    public static void mock(SelectHomeRoomItem card) {
        card.setImage(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large"));
        card.setRoomTitle("Room title");
        card.setRoomDescription("Room description");
    }
}
