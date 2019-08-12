package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.p000v4.util.LongSparseArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

public class LottieComposition {
    private final Rect bounds;
    private final float dpScale;
    private final long endFrame;
    private final int frameRate;
    /* access modifiers changed from: private */
    public final Map<String, LottieImageAsset> images;
    /* access modifiers changed from: private */
    public final LongSparseArray<Layer> layerMap;
    /* access modifiers changed from: private */
    public final List<Layer> layers;
    /* access modifiers changed from: private */
    public final Map<String, List<Layer>> precomps;
    private final long startFrame;

    public static class Factory {
        public static Cancellable fromAssetFileName(Context context, String fileName, OnCompositionLoadedListener loadedListener) {
            try {
                return fromInputStream(context, context.getAssets().open(fileName), loadedListener);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to find file " + fileName, e);
            }
        }

        public static Cancellable fromInputStream(Context context, InputStream stream, OnCompositionLoadedListener loadedListener) {
            FileCompositionLoader loader = new FileCompositionLoader(context.getResources(), loadedListener);
            loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new InputStream[]{stream});
            return loader;
        }

        public static Cancellable fromJson(Resources res, JSONObject json, OnCompositionLoadedListener loadedListener) {
            JsonCompositionLoader loader = new JsonCompositionLoader(res, loadedListener);
            loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject[]{json});
            return loader;
        }

        static LottieComposition fromInputStream(Resources res, InputStream stream) {
            try {
                byte[] buffer = new byte[stream.available()];
                stream.read(buffer);
                LottieComposition fromJsonSync = fromJsonSync(res, new JSONObject(new String(buffer, JPushConstants.ENCODING_UTF_8)));
                Utils.closeQuietly(stream);
                return fromJsonSync;
            } catch (IOException e) {
                throw new IllegalStateException("Unable to find file.", e);
            } catch (JSONException e2) {
                throw new IllegalStateException("Unable to load JSON.", e2);
            } catch (Throwable th) {
                Utils.closeQuietly(stream);
                throw th;
            }
        }

        static LottieComposition fromJsonSync(Resources res, JSONObject json) {
            Rect bounds = null;
            float scale = res.getDisplayMetrics().density;
            int width = json.optInt("w", -1);
            int height = json.optInt("h", -1);
            if (!(width == -1 || height == -1)) {
                bounds = new Rect(0, 0, (int) (((float) width) * scale), (int) (((float) height) * scale));
            }
            LottieComposition composition = new LottieComposition(bounds, json.optLong("ip", 0), json.optLong("op", 0), json.optInt("fr", 0), scale);
            JSONArray assetsJson = json.optJSONArray("assets");
            parseImages(assetsJson, composition);
            parsePrecomps(assetsJson, composition);
            parseLayers(json, composition);
            return composition;
        }

        private static void parseLayers(JSONObject json, LottieComposition composition) {
            JSONArray jsonLayers = json.optJSONArray("layers");
            int length = jsonLayers.length();
            for (int i = 0; i < length; i++) {
                addLayer(composition.layers, composition.layerMap, Factory.newInstance(jsonLayers.optJSONObject(i), composition));
            }
        }

        private static void parsePrecomps(JSONArray assetsJson, LottieComposition composition) {
            if (assetsJson != null) {
                int length = assetsJson.length();
                for (int i = 0; i < length; i++) {
                    JSONObject assetJson = assetsJson.optJSONObject(i);
                    JSONArray layersJson = assetJson.optJSONArray("layers");
                    if (layersJson != null) {
                        List<Layer> layers = new ArrayList<>(layersJson.length());
                        LongSparseArray<Layer> layerMap = new LongSparseArray<>();
                        for (int j = 0; j < layersJson.length(); j++) {
                            Layer layer = Factory.newInstance(layersJson.optJSONObject(j), composition);
                            layerMap.put(layer.getId(), layer);
                            layers.add(layer);
                        }
                        composition.precomps.put(assetJson.optString("id"), layers);
                    }
                }
            }
        }

        private static void parseImages(JSONArray assetsJson, LottieComposition composition) {
            if (assetsJson != null) {
                int length = assetsJson.length();
                for (int i = 0; i < length; i++) {
                    JSONObject assetJson = assetsJson.optJSONObject(i);
                    if (assetJson.has("p")) {
                        LottieImageAsset image = Factory.newInstance(assetJson);
                        composition.images.put(image.getId(), image);
                    }
                }
            }
        }

        private static void addLayer(List<Layer> layers, LongSparseArray<Layer> layerMap, Layer layer) {
            layers.add(layer);
            layerMap.put(layer.getId(), layer);
        }
    }

    private LottieComposition(Rect bounds2, long startFrame2, long endFrame2, int frameRate2, float dpScale2) {
        this.precomps = new HashMap();
        this.images = new HashMap();
        this.layerMap = new LongSparseArray<>();
        this.layers = new ArrayList();
        this.bounds = bounds2;
        this.startFrame = startFrame2;
        this.endFrame = endFrame2;
        this.frameRate = frameRate2;
        this.dpScale = dpScale2;
    }

    /* access modifiers changed from: 0000 */
    public Layer layerModelForId(long id) {
        return (Layer) this.layerMap.get(id);
    }

    public Rect getBounds() {
        return this.bounds;
    }

    public long getDuration() {
        return (long) ((((float) (this.endFrame - this.startFrame)) / ((float) this.frameRate)) * 1000.0f);
    }

    /* access modifiers changed from: 0000 */
    public long getEndFrame() {
        return this.endFrame;
    }

    /* access modifiers changed from: 0000 */
    public List<Layer> getLayers() {
        return this.layers;
    }

    /* access modifiers changed from: 0000 */
    public List<Layer> getPrecomps(String id) {
        return (List) this.precomps.get(id);
    }

    /* access modifiers changed from: 0000 */
    public Map<String, LottieImageAsset> getImages() {
        return this.images;
    }

    /* access modifiers changed from: 0000 */
    public float getDurationFrames() {
        return (((float) getDuration()) * ((float) this.frameRate)) / 1000.0f;
    }

    public float getDpScale() {
        return this.dpScale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        for (Layer layer : this.layers) {
            sb.append(layer.toString("\t"));
        }
        return sb.toString();
    }
}
