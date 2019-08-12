package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton.State;

/* renamed from: com.airbnb.n2.primitives.AirButtonExpandable */
public class AirButtonExpandable extends AirButton {
    public AirButtonExpandable(Context context) {
        super(context);
    }

    public AirButtonExpandable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AirButtonExpandable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setButtonText(String text) {
        if (getState() == State.Normal) {
            setText(text);
            if (TextUtils.isEmpty(text)) {
                setWidthFixed();
            } else {
                setWidthWrapContent();
            }
        }
    }

    public void setState(State state) {
        super.setState(state);
        if (getState() != State.Normal) {
            setWidthFixed();
        } else {
            setWidthWrapContent();
        }
    }

    private void setWidthWrapContent() {
        LayoutParams param = getLayoutParams();
        param.width = -2;
        setLayoutParams(param);
    }

    private void setWidthFixed() {
        LayoutParams param = getLayoutParams();
        param.width = (int) getResources().getDimension(R.dimen.n2_airbutton_min_width);
        setLayoutParams(param);
    }
}
