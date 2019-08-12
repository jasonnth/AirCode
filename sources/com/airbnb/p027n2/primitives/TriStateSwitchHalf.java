package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.TriStateSwitchHalf */
public class TriStateSwitchHalf extends AirImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private boolean isChecked = false;
    private OnCheckedChangeListener listener;

    /* renamed from: com.airbnb.n2.primitives.TriStateSwitchHalf$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(TriStateSwitchHalf triStateSwitchHalf, boolean z);
    }

    public TriStateSwitchHalf(Context context) {
        super(context);
        init();
    }

    public TriStateSwitchHalf(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TriStateSwitchHalf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
    }

    public void setOnChangeListener(OnCheckedChangeListener listener2) {
        this.listener = listener2;
    }

    public void setChecked(boolean isChecked2) {
        if (this.isChecked != isChecked2) {
            this.isChecked = isChecked2;
            refreshDrawableState();
            if (this.listener != null) {
                this.listener.onCheckedChanged(this, isChecked2);
            }
        }
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void toggle() {
        setChecked(!this.isChecked);
    }

    public boolean performClick() {
        setChecked(true);
        return super.performClick();
    }

    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
