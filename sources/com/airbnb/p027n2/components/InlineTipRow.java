package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.InlineTipRow */
public class InlineTipRow extends BaseDividerComponent {
    @BindView
    ImageView closeView;
    @BindView
    AirTextView tipText;

    public InlineTipRow(Context context) {
        super(context);
    }

    public InlineTipRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InlineTipRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_inline_tip_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InlineTipRow);
        String text = ta.getString(R.styleable.n2_InlineTipRow_n2_text);
        ta.recycle();
        setText(text);
    }

    public void setText(CharSequence text) {
        this.tipText.setText(text);
    }

    public void setHintClickListener(OnClickListener clickListener) {
        this.tipText.setClickable(clickListener != null);
        this.tipText.setOnClickListener(clickListener);
    }

    public void setCloseClickListener(OnClickListener closeClickListener) {
        this.closeView.setOnClickListener(closeClickListener);
    }

    public void setDismissible(boolean dismissible) {
        ViewLibUtils.setVisibleIf(this.closeView, dismissible);
    }

    public static void mock(InlineTipRow view) {
        view.setText("Text");
    }
}
