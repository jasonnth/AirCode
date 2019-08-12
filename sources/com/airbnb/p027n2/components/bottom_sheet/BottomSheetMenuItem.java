package com.airbnb.p027n2.components.bottom_sheet;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;

/* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetMenuItem */
public class BottomSheetMenuItem implements BottomSheetItem {
    private final int background;
    private final Drawable drawable;

    /* renamed from: id */
    private final int f2704id;
    private final int textColor;
    private final String title;

    public BottomSheetMenuItem(MenuItem item, int textColor2, int background2) {
        this.f2704id = item.getItemId();
        this.drawable = item.getIcon();
        this.title = item.getTitle().toString();
        this.textColor = textColor2;
        this.background = background2;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public int getBackground() {
        return this.background;
    }

    public int getId() {
        return this.f2704id;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public String getTitle() {
        return this.title;
    }
}
