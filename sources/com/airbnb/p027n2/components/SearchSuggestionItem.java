package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SearchSuggestionItem */
public class SearchSuggestionItem extends FrameLayout {
    private boolean flexibleSize;
    @BindView
    AirTextView textView;

    public SearchSuggestionItem(Context context) {
        super(context);
        init();
    }

    public SearchSuggestionItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchSuggestionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        View.inflate(getContext(), R.layout.n2_search_suggestion_item, this);
        ButterKnife.bind((View) this);
        setClickable(true);
        setBackgroundResource(R.drawable.n2_search_suggestion_item_background);
    }

    public void setSizeFlexible(boolean isFlexible) {
        this.flexibleSize = isFlexible;
        setText(this.textView.getText().toString());
    }

    public void setText(String text) {
        int maxLines;
        float textSize;
        int horizontalPadding;
        int length = text.length();
        if (!this.flexibleSize || TextUtils.isEmpty(text)) {
            maxLines = 1;
            textSize = getContext().getResources().getDimension(R.dimen.n2_search_suggestion_item_text_size_normal);
            horizontalPadding = getContext().getResources().getDimensionPixelSize(R.dimen.n2_search_suggestion_item_text_horizontal_padding_normal);
        } else if (length <= 5) {
            maxLines = 1;
            textSize = getContext().getResources().getDimension(R.dimen.n2_search_suggestion_item_text_size_normal);
            horizontalPadding = 0;
        } else if (length <= 6) {
            maxLines = 1;
            textSize = getContext().getResources().getDimension(R.dimen.n2_search_suggestion_item_text_size_small);
            horizontalPadding = 0;
        } else {
            maxLines = 2;
            textSize = getContext().getResources().getDimension(R.dimen.n2_search_suggestion_item_text_size_small);
            horizontalPadding = getContext().getResources().getDimensionPixelSize(R.dimen.n2_search_suggestion_item_text_horizontal_padding_two_lines);
        }
        this.textView.setMaxLines(maxLines);
        this.textView.setTextSize(0, textSize);
        this.textView.setPadding(horizontalPadding, 0, horizontalPadding, 0);
        this.textView.setText(text);
    }

    public static void mock(SearchSuggestionItem item) {
        item.setText("Beijing");
    }
}
