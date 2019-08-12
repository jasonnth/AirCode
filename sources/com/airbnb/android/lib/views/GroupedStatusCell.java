package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class GroupedStatusCell extends GroupedCell {
    @BindView
    protected ColorizedIconView statusIcon;

    public GroupedStatusCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public GroupedStatusCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupedStatusCell(Context context) {
        this(context, null);
        init(context, null);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        TypedArray a = null;
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedStatusCell, 0, 0);
        }
        if (a != null) {
            setStatusIconVisibility(a.getBoolean(C0880R.styleable.GroupedStatusCell_withStatusIcon, false));
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.grouped_selected_cell;
    }

    public void setStatusIconVisibility(boolean isVisible) {
        ViewUtils.setInvisibleIf(this.statusIcon, !isVisible);
    }

    public void setContent(CharSequence content) {
        this.mContent.setText(content);
    }

    public void setContentVisibility(int visibility) {
        this.mContent.setVisibility(visibility);
    }

    public String toString() {
        return "GroupedStatusCell: " + this.mTitle.getText().toString() + " --> " + this.mContent.getText().toString();
    }
}
