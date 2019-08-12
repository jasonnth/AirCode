package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.FixedActionFooterWithText */
public class FixedActionFooterWithText extends LinearLayout {
    @BindView
    AirButton button;
    @BindView
    AirTextView textView;

    public FixedActionFooterWithText(Context context) {
        super(context);
        init(null);
    }

    public FixedActionFooterWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FixedActionFooterWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_fixed_action_footer_with_text, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        this.button.enableClickWhenLoading(false);
    }

    public void setButtonText(int stringRes) {
        this.button.setText(stringRes);
    }

    public void setTextViewText(int stringRes) {
        ViewLibUtils.bindOptionalTextView((TextView) this.textView, stringRes);
    }

    public void setupLinkedText(CharSequence fullText, CharSequence linkedText, int color, LinkOnClickListener listener) {
        ClickableLinkUtils.setupClickableTextView(this.textView, new SpannableString(TextUtil.fromHtmlSafe(fullText.toString())).toString(), linkedText.toString(), ContextCompat.getColor(getContext(), color), R.color.n2_canonical_press_darken, listener, false);
    }

    public void setButtonText(CharSequence buttonText) {
        this.button.setText(buttonText);
    }

    public void setTextViewText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.textView, text);
    }

    public void setButtonLoading(boolean loading) {
        this.button.setState(loading ? State.Loading : State.Normal, this.button.getCurrentTextColor());
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public static void mockWaiting(FixedActionFooterWithText row) {
        row.setTextViewText((CharSequence) "Text");
        row.setButtonText((CharSequence) "Primary Button");
        row.setButtonOnClickListener(FixedActionFooterWithText$$Lambda$1.lambdaFactory$());
        row.setButtonLoading(true);
    }

    static /* synthetic */ void lambda$mockWaiting$0(View view) {
    }

    public static void mock(FixedActionFooterWithText row) {
        row.setTextViewText((CharSequence) "Text");
        row.setButtonText((CharSequence) "Primary Button");
        row.setButtonOnClickListener(FixedActionFooterWithText$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mock$1(View view) {
    }

    public static void mockLongButtonText(FixedActionFooterWithText row) {
        row.setTextViewText((CharSequence) "Text");
        row.setButtonText((CharSequence) "A button that goes really really really really really really really really long");
        row.setButtonOnClickListener(FixedActionFooterWithText$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockLongButtonText$2(View view) {
    }

    public static void mockMissingText(FixedActionFooterWithText row) {
        row.setButtonText((CharSequence) "Click for text");
        row.setButtonOnClickListener(FixedActionFooterWithText$$Lambda$4.lambdaFactory$(row));
    }

    static /* synthetic */ void lambda$mockMissingText$3(FixedActionFooterWithText row, View view) {
        row.setTextViewText((CharSequence) TextUtils.isEmpty(row.textView.getText()) ? "Some text" : null);
    }
}
