package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.CityRegistrationIconActionRow */
public class CityRegistrationIconActionRow extends BaseDividerComponent {
    @BindView
    AirTextView action;
    @BindView
    AirImageView icon;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public CityRegistrationIconActionRow(Context context) {
        super(context);
    }

    public CityRegistrationIconActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityRegistrationIconActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_city_registration_icon_action_row;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        this.title.setText(getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.setText((TextView) this.subtitle, text, true);
    }

    public void setSubtitle(int textRes) {
        this.subtitle.setText(getResources().getString(textRes));
    }

    public void setAction(CharSequence text) {
        this.action.setText(text);
    }

    public void setAction(int textRes) {
        this.action.setText(getResources().getString(textRes));
    }

    public void setIconRes(int iconRes) {
        ViewLibUtils.setVisibleIf(this.icon, iconRes != 0);
        this.icon.setImageDrawableCompat(iconRes);
    }

    public void setIcon(Drawable drawable) {
        this.icon.setImageDrawable(drawable);
    }

    public void hideIcon() {
        this.icon.setVisibility(8);
    }

    public static void mock(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "subtitle");
        row.setAction((CharSequence) "Action");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
        row.showDivider(true);
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mock$0(View v) {
    }

    public static void mockWithNoSubtitle(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setAction((CharSequence) "Action");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$2.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
        row.setAction((CharSequence) "Action");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockWithLongSubtitle$2(View v) {
    }

    public static void mockWithLongTitle(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Maecenas nec eros non justo accumsan ullamcorper.");
        row.setSubtitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi pellentesque ligula vitae aliquam sagittis.");
        row.setAction((CharSequence) "Action");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockWithLongTitle$3(View v) {
    }

    public static void mockWithRichSubtitle(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", CityRegistrationIconActionRow$$Lambda$5.lambdaFactory$()).build());
        row.setAction((CharSequence) "Action");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$6.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockWithRichSubtitle$5(View v) {
    }

    public static void mockWithLongSubtitleAndNoAction(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
        row.setIconRes(R.drawable.n2_ic_radio_button_selected);
    }

    public static void mockWithNoIcon(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "a row with no icon");
        row.setAction((CharSequence) "Action");
        row.setOnClickListener(CityRegistrationIconActionRow$$Lambda$7.lambdaFactory$());
        row.hideIcon();
    }

    static /* synthetic */ void lambda$mockWithNoIcon$6(View v) {
    }

    public static void mockWithNoActionTextOrIcon(CityRegistrationIconActionRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "A row with no action text or icon ");
        row.hideIcon();
    }
}
