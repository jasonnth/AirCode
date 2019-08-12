package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class EditableCell extends RelativeLayout {
    CurrencyFormatter currencyFormatter;
    private TextView currentContentView;
    @BindView
    TextView mContent;
    @BindView
    EditText mEditText;
    @BindView
    TextView mPrefixContent;
    @BindView
    TextView mTitle;
    @BindView
    View mTopBorder;

    public EditableCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public EditableCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditableCell(Context context) {
        this(context, null);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        View content = LayoutInflater.from(context).inflate(C0880R.layout.editable_cell, this, true);
        ButterKnife.bind(this, content);
        if (getBackground() == null) {
            setBackgroundResource(C0880R.C0881drawable.c_bg_transparent);
        }
        setClickable(true);
        content.setOnClickListener(EditableCell$$Lambda$1.lambdaFactory$(this));
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.EditableCell, 0, 0);
            String titleText = a.getString(C0880R.styleable.EditableCell_title);
            String contentText = a.getString(C0880R.styleable.EditableCell_content);
            int contentColor = a.getColor(C0880R.styleable.EditableCell_contentColor, getResources().getColor(C0880R.color.c_foggy));
            int editTextColor = a.getColor(C0880R.styleable.EditableCell_editTextColor, getResources().getColor(C0880R.color.c_foggy));
            boolean hideTopBorder = a.getBoolean(C0880R.styleable.EditableCell_hideTopBorder, false);
            boolean fullWidthBorder = a.getBoolean(C0880R.styleable.EditableCell_fullWidthBorder, false);
            boolean freezeEditText = a.getBoolean(C0880R.styleable.EditableCell_freezeEditText, false);
            String hintText = a.getString(C0880R.styleable.EditableCell_hint);
            this.mTitle.setText(titleText);
            int titleColor = a.getColor(C0880R.styleable.EditableCell_titleColor, -1);
            if (titleColor != -1) {
                this.mTitle.setTextColor(titleColor);
            }
            if (hideTopBorder) {
                this.mTopBorder.setVisibility(8);
            }
            this.mContent.setText(contentText);
            this.mContent.setTextColor(contentColor);
            this.currentContentView = this.mContent;
            this.mPrefixContent.setTextColor(contentColor);
            this.mEditText.setTextColor(editTextColor);
            if (fullWidthBorder) {
                MarginLayoutParams params = (MarginLayoutParams) this.mTopBorder.getLayoutParams();
                params.setMargins(0, 0, 0, 0);
                this.mTopBorder.setLayoutParams(params);
            }
            if (hintText != null) {
                this.mContent.setHint(hintText);
                this.mContent.setHintTextColor(getResources().getColor(C0880R.color.c_foggy_light));
            }
            int maxLength = a.getInt(C0880R.styleable.EditableCell_maxLength, -1);
            if (maxLength != -1) {
                setMaxLength(maxLength);
            }
            this.mEditText.setFreezesText(freezeEditText);
            a.recycle();
        }
    }

    public void setEditTextHint(int hint, int hintColor) {
        setEditTextHint(getResources().getString(hint), hintColor);
    }

    public void setEditTextHint(String hint, int hintColor) {
        this.mEditText.setHint(hint);
        this.mEditText.setHintTextColor(getResources().getColor(hintColor));
    }

    public void setUpForPercentage() {
        String percentChar = getContext().getString(C0880R.string.percent_sign);
        this.mPrefixContent.setText("");
        this.mContent.setText("");
        this.mEditText.setInputType(2);
        this.mEditText.setFilters(new InputFilter[]{new LengthFilter(2)});
        this.currentContentView = PercentageUtils.isPercentSignPrefixed(getContext()) ? this.mPrefixContent : this.mContent;
        this.currentContentView.setText(percentChar);
        this.currentContentView.setPadding(0, 0, 0, 0);
    }

    public void hideCurrentContent() {
        this.currentContentView.setVisibility(8);
    }

    public void showCurrentContent() {
        this.currentContentView.setVisibility(0);
    }

    public void setUpForCurrency(Listing listing) {
        this.mPrefixContent.setText("");
        this.mContent.setText("");
        this.mEditText.setInputType(2);
        this.currencyFormatter.setCurrency(listing.getListingNativeCurrency(), false, false);
        this.currentContentView = this.currencyFormatter.isSymbolPrefixed() ? this.mPrefixContent : this.mContent;
        this.currentContentView.setText(this.currencyFormatter.getLocalCurrencySymbol());
        this.currentContentView.setPadding(0, 0, 0, 0);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.mEditText.getText());
    }

    public String getText() {
        return this.mEditText.getText().toString();
    }

    public void addTextWatcher(TextWatcher textWatcher) {
        this.mEditText.addTextChangedListener(textWatcher);
    }

    public void removeTextWatcher(TextWatcher textWatcher) {
        this.mEditText.removeTextChangedListener(textWatcher);
    }

    public void setTitle(CharSequence title) {
        this.mTitle.setText(title);
    }

    public void setTitle(int titleRes) {
        setTitle((CharSequence) getResources().getString(titleRes));
    }

    public void setContent(CharSequence content) {
        this.mContent.setText(content);
    }

    public void setContent(int contentRes) {
        setContent((CharSequence) getResources().getString(contentRes));
    }

    public void showTopBorder(boolean show) {
        ViewUtils.setVisibleIf(this.mTopBorder, show);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mTitle.setEnabled(enabled);
        this.mContent.setEnabled(enabled);
        this.mEditText.setEnabled(enabled);
    }

    public void setInputType(int inputType) {
        this.mEditText.setInputType(inputType);
    }

    public void setValue(int value) {
        setValue(Integer.toString(value));
    }

    public void setValue(String value) {
        this.mEditText.setText(value);
    }

    public void focusOnValue() {
        MiscUtils.requestFocusAndOpenKeyboard(getContext(), this.mEditText);
        this.mEditText.setSelection(this.mEditText.getText().length());
    }

    public View getContent() {
        return this.mContent;
    }

    public void setMaxLength(int maxLength) {
        this.mEditText.setFilters(new InputFilter[]{new LengthFilter(maxLength)});
    }
}
