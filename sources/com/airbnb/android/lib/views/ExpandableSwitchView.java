package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class ExpandableSwitchView extends LinearLayout {
    @BindView
    CheckBox checkBox;
    private OnCheckedChangeListener checkedChangeListener;
    @BindView
    FrameLayout expandableView;
    @BindView
    TextView titleTextView;
    @BindView
    View topBorder;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean z);
    }

    public ExpandableSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(1);
        LayoutInflater.from(context).inflate(C0880R.layout.expandable_switch, this, true);
        ButterKnife.bind((View) this);
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.ExpandableSwitch);
        setupViews(a);
        a.recycle();
        setOnClickListener(ExpandableSwitchView$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$new$0(ExpandableSwitchView expandableSwitchView, View view) {
        expandableSwitchView.checkBox.setChecked(!expandableSwitchView.checkBox.isChecked());
    }

    private void setupViews(TypedArray a) {
        this.titleTextView.setText(a.getString(C0880R.styleable.ExpandableSwitch_switch_title_text));
        String detailsText = a.getString(C0880R.styleable.ExpandableSwitch_switch_details_text);
        ViewUtils.setVisibleIf(this.topBorder, a.getBoolean(C0880R.styleable.ExpandableSwitch_showTopBorder, false));
        if (!TextUtils.isEmpty(detailsText)) {
            TextView mDetailsTextView = (TextView) findViewById(C0880R.C0882id.details_text_view);
            mDetailsTextView.setText(detailsText);
            mDetailsTextView.setVisibility(0);
        }
        boolean checked = a.getBoolean(C0880R.styleable.ExpandableSwitch_checked, false);
        this.checkBox.setChecked(checked);
        this.expandableView.addView(LayoutInflater.from(getContext()).inflate(a.getResourceId(C0880R.styleable.ExpandableSwitch_layout_resource, 0), this.expandableView, false));
        ViewUtils.setVisibleIf((View) this.expandableView, checked);
        this.checkBox.setOnCheckedChangeListener(ExpandableSwitchView$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupViews$1(ExpandableSwitchView expandableSwitchView, CompoundButton buttonView, boolean isChecked) {
        ViewUtils.setVisibleIf((View) expandableSwitchView.expandableView, isChecked);
        if (expandableSwitchView.checkedChangeListener != null) {
            expandableSwitchView.checkedChangeListener.onCheckedChanged(isChecked);
        }
    }

    public void setTitleText(int stringId) {
        this.titleTextView.setText(stringId);
    }

    public void setTitleText(String text) {
        this.titleTextView.setText(text);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.checkedChangeListener = listener;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    public void setChecked(boolean checked) {
        this.checkBox.setChecked(checked);
    }
}
