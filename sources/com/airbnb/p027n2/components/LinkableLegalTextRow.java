package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LinkableLegalTextRow */
public class LinkableLegalTextRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView fxBody;
    @BindView
    AirTextView termsBody;
    @BindView
    AirTextView termsTitle;

    public LinkableLegalTextRow(Context context) {
        super(context);
        init(null);
    }

    public LinkableLegalTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LinkableLegalTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_linkable_legal_text_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LinkableLegalTextRow, 0, 0);
        setTermsTitle(a.getString(R.styleable.n2_LinkableLegalTextRow_n2_titleText));
        setTermsBody(a.getString(R.styleable.n2_LinkableLegalTextRow_n2_subtitleText));
        showDivider(a.getBoolean(R.styleable.n2_LinkableLegalTextRow_n2_showDivider, true));
        a.recycle();
    }

    public void setTermsTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)) {
            this.termsTitle.setVisibility(8);
            return;
        }
        this.termsTitle.setText(title);
        this.termsTitle.setVisibility(0);
    }

    public void setTermsBody(CharSequence body) {
        if (TextUtils.isEmpty(body)) {
            this.termsBody.setVisibility(8);
            return;
        }
        this.termsBody.setText(body);
        this.termsBody.setVisibility(0);
        this.termsBody.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setFxBody(CharSequence body) {
        if (TextUtils.isEmpty(body)) {
            this.fxBody.setVisibility(8);
            return;
        }
        this.fxBody.setText(body);
        this.fxBody.setVisibility(0);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(LinkableLegalTextRow view) {
        view.setTermsTitle("Title Text");
        view.setTermsBody("Terms Body");
        view.setFxBody("Fx Body");
    }
}
