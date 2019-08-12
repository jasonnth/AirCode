package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class ManageListingTextRowView extends LinearLayout {
    @BindView
    ColorizedIconView colorizedIconView;
    @BindView
    View dividerView;
    @BindView
    AirTextView subtitleRow;
    @BindView
    AirTextView titleRow;
    @BindView
    AirTextView upsellView;

    public ManageListingTextRowView(Context context) {
        super(context);
        init(null);
    }

    public ManageListingTextRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ManageListingTextRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), C0880R.layout.ml_text_row_view, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
        setBackgroundResource(C0880R.color.white);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.ManageListingTextRowView, 0, 0);
        String titleText = a.getString(C0880R.styleable.ManageListingTextRowView_title);
        String subtitleText = a.getString(C0880R.styleable.ManageListingTextRowView_subtitle);
        boolean dividerVisible = a.getBoolean(C0880R.styleable.ManageListingTextRowView_lib_showDivider, true);
        int drawableId = a.getResourceId(C0880R.styleable.ManageListingTextRowView_drawableId, 0);
        int colorStates = a.getResourceId(C0880R.styleable.ManageListingTextRowView_colorStates, C0880R.color.n2_hof_legacy);
        int titleColor = a.getColor(C0880R.styleable.ManageListingTextRowView_titleTextColor, getResources().getColor(C0880R.color.n2_hof_legacy));
        int subtitleColor = a.getColor(C0880R.styleable.ManageListingTextRowView_subtitleTextColor, getResources().getColor(C0880R.color.n2_foggy_legacy));
        int maxLines = a.getInteger(C0880R.styleable.ManageListingTextRowView_subtitleMaxLines, 0);
        setTitle(titleText);
        setSubtitle(subtitleText);
        showDivider(dividerVisible);
        setDrawable(drawableId, colorStates);
        this.titleRow.setTextColor(titleColor);
        this.subtitleRow.setTextColor(subtitleColor);
        if (maxLines > 0) {
            this.subtitleRow.setMaxLines(maxLines);
        }
        a.recycle();
    }

    public void showDivider(boolean show) {
        ViewUtils.setVisibleIf(this.dividerView, show);
    }

    public void setTitle(String value) {
        this.titleRow.setText(value);
    }

    public void setSubtitle(String value) {
        ViewUtils.setGoneIf(this.subtitleRow, TextUtils.isEmpty(value));
        this.subtitleRow.setText(value);
    }

    public void setDrawable(int drawable, int color) {
        ViewUtils.setVisibleIf((View) this.colorizedIconView, drawable > 0);
        if (drawable > 0) {
            this.colorizedIconView.setDrawableId(drawable);
            if (color > 0) {
                this.colorizedIconView.setColorStateListRes(color);
            }
        }
    }

    public void showDrawable(boolean show) {
        ViewUtils.setVisibleIf((View) this.colorizedIconView, show);
    }

    public void setUpsellPillText(CharSequence text) {
        ViewUtils.setGoneIf(this.upsellView, TextUtils.isEmpty(text));
        this.upsellView.setText(text);
    }
}
