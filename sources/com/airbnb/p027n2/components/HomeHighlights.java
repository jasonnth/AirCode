package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.HomeHighlights */
public class HomeHighlights extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    ViewGroup horizontalIconContainer;

    /* renamed from: com.airbnb.n2.components.HomeHighlights$IconRowData */
    public static class IconRowData {
        final int icon;
        final String label;

        public IconRowData(int icon2, String label2) {
            this.icon = icon2;
            this.label = label2;
        }
    }

    /* renamed from: com.airbnb.n2.components.HomeHighlights$IconViewHolder */
    static class IconViewHolder {
        private IconRowData data;
        private final ImageView icon = ((ImageView) ViewLibUtils.findById(this.view, R.id.icon));
        private final TextView title = ((TextView) ViewLibUtils.findById(this.view, R.id.label));
        /* access modifiers changed from: private */
        public final View view;

        public IconViewHolder(Context context) {
            this.view = View.inflate(context, R.layout.n2_home_highlights_icon_item, null);
            this.view.setTag(this);
        }

        static IconViewHolder fromView(View view2) {
            return (IconViewHolder) view2.getTag();
        }

        public void setData(IconRowData data2) {
            if (this.data == null || this.data.icon != data2.icon) {
                this.icon.setImageResource(data2.icon);
            }
            if (this.data == null || !this.data.label.equals(data2.label)) {
                this.title.setText(data2.label);
            }
            this.data = data2;
        }
    }

    public HomeHighlights(Context context) {
        super(context);
        init(null);
    }

    public HomeHighlights(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HomeHighlights(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_home_highlights, this);
        setLayoutParams(new LayoutParams(-1, -2));
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setData(List<IconRowData> listData) {
        int numViewsToAdd = listData.size() - this.horizontalIconContainer.getChildCount();
        if (numViewsToAdd < 0) {
            for (int i = numViewsToAdd; i < 0; i++) {
                this.horizontalIconContainer.removeViewAt(0);
            }
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, -2, 1.0f);
            for (int i2 = 0; i2 < numViewsToAdd; i2++) {
                this.horizontalIconContainer.addView(new IconViewHolder(getContext()).view, params);
            }
        }
        for (int i3 = 0; i3 < listData.size(); i3++) {
            IconViewHolder.fromView(this.horizontalIconContainer.getChildAt(i3)).setData((IconRowData) listData.get(i3));
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(HomeHighlights view) {
        view.setData(ImmutableList.m1288of(new IconRowData(R.drawable.n2_ic_am_dog, "Label 1"), new IconRowData(R.drawable.n2_ic_am_dog, "Label 2"), new IconRowData(R.drawable.n2_ic_am_dog, "Label 3"), new IconRowData(R.drawable.n2_ic_am_dog, "Label 4")));
    }
}
