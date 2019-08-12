package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ButtonBar */
public class ButtonBar extends LinearLayout implements DividerView {
    @BindView
    View button1;
    @BindView
    View button2;
    @BindView
    View button3;
    @BindView
    View button4;
    @BindView
    View divider;
    @BindView
    AirImageView icon1;
    @BindView
    AirImageView icon2;
    @BindView
    AirImageView icon3;
    @BindView
    AirImageView icon4;
    @BindView
    AirTextView label1;
    @BindView
    AirTextView label2;
    @BindView
    AirTextView label3;
    @BindView
    AirTextView label4;

    public ButtonBar(Context context) {
        super(context);
        init(null);
    }

    public ButtonBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ButtonBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_button_bar, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ButtonBar, 0, 0);
        int numberOfButtons = a.getInt(R.styleable.n2_ButtonBar_n2_numberOfButtons, 4);
        setNumberOfButtons(numberOfButtons);
        String label12 = a.getString(R.styleable.n2_ButtonBar_n2_label1);
        String label22 = a.getString(R.styleable.n2_ButtonBar_n2_label2);
        String label32 = a.getString(R.styleable.n2_ButtonBar_n2_label3);
        String label42 = a.getString(R.styleable.n2_ButtonBar_n2_label4);
        Drawable icon12 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_ButtonBar_n2_icon1);
        Drawable icon22 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_ButtonBar_n2_icon2);
        Drawable icon32 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_ButtonBar_n2_icon3);
        Drawable icon42 = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_ButtonBar_n2_icon4);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_ButtonBar_n2_showDivider, true);
        setLabel1((CharSequence) label12);
        setLabel2((CharSequence) label22);
        setIcon1(icon12);
        setIcon2(icon22);
        if (numberOfButtons > 2) {
            setLabel3((CharSequence) label32);
            setIcon3(icon32);
        } else if (!TextUtils.isEmpty(label32) || icon32 != null) {
            throw new IllegalStateException("Label or icon specified for button 3 when numberOfButtons is 2");
        }
        if (numberOfButtons > 3) {
            setLabel4((CharSequence) label42);
            setIcon4(icon42);
        } else if (!TextUtils.isEmpty(label42) || icon42 != null) {
            throw new IllegalStateException("Label or icon specified for button 4 when numberOfButtons is less than 4");
        }
        showDivider(dividerVisible);
        a.recycle();
    }

    public void setNumberOfButtons(int numberOfButtons) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (numberOfButtons > 4) {
            throw new IllegalStateException("Can't use " + getClass().getSimpleName() + " with " + numberOfButtons + " buttons. Must be less than 4.");
        }
        if (numberOfButtons < 1) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setGoneIf(this, z);
        View view = this.button2;
        if (numberOfButtons < 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setGoneIf(view, z2);
        View view2 = this.button3;
        if (numberOfButtons < 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setGoneIf(view2, z3);
        View view3 = this.button4;
        if (numberOfButtons >= 4) {
            z4 = false;
        }
        ViewLibUtils.setGoneIf(view3, z4);
    }

    public void setLabel1(CharSequence text) {
        this.label1.setText(text);
    }

    public void setLabel1(int stringId) {
        this.label1.setText(stringId);
    }

    public void setIcon1(Drawable icon) {
        this.icon1.setImageDrawable(icon);
    }

    public void setIcon1(int icon) {
        this.icon1.setImageDrawableCompat(icon);
    }

    public void setLabel2(CharSequence text) {
        this.label2.setText(text);
    }

    public void setLabel2(int stringId) {
        this.label2.setText(stringId);
    }

    public void setIcon2(Drawable icon) {
        this.icon2.setImageDrawable(icon);
    }

    public void setIcon2(int icon) {
        this.icon2.setImageDrawableCompat(icon);
    }

    public void setLabel3(CharSequence text) {
        this.label3.setText(text);
    }

    public void setLabel3(int stringId) {
        this.label3.setText(stringId);
    }

    public void setIcon3(Drawable icon) {
        this.icon3.setImageDrawable(icon);
    }

    public void setIcon3(int icon) {
        this.icon3.setImageDrawableCompat(icon);
    }

    public void setLabel4(CharSequence text) {
        this.label4.setText(text);
    }

    public void setLabel4(int stringId) {
        this.label4.setText(stringId);
    }

    public void setIcon4(Drawable icon) {
        this.icon4.setImageDrawable(icon);
    }

    public void setIcon4(int icon) {
        this.icon4.setImageDrawableCompat(icon);
    }

    public void setOnClickListener1(OnClickListener listener) {
        this.button1.setOnClickListener(listener);
    }

    public void setOnClickListener2(OnClickListener listener) {
        this.button2.setOnClickListener(listener);
    }

    public void setOnClickListener3(OnClickListener listener) {
        this.button3.setOnClickListener(listener);
    }

    public void setOnClickListener4(OnClickListener listener) {
        this.button4.setOnClickListener(listener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock2(ButtonBar bar) {
        bar.setIcon1(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel1((CharSequence) "Label");
        bar.setIcon2(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel2((CharSequence) "Label");
        bar.setNumberOfButtons(2);
    }

    public static void mock3(ButtonBar bar) {
        bar.setLabel1((CharSequence) "Label with wrapping");
        bar.setIcon1(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel2((CharSequence) "Label with wrapping");
        bar.setIcon2(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel3((CharSequence) "Label with wrapping");
        bar.setIcon3(R.drawable.n2_ic_placeholder_line_message);
        bar.setNumberOfButtons(3);
    }

    public static void mock4(ButtonBar bar) {
        bar.setLabel1((CharSequence) "Label");
        bar.setIcon1(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel2((CharSequence) "Label");
        bar.setIcon2(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel3((CharSequence) "Label");
        bar.setIcon3(R.drawable.n2_ic_placeholder_line_message);
        bar.setLabel4((CharSequence) "Label");
        bar.setIcon4(R.drawable.n2_ic_placeholder_line_message);
        bar.setNumberOfButtons(4);
    }
}
