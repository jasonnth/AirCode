package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.RequirementChecklistRow */
public class RequirementChecklistRow extends LinearLayout implements DividerView {
    @BindView
    AirImageView iconView;
    @BindView
    AirTextView titleText;

    public RequirementChecklistRow(Context context) {
        super(context);
        init(null);
    }

    public RequirementChecklistRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RequirementChecklistRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_requirement_checklist_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(0);
        setLayoutParams(new LayoutParams(-1, -2));
        setPadding(getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_small), 0, getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_small), 0);
        setGravity(48);
        setClickable(false);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.n2_RequirementChecklistRow);
        String titleText2 = ta.getString(R.styleable.n2_RequirementChecklistRow_n2_text);
        setRowDrawable(ViewLibUtils.getDrawableCompat(context, ta, R.styleable.n2_RequirementChecklistRow_n2_icon));
        setTitle((CharSequence) titleText2);
        ta.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
    }

    public void setRowDrawable(Drawable drawable) {
        this.iconView.setImageDrawable(drawable);
    }

    public void setRowDrawable(int drawableRes) {
        this.iconView.setImageResource(drawableRes);
    }

    public static void mock(RequirementChecklistRow view) {
        view.setTitle((CharSequence) "Title");
    }
}
