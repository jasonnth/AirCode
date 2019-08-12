package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import java.text.DecimalFormat;

/* renamed from: com.airbnb.n2.primitives.StarBar */
public class StarBar extends RelativeLayout {
    @BindView
    StandardsBarContent filledSection;
    private final DecimalFormat percentageFormatter = new DecimalFormat("#");
    @BindView
    AirTextView rightText;
    @BindView
    AirTextView starLabel;

    public StarBar(Context context) {
        super(context);
        init(null);
    }

    public StarBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StarBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_starbar, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        this.filledSection.setThresholdIndicatorVisible(false);
        this.filledSection.setFilledSectionColor(R.color.n2_babu);
        setSelected(true);
    }

    private void setupAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_StarBar, 0, 0);
            setStarLabel(String.valueOf(a.getInt(R.styleable.n2_StarBar_n2_starLabel, -1)));
            a.recycle();
        }
    }

    public void setStarLabel(String label) {
        this.starLabel.setText(label);
    }

    public void setPercentage(float percentage) {
        setPercentage(percentage, this.percentageFormatter);
    }

    public void setPercentage(float percentage, DecimalFormat formatter) {
        this.rightText.setText(getResources().getString(R.string.n2_percentage, new Object[]{formatter.format((double) percentage)}));
        this.filledSection.setValue(percentage / 100.0f);
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
    }
}
