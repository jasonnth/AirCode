package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;

public class GroupedRadioCheck extends GroupedCheck {

    public interface OnCheckedChangeListener {
        void onCheckedChanged(GroupedRadioCheck groupedRadioCheck, boolean z);
    }

    public GroupedRadioCheck(Context context) {
        super(context, null);
    }

    public GroupedRadioCheck(Context context, int numLines) {
        super(context, null);
        init(numLines);
    }

    public GroupedRadioCheck(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(-1);
    }

    public GroupedRadioCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(-1);
    }

    private void init(int numLines) {
        if (numLines != -1) {
            this.title.setLines(numLines);
        }
        this.compoundButton.setClickable(false);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        setOnCheckedChangeListener(GroupedRadioCheck$$Lambda$1.lambdaFactory$(this, listener));
    }

    public void toggle() {
        if (!isChecked()) {
            this.compoundButton.toggle();
        }
    }
}
