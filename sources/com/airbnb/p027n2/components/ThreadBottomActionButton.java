package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ThreadBottomActionButton */
public class ThreadBottomActionButton extends FrameLayout {
    @BindView
    AirTextView textView;

    public ThreadBottomActionButton(Context context) {
        this(context, null);
    }

    public ThreadBottomActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreadBottomActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_thread_bottom_action_button, this);
        ButterKnife.bind((View) this);
        setUpAttributes(attrs);
    }

    private void setUpAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ThreadBottomActionButton, 0, 0);
        setText((CharSequence) a.getString(R.styleable.n2_ThreadBottomActionButton_n2_text));
        a.recycle();
    }

    public void setText(int stringRes) {
        this.textView.setText(stringRes);
    }

    public void setText(CharSequence buttonText) {
        this.textView.setText(buttonText);
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public AirTextView getTextView() {
        return this.textView;
    }

    public static void mock(ThreadBottomActionButton view) {
        view.setText((CharSequence) "Text");
    }
}
