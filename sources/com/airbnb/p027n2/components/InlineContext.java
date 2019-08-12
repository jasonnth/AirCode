package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineContext */
public class InlineContext extends LinearLayout {
    @BindView
    AirTextView textViewStatus;
    @BindView
    AirTextView textViewStatusDetails;

    public InlineContext(Context context) {
        super(context);
        init();
    }

    public InlineContext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InlineContext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_inline_context, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setStatusText(CharSequence s) {
        this.textViewStatus.setText(s);
    }

    public void setStatusDetailsText(CharSequence s) {
        if (s == null || s.length() <= 0) {
            this.textViewStatusDetails.setVisibility(8);
            return;
        }
        this.textViewStatusDetails.setVisibility(0);
        this.textViewStatusDetails.setText(s);
    }

    public static void mock(InlineContext view) {
        view.setStatusText("Status");
        view.setStatusDetailsText("Status details");
    }
}
