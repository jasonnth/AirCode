package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineCaution */
public class InlineCaution extends FrameLayout {
    /* access modifiers changed from: private */
    public OnClickListener actionListener;
    @BindView
    AirTextView cautionText;

    /* renamed from: com.airbnb.n2.components.InlineCaution$ActionableSpan */
    private class ActionableSpan extends ClickableSpan {
        private ActionableSpan() {
        }

        public void onClick(View widget) {
            if (InlineCaution.this.actionListener != null) {
                InlineCaution.this.actionListener.onClick(widget);
            }
        }

        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
        }
    }

    public InlineCaution(Context context) {
        super(context);
        init();
    }

    public InlineCaution(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InlineCaution(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_inline_caution, this);
        ButterKnife.bind((View) this);
    }

    public void setCautionTextAndAction(String s, int actionStart, int actionEnd, OnClickListener actionListener2) {
        if (!checkValidation(s, actionStart, actionEnd)) {
            this.cautionText.setText(s);
            this.actionListener = null;
            return;
        }
        SpannableString spStr = new SpannableString(s);
        spStr.setSpan(new ActionableSpan(), actionStart, actionEnd, 17);
        this.cautionText.setText(spStr);
        this.cautionText.setMovementMethod(LinkMovementMethod.getInstance());
        this.actionListener = actionListener2;
    }

    private boolean checkValidation(String s, int start, int end) {
        return !TextUtils.isEmpty(s) && start >= 0 && start < end && end <= s.length();
    }

    public static void mock(InlineCaution view) {
        String text = "Caution:action";
        view.setCautionTextAndAction(text, text.indexOf("action"), text.length(), InlineCaution$$Lambda$1.lambdaFactory$(view));
    }
}
