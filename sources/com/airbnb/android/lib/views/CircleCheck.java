package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;

public class CircleCheck extends FrameLayout {
    @BindView
    ImageView checkImage;
    private boolean isChecked;
    @BindView
    TextView text;

    public CircleCheck(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CircleCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleCheck(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0880R.layout.circle_check, this, true));
        this.checkImage.setImageResource(C0880R.C0881drawable.gray_circle_stroke);
    }

    public void setText(int stringRes) {
        this.text.setText(stringRes);
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
        this.checkImage.setImageResource(this.isChecked ? C0880R.C0881drawable.rausch_circle : C0880R.C0881drawable.gray_circle_stroke);
    }

    public boolean isChecked() {
        return this.isChecked;
    }
}
