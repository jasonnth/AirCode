package com.facebook.accountkit.internal;

public enum Feature {
    EMAIL_ENABLED(0, 1),
    PHONE_NUMBER_ENABLED(1, 1),
    CALLBACK_BUTTON_ALTERNATE_TEXT(2, 1);
    
    private int defaultValue;
    private int prefKey;

    private Feature(int prefKey2, int defaultValue2) {
        this.prefKey = prefKey2;
        this.defaultValue = defaultValue2;
    }

    /* access modifiers changed from: 0000 */
    public int getPrefKey() {
        return this.prefKey;
    }

    /* access modifiers changed from: 0000 */
    public int getDefaultValue() {
        return this.defaultValue;
    }
}
