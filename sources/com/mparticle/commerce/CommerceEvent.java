package com.mparticle.commerce;

import com.mparticle.MParticle;
import com.mparticle.MParticle.Environment;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CommerceEvent {
    private Map<String, String> customAttributes;
    private String mCheckoutOptions;
    private Integer mCheckoutStep;
    private String mCurrency;
    private String mEventName;
    private List<Impression> mImpressions;
    /* access modifiers changed from: private */
    public Boolean mNonIteraction;
    private String mProductAction;
    private String mProductListName;
    private String mProductListSource;
    private String mPromotionAction;
    /* access modifiers changed from: private */
    public String mScreen;
    private TransactionAttributes mTransactionAttributes;
    private List<Product> productList;
    private List<Promotion> promotionList;

    public static class Builder {
        /* access modifiers changed from: private */
        public Map<String, String> customAttributes;
        /* access modifiers changed from: private */
        public String mCheckoutOptions;
        /* access modifiers changed from: private */
        public Integer mCheckoutStep;
        /* access modifiers changed from: private */
        public String mCurrency;
        /* access modifiers changed from: private */
        public String mEventName;
        /* access modifiers changed from: private */
        public List<Impression> mImpressions;
        /* access modifiers changed from: private */
        public Boolean mNonIteraction;
        /* access modifiers changed from: private */
        public final String mProductAction;
        /* access modifiers changed from: private */
        public String mProductListName;
        /* access modifiers changed from: private */
        public String mProductListSource;
        /* access modifiers changed from: private */
        public final String mPromotionAction;
        /* access modifiers changed from: private */
        public String mScreen;
        /* access modifiers changed from: private */
        public TransactionAttributes mTransactionAttributes;
        /* access modifiers changed from: private */
        public List<Product> productList;
        /* access modifiers changed from: private */
        public List<Promotion> promotionList;

        private Builder() {
            this.customAttributes = null;
            this.promotionList = null;
            this.productList = null;
            this.mCheckoutStep = null;
            this.mCheckoutOptions = null;
            this.mProductListName = null;
            this.mProductListSource = null;
            this.mCurrency = null;
            this.mTransactionAttributes = null;
            this.mScreen = null;
            this.mPromotionAction = null;
            this.mProductAction = null;
        }

        public Builder(String productAction, Product product) {
            this.customAttributes = null;
            this.promotionList = null;
            this.productList = null;
            this.mCheckoutStep = null;
            this.mCheckoutOptions = null;
            this.mProductListName = null;
            this.mProductListSource = null;
            this.mCurrency = null;
            this.mTransactionAttributes = null;
            this.mScreen = null;
            this.mProductAction = productAction;
            this.mPromotionAction = null;
            addProduct(product);
        }

        public Builder(String promotionAction, Promotion promotion) {
            this.customAttributes = null;
            this.promotionList = null;
            this.productList = null;
            this.mCheckoutStep = null;
            this.mCheckoutOptions = null;
            this.mProductListName = null;
            this.mProductListSource = null;
            this.mCurrency = null;
            this.mTransactionAttributes = null;
            this.mScreen = null;
            this.mProductAction = null;
            this.mPromotionAction = promotionAction;
            addPromotion(promotion);
        }

        public Builder(Impression impression) {
            this.customAttributes = null;
            this.promotionList = null;
            this.productList = null;
            this.mCheckoutStep = null;
            this.mCheckoutOptions = null;
            this.mProductListName = null;
            this.mProductListSource = null;
            this.mCurrency = null;
            this.mTransactionAttributes = null;
            this.mScreen = null;
            addImpression(impression);
            this.mPromotionAction = null;
            this.mProductAction = null;
        }

        public Builder(CommerceEvent event) {
            this.customAttributes = null;
            this.promotionList = null;
            this.productList = null;
            this.mCheckoutStep = null;
            this.mCheckoutOptions = null;
            this.mProductListName = null;
            this.mProductListSource = null;
            this.mCurrency = null;
            this.mTransactionAttributes = null;
            this.mScreen = null;
            this.mProductAction = event.getProductAction();
            this.mPromotionAction = event.getPromotionAction();
            if (event.getCustomAttributes() != null) {
                HashMap hashMap = new HashMap();
                hashMap.putAll(event.getCustomAttributes());
                this.customAttributes = hashMap;
            }
            if (event.getPromotions() != null) {
                for (Promotion promotion : event.getPromotions()) {
                    addPromotion(new Promotion(promotion));
                }
            }
            if (event.getProducts() != null) {
                for (Product builder : event.getProducts()) {
                    addProduct(new com.mparticle.commerce.Product.Builder(builder).build());
                }
            }
            this.mCheckoutStep = event.getCheckoutStep();
            this.mCheckoutOptions = event.getCheckoutOptions();
            this.mProductListName = event.getProductListName();
            this.mProductListSource = event.getProductListSource();
            this.mCurrency = event.getCurrency();
            if (event.getTransactionAttributes() != null) {
                this.mTransactionAttributes = new TransactionAttributes(event.getTransactionAttributes());
            }
            this.mScreen = event.mScreen;
            this.mNonIteraction = event.mNonIteraction;
            if (event.getImpressions() != null) {
                for (Impression impression : event.getImpressions()) {
                    addImpression(new Impression(impression));
                }
            }
            this.mEventName = event.getEventName();
        }

        public Builder screen(String screenName) {
            this.mScreen = screenName;
            return this;
        }

        public Builder addProduct(Product product) {
            if (this.productList == null) {
                this.productList = new LinkedList();
            }
            this.productList.add(product);
            return this;
        }

        public Builder transactionAttributes(TransactionAttributes attributes) {
            this.mTransactionAttributes = attributes;
            return this;
        }

        public Builder currency(String currency) {
            this.mCurrency = currency;
            return this;
        }

        public Builder nonInteraction(boolean userTriggered) {
            this.mNonIteraction = Boolean.valueOf(userTriggered);
            return this;
        }

        public Builder customAttributes(Map<String, String> attributes) {
            this.customAttributes = attributes;
            return this;
        }

        public Builder addPromotion(Promotion promotion) {
            if (this.promotionList == null) {
                this.promotionList = new LinkedList();
            }
            this.promotionList.add(promotion);
            return this;
        }

        public Builder checkoutStep(Integer step) {
            if (step == null || step.intValue() >= 0) {
                this.mCheckoutStep = step;
            }
            return this;
        }

        public Builder addImpression(Impression impression) {
            if (impression != null) {
                if (this.mImpressions == null) {
                    this.mImpressions = new LinkedList();
                }
                this.mImpressions.add(impression);
            }
            return this;
        }

        public Builder checkoutOptions(String options) {
            this.mCheckoutOptions = options;
            return this;
        }

        public CommerceEvent build() {
            return new CommerceEvent(this);
        }

        public Builder productListName(String listName) {
            this.mProductListName = listName;
            return this;
        }

        public Builder productListSource(String listSource) {
            this.mProductListSource = listSource;
            return this;
        }

        public Builder products(List<Product> products) {
            this.productList = products;
            return this;
        }

        public Builder impressions(List<Impression> impressions) {
            this.mImpressions = impressions;
            return this;
        }

        public Builder promotions(List<Promotion> promotions) {
            this.promotionList = promotions;
            return this;
        }

        public Builder internalEventName(String eventName) {
            this.mEventName = eventName;
            return this;
        }
    }

    private CommerceEvent(Builder builder) {
        double d;
        double d2;
        double d3;
        double d4 = 0.0d;
        this.mProductAction = builder.mProductAction;
        this.mPromotionAction = builder.mPromotionAction;
        this.customAttributes = builder.customAttributes;
        this.promotionList = builder.promotionList;
        if (builder.productList != null) {
            this.productList = new LinkedList(builder.productList);
        }
        this.mCheckoutStep = builder.mCheckoutStep;
        this.mCheckoutOptions = builder.mCheckoutOptions;
        this.mProductListName = builder.mProductListName;
        this.mProductListSource = builder.mProductListSource;
        this.mCurrency = builder.mCurrency;
        this.mTransactionAttributes = builder.mTransactionAttributes;
        this.mScreen = builder.mScreen;
        this.mImpressions = builder.mImpressions;
        this.mNonIteraction = builder.mNonIteraction;
        this.mEventName = builder.mEventName;
        boolean equals = MParticle.getInstance().getEnvironment().equals(Environment.Development);
        if (MPUtility.isEmpty(this.mProductAction) && MPUtility.isEmpty(this.mPromotionAction) && (this.mImpressions == null || this.mImpressions.size() == 0)) {
            if (equals) {
                throw new IllegalStateException("CommerceEvent must be created with either a product action. promotion action, or an impression");
            }
            ConfigManager.log(LogLevel.ERROR, "CommerceEvent must be created with either a product action, promotion action, or an impression");
        }
        if (this.mProductAction != null) {
            if ((this.mProductAction.equalsIgnoreCase(Product.PURCHASE) || this.mProductAction.equalsIgnoreCase(Product.REFUND)) && (this.mTransactionAttributes == null || MPUtility.isEmpty(this.mTransactionAttributes.getId()))) {
                String str = "CommerceEvent with action " + this.mProductAction + " must include a TransactionAttributes object with a transaction ID.";
                if (equals) {
                    throw new IllegalStateException(str);
                }
                ConfigManager.log(LogLevel.ERROR, str);
            }
            if (this.promotionList != null && this.promotionList.size() > 0) {
                if (equals) {
                    throw new IllegalStateException("Product CommerceEvent should not contain Promotions.");
                }
                ConfigManager.log(LogLevel.ERROR, "Product CommerceEvent should not contain Promotions.");
            }
            if (!this.mProductAction.equals(Product.ADD_TO_CART) && !this.mProductAction.equals(Product.ADD_TO_WISHLIST) && !this.mProductAction.equals(Product.CHECKOUT) && !this.mProductAction.equals(Product.CHECKOUT_OPTION) && !this.mProductAction.equals("click") && !this.mProductAction.equals(Product.DETAIL) && !this.mProductAction.equals(Product.PURCHASE) && !this.mProductAction.equals(Product.REFUND) && !this.mProductAction.equals(Product.REMOVE_FROM_CART) && !this.mProductAction.equals(Product.REMOVE_FROM_WISHLIST)) {
                ConfigManager.log(LogLevel.ERROR, "CommerceEvent created with unrecognized Product action: " + this.mProductAction);
            }
        } else if (this.mPromotionAction != null) {
            if (this.productList != null && this.productList.size() > 0) {
                if (equals) {
                    throw new IllegalStateException("Promotion CommerceEvent should not contain Products.");
                }
                ConfigManager.log(LogLevel.ERROR, "Promotion CommerceEvent should not contain Products.");
            }
            if (!this.mPromotionAction.equals("view") && !this.mPromotionAction.equals("click")) {
                ConfigManager.log(LogLevel.ERROR, "CommerceEvent created with unrecognized Promotion action: " + this.mProductAction);
            }
        } else {
            if (this.productList != null && this.productList.size() > 0) {
                if (equals) {
                    throw new IllegalStateException("Impression CommerceEvent should not contain Products.");
                }
                ConfigManager.log(LogLevel.ERROR, "Impression CommerceEvent should not contain Products.");
            }
            if (this.promotionList != null && this.promotionList.size() > 0) {
                if (equals) {
                    throw new IllegalStateException("Impression CommerceEvent should not contain Promotions.");
                }
                ConfigManager.log(LogLevel.ERROR, "Impression CommerceEvent should not contain Promotions.");
            }
        }
        if (this.mTransactionAttributes == null || this.mTransactionAttributes.getRevenue() == null) {
            if (this.mTransactionAttributes == null) {
                this.mTransactionAttributes = new TransactionAttributes();
            } else {
                Double shipping = this.mTransactionAttributes.getShipping();
                Double tax = this.mTransactionAttributes.getTax();
                if (shipping != null) {
                    d = shipping.doubleValue();
                } else {
                    d = 0.0d;
                }
                double d5 = d + 0.0d;
                if (tax != null) {
                    d4 = tax.doubleValue();
                }
                d4 += d5;
            }
            if (this.productList != null) {
                Iterator it = this.productList.iterator();
                while (true) {
                    d3 = d2;
                    if (!it.hasNext()) {
                        break;
                    }
                    Product product = (Product) it.next();
                    if (product != null) {
                        d2 = (product.getQuantity() * product.getUnitPrice()) + d3;
                    } else {
                        d2 = d3;
                    }
                }
            } else {
                d3 = d2;
            }
            this.mTransactionAttributes.setRevenue(Double.valueOf(d3));
        }
    }

    private CommerceEvent() {
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.mScreen != null) {
                jSONObject.put("sn", this.mScreen);
            }
            if (this.mNonIteraction != null) {
                jSONObject.put("ni", this.mNonIteraction.booleanValue());
            }
            if (this.mProductAction != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put("pd", jSONObject2);
                jSONObject2.put("an", this.mProductAction);
                if (this.mCheckoutStep != null) {
                    jSONObject2.put("cs", this.mCheckoutStep);
                }
                if (this.mCheckoutOptions != null) {
                    jSONObject2.put("co", this.mCheckoutOptions);
                }
                if (this.mProductListName != null) {
                    jSONObject2.put("pal", this.mProductListName);
                }
                if (this.mProductListSource != null) {
                    jSONObject2.put("pls", this.mProductListSource);
                }
                if (this.mTransactionAttributes != null) {
                    if (this.mTransactionAttributes.getId() != null) {
                        jSONObject2.put("ti", this.mTransactionAttributes.getId());
                    }
                    if (this.mTransactionAttributes.getAffiliation() != null) {
                        jSONObject2.put("ta", this.mTransactionAttributes.getAffiliation());
                    }
                    if (this.mTransactionAttributes.getRevenue() != null) {
                        jSONObject2.put("tr", this.mTransactionAttributes.getRevenue());
                    }
                    if (this.mTransactionAttributes.getTax() != null) {
                        jSONObject2.put("tt", this.mTransactionAttributes.getTax());
                    }
                    if (this.mTransactionAttributes.getShipping() != null) {
                        jSONObject2.put("ts", this.mTransactionAttributes.getShipping());
                    }
                    if (this.mTransactionAttributes.getCouponCode() != null) {
                        jSONObject2.put("tcc", this.mTransactionAttributes.getCouponCode());
                    }
                }
                if (this.productList != null && this.productList.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < this.productList.size(); i++) {
                        jSONArray.put(new JSONObject(((Product) this.productList.get(i)).toString()));
                    }
                    jSONObject2.put("pl", jSONArray);
                }
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject.put("pm", jSONObject3);
                jSONObject3.put("an", this.mPromotionAction);
                if (this.promotionList != null && this.promotionList.size() > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < this.promotionList.size(); i2++) {
                        jSONArray2.put(new JSONObject(((Promotion) this.promotionList.get(i2)).toString()));
                    }
                    jSONObject3.put("pl", jSONArray2);
                }
            }
            if (this.mImpressions != null && this.mImpressions.size() > 0) {
                JSONArray jSONArray3 = new JSONArray();
                for (Impression impression : this.mImpressions) {
                    JSONObject jSONObject4 = new JSONObject();
                    if (impression.getListName() != null) {
                        jSONObject4.put("pil", impression.getListName());
                    }
                    if (impression.getProducts() != null && impression.getProducts().size() > 0) {
                        JSONArray jSONArray4 = new JSONArray();
                        jSONObject4.put("pl", jSONArray4);
                        for (Product product : impression.getProducts()) {
                            jSONArray4.put(new JSONObject(product.toString()));
                        }
                    }
                    if (jSONObject4.length() > 0) {
                        jSONArray3.put(jSONObject4);
                    }
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("pi", jSONArray3);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            return super.toString();
        }
    }

    public Map<String, String> getCustomAttributes() {
        return this.customAttributes;
    }

    public String getScreen() {
        return this.mScreen;
    }

    public Boolean getNonInteraction() {
        return this.mNonIteraction;
    }

    public String getProductAction() {
        return this.mProductAction;
    }

    public Integer getCheckoutStep() {
        return this.mCheckoutStep;
    }

    public String getCheckoutOptions() {
        return this.mCheckoutOptions;
    }

    public String getProductListName() {
        return this.mProductListName;
    }

    public String getProductListSource() {
        return this.mProductListSource;
    }

    public TransactionAttributes getTransactionAttributes() {
        return this.mTransactionAttributes;
    }

    public List<Product> getProducts() {
        if (this.productList == null) {
            return null;
        }
        return Collections.unmodifiableList(this.productList);
    }

    public String getPromotionAction() {
        return this.mPromotionAction;
    }

    public List<Promotion> getPromotions() {
        if (this.promotionList == null) {
            return null;
        }
        return Collections.unmodifiableList(this.promotionList);
    }

    public boolean equals(Object o) {
        return o != null && o.toString().equals(toString());
    }

    public List<Impression> getImpressions() {
        if (this.mImpressions == null) {
            return null;
        }
        return Collections.unmodifiableList(this.mImpressions);
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public String getEventName() {
        return this.mEventName;
    }
}
