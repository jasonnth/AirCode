package com.airbnb.p027n2.primitives.fonts;

/* renamed from: com.airbnb.n2.primitives.fonts.Font */
public enum Font {
    Default(null),
    AirGlyphs("fonts/Airglyphs.ttf"),
    CircularBook("fonts/CircularAir-Book.otf"),
    CircularBold("fonts/CircularAir-Bold.otf"),
    CircularLight("fonts/CircularAir-Light.otf"),
    CircularExtraBlack("fonts/CircularAir-ExtraBlack.otf"),
    CircularCondensedBold("fonts/CircularAir-CondBold.otf");
    
    public final String mFilename;

    private Font(String filename) {
        this.mFilename = filename;
    }

    public static Font getFont(int index) {
        Font[] values = values();
        if (index < 0 || index >= values.length) {
            return null;
        }
        return values[index];
    }
}
