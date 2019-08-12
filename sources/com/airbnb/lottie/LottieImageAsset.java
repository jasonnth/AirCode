package com.airbnb.lottie;

import org.json.JSONObject;

public class LottieImageAsset {
    private final String fileName;
    private final int height;

    /* renamed from: id */
    private final String f2701id;
    private final int width;

    static class Factory {
        static LottieImageAsset newInstance(JSONObject imageJson) {
            return new LottieImageAsset(imageJson.optInt("w"), imageJson.optInt("h"), imageJson.optString("id"), imageJson.optString("p"));
        }
    }

    private LottieImageAsset(int width2, int height2, String id, String fileName2) {
        this.width = width2;
        this.height = height2;
        this.f2701id = id;
        this.fileName = fileName2;
    }

    public String getId() {
        return this.f2701id;
    }

    public String getFileName() {
        return this.fileName;
    }
}
