package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class GroupedCell extends RelativeLayout {
    @BindView
    protected AirTextView mContent;
    private boolean mIsVertical;
    @BindView
    protected ColorizedIconView mNextArrow;
    @BindView
    protected AirTextView mTitle;
    @BindView
    protected GroupedTooltip mTooltip;
    @BindView
    protected View mTopBorder;

    public GroupedCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mIsVertical = false;
        init(context, attrs);
    }

    public GroupedCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsVertical = false;
        init(context, attrs);
    }

    public GroupedCell(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = null;
        int layoutToUse = getLayoutId();
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedCell, 0, 0);
            int layout = a.getResourceId(C0880R.styleable.GroupedCell_custom_layout, 0);
            if (layout > 0) {
                layoutToUse = layout;
            } else if (a.getBoolean(C0880R.styleable.GroupedCell_vertical, false)) {
                this.mIsVertical = true;
                layoutToUse = C0880R.layout.grouped_subtitle_cell;
            }
        }
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(layoutToUse, this, true));
        if (a != null) {
            String titleText = a.getString(C0880R.styleable.GroupedCell_title);
            int titleSize = a.getDimensionPixelSize(C0880R.styleable.GroupedCell_titleSize, -1);
            String contentText = a.getString(C0880R.styleable.GroupedCell_content);
            int contentSize = a.getDimensionPixelSize(C0880R.styleable.GroupedCell_contentSize, -1);
            int contentColor = a.getColor(C0880R.styleable.GroupedCell_contentColor, getResources().getColor(C0880R.color.c_hof));
            boolean hideTopBorder = a.getBoolean(C0880R.styleable.GroupedCell_hideTopBorder, false);
            boolean fullWidthBorder = a.getBoolean(C0880R.styleable.GroupedCell_fullWidthBorder, false);
            boolean withRightCaret = a.getBoolean(C0880R.styleable.GroupedCell_withRightCaret, false);
            int maxLines = a.getInteger(C0880R.styleable.GroupedCell_maxLines, 0);
            String hintText = a.getString(C0880R.styleable.GroupedCell_hint);
            float contentPadding = a.getDimension(C0880R.styleable.GroupedCell_contentPadding, -1.0f);
            this.mTitle.setText(titleText);
            if (((float) titleSize) != -1.0f) {
                this.mTitle.setTextSize(0, (float) titleSize);
            }
            int titleColor = a.getColor(C0880R.styleable.GroupedCell_titleColor, -1);
            if (titleColor != -1) {
                this.mTitle.setTextColor(titleColor);
            }
            if (withRightCaret) {
                showRightCaret(true);
            } else {
                this.mContent.setText(contentText);
                this.mContent.setTextColor(contentColor);
                if (((float) contentSize) != -1.0f) {
                    this.mContent.setTextSize(0, (float) contentSize);
                }
            }
            setTopBorderVisibility(hideTopBorder ? 8 : 0);
            if (fullWidthBorder) {
                MarginLayoutParams params = (MarginLayoutParams) this.mTopBorder.getLayoutParams();
                params.setMargins(0, 0, 0, 0);
                this.mTopBorder.setLayoutParams(params);
            }
            if (contentPadding != -1.0f) {
                MarginLayoutParams titleParams = (MarginLayoutParams) this.mTitle.getLayoutParams();
                titleParams.setMargins(0, 0, 0, 0);
                this.mTitle.setLayoutParams(titleParams);
                MarginLayoutParams contentParams = (MarginLayoutParams) this.mContent.getLayoutParams();
                contentParams.setMargins(0, 0, 0, 0);
                this.mContent.setLayoutParams(contentParams);
            }
            if (maxLines > 0) {
                setMaxLines(maxLines);
            }
            if (hintText != null) {
                this.mContent.setHint(hintText);
                this.mContent.setHintTextColor(getResources().getColor(C0880R.color.c_foggy_light));
            }
            a.recycle();
        }
        if (getBackground() == null) {
            TypedArray typedArray = context.obtainStyledAttributes(new int[]{C0880R.attr.selectableItemBackground});
            setBackgroundResource(typedArray.getResourceId(0, 0));
            typedArray.recycle();
        }
    }

    public void setMaxLines(int maxLines) {
        this.mContent.setMaxLines(maxLines);
        this.mTitle.setMaxLines(maxLines);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.grouped_cell;
    }

    public void setTopBorderVisibility(int visibility) {
        this.mTopBorder.setVisibility(visibility);
    }

    public void setTitle(CharSequence title) {
        this.mTitle.setText(title);
    }

    public void setTitle(int titleRes) {
        setTitle((CharSequence) getResources().getString(titleRes));
    }

    public void setTitleDrawable(int drawableRes, int paddingDimen) {
        this.mTitle.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        this.mTitle.setCompoundDrawablePadding(getResources().getDimensionPixelSize(paddingDimen));
    }

    public void setContent(CharSequence content) {
        if (this.mContent.getVisibility() == 8) {
            this.mContent.setVisibility(0);
        }
        if (!(this.mNextArrow == null || this.mNextArrow.getVisibility() == 8)) {
            this.mNextArrow.setVisibility(8);
        }
        this.mContent.setText(content);
    }

    public void setContentColor(int color) {
        this.mContent.setTextColor(color);
    }

    public void setContentVisibility(boolean isVisible) {
        ViewUtils.setGoneIf(this.mContent, !isVisible);
    }

    public void setHTMLContent(String content) {
        this.mContent.setText(Html.fromHtml(content));
    }

    public void setContent(int contentRes) {
        setContent((CharSequence) getResources().getString(contentRes));
    }

    public void showTopBorder(boolean show) {
        ViewUtils.setVisibleIf(this.mTopBorder, show);
    }

    public void showRightCaret(boolean show) {
        if (this.mNextArrow == null) {
            throw new UnsupportedOperationException("no right caret in this layout. is this @layout/grouped_cell_vertical?");
        }
        if (!this.mIsVertical) {
            ViewUtils.setGoneIf(this.mContent, show);
        }
        ViewUtils.setVisibleIf((View) this.mNextArrow, show);
    }

    public void forceRightCaret(boolean show) {
        if (this.mNextArrow == null) {
            throw new UnsupportedOperationException("no right caret in this layout. is this @layout/grouped_cell_vertical?");
        }
        ViewUtils.setVisibleIf((View) this.mNextArrow, show);
    }

    public GroupedTooltip getTooltip() {
        if (this.mTooltip != null) {
            return this.mTooltip;
        }
        throw new UnsupportedOperationException("no tooltip in this layout. is this @layout/grouped_cell_vertical?");
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mTitle.setEnabled(enabled);
        this.mContent.setEnabled(enabled);
    }

    public TextView getTitle() {
        return this.mTitle;
    }

    public TextView getContent() {
        return this.mContent;
    }

    public ColorizedIconView getIcon() {
        return this.mNextArrow;
    }

    public void setDividerHorizontalPadding(int horizontalMargin) {
        ((LayoutParams) this.mTopBorder.getLayoutParams()).setMargins(horizontalMargin, 0, 0, horizontalMargin);
    }

    public String toString() {
        return "GroupedCell: " + this.mTitle.getText().toString() + " --> " + this.mContent.getText().toString();
    }

    public void setRowColor(int color) {
        this.mTitle.setTextColor(color);
        this.mContent.setTextColor(color);
    }
}
