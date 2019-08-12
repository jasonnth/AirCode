package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.p000v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.TooltipDialogFragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SuperhostView extends LinearLayout {
    @BindView
    ColorizedIconView mTooltip;

    public SuperhostView(Context context) {
        this(context, null);
    }

    public SuperhostView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperhostView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(C0880R.layout.superhost_view, this, true);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void updateSuperhostStatus(boolean show, boolean isCurrentUser, FragmentManager fm) {
        String superhostDescription;
        if (!show) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (isCurrentUser) {
            SimpleDateFormat fmt = new SimpleDateFormat(getResources().getString(C0880R.string.mdy_format_shorter), Locale.getDefault());
            Calendar superhostCalendar = Calendar.getInstance();
            superhostCalendar.set(2014, 10, 1);
            String superhostDateString = fmt.format(superhostCalendar.getTime());
            this.mTooltip.setVisibility(8);
            superhostDescription = getResources().getString(C0880R.string.superhost_host_view_description, new Object[]{superhostDateString});
        } else {
            superhostDescription = getResources().getString(C0880R.string.superhost_guest_view_description);
        }
        this.mTooltip.setVisibility(0);
        this.mTooltip.setOnClickListener(SuperhostView$$Lambda$1.lambdaFactory$(this, fm, superhostDescription));
    }

    /* access modifiers changed from: private */
    public void showSuperhostTooltip(FragmentManager fm, String superhostDescription) {
        String termsLink = getResources().getString(C0880R.string.superhost_tooltip_link, new Object[]{getResources().getString(C0880R.string.superhost_tooltip_dialog_link_text)});
        TooltipDialogFragment.newInstance(C0880R.string.superhost_tooltip_dialog_title, getResources().getString(C0880R.string.superhost_tooltip_dialog_body, new Object[]{superhostDescription, termsLink}), getResources().getString(C0880R.string.superhost_terms_url)).show(fm, "tooltip");
    }
}
