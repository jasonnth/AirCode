package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BedDetailsCard */
public class BedDetailsCard extends LinearLayout {
    @BindView
    AirTextView descriptionView;
    @BindView
    AirTextView iconsView;
    @BindView
    AirTextView titleView;

    public BedDetailsCard(Context context) {
        super(context);
        init(null);
    }

    public BedDetailsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BedDetailsCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_bed_details_card, this);
        ButterKnife.bind((View) this);
    }

    public void setIcons(int textRes) {
        setIcons((CharSequence) getContext().getString(textRes));
    }

    public void setIcons(CharSequence text) {
        this.iconsView.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getContext().getString(textRes));
    }

    public void setTitle(CharSequence text) {
        this.titleView.setText(text);
    }

    public void setDescription(int textRes) {
        setDescription((CharSequence) getContext().getString(textRes));
    }

    public void setDescription(CharSequence text) {
        this.descriptionView.setText(text);
    }

    public static void mock(BedDetailsCard card) {
        card.setIcons((CharSequence) "<icons go here>");
        card.setTitle((CharSequence) "Common spaces");
        card.setDescription((CharSequence) "1 sofa bed, 2 couches");
    }

    public static void mockWithShortText(BedDetailsCard card) {
        card.setIcons((CharSequence) "<icons>");
        card.setTitle((CharSequence) "Room");
        card.setDescription((CharSequence) "a bed");
    }

    public static void mockWithText(BedDetailsCard card) {
        card.setIcons((CharSequence) "<icons go here>");
        card.setTitle((CharSequence) "Bedroom 1");
        card.setDescription((CharSequence) "2 king beds, 1 queen bed, 2 twin beds, 1 crib, 3 hammocks");
    }
}
