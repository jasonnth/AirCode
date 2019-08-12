package com.airbnb.p027n2.collections;

import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.collections.SheetState */
public enum SheetState {
    Normal(R.color.n2_default_sheet_background, R.color.n2_babu_dark),
    Error(R.color.n2_error_sheet_background, R.color.n2_arches_dark);
    
    public final int backgroundColor;
    public final int progressBarColor;

    private SheetState(int backgroundColor2, int progressBarColor2) {
        this.backgroundColor = backgroundColor2;
        this.progressBarColor = progressBarColor2;
    }
}
