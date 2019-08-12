package com.airbnb.android.core.views.calendar;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.airbnb.p027n2.components.PrimaryTextBottomBar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.paris.Paris;

public class CalendarBottomBarInterfaceWrapper {
    private final FixedActionFooter fixedActionFooter;
    private final PrimaryTextBottomBar primaryTextBottomBar;

    public CalendarBottomBarInterfaceWrapper(View view) {
        if (view instanceof FixedActionFooter) {
            this.fixedActionFooter = (FixedActionFooter) view;
            this.primaryTextBottomBar = null;
        } else if (view instanceof PrimaryTextBottomBar) {
            this.primaryTextBottomBar = (PrimaryTextBottomBar) view;
            this.fixedActionFooter = null;
        } else {
            throw new IllegalArgumentException("View must be an instance of FixedActionFooter or PrimaryTextBottomBar");
        }
    }

    public void setStyle(Style style) {
        if (this.fixedActionFooter != null) {
            if (style == Style.WHITE) {
                Paris.style(this.fixedActionFooter).applyBabu();
            } else if (style == Style.BABU) {
                Paris.style(this.fixedActionFooter).applyWhite();
            }
        } else if (this.primaryTextBottomBar == null) {
        } else {
            if (style == Style.WHITE_NEW) {
                this.primaryTextBottomBar.setStyle(1);
            } else if (style == Style.BABU_NEW) {
                this.primaryTextBottomBar.setStyle(2);
            }
        }
    }

    public void setEnabled(boolean enabled) {
        if (this.fixedActionFooter != null) {
            this.fixedActionFooter.setButtonEnabled(enabled);
        } else if (this.primaryTextBottomBar != null) {
            this.primaryTextBottomBar.setEnabled(enabled);
        }
    }

    public void setText(int textRes) {
        if (this.fixedActionFooter != null) {
            this.fixedActionFooter.setButtonText(textRes);
        } else if (this.primaryTextBottomBar != null) {
            this.primaryTextBottomBar.setText(textRes);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        if (this.fixedActionFooter != null) {
            this.fixedActionFooter.setButtonOnClickListener(listener);
        } else if (this.primaryTextBottomBar != null) {
            this.primaryTextBottomBar.setOnClickListener(listener);
        }
    }

    public void setOptionalText(String optionalText) {
        if (this.primaryTextBottomBar != null) {
            this.primaryTextBottomBar.setOptionalText(optionalText);
        }
    }
}
