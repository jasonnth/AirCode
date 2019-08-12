package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.TextRow */
public class TextRow extends BaseDividerComponent {
    @BindView
    ExpandableTextView textView;

    public TextRow(Context context) {
        super(context);
    }

    public TextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_text_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.textView.dispatchTouchEvent(event);
    }

    public void setExpandable(boolean expandable) {
        this.textView.setExpandable(expandable);
    }

    public void setMaxLines(int maxLines) {
        this.textView.setMaxLines(maxLines);
    }

    public void clearMaxLines() {
        this.textView.clearMaxLines();
    }

    public void setReadMoreText(CharSequence readMoreText) {
        this.textView.setReadMoreText(readMoreText);
    }

    public void setText(int stringRes) {
        setText((CharSequence) getContext().getString(stringRes));
    }

    public void setText(CharSequence text) {
        this.textView.setContentText(text);
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public void setContentText(CharSequence text) {
        setText(text);
    }

    public void expand() {
        this.textView.expand();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.textView.setOnClickListener(listener);
    }

    public static void mockCollapsed(TextRow view) {
        view.setReadMoreText("read more");
        view.setText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.");
        view.setMaxLines(2);
    }

    public static void mockExpanded(TextRow view) {
        view.setText((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.");
    }

    public static void mockRichText(TextRow view) {
        view.setReadMoreText("read more");
        view.setText(new AirTextBuilder(view.getContext()).append("Lorem ipsum ").appendBold("dolor sit amet,").appendItalic(" consectetur adipiscing elit. ").appendLink("Cras consectetur", TextRow$$Lambda$1.lambdaFactory$()).append(" ipsum eu est aliquam imperdiet. Nam sodales consequat magna, sed luctus sem pellentesque eu.\n\nCras et ante vel massa mattis bibendum. Phasellus efficitur augue at tempus volutpat.").build());
        view.setMaxLines(4);
    }
}
