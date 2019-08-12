package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;

public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static ReactFontManager sReactFontManagerInstance;
    private Map<String, FontFamily> mFontCache = new HashMap();

    private static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        public Typeface getTypeface(int style) {
            return (Typeface) this.mTypefaceSparseArray.get(style);
        }

        public void setTypeface(int style, Typeface typeface) {
            this.mTypefaceSparseArray.put(style, typeface);
        }
    }

    private ReactFontManager() {
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    public Typeface getTypeface(String fontFamilyName, int style, AssetManager assetManager) {
        FontFamily fontFamily = (FontFamily) this.mFontCache.get(fontFamilyName);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(fontFamilyName, fontFamily);
        }
        Typeface typeface = fontFamily.getTypeface(style);
        if (typeface == null) {
            typeface = createTypeface(fontFamilyName, style, assetManager);
            if (typeface != null) {
                fontFamily.setTypeface(style, typeface);
            }
        }
        return typeface;
    }

    public void setTypeface(String fontFamilyName, int style, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = (FontFamily) this.mFontCache.get(fontFamilyName);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(fontFamilyName, fontFamily);
            }
            fontFamily.setTypeface(style, typeface);
        }
    }

    private static Typeface createTypeface(String fontFamilyName, int style, AssetManager assetManager) {
        String extension = EXTENSIONS[style];
        String[] strArr = FILE_EXTENSIONS;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                return Typeface.createFromAsset(assetManager, FONTS_ASSET_PATH + fontFamilyName + extension + strArr[i]);
            } catch (RuntimeException e) {
                i++;
            }
        }
        return Typeface.create(fontFamilyName, style);
    }
}
