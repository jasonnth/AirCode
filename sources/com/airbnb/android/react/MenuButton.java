package com.airbnb.android.react;

class MenuButton {
    final int icon;
    final int title;
    final boolean useForegroundColor;

    MenuButton(int icon2, int title2) {
        this(icon2, title2, true);
    }

    MenuButton(int icon2, int title2, boolean useForegroundColor2) {
        this.icon = icon2;
        this.title = title2;
        this.useForegroundColor = useForegroundColor2;
    }
}
