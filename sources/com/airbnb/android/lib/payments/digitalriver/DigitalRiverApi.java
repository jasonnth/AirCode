package com.airbnb.android.lib.payments.digitalriver;

import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import java.io.IOException;

public interface DigitalRiverApi {
    void tokenize(DigitalRiverCreditCard digitalRiverCreditCard, String str, String str2, DigitalRiverTokenizerListener digitalRiverTokenizerListener) throws IOException;
}
