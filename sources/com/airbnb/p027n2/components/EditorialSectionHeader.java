package com.airbnb.p027n2.components;

import android.content.Context;
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
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.EditorialSectionHeader */
public class EditorialSectionHeader extends LinearLayout implements DividerView {
    @BindView
    AirTextView button;
    @BindView
    AirTextView description;
    @BindView
    AirTextView title;
    @BindView
    View topSpace;

    public EditorialSectionHeader(Context context) {
        super(context);
        init();
    }

    public EditorialSectionHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditorialSectionHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_editorial_section_header, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        this.title.setText(textRes);
    }

    public void setDescription(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.description, !TextUtils.isEmpty(text));
        this.description.setText(text);
    }

    public void setDescription(int textRes) {
        setDescription((CharSequence) getResources().getString(textRes));
    }

    public void setButtonText(int stringRes) {
        setButtonText((CharSequence) getContext().getString(stringRes));
    }

    public void setButtonText(CharSequence buttonText) {
        ViewLibUtils.setGoneIf(this.button, TextUtils.isEmpty(buttonText));
        this.button.setText(buttonText);
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.topSpace, showDivider);
    }

    public static void mock(EditorialSectionHeader header) {
        header.setTitle((CharSequence) "Title");
        header.setButtonText((CharSequence) "Button");
    }
}
