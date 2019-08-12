package com.facebook.accountkit.p029ui;

import com.facebook.accountkit.C3354R;

/* renamed from: com.facebook.accountkit.ui.ButtonType */
public enum ButtonType {
    BEGIN(C3354R.string.com_accountkit_button_begin),
    CONFIRM(C3354R.string.com_accountkit_button_confirm),
    CONTINUE(C3354R.string.com_accountkit_button_continue),
    LOG_IN(C3354R.string.com_accountkit_button_log_in),
    NEXT(C3354R.string.com_accountkit_button_next),
    OK(C3354R.string.com_accountkit_button_ok),
    SEND(C3354R.string.com_accountkit_button_send),
    START(C3354R.string.com_accountkit_button_start),
    SUBMIT(C3354R.string.com_accountkit_button_submit);
    
    private final int value;

    private ButtonType(int value2) {
        this.value = value2;
    }

    public int getValue() {
        return this.value;
    }
}
