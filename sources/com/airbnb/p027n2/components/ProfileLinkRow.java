package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ProfileLinkRow */
public class ProfileLinkRow extends BaseDividerComponent {
    @BindView
    AirTextView linkText;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_profile_link_row;
    }

    public ProfileLinkRow(Context context) {
        super(context);
    }

    public ProfileLinkRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileLinkRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setSubtitle(int stringId) {
        setSubtitle((CharSequence) getResources().getString(stringId));
    }

    public void setLinkText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.linkText, !TextUtils.isEmpty(text));
        this.linkText.setText(text);
    }

    public void setLinkText(int stringRes) {
        setLinkText((CharSequence) getResources().getString(stringRes));
    }

    public void setOnClickLinkListener(OnClickListener l) {
        this.linkText.setOnClickListener(l);
    }

    public static void mock(ProfileLinkRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "Subtitle");
        row.setLinkText((CharSequence) "Link");
    }
}
