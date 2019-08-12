package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airmapview.AirMapInterface;

/* renamed from: com.facebook.accountkit.ui.SkinManager */
public final class SkinManager extends BaseUIManager {
    public static final Creator<SkinManager> CREATOR = new Creator<SkinManager>() {
        public SkinManager createFromParcel(Parcel source) {
            return new SkinManager(source);
        }

        public SkinManager[] newArray(int size) {
            return new SkinManager[size];
        }
    };
    private static final double DISABLED_COLOR_ALPHA = 0.25d;
    private static final double MAXIMUM_TINT_INTENSITY = 0.85d;
    private static final double MINIMUM_TINT_INTENSITY = 0.55d;
    private final int backgroundImage;
    private final int primaryColor;
    private final Skin skin;
    private final Tint tint;
    private final double tintIntensity;

    /* renamed from: com.facebook.accountkit.ui.SkinManager$Skin */
    public enum Skin {
        NONE,
        CLASSIC,
        CONTEMPORARY,
        TRANSLUCENT
    }

    /* renamed from: com.facebook.accountkit.ui.SkinManager$Tint */
    public enum Tint {
        WHITE,
        BLACK
    }

    public SkinManager(Skin skin2, int primaryColor2, int backgroundImage2, Tint tint2, double tintIntensity2) {
        super(-1);
        this.skin = skin2;
        this.primaryColor = primaryColor2;
        this.backgroundImage = backgroundImage2;
        if (hasBackgroundImage()) {
            this.tint = tint2;
            this.tintIntensity = Math.min(MAXIMUM_TINT_INTENSITY, Math.max(MINIMUM_TINT_INTENSITY, tintIntensity2));
            return;
        }
        this.tint = Tint.WHITE;
        this.tintIntensity = MINIMUM_TINT_INTENSITY;
    }

    public SkinManager(Skin skin2, int primaryColor2) {
        this(skin2, primaryColor2, -1, Tint.WHITE, MINIMUM_TINT_INTENSITY);
    }

    private SkinManager(Parcel source) {
        super(source);
        this.skin = Skin.values()[source.readInt()];
        this.primaryColor = source.readInt();
        this.backgroundImage = source.readInt();
        this.tint = Tint.values()[source.readInt()];
        this.tintIntensity = source.readDouble();
    }

    public Skin getSkin() {
        return this.skin;
    }

    public boolean hasBackgroundImage() {
        return this.backgroundImage >= 0;
    }

    /* access modifiers changed from: 0000 */
    public int getBackgroundImageResId() {
        return this.backgroundImage;
    }

    public Tint getTint() {
        return this.tint;
    }

    public double getTintIntensity() {
        return this.tintIntensity;
    }

    /* access modifiers changed from: 0000 */
    public int getDisabledColor(int color) {
        int backgroundColor;
        switch (this.tint) {
            case WHITE:
                backgroundColor = -1;
                break;
            default:
                backgroundColor = AirMapInterface.CIRCLE_BORDER_COLOR;
                break;
        }
        return Color.rgb((int) ((DISABLED_COLOR_ALPHA * ((double) Color.red(color))) + (0.75d * ((double) Color.red(backgroundColor)))), (int) ((DISABLED_COLOR_ALPHA * ((double) Color.green(color))) + (0.75d * ((double) Color.green(backgroundColor)))), (int) ((DISABLED_COLOR_ALPHA * ((double) Color.blue(color))) + (0.75d * ((double) Color.blue(backgroundColor)))));
    }

    public int getPrimaryColor() {
        return this.primaryColor;
    }

    /* access modifiers changed from: 0000 */
    public int getTintColor() {
        switch (this.tint) {
            case WHITE:
                return Color.argb((int) (this.tintIntensity * 255.0d), 255, 255, 255);
            default:
                return Color.argb((int) (this.tintIntensity * 255.0d), 0, 0, 0);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getTextColor() {
        switch (getTint()) {
            case BLACK:
                return -1;
            default:
                return AirMapInterface.CIRCLE_BORDER_COLOR;
        }
    }

    public Fragment getBodyFragment(LoginFlowState state) {
        return super.getBodyFragment(state);
    }

    public ButtonType getButtonType(LoginFlowState state) {
        return super.getButtonType(state);
    }

    public Fragment getFooterFragment(LoginFlowState state) {
        return super.getFooterFragment(state);
    }

    public Fragment getHeaderFragment(LoginFlowState state) {
        return super.getHeaderFragment(state);
    }

    public TextPosition getTextPosition(LoginFlowState state) {
        return super.getTextPosition(state);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.skin.ordinal());
        dest.writeInt(this.primaryColor);
        dest.writeInt(this.backgroundImage);
        dest.writeInt(this.tint.ordinal());
        dest.writeDouble(this.tintIntensity);
    }
}
