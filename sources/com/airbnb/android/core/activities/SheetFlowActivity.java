package com.airbnb.android.core.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetProgressBar;
import icepick.State;

public abstract class SheetFlowActivity extends AirActivity {
    @State
    SheetTheme currentTheme;
    @BindView
    protected LoaderFrame loaderFrame;
    @BindView
    protected SheetProgressBar progressBar;
    @BindView
    protected ViewGroup rootView;
    protected AirToolbar toolbar;

    public enum SheetTheme {
        JELLYFISH(C0716R.color.n2_jellyfish_babu_bg, C0716R.C0717drawable.ic_action_back_white, C0716R.color.n2_action_bar_foreground_light),
        BABU(C0716R.color.n2_babu, C0716R.C0717drawable.ic_action_back_white, C0716R.color.n2_action_bar_foreground_light),
        ARCHES(C0716R.color.n2_arches, C0716R.C0717drawable.ic_action_back_white, C0716R.color.n2_action_bar_foreground_light),
        WHITE(17170443, C0716R.C0717drawable.ic_action_back, C0716R.color.n2_action_bar_foreground_dark),
        BABU_X(C0716R.color.n2_babu, C0716R.C0717drawable.ic_action_close_white, C0716R.color.n2_action_bar_foreground_light);
        
        final int backButtonDrawableRes;
        final int backgroundColorRes;
        final int toolbarTextColorRes;

        private SheetTheme(int background, int backButton, int toolbarColor) {
            this.backgroundColorRes = background;
            this.backButtonDrawableRes = backButton;
            this.toolbarTextColorRes = toolbarColor;
        }

        public int getToolbarColor(Context context) {
            return context.getResources().getColor(this.toolbarTextColorRes);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0716R.layout.activity_sheet_flow);
        ButterKnife.bind((Activity) this);
        this.toolbar = (AirToolbar) findViewById(C0716R.C0718id.toolbar);
        setToolbar(this.toolbar);
        setSheetTheme(this.currentTheme == null ? getDefaultTheme() : this.currentTheme, false);
    }

    public void showFragment(Fragment frag) {
        showFragment(frag, C0716R.C0718id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void setTheme(int resid) {
        super.setTheme(C0716R.C0719style.Theme_Airbnb_TransparentActionBarDarkText);
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        if (useHomeAsBack()) {
            onBackPressed();
        } else {
            super.onHomeActionPressed();
        }
    }

    public boolean useHomeAsBack() {
        return false;
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.BABU;
    }

    public void setSheetTheme(SheetTheme newTheme, boolean animate) {
        getSupportActionBar().setHomeAsUpIndicator(newTheme.backButtonDrawableRes);
        this.toolbar.setForegroundColor(newTheme.getToolbarColor(this));
        if (animate) {
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{new ColorDrawable(getResources().getColor(this.currentTheme.backgroundColorRes)), new ColorDrawable(getResources().getColor(newTheme.backgroundColorRes))});
            this.rootView.setBackground(td);
            td.startTransition(250);
        } else {
            this.rootView.setBackgroundResource(newTheme.backgroundColorRes);
        }
        this.currentTheme = newTheme;
    }

    public void setSheetTheme(SheetTheme theme) {
        setSheetTheme(theme, true);
    }

    public void startLoader() {
        this.loaderFrame.startAnimation();
    }

    public void stopLoader() {
        this.loaderFrame.finishImmediate();
    }
}
