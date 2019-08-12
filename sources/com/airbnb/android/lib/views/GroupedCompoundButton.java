package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public abstract class GroupedCompoundButton extends RelativeLayout {
    protected CompoundButton compoundButton;
    @BindView
    ViewStub compoundButtonStub;
    @BindView
    TextView subtitle;
    @BindView
    TextView title;
    @BindView
    GroupedTooltip tooltip;
    @BindView
    View topBorder;

    /* access modifiers changed from: protected */
    public abstract int getCompoundButtonLayoutRes();

    public GroupedCompoundButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public GroupedCompoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupedCompoundButton(Context context) {
        this(context, null);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        boolean z = true;
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(getLayoutRes(), this, true));
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedCompoundButton, 0, 0);
            String titleText = a.getString(C0880R.styleable.GroupedCompoundButton_title);
            String contentText = a.getString(C0880R.styleable.GroupedCompoundButton_content);
            boolean hideTopBorder = a.getBoolean(C0880R.styleable.GroupedCompoundButton_hideTopBorder, false);
            boolean fullWidthBorder = a.getBoolean(C0880R.styleable.GroupedCompoundButton_fullWidthBorder, false);
            int maxLines = a.getInteger(C0880R.styleable.GroupedCompoundButton_maxLines, 1);
            a.recycle();
            setMaxLines(maxLines);
            this.title.setText(titleText);
            if (hideTopBorder) {
                z = false;
            }
            showTopBorder(z);
            setFullWidthBorder(fullWidthBorder);
            if (!TextUtils.isEmpty(contentText)) {
                this.subtitle.setText(contentText);
            } else {
                this.subtitle.setVisibility(8);
            }
        }
        if (getBackground() == null) {
            setBackgroundResource(C0880R.C0881drawable.c_bg_transparent);
        }
        this.compoundButtonStub.setLayoutResource(getCompoundButtonLayoutRes());
        this.compoundButton = (CompoundButton) this.compoundButtonStub.inflate();
        super.setOnClickListener(GroupedCompoundButton$$Lambda$1.lambdaFactory$(this));
    }

    public void setFullWidthBorder(boolean fullWidthBorder) {
        MarginLayoutParams params = (MarginLayoutParams) this.topBorder.getLayoutParams();
        if (fullWidthBorder) {
            params.setMargins(0, 0, 0, 0);
        } else {
            int margin = getResources().getDimensionPixelSize(C0880R.dimen.gutter_padding);
            params.setMargins(margin, 0, margin, 0);
        }
        this.topBorder.setLayoutParams(params);
    }

    /* access modifiers changed from: protected */
    public int getLayoutRes() {
        return C0880R.layout.grouped_compound_button;
    }

    public void setTitle(String title2) {
        this.title.setText(title2);
    }

    public void setTitle(int titleRes) {
        this.title.setText(titleRes);
    }

    public void setSubtitle(int subtitleRes) {
        this.subtitle.setText(subtitleRes);
        this.subtitle.setVisibility(0);
    }

    public void setTitle(Spanned title2) {
        this.title.setText(title2);
    }

    public TextView getTitle() {
        return this.title;
    }

    public void setMaxLines(int maxLines) {
        this.title.setMaxLines(maxLines);
    }

    public void showTopBorder(boolean show) {
        ViewUtils.setVisibleIf(this.topBorder, show);
    }

    public void showSubtitle(boolean show) {
        ViewUtils.setVisibleIf((View) this.subtitle, show);
    }

    public void showTooltip(boolean show) {
        ViewUtils.setVisibleIf((View) this.tooltip, show);
    }

    public boolean isChecked() {
        return this.compoundButton.isChecked();
    }

    public void setChecked(boolean checked) {
        if (this.compoundButton.isChecked() != checked) {
            this.compoundButton.setChecked(checked);
        }
    }

    public CompoundButton getCompoundButton() {
        return this.compoundButton;
    }

    public void toggle() {
        this.compoundButton.toggle();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.compoundButton.setOnCheckedChangeListener(listener);
    }

    public GroupedTooltip getTooltip() {
        return this.tooltip;
    }

    public void setTitleColor(int colorRes) {
        this.title.setTextColor(getResources().getColor(colorRes));
    }
}
