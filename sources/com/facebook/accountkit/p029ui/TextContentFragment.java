package com.facebook.accountkit.p029ui;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;

/* renamed from: com.facebook.accountkit.ui.TextContentFragment */
abstract class TextContentFragment extends ContentFragment {
    private static final String CONTENT_PADDING_BOTTOM_KEY = "contentPaddingBottom";
    private static final String CONTENT_PADDING_TOP_KEY = "contentPaddingTop";
    private NextButtonTextProvider nextButtonTextProvider;
    private TextView textView;

    /* renamed from: com.facebook.accountkit.ui.TextContentFragment$NextButtonTextProvider */
    public interface NextButtonTextProvider {
        String getNextButtonText();
    }

    /* access modifiers changed from: protected */
    public abstract Spanned getText(String str);

    TextContentFragment() {
    }

    public int getContentPaddingBottom() {
        return getViewState().getInt(CONTENT_PADDING_BOTTOM_KEY, 0);
    }

    public void setContentPaddingBottom(int contentPaddingBottom) {
        getViewState().putInt(CONTENT_PADDING_BOTTOM_KEY, contentPaddingBottom);
        updateContentPadding();
    }

    public int getContentPaddingTop() {
        return getViewState().getInt(CONTENT_PADDING_TOP_KEY, 0);
    }

    public void setContentPaddingTop(int contentPaddingTop) {
        getViewState().putInt(CONTENT_PADDING_TOP_KEY, contentPaddingTop);
        updateContentPadding();
    }

    /* access modifiers changed from: protected */
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C3354R.layout.com_accountkit_fragment_phone_login_text, container, false);
    }

    public void setNextButtonTextProvider(NextButtonTextProvider nextButtonTextProvider2) {
        this.nextButtonTextProvider = nextButtonTextProvider2;
    }

    /* access modifiers changed from: protected */
    public void onViewReadyWithState(View view, Bundle viewState) {
        super.onViewReadyWithState(view, viewState);
        this.textView = (TextView) view.findViewById(C3354R.C3356id.com_accountkit_text);
        if (this.textView != null) {
            this.textView.setMovementMethod(new CustomLinkMovement(new OnURLClickedListener() {
                public void onURLClicked(String url) {
                    Logger.logUIPhoneLoginInteraction(Buttons.POLICY_LINKS.name(), url);
                }
            }));
        }
        updateContentPadding();
        updateText();
    }

    public void onStart() {
        super.onStart();
        updateText();
    }

    private void updateContentPadding() {
        if (this.textView != null) {
            this.textView.setPadding(this.textView.getPaddingLeft(), getContentPaddingTop(), this.textView.getPaddingRight(), getContentPaddingBottom());
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateText() {
        if (this.textView != null && this.nextButtonTextProvider != null && getActivity() != null) {
            this.textView.setText(getText(this.nextButtonTextProvider.getNextButtonText()));
        }
    }
}
