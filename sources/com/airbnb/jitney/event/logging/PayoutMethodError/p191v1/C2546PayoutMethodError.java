package com.airbnb.jitney.event.logging.PayoutMethodError.p191v1;

/* renamed from: com.airbnb.jitney.event.logging.PayoutMethodError.v1.PayoutMethodError */
public enum C2546PayoutMethodError {
    InvalidSwift(1),
    InvalidIban(2),
    SwiftLengthNotMatch(3),
    IbanLengthNotMatch(4),
    AccountNameRequired(5),
    IbanRequired(6),
    SwiftRequired(7),
    EmailRequired(8),
    IbanNotMatch(9),
    LengthNotMatch(10),
    RequiredField(11),
    ConfirmationNotMatch(12),
    InvalidField(13);
    
    public final int value;

    private C2546PayoutMethodError(int value2) {
        this.value = value2;
    }
}
