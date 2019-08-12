package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.Price;

final /* synthetic */ class PriceBreakdownAdapter$$Lambda$1 implements OnClickListener {
    private final Context arg$1;
    private final Price arg$2;

    private PriceBreakdownAdapter$$Lambda$1(Context context, Price price) {
        this.arg$1 = context;
        this.arg$2 = price;
    }

    public static OnClickListener lambdaFactory$(Context context, Price price) {
        return new PriceBreakdownAdapter$$Lambda$1(context, price);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(WebViewIntentBuilder.newBuilder(this.arg$1, this.arg$2.getUrl()).toIntent());
    }
}
