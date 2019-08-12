package com.airbnb.android.photomarkupeditor.enums;

import com.airbnb.android.photomarkupeditor.C0904R;

public enum DrawingColor {
    Disabled(C0904R.color.black),
    Rausch(C0904R.color.n2_rausch),
    Babu(C0904R.color.n2_babu),
    Hof(C0904R.color.n2_hof),
    Beach(C0904R.color.n2_beach);
    
    public final int color;

    private DrawingColor(int color2) {
        this.color = color2;
    }
}
