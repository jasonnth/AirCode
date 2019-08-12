package com.mparticle.commerce;

import com.facebook.internal.AnalyticsEvents;
import com.mparticle.MParticle;
import com.mparticle.MParticle.Environment;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public final class Product {
    public static final String ADD_TO_CART = "add_to_cart";
    public static final String ADD_TO_WISHLIST = "add_to_wishlist";
    public static final String CHECKOUT = "checkout";
    public static final String CHECKOUT_OPTION = "checkout_option";
    public static final String CLICK = "click";
    public static final String DETAIL = "view_detail";
    public static final String PURCHASE = "purchase";
    public static final String REFUND = "refund";
    public static final String REMOVE_FROM_CART = "remove_from_cart";
    public static final String REMOVE_FROM_WISHLIST = "remove_from_wishlist";
    private static EqualityComparator mComparator = null;
    /* access modifiers changed from: private */
    public String mBrand;
    /* access modifiers changed from: private */
    public String mCategory;
    /* access modifiers changed from: private */
    public String mCouponCode;
    private Map<String, String> mCustomAttributes;
    private String mName;
    /* access modifiers changed from: private */
    public Integer mPosition;
    /* access modifiers changed from: private */
    public double mPrice;
    /* access modifiers changed from: private */
    public double mQuantity;
    private String mSku;
    private long mTimeAdded;
    /* access modifiers changed from: private */
    public String mVariant;

    public static class Builder {
        /* access modifiers changed from: private */
        public String mBrand;
        /* access modifiers changed from: private */
        public String mCategory;
        /* access modifiers changed from: private */
        public String mCouponCode;
        /* access modifiers changed from: private */
        public Map<String, String> mCustomAttributes;
        /* access modifiers changed from: private */
        public String mName;
        /* access modifiers changed from: private */
        public Integer mPosition;
        /* access modifiers changed from: private */
        public double mPrice;
        /* access modifiers changed from: private */
        public double mQuantity;
        /* access modifiers changed from: private */
        public String mSku;
        /* access modifiers changed from: private */
        public String mVariant;

        private Builder() {
            this.mName = null;
            this.mQuantity = 1.0d;
            this.mCustomAttributes = null;
        }

        public Builder(String name, String sku, double unitPrice) {
            this.mName = null;
            this.mQuantity = 1.0d;
            this.mCustomAttributes = null;
            this.mName = name;
            this.mSku = sku;
            this.mPrice = unitPrice;
        }

        public Builder(Product product) {
            this(product.getName(), product.getSku(), product.getUnitPrice());
            this.mCategory = product.mCategory;
            this.mCouponCode = product.mCouponCode;
            this.mPosition = product.mPosition;
            this.mPrice = product.mPrice;
            this.mQuantity = product.mQuantity;
            this.mBrand = product.mBrand;
            this.mVariant = product.mVariant;
            if (product.getCustomAttributes() != null) {
                HashMap hashMap = new HashMap();
                hashMap.putAll(product.getCustomAttributes());
                this.mCustomAttributes = hashMap;
            }
        }

        public Builder customAttributes(Map<String, String> attributes) {
            this.mCustomAttributes = attributes;
            return this;
        }

        public Builder category(String category) {
            this.mCategory = category;
            return this;
        }

        public Builder couponCode(String couponCode) {
            this.mCouponCode = couponCode;
            return this;
        }

        public Builder sku(String sku) {
            this.mSku = sku;
            return this;
        }

        public Builder name(String name) {
            this.mName = name;
            return this;
        }

        public Builder position(Integer position) {
            this.mPosition = position;
            return this;
        }

        public Builder unitPrice(double price) {
            this.mPrice = price;
            return this;
        }

        public Builder quantity(double quantity) {
            this.mQuantity = quantity;
            return this;
        }

        public Builder brand(String brand) {
            this.mBrand = brand;
            return this;
        }

        public Builder variant(String variant) {
            this.mVariant = variant;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public interface EqualityComparator {
        boolean equals(Product product, Product product2);
    }

    public Map<String, String> getCustomAttributes() {
        return this.mCustomAttributes;
    }

    public double getTotalAmount() {
        return getUnitPrice() * getQuantity();
    }

    public static void setEqualityComparator(EqualityComparator comparator) {
        mComparator = comparator;
    }

    private Product() {
        this.mName = null;
    }

    private Product(Builder builder) {
        this.mName = null;
        this.mName = builder.mName;
        this.mCategory = builder.mCategory;
        this.mCouponCode = builder.mCouponCode;
        this.mSku = builder.mSku;
        this.mPosition = builder.mPosition;
        this.mPrice = builder.mPrice;
        this.mQuantity = builder.mQuantity;
        this.mBrand = builder.mBrand;
        this.mVariant = builder.mVariant;
        this.mCustomAttributes = builder.mCustomAttributes;
        updateTimeAdded();
        boolean equals = MParticle.getInstance().getEnvironment().equals(Environment.Development);
        if (MPUtility.isEmpty(this.mName)) {
            String str = "Product name is required.";
            if (equals) {
                throw new IllegalArgumentException(str);
            }
            this.mName = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            ConfigManager.log(LogLevel.ERROR, str);
        } else if (MPUtility.isEmpty(this.mSku)) {
            String str2 = "Product sku is required.";
            if (equals) {
                throw new IllegalArgumentException(str2);
            }
            this.mSku = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            ConfigManager.log(LogLevel.ERROR, str2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateTimeAdded() {
        this.mTimeAdded = System.currentTimeMillis();
    }

    public String getName() {
        return this.mName;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getSku() {
        return this.mSku;
    }

    public Integer getPosition() {
        return this.mPosition;
    }

    public double getUnitPrice() {
        return this.mPrice;
    }

    public double getQuantity() {
        if (this.mQuantity < 1.0d) {
            return 1.0d;
        }
        return this.mQuantity;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Product)) {
            return false;
        }
        Product product = (Product) object;
        if (mComparator != null) {
            return mComparator.equals(this, product);
        }
        return false;
    }

    public String getBrand() {
        return this.mBrand;
    }

    public String getVariant() {
        return this.mVariant;
    }

    public String toString() {
        return toJson().toString();
    }

    public static Product fromString(String json) {
        try {
            return fromJson(new JSONObject(json));
        } catch (JSONException e) {
            return null;
        }
    }

    static Product fromJson(JSONObject jsonObject) {
        try {
            Builder builder = new Builder(jsonObject.getString("nm"), jsonObject.optString("id", null), jsonObject.optDouble("pr", 0.0d));
            builder.category(jsonObject.optString("ca", null));
            builder.couponCode(jsonObject.optString("cc", null));
            if (jsonObject.has("ps")) {
                builder.position(Integer.valueOf(jsonObject.optInt("ps", 0)));
            }
            if (jsonObject.has("qt")) {
                builder.quantity(jsonObject.optDouble("qt", 1.0d));
            }
            builder.brand(jsonObject.optString("br", null));
            builder.variant(jsonObject.optString("va", null));
            if (jsonObject.has("attrs")) {
                JSONObject jSONObject = jsonObject.getJSONObject("attrs");
                if (jSONObject.length() > 0) {
                    HashMap hashMap = new HashMap();
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        hashMap.put(str, jSONObject.getString(str));
                    }
                    builder.customAttributes(hashMap);
                }
            }
            Product build = builder.build();
            build.mTimeAdded = jsonObject.optLong("act", 0);
            return build;
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.mName != null) {
                jSONObject.put("nm", this.mName);
            }
            if (this.mCategory != null) {
                jSONObject.put("ca", this.mCategory);
            }
            if (this.mCouponCode != null) {
                jSONObject.put("cc", this.mCouponCode);
            }
            if (this.mSku != null) {
                jSONObject.put("id", this.mSku);
            }
            if (this.mPosition != null) {
                jSONObject.put("ps", this.mPosition);
            }
            jSONObject.put("pr", this.mPrice);
            jSONObject.put("qt", this.mQuantity);
            jSONObject.put("act", this.mTimeAdded);
            jSONObject.put("tpa", getTotalAmount());
            if (this.mBrand != null) {
                jSONObject.put("br", this.mBrand);
            }
            if (this.mVariant != null) {
                jSONObject.put("va", this.mVariant);
            }
            if (this.mCustomAttributes != null && this.mCustomAttributes.size() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                for (Entry entry : this.mCustomAttributes.entrySet()) {
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject.put("attrs", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setQuantity(double quantity) {
        this.mQuantity = quantity;
    }
}
