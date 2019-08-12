package com.airbnb.android.core.views;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;

public class AppOverlayView {
    private final Context context;
    private LayoutParams params;
    private WindowManager windowManager;

    public AppOverlayView(Context context2) {
        this.context = context2;
        init();
    }

    private void init() {
        this.windowManager = (WindowManager) this.context.getSystemService("window");
        this.params = new LayoutParams(-1, -2, DatePickerDialog.DATE_PICKER_OK, 8, -3);
        this.params.gravity = 48;
        this.params.x = 0;
        this.params.y = 0;
    }

    public void addView(View view) {
        this.windowManager.addView(view, this.params);
    }

    public void removeView(View view) {
        if (view != null) {
            ((WindowManager) this.context.getSystemService("window")).removeView(view);
        }
    }
}
