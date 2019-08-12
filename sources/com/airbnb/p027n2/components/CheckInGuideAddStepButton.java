package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.CheckInGuideAddStepButton */
public class CheckInGuideAddStepButton extends FrameLayout {
    @BindView
    AirButton button;

    public CheckInGuideAddStepButton(Context context) {
        super(context);
        init(null);
    }

    public CheckInGuideAddStepButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CheckInGuideAddStepButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_checkin_guide_add_step_button, this);
        ButterKnife.bind((View) this);
    }

    public void setText(CharSequence note) {
        this.button.setText(note);
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public static void mock(CheckInGuideAddStepButton card) {
        card.setText("Add another step");
        card.setOnClickListener(CheckInGuideAddStepButton$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mock$0(View v) {
    }
}
