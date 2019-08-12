package com.mparticle.commerce;

import android.content.Context;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.facebook.appevents.AppEventsConstants;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.commerce.CommerceEvent.Builder;
import com.mparticle.internal.ConfigManager;
import java.math.BigDecimal;
import java.util.List;

public class CommerceApi {
    Context mContext;

    private CommerceApi() {
    }

    public CommerceApi(Context context) {
        this.mContext = context;
    }

    public Cart cart() {
        return Cart.getInstance(this.mContext);
    }

    public synchronized void checkout(int step, String options) {
        List products = Cart.getInstance(this.mContext).products();
        if (products == null || products.size() <= 0) {
            ConfigManager.log(LogLevel.ERROR, "checkout() called but there are no Products in the Cart, no event was logged.");
        } else {
            MParticle.getInstance().logEvent(new Builder(Product.CHECKOUT, (Product) products.get(0)).checkoutStep(Integer.valueOf(step)).checkoutOptions(options).products(products).build());
        }
    }

    public synchronized void checkout() {
        List products = Cart.getInstance(this.mContext).products();
        if (products == null || products.size() <= 0) {
            ConfigManager.log(LogLevel.ERROR, "checkout() called but there are no Products in the Cart, no event was logged.");
        } else {
            MParticle.getInstance().logEvent(new Builder(Product.CHECKOUT, (Product) products.get(0)).products(products).build());
        }
    }

    public void purchase(TransactionAttributes attributes) {
        purchase(attributes, false);
    }

    public synchronized void purchase(TransactionAttributes attributes, boolean clearCart) {
        List products = Cart.getInstance(this.mContext).products();
        if (products == null || products.size() <= 0) {
            ConfigManager.log(LogLevel.ERROR, "purchase() called but there are no Products in the Cart, no event was logged.");
        } else {
            CommerceEvent build = new Builder(Product.PURCHASE, (Product) products.get(0)).products(products).transactionAttributes(attributes).build();
            if (clearCart) {
                Cart.getInstance(this.mContext).clear();
            }
            MParticle.getInstance().logEvent(build);
        }
    }

    public void refund(TransactionAttributes attributes, boolean clearCart) {
        List products = Cart.getInstance(this.mContext).products();
        if (products == null || products.size() <= 0) {
            ConfigManager.log(LogLevel.ERROR, "refund() called but there are no Products in the Cart, no event was logged.");
            return;
        }
        CommerceEvent build = new Builder(Product.REFUND, (Product) products.get(0)).products(products).transactionAttributes(attributes).build();
        if (clearCart) {
            Cart.getInstance(this.mContext).clear();
        }
        MParticle.getInstance().logEvent(build);
    }

    public BigDecimal getCurrentUserLtv() {
        try {
            return new BigDecimal(this.mContext.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0).getString("mp::ltv", AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException e) {
            return new BigDecimal(0);
        }
    }
}
