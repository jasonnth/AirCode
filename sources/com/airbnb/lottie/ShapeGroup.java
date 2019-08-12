package com.airbnb.lottie;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ShapeGroup {
    private final List<Object> items;
    private final String name;

    static class Factory {
        /* access modifiers changed from: private */
        public static ShapeGroup newInstance(JSONObject json, LottieComposition composition) {
            JSONArray jsonItems = json.optJSONArray("it");
            String name = json.optString("nm");
            List<Object> items = new ArrayList<>();
            for (int i = 0; i < jsonItems.length(); i++) {
                Object newItem = ShapeGroup.shapeItemWithJson(jsonItems.optJSONObject(i), composition);
                if (newItem != null) {
                    items.add(newItem);
                }
            }
            return new ShapeGroup(name, items);
        }
    }

    static Object shapeItemWithJson(JSONObject json, LottieComposition composition) {
        String type = json.optString("ty");
        char c = 65535;
        switch (type.hashCode()) {
            case 3239:
                if (type.equals("el")) {
                    c = 7;
                    break;
                }
                break;
            case 3270:
                if (type.equals("fl")) {
                    c = 3;
                    break;
                }
                break;
            case 3295:
                if (type.equals("gf")) {
                    c = 4;
                    break;
                }
                break;
            case 3307:
                if (type.equals("gr")) {
                    c = 0;
                    break;
                }
                break;
            case 3308:
                if (type.equals("gs")) {
                    c = 2;
                    break;
                }
                break;
            case 3488:
                if (type.equals("mm")) {
                    c = 11;
                    break;
                }
                break;
            case 3633:
                if (type.equals("rc")) {
                    c = 8;
                    break;
                }
                break;
            case 3669:
                if (type.equals("sh")) {
                    c = 6;
                    break;
                }
                break;
            case 3679:
                if (type.equals("sr")) {
                    c = 10;
                    break;
                }
                break;
            case 3681:
                if (type.equals("st")) {
                    c = 1;
                    break;
                }
                break;
            case 3705:
                if (type.equals("tm")) {
                    c = 9;
                    break;
                }
                break;
            case 3710:
                if (type.equals("tr")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Factory.newInstance(json, composition);
            case 1:
                return Factory.newInstance(json, composition);
            case 2:
                return Factory.newInstance(json, composition);
            case 3:
                return Factory.newInstance(json, composition);
            case 4:
                return Factory.newInstance(json, composition);
            case 5:
                return Factory.newInstance(json, composition);
            case 6:
                return Factory.newInstance(json, composition);
            case 7:
                return Factory.newInstance(json, composition);
            case 8:
                return Factory.newInstance(json, composition);
            case 9:
                return Factory.newInstance(json, composition);
            case 10:
                return Factory.newInstance(json, composition);
            case 11:
                return Factory.newInstance(json);
            default:
                Log.w("LOTTIE", "Unknown shape type " + type);
                return null;
        }
    }

    ShapeGroup(String name2, List<Object> items2) {
        this.name = name2;
        this.items = items2;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public List<Object> getItems() {
        return this.items;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.name + "' Shapes: " + Arrays.toString(this.items.toArray()) + '}';
    }
}
