package com.airbnb.p027n2.components.bottom_sheet;

/* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetHeader */
public class BottomSheetHeader implements BottomSheetItem {
    private final int textColor;
    private final String title;

    public BottomSheetHeader(String title2, int textColor2) {
        this.title = title2;
        this.textColor = textColor2;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public String getTitle() {
        return this.title;
    }
}
