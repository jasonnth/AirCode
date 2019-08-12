package com.mparticle.commerce;

import android.content.Context;
import android.content.SharedPreferences;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductBagApi {
    private List<ProductBag> bags;
    private final Context mContext;

    private ProductBagApi() {
        this.mContext = null;
    }

    public ProductBagApi(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public List<ProductBag> getBags() {
        restoreBags();
        return Collections.unmodifiableList(this.bags);
    }

    public boolean addProduct(String bagName, Product product) {
        if (MPUtility.isEmpty(bagName)) {
            ConfigManager.log(LogLevel.ERROR, "Bag name must not be null or empty when calling ProductBags.addProduct()");
            return false;
        }
        if (product == null) {
            ConfigManager.log(LogLevel.WARNING, "Null Product instance passed to ProductBags.addProduct(), creating empty bag.");
        }
        restoreBags();
        ProductBag findBag = findBag(bagName);
        if (findBag == null) {
            findBag = new ProductBag(bagName);
            this.bags.add(findBag);
        }
        if (product != null) {
            findBag.products.add(product);
        }
        save();
        return true;
    }

    public ProductBag findBag(String bagName) {
        if (MPUtility.isEmpty(bagName)) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.bags.size()) {
                return null;
            }
            if (bagName.equalsIgnoreCase(((ProductBag) this.bags.get(i2)).bagName)) {
                return (ProductBag) this.bags.get(i2);
            }
            i = i2 + 1;
        }
    }

    public boolean removeProduct(String bagName, Product product) {
        if (MPUtility.isEmpty(bagName)) {
            ConfigManager.log(LogLevel.ERROR, "Bag name must not be null or empty when calling ProductBags.removeProduct()");
            return false;
        } else if (product == null) {
            ConfigManager.log(LogLevel.ERROR, "Null Product instance passed to ProductBags.removeProduct()");
            return false;
        } else {
            restoreBags();
            ProductBag findBag = findBag(bagName);
            if (findBag == null) {
                ConfigManager.log(LogLevel.ERROR, "Could not find Product Bag: " + bagName + " while trying to remove Product.");
                return false;
            } else if (!findBag.products.remove(product)) {
                ConfigManager.log(LogLevel.ERROR, "Failed to remove Product:\n" + product.toString() + "\n" + "from Product Bag: " + bagName + " - see Product.setEqualityComparator.");
                return false;
            } else {
                save();
                return true;
            }
        }
    }

    public boolean clearProductBag(String bagName) {
        if (MPUtility.isEmpty(bagName)) {
            ConfigManager.log(LogLevel.ERROR, "Bag name should not be null or empty when calling ProductBags.clearProductBag()");
            return false;
        }
        restoreBags();
        ProductBag findBag = findBag(bagName);
        if (findBag == null) {
            return false;
        }
        findBag.products.clear();
        save();
        return true;
    }

    public boolean removeProductBag(String bagName) {
        if (MPUtility.isEmpty(bagName)) {
            ConfigManager.log(LogLevel.ERROR, "Bag name should not be null or empty when calling ProductBags.removeProductBag()");
            return false;
        }
        restoreBags();
        ProductBag findBag = findBag(bagName);
        if (findBag == null) {
            return false;
        }
        this.bags.remove(findBag);
        save();
        return true;
    }

    private void save() {
        if (this.bags != null) {
            this.mContext.getSharedPreferences("mParticlePrefs_productbags", 0).edit().putString("mp::productbags", toString()).apply();
        }
    }

    public String toString() {
        restoreBags();
        JSONObject jSONObject = new JSONObject();
        if (this.bags != null) {
            for (int i = 0; i < this.bags.size(); i++) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject.put(((ProductBag) this.bags.get(i)).getName(), jSONObject2);
                    JSONArray jSONArray = new JSONArray();
                    for (Product json : ((ProductBag) this.bags.get(i)).getProducts()) {
                        jSONArray.put(json.toJson());
                    }
                    jSONObject2.put("pl", jSONArray);
                } catch (JSONException e) {
                }
            }
        }
        return jSONObject.toString();
    }

    private void restoreBags() {
        if (this.bags == null) {
            this.bags = new LinkedList();
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("mParticlePrefs_productbags", 0);
            if (sharedPreferences.contains("mp::productbags")) {
                try {
                    JSONObject jSONObject = new JSONObject(sharedPreferences.getString("mp::productbags", null));
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        ProductBag productBag = new ProductBag(str);
                        JSONArray optJSONArray = jSONObject.getJSONObject(str).optJSONArray("pl");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                productBag.products.add(Product.fromJson(optJSONArray.getJSONObject(i)));
                            }
                        }
                        this.bags.add(productBag);
                    }
                } catch (Exception e) {
                    sharedPreferences.edit().clear().apply();
                }
            }
        }
    }
}
