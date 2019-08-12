package com.mparticle.commerce;

import android.content.Context;
import android.content.SharedPreferences;
import com.mparticle.MParticle;
import com.mparticle.commerce.CommerceEvent.Builder;
import com.mparticle.commerce.Product.EqualityComparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Cart {
    /* access modifiers changed from: private */
    public static Context mContext;
    private final SharedPreferences prefs;
    private final List<Product> productList;

    /* renamed from: com.mparticle.commerce.Cart$a */
    private static class C4588a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static Cart f3688a = new Cart(Cart.mContext);
    }

    private Cart(Context context) {
        this.prefs = context.getSharedPreferences("mParticlePrefs_cart", 0);
        this.productList = new LinkedList();
        loadFromString(this.prefs.getString("mp::cart", null));
    }

    public static Cart getInstance(Context context) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        mContext = context;
        return C4588a.f3688a;
    }

    public static void setProductEqualityComparator(EqualityComparator comparator) {
        Product.setEqualityComparator(comparator);
    }

    public synchronized Cart add(Product product) {
        return add(product, true);
    }

    public synchronized Cart add(Product product, boolean logEvent) {
        if (product != null) {
            if (!this.productList.contains(product)) {
                product.updateTimeAdded();
                this.productList.add(product);
                save();
                if (logEvent) {
                    MParticle.getInstance().logEvent(new Builder(Product.ADD_TO_CART, product).build());
                }
            }
        }
        return this;
    }

    public synchronized Cart remove(Product product) {
        return remove(product, true);
    }

    public synchronized Cart remove(Product product, boolean logEvent) {
        if (product != null) {
            if (this.productList.remove(product)) {
                save();
            }
        }
        if (logEvent) {
            MParticle.getInstance().logEvent(new Builder(Product.REMOVE_FROM_CART, product).build());
        }
        return this;
    }

    public synchronized boolean remove(int index) {
        if (index >= 0) {
            if (this.productList.size() > index) {
                Product product = (Product) this.productList.remove(index);
                save();
                MParticle.getInstance().logEvent(new Builder(Product.REMOVE_FROM_CART, product).build());
            }
        }
        return false;
    }

    public synchronized void loadFromString(String cartJson) {
        if (cartJson != null) {
            try {
                JSONArray jSONArray = new JSONObject(cartJson).getJSONArray("pl");
                clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.productList.add(Product.fromJson(jSONArray.getJSONObject(i)));
                }
                save();
            } catch (JSONException e) {
            }
        }
    }

    public synchronized String toString() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.productList.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.productList.size()) {
                    break;
                }
                jSONArray.put(((Product) this.productList.get(i2)).toJson());
                i = i2 + 1;
            }
            try {
                jSONObject.put("pl", jSONArray);
            } catch (JSONException e) {
            }
        }
        return jSONObject.toString();
    }

    public synchronized Cart clear() {
        this.productList.clear();
        save();
        return this;
    }

    private synchronized void save() {
        this.prefs.edit().putString("mp::cart", toString()).apply();
    }

    public synchronized Product getProduct(String name) {
        Product product;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.productList.size()) {
                if (((Product) this.productList.get(i2)).getName() != null && ((Product) this.productList.get(i2)).getName().equalsIgnoreCase(name)) {
                    product = (Product) this.productList.get(i2);
                    break;
                }
                i = i2 + 1;
            } else {
                product = null;
                break;
            }
        }
        return product;
    }

    public List<Product> products() {
        return Collections.unmodifiableList(this.productList);
    }
}
