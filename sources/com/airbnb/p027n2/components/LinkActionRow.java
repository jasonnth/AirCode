package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.LinkActionRow */
public class LinkActionRow extends BaseDividerComponent {
    @BindView
    AirTextView textView;

    public LinkActionRow(Context context) {
        super(context);
    }

    public LinkActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinkActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_link_action_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LinkActionRow);
        setText((CharSequence) a.getString(R.styleable.n2_LinkActionRow_n2_text));
        a.recycle();
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
    }

    public void setText(int stringRes) {
        setText((CharSequence) getResources().getString(stringRes));
    }

    public static void mock(LinkActionRow row) {
        row.setText((CharSequence) "Link action row");
        row.setOnClickListener(LinkActionRow$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mock$0(View v) {
    }
}
