package com.airbnb.p027n2.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.CategorizedFilterButtons */
public class CategorizedFilterButtons extends LinearLayout {
    @BindView
    LinearLayout button1;
    @BindView
    ImageView button1CloseIcon;
    @BindView
    View button1Divider;
    @BindView
    AirTextView button1Text;
    @BindView
    LinearLayout button2;
    @BindView
    ImageView button2CloseIcon;
    @BindView
    View button2Divider;
    @BindView
    AirTextView button2Text;
    @BindView
    LinearLayout button3;
    @BindView
    ImageView button3CloseIcon;
    @BindView
    AirTextView button3Text;
    @BindView
    LinearLayout buttonContainer;
    @BindView
    AirTextView title;

    public CategorizedFilterButtons(Context context) {
        super(context);
        init(null);
    }

    public CategorizedFilterButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CategorizedFilterButtons(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @SuppressLint({"NewApi"})
    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_categorized_filter_buttons, this);
        ButterKnife.bind((View) this);
        setOrientation(0);
        setGravity(16);
    }

    public void setTitleText(CharSequence text) {
        this.title.setText(text);
        ViewLibUtils.setGoneIf(this.title, TextUtils.isEmpty(text));
    }

    public void setButton1Text(CharSequence text) {
        this.button1Text.setText(text);
    }

    public void setButton1ClickListener(OnClickListener clickListener) {
        this.button1.setOnClickListener(clickListener);
    }

    public void setButton2Text(CharSequence text) {
        this.button2Text.setText(text);
    }

    public void setButton2ClickListener(OnClickListener clickListener) {
        this.button2.setOnClickListener(clickListener);
    }

    public void setButton3Text(CharSequence text) {
        this.button3Text.setText(text);
    }

    public void setButton3ClickListener(OnClickListener clickListener) {
        this.button3.setOnClickListener(clickListener);
    }

    public void setButton1Selected(boolean selected) {
        setButtonSelected(selected, this.button1Text, this.button1CloseIcon);
    }

    public void setButton2Selected(boolean selected) {
        setButtonSelected(selected, this.button2Text, this.button2CloseIcon);
    }

    public void setButton3Selected(boolean selected) {
        setButtonSelected(selected, this.button3Text, this.button3CloseIcon);
    }

    private void setButtonSelected(boolean selected, AirTextView buttonText, ImageView closeIcon) {
        buttonText.setSelected(selected);
        ViewLibUtils.setVisibleIf(closeIcon, selected);
    }

    public void setBackground(boolean selected) {
        this.buttonContainer.setBackground(AppCompatResources.getDrawable(getContext(), selected ? R.drawable.n2_categorized_filter_buttons_background_selected : R.drawable.n2_categorized_filter_buttons_background));
    }

    public void setMode(int mode) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        ViewLibUtils.setVisibleIf(this.button1, mode == 1 || mode == 2 || mode == 3);
        LinearLayout linearLayout = this.button2;
        if (mode == 2 || mode == 3) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(linearLayout, z);
        View view = this.button1Divider;
        if (mode == 2 || mode == 3) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(view, z2);
        LinearLayout linearLayout2 = this.button3;
        if (mode == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setVisibleIf(linearLayout2, z3);
        View view2 = this.button2Divider;
        if (mode != 3) {
            z4 = false;
        }
        ViewLibUtils.setVisibleIf(view2, z4);
    }

    public static void mock(CategorizedFilterButtons view) {
        view.setMode(3);
        view.setTitleText("Room type");
        view.setButton1Text("Entire home");
        view.setButton2Text("Private room");
        view.setButton3Text("Shared room");
    }
}
