package com.airbnb.p027n2.components;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InviteRow */
public final class InviteRow extends BaseDividerComponent {
    @BindView
    AirButton button;
    @BindView
    AirTextView descriptionText;
    @BindView
    HaloImageView photo;
    @BindView
    AirTextView titleText;

    public InviteRow(Context context) {
        super(context);
    }

    public InviteRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InviteRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style((BaseDividerComponent) this).apply(attrs);
        this.photo.setImageDefault();
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_invite_row;
    }

    public void setPhoto(Uri uri) {
        this.photo.setImageUri(uri);
    }

    public void setTitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.titleText, text, false);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setDescriptionText(CharSequence text) {
        this.descriptionText.setText(text);
    }

    public void setDescriptionText(int textRes) {
        setDescriptionText((CharSequence) getResources().getString(textRes));
    }

    public void setButtonText(CharSequence text) {
        this.button.setText(text);
    }

    public void setButtonText(int textRes) {
        setButtonText((CharSequence) getResources().getString(textRes));
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public static void mockWithTitle(InviteRow row) {
        row.setPhoto(Uri.parse("https://a0.muscache.com/im/users/4025021/profile_pic/1432233919/original.jpg?aki_policy=profile_x_medium"));
        row.setTitle((CharSequence) "Bob Joe");
        row.setDescriptionText((CharSequence) "bobjoe@joebob.com");
        row.setButtonText((CharSequence) "Invite");
        row.setButtonClickListener(InviteRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWithNoTitleAndNoImage(InviteRow row) {
        row.setDescriptionText((CharSequence) "bobjoe@joebob.com");
        row.setButtonText((CharSequence) "Invite");
        row.setButtonClickListener(InviteRow$$Lambda$2.lambdaFactory$());
    }
}
