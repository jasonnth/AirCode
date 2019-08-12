package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class GroupedEditTextContentCell extends GroupedCell {
    @BindView
    protected EditText editText;

    public GroupedEditTextContentCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GroupedEditTextContentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupedEditTextContentCell(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        TypedArray a = null;
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedEditTextContentCell, 0, 0);
        }
        if (a != null) {
            String contentText = a.getString(C0880R.styleable.GroupedCell_content);
            int contentColor = a.getColor(C0880R.styleable.GroupedCell_contentColor, getResources().getColor(C0880R.color.c_hof));
            boolean withRightCaret = a.getBoolean(C0880R.styleable.GroupedCell_withRightCaret, false);
            if (a.getBoolean(C0880R.styleable.GroupedEditTextContentCell_hideRightCaret, false)) {
                hideRightCaret();
            } else {
                showRightCaret(withRightCaret);
            }
            if (contentText != null) {
                setContent(contentText);
            }
            if (contentColor != -1) {
                this.mContent.setTextColor(contentColor);
            }
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.grouped_edit_text_content_cell;
    }

    public void setContent(CharSequence content) {
        ViewUtils.setVisibleIf((View) this.mContent, !content.toString().isEmpty());
        this.mContent.setText(content);
    }

    public void setIcon(int resId, int color) {
        if (this.mNextArrow == null) {
            throw new IllegalStateException("GroupedEditTextContentCell.setIcon : cannot set icon when mNextArrow is gone");
        }
        this.mNextArrow.setVisibility(0);
        this.mNextArrow.setDrawableId(resId);
        this.mNextArrow.setColor(color);
    }

    public void showRightCaret(boolean show) {
        if (this.mNextArrow == null) {
            throw new UnsupportedOperationException("no right caret in this layout. is this @layout/grouped_cell_vertical?");
        }
        ViewUtils.setInvisibleIf(this.mNextArrow, !show);
    }

    public void hideRightCaret() {
        if (this.mNextArrow == null) {
            throw new UnsupportedOperationException("no right caret in this layout. is this @layout/grouped_cell_vertical?");
        }
        ViewUtils.setGoneIf(this.mNextArrow, true);
    }

    public void showEditTextField(boolean show) {
        if (this.editText == null) {
            throw new UnsupportedOperationException("no edit text field in this layout.");
        }
        ViewUtils.setVisibleIf((View) this.editText, show);
    }

    public void addEditTextTextChangedListener(TextWatcher textWatcher) {
        if (this.editText == null) {
            throw new UnsupportedOperationException("no edit text field in this layout");
        }
        this.editText.addTextChangedListener(textWatcher);
    }

    public String toString() {
        return "GroupedEditTextContentCell: " + this.mTitle.getText().toString() + " --> " + this.mContent.getText().toString();
    }
}
