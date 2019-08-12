package com.airbnb.android.login.p339ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.login.ui.views.OAuthOptionButton */
public class OAuthOptionButton extends AirButton {
    public OAuthOptionButton(Context context) {
        super(context);
    }

    public OAuthOptionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OAuthOptionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOptionAsPrimary(OAuthOption primaryOption) {
        setCompoundDrawablesWithIntrinsicBounds(getCompatIcon(primaryOption.icon), null, null, null);
        setText(getContext().getString(C7331R.string.signin_continue_with, new Object[]{getContext().getString(primaryOption.title)}));
    }

    public void setOption(OAuthOption option) {
        setCompoundDrawablesWithIntrinsicBounds(getCompatIcon(option.icon), null, null, null);
        setText(getContext().getString(option.title));
    }

    private Drawable getCompatIcon(int icon) {
        Drawable compatIcon = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), icon));
        DrawableCompat.setTint(compatIcon, ContextCompat.getColor(getContext(), C7331R.color.n2_babu));
        return compatIcon;
    }
}
