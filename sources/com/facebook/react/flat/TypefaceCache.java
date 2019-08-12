package com.facebook.react.flat;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.facebook.infer.annotation.Assertions;
import java.util.HashMap;

final class TypefaceCache {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final HashMap<String, Typeface[]> FONTFAMILY_CACHE = new HashMap<>();
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static final int MAX_STYLES = 4;
    private static final HashMap<Typeface, Typeface[]> TYPEFACE_CACHE = new HashMap<>();
    private static AssetManager sAssetManager = null;

    TypefaceCache() {
    }

    public static void setAssetManager(AssetManager assetManager) {
        sAssetManager = assetManager;
    }

    public static Typeface getTypeface(String fontFamily, int style) {
        Typeface[] cache = (Typeface[]) FONTFAMILY_CACHE.get(fontFamily);
        if (cache == null) {
            cache = new Typeface[4];
            FONTFAMILY_CACHE.put(fontFamily, cache);
        } else if (cache[style] != null) {
            return cache[style];
        }
        Typeface typeface = createTypeface(fontFamily, style);
        cache[style] = typeface;
        TYPEFACE_CACHE.put(typeface, cache);
        return typeface;
    }

    private static Typeface createTypeface(String fontFamilyName, int style) {
        StringBuilder fileNameBuffer = new StringBuilder(32).append(FONTS_ASSET_PATH).append(fontFamilyName).append(EXTENSIONS[style]);
        int length = fileNameBuffer.length();
        String[] strArr = FILE_EXTENSIONS;
        int length2 = strArr.length;
        int i = 0;
        while (i < length2) {
            try {
                return Typeface.createFromAsset(sAssetManager, fileNameBuffer.append(strArr[i]).toString());
            } catch (RuntimeException e) {
                fileNameBuffer.setLength(length);
                i++;
            }
        }
        return (Typeface) Assertions.assumeNotNull(Typeface.create(fontFamilyName, style));
    }

    public static Typeface getTypeface(Typeface typeface, int style) {
        if (typeface == null) {
            return Typeface.defaultFromStyle(style);
        }
        Typeface[] cache = (Typeface[]) TYPEFACE_CACHE.get(typeface);
        if (cache == null) {
            cache = new Typeface[4];
            cache[typeface.getStyle()] = typeface;
        } else if (cache[style] != null) {
            return cache[style];
        }
        Typeface typeface2 = Typeface.create(typeface, style);
        cache[style] = typeface2;
        TYPEFACE_CACHE.put(typeface2, cache);
        return typeface2;
    }
}
