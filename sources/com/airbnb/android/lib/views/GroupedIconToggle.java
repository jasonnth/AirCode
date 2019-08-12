package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class GroupedIconToggle extends RelativeLayout {
    @BindView
    LinearLayout iconToggles;
    @BindView
    AirTextView title;
    @BindView
    View topBorder;

    public GroupedIconToggle(Context context) {
        super(context);
        init(context, null);
    }

    public GroupedIconToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupedIconToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        boolean z = true;
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0880R.layout.grouped_icon_toggle, this, true));
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedIconToggle, 0, 0);
        String titleText = a.getString(C0880R.styleable.GroupedIconToggle_title);
        if (this.title != null || !BuildHelper.isDevelopmentBuild()) {
            this.title.setText(titleText);
            int iconLayout = a.getResourceId(C0880R.styleable.GroupedIconToggle_icon_layout, 0);
            if (iconLayout != 0) {
                LayoutInflater.from(context).inflate(iconLayout, this.iconToggles, true);
                boolean hideTopBorder = a.getBoolean(C0880R.styleable.GroupedCell_hideTopBorder, false);
                View view = this.topBorder;
                if (hideTopBorder) {
                    z = false;
                }
                ViewUtils.setVisibleIf(view, z);
                if (a.getBoolean(C0880R.styleable.GroupedIconToggle_fullWidthBorder, false)) {
                    MarginLayoutParams params = (MarginLayoutParams) this.topBorder.getLayoutParams();
                    params.setMargins(0, 0, 0, 0);
                    this.topBorder.setLayoutParams(params);
                }
                a.recycle();
            } else if (BuildHelper.isDevelopmentBuild()) {
                throw new IllegalArgumentException("You didn't supply an icon layout to a GroupedIconToggle");
            }
        } else {
            throw new IllegalArgumentException("You didn't supply a title to a GroupedIconToggle");
        }
    }

    public void setIconToggleListener(int iconId, OnClickListener listener) {
        ButterKnife.findById((View) this.iconToggles, iconId).setOnClickListener(listener);
    }
}
